package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.xml.sax.ErrorHandler;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beansellcar;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmShelvesCar extends JDialog implements ActionListener {
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton ButtonShelvesCar = new JButton("下架该车");
	private JButton ButtonModifyShelvesCar = new JButton("修改上架信息");
	
	private static Object tblCarTitle[] =  new String[] { "车辆品牌", "车辆车系", "车辆车型", "出售价格","挂牌日期","看车方式"};
	private static Object tblCarData[][];
	static DefaultTableModel tabCarModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	static DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private static JTable dataTableCar = new JTable();

	Font font = new Font("宋体", Font.BOLD, 18);

	static List<Beansellcar> sellcar = null; 
	
	Beansellcar beansellcar = null;
	
	public static void reloadCarTable() throws BaseException {
		try {
			sellcar = SummerWorkUtil.sellcarManager.loadCars(Beanuser.LoginUser.getUsername());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tcr.setHorizontalAlignment(JLabel.CENTER);
		dataTableCar.setDefaultRenderer(Object.class, tcr);
		dataTableCar.setModel(tabCarModel);
		tblCarData = new Object[sellcar.size()][Beanusercar.tableCarTitles.length];
		for (int i = 0; i < sellcar.size(); i++) {
			tblCarData[i][0] = SummerWorkUtil.carbrandManager.loadCarbrand(SummerWorkUtil.carManager.loadCar(sellcar.get(i).getCarnum()).getBrandnum());
			tblCarData[i][1] = SummerWorkUtil.carManager.loadCar(sellcar.get(i).getCarnum()).getCarcategory();
			tblCarData[i][2] = SummerWorkUtil.cartypeManager.loadCartype(SummerWorkUtil.carManager.loadCar(sellcar.get(i).getCarnum()).getCartypenum());
			tblCarData[i][3] = sellcar.get(i).getSellprice();
			tblCarData[i][4] = sellcar.get(i).getSelldate();
			tblCarData[i][5] = sellcar.get(i).getSellway();
		}
		tabCarModel.setDataVector(tblCarData, tblCarTitle);
		dataTableCar.validate();
		dataTableCar.repaint();
	}

	public FrmShelvesCar() throws BaseException {
		super();
		
		this.setTitle("买车");
		Font font =  new Font("宋体", Font.BOLD, 18);
		ButtonShelvesCar.setFont(font);
		toolBar.add(ButtonShelvesCar);
		toolBar.add(ButtonModifyShelvesCar);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(new JScrollPane(this.dataTableCar), BorderLayout.CENTER);
		try {
			this.reloadCarTable();
		} catch (BaseException e1) {
			e1.printStackTrace();
		}
		// 状态栏
		this.setSize(1500, 600);
		this.ButtonModifyShelvesCar.addActionListener(this);
		this.ButtonShelvesCar.addActionListener(this);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);
		this.validate();
		this.dataTableCar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				beansellcar = sellcar.get(FrmShelvesCar.this.dataTableCar.getSelectedRow());
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmSellcarChoose dlg = new FrmSellcarChoose();
				dlg.setVisible(true);
			}
		});
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ButtonModifyShelvesCar) {
			int i  =  this.dataTableCar.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择要修改的二手车", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				FrmModifySellcar dlg = new FrmModifySellcar(sellcar.get(i));
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.ButtonShelvesCar) {
			int i  =  this.dataTableCar.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择要修改的二手车", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定要下架该车嘛？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				try {
					SummerWorkUtil.sellcarManager.ShelvesCar(sellcar.get(i));
					this.reloadCarTable();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

