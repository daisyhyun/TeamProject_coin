package Codes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import PanelInfo.CoinPriceSummary;
import PanelInfo.ShowGraphs;
import Tools.ResourcesManage;

public class mainframe extends JPanel{
	public static JFrame frame = new JFrame("Black Cow");
	ResourcesManage ImgManage = new ResourcesManage();
	CoinInfo[] coins = new CoinInfo[Constants.GET_COIN_NUM];
	ParseHtml parsed = new ParseHtml();
	Color BG = new Color(255,255,255,150);
	public static mainframe mf = new mainframe();
	public static ShowGraphs sg;
	public static CoinPriceSummary cps;
	public static boolean switch_tmp = true;
	
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		gd.drawImage(ImgManage.CallImage("MainBackGround.jpg",1520,800), null, 0, 0);
		/*new Thread(new Runnable() {

			@Override
			public void run() {
				JOptionPane.showOptionDialog(null, "Parsing Data from server...", "Info",
					JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
			}

		}).start();*/
		
		if(switch_tmp) {
			setCoinInfo();
			this.switch_tmp = false;
		}
		setCoinInfoNotHistory();
		//MainMenu buttons
		this.setLayout(null);
		JButton price_summary = new JButton("Coin price summary");
		price_summary.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 25));
		price_summary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mf.setVisible(false);
				cps = new CoinPriceSummary(coins, parsed);
				cps.setVisible(true);
				frame.add(cps);
			}
		});
		JButton show_graph = new JButton("Show graphs");
		show_graph.setFont(new Font("∏º¿∫ ∞ÌµÒ",  Font.BOLD, 25));
		show_graph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mf.setVisible(false);
				sg = new ShowGraphs(coins);
				sg.setVisible(true);
				frame.add(sg);
			}
		});
		JButton purchase_coin = new JButton("Purchase coins");
		purchase_coin.setFont(new Font("∏º¿∫ ∞ÌµÒ",  Font.BOLD, 25));
		purchase_coin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//¥©∏£∏È ∞°∞‘ «œ∏Èµ 
				System.exit(0);
			}
		});
		JButton exit_program = new JButton("Exit program");
		exit_program.setFont(new Font("∏º¿∫ ∞ÌµÒ",  Font.BOLD, 25));
		exit_program.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		price_summary.setBounds(100, 300, 300, 60);
		show_graph.setBounds(100, 400, 300, 60);
		purchase_coin.setBounds(100, 500, 300, 60);
		exit_program.setBounds(100, 600, 300, 60);
		add(price_summary);
		add(show_graph);
		add(purchase_coin);
		add(exit_program);
		
		g.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 100));
		g.setColor(new Color(235,235,100,255));
		g.drawString("Black Cow", 100, 130);
	}
	public static void main(String[] args) {
		frame.setSize(Constants.FRAME_SIZE_X,Constants.FRAME_SIZE_Y);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//√¢ ≤Ù∏È ≈ÕπÃ≥Œ ¡æ∑·
		
		frame.add(mf);
		mf.setVisible(true);
		mf.setSize(Constants.FRAME_SIZE_X,Constants.FRAME_SIZE_Y);
		mf.repaint();
	}
	
	public CoinInfo[] getCoinInfo() {
		return this.coins;
	}
	
	public void setCoinInfoNotHistory() {
		this.coins = parsed.setCoinNotHistory();
	}
	
	public void setCoinInfo() {
		  this.coins = parsed.setCoin();
	}
}
