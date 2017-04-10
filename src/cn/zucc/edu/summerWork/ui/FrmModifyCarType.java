package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beancartype;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmModifyCarType extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JPanel Cartypenum = new JPanel();
	private JPanel Cartypename = new JPanel();
	private JPanel Cartypeintroduce = new JPanel();

	private JButton btnOk = new JButton("修改");
	private JButton btnCancel = new JButton("返回");

	private JLabel labelCartypenum = new JLabel("车辆车型编号：");
	private JLabel labelCartypename = new JLabel("车辆车型名称：");
	private JLabel labelCartypeintroduce = new JLabel("车辆车型介绍：");
	private JLabel edtCartypenum;
	private JLabel edtCartypename;
	
	private JTextField edtCartypeintroduce = new JTextField(20);

	private Font font = new Font("宋体", Font.BOLD, 18);

	public FrmModifyCarType(Beancartype ModifyCarType) {
		super();
		this.setTitle("二手车品牌申请");
		edtCartypenum = new JLabel(ModifyCarType.getCartypenum());
		edtCartypename = new JLabel(ModifyCarType.getCartypename());
		edtCartypeintroduce.setText(ModifyCarType.getCartypeintroduce());
		FontUIResource fontRes = new javax.swing.plaf.FontUIResource(font);
		FrmAddCar.setUIFont(fontRes);
		btnOk.setFont(font);
		btnCancel.setFont(font);
		labelCartypenum.setFont(font);
		labelCartypename.setFont(font);
		labelCartypeintroduce.setFont(font);
		edtCartypenum.setFont(font);
		edtCartypename.setFont(font);
		edtCartypeintroduce.setFont(font);
		workPane.setLayout(new GridLayout(4, 1));
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		Cartypenum.add(labelCartypenum);
		Cartypenum.add(edtCartypenum);
		workPane.add(Cartypenum);
		Cartypename.add(labelCartypename);
		Cartypename.add(edtCartypename);
		workPane.add(Cartypename);
		Cartypeintroduce.add(labelCartypeintroduce);
		Cartypeintroduce.add(edtCartypeintroduce);
		workPane.add(Cartypeintroduce);
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
				Beancartype Cartype = new Beancartype();
				Cartype.setCartypenum(edtCartypenum.getText());
				Cartype.setCartypename(edtCartypename.getText());
				Cartype.setCartypeintroduce(edtCartypeintroduce.getText());
				SummerWorkUtil.cartypeManager.ModifyCartype(Cartype);
				FrmSerchCarType.reloadUserCarbrandApply();
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}
}
