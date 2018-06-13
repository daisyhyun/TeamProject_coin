package PanelInfo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import Codes.CoinInfo;
import Codes.Constants;
import Codes.ParseHtml;
import Codes.mainframe;

public class CoinPriceSummary extends JPanel{
	ParseHtml parsed = new ParseHtml();
	private CoinInfo[] coins;
	private String[] ColumnName = {"Index","Coin Name", "Coin Cap", "Current Value", "Volume"};
	private Object[][] CoinInformation = new Object[Constants.GET_COIN_NUM][5];
	public CoinPriceSummary(CoinInfo[] coins, ParseHtml parsed) {
		this.parsed = parsed;
		this.setLayout(null);
		this.coins = coins;
		JButton Return = new JButton("¢¸");
		Return.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 10));
		Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mainframe.mf.setVisible(true);
				mainframe.frame.repaint();
			}
		});
		Return.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		Return.setBounds(0, 0, 55, 55);
		add(Return);
		
		JButton renew = new JButton("RESET");
		renew.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 10));
		renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCoinInfoNotHistory();
				repaint();
			}
		});
		renew.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		renew.setBounds(1100, 0, 100, 55);
		add(renew);
		
		for(int i=0; i<Constants.GET_COIN_NUM;i++) {
			CoinInformation[i][0] = coins[i].getCoinIndex();
			CoinInformation[i][1] = coins[i].getCoinName();
			CoinInformation[i][2] = String.format("$%.0f", coins[i].getCoinCap());
			CoinInformation[i][3] = "$"+coins[i].getCurrent();
			CoinInformation[i][4] = coins[i].getCoinVolume();
		}
		JTable coin = new JTable(CoinInformation, ColumnName);
		JScrollPane coinlist = new JScrollPane(coin, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		coin.setLocation(100,100);
		coin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		coin.getColumnModel().getColumn(0).setPreferredWidth(40);
		coin.getColumnModel().getColumn(1).setPreferredWidth(300);
		coin.getColumnModel().getColumn(2).setPreferredWidth(300);
		coin.getColumnModel().getColumn(3).setPreferredWidth(329);
		coin.getColumnModel().getColumn(4).setPreferredWidth(329);
		coin.getTableHeader().setBackground(new Color(50,200,50,255));
		coin.getTableHeader().setReorderingAllowed(false);
		coin.setRowHeight(30);
		coin.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		coin.setFillsViewportHeight(true);
		coinlist.setBounds(100,100,1300,600);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		coin.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		coin.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		centerRenderer.setHorizontalAlignment( JLabel.LEFT );
		coin.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		coin.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		coin.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		
		//coinlist.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		//coinlist.setpre
		this.add(coinlist);
	}
	public void setCoinInfoNotHistory() {
		this.coins = parsed.setCoinNotHistory();
	}
}
