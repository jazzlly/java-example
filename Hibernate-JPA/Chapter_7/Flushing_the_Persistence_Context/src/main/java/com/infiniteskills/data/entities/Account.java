package com.infiniteskills.data.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACCOUNT_ID")
	private Long accountId;

	@Enumerated(EnumType.STRING)
	@Column(name="ACCOUNT_TYPE")
	private AccountTypeEnum accountType;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ACCOUNT", joinColumns=@JoinColumn(name="ACCOUNT_ID"), 
		inverseJoinColumns=@JoinColumn(name="USER_ID"))
	private Set<User> users = new HashSet<User>();

	@ManyToOne
	@JoinColumn(name="BANK_ID")
	private Bank bank;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	List<Transaction> transactions = new ArrayList<Transaction>();

	@Column(name = "NAME")
	private String name;

	@Column(name = "INITIAL_BALANCE")
	private BigDecimal initialBalance;

	@Column(name = "CURRENT_BALANCE")
	private BigDecimal currentBalance;

	@Column(name = "OPEN_DATE")
	private Date openDate;

	@Column(name = "CLOSE_DATE")
	private Date closeDate;

	@Column(name = "LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;
}
