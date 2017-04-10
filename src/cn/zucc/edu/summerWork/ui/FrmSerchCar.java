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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmSerchCar extends JDialog implements ActionListener {
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton ButtonModifyCar = new JButton("修改二手车信息");
	

	private static Object tblCarTitle[] = Beanusercar.tableCarTitles;
	private static Object tblCarData[][];
	static DefaultTableModel tabCarModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	static DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private static JTable dataTableCar = new JTable();

	Font font = new Font("宋体", Font.BOLD, 18);

	static List<Beanusercar> allCar = null;
	Beanusercar beanusercar = null;

	static void reloadUserCarApply() throws BaseException {
		try {
			allCar = SummerWorkUtil.carManager.loadAllCars();
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
		dataTableCar.validate();
		dataTableCar.repaint();
	}

	public FrmSerchCar() throws BaseException {
		super();
		this.setTitle("二手车信息处理");

		toolBar.add(ButtonModifyCar);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);

		this.reloadUserCarApply();
		this.getContentPane().add(new JScrollPane(this.dataTableCar), BorderLayout.CENTER);

		this.setSize(1500, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.ButtonModifyCar.addActionListener(this);
		this.validate();
		this.dataTableCar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				beanusercar = allCar.get(FrmSerchCar.this.dataTableCar.getSelectedRow());
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmModifyChoose dlg = new FrmModifyChoose();
				dlg.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ButtonModifyCar) {
			int i = this.dataTableCar.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择要修改的二手车", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			beanusercar = allCar.get(i);
			try {
				FrmModifyCar dlg = new FrmModifyCar(beanusercar);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}
	}
}