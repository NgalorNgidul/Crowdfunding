package net.crowdfunding.intf.model;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5667478295708500285L;
	private long id;
	private String code;
	private Date registration;
	private double principal;
	private double margin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getMargin() {
		return margin;
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}

}
