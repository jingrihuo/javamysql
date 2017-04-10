package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.soap.SOAPBinding.Use;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.DateFormatter;

import com.eltima.components.ui.DatePicker;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmAddCar extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JPanel workCardisplacementPane = new JPanel();
	private JPanel workCaryear = new JPanel();
	private JPanel workCaruseyear = new JPanel();
	private JPanel workCarlength = new JPanel();

	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JButton btnApply = new JButton("没有你的车辆品牌，车辆车系，车辆车型？请点击此处申请");

	private JLabel labelCarNum = new JLabel("二手车编号：", JLabel.CENTER);
	private JLabel labelCarName = new JLabel("二手车品牌：", JLabel.CENTER);
	private JLabel labelCarState = new JLabel("二手车车系：", JLabel.CENTER);
	private JLabel labelCartype = new JLabel("二手车车型：", JLabel.CENTER);
	private JLabel labelCartransmission = new JLabel("二手车变速箱：", JLabel.CENTER);
	private JLabel labelCardisplacement = new JLabel("二手车排量：", JLabel.CENTER);
	private JLabel labeldisplacementunit = new JLabel("L");
	private JLabel labelCaryear = new JLabel("二手车生产年份：", JLabel.CENTER);
	private JLabel labeLyearunit = new JLabel("年");
	private JLabel labelCaruseyear = new JLabel("二手车车龄：", JLabel.CENTER);
	private JLabel labeLyearunit1 = new JLabel("年");
	private JLabel labelCarplatedate = new JLabel("二手车上牌时间：", JLabel.CENTER);
	private JLabel labelCarlength = new JLabel("二手车行驶里程：", JLabel.CENTER);
	private JLabel labellengthunit = new JLabel("公里");
	private JLabel labelCarcolor = new JLabel("二手车车辆颜色：", JLabel.CENTER);
	private JLabel labelCartypeintroduce = new JLabel("二手车车辆介绍：", JLabel.CENTER);

	private Font font = new Font("宋体", Font.BOLD, 18);
	private Dimension dimension = new Dimension(177, 24);
	private DatePicker DateCarplatedate;
	private static final String DefaultFormat = "yyyy-MM-dd";
	private Date date = new Date();
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	DateFormatter df = new DateFormatter(format);

	private JTextField edtCarNum = new JTextField(10);
	private JTextField edtCardisplacementPane = new JTextField(10);
	private JTextField edtCaryear = new JTextField(10);
	private JTextField edtCaruseyear = new JTextField(10);
	private JTextField edtCarlength = new JTextField(10);
	private JTextField edtCarcolor = new JTextField(10);
	private JTextField edtCartypeintroduce = new JTextField(50);

	private JComboBox CarNameState;
	private JComboBox CarCategoryState;
	private JComboBox CarCartypeState;
	private JComboBox CartransmissionState = new JComboBox(new String[] { "手动挡", "自动挡" });

	public FrmAddCar() throws BaseException {
		this.setTitle("二手车申请");
		btnApply.setFont(font);
		btnOk.setFont(font);
		btnCancel.setFont(font);
		labelCarNum.setFont(font);
		labelCarName.setFont(font);
		labelCarState.setFont(font);
		labelCartype.setFont(font);
		labelCartransmission.setFont(font);
		labelCardisplacement.setFont(font);
		labeldisplacementunit.setFont(font);
		labelCaryear.setFont(font);
		labeLyearunit.setFont(font);
		labelCaruseyear.setFont(font);
		labeLyearunit1.setFont(font);
		labelCarplatedate.setFont(font);
		labelCarlength.setFont(font);
		labellengthunit.setFont(font);
		labelCarcolor.setFont(font);
		labelCartypeintroduce.setFont(font);
		edtCarNum.setFont(font);
		edtCardisplacementPane.setFont(font);
		edtCaryear.setFont(font);
		edtCaruseyear.setFont(font);
		edtCarlength.setFont(font);
		edtCarcolor.setFont(font);
		edtCartypeintroduce.setFont(font);
		DateCarplatedate = new DatePicker(date, DefaultFormat, font, dimension);
		workPane.setLayout(new GridLayout(13, 2, 0, 10));
		CartransmissionState.setSelectedIndex(0);
		CarNameState = new JComboBox(SummerWorkUtil.carbrandManager.loadCarbrandname());
		CarNameState.setSelectedIndex(0);
		CarNameState.addActionListener(this);
		CarNameState.setFont(font);
		CarCategoryState = new JComboBox();
		CarCategoryState.removeAllItems();
		String[] result = SummerWorkUtil.carcategoryManager.loadCategoryname(CarNameState.getSelectedItem().toString());
		for (int i = 0; i < result.length; i++) {
			CarCategoryState.addItem(result[i]);
		}
		CarCategoryState.setSelectedItem(0);
		CarCategoryState.setFont(font);
		CarCartypeState = new JComboBox(SummerWorkUtil.cartypeManager.loadAllCartypename());
		CarCartypeState.setSelectedIndex(0);
		CarCartypeState.setFont(font);
		CartransmissionState.setFont(font);
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		toolBar.add(btnApply);
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelCarNum);
		workPane.add(edtCarNum);
		workPane.add(labelCarName);
		workPane.add(CarNameState);
		workPane.add(labelCarState);
		workPane.add(CarCategoryState);
		workPane.add(labelCartype);
		workPane.add(CarCartypeState);
		workPane.add(labelCartransmission);
		workPane.add(CartransmissionState);
		workPane.add(labelCardisplacement);
		workCardisplacementPane.add(edtCardisplacementPane);
		workCardisplacementPane.add(labeldisplacementunit);
		workPane.add(workCardisplacementPane);
		workPane.add(labelCaryear);
		workCaryear.add(edtCaryear);
		workCaryear.add(labeLyearunit);
		workPane.add(workCaryear);
		workPane.add(labelCaruseyear);
		workCaruseyear.add(edtCaruseyear);
		workCaruseyear.add(labeLyearunit1);
		workPane.add(workCaruseyear);
		workPane.add(labelCarplatedate);
		workPane.add(DateCarplatedate);
		workPane.add(labelCarlength);
		workCarlength.add(edtCarlength);
		workCarlength.add(labellengthunit);
		workPane.add(workCarlength);
		workPane.add(labelCarcolor);
		workPane.add(edtCarcolor);
		workPane.add(labelCartypeintroduce);
		workPane.add(edtCartypeintroduce);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(750, 540);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);
		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.btnApply.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
			try {
				if (CarCategoryState.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "请选择车系", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Beanusercar Usercar = new Beanusercar();
				if (edtCarNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入车辆编号", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Usercar.setCarnum(edtCarNum.getText());
				Usercar.setUsername(Beanuser.LoginUser.getUsername());
				Usercar.setCartypenum(
						SummerWorkUtil.cartypeManager.loadCartypenum(CarCartypeState.getSelectedItem().toString()));
				Usercar.setBrandnum(
						SummerWorkUtil.carbrandManager.loadbrandnum(CarNameState.getSelectedItem().toString()));
				Usercar.setCarcategory(CarCategoryState.getSelectedItem().toString());
				Usercar.setCartransmission(CartransmissionState.getSelectedItem().toString());
				if (edtCardisplacementPane.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入车辆排量", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					double CardisplacementPane = Double.parseDouble(edtCardisplacementPane.getText());
					if (CardisplacementPane >= 100) {
						JOptionPane.showMessageDialog(null, "请输入正确的汽车排量", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "请输入正确的汽车排量", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Usercar.setCardisplacement(edtCardisplacementPane.getText() + "L");
				if (edtCaryear.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入车辆生产年份", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					int Caryear = Integer.parseInt(edtCaryear.getText());
					if (Caryear <= 2000) {
						JOptionPane.showMessageDialog(null, "本二手车市场不收2006年以前的车", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (Caryear > 2016) {
						JOptionPane.showMessageDialog(null, "本二手车市场不收未来的车", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "请输入正确的汽车生产年份", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Usercar.setCaryear(edtCaryear.getText() + "年");
				if (edtCaruseyear.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入车辆车龄", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					int Caruseyear = Integer.parseInt(edtCaruseyear.getText());
					if (Caruseyear >= 6) {
						JOptionPane.showMessageDialog(null, "本二手车市场不收车龄在6年以上的车", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "请输入正确的车辆车龄", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Usercar.setCaruseyear(edtCaruseyear.getText() + "年");
				Usercar.setCarplatedate((Date) DateCarplatedate.getValue());
				if (edtCarlength.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入车辆行驶里程", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int carlength;
				try {
					carlength = Integer.parseInt(edtCarlength.getText());
					if (carlength >= 10000) {
						JOptionPane.showMessageDialog(null, "本二手车市场不收车辆行驶里程在10000公里以上的车", "错误",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "请输入整型数字车辆行驶里程", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Usercar.setCarlength(carlength);
				if (edtCarcolor.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入车辆颜色", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Usercar.setCarcolor(edtCarcolor.getText());
				Usercar.setCartypeintroduce(edtCartypeintroduce.getText());
				Usercar.setCartype("待审核");
				boolean result = SummerWorkUtil.carManager.addCar(Usercar);
				if (result) {
					JOptionPane.showMessageDialog(null, "你好用户" + Beanuser.LoginUser.getUsername() + "管理员已经接受了你的申请请耐心等待",
							"反馈", JOptionPane.PLAIN_MESSAGE);
					this.setVisible(false);
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == this.CarNameState) {
			try {
				this.CarCategoryState.removeAllItems();
				String[] result = SummerWorkUtil.carcategoryManager
						.loadCategoryname(CarNameState.getSelectedItem().toString());
				for (int i = 0; i < result.length; i++) {
					this.CarCategoryState.addItem(result[i]);
				}
				this.CarCartypeState.setSelectedItem(0);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == this.btnApply) {
			FrmApply dlg = new FrmApply();
			this.setVisible(false);
			dlg.setVisible(true);
		}
	}

	public static void setUIFont(javax.swing.plaf.FontUIResource f) {
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put(key, f);
		}
	}
}
