-- 设备安全状态表, 保存设备的安全状态
DROP TABLE IF EXISTS dev_sec_stat;
CREATE TABLE dev_sec_stat (
    id varchar(40) NOT NULL,
    dev_uuid varchar(64) NOT NULL,
    account varchar(64) NOT NULL,
    root_violation_stat int(10) DEFAULT 0,
    tf_violation_stat int(10) DEFAULT 0,
    sim_violation_stat int(10) DEFAULT 0,
    lost_violation_stat int(10) DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE INDEX (dev_uuid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 安全事件报警表, 记录安全报警事件
DROP TABLE IF EXISTS sec_event_alert;
CREATE TABLE sec_event_alert (
    id varchar(40) NOT NULL,
    alert_level varchar(255) DEFAULT '',
    start_time datetime NOT NULL,
    end_time datetime DEFAULT NULL,
    target_id varchar(64) NOT NULL,
    target_type varchar(64) NOT NULL,
    target_name varchar(255) NOT NULL,
    alert_reason varchar(255) NOT NULL,
    alert_stat varchar(255) DEFAULT '0',
    PRIMARY KEY (id),
    INDEX (start_time),
    INDEX (target_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 处罚动作表
DROP TABLE IF EXISTS operation;
CREATE TABLE operation (
    id varchar(40) NOT NULL,
    code varchar(64) NOT NULL UNIQUE,
    name varchar(255) NOT NULL,
    remark varchar(255) DEFAULT '',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 报警和处罚动作关联表
DROP TABLE IF EXISTS alert_operation_rel;
CREATE TABLE alert_operation_rel (
    id varchar(255) NOT NULL,
    alert_id varchar(40) NOT NULL,
    operation_id varchar(40) NOT NULL,
    operation_code varchar(64) NOT NULL,
    operation_stat varchar(64) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO operation(id, code, name, remark) VALUES ('0', 'cmc_op_code_disable_uas_login', 'disable_uas_login', '禁止统一认证用户登录');
INSERT INTO operation(id, code, name, remark) VALUES ('1', 'cmc_op_code_disable_uas_auth', 'disable_uas_auth', '禁止统一认证用户授权');
COMMIT;

