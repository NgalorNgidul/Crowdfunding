package net.crowdfunding.impl.beans;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import net.crowdfunding.intf.beans.IRegistration;
import net.crowdfunding.intf.model.Registration;

@Stateless
@Remote(IRegistration.class)
public class RegistrationImpl implements IRegistration {

	@PersistenceContext(unitName = "CrowdfundingEjb", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Registration get(String email, Date timestamp) {
		Query qry = em.createNamedQuery("getRegistrationById");
		qry.setParameter("email", email);
		qry.setParameter("timestamp", timestamp);
		List<Registration> resultList = qry.getResultList();
		return resultList.size() == 1 ? resultList.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Registration get(String key) {
		Query qry = em.createNamedQuery("getRegistrationByKey");
		qry.setParameter("key", key);
		List<Registration> resultList = qry.getResultList();
		return resultList.size() == 1 ? resultList.get(0) : null;
	}

	@Override
	public void save(Registration reg) {
		em.merge(reg);
	}

}
