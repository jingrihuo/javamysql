package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("注册");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelUser = new JLabel("     用户名： ");
	private JLabel labelPwd = new JLabel("    设置密码：");
	private JLabel labelPwd2 = new JLabel("    重复密码：");
	private JLabel labelUserphone = new JLabel("用户手机号码：");
	private JLabel labelUseremail = new JLabel("用户邮箱地址：");
	private JLabel labelUseraddress = new JLabel("    用户住址：");

	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JTextField edtUserphone = new JTextField(20);
	private JTextField edtUseremail = new JTextField(20);
	private JTextField edtUseraddress = new JTextField(20);

	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		Font font = new Font("宋体", Font.BOLD, 18);
		FontUIResource fontRes = new javax.swing.plaf.FontUIResource(font);
		FrmAddCar.setUIFont(fontRes);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnOk.setFont(font);
		btnCancel.setFont(font);
		labelUser.setFont(font);
		labelPwd.setFont(font);
		labelPwd2.setFont(font);
		labelUserphone.setFont(font);
		labelUseremail.setFont(font);
		labelUseraddress.setFont(font);
		edtUserId.setFont(font);
		edtPwd.setFont(font);
		edtPwd2.setFont(font);
		edtUserphone.setFont(font);
		edtUseremail.setFont(font);
		edtUseraddress.setFont(font);
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		workPane.add(labelUserphone);
		workPane.add(edtUserphone);
		workPane.add(labelUseremail);
		workPane.add(edtUseremail);
		workPane.add(labelUseraddress);
		workPane.add(edtUseraddress);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(500,320);

		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel)
			this.setVisible(false);
		else if (e.getSource() == this.btnOk) {
			String username = this.edtUserId.getText();
			String pwd1 = new String(this.edtPwd.getPassword());
			String pwd2 = new String(this.edtPwd2.getPassword());
			String userphone = this.edtUserphone.getText();
			String useremail = this.edtUseremail.getText();
			String useraddress = this.edtUseraddress.getText();
			try {
				SummerWorkUtil.userManager.reg(username, pwd1, pwd2, userphone, useremail, useraddress);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
