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
import cn.zucc.edu.summerWork.model.Beancarcategory;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmModifyCarCategory extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JPanel brand = new JPanel();
	private JPanel categorynum = new JPanel();
	private JPanel categoryname = new JPanel();

	private JButton btnOk = new JButton("修改");
	private JButton btnCancel = new JButton("退出");
	
	private JLabel CarNameState;
	private JLabel labelBrand = new JLabel("车辆品牌选择：");
	private JLabel labelCategorynum = new JLabel("车辆车系编号：");
	private JLabel labelCategoryname = new JLabel("车辆车系名字：");
	private JLabel edtCategorynum;
	
	private JTextField edtCategoryname = new JTextField(20);

	private Font font = new Font("宋体", Font.BOLD, 18);

	public FrmModifyCarCategory(Beancarcategory ModifyCarCategory) throws BaseException {
		super();
		this.setTitle("修改车辆车系");
		CarNameState = new JLabel(SummerWorkUtil.carbrandManager.loadCarbrand(ModifyCarCategory.getBrandnum()));
		edtCategorynum = new JLabel(ModifyCarCategory.getCategorynum());
		edtCategoryname.setText(ModifyCarCategory.getCategoryname());
		FontUIResource fontRes = new javax.swing.plaf.FontUIResource(font);
		FrmAddCar.setUIFont(fontRes);
		btnOk.setFont(font);
		btnCancel.setFont(font);
		labelBrand.setFont(font);
		labelCategorynum.setFont(font);
		labelCategoryname.setFont(font);
		edtCategorynum.setFont(font);
		edtCategoryname.setFont(font);
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
		if (e.getSource() == this.btnOk) {
			try {
				Beancarcategory carcategory = new Beancarcategory();
				carcategory.setCategoryname(edtCategoryname.getText());
				carcategory.setCategorynum(edtCategorynum.getText());
				carcategory.setCategorytype("申请中");
				SummerWorkUtil.carcategoryManager.modifyCategory(carcategory);
				FrmSerchCarCategory.reloadUserCarbrandApply();
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if (e.getSource() == btnCancel) {
			this.setVisible(false);
			return;
		}

	}
}

