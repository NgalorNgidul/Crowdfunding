package net.crowdfunding.intf.model;

import java.io.Serializable;
import java.util.Date;

public class ProjectSchedule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2059081366627054905L;
	private long id;
	private Date date;
	private double principal;
	private double margin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
