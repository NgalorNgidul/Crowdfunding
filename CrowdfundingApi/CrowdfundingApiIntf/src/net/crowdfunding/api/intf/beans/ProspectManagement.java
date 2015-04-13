package net.crowdfunding.api.intf.beans;

import java.util.List;

import net.crowdfunding.api.intf.dto.ProspectDto;

public interface ProspectManagement {
	ProspectDto get(Long id);

	List<ProspectDto> listPopular();

	List<ProspectDto> find(String textKey);
}
