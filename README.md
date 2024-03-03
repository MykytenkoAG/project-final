## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:
1. Выполнено.
2. Выполнено.
3. Выполнено.
4. Выполнено.
Тесты можно запускать в профилях "h2" и "pg". Профиль следует задавать в переменных окружения.
<br>
SPRING_PROFILES_ACTIVE=h2
<br>
или
<br>
SPRING_PROFILES_ACTIVE=pg, предварительно запустив контейнер с сервером тестовой БД
<br>
```shell
   docker-compose up -d postgres-db-test
```
Название одной из колонок таблицы "contact" пришлось
поменять, так как "value" в h2 является зарезервированным словом.

5. Выполнено.
6. Выполнено.
7. Выполнено.
<br>
Для просмотра всех назначенных задаче тэгов необходимо отправить GET-запрос
по адресу <br> [/api/tags/{taskId}]()
<br>
Для того, чтобы назначить задаче тэги необходимо отправить
PATCH-запрос по адресу <br> [/api/tags/{taskId}]()
<br>
Для того, чтобы найти все задачи с определенными тегами
необходимо отправить GET-запрос по адресу <br>
[/api/tags/tasks]()
<br>

8. Выполнено. Для получения значения времени необходимо отправить GET-запрос вида
<br>
[/api/tasks/time-in-testing/{taskId}]()
<br> или <br>
[/api/tasks/time-in-progress/{taskId}]()
<br>
Результатом запроса будет значение времени выполнения в формате часы:минуты:секунды.

9. Выполнено.

10. Выполнено.
```shell
   docker-compose up
```
```shell
   docker-compose stop
```
```shell
   docker-compose down
```

11. Выполнено. Для проверки:
http://localhost:8080/?lang=en
<br>
http://localhost:8080/?lang=ru
<br>

12. Выполнено частично. При аутентификации JWT формируется, при POST-запросах с форм JWT валидируется.
Но, существует одна серьезная проблема, которую мне решить не удалось. Дело в том, что
во вьюхах много обычных гиперссылок.
Я в принципе, понимаю, как перехватывать нажатие на них и отправлять GET-запрос с хедером
аутентификации, но вот что делать с ответом от сервера при помощи JavaScript, мне не совсем
понятно. Если просто отрисовать весь html, пришедший от сервера, то автоматически загрузятся
еще и ненужные JS-скрипты, да и адрес в строке браузера останется неизменным.
В интернете можно найти примеры, но они реализованы на различных фреймворках, изучение
которых в данном курсе затронуто не было.
Поэтому в данном проекте перехватываются только события отправки форм, добавляются хедеры
аутентификации и при помощи AJAX отправляются на сервер (реализация в файле resources/static/js/forms.js).

***Приложение всегда необходимо запускать со следующими переменными окружения (при тестах вместо "prod" выбрать профиль "pg" или "h2")***:
<br>
DB_URL=jdbc:postgresql://localhost:5432/jira
<br>
DB_USER=jira
<br>
DB_PWD=JiraRush
<br>
SPRING_URL=http://localhost:8080
<br>
E_PG_DATA=/var/lib/postgresql/data/pgdata
<br>
E_DB=jira
<br>
E_DB_TEST=jira-test
<br>
E_CLIENT_ID_GITHUB=3d0d8738e65881fff266
<br>
E_CLIENT_ID_GOOGLE=329113642700-f8if6pu68j2repq3ef6umd5jgiliup60.apps.googleusercontent.com
<br>
E_CLIENT_ID_GITLAB=b8520a3266089063c0d8261cce36971defa513f5ffd9f9b7a3d16728fc83a494
<br>
E_MAIL_HOST=smtp.gmail.com
<br>
E_MAIL_USERNAME=jira4jr@gmail.com
<br>
E_MAIL_PASSWORD=zdfzsrqvgimldzyj
<br>
E_MAIL_PORT=587
<br>
SPRING_PROFILES_ACTIVE=prod
<br>