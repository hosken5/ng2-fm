CREATE TABLE `paymentinfozy` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `coalsellid` int(11) DEFAULT NULL,
  `fkrq` date DEFAULT NULL,
  `fkje` decimal(11,2) DEFAULT NULL,
  `jxts` int(11) DEFAULT NULL,
  `ll` decimal(11,2) DEFAULT NULL,
  `rmtsy` decimal(11,2) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;