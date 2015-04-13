package net.crowdfunding.api.impl.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import net.crowdfunding.api.intf.beans.ProspectManagement;
import net.crowdfunding.api.intf.dto.ProspectDto;
import net.crowdfunding.intf.beans.IProspect;
import net.crowdfunding.intf.model.Prospect;

@Stateless
@Remote(ProspectManagement.class)
public class ProspectManagementImpl implements ProspectManagement {

	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/ProspectImpl")
	IProspect iProspect;

	ProspectDto createDto(Prospect prospect) {
		int days[] = { 25, 18, 2, 44, 34, 10 };
		int i = 0;
		//
		ProspectDto dto = new ProspectDto();
		dto.setId(prospect.getId());
		dto.setTitle(prospect.getTitle());
		dto.setSmallImage("images/projects/small/" + dto.getId() + ".jpg");
		dto.setShortDescription(prospect.getShortDescription());
		dto.setLocation(prospect.getLocation());
		dto.setProvince(prospect.getProvince());
		dto.setOwnerName(prospect.getOwner().getName());
		dto.setPrincipal(prospect.getPrincipal());
		dto.setPledged(14000000);
		Double dPersen = dto.getPledged() * 100 / dto.getPrincipal();
		Integer iPersen = dPersen.intValue();
		dto.setPledgedPersentage(iPersen);
		dto.setRemainingDay(days[i++]);
		return dto;
	}

	@Override
	public ProspectDto get(Long id) {
		return createDto(iProspect.get(id));
	}

	@Override
	public List<ProspectDto> listPopular() {
		List<ProspectDto> result = new ArrayList<ProspectDto>();
		List<Prospect> prospects = iProspect.listPopular();
		for (Prospect prospect : prospects) {
			result.add(createDto(prospect));
		}
		return result;
	}

	@Override
	public List<ProspectDto> find(String textKey) {
		List<ProspectDto> result = new ArrayList<ProspectDto>();
		List<Prospect> prospects = iProspect.find(textKey);
		for (Prospect prospect : prospects) {
			result.add(createDto(prospect));
		}
		return result;
	}

}
