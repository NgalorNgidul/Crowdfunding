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
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

	@Resource(mappedName = "java:jboss/mail/Default")
	private javax.mail.Session mailSession;

	private void sendEmail(String emailAddress, String key, int invest) {
		String text = invest == 0 ? "Terima kasih atas ketertarikan anda mencari pembiayaan di Croowd.\n\n"
				+ "Apabila anda ingin melanjutkan klik link berikut\n\n"
				+ "http://app.croowd.co.id/member/verification/"
				+ key
				+ "\n\nUntuk mempelajari lebih lanjut silahkan kunjungi situs kami di http://www.croowd.co.id\n\n"
				+ "Salam sukses\n" + "Tim Croowd"
				: "Terima kasih atas ketertarikan anda menjadi investor di Croowd.\n\n"
						+ "Apabila anda ingin melanjutkan menjadi calon investor di tempat kami klik link berikut\n\n"
						+ "http://app.croowd.co.id/member/verification/"
						+ key
						+ "\n\nUntuk mempelajari lebih lanjut silahkan kunjungi situs kami di http://www.croowd.co.id\n\n"
						+ "Salam sukses\n" + "Tim Croowd";
		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress("manager@croowd.co.id");
			Address[] to = new InternetAddress[] { new InternetAddress(
					emailAddress) };

			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject("Registrasi dan validasi Croowd.co.id");
			m.setSentDate(new java.util.Date());
			m.setContent(text, "text/plain");
			Transport.send(m);
			System.out.println("Mail sent!");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
			System.out.println("Error in Sending Mail: " + e);
		}
	}

	@Override
	public String register(RegistrationDto dto) {
		// Generate key
		Date timestamp = new Date();
		String key = iSecurity.getUriRandomHash(timestamp.toString());
		//
		sendEmail(dto.getEmail(), key, dto.getInvest());
		//
		RegistrationPK id = new RegistrationPK();
		id.setEmail(dto.getEmail());
		id.setTimestamp(timestamp);
		Registration reg = new Registration();
		reg.setId(id);
		reg.setName(dto.getName());
		reg.setInvest(dto.getInvest());
		if (reg.getInvest() == 0) {
			reg.setPrincipal(dto.getPrincipal());
			reg.setTenor(dto.getTenor());
		}
		reg.setKey(key);
		try {
			Connection connection = connectionFactory.createConnection();
			javax.jms.Session session = connection.createSession(false,
					javax.jms.Session.AUTO_ACKNOWLEDGE);
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
