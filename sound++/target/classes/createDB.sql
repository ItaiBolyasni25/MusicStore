/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  maian
 * Created: Jan 29, 2019
 */
drop database songstore;
create database songstore;
DROP USER IF EXISTS songstore@localhost;
CREATE USER songstore@'localhost' IDENTIFIED WITH mysql_native_password BY 'dawson123' REQUIRE NONE;
GRANT ALL ON songstore.* TO songstore@'localhost';
FLUSH PRIVILEGES;