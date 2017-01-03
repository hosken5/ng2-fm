CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '用户名称',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `loginname` varchar(30) NOT NULL COMMENT '登陆名称',
  `password` varchar(40) NOT NULL COMMENT '密码',
  `passwordsalt` varchar(40) NOT NULL COMMENT '密码盐',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `activated` tinyint(1) NOT NULL COMMENT '是否可用',
  `creator` varchar(20) NOT NULL COMMENT '通过谁创建',
  `createtime` datetime NOT NULL COMMENT '创建日期',
  `role` varchar(10) not null  comment '所属角色 0:admin,1:财务人员，2：销售人员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`),
  UNIQUE KEY `loginname_unique` (`loginname`)
);