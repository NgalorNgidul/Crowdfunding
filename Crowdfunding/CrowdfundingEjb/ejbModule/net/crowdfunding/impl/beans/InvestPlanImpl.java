package net.crowdfunding.impl.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import net.crowdfunding.intf.beans.IInvestPlan;
import net.crowdfunding.intf.model.InvestPlan;

@Stateless
@Remote(IInvestPlan.class)
public class InvestPlanImpl implements IInvestPlan {

	@PersistenceContext(unitName = "CrowdfundingEjb", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	@Override
	public InvestPlan get(long id) {
		return em.find(InvestPlan.class, id);
	}

	@Override
	public long save(InvestPlan data) {
		if (data.getId() == 0) {
			em.persist(data);
		} else {
			em.merge(data);
		}
		return data.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvestPlan> listByMember(long memberId) {
		Query qry = em.createNamedQuery("listInvestPlanByMember");
		qry.setParameter("memberId", memberId);
		List<InvestPlan> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvestPlan> listByMemberStatus(long memberId, int status) {
		Query qry = em.createNamedQuery("listInvestPlanByMemberStatus");
		qry.setParameter("memberId", memberId);
		qry.setParameter("status", status);
		List<InvestPlan> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvestPlan> listAll() {
		Query qry = em.createNamedQuery("listAllInvestPlan");
		List<InvestPlan> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvestPlan> listAllByStatus(int status) {
		Query qry = em.createNamedQuery("listAllInvestPlanByStatus");
		qry.setParameter("status", status);
		List<InvestPlan> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvestPlan> listByProspect(long prospect) {
		Query qry = em.createNamedQuery("listInvestPlanByProspect");
		qry.setParameter("prospectId", prospect);
		List<InvestPlan> result = qry.getResultList();
		return result;
	}

}
