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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.plaf.FontUIResource;

import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmApply extends JFrame implements ActionListener {
	private JToolBar bar = new JToolBar();
	
	private JButton ButtonCarbrand =  new JButton("申请车辆品牌");
	private JButton ButtonCaercategory =  new JButton("申请车辆车系");
	private JButton ButtonCartype = new JButton("申请车辆车型");
	private JButton ButtonReturn = new JButton("返回");
	
	public FrmApply() {
		super();
		this.setTitle("用户" + Beanuser.LoginUser.getUsername() + "你好,请选择你要提交的申请");
		Font font =  new Font("宋体", Font.BOLD, 24);
		FontUIResource fontRes = new javax.swing.plaf.FontUIResource(font);
		FrmAddCar.setUIFont(fontRes);
		ButtonCarbrand.setFont(font);
		ButtonCaercategory.setFont(font);
		ButtonCartype.setFont(font);
		ButtonReturn.setFont(font);
		bar.setLayout(new GridLayout(4, 1));
		bar.add(this.ButtonCarbrand);
		bar.add(this.ButtonCaercategory);
		bar.add(this.ButtonCartype);
		bar.add(this.ButtonReturn);
		this.getContentPane().add(bar, BorderLayout.CENTER);
		this.setSize(480, 360);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.ButtonCarbrand.addActionListener(this);
		this.ButtonCaercategory.addActionListener(this);
		this.ButtonCartype.addActionListener(this);
		this.ButtonReturn.addActionListener(this);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ButtonCarbrand) {
			FrmApplyCarbard dlg = new FrmApplyCarbard();
			this.setVisible(false);
			dlg.setVisible(true);
		}else if (e.getSource() == this.ButtonCaercategory) {
			FrmApplyCarcategory dlg;
			try {
				dlg = new FrmApplyCarcategory();
				this.setVisible(false);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			};
		}else if (e.getSource() == this.ButtonCartype) {
			FrmApplyCartype dlg =new FrmApplyCartype();
			this.setVisible(false);
			dlg.setVisible(true);
		}else if (e.getSource() == this.ButtonReturn) {
			FrmAddCar dlg;
			try {
				dlg = new FrmAddCar();
				this.setVisible(false);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
