## Проект RandomPeopleGenerator ##

***Опсиание.***

Приложение предназначено для генерации случайных пользователей со следующими данными:

1. Фамилия 
2. Имя
3. Отчество
4. Пол
5. Дата рождения
4. ИНН - для физического лица из 77 региона, валидный.
5. Индекс - рандомное значение.
5. Страна
6. Область
6. Город
7. Улица
8. Дом
9. Квартира

В папке *resources* проекта находятся файлы формата .txt, использующиеся для генерации случайных пользователей. 

Данные о пользователях записываются в файлы формата Exel и PDF.

Данные о пользователях создаются либо из имеющихся в проекте данных, либо с помощью API (URL: https://randomuser.me/api/). Если отсутствует интернет-соединение, то приложение выводит сообщение: *Ошибка получения данных от сервера!* и создает два файла. 

Данные о пользователях заносится в БД со структурой:
address ( id int auto_increment not null, postcode varchar(256), country varchar(256), region varchar(256), city varchar(256), street varchar(256), house int, flat int, primary key (id) )

persons ( id int auto_increment not null, surname varchar(256), name varchar(256), middlename varchar(256), birthday date, gender varchar(1), inn varchar(12), address_id int not null, foreign key (address_id) references address(id), primary key (id) )

**Запуск проекта.**

1.  В файле database.properties поменять: url, username, password, на данные своей БД.
2.  Запустить командную строку от имени администратора.
1. Перейти в папку с проектом при помощи комманды cd + путь. Пример: cd C:\Users\Карина\IdeaProjects\RandomPeopleGenerator
1. Выполнить команду: mvn compile
1. Выполнить команду: mvn exec:java -Dexec.mainClass="Generator"
1. В папке resources (путь:src\main\resources) были созданы два файла "Files" форматов: pdf и xls.

