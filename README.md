API для управления файлами 
=========
Данный проект является серверной частью веб-приложения для управления файлами. Он включает в себя API для создания, получения и списка файлов. Проект использует Spring Boot для создания RESTful API и взаимодействует с базой данных для хранения информации о файлах.

В этом коде представлено RESTful API для управления файлами. Оно разработано с использованием Spring Boot, Spring Data JPA и Lombok.

FileController — это класс, который отвечает за обработку HTTP-запросов. Он использует аннотации @RestController, @Component и @Repository, чтобы указать, что это компонент Spring и репозиторий.

Метод createFile принимает JSON-представление файла, сохраняет его в базе данных и возвращает идентификатор созданного файла.

Метод getFile по идентификатору запрашивает файл из базы данных и возвращает его JSON-представление.

Метод getAllFiles запрашивает список файлов из базы данных, преобразует их в JSON-представление с помощью FileDtoFactory и возвращает список JSON-представлений.

FileDto — это класс, который представляет данные файла в формате JSON. Он использует аннотации Lombok для автоматической генерации геттеров, сеттеров, конструкторов и т.д.

BadRequestException — это пользовательское исключение, которое используется для возврата ошибки HTTP-статуса 400 (неверный запрос) в случае ошибок в запросе.

FileDtoFactory — это класс, который отвечает за преобразование объектов FileEntity в объекты FileDto и наоборот.

FileEntity — это класс, который представляет сущность файла в базе данных. Он наследуется от FileDto и добавляет аннотации JPA для описания таблицы и полей в базе данных.

FileRepository — это интерфейс, который расширяет JpaRepository и определяет методы для работы с сущностью FileEntity в базе данных.

TestTaskApplication — это класс, который содержит метод main и запускает приложение.

Общие решения, которые используются в этом коде:

- **Контроллеры** (**`FileController`**): отвечают за обработку HTTP-запросов и возврат ответов.
- **Сущности** (**`FileEntity`**): представляют данные в базе данных.
- **DTO** (**`FileDto`**): представляют данные в формате JSON для обмена между контроллерами и клиентом.
- **Репозитории** (**`FileRepository`**): отвечают за работу с базой данных.

**Запуск приложения**
----

1. Клонировать репозиторий:

`git clone https://github.com/EkaterinaBezheskaia/CaseLabTestTask.git`

2. Перейти в директорию проекта (где сохранен клон репозитория):

`cd D:\Testing\CaseLabTestTask`

3. Упаковать приложение с помощью Maven:

`mvn -f D:\Testing\CaseLabTestTask\TestTask\pom.xml clean package`

4. Запустить приложение с помощью Java (директория jar указана при обработке Maven):

`java -jar D:\Testing\CaseLabTestTask\TestTask\target\TestTask-0.0.1-SNAPSHOT.jar`

5. Приложение будет доступно по адресу:

`http://localhost:8081`


**Примеры тестовых запросов для проверки API-методов**
----
**1. Создание файла**

Для запроса необходимо создать файл в формате JSON с содержанием в формате:

    {
    "title": "Новый файл",
    "creationDate": "2023-02-20T14:30:00",
    "description": "Это новый файл",
    "base64File": "ваш кодированный файл base64"
    }

Далее выбрать директорию, в которой сохранен файл.

`POST /api/files/{id}`

Пример запроса:

`curl.exe -X POST --user root --data "@test1.json" -H 'content-type: application/json;' http://localhost:8081/api/files/1`

Далее система требует пароль пользователя root: 123456. В ответ получаем id файла.

**2. Получение файла по ID**

`GET /{id}`

Пример запроса:

`curl.exe -X GET --user root http://localhost:8081/1`

Далее система требует пароль пользователя root: 123456. В ответ получаем содержимое файла с id=1.

**3. Получение всех файлов**

`GET /api/files`

Пример запроса:

`curl.exe -X GET --user root http://localhost:8081/api/files`

Далее система требует пароль пользователя root: 123456. В ответ получаем содержимое файлов, отсортированных по времени создания.

Эти команды позволят вам проверить основные функции вашего API, включая создание и получение файлов, а также получение списка всех файлов.
