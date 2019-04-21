package mailClient.action;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import mailClient.entry.MailEntry;

public class SendAction {
	public boolean check(MailEntry me) {
		String r = "\\w+@\\w+.\\w+";

		String addressee = me.getAddressee();
		String copy = me.getCopy();
		String bcc = me.getBcc();
		String subject = me.getSubject();
		if (addressee == null || addressee.trim().equals(""))
			return false;
		if (subject == null || subject.trim().equals(""))
			return false;
		if (!addressee.matches(r))
			return false;
		if (!copy.trim().equals("") && !copy.matches(r))
			return false;
		if (!bcc.trim().equals("") && !bcc.matches(r))
			return false;

		return true;
	}

	public boolean send(MailEntry me) {
		try {
			Properties props = new Properties();
			// 加载配置文件
			props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
			String host = props.getProperty("host");
			String uname = props.getProperty("uname");
			String pwd = props.getProperty("pwd");
			Session session = MailUtils.createSession(host, uname, pwd);
			String from = props.getProperty("from");
			String subject = me.getSubject();
			String to = me.getAddressee();
			Mail mail = new Mail(from, to, subject, me.getMassage());
			if(!me.getCopy().trim().equals(""))
				mail.addCcAddress(me.getCopy());
			if(!me.getBcc().trim().equals(""))
				mail.addBccAddress(me.getBcc());
			try {
				MailUtils.send(session, mail);
			} catch (MessagingException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
