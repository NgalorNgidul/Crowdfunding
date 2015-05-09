package net.crowdfunding.impl.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import net.crowdfunding.intf.beans.IMember;
import net.crowdfunding.intf.model.Member;

@Stateless
@Remote(IMember.class)
public class MemberImpl implements IMember {

	@PersistenceContext(unitName = "CrowdfundingEjb", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	@Override
	public void save(Member member) {
		if (member.getId() == 0) {
			em.persist(member);
		} else {
			em.merge(member);
		}
	}

	@Override
	public Member get(long id) {
		return em.find(Member.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Member getMemberByUser(long userId) {
		Query qry = em.createNamedQuery("getMemberByUser");
		qry.setParameter("userId", userId);
		List<Member> result = qry.getResultList();
		if (result.size() == 1) {
			return result.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> listAll() {
		Query qry = em.createNamedQuery("listAllMember");
		List<Member> result = qry.getResultList();
		return result;
	}

}
