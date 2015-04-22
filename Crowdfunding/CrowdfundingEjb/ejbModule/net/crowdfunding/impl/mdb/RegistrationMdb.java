package net.crowdfunding.impl.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import net.crowdfunding.intf.model.Registration;

/**
 * Message-Driven Bean implementation class for: RegistrationMdb
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/RegistrationQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class RegistrationMdb implements MessageListener {

	@PersistenceContext(unitName = "CrowdfundingEjb", type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public RegistrationMdb() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		ObjectMessage msg = (ObjectMessage) message;
		try {
			if (msg != null) {
				Registration reg = (Registration) msg.getObject();
				em.persist(reg);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
