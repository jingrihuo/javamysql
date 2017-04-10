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
import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beancarcategory;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmSerchCarCategory extends JDialog implements ActionListener {
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton ButtonModifyCarBrand = new JButton("修改二手车车系信息");
	private JButton ButtonBanndCarBrand = new JButton("禁用二手车车系");
	private JButton ButtonRstBanndCarBrand = new JButton("启用二手车车系");
	
	private static Object tblCarTitle[] = new String[] { "品牌名称", "车系名称", "状态" };
	private static Object tblCarData[][];
	static DefaultTableModel tabCarModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	static DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private static JTable dataTableCar = new JTable();

	Font font = new Font("宋体", Font.BOLD, 18);

	static List<Beancarcategory> allCarcategory = null;
	Beancarcategory UserCarcategory = null;

	static void reloadUserCarbrandApply() throws BaseException {
		try {
			allCarcategory = SummerWorkUtil.carcategoryManager.loadCategoryname();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tcr.setHorizontalAlignment(JLabel.CENTER);
		dataTableCar.setDefaultRenderer(Object.class, tcr);
		dataTableCar.setModel(tabCarModel);
		tblCarData = new Object[allCarcategory.size()][tblCarTitle.length];
		for (int i = 0; i < allCarcategory.size(); i++) {
			tblCarData[i][0] = SummerWorkUtil.carbrandManager.loadCarbrand(allCarcategory.get(i).getBrandnum());
			tblCarData[i][1] = allCarcategory.get(i).getCategoryname();
			tblCarData[i][2] = allCarcategory.get(i).getCategorytype();
		}
		tabCarModel.setDataVector(tblCarData, tblCarTitle);
		dataTableCar.validate();
		dataTableCar.repaint();
	}

	public FrmSerchCarCategory() throws BaseException {
		super();
		this.setTitle("二手车信息处理");

		toolBar.add(ButtonModifyCarBrand);
		toolBar.add(ButtonBanndCarBrand);
		toolBar.add(ButtonRstBanndCarBrand);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);

		this.reloadUserCarbrandApply();
		this.getContentPane().add(new JScrollPane(this.dataTableCar), BorderLayout.CENTER);

		this.setSize(1500, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.ButtonModifyCarBrand.addActionListener(this);
		this.ButtonBanndCarBrand.addActionListener(this);
		this.ButtonRstBanndCarBrand.addActionListener(this);
		
		this.validate();
		this.dataTableCar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				UserCarcategory = allCarcategory.get(FrmSerchCarCategory.this.dataTableCar.getSelectedRow());
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
		if (e.getSource() == this.ButtonBanndCarBrand) {
			int i = this.dataTableCar.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择要禁用的车辆品牌", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!allCarcategory.get(i).getCategorytype().equals("通过")) {
				JOptionPane.showMessageDialog(null, "该品牌已经处于禁用状态", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定禁用"+allCarcategory.get(i).getCategoryname()+"嘛", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					SummerWorkUtil.carcategoryManager.modifyCategory("禁用", allCarcategory.get(i));
					this.reloadUserCarbrandApply();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.ButtonRstBanndCarBrand) {
			int i = this.dataTableCar.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择要解禁的车辆品牌", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!allCarcategory.get(i).getCategorytype().equals("禁用")) {
				JOptionPane.showMessageDialog(null, "该品牌不处于禁用状态", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定解禁"+allCarcategory.get(i).getCategoryname()+"嘛", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					SummerWorkUtil.carcategoryManager.modifyCategory("通过",allCarcategory.get(i));
					this.reloadUserCarbrandApply();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
		}else if (e.getSource() == this.ButtonModifyCarBrand) {
			int i = this.dataTableCar.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择要修改车辆品牌", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				FrmModifyCarCategory dlg = new FrmModifyCarCategory(allCarcategory.get(i));
				dlg.setVisible(true);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}
	}
}