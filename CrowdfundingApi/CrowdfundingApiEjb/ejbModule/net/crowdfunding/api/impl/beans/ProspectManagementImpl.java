package net.crowdfunding.api.impl.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import net.crowdfunding.api.intf.beans.ProspectManagement;
import net.crowdfunding.api.intf.dto.ProspectDto;
import net.crowdfunding.intf.beans.IProspect;
import net.crowdfunding.intf.model.Prospect;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.simbiosis.system.api.bean.ISessionManager;

@Stateless
@Remote(ProspectManagement.class)
public class ProspectManagementImpl implements ProspectManagement {

	@EJB(lookup = "java:global/SystemApi/SystemApiEjb/SessionManager")
	ISessionManager iSessionManager;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/ProspectImpl")
	IProspect iProspect;

	ProspectDto createDto(Prospect prospect) {
		//
		ProspectDto dto = new ProspectDto();
		dto.setId(prospect.getId());
		dto.setTitle(prospect.getTitle());
		dto.setSmallImage("images/projects/small/" + dto.getId() + ".jpg");
		dto.setShortDescription(prospect.getShortDescription());
		dto.setDescription(dto.getShortDescription());
		dto.setLocation(prospect.getLocation());
		dto.setProvince(prospect.getProvince());
		dto.setOwnerName(prospect.getOwner().getName());
		dto.setPrincipal(prospect.getPrincipal());
		dto.setPledged(14000000);
		Double dPersen = dto.getPledged() * 100 / dto.getPrincipal();
		Integer iPersen = dPersen.intValue();
		dto.setPledgedPersentage(iPersen);
		DateTime nowTime = new DateTime();
		DateTime endTime = new DateTime(prospect.getEnd());
		dto.setRemainingDay(Days.daysBetween(nowTime, endTime).getDays());
		return dto;
	}

	@Override
	public ProspectDto get(Long id) {
		return createDto(iProspect.get(id));
	}

	@Override
	public List<ProspectDto> listPopular() {
		List<ProspectDto> result = new ArrayList<ProspectDto>();
		List<Prospect> prospects = iProspect.listAll();
		int i = 0;
		Iterator<Prospect> iter = prospects.iterator();
		while (i++ < 4 && iter.hasNext()) {
			Prospect prospect = iter.next();
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

	@Override
	public List<ProspectDto> listAll(String session) {
		if (iSessionManager.isValid(session)) {
			List<ProspectDto> result = new ArrayList<ProspectDto>();
			for (Prospect prospect : iProspect.listAll()) {
				result.add(createDto(prospect));
			}
			return result;
		}
		return new ArrayList<ProspectDto>();
	}

}
