# **Процедура запуска авто-тестов**

## **Precondition**

1. установлена Java не ниже версии 8.0 
1. Docker Desktop
1. IntelliJ Idea Ultimate

## **Необходимые шаги**

1. Клонировать [репозиторий](https://github.com/EkaterinaPeregudova/Diplom),
1. Запустить Docker командой **`docker-compose up`**
1. Запустить приложение из каталога **artifacts** командой:
    * **`java -jar aqa-shop.jar`** с использованием mySQL
    * **`java -jar -Dspring.profiles.active=postgres aqa-shop.jar`** с использованием postgreSQL
1. Запустить авто-тесты командой:
    * **`gradlew clean test`** для работы с mysSQL
    * **`gradlew clean test -Durl=jdbc:postgresql://localhost:5432/app`** для работы с postgreSQL
1. Создать отчет командой **`gradlew allureReport`**
1. Просмотреть отчет командой **`gradlew allureServe`**