package PanelInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.prism.Graphics;

import Tools.ResourcesManage;
import Codes.mainframe;

public class PurchaseCoin extends JPanel{
	public PurchaseCoin(boolean check) {
		this.setLayout(null);
		JButton first_url = new  JButton("Binance");
		first_url.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		first_url.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
				String first ="https://www.binance.com/kr";
				java.awt.Desktop.getDesktop().browse(java.net.URI.create(first));
				}catch (Exception error){
					error.printStackTrace();
				}
			}
		});
		JButton second_url = new  JButton("Upbit");
		second_url.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		second_url.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
				String first ="https://upbit.com/home";
				java.awt.Desktop.getDesktop().browse(java.net.URI.create(first));
				}catch (Exception error){
					error.printStackTrace();
				}
			}
		});		
		JButton third_url = new  JButton("Bithumb");
		third_url.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		third_url.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
				String first ="https://www.bithumb.com/";
				java.awt.Desktop.getDesktop().browse(java.net.URI.create(first));
				}catch (Exception error){
					error.printStackTrace();
				}
			}
		});
		JButton Return = new JButton("¢¸");
		Return.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 10));
		Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mainframe.mf.setVisible(true);
				mainframe.frame.repaint();
			}
		});
		JLabel Big =new JLabel("If you want buy coins, Click the button");
		Big.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,50));
		JLabel first = new JLabel("Global Website");
		first.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		JLabel second = new JLabel("Korean Website");
		second.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		JLabel third = new JLabel("Korean Website");
		third.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		first.setBounds(700,300,400,60);
		second.setBounds(700,400,400,60);
		third.setBounds(700,500,400,60);
		Big.setBounds(100,150,1000,100);
		first_url.setBounds(200,300,300,60);
		second_url.setBounds(200,400,300,60);
		third_url.setBounds(200,500,300,60);
		Return.setBounds(0, 0, 100, 100);
		add(first_url);
		add(second_url);
		add(third_url);
		add(first);
		add(second);
		add(third);
		add(Big);
		add(Return);
		
	}
}
