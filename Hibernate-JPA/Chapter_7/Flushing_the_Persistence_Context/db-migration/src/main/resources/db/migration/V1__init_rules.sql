DROP TABLE IF EXISTS `security_rule_template`;
CREATE TABLE `security_rule_template` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `status` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  `org_name` varchar(255) DEFAULT NULL,
  `org_code` varchar(200) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `security_rule`;
CREATE TABLE `security_rule` (
  `id` varchar(32) NOT NULL,
  `rule_template_id` varchar(32) NOT NULL,
  `jcontent` text,
  `config_type` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `security_rule_distribution`;
CREATE TABLE `security_rule_distribution` (
  `id` varchar(32) NOT NULL,
  `rule_template_id` varchar(32) NOT NULL,
  `target_id` varchar(32) DEFAULT NULL,
  `target_type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;