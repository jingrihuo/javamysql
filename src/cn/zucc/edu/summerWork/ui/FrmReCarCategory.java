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

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beancarcategory;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmReCarCategory extends JDialog implements ActionListener {
	private JMenuBar toolBar = new JMenuBar();

	private JMenu menu_Apply = new JMenu("处理申请");
	private JMenuItem PassApply = new JMenuItem("通过申请");
	private JMenuItem NoPassApply = new JMenuItem("拒绝申请");

	private static Object tblCarTitle[] = new String[] { "车辆品牌", "申请车系", "申请状态" };
	private static Object tblCarData[][];
	static DefaultTableModel tabCarModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	static DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private static JTable dataTableCar = new JTable();

	Font font = new Font("宋体", Font.BOLD, 18);

	static List<Beancarcategory> allCarcategory= null;
	Beancarcategory UserCarcategory= null;

	static void reloadUserCarbrandApply() throws BaseException {
		try {
			allCarcategory = SummerWorkUtil.carcategoryManager.loadApplyCategoryname();
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

	public FrmReCarCategory() throws BaseException {
		super();
		this.setTitle("车辆品牌申请处理");
		this.menu_Apply.setFont(font);
		this.PassApply.setFont(font);
		this.NoPassApply.setFont(font);

		this.menu_Apply.add(PassApply);
		this.PassApply.addActionListener(this);
		this.menu_Apply.add(NoPassApply);
		this.NoPassApply.addActionListener(this);

		this.toolBar.add(this.menu_Apply);
		this.setJMenuBar(toolBar);

		this.reloadUserCarbrandApply();
		this.getContentPane().add(new JScrollPane(this.dataTableCar), BorderLayout.CENTER);

		this.setSize(1300, 960);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.dataTableCar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserCarcategory  = allCarcategory.get(FrmReCarCategory.this.dataTableCar.getSelectedRow());
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmChooseReapply dlg = new FrmChooseReapply();
				dlg.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.PassApply) {
			int i = this.dataTableCar.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择要处理的申请", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定通过申请嘛？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					SummerWorkUtil.carcategoryManager.modifyCategory("通过",allCarcategory.get(i));
					this.reloadUserCarbrandApply();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == this.NoPassApply) {
			int i = this.dataTableCar.getSelectedRow();
			if (i<0) {
				JOptionPane.showMessageDialog(null, "请选择要处理的申请", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定拒绝申请嘛？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					SummerWorkUtil.carcategoryManager.modifyCategory("拒绝",allCarcategory.get(i));
					this.reloadUserCarbrandApply();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
