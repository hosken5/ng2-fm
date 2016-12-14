CREATE TABLE `resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `port` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `lastupdatetime` timestamp NULL DEFAULT NULL,
  `valid` int(11) DEFAULT NULL,
  `validtime` timestamp NULL DEFAULT NULL,
  `lastaccessstarttime` timestamp NULL DEFAULT NULL,
  `lastaccessstatus` varchar(11) DEFAULT NULL,
  `lastaccessmsg` varchar(200) DEFAULT NULL,
  `lastaccessendtime` timestamp NULL DEFAULT NULL,
  `fullurl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;