package net.crowdfunding.intf.beans;

import java.util.List;

import net.crowdfunding.intf.model.InvestPlan;

public interface IInvestPlan {
	InvestPlan get(long id);

	long save(InvestPlan data);

	List<InvestPlan> listByMember(long memberId);

	List<InvestPlan> listByMemberStatus(long memberId, int status);

	List<InvestPlan> listAll();

	List<InvestPlan> listAllByStatus(int status);

	List<InvestPlan> listByProspect(long prospect);

}
