drop database songstore;
create database songstore;
use songstore;
DROP USER IF EXISTS songstore@localhost;
CREATE USER songstore@'localhost' IDENTIFIED WITH mysql_native_password BY 'dawson123' REQUIRE NONE;
GRANT ALL ON songstore.* TO songstore@'localhost';
FLUSH PRIVILEGES;