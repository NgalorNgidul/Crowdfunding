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
import net.crowdfunding.intf.beans.IMember;
import net.crowdfunding.intf.beans.IRegistration;
import net.crowdfunding.intf.model.Member;
import net.crowdfunding.intf.model.Registration;
import net.crowdfunding.intf.model.RegistrationPK;

import org.simbiosis.system.api.bean.IUserManager;
import org.simbiosis.system.bean.ISecurity;
import org.simbiosis.system.model.User;

@Stateless
@Remote(RegistrationManagement.class)
public class RegistrationManagementImpl implements RegistrationManagement {

	@Resource(mappedName = "java:/ConnectionFactory")
	ConnectionFactory connectionFactory;
	@Resource(mappedName = "java:/jms/queue/RegistrationQueue")
	Queue registrationQueue;

	@EJB(lookup = "java:global/System/SystemEjb/SecurityImpl")
	ISecurity iSecurity;

	@EJB(lookup = "java:global/SystemApi/SystemApiEjb/UserManager")
	IUserManager iUser;

	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/RegistrationImpl")
	IRegistration iRegistration;

	@EJB(lookup = "java:global/Crowdfunding/CrowdfundingEjb/MemberImpl")
	IMember iMember;

	@Resource(mappedName = "java:jboss/mail/Default")
	private javax.mail.Session mailSession;

	private void sendEmail(String emailAddress, String key, String name,
			int invest) {
		String url = "http://app.croowd.co.id/validation/#" + key;
		String text = invest == 0 ? "Yang terhormat sdr/i "
				+ name
				+ ",\n terima kasih atas ketertarikan anda mencari pembiayaan di Croowd.\n\n"
				+ "Untuk melanjutkan proses registrasi, silahkan klik link berikut\n\n"
				+ url
				+ "\n\nUntuk mempelajari lebih lanjut tentang Croowd silahkan kunjungi situs kami di http://www.croowd.co.id\n\n"
				+ "Salam sukses\n" + "Tim Croowd"
				: "Yang terhormat sdr/i "
						+ name
						+ ",\n terima kasih atas ketertarikan anda menjadi investor di Croowd.\n\n"
						+ "Apabila anda ingin melanjutkan menjadi calon investor di tempat kami silahkan klik link berikut\n\n"
						+ url
						+ "\n\nUntuk mempelajari lebih lanjut tentang Croowd silahkan kunjungi situs kami di http://www.croowd.co.id\n\n"
						+ "Salam sukses\n" + "Tim Croowd";
		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress(
					"Management Croowd<manager@croowd.co.id>");
			Address[] to = new InternetAddress[] { new InternetAddress(
					emailAddress) };

			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject("Validasi keanggotaan Croowd.co.id untuk sdr/i "
					+ name);
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
		sendEmail(dto.getEmail(), key, dto.getName(), dto.getInvest());
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

	@Override
	public RegistrationDto validate(String key) {
		Registration reg = iRegistration.get(key);
		if (reg != null && reg.getStatus() <= 1) {
			reg.setStatus(1);
			iRegistration.save(reg);
			//
			RegistrationDto result = new RegistrationDto();
			result.setTimestamp(reg.getId().getTimestamp());
			result.setEmail(reg.getId().getEmail());
			result.setName(reg.getName());
			//
			return result;
		}
		return null;
	}

	@Override
	public String registerMember(RegistrationDto dto) {
		Registration reg = iRegistration
				.get(dto.getEmail(), dto.getTimestamp());
		if (reg != null) {
			// Registered
			reg.setStatus(2);
			iRegistration.save(reg);
			// FIXME: company, branch dan subBranch masih di pantek hardcode
			Date today = new Date();
			long company = 2;
			long branch = 2;
			long subBranch = 2;
			// Create user
			User user = new User();
			user.setName(dto.getEmail());
			user.setActive(1);
			user.setRealName(dto.getName());
			user.setPassword(dto.getPassword());
			user.setEmail(dto.getEmail());
			user.setFirstModule("member");
			user = iUser.saveAsMember(company, branch, subBranch, user);
			// Create member
			Member member = new Member();
			member.setName(dto.getName());
			member.setEmail(dto.getEmail());
			member.setAddress(dto.getAddress());
			member.setCity(dto.getCity());
			member.setProvince(dto.getProvince());
			member.setTsCreate(today);
			member.setUserCreate(user.getId());
			iMember.save(member);
		}
		return "";
	}

}
