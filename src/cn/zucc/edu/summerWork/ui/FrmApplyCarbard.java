package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.DateFormatter;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmApplyCarbard extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JPanel Brandnum = new JPanel();
	private JPanel Brandname = new JPanel();
	private JPanel Brandcountry = new JPanel();
	private JPanel Brandintroduce = new JPanel();

	private JButton btnOk = new JButton("提交申请");
	private JButton btnCancel = new JButton("返回");

	private JLabel labelBrandnum = new JLabel("车辆品牌编号：");
	private JLabel labelBrandname = new JLabel("车辆品牌名称：");
	private JLabel labelBrandcountry = new JLabel("车辆品牌国籍：");
	private JLabel labelBrandintroduce = new JLabel("车辆品牌介绍：");

	private JTextField edtBrandnum = new JTextField(20);
	private JTextField edtBrandname = new JTextField(20);
	private JTextField edtBrandcountry = new JTextField(20);
	private JTextField edtBrandintroduce = new JTextField(20);

	private Font font = new Font("宋体", Font.BOLD, 18);

	public FrmApplyCarbard() {
		super();
		this.setTitle("二手车品牌申请");
		FontUIResource fontRes = new javax.swing.plaf.FontUIResource(font);
		FrmAddCar.setUIFont(fontRes);
		btnOk.setFont(font);
		btnCancel.setFont(font);
		labelBrandnum.setFont(font);
		labelBrandname.setFont(font);
		labelBrandcountry.setFont(font);
		labelBrandintroduce.setFont(font);
		edtBrandnum.setFont(font);
		edtBrandname.setFont(font);
		edtBrandcountry.setFont(font);
		edtBrandintroduce.setFont(font);
		workPane.setLayout(new GridLayout(4, 1));
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		Brandnum.add(labelBrandnum);
		Brandnum.add(edtBrandnum);
		workPane.add(Brandnum);
		Brandname.add(labelBrandname);
		Brandname.add(edtBrandname);
		workPane.add(Brandname);
		Brandcountry.add(labelBrandcountry);
		Brandcountry.add(edtBrandcountry);
		workPane.add(Brandcountry);
		Brandintroduce.add(labelBrandintroduce);
		Brandintroduce.add(edtBrandintroduce);
		workPane.add(Brandintroduce);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(480, 360);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel) {
			FrmApply dlg = new FrmApply();
			this.setVisible(false);
			dlg.setVisible(true);
		} else if (e.getSource() == this.btnOk) {
			try {
				Beancarbrand Carbrand = new Beancarbrand();
				Carbrand.setBrandnum(edtBrandnum.getText());
				Carbrand.setBrandname(edtBrandname.getText());
				Carbrand.setBrandcountry(edtBrandcountry.getText());
				Carbrand.setBrandintroduce(edtBrandintroduce.getText());
				SummerWorkUtil.carbrandManager.addCarbrand(Carbrand);
				JOptionPane.showMessageDialog(null, "你好用户" + Beanuser.LoginUser.getUsername() + "管理员已经接受了你的申请请耐心等待",
						"标题", JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}
}
