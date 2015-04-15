package net.crowdfunding.intf.model;

import java.io.Serializable;
import java.util.Date;

public class Prospect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5912790465950400324L;
	long id;
	String title;
	String shortDescription;
	String description;
	String smallImage;
	String bigImage1;
	String bigImage2;
	Member owner;
	String location;
	String province;
	double principal;
	double rate;
	int tenor;
	//
	int verified;
	Date verifiedDate;
	long verifier;
	Date begin;
	Date end;
	//
	long userCreate;
	Date tsCreate;
	long userEdit;
	Date tsEdit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getBigImage1() {
		return bigImage1;
	}

	public void setBigImage1(String bigImage1) {
		this.bigImage1 = bigImage1;
	}

	public String getBigImage2() {
		return bigImage2;
	}

	public void setBigImage2(String bigImage2) {
		this.bigImage2 = bigImage2;
	}

	public Member getOwner() {
		return owner;
	}

	public void setOwner(Member owner) {
		this.owner = owner;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getTenor() {
		return tenor;
	}

	public void setTenor(int tenor) {
		this.tenor = tenor;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
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

	public long getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(long userCreate) {
		this.userCreate = userCreate;
	}

	public Date getTsCreate() {
		return tsCreate;
	}

	public void setTsCreate(Date tsCreate) {
		this.tsCreate = tsCreate;
	}

	public long getUserEdit() {
		return userEdit;
	}

	public void setUserEdit(long userEdit) {
		this.userEdit = userEdit;
	}

	public Date getTsEdit() {
		return tsEdit;
	}

	public void setTsEdit(Date tsEdit) {
		this.tsEdit = tsEdit;
	}

}
