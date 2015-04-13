package net.crowdfunding.api.impl.beans;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import net.crowdfunding.api.intf.beans.RegistrationManagement;
import net.crowdfunding.api.intf.dto.RegistrationDto;
import net.crowdfunding.intf.model.Registration;
import net.crowdfunding.intf.model.RegistrationPK;

import org.simbiosis.system.bean.ISecurity;

@Stateless
@Remote(RegistrationManagement.class)
public class RegistrationManagementImpl implements RegistrationManagement {

	@Resource(mappedName = "java:/ConnectionFactory")
	ConnectionFactory connectionFactory;
	@Resource(mappedName = "java:/jms/queue/RegistrationQueue")
	Queue registrationQueue;

	@EJB(lookup = "java:global/System/SystemEjb/SecurityImpl")
	ISecurity iSecurity;

	@Override
	public String register(RegistrationDto dto) {
		RegistrationPK id = new RegistrationPK();
		id.setEmail(dto.getEmail());
		id.setTimestamp(new Date());
		Registration reg = new Registration();
		reg.setId(id);
		reg.setName(dto.getName());
		reg.setInvest(dto.getInvest());
		if (reg.getInvest() == 0) {
			reg.setPrincipal(dto.getPrincipal());
			reg.setTenor(dto.getTenor());
		}
		reg.setKey(iSecurity.getUriRandomHash(id.getTimestamp().toString()));
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session
					.createProducer(registrationQueue);
			ObjectMessage message = session.createObjectMessage();
			message.setObject(reg);
			connection.start();
			producer.send(message);
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return "";
	}

}
