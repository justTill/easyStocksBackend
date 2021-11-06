# easyStocksBackend

## getting Started (locally)
1. Fill application properties or create application-local.properties file
2. Overwrite Database variables (url, username, password)
3. Start local Postgres DB with following command (change the command accordingly to your application properties)
```
docker run -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=easystocks -p 5432:5432 -d postgres:14.0-alpine 
``` 
Start Spring Boot application via Intellij or other IDE 