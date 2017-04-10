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
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmBannedUser extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();

	private JButton ButtonBannedUser = new JButton("封禁用户");
	private JButton ButtonRestBannedUser = new JButton("解封用户");

	private static Object tblCarTitle[] = new String[] { "用户名", "用户电话", "用户邮箱地址", "用户住址", "用户创建日期", "用户状态" };
	private static Object tblCarData[][];
	static DefaultTableModel tabCarModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	static DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private static JTable dataTableCar = new JTable();

	Font font = new Font("宋体", Font.BOLD, 18);

	static List<Beanuser> allUser = null;
	Beanuser beanuser = null;

	static void reloadUserApply() throws BaseException {
		try {
			allUser = SummerWorkUtil.userManager.loadAllUsers();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tcr.setHorizontalAlignment(JLabel.CENTER);
		dataTableCar.setDefaultRenderer(Object.class, tcr);
		dataTableCar.setModel(tabCarModel);
		tblCarData = new Object[allUser.size()][tblCarTitle.length];
		for (int i = 0; i < allUser.size(); i++) {
			tblCarData[i][0] = allUser.get(i).getUsername();
			tblCarData[i][1] = allUser.get(i).getUserphone();
			tblCarData[i][2] = allUser.get(i).getUseremail();
			tblCarData[i][3] = allUser.get(i).getUseraddress();
			tblCarData[i][4] = allUser.get(i).getUsercreatedate();
			if (allUser.get(i).getUserbannedtime() == null) {
				tblCarData[i][5] = "可使用";
			} else {
				tblCarData[i][5] = "封禁中";
			}
		}
		tabCarModel.setDataVector(tblCarData, tblCarTitle);
		dataTableCar.validate();
		dataTableCar.repaint();
	}

	public FrmBannedUser() throws BaseException {
		super();
		this.setTitle("二手车信息处理");

		toolBar.add(ButtonBannedUser);
		toolBar.add(ButtonRestBannedUser);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);

		this.reloadUserApply();
		this.getContentPane().add(new JScrollPane(this.dataTableCar), BorderLayout.CENTER);

		this.setSize(1020, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.ButtonBannedUser.addActionListener(this);
		this.ButtonRestBannedUser.addActionListener(this);
		this.validate();
		this.dataTableCar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				beanuser = allUser.get(FrmBannedUser.this.dataTableCar.getSelectedRow());
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmSuperuser dlg = new FrmSuperuser();
				dlg.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ButtonBannedUser) {
			int i = this.dataTableCar.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(allUser.get(i).getUserbannedtime()!=null){
				JOptionPane.showMessageDialog(null, "用户"+allUser.get(i).getUsername()+"已经在封禁中", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定要封禁用户" + allUser.get(i).getUsername() + "嘛？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					SummerWorkUtil.userManager.userBannedtime(allUser.get(i).getUsername());
					this.reloadUserApply();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == ButtonRestBannedUser) {
			int i = this.dataTableCar.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(allUser.get(i).getUserbannedtime()==null){
				JOptionPane.showMessageDialog(null, "用户"+allUser.get(i).getUsername()+"不在封禁中", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定要解封用户" + allUser.get(i).getUsername() + "嘛？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					SummerWorkUtil.userManager.resetUserBannedtime(allUser.get(i).getUsername());
					this.reloadUserApply();
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
