package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmSuperuser extends JFrame implements ActionListener {
	private JToolBar bar = new JToolBar();
	
	private JButton ButtonReApply =  new JButton("处理申请");
	private JButton ButtonBannedUser =  new JButton("封禁账户");
	private JButton ButtonSerchCar = new JButton("修改信息");
	private JButton ButtonNewSuperUser= new JButton("新管理员");
	
	public FrmSuperuser() {
		super();
		this.setTitle("用户" + Beanuser.LoginUser.getUsername() + "你好");
		bar.setLayout(new GridLayout(4, 1));
		Font font =  new Font("宋体", Font.BOLD, 24);
		ButtonReApply.setFont(font);
		ButtonBannedUser.setFont(font);
		ButtonSerchCar .setFont(font);
		ButtonNewSuperUser.setFont(font);
		bar.add(this.ButtonReApply);
		bar.add(this.ButtonBannedUser);
		bar.add(this.ButtonSerchCar);
		bar.add(this.ButtonNewSuperUser);
		this.getContentPane().add(bar, BorderLayout.CENTER);
		this.setSize(480, 360);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.ButtonReApply.addActionListener(this);
		this.ButtonBannedUser.addActionListener(this);
		this.ButtonSerchCar.addActionListener(this);
		this.ButtonNewSuperUser.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});	
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ButtonReApply) {
			FrmChooseReapply dlg = new FrmChooseReapply();
			this.setVisible(false);
			dlg.setVisible(true);
		}else if (e.getSource() == this.ButtonSerchCar) {
			FrmModifyChoose dlg = new FrmModifyChoose();
			this.setVisible(false);
			dlg.setVisible(true);
		}else if (e.getSource() == this.ButtonBannedUser) {
			try {
				FrmBannedUser dlg = new FrmBannedUser();
				this.setVisible(false);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}	
		}else if (e.getSource() == this.ButtonNewSuperUser) {
			FrmSuperRegister dlg = new FrmSuperRegister();
			dlg.setVisible(true);
		}
		
	}
}
