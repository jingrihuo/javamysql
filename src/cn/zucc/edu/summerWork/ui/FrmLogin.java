package cn.zucc.edu.summerWork.ui;

import java.sql.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.plaf.FontUIResource;

import cn.zucc.edu.summerWork.util.BaseException;
import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.ui.*;

import java.util.*;

public class FrmLogin extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();

	private JButton btnLogin = new JButton("登陆");
	private JButton btnCancel = new JButton("退出");
	private JButton btnRegister = new JButton("注册");

	private JLabel labelUser = new JLabel("用户：");
	private JLabel labelPwd = new JLabel("密码：");

	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);

	public FrmLogin() {
		this.setTitle("二手车市场系统");
		Font font = new Font("宋体", Font.BOLD, 18);
		FontUIResource fontRes = new javax.swing.plaf.FontUIResource(font);
		FrmAddCar.setUIFont(fontRes);
		btnLogin.setFont(font);
		btnCancel.setFont(font);
		btnRegister.setFont(font);
		labelUser.setFont(font);
		labelPwd.setFont(font);
		edtUserId.setFont(font);
		edtPwd.setFont(font);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 180);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.btnRegister.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLogin) {
			Beanuser user = new Beanuser();
			String userid = this.edtUserId.getText();
			String pwd = new String(this.edtPwd.getPassword());
			try {
				user = SummerWorkUtil.userManager.login(userid, pwd);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if (user.getUserbannedtime() != null) {
					if (now.getTime() > user.getUserbannedtime().getTime()) {
						SummerWorkUtil.userManager.resetUserBannedtime(user.getUsername());
					} else {
						JOptionPane.showMessageDialog(null, "由于某种原因您的账号在" + user.getUserbannedtime() + "前处于封禁状态", "错误",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (user.getUsercode().equals(pwd)) {
					Beanuser.LoginUser = user;
					if (user.getUsertype().equals("用户")) {
						SummerWorkUtil.userManager.resetWrongPwd(user.getUsername());
						FrmUser dlg = new FrmUser();
						this.setVisible(false);
					} else {
						SummerWorkUtil.userManager.resetWrongPwd(user.getUsername());
						FrmSuperuser dlg = new FrmSuperuser();
						this.setVisible(false);
					}
				} else {
					int i = 3 - SummerWorkUtil.userManager.wrongPwd(user.getUsername());
					if (i <= 0) {
						SummerWorkUtil.userManager.userBannedtime(user.getUsername());
						JOptionPane.showMessageDialog(null, "由于密码输入错误次数过多，你的账户将会被暂时封禁", "错误",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					JOptionPane.showMessageDialog(null, "密码输入错误,你还剩" + i + "输入机会", "错误", JOptionPane.ERROR_MESSAGE);
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if (e.getSource() == this.btnRegister) {
			FrmRegister dlg = new FrmRegister(this, "注册", true);
			dlg.setVisible(true);
		}
	}

}
