package net.crowdfunding.impl.beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

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

}
