package com.financial.tools.recorderserver.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	private float totalFee;

	@Column(name = "STATUS")
	private int status;

	@JsonProperty("userNameList")
	@Column(name = "USER_NAMES")
	private String userNames;

	@Column(name = "RECORD_DATE")
	private Date recordDate;

	public FinancialRecord() {
	}

	public FinancialRecord(long id, String name, float totalFee, FinancialRecordStatus status, Date recordDate) {
		super();
		this.id = id;
		this.name = name;
		this.totalFee = totalFee;
		this.status = status.getValue();
		this.recordDate = new Date(recordDate.getTime());
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

	public float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(float totalFee) {
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

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = new Date(recordDate.getTime());
	}

}
