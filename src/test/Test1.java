package test;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Test1 {
	public static void main(String[] args) {
		Frame f = new Frame("一级菜单栏");

		f.setBounds(500, 200, 500, 400);

		f.setLayout(new FlowLayout());

		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		
		MenuBar mb = new MenuBar();
		
		Menu m = new Menu("文件");
		Menu m1 = new Menu("其它");
		
		MenuItem mi = new MenuItem("退出");
		MenuItem mi1 = new MenuItem("关于");
		
		mi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		
		//添加
		mb.add(m);
		m.add(mi);
		m.add(m1);
		m1.add(mi1);
		
		//菜单栏的添加较为特殊
		f.setMenuBar(mb);

		f.setVisible(true);
	}
}
