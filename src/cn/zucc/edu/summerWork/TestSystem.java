package cn.zucc.edu.summerWork;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TestSystem extends MouseAdapter {
	JFrame f = null;
	JLabel label = null;
	JLabel label1 = null;
	JLabel label2 = null;
	Rectangle rec1 = null;
	Rectangle rec2 = null;

	public TestSystem() {
		f = new JFrame("ColorChooser Example");
		Container contentPane = f.getContentPane();
		contentPane.addMouseListener(this);

		label = new JLabel(" ", JLabel.CENTER);
		label.setPreferredSize(new Dimension(300, 20));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));

		label1 = new JLabel("左Label", JLabel.CENTER);
		label1.setBackground(Color.red);
		label1.setForeground(Color.black);
		label1.setOpaque(true);
		label1.setBounds(0, 0, 150, 150);
		panel.add(label1);

		label2 = new JLabel("右Label", JLabel.CENTER);
		label2.setBackground(Color.green);
		label2.setForeground(Color.black);
		label2.setOpaque(true);
		label2.setBounds(150, 0, 150, 150);
		panel.add(label2);

		rec1 = label1.getBounds();
		rec2 = label2.getBounds();

		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(label, BorderLayout.SOUTH);

		f.setSize(new Dimension(300, 150));
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] arg) {
		SwingUtil.setLookAndFeel();
		new TestSystem();
	}

	// 实现MouseAdapter中的mousePressed()与mouseClicked()方法.当按下鼠标时,就能知道鼠标光标目前的位置.当连续键击鼠标
	// 两次时,若光标所在位置在label中,就会出现颜色选择对话框,用户可选择任一颜色更改label的颜色.
	public void mousePressed(MouseEvent e) {
		label.setText("目前鼠标坐标（X,Y）为：（" + e.getX() + "," + e.getY() + ")");
	}

	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();

		if (e.getClickCount() == 2) {
			if (rec1.contains(point)) { /*
										 * 利用JColorChooser的showDialog()静态方法输出颜色选择对话框
										 * ,showDialog()中的3个参数依次是:
										 * 对话框的父组件,颜色选择对话框标题
										 * ,与对话框默认颜色.当用户选择完颜色之后,按下"OK"按钮则返回
										 * Color对象,若按下"Cancel"按钮则返回null值.
										 */
				Color color = JColorChooser.showDialog(f,
						"Change label1 Color", Color.white);
				if (color != null) // 若为null值表示用户按下Cancel按钮
					label1.setBackground(color);
			}
			if (rec2.contains(point)) {
				Color color = JColorChooser.showDialog(f,
						"Change label2 Color", Color.yellow);
				if (color != null) // 若为null值表示用户按下Cancel按钮
					label2.setBackground(color);
			}
		}
	}
}

class SwingUtil {
	public static final void setLookAndFeel() {
	}
}
		
