# Distributed Questionnaire Management System

## 📖 Overview
[cite_start]This project is a distributed web application designed to allow users to create, administer, and analyze online questionnaires[cite: 14]. [cite_start]The platform leverages Amazon AWS cloud resources to ensure a scalable, secure, and high-performing user experience[cite: 21].

## ✨ Features
* [cite_start]**User Authentication:** Secure user registration, login, and logout capabilities[cite: 56].
* [cite_start]**Questionnaire Creation:** Registered users can build custom questionnaires with multiple-choice questions, set a specific deadline in days, and upload images for individual questions[cite: 34].
* [cite_start]**Email Invitations:** Creators can invite specific users to complete a newly created questionnaire via automated email notifications[cite: 35].
* [cite_start]**Submission Limits:** Users can complete available (non-expired) questionnaires exactly once[cite: 18, 38].
* [cite_start]**Real-time Analytics:** Any user can view partial results during an active campaign and final results once the questionnaire expires, displaying the total votes for each answer[cite: 44, 45, 49].

## 🏗️ Architecture & Technologies
[cite_start]The system's architecture is divided into a frontend and a backend, relying entirely on AWS cloud services for its infrastructure[cite: 51, 67, 69].

### Frontend
* [cite_start]**Framework:** Flutter (Dart) for a responsive and interactive user interface[cite: 72, 290].
* [cite_start]**Hosting:** AWS CloudFront, which distributes static and dynamic contents globally through edge locations to reduce latency[cite: 74, 75].

### Backend
* [cite_start]**Framework:** Spring Boot (Java 17) to handle business logic and cloud integrations[cite: 227].
* [cite_start]**Deployment:** AWS Elastic Beanstalk, utilizing a containerized environment for automated scaling, load balancing, and infrastructure management[cite: 83, 84].

### AWS Cloud Services
* [cite_start]**Database (NoSQL):** AWS DynamoDB for storing questionnaires, user submissions, and results[cite: 86, 87].
* [cite_start]**Storage:** AWS Simple Storage Service (S3) for uploading and downloading question-related images[cite: 262].
* [cite_start]**Authentication:** AWS Cognito for secure user management [cite: 236][cite_start], coupled with an AWS Lambda function to automatically verify user emails during registration[cite: 274].
* [cite_start]**Messaging:** AWS Simple Email Service (SES) to dispatch individual and bulk email invitations[cite: 254].

## 🚀 Live Demo
[cite_start]**Note:** The original live demo hosted at `http://frontendquestionario.s3-website.eu-north-1.amazonaws.com` [cite: 318] is currently **offline**. The AWS resources have been deactivated to prevent ongoing cloud infrastructure costs.

For more details, please read the "Report_Project_Sistributed_Systems.pdf".
