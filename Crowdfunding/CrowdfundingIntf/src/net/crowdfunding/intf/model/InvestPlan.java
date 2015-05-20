package net.crowdfunding.intf.model;

import java.io.Serializable;
import java.util.Date;

public class InvestPlan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8378988283347942623L;
	long id;
	Date date;
	double value;
	Member member;
	Prospect prospect;
	//
	int verified;
	Date verifiedDate;
	long verifier;
	//
	Date timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Prospect getProspect() {
		return prospect;
	}

	public void setProspect(Prospect prospect) {
		this.prospect = prospect;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	public Date getVerifiedDate() {
		return verifiedDate;
	}

	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}

	public long getVerifier() {
		return verifier;
	}

	public void setVerifier(long verifier) {
		this.verifier = verifier;
	}

}
