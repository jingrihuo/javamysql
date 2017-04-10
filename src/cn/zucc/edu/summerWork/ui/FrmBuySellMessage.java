package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Button;
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
import cn.zucc.edu.summerWork.model.Beantradingcords;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmBuySellMessage extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();

	private JButton btnreturn = new JButton("返回");
	
	private Font font = new Font("宋体", Font.BOLD, 18);

	private Object tblCarTitle[] = new String[] { "卖家", "买家", "成交金额", "成交时间"};
	private Object tblCarData[][];
	DefaultTableModel tabCarModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private JTable dataTableCar = new JTable();

	List<Beantradingcords> tradingcords = null;
	Beanusercar beansellcar = null;

	public void reloadCarTable() throws BaseException {
		try {
			tradingcords = SummerWorkUtil.tradingcordsManager.LoadTradingcords();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tcr.setHorizontalAlignment(JLabel.CENTER);
		dataTableCar.setDefaultRenderer(Object.class, tcr);
		dataTableCar.setModel(tabCarModel);
		tblCarData = new Object[tradingcords.size()][Beanusercar.tableCarTitles.length];
		for (int i = 0; i < tradingcords.size(); i++) {
			tblCarData[i][0] = tradingcords.get(i).getTradingsell();
			tblCarData[i][1] = tradingcords.get(i).getTradingbuy();
			tblCarData[i][2] = tradingcords.get(i).getTradingprice();
			tblCarData[i][3] = tradingcords.get(i).getTradingdata();
		}
		tabCarModel.setDataVector(tblCarData, tblCarTitle);
		dataTableCar.validate();
		dataTableCar.repaint();
	}

	public FrmBuySellMessage() throws BaseException {
		super();
		this.setTitle("买车");
		
		
		this.reloadCarTable();
		this.getContentPane().add(new JScrollPane(this.dataTableCar), BorderLayout.CENTER);
		this.setSize(1200, 800);

		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);
		this.validate();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmUser dlg = new FrmUser();
				dlg.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
