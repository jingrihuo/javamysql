package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beansellcar;
import cn.zucc.edu.summerWork.model.Beantradingcords;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmBuyCar extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();

	private String brandnum = " ";
	private String carcategory = " ";

	private JButton btnquery = new JButton("查询");

	private JLabel labelCarName = new JLabel("二手车品牌：", JLabel.CENTER);
	private JLabel labelCarState = new JLabel("二手车车系：", JLabel.CENTER);

	private JButton btnBuyCar = new JButton("买车");
	private JButton btnShowCar = new JButton("查看车辆信息");

	private JComboBox CarNameState;
	private JComboBox CarCategoryState;

	private Font font = new Font("宋体", Font.BOLD, 18);

	private Object tblCarTitle[] = new String[] { "车辆品牌", "车辆车系", "车辆车型", "出售价格", "挂牌日期", "看车方式" };
	private Object tblCarData[][];
	DefaultTableModel tabCarModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private JTable dataTableCar = new JTable();

	List<Beanusercar> sellcar = null;
	Beanusercar beansellcar = null;

	public void reloadCarTable() throws BaseException {
		try {
			sellcar = SummerWorkUtil.sellcarManager.QuerySellCar(brandnum, carcategory,
					Beanuser.LoginUser.getUsername());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tcr.setHorizontalAlignment(JLabel.CENTER);
		dataTableCar.setDefaultRenderer(Object.class, tcr);
		dataTableCar.setModel(tabCarModel);
		tblCarData = new Object[sellcar.size()][Beanusercar.tableCarTitles.length];
		for (int i = 0; i < sellcar.size(); i++) {
			tblCarData[i][0] = SummerWorkUtil.carbrandManager.loadCarbrand(sellcar.get(i).getBrandnum());
			tblCarData[i][1] = sellcar.get(i).getCarcategory();
			tblCarData[i][2] = SummerWorkUtil.cartypeManager.loadCartype(sellcar.get(i).getCartypenum());
			tblCarData[i][3] = SummerWorkUtil.sellcarManager.loadSellcar(sellcar.get(i).getCarnum()).getSellprice();
			tblCarData[i][4] = SummerWorkUtil.sellcarManager.loadSellcar(sellcar.get(i).getCarnum()).getSelldate();
			tblCarData[i][5] = SummerWorkUtil.sellcarManager.loadSellcar(sellcar.get(i).getCarnum()).getSellway();
		}
		tabCarModel.setDataVector(tblCarData, tblCarTitle);
		dataTableCar.validate();
		dataTableCar.repaint();
	}

	public FrmBuyCar() throws BaseException {
		super();
		this.setTitle("买车");
		labelCarName.setFont(font);
		labelCarState.setFont(font);
		CarNameState = new JComboBox(SummerWorkUtil.carbrandManager.loadCarbrandname());
		CarNameState.setFont(font);
		CarCategoryState = new JComboBox();
		toolBar.add(labelCarName);
		toolBar.add(CarNameState);
		toolBar.add(labelCarState);
		toolBar.add(CarCategoryState);
		toolBar.add(btnquery);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		workPane.add(btnBuyCar);
		workPane.add(btnShowCar);
		this.getContentPane().add(workPane, BorderLayout.SOUTH);
		this.reloadCarTable();
		this.getContentPane().add(new JScrollPane(this.dataTableCar), BorderLayout.CENTER);
		this.setSize(1200, 800);
		this.btnBuyCar.addActionListener(this);
		this.btnShowCar.addActionListener(this);
		this.btnquery.addActionListener(this);
		this.CarNameState.addActionListener(this);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);
		this.validate();
		this.dataTableCar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				beansellcar = sellcar.get(FrmBuyCar.this.dataTableCar.getSelectedRow());
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmUser dlg = new FrmUser();
				dlg.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnquery) {
			try {
				brandnum = SummerWorkUtil.carbrandManager.loadbrandnum(CarNameState.getSelectedItem().toString());
				carcategory = this.CarCategoryState.getSelectedItem().toString();
				this.reloadCarTable();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == this.CarNameState) {
			this.CarCategoryState.removeAllItems();
			try {
				String[] result = SummerWorkUtil.carcategoryManager
						.loadCategoryname(CarNameState.getSelectedItem().toString());
				for (int i = 0; i < result.length; i++) {
					this.CarCategoryState.addItem(result[i]);
				}
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
			this.CarCategoryState.setFont(font);
			this.CarCategoryState.setSelectedItem(0);
		} else if (e.getSource() == this.btnShowCar) {
			int i = this.dataTableCar.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择要查看的二手车", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				FrmCarMain dlg = new FrmCarMain(sellcar.get(i));
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.btnBuyCar) {
			int i = this.dataTableCar.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择要购买的二手车", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				if (JOptionPane.showConfirmDialog(this, "确定以" + SummerWorkUtil.sellcarManager.loadSellcar(sellcar.get(i).getCarnum()).getSellprice() + "元购买此车嘛？", "确认",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				try {
					Beantradingcords Tradingcords = new Beantradingcords();
					Tradingcords.setUsername(Beanuser.LoginUser.getUsername());
					Tradingcords.setTradingsell(SummerWorkUtil.sellcarManager.loadSellcar(sellcar.get(i).getCarnum()).getUsername());
					Tradingcords.setTradingbuy(Beanuser.LoginUser.getUsername());
					Tradingcords.setTradingcarnum(sellcar.get(i).getCarnum());
					Tradingcords.setTradingprice(SummerWorkUtil.sellcarManager.loadSellcar(sellcar.get(i).getCarnum()).getSellprice());
					SummerWorkUtil.tradingcordsManager.AddTradingcords(Tradingcords);
					SummerWorkUtil.sellcarManager.DeleteSellCar(SummerWorkUtil.sellcarManager.loadSellcar(sellcar.get(i).getCarnum()));
					this.reloadCarTable();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
				}
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}
	}
}
