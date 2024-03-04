package in.ashokit.utility;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String to, String sub, String body) {
		boolean isSent = false;
		try {
			MimeMessage mimeMsg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
			helper.setTo(to);
			helper.setSubject(sub);
			helper.setText(body, true);

			mailSender.send(mimeMsg);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSent;
	}
}
