package net.crowdfunding.api.intf.beans;

import java.util.List;

import net.crowdfunding.intf.model.Member;

public interface MemberManagement {
	Member get(Long id);

	Member getBySession(String sessionName);

	List<Member> listAll(String sessionName);

}
