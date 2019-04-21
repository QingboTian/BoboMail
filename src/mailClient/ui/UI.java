package mailClient.ui;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import mailClient.action.GatherAction;
import mailClient.action.SendAction;
import mailClient.entry.MailEntry;

public class UI {
	// 创建初始图形化界面
	public static void frame() {
		Frame f = new Frame("BoboMail 	                    	QQ:820965236");
		// 定位与大小
		f.setBounds(500, 200, 1000, 700);
		// 布局
		f.setLayout(new FlowLayout());

		// 关闭客户端
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});

		MenuBar mb = new MenuBar();

		Menu gather = new Menu("收取");
		Menu write = new Menu("写邮件");
		Menu about = new Menu("关于");
		MenuItem mi = new MenuItem("写邮件");
		MenuItem abt = new MenuItem("关于");
		MenuItem get = new MenuItem("收取");

		mi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 创建发送邮件图形化界面
				createWriteUI();
			}

		});

		abt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});
		
		get.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					createGatherUI();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		mb.add(gather);
		mb.add(write);
		mb.add(about);
		write.add(mi);
		about.add(abt);
		gather.add(get);

		f.setMenuBar(mb);

		// 显示
		f.setVisible(true);
	}

	/*
	 * // 启动软件 public static void main(String[] args) { frame(); }
	 */

	public static void createWriteUI() {
		Frame f = new Frame("写邮件");
		// 定位与大小
		f.setBounds(500, 200, 900, 600);
		// 布局
		f.setLayout(new FlowLayout(FlowLayout.LEFT));

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				f.setVisible(false);
			}
		});

		Label addressee = new Label("收件人:");
		TextField ta = new TextField();
		ta.setColumns(110);
		Label copy = new Label("    抄送:");
		TextField tc = new TextField();
		tc.setColumns(110);
		Label bcc = new Label("    暗送:");
		TextField tb = new TextField();
		tb.setColumns(110);
		Label subject = new Label("    主题:");
		TextField ts = new TextField();
		ts.setColumns(110);
		Label message = new Label("    正文:");
		TextArea msg = new TextArea(23, 122);
		Button send = new Button("发送");

		f.add(addressee);
		f.add(ta);
		f.add(copy);
		f.add(tc);
		f.add(bcc);
		f.add(tb);
		f.add(subject);
		f.add(ts);
		f.add(message);
		f.add(msg);
		f.add(send);

		// 监听发送按钮
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 校验
				MailEntry me = new MailEntry();
				me.setAddressee(ta.getText());
				me.setCopy(tc.getText());
				me.setBcc(tb.getText());
				me.setSubject(ts.getText());
				me.setMassage(msg.getText());
				boolean check = new SendAction().check(me);
				// 发送
				if (check) {
					f.setVisible(false);
					boolean send2 = new SendAction().send(me);
					if (send2) {
						successWin();
					} else
						falseWin();

				} else {
					falseWin();
				}
			}
		});

		// 显示
		f.setVisible(true);
	}

	public static void successWin() {
		Frame f = new Frame("消息通知");

		f.setBounds(800, 400, 300, 200);

		f.setLayout(new FlowLayout(FlowLayout.CENTER));
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				f.setVisible(false);
			}
		});

		Label l = new Label("邮件发送成功！");
		Button b = new Button("确认");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
			}
		});
		f.add(l);
		f.add(b);

		f.setVisible(true);
	}

	public static void falseWin() {
		Frame f = new Frame("消息通知");

		f.setBounds(800, 400, 300, 200);

		f.setLayout(new FlowLayout(FlowLayout.CENTER));
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				f.setVisible(false);
			}
		});

		Label l = new Label("邮件发送失败！");
		Button b = new Button("确认");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
			}
		});
		f.add(l);
		f.add(b);

		f.setVisible(true);
	}

	public static void about() {
		Frame f = new Frame("关于");

		f.setBounds(800, 400, 400, 250);

		f.setLayout(new FlowLayout(FlowLayout.CENTER));
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				f.setVisible(false);
			}
		});

		Label l = new Label("此软件受版权保护，仅供学习使用，禁止商用！否则追究法律责任！");
		Label l2 = new Label("                          作者：啵 | 联系方式：820965236                         ");
		Button b = new Button("确认");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
			}
		});
		f.add(l);
		f.add(l2);
		f.add(b);

		f.setVisible(true);
	}
	
	public static void createGatherUI() throws Exception{
		final Map<String, Object> mail = new GatherAction().getMail();
		
		Frame f = new Frame("收邮件");
		// 定位与大小
		f.setBounds(550, 250, 900, 600);
		// 布局
		f.setLayout(new FlowLayout(FlowLayout.LEFT));

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				f.setVisible(false);
			}
		});
		
		Label l = new Label("邮件列表：");
		Choice c = new Choice();

		for (Map.Entry<String,Object> i : mail.entrySet()) {
			c.add(i.getKey());
		}
		
		TextArea content = new TextArea(33,122);

		c.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String k = (String)e.getItem();
				Object obj = mail.get(k);
				content.setText("");
				content.setText(String.valueOf(obj));
			}
		});
		
		f.add(l);
		f.add(c);
		f.add(content);
		
		
		f.setVisible(true);
	}
}
