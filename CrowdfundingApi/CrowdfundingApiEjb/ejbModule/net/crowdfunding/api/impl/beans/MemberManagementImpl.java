package net.crowdfunding.api.impl.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import net.crowdfunding.api.intf.beans.MemberManagement;
import net.crowdfunding.api.intf.dto.MemberDto;
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

	private void copyFromDto(MemberDto dto,Member member){
		member.setName(dto.getName());
		member.setPob(dto.getPob());
		member.setDob(dto.getDob());
		member.setSex(dto.getSex());
		member.setMotherName(dto.getMotherName());
		member.setIdType(dto.getIdType());
		member.setIdCode(dto.getIdCode());
		member.setAddress(dto.getAddress());
		member.setCity(dto.getCity());
		member.setZipCode(dto.getZipCode());
		member.setEmail(dto.getEmail());
		member.setFixPhone(dto.getFixPhone());
		member.setCellPhone(dto.getCellPhone());
	}
	@Override
	public Long save(MemberDto dto) {
		if (iSessionManager.isValid(dto.getSession())) {
			Session session = iSessionManager.getSession(dto.getSession());
			if (session != null) {
				if (dto.getId()==0){
					Member member = new Member();
					copyFromDto(dto, member);
					member.setRegistration(new Date());
					member.setUserCreate(session.getUser().getId());
					member.setTsCreate(member.getRegistration());
					member.setUserEdit(member.getUserCreate());
					member.setTsEdit(member.getTsCreate());
					iMember.save(member);
				}else {
					Member member = iMember.get(dto.getId());
					if (session.getUser().getId()==member.getUserCreate()){
						copyFromDto(dto, member);
						member.setUserEdit(session.getUser().getId());
						member.setTsEdit(new Date());
						iMember.save(member);
					}
				}
			}
		}
		return 0L;
	}
	
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
