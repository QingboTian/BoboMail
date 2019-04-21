package mailClient.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.junit.Test;

public class GatherAction {

	@Test
	public Map<String,Object> getMail() throws Exception {
		
		Map<String,Object> map = new HashMap<String, Object>();

		// 1、连接邮件服务器的参数配置
		Properties props = new Properties();
		Properties props2 = new Properties();

		// 加载配置文件
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String recipientAccount = props.getProperty("uname");
		String recipientPassword = props.getProperty("pwd");
		String recipientAddress = props.getProperty("from");
		// 设置传输协议
		props2.setProperty("mail.store.protocol", props.getProperty("mail.store.protocol"));
		// 设置收件人的POP3服务器
		props.getProperty("mail.pop3.host");
		props2.setProperty("mail.pop3.host", props.getProperty("mail.pop3.host"));
		// 2、创建定义整个应用程序所需的环境信息的 Session 对象
		Session session = Session.getInstance(props2);
		// 设置调试信息在控制台打印出来
		// session.setDebug(true);

		Store store = session.getStore(props.getProperty("mail.store.protocol"));
		// 连接收件人POP3服务器
		store.connect(props.getProperty("mail.pop3.host"), recipientAccount, recipientPassword);
		// 获得用户的邮件账户，注意通过pop3协议获取某个邮件夹的名称只能为inbox
		Folder folder = store.getFolder("inbox");
		// 设置对邮件账户的访问权限
		folder.open(Folder.READ_WRITE);

		// 得到邮件账户的所有邮件信息
		Message[] messages = folder.getMessages();
		int count = 10;
		if (messages.length > 10){
			for (int i = messages.length - 1; i >= 0 && count > 0; i--) {
				// 获得邮件主题
				String subject = messages[i].getSubject();
				// 获得邮件发件人
				/*Address[] from = messages[i].getFrom();*/
				// 获取邮件内容（包含邮件内容的html代码）
				Object content = messages[i].getContent();
				map.put(subject, content);
				count--;
			}
		}else{
			for (int i = messages.length - 1; i >= 0; i--) {
				// 获得邮件主题
				String subject = messages[i].getSubject();
				// 获得邮件发件人
				/*Address[] from = messages[i].getFrom();*/
				// 获取邮件内容（包含邮件内容的html代码）
				Object content = messages[i].getContent();
				map.put(subject, content);
			}
		}
		

		// 关闭邮件夹对象
		folder.close();
		// 关闭连接对象
		store.close();
		
		return map;
	}

}
