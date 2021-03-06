package net.crowdfunding.api.impl.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import net.crowdfunding.intf.beans.IInvestPlan;
import net.crowdfunding.intf.beans.IMember;
import net.crowdfunding.intf.beans.IProspect;
import net.crowdfunding.intf.model.InvestPlan;
import net.crowdfunding.intf.model.Member;
import net.crowdfunding.intf.model.Prospect;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.simbiosis.system.api.bean.ISessionManager;
import org.simbiosis.system.model.Session;
import org.simbiosis.system.model.User;
import org.simbiosis.systemui.api.bean.IConfigManager;
import org.simbiosis.systemui.api.dto.UiConfigDto;

@Stateless
@Remote(ProspectManagement.class)
public class ProspectManagementImpl implements ProspectManagement {

	@EJB(lookup = "java:global/SystemApi/SystemApiEjb/SessionManager")
	ISessionManager iSessionManager;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/MemberImpl")
	IMember iMember;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/ProspectImpl")
	IProspect iProspect;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/InvestPlanImpl")
	IInvestPlan iInvestPlan;
	@EJB(lookup = "java:global/SystemUiApi/SystemUiApiEjb/ConfigManager")
	IConfigManager configManager;

	ProspectDto createDto(Prospect prospect) {
		UiConfigDto config = configManager.getConfig();
		//
		ProspectDto dto = new ProspectDto();
		dto.setId(prospect.getId());
		dto.setTitle(prospect.getTitle());
		dto.setSmallImage("http://" + config.getSimbiosisApi()
				+ "/resources/getProspectImage?type=small&id=" + dto.getId());
		dto.setShortDescription(prospect.getShortDescription());
		dto.setDescription(prospect.getDescription());
		dto.setCategory(prospect.getCategory());
		dto.setLocation(prospect.getLocation());
		dto.setProvince(prospect.getProvince());
		dto.setPrincipal(prospect.getPrincipal());
		dto.setTenor(prospect.getTenor());
		dto.setCampaignPeriod(prospect.getCampaignPeriod());
		// Cari invest plan
		List<InvestPlan> invests = iInvestPlan.listByProspect(prospect.getId());
		Double dPledged = 0D;
		int pledgedCount = 0;
		for (InvestPlan invest : invests) {
			if (invest.getVerified() == 1) {
				dPledged += invest.getValue();
				pledgedCount++;
			}
		}
		dto.setPledged(dPledged);
		dto.setPledgedCount(pledgedCount);
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
		dto.setZipCode(owner.getZipCode());
		dto.setProvince(owner.getProvince());
		dto.setPhone(owner.getCellPhone());
		if (prospect.getVerified() == 1) {
			dto.setStatus(1);
		} else {
			dto.setStatus(0);
		}
		return dto;
	}

	@Override
	public Long save(ProspectDto dto) {
		if (iSessionManager.isValid(dto.getSession())) {
			Session session = iSessionManager.getSession(dto.getSession());
			Prospect prospect = null;
			if (dto.getId() == 0) {
				prospect = new Prospect();
				prospect.setId(dto.getId());
				Member member = iMember.getMemberByUser(session.getUser()
						.getId());
				prospect.setOwner(member);
			} else {
				prospect = iProspect.get(dto.getId());
			}
			prospect.setTitle(dto.getTitle());
			prospect.setPrincipal(dto.getPrincipal());
			prospect.setTenor(dto.getTenor());
			prospect.setCategory(dto.getCategory());
			prospect.setShortDescription(dto.getShortDescription());
			prospect.setDescription(dto.getDescription());
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
	public FindProspectDto listAll(long startId) {
		// Ambil data
		List<Prospect> prospects = iProspect.listAllVerifiedByDate();
		Collections.sort(prospects, new Comparator<Prospect>() {

			@Override
			public int compare(Prospect o1, Prospect o2) {
				Long l1 = o1.getId();
				Long l2 = o2.getId();
				return l1.compareTo(l2);
			}
		});
		// Tampilkan
		int maxPerSite = 6;
		List<ProspectDto> prospectList = new ArrayList<ProspectDto>();
		List<PageDto> pages = new ArrayList<PageDto>();
		int i = 0, index = 0;
		for (Prospect prospect : prospects) {
			if (index < maxPerSite && prospect.getId() >= startId) {
				prospectList.add(createDto(prospect));
				//
				index++;
			}
			//
			if (i % maxPerSite == 0) {
				PageDto page = new PageDto();
				page.setPage((i / maxPerSite) + 1);
				page.setStart(i > 0 ? prospect.getId() : 0);
				pages.add(page);
			}
			//
			i++;
		}
		FindProspectDto result = new FindProspectDto();
		result.setProspects(prospectList);
		result.setPages(pages);
		return result;
	}

	@Override
	public FindProspectDto listAllByCat(String category, long startId) {
		// Ambil data
		List<Prospect> prospects = iProspect.listAllVerifiedByCat(category);
		Collections.sort(prospects, new Comparator<Prospect>() {

			@Override
			public int compare(Prospect o1, Prospect o2) {
				Long l1 = o1.getId();
				Long l2 = o2.getId();
				return l1.compareTo(l2);
			}
		});
		// Tampilkan
		int maxPerSite = 6;
		List<ProspectDto> prospectList = new ArrayList<ProspectDto>();
		List<PageDto> pages = new ArrayList<PageDto>();
		int i = 0, index = 0;
		for (Prospect prospect : prospects) {
			if (index < maxPerSite && prospect.getId() >= startId) {
				prospectList.add(createDto(prospect));
				//
				index++;
			}
			//
			if (i % maxPerSite == 0) {
				PageDto page = new PageDto();
				page.setPage((i / maxPerSite) + 1);
				page.setStart(i > 0 ? prospect.getId() : 0);
				pages.add(page);
			}
			//
			i++;
		}
		FindProspectDto result = new FindProspectDto();
		result.setProspects(prospectList);
		result.setPages(pages);
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
					DateTime dt = new DateTime(prospect.getCampaignStart());
					prospect.setCampaignEnd(dt.plusDays(
							prospect.getCampaignPeriod()).toDate());
					//
					iProspect.save(prospect);
				}
			}
		}
	}

	@Override
	public List<String> listCategories() {
		List<String> categories = new ArrayList<String>();
		categories.add("Software");
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
		List<String> strCategories = listCategories();
		List<InformationDto> categories = new ArrayList<InformationDto>();
		for (String category : strCategories) {
			int number = iProspect.countByCategory(category);
			categories.add(new InformationDto(category, number));
		}
		return categories;
	}

}
