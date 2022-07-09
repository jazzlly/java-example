-- 消息meta信息表
-- 消息meta信息表由同步客户端负责保存，修改。
-- 该表保存已经获取到的消息的offset。
-- 当业务服务重启时或发生异常，进行恢复时，
-- 可从该offset之后向消息中心获取消息，而不用从头开始获取消息。
--
DROP TABLE IF EXISTS sync_client_meta;
CREATE TABLE sync_client_meta (
    id varchar(32) NOT NULL,
    kaf_topic varchar(255) NOT NULL UNIQUE 
            COMMENT 'topic of the kafka message',
    kaf_msg_offset bigint(19) NOT NULL 
            COMMENT 'offset of the topic which has bee comsumed',
    PRIMARY KEY (id)
);

-- 业务事件表
-- 业务事件表保存获取到的业务事件。消费者IO线程负责将原始消息转化为业务事件，
-- 并保存到本地数据库。
DROP TABLE IF EXISTS sync_buss_event;
CREATE TABLE sync_buss_event (
    id varchar(32) NOT NULL,
    kaf_topic varchar(255) NOT NULL 
            COMMENT 'topic of the original kafka message',
    kaf_partition int(10) NOT NULL 
            COMMENT 'partition of the original kafka message',
    kaf_msg_offset bigint(20) NOT NULL 
            COMMENT 'offset of the message',
    kaf_msg_key varchar(255) NOT NULL 
            COMMENT 'key of the message',
    kaf_msg_value text NOT NULL 
            COMMENT 'value of the message',
    kaf_timestamp datetime(3) NOT NULL
            COMMENT 'timestamp of the message',
    evt_type int(10) NOT NULL 
            COMMENT 'type the business event converted from the message',
    evt_json_content text NOT NULL 
            COMMENT 'content of the business event converted from the message',
    PRIMARY KEY (id)
);

-- 生产者消息重发表
-- 当生产者发送消息失败时, 比如kafka消息中心网络不可达，生产者将消息保存在重发表中
-- 后续进行重发。重发成功后，删除重发表中该消息。重发时通过fail_time保证消息的顺序。
DROP TABLE IF EXISTS sync_producer_retry_msg;
CREATE TABLE sync_producer_retry_msg (
    id varchar(32) NOT NULL,
    kaf_topic varchar(255) NOT NULL,
    kaf_msg_key varchar(255) NOT NULL,
    kaf_msg_value text NOT NULL,
    fail_time datetime(3) NOT NULL,
    PRIMARY KEY (id)
);

