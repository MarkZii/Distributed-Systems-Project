# Distributed Questionnaire Management System

## 📖 Overview
This project is a distributed web application designed to allow users to create, administer, and analyze online questionnaires. The platform leverages Amazon AWS cloud resources to ensure a scalable, secure, and high-performing user experience.

## ✨ Features
* **User Authentication:** Secure user registration, login, and logout capabilities.
* **Questionnaire Creation:** Registered users can build custom questionnaires with multiple-choice questions, set a specific deadline in days, and upload images for individual questions.
* **Email Invitations:** Creators can invite specific users to complete a newly created questionnaire via automated email notifications.
* **Submission Limits:** Users can complete available (non-expired) questionnaires exactly once.
* **Real-time Analytics:** Any user can view partial results during an active campaign and final results once the questionnaire expires, displaying the total votes for each answer.

## 🏗️ Architecture & Technologies
The system's architecture is divided into a frontend and a backend, relying entirely on AWS cloud services for its infrastructure.

### Frontend
* **Framework:** Flutter (Dart) for a responsive and interactive user interface.
* **Hosting:** AWS CloudFront, which distributes static and dynamic contents globally through edge locations to reduce latency.

### Backend
* **Framework:** Spring Boot (Java 17) to handle business logic and cloud integrations.
* **Deployment:** AWS Elastic Beanstalk, utilizing a containerized environment for automated scaling, load balancing, and infrastructure management.

### AWS Cloud Services
* **Database (NoSQL):** AWS DynamoDB for storing questionnaires, user submissions, and results.
* **Storage:** AWS Simple Storage Service (S3) for uploading and downloading question-related images.
* **Authentication:** AWS Cognito for secure user management, coupled with an AWS Lambda function to automatically verify user emails during registration.
* **Messaging:** AWS Simple Email Service (SES) to dispatch individual and bulk email invitations.

## 🚀 Live Demo
**Note:** The original live demo hosted at `http://frontendquestionario.s3-website.eu-north-1.amazonaws.com` is currently **offline**. The AWS resources have been deactivated to prevent ongoing cloud infrastructure costs.

For more details, please read the "Report_Project_Sistributed_Systems.pdf".
