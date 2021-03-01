数据库名： enjoy_school.db
Admin表:
-------------------------------------
|   name    |  type   | primary key |
| username  |  text   |    Y        |
| passwd    |  text   |    N        |
| realname  |  text   |    N        |
| email     |  text   |    N        |
| age       | integer |    N        |
| tele      |  text   |    N        |
| gender    |  text   |    N        |
| headimg   |  text   |    N        |
-------------------------------------
News表：
-------------------------------------
|   name    |  type   | primary key |
| title     |  text   |    Y        |
| content   |  text   |    N        |
| link      |  text   |    N        |
-------------------------------------
Printer表：
-----------------------------------------
|   name        |  type   | primary key |
|printer_id     |  text   |    Y        |
|printer_address| text    |    N        |
| printer_usage | boolean |    N        |
|printer_imageId| text    |    N        |
-----------------------------------------
Washer表：
-------------------------------------
|   name     |  type   | primary key |
| wash_id    |  text   |    Y        |
| wash_usage |  text   |    N        |
| wash_time  |  text   |    N        |
|wash_address| text    |    N        |
|wash_imageId| text    |    N        |
-------------------------------------
SecondHandBooks
--------------------------------------
|   name      |  type   | primary key |
| book_id     |  text   |    Y        |
| book_name   |  text   |    N        |
| book_price  |  text   |    N        |
| book_imageId| text    |    N        |
--------------------------------------

Kitchen
----------------------------------------
|   name        |  type   | primary key |
| kitchen_id    |  text   |   Y         |
|kitchen_address|  text   |   N         |
|kitchen_imageId|  text   |   N         |
| kitchen_wait  |  text   |   N         |
| kitchen_time  |  text   |   N         |
-----------------------------------------