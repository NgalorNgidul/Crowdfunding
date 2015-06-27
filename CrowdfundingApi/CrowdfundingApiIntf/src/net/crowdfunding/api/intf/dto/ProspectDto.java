package net.crowdfunding.api.intf.dto;

import java.io.Serializable;
import java.util.Date;

public class ProspectDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5850733432189684484L;
	Long id;
	String session;
	String smallImage;
	String bigImage;
	String title;
	String shortDescription;
	String description;
	String category;
	String ownerName;
	Long ownerId;
	String email;
	String address;
	String city;
	String zipCode;
	String fixPhone;
	String cellPhone;
	String phone;
	String location;
	String province;
	Double principal;
	Double rate;
	Integer tenor;
	Integer campaignPeriod;
	Integer pledgedCount;
	Double pledged;
	Integer pledgedPersentage;
	Date start;
	Date end;
	Integer remainingDay;
	int status;

	public ProspectDto() {
		id = 0L;
		ownerId = 0L;
		principal = 0D;
		rate = 0D;
		tenor = 0;
		campaignPeriod = 0;
		pledgedCount = 0;
		pledged = 0D;
		pledgedPersentage = 0;
		remainingDay = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getBigImage() {
		return bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getTenor() {
		return tenor;
	}

	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}

	public Integer getCampaignPeriod() {
		return campaignPeriod;
	}

	public void setCampaignPeriod(Integer campaignPeriod) {
		this.campaignPeriod = campaignPeriod;
	}

	public Integer getPledgedCount() {
		return pledgedCount;
	}

	public void setPledgedCount(Integer pledgedCount) {
		this.pledgedCount = pledgedCount;
	}

	public Double getPledged() {
		return pledged;
	}

	public void setPledged(Double pledged) {
		this.pledged = pledged;
	}

	public Integer getPledgedPersentage() {
		return pledgedPersentage;
	}

	public void setPledgedPersentage(Integer pledgedPersentage) {
		this.pledgedPersentage = pledgedPersentage;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Integer getRemainingDay() {
		return remainingDay;
	}

	public void setRemainingDay(Integer remainingDay) {
		this.remainingDay = remainingDay;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFixPhone() {
		return fixPhone;
	}

	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

}
