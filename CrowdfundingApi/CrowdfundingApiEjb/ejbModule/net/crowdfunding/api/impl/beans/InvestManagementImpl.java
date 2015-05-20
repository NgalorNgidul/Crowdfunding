package net.crowdfunding.api.impl.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import net.crowdfunding.api.intf.beans.InvestManagement;
import net.crowdfunding.api.intf.dto.InvestDto;
import net.crowdfunding.api.intf.dto.ProspectDto;
import net.crowdfunding.intf.beans.IInvestPlan;
import net.crowdfunding.intf.beans.IMember;
import net.crowdfunding.intf.beans.IProspect;
import net.crowdfunding.intf.model.InvestPlan;
import net.crowdfunding.intf.model.Member;
import net.crowdfunding.intf.model.Prospect;

import org.simbiosis.system.api.bean.ISessionManager;
import org.simbiosis.system.model.Session;
import org.simbiosis.system.model.User;

@Stateless
@Remote(InvestManagement.class)
public class InvestManagementImpl implements InvestManagement {

	@EJB(lookup = "java:global/SystemApi/SystemApiEjb/SessionManager")
	ISessionManager iSessionManager;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/MemberImpl")
	IMember iMember;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/ProspectImpl")
	IProspect iProspect;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/InvestPlanImpl")
	IInvestPlan iInvestPlan;

	@Override
	public Long savePlan(InvestDto invest) {
		if (iSessionManager.isValid(invest.getSession())) {
			Session session = iSessionManager.getSession(invest.getSession());
			if (session != null) {
				InvestPlan ip = null;
				if (invest.getId() == 0) {
					ip = new InvestPlan();
					ip.setDate(new Date());
					ip.setTimestamp(ip.getDate());
					Member member = iMember.getMemberByUser(session.getUser()
							.getId());
					ip.setMember(member);
					Prospect prospect = iProspect.get(invest.getProspectId());
					ip.setProspect(prospect);
				} else {
					ip = iInvestPlan.get(invest.getId());
				}
				ip.setValue(invest.getValue());
				return iInvestPlan.save(ip);
			}
		}
		return 0L;
	}

	@Override
	public void verifyPlan(String sessionName, Long id) {
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				if (session.getUser().getType() == 2) {
					InvestPlan plan = iInvestPlan.get(id);
					//
					plan.setVerified(1);
					plan.setVerifiedDate(new Date());
					plan.setVerifier(session.getUser().getId());
					//
					iInvestPlan.save(plan);
				}
			}
		}
	}

	@Override
	public List<InvestDto> listAllPlanByOwnerStatus(String sessionName,
			int status) {
		List<InvestDto> result = new ArrayList<InvestDto>();
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				Member investor = iMember.getMemberByUser(session.getUser()
						.getId());
				List<InvestPlan> plans = null;
				switch (status) {
				case 0:
					plans = iInvestPlan.listByMember(investor.getId());
					break;
				case 1:
				case 2:
					plans = iInvestPlan.listByMemberStatus(investor.getId(),
							status - 1);
					break;
				}
				if (plans != null) {

					for (InvestPlan plan : plans) {
						InvestDto dto = new InvestDto();
						dto.setId(plan.getId());
						dto.setValue(plan.getValue());
						dto.setMemberId(investor.getId());
						dto.setProspectId(plan.getProspect().getId());
						ProspectDto prospect = dto.getProspect();
						prospect.setId(plan.getProspect().getId());
						prospect.setOwnerName(plan.getProspect().getOwner()
								.getName());
						prospect.setTitle(plan.getProspect().getTitle());
						prospect.setLocation(plan.getProspect().getLocation());
						prospect.setShortDescription(plan.getProspect()
								.getShortDescription());
						prospect.setDescription(plan.getProspect()
								.getDescription());
						prospect.setPrincipal(plan.getProspect().getPrincipal());
						prospect.setTenor(plan.getProspect().getTenor());
						result.add(dto);
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<InvestDto> listAllPlanByStatus(String sessionName, int status) {
		List<InvestDto> result = new ArrayList<InvestDto>();
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				User user = session.getUser();
				if (user.getType() == 2) {
					List<InvestPlan> plans = null;
					switch (status) {
					case 0:
						plans = iInvestPlan.listAll();
						break;
					case 1:
					case 2:
						plans = iInvestPlan.listAllByStatus(status - 1);
						break;
					}
					if (plans != null) {
						for (InvestPlan plan : plans) {
							Member member = plan.getMember();
							InvestDto dto = new InvestDto();
							dto.setId(plan.getId());
							dto.setValue(plan.getValue());
							dto.setMemberId(member.getId());
							dto.setMemberName(member.getName());
							dto.setProspectId(plan.getProspect().getId());
							ProspectDto prospect = dto.getProspect();
							prospect.setId(plan.getProspect().getId());
							prospect.setOwnerName(plan.getProspect().getOwner()
									.getName());
							prospect.setTitle(plan.getProspect().getTitle());
							prospect.setLocation(plan.getProspect()
									.getLocation());
							prospect.setShortDescription(plan.getProspect()
									.getShortDescription());
							prospect.setDescription(plan.getProspect()
									.getDescription());
							prospect.setPrincipal(plan.getProspect()
									.getPrincipal());
							prospect.setTenor(plan.getProspect().getTenor());
							result.add(dto);
						}
					}
				}
			}
		}
		return result;
	}

}
