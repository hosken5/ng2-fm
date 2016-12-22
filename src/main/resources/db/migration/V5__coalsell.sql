CREATE TABLE `coalsell` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `teamid` int(11) DEFAULT NULL,
  `financecellid` int(11) DEFAULT NULL,
  `uppercomp` varchar(200) DEFAULT NULL,
  `lowercomp` varchar(200) DEFAULT NULL,
  `ywx` varchar(200) DEFAULT NULL,
  `rzfs` varchar(2) DEFAULT NULL,
  `htzjll` decimal(10,2) DEFAULT NULL,
  `zrll` decimal(10,2) DEFAULT NULL,
  `yfkbl` decimal(10,2) DEFAULT NULL,
  `jsl` int(11) DEFAULT NULL,
  `upperjsrq` date DEFAULT NULL,
  `lowerjsrq` date DEFAULT NULL,
  `ysfs` varchar(11) DEFAULT NULL,
  `fyrq` date DEFAULT NULL,
  `xshsze` decimal(11,2) DEFAULT NULL,
  `cgmze` decimal(11,2) DEFAULT NULL,
  `rmtzsy` decimal(11,2) DEFAULT NULL,
  `upperzjzy` decimal(11,2) DEFAULT NULL,
  `lowerzjzy` decimal(11,2) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL  COMMENT '',
  `lastupdatetime` timestamp NULL DEFAULT NULL,
  `creator` VARCHAR (11)  DEFAULT  NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8