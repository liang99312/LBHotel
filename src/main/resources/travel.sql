CREATE DATABASE travel DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `travel`.`a01` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bh` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `a0111` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `a0105` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `a01pic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dm` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `a01qx` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`id`))ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into `travel`.a01 (id, bh, mc, password, state,a01qx) 
  values(1,'sa','super系统管理员','sa123',9,'-1');

CREATE TABLE `travel`.`kehu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dm` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dz` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lxr` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lxdh` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bz` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT 0,
  PRIMARY KEY (`id`))ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `travel`.`xianlu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dm` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bz` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT 0,
  PRIMARY KEY (`id`))ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `travel`.`dijieshe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dm` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bz` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT 0,
  PRIMARY KEY (`id`))ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `travel`.`lvxing` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `kh_id` INT NOT NULL,
  `khmc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ctsj` datetime DEFAULT NULL,
  `ctlx` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ctxz` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ctdjs` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ctfy` FLOAT(7,2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ctlr` FLOAT(7,2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ctfj` FLOAT(7,2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ctbx` FLOAT(7,2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `a01_id` INT,
  `a01mc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bz` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` int(11) DEFAULT 0,
  PRIMARY KEY (`id`))ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;