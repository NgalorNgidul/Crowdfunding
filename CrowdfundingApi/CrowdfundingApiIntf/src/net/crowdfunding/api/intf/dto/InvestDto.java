package net.crowdfunding.api.intf.dto;

import java.io.Serializable;

public class InvestDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -452847183254859850L;
	String session;
	Long id;
	Long memberId;
	String memberName;
	Double value;

	Long prospectId;
	ProspectDto prospect = new ProspectDto();

	public InvestDto() {
		id = 0L;
		memberId = 0L;
		prospectId = 0L;
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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getProspectId() {
		return prospectId;
	}

	public void setProspectId(Long prospectId) {
		this.prospectId = prospectId;
	}

	public ProspectDto getProspect() {
		return prospect;
	}

	public void setProspect(ProspectDto prospect) {
		this.prospect = prospect;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
