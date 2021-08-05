# Pizza app

## Starting the application
- Navigate to the project directory in a console
- Run the command: **mvnw spring-boot:run**
- The application will accept connections at **localhost:8080**

## Uploading pizzas
- The relative path for the upload is: **api/pizzas/upload**
- The application accepts csv input, in **multipart** content type
- The field name is **file**
- There is an example file: src/test/resources/pizzas.csv

## Calculate best combination for budget
- The relative path for the calculator is: **/order**
- The accepted values are from 0 to 100000
