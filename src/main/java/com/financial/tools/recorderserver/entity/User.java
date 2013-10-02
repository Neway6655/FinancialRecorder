package com.financial.tools.recorderserver.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FR_USER")
public class User implements Serializable {

	private static final long serialVersionUID = -3791821536205637003L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PASSWORD")
	private byte[] password;

	@Column(name = "BALANCE")
	private float balance;

	@Column(name = "TYPE")
	private int type;

	public User() {
	}

	public User(String name, byte[] password, long balance, UserType type) {
		super();
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.type = type.getValue();
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

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
