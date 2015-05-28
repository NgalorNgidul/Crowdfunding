package net.crowdfunding.intf.model;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4805770951949198325L;
	long id;
	String name;
	int sex;
	String pob;
	Date dob;
	int idType;
	String idCode;
	String taxNr;
	String address;
	String city;
	String zipCode;
	String province;
	String email;
	String fixPhone;
	String cellPhone;
	Date registration;
	String motherName;

	double mainIncome;
	double sideIncome;
	int mainIncomeType;
	int sideIncomeType;
	int sideIncomeLic;
	int sideIncomeDur;

	double expense;
	double otherExpense;

	int home;
	int homeStayDur;

	int vehicle;
	int vehicleProduction;

	Date tsCreate;
	long userCreate;
	Date tsEdit;
	long userEdit;

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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPob() {
		return pob;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public Date getTsCreate() {
		return tsCreate;
	}

	public void setTsCreate(Date tsCreate) {
		this.tsCreate = tsCreate;
	}

	public long getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(long userCreate) {
		this.userCreate = userCreate;
	}

	public Date getTsEdit() {
		return tsEdit;
	}

	public void setTsEdit(Date tsEdit) {
		this.tsEdit = tsEdit;
	}

	public long getUserEdit() {
		return userEdit;
	}

	public void setUserEdit(long userEdit) {
		this.userEdit = userEdit;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTaxNr() {
		return taxNr;
	}

	public void setTaxNr(String taxNr) {
		this.taxNr = taxNr;
	}

	public double getMainIncome() {
		return mainIncome;
	}

	public void setMainIncome(double mainIncome) {
		this.mainIncome = mainIncome;
	}

	public double getSideIncome() {
		return sideIncome;
	}

	public void setSideIncome(double sideIncome) {
		this.sideIncome = sideIncome;
	}

	public int getSideIncomeType() {
		return sideIncomeType;
	}

	public void setSideIncomeType(int sideIncomeType) {
		this.sideIncomeType = sideIncomeType;
	}

	public int getSideIncomeLic() {
		return sideIncomeLic;
	}

	public void setSideIncomeLic(int sideIncomeLic) {
		this.sideIncomeLic = sideIncomeLic;
	}

	public int getSideIncomeDur() {
		return sideIncomeDur;
	}

	public void setSideIncomeDur(int sideIncomeDur) {
		this.sideIncomeDur = sideIncomeDur;
	}

	public int getHome() {
		return home;
	}

	public void setHome(int home) {
		this.home = home;
	}

	public int getHomeStayDur() {
		return homeStayDur;
	}

	public void setHomeStayDur(int homeStayDur) {
		this.homeStayDur = homeStayDur;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public double getOtherExpense() {
		return otherExpense;
	}

	public void setOtherExpense(double otherExpense) {
		this.otherExpense = otherExpense;
	}

	public int getVehicle() {
		return vehicle;
	}

	public void setVehicle(int vehicle) {
		this.vehicle = vehicle;
	}

	public int getVehicleProduction() {
		return vehicleProduction;
	}

	public void setVehicleProduction(int vehicleProduction) {
		this.vehicleProduction = vehicleProduction;
	}

	public int getMainIncomeType() {
		return mainIncomeType;
	}

	public void setMainIncomeType(int mainIncomeType) {
		this.mainIncomeType = mainIncomeType;
	}

}
