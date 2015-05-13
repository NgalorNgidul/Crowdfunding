package net.crowdfunding.api.intf.beans;

import java.util.List;

import net.crowdfunding.api.intf.dto.MemberDto;
import net.crowdfunding.intf.model.Member;

public interface MemberManagement {
	Long save(MemberDto member);

	Member get(Long id);

	Member getBySession(String sessionName);

	List<Member> listAll(String sessionName);

}
