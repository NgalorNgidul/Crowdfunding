package net.crowdfunding.impl.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import net.crowdfunding.intf.beans.IProspect;
import net.crowdfunding.intf.model.Prospect;

@Stateless
@Remote(IProspect.class)
public class ProspectImpl implements IProspect {

	@PersistenceContext(unitName = "CrowdfundingEjb", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	@Override
	public long save(Prospect prospect) {
		if (prospect.getId() == 0) {
			em.persist(prospect);
		} else {
			em.merge(prospect);
		}
		return prospect.getId();
	}

	@Override
	public Prospect get(long id) {
		Prospect result = em.find(Prospect.class, id);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prospect> listAll() {
		Query qry = em.createNamedQuery("listAll");
		List<Prospect> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prospect> find(String textKey) {
		String text = "%" + textKey.toLowerCase() + "%";
		Query qry = em.createNamedQuery("findByText");
		qry.setParameter("text", text);
		List<Prospect> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prospect> listAllByOwner(long ownerId) {
		Query qry = em.createNamedQuery("listAllByOwner");
		qry.setParameter("ownerId", ownerId);
		List<Prospect> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prospect> listAllByOwnerStatus(long ownerId, int status) {
		Query qry = em.createNamedQuery("listAllByOwnerStatus");
		qry.setParameter("ownerId", ownerId);
		qry.setParameter("status", status);
		List<Prospect> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prospect> listByVerified(int status) {
		Query qry = em.createNamedQuery("listByVerified");
		qry.setParameter("verified", status);
		List<Prospect> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prospect> listAllVerified() {
		Query qry = em.createNamedQuery("listAllVerified");
		List<Prospect> result = qry.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prospect> listAllVerifiedByDate() {
		Query qry = em.createNamedQuery("listAllVerifiedByDate");
		List<Prospect> result = qry.getResultList();
		return result;
	}

}
