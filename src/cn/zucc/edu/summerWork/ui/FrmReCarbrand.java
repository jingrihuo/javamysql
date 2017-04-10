package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beancarbrand;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmReCarbrand extends JDialog implements ActionListener {
	private JMenuBar toolBar = new JMenuBar();

	private JMenu menu_Apply = new JMenu("处理申请");
	private JMenuItem PassApply = new JMenuItem("通过申请");
	private JMenuItem NoPassApply = new JMenuItem("拒绝申请");

	private static Object tblCarTitle[] = new String[] { "品牌名称", "品牌国别", "品牌介绍", "状态" };
	private static Object tblCarData[][];
	static DefaultTableModel tabCarModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	static DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private static JTable dataTableCar = new JTable();

	Font font = new Font("宋体", Font.BOLD, 18);

	static List<Beancarbrand> allCarbrand = null;
	Beancarbrand UserCarbrand = null;

	static void reloadUserCarbrandApply() throws BaseException {
		try {
			allCarbrand = SummerWorkUtil.carbrandManager.loadAllApplyCarbrands();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tcr.setHorizontalAlignment(JLabel.CENTER);
		dataTableCar.setDefaultRenderer(Object.class, tcr);
		dataTableCar.setModel(tabCarModel);
		tblCarData = new Object[allCarbrand.size()][tblCarTitle.length];
		for (int i = 0; i < allCarbrand.size(); i++) {
			tblCarData[i][0] = allCarbrand.get(i).getBrandname();
			tblCarData[i][1] = allCarbrand.get(i).getBrandcountry();
			tblCarData[i][2] = allCarbrand.get(i).getBrandintroduce();
			tblCarData[i][3] = allCarbrand.get(i).getBrandtype();
		}
		tabCarModel.setDataVector(tblCarData, tblCarTitle);
		dataTableCar.validate();
		dataTableCar.repaint();
	}

	public FrmReCarbrand() throws BaseException {
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
				UserCarbrand  = allCarbrand.get(FrmReCarbrand.this.dataTableCar.getSelectedRow());
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
					SummerWorkUtil.carbrandManager.modifyCarbrandtype("通过",allCarbrand.get(i));
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
					SummerWorkUtil.carbrandManager.modifyCarbrandtype("拒绝",UserCarbrand);
					this.reloadUserCarbrandApply();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}