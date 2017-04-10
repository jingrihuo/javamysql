package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import com.eltima.components.ui.DatePicker;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beansellcar;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmModifySellcar extends JDialog implements ActionListener {
	private boolean Sell = false;
	public Beansellcar SellCar;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();

	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");

	private JLabel labelSellDate = new JLabel("挂牌截止日期:");
	private JLabel labelSellPrice = new JLabel("挂牌价格:");
	private JLabel labelSellway = new JLabel("看车方式:");

	private Font font = new Font("宋体", Font.BOLD, 18);
	private Dimension dimension = new Dimension(177, 24);
	private DatePicker DateCarSellDate;
	private static final String DefaultFormat = "yyyy-MM-dd";
	private Date date = new Date();
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	DateFormatter df = new DateFormatter(format);

	private JTextField edtSellPrice = new JTextField(20);

	private JComboBox SellwayState = new JComboBox(new String[] { "远程", "实地" });

	public FrmModifySellcar(Beansellcar sellCar) throws BaseException {
		super();
		this.setTitle("卖车信息确认");
		SellCar = sellCar;
		btnOk.setFont(font);
		btnCancel.setFont(font);
		labelSellDate.setFont(font);
		labelSellPrice.setFont(font);
		labelSellway.setFont(font);
		edtSellPrice.setFont(font);
		SellwayState.setFont(font);
		DateCarSellDate = new DatePicker(date, DefaultFormat, font, dimension);
		DateCarSellDate.fd.setEditable(false);
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelSellPrice);
		workPane.add(edtSellPrice);
		workPane.add(labelSellDate);
		workPane.add(DateCarSellDate);
		workPane.add(labelSellway);
		workPane.add(SellwayState);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(360, 240);
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
			int price;
			try {
				price = Integer.parseInt(edtSellPrice.getText());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "二手车价格输入不正确", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Beanusercar beanusercar = null;
			try {
				 beanusercar = SummerWorkUtil.carManager.loadCar(SellCar.getCarnum());
			} catch (BaseException e1) {
				e1.printStackTrace();
				return;
			}
			if (price > beanusercar.getCarprice()) {
				JOptionPane.showMessageDialog(null, "二手车出售价格不可高于管理员制定的价格", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定以" + price + "元出售此车嘛？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				Beansellcar ModifySellcar = new Beansellcar();
				ModifySellcar.setSellnum(SellCar.getSellnum());
				ModifySellcar.setSellprice(price);
				ModifySellcar.setSelldate((Date) DateCarSellDate.getValue());
				ModifySellcar.setSellway(SellwayState.getSelectedItem().toString());
				ModifySellcar.setSelltype("下架");
				try {
					SummerWorkUtil.sellcarManager.ModifyCar(ModifySellcar);
					FrmSerchCar.reloadUserCarApply();
					Sell = true;	
					FrmSellCar.reloadCarTable();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
			this.setVisible(false);
		} else if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
		}

	}
	public boolean getSell() {
		return Sell;
	}

}
