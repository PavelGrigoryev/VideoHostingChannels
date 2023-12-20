# VideoHostingChannels веб-сервис для работы с каналами видео хостинга на Spring Boot

## Автор: [Grigoryev Pavel](https://pavelgrigoryev.github.io/GrigoryevPavel/)

***

### Технологии, которые я использовал на проекте:

* Java 17
* Gradle 8.4
* Lombok plugin 8.4
* Postgresql 15.4
* Spring-boot 3.1.5
* Spring-boot-starter-data-jpa
* Spring-boot-starter-web
* Spring-boot-starter-validation
* Liquibase
* Mapstruct 1.5.5.Final

***

### Инструкция для запуска приложения локально:

1. У вас должна быть установлена
   [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html),
   [Intellij IDEA Ultimate](https://www.jetbrains.com/idea/download/),
   [Postgresql](https://www.postgresql.org/download/) (P.S: Postgresql можно развернуть в докере).
2. В Postgresql нужно создать базу данных. Как пример: "video_hosting_channels". Sql: CREATE DATABASE
   video_hosting_channels.
3. В [application.yaml](src/main/resources/application.yaml) в строчке №6 введите ваш username для Postgresql, в строчке
   №7 введите ваш password для Postgresql.
4. При запуске приложения Liquibase сам создаст таблицы и наполнит их дефолтными значениями.
5. [ChannelAvatarUpdater](src/main/java/ru/clevertec/videohostingchannels/listener/ChannelAvatarUpdater.java) вставит в
   каналы аватарки. Он работает если в [application.yaml](src/main/resources/application.yaml) в строке №3 активный
   профиль dev. Если поставить другой профиль, то аватар значения дефолтных каналов при первом запуске не обновятся и
   будут null.
6. Приложение готово к использованию.

***

### Http Запросы

* [users.http](src/main/resources/http/users.http) для пользователей
* [channels.http](src/main/resources/http/channels.http) для каналов
* [subscriptions](src/main/resources/http/subscriptions.http) для подписок

***
