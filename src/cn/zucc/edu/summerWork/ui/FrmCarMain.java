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

public class FrmCarMain extends JDialog implements ActionListener {
	
	private Beanusercar Modifycar = new Beanusercar();

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JPanel workCardisplacementPane = new JPanel();
	private JPanel workCaryear = new JPanel();
	private JPanel workCaruseyear = new JPanel();
	private JPanel workCarlength = new JPanel();

	private JButton btnOk = new JButton("确定");

	private JLabel CarNameState;
	private JLabel CarCategoryState;
	private JLabel CarCartypeState;
	private JLabel CartransmissionState;
	private JLabel edtCardisplacementPane;
	private JLabel edtCaryear;
	private JLabel edtCaruseyear;
	private JLabel edtCarlength;
	private JLabel edtCarcolor;
	private JLabel edtCartypeintroduce;
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
	private JLabel edtCarplatedate;
	
	private Font font = new Font("宋体", Font.BOLD, 18);
	private SimpleDateFormat DT = new SimpleDateFormat("yyyy-MM-dd");
 
	public FrmCarMain(Beanusercar Usercar) throws BaseException {
		this.setTitle("二手车信息修改");
		Modifycar.setCarnum(Usercar.getCarnum());
		CarNameState = new JLabel(SummerWorkUtil.carbrandManager.loadCarbrand(Usercar.getBrandnum()), JLabel.CENTER);
		CarCategoryState = new JLabel(Usercar.getCarcategory(), JLabel.CENTER);
		CarCartypeState  = new JLabel(SummerWorkUtil.cartypeManager.loadCartype(Usercar.getCartypenum()), JLabel.CENTER);
		CartransmissionState = new JLabel(Usercar.getCartransmission(), JLabel.CENTER);
		edtCardisplacementPane = new JLabel(Usercar.getCardisplacement().substring(0, Usercar.getCardisplacement().length()-1),JLabel.CENTER);
		edtCaryear = new JLabel(Usercar.getCaryear().substring(0,Usercar.getCaryear().length()-1), JLabel.CENTER);
		edtCaruseyear = new JLabel(Usercar.getCaruseyear().substring(0, Usercar.getCaruseyear().length()-1), JLabel.CENTER);
		edtCarlength = new JLabel(Integer.toString(Usercar.getCarlength()), JLabel.CENTER);
		edtCarcolor = new JLabel(Usercar.getCarcolor(),JLabel.CENTER);
		edtCartypeintroduce = new JLabel(Usercar.getCartypeintroduce(),JLabel.CENTER);
		edtCarplatedate = new JLabel(DT.format(Usercar.getCarplatedate()),JLabel.CENTER);
		CarNameState.setFont(font);
		CarCategoryState.setFont(font);
		CarCartypeState.setFont(font);
		CartransmissionState.setFont(font);
		btnOk.setFont(font);
		edtCarplatedate.setFont(font);;
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
		workPane.setLayout(new GridLayout(12, 2, 0, 10));
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		toolBar.add(btnOk);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
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
		workPane.add(edtCarplatedate);
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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnOk) {
			this.setVisible(false);
		}
	}

}