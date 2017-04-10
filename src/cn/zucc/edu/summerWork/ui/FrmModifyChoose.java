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

public class FrmModifyChoose extends JFrame implements ActionListener {
	private JToolBar bar = new JToolBar();
	
	private JButton ButtonModifCar =  new JButton("修改二手车");
	private JButton ButtonModifCarBrand =  new JButton("修改二手车品牌");
	private JButton ButtonModifCarCategory = new JButton("修改二手车车系");
	private JButton ButtonModifCarType = new JButton("修改二手车车型");
	
	public FrmModifyChoose() {
		super();
		this.setTitle("用户" + Beanuser.LoginUser.getUsername() + "你好");
		bar.setLayout(new GridLayout(4, 1));
		Font font =  new Font("宋体", Font.BOLD, 24);
		ButtonModifCar.setFont(font);
		ButtonModifCarBrand.setFont(font);
		ButtonModifCarCategory .setFont(font);
		ButtonModifCarType.setFont(font);
		bar.add(this.ButtonModifCar);
		bar.add(this.ButtonModifCarBrand);
		bar.add(this.ButtonModifCarCategory);
		bar.add(this.ButtonModifCarType);
		this.getContentPane().add(bar, BorderLayout.CENTER);
		this.setSize(480, 360);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.ButtonModifCar.addActionListener(this);
		this.ButtonModifCarBrand.addActionListener(this);
		this.ButtonModifCarCategory.addActionListener(this);
		this.ButtonModifCarType.addActionListener(this);
		
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
		if (e.getSource() == this.ButtonModifCar) {
			try {
				FrmSerchCar dlg =new FrmSerchCar();
				dlg.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.ButtonModifCarBrand) {
			try {
				FrmSerchCarBrand dlg = new FrmSerchCarBrand();
				dlg.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.ButtonModifCarCategory) {
			try {
				FrmSerchCarCategory dlg = new FrmSerchCarCategory();
				dlg.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.ButtonModifCarType) {
			try {
				FrmSerchCarType dlg = new FrmSerchCarType();
				dlg.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}

