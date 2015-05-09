package net.crowdfunding.api.impl.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import net.crowdfunding.api.intf.beans.MemberManagement;
import net.crowdfunding.intf.beans.IMember;
import net.crowdfunding.intf.model.Member;

import org.simbiosis.system.api.bean.ISessionManager;
import org.simbiosis.system.model.Session;

@Stateless
@Remote(MemberManagement.class)
public class MemberManagementImpl implements MemberManagement {

	@EJB(lookup = "java:global/SystemApi/SystemApiEjb/SessionManager")
	ISessionManager iSessionManager;
	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/MemberImpl")
	IMember iMember;

	@Override
	public Member getBySession(String sessionName) {
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				Member member = iMember.getMemberByUser(session.getUser()
						.getId());
				return member;
			}
		}
		return null;
	}

	@Override
	public Member get(Long id) {
		return iMember.get(id);
	}

	@Override
	public List<Member> listAll(String sessionName) {
		List<Member> result = new ArrayList<Member>();
		if (iSessionManager.isValid(sessionName)) {
			Session session = iSessionManager.getSession(sessionName);
			if (session != null) {
				if (session.getUser().getType() == 2) {
					result.addAll(iMember.listAll());
				}
			}
		}
		return result;
	}

}
