package net.crowdfunding.api.impl.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import net.crowdfunding.api.intf.beans.ProspectManagement;
import net.crowdfunding.api.intf.dto.FindProspectDto;
import net.crowdfunding.api.intf.dto.InformationDto;
import net.crowdfunding.api.intf.dto.PageDto;
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
		dto.setSmallImage("http://app.croowd.co.id/resources/getProspectImage?type=small&id="
				+ dto.getId());
		dto.setShortDescription(prospect.getShortDescription());
		dto.setDescription(prospect.getDescription());
		dto.setLocation(prospect.getLocation());
		dto.setProvince(prospect.getProvince());
		dto.setPrincipal(prospect.getPrincipal());
		dto.setTenor(prospect.getTenor());
		// FIXME: jumlah yang terkumpul masih hardcoded
		dto.setPledged(14000000D);
		Double dPersen = dto.getPledged() * 100 / dto.getPrincipal();
		Integer iPersen = dPersen.intValue();
		dto.setPledgedPersentage(iPersen);
		DateTime nowTime = new DateTime();
		DateTime endTime = new DateTime(prospect.getCampaignEnd());
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
		if (iSessionManager.isValid(dto.getSession())) {
			Session session = iSessionManager.getSession(dto.getSession());
			Prospect prospect = new Prospect();
			if (dto.getId() != 0) {
				prospect.setId(dto.getId());
			}
			Member member = iMember.getMemberByUser(session.getUser().getId());
			prospect.setOwner(member);
			prospect.setTitle(dto.getTitle());
			prospect.setPrincipal(dto.getPrincipal());
			prospect.setTenor(dto.getTenor());
			prospect.setCategory(dto.getCategory());
			prospect.setDescription(dto.getDescription());
			prospect.setShortDescription(dto.getShortDescription());
			prospect.setLocation(dto.getLocation());
			prospect.setCampaignPeriod(dto.getCampaignPeriod());
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
		List<Prospect> prospects = iProspect.listAllVerified();
		int i = 0;
		Iterator<Prospect> iter = prospects.iterator();
		while (i++ < 4 && iter.hasNext()) {
			Prospect prospect = iter.next();
			result.add(createDto(prospect));
		}
		return result;
	}

	@Override
	public List<ProspectDto> listNewcomer() {
		List<ProspectDto> result = new ArrayList<ProspectDto>();
		List<Prospect> prospects = iProspect.listAllVerifiedByDate();
		int i = 0;
		Iterator<Prospect> iter = prospects.iterator();
		while (i++ < 4 && iter.hasNext()) {
			Prospect prospect = iter.next();
			result.add(createDto(prospect));
		}
		return result;
	}

	@Override
	public FindProspectDto listAll() {
		List<ProspectDto> result = new ArrayList<ProspectDto>();
		List<Prospect> prospects = iProspect.listAllVerifiedByDate();
		int i = 0;
		Iterator<Prospect> iter = prospects.iterator();
		while (i++ < 4 && iter.hasNext()) {
			Prospect prospect = iter.next();
			result.add(createDto(prospect));
		}
		FindProspectDto resultStruct = new FindProspectDto();
		resultStruct.setProspects(result);
		List<PageDto> pages = new ArrayList<PageDto>();
		PageDto page = new PageDto();
		pages.add(page);
		resultStruct.setPages(pages);
		return resultStruct;
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

				} else if (user.getType() == 2) {// Admin
					for (Prospect prospect : iProspect.listAll()) {
						result.add(createDto(prospect));
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<ProspectDto> listPublishApproval(String sessionName) {
		List<ProspectDto> result = new ArrayList<ProspectDto>();
		// Pastikan session valid
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				User user = session.getUser();
				if (user.getType() == 2) {// Admin
					for (Prospect prospect : iProspect.listByVerified(0)) {
						result.add(createDto(prospect));
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<ProspectDto> listAllByOwnerStatus(String sessionName, int status) {
		List<ProspectDto> result = new ArrayList<ProspectDto>();
		// Pastikan session valid
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				Member member = iMember.getMemberByUser(session.getUser()
						.getId());
				List<Prospect> prospects = null;
				switch (status) {
				case 0:
					prospects = iProspect.listAllByOwner(member.getId());
					break;
				case 1:
				case 2:
					prospects = iProspect.listAllByOwnerStatus(member.getId(),
							status - 1);
					break;
				}
				if (prospects != null) {
					for (Prospect prospect : prospects) {
						result.add(createDto(prospect));
					}
				}
			}
		}
		return result;
	}

	@Override
	public void approve(String sessionName, Long prospectId) {
		// Pastikan session valid
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				User user = session.getUser();
				if (user.getType() == 2) {// Admin
					Prospect prospect = iProspect.get(prospectId);
					prospect.setVerified(1);
					prospect.setVerifiedDate(new Date());
					prospect.setVerifier(user.getId());
					//
					prospect.setCampaignStart(prospect.getVerifiedDate());
					//
					iProspect.save(prospect);
				}
			}
		}
	}

	@Override
	public List<String> listCategories() {
		List<String> categories = new ArrayList<String>();
		categories.add("Kendaraan");
		categories.add("Rumah baru");
		categories.add("Renovasi");
		categories.add("Biaya sekolah");
		categories.add("Biaya pengobatan");
		categories.add("Bayar kartu kredit");
		return categories;
	}

	@Override
	public List<InformationDto> listCategoriesInfo() {
		List<InformationDto> categories = new ArrayList<InformationDto>();
		categories.add(new InformationDto("Kendaraan"));
		categories.add(new InformationDto("Rumah baru"));
		categories.add(new InformationDto("Renovasi"));
		categories.add(new InformationDto("Biaya sekolah"));
		categories.add(new InformationDto("Biaya pengobatan"));
		categories.add(new InformationDto("Bayar kartu kredit"));
		return categories;
	}

}
