package net.crowdfunding.api.intf.dto;

import java.io.Serializable;
import java.util.List;

public class FindProspectDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6217425561125779663L;
	List<PageDto> pages;
	List<ProspectDto> prospects;

	public List<PageDto> getPages() {
		return pages;
	}

	public void setPages(List<PageDto> pages) {
		this.pages = pages;
	}

	public List<ProspectDto> getProspects() {
		return prospects;
	}

	public void setProspects(List<ProspectDto> prospects) {
		this.prospects = prospects;
	}

}
