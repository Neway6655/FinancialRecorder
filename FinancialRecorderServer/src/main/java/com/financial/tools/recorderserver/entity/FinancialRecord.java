package com.financial.tools.recorderserver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FR_FINANCIAL_RECORD")
public class FinancialRecord implements Serializable {

	private static final long serialVersionUID = 714949257350137208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TOTAL_FEE")
	private long totalFee;

	@Column(name = "STATUS")
	private int status;

	@Column(name = "USER_NAMES")
	private String userNames;

	public FinancialRecord() {
	}

	public FinancialRecord(long id, String name, long totalFee, FinancialRecordStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.totalFee = totalFee;
		this.status = status.getValue();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

}
