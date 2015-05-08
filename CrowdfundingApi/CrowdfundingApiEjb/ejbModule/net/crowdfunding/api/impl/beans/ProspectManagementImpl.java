package net.crowdfunding.api.impl.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import net.crowdfunding.api.intf.beans.ProspectManagement;
import net.crowdfunding.api.intf.dto.ProspectDto;
import net.crowdfunding.intf.beans.IMember;
import net.crowdfunding.intf.beans.IProspect;
import net.crowdfunding.intf.model.Member;
import net.crowdfunding.intf.model.Prospect;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.simbiosis.system.api.bean.ISessionManager;
import org.simbiosis.system.model.Session;
import org.simbiosis.system.model.User;

@Stateless
@Remote(ProspectManagement.class)
public class ProspectManagementImpl implements ProspectManagement {

	@EJB(lookup = "java:global/SystemApi/SystemApiEjb/SessionManager")
	ISessionManager iSessionManager;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/MemberImpl")
	IMember iMember;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/ProspectImpl")
	IProspect iProspect;

	ProspectDto createDto(Prospect prospect) {
		//
		ProspectDto dto = new ProspectDto();
		dto.setId(prospect.getId());
		dto.setTitle(prospect.getTitle());
		// FIXME: lokasi gambar masih hardcoded
		dto.setSmallImage("images/projects/small/" + dto.getId() + ".jpg");
		dto.setShortDescription(prospect.getShortDescription());
		dto.setDescription(dto.getShortDescription());
		dto.setLocation(prospect.getLocation());
		dto.setProvince(prospect.getProvince());
		dto.setPrincipal(prospect.getPrincipal());
		dto.setTenor(prospect.getTenor());
		// FIXME: jumlah yang terkumpul masih hardcoded
		dto.setPledged(14000000);
		Double dPersen = dto.getPledged() * 100 / dto.getPrincipal();
		Integer iPersen = dPersen.intValue();
		dto.setPledgedPersentage(iPersen);
		DateTime nowTime = new DateTime();
		DateTime endTime = new DateTime(prospect.getEnd());
		dto.setRemainingDay(Days.daysBetween(nowTime, endTime).getDays());
		Member owner = prospect.getOwner();
		dto.setOwnerId(owner.getId());
		dto.setOwnerName(owner.getName());
		dto.setAddress(owner.getAddress());
		dto.setEmail(owner.getEmail());
		dto.setCity(owner.getCity());
		dto.setZipCode("");
		dto.setPhone(owner.getCellPhone());
		return dto;
	}

	@Override
	public Long save(ProspectDto dto) {
		if (iSessionManager.isValid(dto.getSessionName())) {
			Session session = iSessionManager.getSession(dto.getSessionName());
			Prospect prospect = new Prospect();
			Member member = iMember.getMemberByUser(session.getUser().getId());
			prospect.setOwner(member);
			prospect.setTitle(dto.getTitle());
			prospect.setPrincipal(dto.getPrincipal());
			prospect.setTenor(dto.getTenor());
			prospect.setDescription(dto.getDescription());
			prospect.setLocation(dto.getLocation());
			return iProspect.save(prospect);
		}
		return 0L;
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
	public List<ProspectDto> listAll(String sessionName) {
		List<ProspectDto> result = new ArrayList<ProspectDto>();
		// Pastikan session valid
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				User user = session.getUser();
				if (user.getType() == 3) {// Member

				} else if (user.getType() == 1) {// Admin
					for (Prospect prospect : iProspect.listAll()) {
						result.add(createDto(prospect));
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<ProspectDto> listAllByOwner(String sessionName) {
		List<ProspectDto> result = new ArrayList<ProspectDto>();
		// Pastikan session valid
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				Member member = iMember.getMemberByUser(session.getUser()
						.getId());
				for (Prospect prospect : iProspect.listAllByOwner(member
						.getId())) {
					result.add(createDto(prospect));
				}
				return result;
			}
		}
		return result;
	}

}
