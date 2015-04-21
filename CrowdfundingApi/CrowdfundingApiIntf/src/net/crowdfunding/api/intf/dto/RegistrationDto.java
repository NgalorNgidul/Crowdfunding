package net.crowdfunding.api.intf.dto;

import java.io.Serializable;
import java.util.Date;

public class RegistrationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855379872733936622L;
	private Date timestamp;
	private String email;
	private String name;
	private String address;
	private String city;
	private String zipCode;
	private String province;
	private String phone;
	private String password;
	private Integer invest;
	private Double principal;
	private Integer tenor;
	private String key;
	private Integer status;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

}
