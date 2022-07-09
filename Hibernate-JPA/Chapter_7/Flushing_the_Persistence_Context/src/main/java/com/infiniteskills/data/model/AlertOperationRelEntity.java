package com.infiniteskills.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "alert_operation_rel")
public class AlertOperationRelEntity {
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Basic
    @Column(name = "alert_id")
    private String alertId;

    @Basic
    @Column(name = "operation_id")
    private String operationId;

    @Basic
    @Column(name = "operation_code")
    private String operationCode;

    @Basic
    @Column(name = "operation_stat")
    private String operationStat;
}
