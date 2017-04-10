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

public class FrmModifyCar extends JDialog implements ActionListener {

	private Beanusercar Modifycar = new Beanusercar();

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JPanel workCardisplacementPane = new JPanel();
	private JPanel workCaryear = new JPanel();
	private JPanel workCaruseyear = new JPanel();
	private JPanel workCarlength = new JPanel();

	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");

	private JLabel CarNum;
	private JLabel CarNameState;
	private JLabel CarCategoryState;
	private JLabel CarCartypeState;
	private JLabel CartransmissionState;
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

	private JTextField edtCardisplacementPane = new JTextField(10);
	private JTextField edtCaryear = new JTextField(10);
	private JTextField edtCaruseyear = new JTextField(10);
	private JTextField edtCarlength = new JTextField(10);
	private JTextField edtCarcolor = new JTextField(10);
	private JTextField edtCartypeintroduce = new JTextField(50);

	public FrmModifyCar(Beanusercar Usercar) throws BaseException {
		this.setTitle("二手车信息修改");
		Modifycar.setCarnum(Usercar.getCarnum());
		CarNum = new JLabel(Usercar.getCarnum(), JLabel.CENTER);
		CarNameState = new JLabel(SummerWorkUtil.carbrandManager.loadCarbrand(Usercar.getBrandnum()), JLabel.CENTER);
		CarCategoryState = new JLabel(Usercar.getCarcategory(), JLabel.CENTER);
		CarCartypeState = new JLabel(SummerWorkUtil.cartypeManager.loadCartype(Usercar.getCartypenum()), JLabel.CENTER);
		CartransmissionState = new JLabel(Usercar.getCartransmission(), JLabel.CENTER);
		edtCardisplacementPane
				.setText(Usercar.getCardisplacement().substring(0, Usercar.getCardisplacement().length() - 1));
		edtCaryear.setText(Usercar.getCaryear().substring(0, Usercar.getCaryear().length() - 1));
		edtCaruseyear.setText(Usercar.getCaruseyear().substring(0, Usercar.getCaruseyear().length() - 1));
		edtCarlength.setText(Integer.toString(Usercar.getCarlength()));
		edtCarcolor.setText(Usercar.getCarcolor());
		edtCartypeintroduce.setText(Usercar.getCartypeintroduce());
		FontUIResource fontRes = new javax.swing.plaf.FontUIResource(font);
		FrmAddCar.setUIFont(fontRes);
		CarNameState.setFont(font);
		CarCategoryState.setFont(font);
		CarCartypeState.setFont(font);
		CartransmissionState.setFont(font);
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
		edtCardisplacementPane.setFont(font);
		edtCaryear.setFont(font);
		edtCaruseyear.setFont(font);
		edtCarlength.setFont(font);
		edtCarcolor.setFont(font);
		edtCartypeintroduce.setFont(font);
		DateCarplatedate = new DatePicker(date, DefaultFormat, font, dimension);
		workPane.setLayout(new GridLayout(13, 2, 0, 10));
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelCarNum);
		workPane.add(CarNum);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
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
			Modifycar.setCardisplacement(edtCardisplacementPane.getText() + "L");
			if (edtCaryear.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入车辆生产年份", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				int Caryear = Integer.parseInt(edtCaryear.getText());
				if (Caryear <= 2000) {
					JOptionPane.showMessageDialog(null, "请输入正确的汽车生产年份", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (Caryear > 2016) {
					JOptionPane.showMessageDialog(null, "请输入正确的汽车生产年份", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "请输入正确的汽车生产年份", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Modifycar.setCaryear(edtCaryear.getText() + "年");
			if (edtCaruseyear.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入车辆车龄", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				int Caruseyear = Integer.parseInt(edtCaruseyear.getText());
				if (Caruseyear >= 6) {
					JOptionPane.showMessageDialog(null, "请输入正确的车辆车龄", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "请输入正确的车辆车龄", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Modifycar.setCaruseyear(edtCaruseyear.getText() + "年");
			Modifycar.setCarplatedate((Date) DateCarplatedate.getValue());
			if (edtCarlength.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入车辆行驶里程", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int carlength;
			try {
				carlength = Integer.parseInt(edtCarlength.getText());
				if (carlength >= 10000) {
					JOptionPane.showMessageDialog(null, "请输入正的车辆行驶里程", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "请输入整型数字车辆行驶里程", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Modifycar.setCarlength(carlength);
			if (edtCarcolor.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请输入车辆颜色", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Modifycar.setCarcolor(edtCarcolor.getText());
			Modifycar.setCartypeintroduce(edtCartypeintroduce.getText());
			try {
				SummerWorkUtil.carManager.Modifycar(Modifycar);
				FrmSerchCar.reloadUserCarApply();
				this.setVisible(false);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}
	}

}
