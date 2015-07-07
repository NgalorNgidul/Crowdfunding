package net.crowdfunding.api.intf.beans;

import java.util.List;

import net.crowdfunding.api.intf.dto.FindProspectDto;
import net.crowdfunding.api.intf.dto.InformationDto;
import net.crowdfunding.api.intf.dto.ProspectDto;

public interface ProspectManagement {
	Long save(ProspectDto dto);

	ProspectDto get(Long id);

	List<ProspectDto> listPopular();

	List<ProspectDto> listNewcomer();

	FindProspectDto listAll(long idMin);

	List<ProspectDto> find(String textKey);

	List<ProspectDto> listAll(String session);

	List<ProspectDto> listPublishApproval(String session);

	List<ProspectDto> listAllByOwnerStatus(String session, int status);

	void approve(String session, Long prospectId);

	List<String> listCategories();

	List<InformationDto> listCategoriesInfo();
}
