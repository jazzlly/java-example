package com.infiniteskills.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "sec_event_alert")
public class SecEventAlertEntity {

    public static final String TARGET_TYPE_USER = "57201";
    public static final String TARGET_TYPE_DEVICE = "57203";

    public static final String ALERT_REASON_ROOT_VIOLATION = "cmc_alert_reason_root_violation";
    public static final String ALERT_REASON_TF_VIOLATION = "cmc_alert_reason_tf_violation";
    public static final String ALERT_REASON_SIM_VIOLATION = "cmc_alert_reason_sim_violation";
    public static final String ALERT_REASON_LOST_VIOLATION = "cmc_alert_reason_lost_violation";

    public static final String ALERT_STAT_ONGOING = "cmc_alert_stat_ongoing";
    public static final String ALERT_STAT_DISMISSED = "cmc_alert_stat_dismissed";
    public static final String ALERT_STAT_CONFIRMED = "cmc_alert_stat_confirmed";

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(length = 40)
    private String id;

    @Basic
    @Column(name = "alert_level")
    private String alertLevel = "";

    @Basic
    @Column(name = "start_time")
    private Timestamp startTime = new Timestamp(new Date().getTime());

    @Basic
    @Column(name = "end_time")
    private Timestamp endTime;

    @Basic
    @Column(name = "target_id")
    private String targetId = "";

    @Basic
    @Column(name = "target_type")
    private String targetType = TARGET_TYPE_USER;

    @Basic
    @Column(name = "target_name")
    private String targetName = "";

    @Basic
    @Column(name = "alert_reason")
    private String alertReason = ALERT_REASON_ROOT_VIOLATION;

    @Basic
    @Column(name = "alert_stat")
    private String alertStat = ALERT_STAT_ONGOING;
}
