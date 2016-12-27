CREATE TABLE `billinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `coalsellid` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `lastupdatetime` timestamp NULL DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `hkinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `coalsellid` int(11) DEFAULT NULL,
  `hkrq` date DEFAULT NULL,
  `hkje` decimal(11,2) DEFAULT NULL,
  `hkfs` varchar(2) DEFAULT NULL,
  `ll` int(11) DEFAULT NULL,
  `dqr` date DEFAULT NULL,
  `txts` int(11) DEFAULT NULL,
  `txx` decimal(11,2) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
   `createtime` timestamp NULL DEFAULT NULL,
  `lastupdatetime` timestamp NULL DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `paymentinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `coalsellid` int(11) DEFAULT NULL,
  `fkrq` date DEFAULT NULL,
  `fkje` decimal(11,2) DEFAULT NULL,
  `jxts` int(11) DEFAULT NULL,
  `ll` decimal(11,2) DEFAULT NULL,
  `rmtsy` decimal(11,2) DEFAULT NULL,
  `hkrq` date DEFAULT NULL,
  `hkje` decimal(11,2) DEFAULT NULL,
  `hkjxts` int(11) DEFAULT NULL,
  `hkll` decimal(11,2) DEFAULT NULL,
  `hkrmtsy` decimal(11,2) DEFAULT NULL,
   `createtime` timestamp NULL DEFAULT NULL,
  `lastupdatetime` timestamp NULL DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `bz` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;