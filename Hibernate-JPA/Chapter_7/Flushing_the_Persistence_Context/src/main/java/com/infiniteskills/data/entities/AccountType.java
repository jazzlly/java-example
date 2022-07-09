package com.infiniteskills.data.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ACCOUNT_TYPE")
public class AccountType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_TYPE_ID")
	private Long accountTypeId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LAST_UPDATED_DATE")
	private Date LastUpdatedDate;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Transient
	private String temp;
}
