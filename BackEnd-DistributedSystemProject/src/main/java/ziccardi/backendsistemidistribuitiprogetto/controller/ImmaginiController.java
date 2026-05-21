package ziccardi.backendsistemidistribuitiprogetto.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import ziccardi.backendsistemidistribuitiprogetto.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/immagini")
public class ImmaginiController {

    @Autowired
    private StorageService service;

    @PostMapping("/carica")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        String link = service.uploadFile(file);
        return ResponseEntity.ok().body(link);
    }

    @GetMapping("/downloadNome")
    public ResponseEntity<ByteArrayResource> downloadFileName(@RequestParam(value = "file_name") String fileName){
        System.out.println(fileName);
        byte[] data = service.downloadFileNome(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @GetMapping("/downloadURI")
    public ResponseEntity<InputStreamResource> downloadFileURI(@RequestParam(value = "URIS3_file") String s3Uri) {
        try {
            InputStream inputStream = service.downloadFileURI(s3Uri);
            InputStreamResource resource = new InputStreamResource(inputStream);
            // Estrai il nome del file dall'URI
            String fileName = s3Uri.substring(s3Uri.lastIndexOf("/") + 1);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/elimina")
    public ResponseEntity<String> deleteFile(@RequestParam(value = "file_name") String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
}