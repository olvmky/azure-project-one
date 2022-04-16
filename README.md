# Expense Reimbursement System 
### Spring Boot Java SRE/Devops

## Introduction
The Expense Reimbursement System will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Application Details
Build on two different APIs. 
Connected by Azure Postgre Datbase on jdbc:postgresql://project-one.postgres.database.azure.com:5432/project-one
Scripts can found from projectOne/Scripts.sql
A CICD piplines have been built for both APIs with following:
- Maven clean, package
- Sonar Analysis Report
- Docker build and push
- deploy to Kubernetes
With access to Granfana with Loki & Prometheus 

