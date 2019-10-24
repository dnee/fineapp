В проекте используется
Spring Boot,
PostgreSQL,
FlyWayDB

Для компиляции необходимо иметь
Maven,
JDK 8 (min)

Перед компиляцией открыть
\src\main\resources\application.properties
заменить в нем 
spring.datasource.url=jdbc:postgresql://localhost/testdb4

spring.datasource.username=testuser4

spring.datasource.password=12345

На ваши настройки подключения к БД

Для сборки необходимо выполнить команду: mvn clean package

При успешной сборке, в папке target появится файл fine-app.war

Задеплоить fine-app.war в Apache Tomcat

При корректных настройках подключения к СУБД FlyWayDB создает необходимые таблицы и заполняет их
