package com.infiniteskills.data.entities;

import lombok.Data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@Entity
@Table(name = "TIME_TEST")
public class TimeTest {
    @Id
    @GeneratedValue
    @Column(name = "TIME_TEST_ID")
    private Long timeTestId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATETIME_COLUMN")
    private Date datetimeColumn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP_COLUMN")
    private Date timestampColumn;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_COLUMN")
    private Date dateColumn;

    @Temporal(TemporalType.TIME)
    @Column(name = "TIME_COLUMN")
    private Date timeColumn;

    @Column(name = "SQL_DATETIME_COLUMN")
    private java.sql.Timestamp sqlDatetimeColumn;

    @Column(name = "SQL_TIMESTAMP_COLUMN")
    private java.sql.Timestamp sqlTimestampColumn;

    @Column(name = "SQL_DATE_COLUMN")
    private java.sql.Date sqlDateColumn;

    @Column(name = "SQL_TIME_COLUMN")
    private java.sql.Time sqlTimeColumn;

    public TimeTest(Date date){
        this.datetimeColumn = date;
        this.timestampColumn = date;
        this.timeColumn = date;
        this.dateColumn = date;

        this.sqlDatetimeColumn = new java.sql.Timestamp(date.getTime());
        this.sqlTimestampColumn = new java.sql.Timestamp(date.getTime());
        this.sqlDateColumn = new java.sql.Date(date.getTime());
        this.sqlTimeColumn = new java.sql.Time(date.getTime());
    }
}

