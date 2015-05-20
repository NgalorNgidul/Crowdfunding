package net.crowdfunding.api.intf.beans;

import java.util.List;

import net.crowdfunding.api.intf.dto.InvestDto;

public interface InvestManagement {
	Long savePlan(InvestDto invest);

	void verifyPlan(String sessionName, Long id);

	List<InvestDto> listAllPlanByOwnerStatus(String sessionName, int status);

	List<InvestDto> listAllPlanByStatus(String sessionName, int status);

}
