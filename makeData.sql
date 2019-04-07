1.找到mysql的my.cnf配置文件，将max_heap_table_size改大些，改成4000M，重启下mysql服务即可。

#创建一张内存表
CREATE TABLE `person_info_memory` (  
    `id` INT (7) NOT NULL AUTO_INCREMENT,  
    `account` VARCHAR (10),   
    `name` VARCHAR (20),  
    `area` VARCHAR (20),  
    `title` VARCHAR (20), 
    `motto` VARCHAR (50),
    PRIMARY KEY (`id`),  
    UNIQUE(`account`),
    KEY `index_area_title`(`area`,`title`)
) ENGINE = MEMORY AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8  
#创建一张店铺小数据表
CREATE TABLE `shop_info_small` (  
    `shop_id` INT (2) NOT NULL AUTO_INCREMENT, 
    `shop_name` VARCHAR (20),  
    `person_id` INT (2),
    `shop_profile` VARCHAR (50),
    PRIMARY KEY (`shop_id`),
    UNIQUE(`shop_name`)
) ENGINE = MYISAM AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8  
#创建一张小数据表
CREATE TABLE `person_info_small` (  
    `id` INT (2) NOT NULL AUTO_INCREMENT,  
    `account` VARCHAR (10),   
    `name` VARCHAR (20),  
    `area` VARCHAR (20),  
    `title` VARCHAR (20), 
    `motto` VARCHAR (50),
    PRIMARY KEY (`id`),  
    UNIQUE(`account`),
    KEY `index_area_title`(`area`,`title`) 
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8  
#创建一张大数据表
CREATE TABLE `person_info_large` (  
    `id` INT (7) NOT NULL AUTO_INCREMENT,  
    `account` VARCHAR (10),   
    `name` VARCHAR (20),  
    `area` VARCHAR (20),  
    `title` VARCHAR (20), 
    `motto` VARCHAR (50),
    PRIMARY KEY (`id`),  
    UNIQUE(`account`),
    KEY `index_area_title`(`area`,`title`) 
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8  

#创建一个能够返回随机字符串mysql自定义函数
DELIMITER $$
CREATE FUNCTION `rand_string`(n INT) RETURNS varchar(255) CHARSET utf8
BEGIN
DECLARE chars_str varchar(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
DECLARE return_str varchar(255) DEFAULT '' ;
DECLARE i INT DEFAULT 0;
WHILE i < n DO
SET return_str = concat(return_str,substring(chars_str , FLOOR(1 + RAND()*12 + RAND()*50),1));
SET i = i +1;
END WHILE;
RETURN return_str;
END $$
#创建一个批量往内存表里灌数据的存储过程
DELIMITER $$
CREATE  PROCEDURE `add_person_info_large`(IN n int)  
BEGIN    
  DECLARE i INT DEFAULT 1;  
    WHILE (i <= n ) DO  
      INSERT into person_info_memory  (account,name,area,title, motto) VALUEs (rand_string(10),rand_string(20),rand_string(20) ,rand_string(20),rand_string(50));  
            set i=i+1;  
    END WHILE;  
END $$
#创建一个批量往小表里灌数据的存储过程
DELIMITER $$
CREATE  PROCEDURE `add_person_info_small`(IN n int)  
BEGIN    
  DECLARE i INT DEFAULT 1;  
    WHILE (i <= n ) DO  
      INSERT into person_info_small  (account,name,area,title, motto) VALUEs (rand_string(10),rand_string(20),rand_string(20) ,rand_string(20),rand_string(50));  
            set i=i+1;  
    END WHILE;  
END $$
#调用存储过程，插入100万条数据(由于我们的随机数可能会出现重复的情况，所以插入的条数也许达不到100万便会出错停止,可以自行加入些随机数优化一下)
CALL add_person_info_large(1000000);
#调用存储过程，插入10条数据到小表里
CALL add_person_info_small(2);
#将内存表的数据移动到person_info_large中
insert into person_info_large(account,name,area,title,motto)
select account,name,area,title,motto from person_info_memory;
#若遇数据冲突没法到达100万的情况，通过变换唯一键值的方式来插入数据
insert into person_info_large(account,name,area,title,motto)
select concat(substring(account, 2),'a'),concat(substring(name, 2),'a'),area,title,motto from person_info_memory;
insert into person_info_large(account,name,area,title,motto)
select concat(substring(account, 2),'b'),concat(substring(name, 2),'b'),area,title,motto from person_info_memory;


CREATE TABLE `test_myisam` (  
    `id` INT (2) NOT NULL AUTO_INCREMENT, 
    `name` VARCHAR (20),  
    `unique_id` INT (2),
    `normal_id` INT (2),
    PRIMARY KEY (`id`),
    UNIQUE(`unique_id`),
    INDEX(`normal_id`)
) ENGINE = MYISAM AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 

CREATE TABLE `test_innodb` (  
    `id` INT (2) NOT NULL AUTO_INCREMENT, 
    `name` VARCHAR (20),  
    `unique_id` INT (2),
    `normal_id` INT (2),
    PRIMARY KEY (`id`),
    UNIQUE(`unique_id`),
    INDEX(`normal_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 
insert into test_innodb (name,unique_id,normal_id) values('a',1,1),('d',4,4),('h',8,8),('k',11,11);
insert into test_myisam (name,unique_id,normal_id) values('a',1,1),('d',4,4),('h',8,8),('k',11,11);
