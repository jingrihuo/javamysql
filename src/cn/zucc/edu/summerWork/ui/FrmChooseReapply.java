package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmChooseReapply extends JFrame implements ActionListener {
	private JToolBar bar = new JToolBar();
	
	private JButton ButtonApplyCar =  new JButton("二手车申请");
	private JButton ButtonApplyCarbrand =  new JButton("二手车厂商申请");
	private JButton ButtonApplyCarcategory = new JButton("二手车车系申请");
	private JButton ButtonApplyCartype = new JButton("二手车车型申请");
	
	public FrmChooseReapply() {
		super();
		this.setTitle("用户" + Beanuser.LoginUser.getUsername() + "你好");
		bar.setLayout(new GridLayout(4, 1));
		Font font =  new Font("宋体", Font.BOLD, 24);
		ButtonApplyCar.setFont(font);
		ButtonApplyCarbrand.setFont(font);
		ButtonApplyCarcategory.setFont(font);
		ButtonApplyCartype.setFont(font);
		bar.add(this.ButtonApplyCar);
		bar.add(this.ButtonApplyCarbrand);
		bar.add(this.ButtonApplyCarcategory);
		bar.add(this.ButtonApplyCartype);
		this.getContentPane().add(bar, BorderLayout.CENTER);
		this.setSize(480, 360);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.ButtonApplyCar.addActionListener(this);
		this.ButtonApplyCarbrand.addActionListener(this);
		this.ButtonApplyCarcategory.addActionListener(this);
		this.ButtonApplyCartype.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmSuperuser dlg = new FrmSuperuser();
				dlg.setVisible(true);
			}
		});	
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ButtonApplyCar) {
			try {
				FrmReCar dlg = new FrmReCar();
				this.setVisible(false);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.ButtonApplyCarbrand) {
			try {
				FrmReCarbrand dlg = new FrmReCarbrand();
				this.setVisible(false);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.ButtonApplyCarcategory) {
			try {
				FrmReCarCategory dlg = new FrmReCarCategory();
				this.setVisible(false);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.ButtonApplyCartype) {
			try {
				FrmReCartype dlg = new FrmReCartype();
				this.setVisible(false);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}
	}
}
