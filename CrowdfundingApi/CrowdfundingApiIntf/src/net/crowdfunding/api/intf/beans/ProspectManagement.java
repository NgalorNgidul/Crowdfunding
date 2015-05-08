package net.crowdfunding.api.intf.beans;

import java.util.List;

import net.crowdfunding.api.intf.dto.ProspectDto;

public interface ProspectManagement {
	Long save(ProspectDto dto);

	ProspectDto get(Long id);

	List<ProspectDto> listPopular();

	List<ProspectDto> find(String textKey);

	List<ProspectDto> listAll(String session);

	List<ProspectDto> listAllByOwner(String session);
}
