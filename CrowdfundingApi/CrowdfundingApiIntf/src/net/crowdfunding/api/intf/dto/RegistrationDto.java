package net.crowdfunding.api.intf.dto;

import java.io.Serializable;
import java.util.Date;

public class RegistrationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855379872733936622L;
	String email;
	Date timestamp;
	String name;
	Integer invest;
	Double principal;
	Integer tenor;
	String key;
	Integer status;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getInvest() {
		return invest;
	}

	public void setInvest(Integer invest) {
		this.invest = invest;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Integer getTenor() {
		return tenor;
	}

	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
