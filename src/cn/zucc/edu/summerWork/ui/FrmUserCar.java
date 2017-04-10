package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmUserCar extends JFrame implements ActionListener {
	private JMenuBar menubar = new JMenuBar();

	private JMenu menu_car = new JMenu("车辆管理");

	private JButton refresh = new JButton("刷新");

	private JMenuItem menuItem_AddCar = new JMenuItem("申请二手车");
	private JMenuItem menuItem_DeleteCar = new JMenuItem("二手车取回");

	private Object tblCarTitle[] = Beanusercar.tableCarTitles;
	private Object tblCarData[][];
	DefaultTableModel tabCarModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private JTable dataTableCar = new JTable();

	List<Beanusercar> allCar = null;
	Beanusercar beanusercar = null;

	public void reloadCarTable() throws BaseException {
		try {
			allCar = SummerWorkUtil.carManager.loadCars(Beanuser.LoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tcr.setHorizontalAlignment(JLabel.CENTER);
		dataTableCar.setDefaultRenderer(Object.class, tcr);
		dataTableCar.setModel(tabCarModel);
		tblCarData = new Object[allCar.size()][Beanusercar.tableCarTitles.length];
		for (int i = 0; i < allCar.size(); i++) {
			tblCarData[i][0] = SummerWorkUtil.carbrandManager.loadCarbrand(allCar.get(i).getBrandnum());
			tblCarData[i][1] = allCar.get(i).getCarcategory();
			tblCarData[i][2] = SummerWorkUtil.cartypeManager.loadCartype(allCar.get(i).getCartypenum());
			tblCarData[i][3] = allCar.get(i).getCartransmission();
			tblCarData[i][4] = allCar.get(i).getCardisplacement();
			tblCarData[i][5] = allCar.get(i).getCaryear();
			tblCarData[i][6] = allCar.get(i).getCaruseyear();
			tblCarData[i][7] = allCar.get(i).getCarplatedate();
			tblCarData[i][8] = allCar.get(i).getCarlength();
			tblCarData[i][9] = allCar.get(i).getCarcolor();
			tblCarData[i][10] = allCar.get(i).getCarprice();
			tblCarData[i][11] = allCar.get(i).getCartypeintroduce();
			tblCarData[i][12] = allCar.get(i).getCartype();
		}
		tabCarModel.setDataVector(tblCarData, tblCarTitle);
		this.dataTableCar.validate();
		this.dataTableCar.repaint();
	}

	public FrmUserCar() {
		super();
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("用户" + Beanuser.LoginUser.getUsername() + "你好");
		Font font =  new Font("宋体", Font.BOLD, 24);
		this.menu_car.setFont(font);
		this.menuItem_AddCar.setFont(font);
		this.menuItem_DeleteCar.setFont(font);
		this.refresh.setFont(font);
		// 菜单
		this.refresh.addActionListener(this);
		this.menu_car.add(this.menuItem_AddCar);
		this.menuItem_AddCar.addActionListener(this);
		this.menu_car.add(this.menuItem_DeleteCar);
		this.menuItem_DeleteCar.addActionListener(this);
		
		this.menubar.add(menu_car);
		this.menubar.add(refresh);
		this.setJMenuBar(menubar);
		this.getContentPane().add(new JScrollPane(this.dataTableCar), BorderLayout.CENTER);
		try {
			this.reloadCarTable();
		} catch (BaseException e1) {
			e1.printStackTrace();
		}
		// 状态栏
		this.dataTableCar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				beanusercar = allCar.get(FrmUserCar.this.dataTableCar.getSelectedRow());
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmUser dlg = new FrmUser();
			}
		});
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.menuItem_AddCar) {
			FrmAddCar dlg;
			try {
				dlg = new FrmAddCar();
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == this.menuItem_DeleteCar) {
			int i  =  this.dataTableCar.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择要取回的二手车", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定取回该车嘛", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {
				SummerWorkUtil.carManager.deleteCar(allCar.get(i));
				this.reloadCarTable();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
			}
		} else if (e.getSource() == this.refresh) {
			try {
				this.reloadCarTable();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}
	}
}
