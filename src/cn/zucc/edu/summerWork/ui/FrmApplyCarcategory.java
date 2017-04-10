package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beancarcategory;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmApplyCarcategory extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JPanel brand = new JPanel();
	private JPanel categorynum = new JPanel();
	private JPanel categoryname = new JPanel();

	private JButton btnOk = new JButton("提交申请");
	private JButton btnCancel = new JButton("返回");

	private JLabel labelBrand = new JLabel("车辆品牌选择：");
	private JLabel labelCategorynum = new JLabel("车辆车系编号：");
	private JLabel labelCategoryname = new JLabel("车辆车系名字：");

	private JTextField edtCategorynum = new JTextField(20);
	private JTextField edtCategoryname = new JTextField(20);

	private JComboBox CarNameState;

	private Font font = new Font("宋体", Font.BOLD, 18);

	public FrmApplyCarcategory() throws BaseException {
		super();
		this.setTitle("二手车品牌申请");
		FontUIResource fontRes = new javax.swing.plaf.FontUIResource(font);
		FrmAddCar.setUIFont(fontRes);
		btnOk.setFont(font);
		btnCancel.setFont(font);
		labelBrand.setFont(font);
		labelCategorynum.setFont(font);
		labelCategoryname.setFont(font);
		edtCategorynum.setFont(font);
		edtCategoryname.setFont(font);
		CarNameState = new JComboBox(SummerWorkUtil.carbrandManager.loadCarbrandname());
		CarNameState.setSelectedIndex(0);
		CarNameState.setFont(font);
		workPane.setLayout(new GridLayout(3, 1));
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		brand.add(labelBrand);
		brand.add(CarNameState);
		workPane.add(brand);
		categorynum.add(labelCategorynum);
		categorynum.add(edtCategorynum);
		workPane.add(categorynum);
		categoryname.add(labelCategoryname);
		categoryname.add(edtCategoryname);
		workPane.add(categoryname);
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
				Beancarcategory carcategory = new Beancarcategory();
				carcategory.setBrandnum(SummerWorkUtil.carbrandManager.loadbrandnum(CarNameState.getSelectedItem().toString()));
				carcategory.setCategoryname(edtCategoryname.getText());
				carcategory.setCategorynum(edtCategorynum.getText());
				carcategory.setCategorytype("申请中");
				SummerWorkUtil.carcategoryManager.addCategory(carcategory);
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
