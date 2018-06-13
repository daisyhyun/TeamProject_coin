package PanelInfo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Codes.CoinInfo;
import Codes.Constants;
import Codes.mainframe;
import Tools.ResourcesManage;

public class ShowGraphs extends JPanel implements ActionListener{
	private final int graph_x = 290, graph_y = 70, graph_w = 1130, graph_h = 600; 	//»Ú πŸ≈¡
	private final int real_x = 370, real_y = 100, real_xt = 1350, real_yt = 630;		//Ω«¡¶ ±◊∑°«¡
	
	private CoinInfo[] coins;
	private CoinInfo currcoin;
	private ResourcesManage ImgManage = new ResourcesManage();
	private int mousex, mousey;
	SimpleDateFormat DefaultFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat StatusFormat = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss"); 
	Date date = new Date();
	private boolean switchs = false;
	
	public void paintComponent(Graphics g) {
		Graphics2D gd = (Graphics2D) g;
		gd.drawImage(ImgManage.CallImage("MainBackGround.jpg",1520,800), null, 0, 0);
		this.setLayout(null);
		
		g.setColor(new Color(255,255,255,100));
		g.fillRect(70, 50, Constants.FRAME_SIZE_X-105,
				Constants.FRAME_SIZE_Y-140);
		
		JButton Return = new JButton("¢∏");
		Return.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 10));
		Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mainframe.mf.setVisible(true);
				mainframe.frame.repaint();
			}
		});
		Return.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 15));
		Return.setBounds(0, 0, 55, 55);
		add(Return);
		
		JButton Up = new JButton("-");
		Up.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 10));
		Up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Constants.COIN_HISTORY_SIZE * 1.2<=300)
					Constants.COIN_HISTORY_SIZE *= 1.2;
			}
		});
		Up.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 15));
		Up.setBounds(1425, 275, 50, 70);
		add(Up);
		
		JButton Down = new JButton("+");
		Down.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 10));
		Down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Constants.COIN_HISTORY_SIZE * 0.8>=13)
					Constants.COIN_HISTORY_SIZE *= 0.8;
			}
		});
		Down.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 15));
		Down.setBounds(1425, 375, 50, 70);
		add(Down);
		
		g.setColor(Color.WHITE);
		g.fillRect(graph_x, graph_y, graph_w, graph_h);
		g.setColor(new Color(80,80,80,250));
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.drawLine(real_x, real_yt, real_x, real_y);		//x√‡
		g.drawLine(real_x, real_yt, real_xt, real_yt);		//y√‡
		
		g.setColor(new Color(200,200,200,100));
		g.fillRect(real_x, real_y, real_xt-real_x, real_yt-real_y);
		
		g.setColor(Color.WHITE);
		//∫∏¡∂º± ±◊∏Æ±‚
		for(int i=Constants.COIN_HISTORY_LIMIT-Constants.COIN_HISTORY_SIZE; i<Constants.COIN_HISTORY_LIMIT; i++) {
			if((Constants.COIN_HISTORY_LIMIT-i-1)%(Constants.COIN_HISTORY_SIZE/10)==0) {
				date = new Date(new Date().getTime() - Constants.DAY*(Constants.COIN_HISTORY_LIMIT-i-1));
				String date_str = DefaultFormat.format(date);
				g.setColor(Color.BLACK);
				g.drawString(date_str, (int)convertIndex(i)-30, real_yt+25);
				g.setColor(new Color(60,60,60,200));
				((Graphics2D) g).setStroke(new BasicStroke(3));
				g.drawLine((int)convertIndex(i), real_yt-4, (int)convertIndex(i), real_yt+4);
			}
			else {
				g.setColor(new Color(130,130,130,180));
				((Graphics2D) g).setStroke(new BasicStroke(2));
				g.drawLine((int)convertIndex(i), real_yt-2, (int)convertIndex(i), real_yt+2);
			}
		}

		//πˆ∆∞
		JButton[] Coin_Tap = new JButton[Constants.GET_COIN_NUM];
		for(int i=0; i<Constants.GET_COIN_NUM;i++){
			Coin_Tap[i] = new JButton(coins[i].getCoinName());
			Coin_Tap[i].addActionListener(this);
			Coin_Tap[i].setBounds(100,70+40*i,150,35);
			add(Coin_Tap[i]);
		}
		if(switchs) {
			double min_val = 10e20, max_val = 0;
			for(int j=Constants.COIN_HISTORY_LIMIT-Constants.COIN_HISTORY_SIZE; j<Constants.COIN_HISTORY_LIMIT;j++) {
				if(currcoin.getCoinHistory()[j][1]>max_val)
					max_val = currcoin.getCoinHistory()[j][1];
				if(currcoin.getCoinHistory()[j][1]<min_val)
					min_val = currcoin.getCoinHistory()[j][1];
			}
			for(int j=Constants.COIN_HISTORY_LIMIT-Constants.COIN_HISTORY_SIZE; j<Constants.COIN_HISTORY_LIMIT;j++) {
				if(currcoin.getCoinHistory()[j][2]>max_val)
					max_val = currcoin.getCoinHistory()[j][2];
				if(currcoin.getCoinHistory()[j][2]<min_val)
					min_val = currcoin.getCoinHistory()[j][2];
			}
			for(int j=Constants.COIN_HISTORY_LIMIT-Constants.COIN_HISTORY_SIZE; j<Constants.COIN_HISTORY_LIMIT-1;j++) {
				((Graphics2D) g).setStroke(new BasicStroke(1));
				g.setColor(Color.BLACK);
				//System.out.println(convertIndex(j)+","+convertLocation(max_val, min_val, currcoin.getCoinHistory()[j]));
				/*g.drawLine(convertIndex(j), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][1]),
						convertIndex(j+1), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j+1][1]));
				g.drawLine(convertIndex(j), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][2]),
						convertIndex(j+1), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j+1][2]));
				g.drawLine(convertIndex(j), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][3]),
						convertIndex(j+1), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j+1][3]));
				g.drawLine(convertIndex(j), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][0]),
						convertIndex(j+1), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j+1][0]));*/
				double margin_ratio = 0.35; double gap = (real_xt-real_x)/(Constants.COIN_HISTORY_SIZE+1);
				Graphics2D g2 = (Graphics2D) g; Shape l;
				
				if(currcoin.getCoinHistory()[j][0]>currcoin.getCoinHistory()[j][3]) {		//¿Ω∫¿
					g.setColor(Color.BLUE);

					l = new Rectangle2D.Double(convertIndex(j)-gap*margin_ratio, convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][0])
							, 2*gap*margin_ratio, convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][3])-
							convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][0]));
					g2.fill(l);
					l = new Line2D.Double(convertIndex(j), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][1]), 
							convertIndex(j), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][2]));
					g2.draw(l);
				}else {
					g.setColor(Color.RED);
					l = new Rectangle2D.Double(convertIndex(j)-gap*margin_ratio, convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][3])
							, 2*gap*margin_ratio, convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][0])-
							convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][3]));
					g2.fill(l);
					l = new Line2D.Double(convertIndex(j), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][1]), 
							convertIndex(j), convertLocation(max_val, min_val, currcoin.getCoinHistory()[j][2]));
					g2.draw(l);
				}
			}
			g.setColor(Color.BLACK);
			((Graphics2D) g).setStroke(new BasicStroke(2));
			int gap = (real_yt-real_y-2*Constants.VERTICAL_MARGIN)/(Constants.VERTICAL_BORDER_NUM-1);
			int min_length_border = 1000;
			String valueboarder;
			for(int i=0; i<8; i++) {
				valueboarder = ""+((max_val-min_val)/(Constants.VERTICAL_BORDER_NUM-1)*i+min_val);
				if(valueboarder.length()<min_length_border)
					min_length_border = valueboarder.length();
			}
			for(int i=0; i<8; i++) {
				g.setColor(Color.BLACK);
				valueboarder = ""+((max_val-min_val)/(Constants.VERTICAL_BORDER_NUM-1)*i+min_val);
				if(valueboarder.length()>min_length_border)
					valueboarder = valueboarder.substring(0,min_length_border);
				g.drawString(valueboarder, real_x-5-valueboarder.length()*7, real_yt - Constants.VERTICAL_MARGIN - i*gap);
				g.setColor(new Color(100,100,100,70));
				g.drawLine(real_x, real_y + Constants.VERTICAL_MARGIN + i*gap,
						real_xt, real_y + Constants.VERTICAL_MARGIN + i*gap);
			}
			if(mousex<real_xt&&mousex>real_x&&mousey<real_yt&&mousey>real_y) {
				drawline(g);
				g.setColor(Color.RED);
				double ratio_mousey = (double)(real_yt-mousey)/(double)(real_yt-real_y);
				valueboarder = ""+((max_val-min_val)*ratio_mousey+min_val);
				if(valueboarder.length()>min_length_border)
					valueboarder = valueboarder.substring(0,min_length_border);
				//System.out.println(valueboarder);
				g.drawString(valueboarder, real_xt+5, mousey);
				drawcursor(g,real_xt,mousey);
				drawcursor(g,mousex,mousey);
				drawcursor(g,mousex,real_yt);
				drawStatus(g);
			}
		}
		//drawcursor(g,mousex,mousey);
	}
	private double convertIndex(int index) {	//0∫Œ≈Õ~
		double gap = (double)(real_xt-real_x)/(double)(Constants.COIN_HISTORY_SIZE+1);
		return (real_x+gap*(double)(index-Constants.COIN_HISTORY_LIMIT+Constants.COIN_HISTORY_SIZE+1));
	}
	private int convertLocation(double max, double min, double value) {
		double ratio = (value-min)/(max-min);
		return (int)(real_yt-Constants.VERTICAL_MARGIN - (real_yt-real_y-2*Constants.VERTICAL_MARGIN)*ratio);
	}
	public ShowGraphs(CoinInfo[] coins) {
		this.coins = coins;
		this.setSize(Constants.FRAME_SIZE_X,Constants.FRAME_SIZE_Y);
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				mousex = e.getX();
				mousey = e.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				mousex = e.getX();
				mousey = e.getY();
			}
		});
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long renderms = System.currentTimeMillis();
				for (;;)
					try {
						renderms = System.currentTimeMillis();
						Thread.sleep(5);
						repaint();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						//System.out.println(renderms-System.currentTimeMillis()+"");
					}
			}
		}).start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		for(int i=0; i<Constants.GET_COIN_NUM; i++)
			if(coins[i].getCoinName()==e.getActionCommand())
			{
				currcoin = coins[i];
				switchs = true;
				break;
			}
	}
	public void drawline(Graphics g) {
		g.setColor(Color.BLACK);
		((Graphics2D) g).setStroke(new BasicStroke(1));
		g.drawLine(mousex, real_y, mousex, real_yt);
		g.drawLine(real_x, mousey, real_xt, mousey);
	}
	public void drawcursor(Graphics g, int x, int y) {
		g.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 10));
		g.setColor(Color.BLACK);
		g.fillOval(x - 2, y - 2, 4, 4);
		//g.drawString(x + "," + y, x + 3, y - 3);
	}
	public void drawStatus(Graphics g) {
		double start = (double)(real_xt-real_x)/(Constants.COIN_HISTORY_SIZE+1)+real_x;
		double end = real_xt - (double)(real_xt-real_x)/(Constants.COIN_HISTORY_SIZE+2);
		double gaps = (double)(real_xt-real_x)/(Constants.COIN_HISTORY_SIZE+1);
		double gap = (end-mousex)/(end-start);
		Date dates = new Date(new Date().getTime() - (long)((Constants.COIN_HISTORY_SIZE-1)*Constants.DAY*gap));
		String str = StatusFormat.format(dates);
		str = str.substring(1,str.length());
		g.drawString(str, mousex+10, mousey-10);
	}
}
