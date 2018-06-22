CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `pname` varchar(45) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
);
 
INSERT INTO `product` (`id`,`pname`,`quantity`) VALUES
 (1,'Mouse',50),
 (2,'Keyboard',5),
 (3,'Monitor',34);