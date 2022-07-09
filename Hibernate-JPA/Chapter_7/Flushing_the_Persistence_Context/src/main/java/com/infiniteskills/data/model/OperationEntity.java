package com.infiniteskills.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "operation")
public class OperationEntity {

    public static final String OP_CODE_DISABLE_UAS_LOGIN = "cmc_op_code_disable_uas_login";
    public static final String OP_CODE_DISABLE_UAS_AUTH = "cmc_op_code_disable_uas_auth";

    public static final String OP_STAT_ONGOING = "cmc_op_stat_ongoing";
    public static final String OP_STAT_CANCEL = "cmc_op_stat_cancel";

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @Column(length = 40)
    private String id;

    @Basic
    @Column(name = "code")
    private String code;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "remark")
    private String remark;
}
