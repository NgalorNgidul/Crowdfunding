package net.crowdfunding.api.intf.beans;

import java.util.List;

import net.crowdfunding.api.intf.dto.InvestDto;

public interface InvestManagement {
	Long savePlan(InvestDto invest);

	List<InvestDto> listAllPlanByOwner(String sessionName);

	List<InvestDto> listAllPlan(String sessionName);
}
