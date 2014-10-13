CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `salt` varchar(36) NOT NULL,
  `username` varchar(50) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qleu8ddawkdltal07p8e6hgva` (`role_id`),
  CONSTRAINT `FK_qleu8ddawkdltal07p8e6hgva` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `linklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inserttime` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `state` int(11) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `url` varchar(1024) NOT NULL,
  `insertuser_id` int(11) DEFAULT NULL,
  `updateuser_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1dacdgtybpap4j09xocyp029p` (`insertuser_id`),
  KEY `FK_wx6sfpm5jqvjdwrdhph4bbsa` (`updateuser_id`),
  CONSTRAINT `FK_wx6sfpm5jqvjdwrdhph4bbsa` FOREIGN KEY (`updateuser_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_1dacdgtybpap4j09xocyp029p` FOREIGN KEY (`insertuser_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into role (id, name) VALUES (1, 'ADMIN');
insert into user (id, email, firstname, lastname, password, salt, username, role_id) VALUES (1, 'patrick.boesch@students.ffhs.ch', 'Admin', 'Hans', '4962D99791FAAAC073E80FE7E639590DC78E82CA191BBBB6F197FAC351A171C54A1D95FDBC50358889AF6E0D7A40EEB25EEA2A61BFC3AC76EF660E2EE8B2F848', 'fc41751f-05c6-45ea-98e3-709e50bca8f3', 'admin', 1);