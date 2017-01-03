
 alter table  user modify column   `loginname` varchar(30) NOT NULL COMMENT '登陆名称' ;
 alter table  user modify column  `password` varchar(40) NOT NULL COMMENT '密码' ;
 alter table  user modify column  `passwordsalt` varchar(40) NOT NULL COMMENT '密码盐';
 alter table  user modify column  `email` varchar(45) DEFAULT NULL COMMENT '邮箱';
 alter table  user modify column  `activated` tinyint(1) DEFAULT NULL COMMENT '是否可用';
 alter table  user modify column  `creator` varchar(20) DEFAULT '' COMMENT '通过谁创建';
 alter table  user modify column  `createtime` datetime DEFAULT NULL COMMENT '创建日期';
 alter table  user modify column  `role` varchar(10) DEFAULT '' COMMENT '所属角色 0:admin,1:财务人员，2：销售人员' ;