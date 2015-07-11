package net.crowdfunding.api.intf.dto;

import java.io.Serializable;

public class InformationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6364755420675264404L;

	private String name;
	private Integer iInfo;

	public InformationDto() {
		iInfo = 0;
	}

	public InformationDto(String name) {
		this.name = name;
		iInfo = 0;
	}

	public InformationDto(String name, int number) {
		this.name = name;
		iInfo = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getiInfo() {
		return iInfo;
	}

	public void setiInfo(Integer iInfo) {
		this.iInfo = iInfo;
	}

}
