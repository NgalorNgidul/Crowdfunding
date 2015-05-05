package net.crowdfunding.api.intf.beans;

import net.crowdfunding.intf.model.Member;

public interface MemberManagement {
	Member getBySession(String sessionName);
}
