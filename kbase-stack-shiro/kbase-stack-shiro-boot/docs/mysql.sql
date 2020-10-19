CREATE TABLE IF NOT EXISTS `sys_user`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `username` VARCHAR(100) NOT NULL,
   `password` VARCHAR(40),
   `salt` VARCHAR(8),
   `username_cn` VARCHAR(100),
   `remark` VARCHAR(200),
   `email` VARCHAR(50),
   `phone` VARCHAR(20),
   `age` int(3),
   `sex` int(1),
   `birthday` DATE,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;