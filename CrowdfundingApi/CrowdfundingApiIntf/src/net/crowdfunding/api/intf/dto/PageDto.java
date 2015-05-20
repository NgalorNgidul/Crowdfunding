package net.crowdfunding.api.intf.dto;

import java.io.Serializable;

public class PageDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5236225059513743402L;
	Integer page;
	Long start;

	public PageDto() {
		page = 1;
		start = 0L;
	}

	public PageDto(Integer page, Long start) {
		this.page = page;
		this.start = start;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

}
