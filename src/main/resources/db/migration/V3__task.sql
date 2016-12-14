CREATE TABLE `task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `taskname` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `cron` varchar(50) DEFAULT NULL,
  `resource` varchar(100) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  `lastaccessstarttime` datetime DEFAULT NULL,
  `lastaccessendtime` datetime DEFAULT NULL,
  `lastaccessmsg` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `startHour` int(11) DEFAULT NULL,
  `startMin` int(11) DEFAULT NULL,
  `startSec` int(11) DEFAULT NULL,
  `timeunit` varchar(10) DEFAULT NULL,
  `triggertype` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `isdelete` int(11) DEFAULT '0',
  `off` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8;

CREATE TABLE `tasklog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `taskid` int(11) DEFAULT NULL,
  `taskname` varchar(200) DEFAULT NULL,
  `starttime` timestamp NULL DEFAULT NULL,
  `endtime` timestamp NULL DEFAULT NULL,
  `message` varchar(2000) DEFAULT NULL,
  `reqsuccess` int(11) DEFAULT NULL,
  `ressuccess` int(11) DEFAULT NULL,
  `resmessage` varchar(2000) DEFAULT NULL,
  `restime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15430 DEFAULT CHARSET=utf8;

