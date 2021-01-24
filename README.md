# Patient-Billing Module

Patient billing module is spring boot service, created to manage Patient, Physician and Visit. Billing module can be executed seprately.

# Running application
- DB script needs to execute first
   - to execute db script, you can MySQLWorkbench or mysql cmd tool
        ```sh 
        source db1.sql
        ```

- To run Patient module
    - change diretory to <source_code>\patient-api
    - modify the database details in tenants\tenant1.json file
    - execute 
        ```sh
            mvn spring-boot:run
        ```
        to start the api
        Using postman, or any HttpRequest tool, 
        http://localhost:8080/patients/?tenantId=tenant1
            where, tenantId is same as json files inside tenants folder.
    
    Similary, you can execute billing api
    - change diretory to <source_code>\billing-api
    - modify the database details in tenants\tenant1.json file
    - execute 
        ```sh
            mvn spring-boot:run
        ```
        http://localhost:8082/billing/?tenantId=tenant1
    
    CORS settings are enabled for this application.
        
    Multiple tenants can be used with same codebase. Meaning, this application is multi tenant, to achive cross db's support.
    
    Mainly, we have used SpringBoot and JDBCTemplates to achieve this.
    
    Web application is under developmet for the same, where i will be using Angular with bootstrap for faster development.
