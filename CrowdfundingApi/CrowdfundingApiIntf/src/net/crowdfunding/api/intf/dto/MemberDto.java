package net.crowdfunding.api.intf.dto;

import net.crowdfunding.intf.model.Member;

public class MemberDto extends Member {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6181678860531598078L;
	String session;

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

}
