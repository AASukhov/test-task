Приложение доступно на порту 8080.

При помощи flyway создается база данных из трех таблиц: clients, phones, emails.

Доступные эндпоинты:

1) @PostMapping ("/api/client/new") - создание нового клиента

В теле запроса информация вида:
```
{
  "login":"alex"
}
```

2) @PostMapping("/api/client/{client-id}/contact/new") - добавление нового контакта: телефона или почты
{client-id} - id клиента из таблицы clients

В теле запроса информация вида:
```
{
  "phone":123456789,
  "email":null,
  "type":"PHONE"
}
```
Если добавляется телефон, то type = "PHONE", если почтовый адрес, то type = "EMAIL"

3) @GetMapping("/api/client/all") - получение списка всех клиентов
4) @GetMapping("/api/client/{client-id}/info") - получение информации о клиенте с id = {client-id}
5) @GetMapping("/api/client/{client-id}/contact") - получение всех контактов, связанных с клиентом с id = {client-id}. 
   Контакты 
   сгруппированы по признаку принадлежности к телефонам или почтовым адресам.
6) @GetMapping("/api/client/{client-id}/contact/?type=......") - получение всех контактов указанного в строке 
   запроса параметра type (EMAIL или PHONE), связанных с клиентом с id = {client-id}. 