package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmCarPrice extends JDialog implements ActionListener {

	Beanusercar beanusercar = null;

	private JPanel workPane = new JPanel();
	private JPanel toolBar = new JPanel();

	private JButton btnOk = new JButton("确认");
	private JButton btnCancel = new JButton("取消");

	private JLabel LabelCarPrice = new JLabel("二手车价格", JLabel.CENTER);
	private JLabel LabelCarPriceUnit = new JLabel("元", JLabel.CENTER);
	
	private JTextField edtCarPrice = new JTextField(20);
	int price = -1;

	public FrmCarPrice(Beanusercar usecar) {
		super();
		this.setTitle("请根据调查报告给一个合理的二手车价格");
		Font font = new Font("宋体", Font.BOLD, 16);
		FontUIResource fontRes = new javax.swing.plaf.FontUIResource(font);
		FrmAddCar.setUIFont(fontRes);
		btnOk.setFont(font);
		btnCancel.setFont(font);
		LabelCarPrice.setFont(font);
		LabelCarPriceUnit.setFont(font);
		edtCarPrice.setFont(font);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(LabelCarPrice);
		workPane.add(edtCarPrice);
		workPane.add(LabelCarPriceUnit);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(420, 160);

		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);
		beanusercar = usecar;
		this.validate();
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnOk) {
			try {
				price = Integer.parseInt(edtCarPrice.getText());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "二手车价格输入不正确", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定以" + price + "元通过申请嘛？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					SummerWorkUtil.carManager.AddCarPrice(price, beanusercar);
					SummerWorkUtil.carManager.ModifyCartype("通过申请", beanusercar);
					FrmReCar.reloadUserCarApply();
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
		}
	}

	public int getprice() {
		return this.price;
	}
}