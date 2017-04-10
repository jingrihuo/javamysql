package cn.zucc.edu.summerWork.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.JToolBar;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import cn.zucc.edu.summerWork.SummerWorkUtil;
import cn.zucc.edu.summerWork.model.Beanuser;
import cn.zucc.edu.summerWork.model.Beanusercar;
import cn.zucc.edu.summerWork.util.BaseException;

public class FrmSellcarChoose extends JFrame implements ActionListener {
	private JToolBar bar = new JToolBar();
	
	private JButton ButtonSellCar =  new JButton("我要卖车");
	private JButton ButtonModifySellCar =  new JButton("查看我卖的车");
	
	public FrmSellcarChoose() {
		super();
		this.setTitle("用户" + Beanuser.LoginUser.getUsername() + "你好");
		bar.setLayout(new GridLayout(2, 1));
		Font font =  new Font("宋体", Font.BOLD, 24);
		ButtonSellCar.setFont(font);
		ButtonModifySellCar.setFont(font);
		bar.add(this.ButtonSellCar);
		bar.add(this.ButtonModifySellCar);
		this.getContentPane().add(bar, BorderLayout.CENTER);
		this.setSize(480, 360);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.ButtonModifySellCar.addActionListener(this);
		this.ButtonSellCar.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				FrmUser dlg = new FrmUser();
				dlg.setVisible(true);
			}
		});	
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ButtonSellCar) {
			try {
				FrmSellCar dlg = new FrmSellCar();
				dlg.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}	
		}else if (e.getSource() == this.ButtonModifySellCar) {
			try {
				FrmShelvesCar dlg = new FrmShelvesCar();
				dlg.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}	
		}
	}
}