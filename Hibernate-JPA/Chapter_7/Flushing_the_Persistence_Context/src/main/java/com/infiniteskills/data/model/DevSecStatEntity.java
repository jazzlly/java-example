package com.infiniteskills.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "dev_sec_stat")
public class DevSecStatEntity {
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(length = 40)
    private String id;

    @Basic
    @Column(name = "dev_uuid")
    private String devUuid = "";

    @Basic
    @Column(name = "account")
    private String account = "";

    @Basic
    @Column(name = "root_violation_stat")
    private Integer rootStat = 0;

    @Basic
    @Column(name = "tf_violation_stat")
    private Integer tfStat = 0;

    @Basic
    @Column(name = "sim_violation_stat")
    private Integer simStat = 0;

    @Basic
    @Column(name = "lost_violation_stat")
    private Integer lostStat = 0;
}
