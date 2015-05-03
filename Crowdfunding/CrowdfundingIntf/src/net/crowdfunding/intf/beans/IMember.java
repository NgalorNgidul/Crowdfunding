package net.crowdfunding.intf.beans;

import net.crowdfunding.intf.model.Member;

public interface IMember {
	void save(Member member);

	Member getMemberByUser(long userId);
}
