package net.crowdfunding.intf.beans;

import java.util.List;

import net.crowdfunding.intf.model.Member;

public interface IMember {
	void save(Member member);

	Member get(long id);

	Member getMemberByUser(long userId);

	List<Member> listAll();
}
