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
		Query qry = em.createNamedQuery("listAllByOwnerId");
		qry.setParameter("ownerId", ownerId);
		List<Prospect> result = qry.getResultList();
		return result;
	}

}
