package Codes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseHtml {
    Document ParsedFile, ParsedFile_history;
    Elements ParsedElement, ParsedElement_history;
    
	SimpleDateFormat StatusFormat = new SimpleDateFormat("yyyyyMMdd"); 
    
    String[] CoinName = new String[Constants.GET_COIN_NUM];
    String[] CoinName_tmp = new String[Constants.GET_COIN_NUM];
    int[] CoinIndex = new int[Constants.GET_COIN_NUM];
    double[] CoinCap = new double[Constants.GET_COIN_NUM];
    CoinInfo[] coins = new CoinInfo[Constants.GET_COIN_NUM];
    double[][][] History = new double[Constants.GET_COIN_NUM][Constants.COIN_HISTORY_LIMIT][4];
    public CoinInfo[] setCoinNotHistory() {
		String NameArray;
		String[] SplitName;
		int CoinCapIndexSplit = 0;
		
		renew();
		
	    for (int i=0;i<Constants.GET_COIN_NUM;i++) {
           NameArray = ParsedElement.get(i).text();
           SplitName = NameArray.split(" ");
           CoinIndex[i] = Integer.parseInt(SplitName[0]);
           CoinName[i] = "";	CoinName_tmp[i] = "";
           coins[i] = new CoinInfo();
           
           for(int j=2; !isNumber(SplitName[j]);j++) {
        	   if(j!=2) {
        		   CoinName[i]+=" ";
        		   CoinName_tmp[i]+="-";
        	   }
        	   CoinName[i] += SplitName[j];
        	   CoinName_tmp[i] += SplitName[j];
        	   CoinCapIndexSplit = j+1;
           }
           CoinCap[i] = Double.parseDouble(SplitName[CoinCapIndexSplit]);
           //System.out.println(Double.parseDouble(SplitName[CoinCapIndexSplit+1].substring(1,SplitName[CoinCapIndexSplit+1].length())));
           //setting terminated
           coins[i].setCoinCap(CoinCap[i]);
           coins[i].setCoinIndex(i+1);
           coins[i].setCoinName(CoinName[i]);
           coins[i].setCoinHistory(History[i]);
           coins[i].setCurrent(Double.parseDouble(SplitName[CoinCapIndexSplit+1].substring(1,SplitName[CoinCapIndexSplit+1].length())));
           coins[i].setCoinVolume(SplitName[CoinCapIndexSplit+2]);
	    }
	    //this.printNames();
	    return coins;
    }
	public CoinInfo[] setCoin() {		//모두 초기화
		String NameArray;
		String[] SplitName;
		int CoinCapIndexSplit = 0;
		
		renew();
		
	    for (int i=0;i<Constants.GET_COIN_NUM;i++) {
           NameArray = ParsedElement.get(i).text();
           SplitName = NameArray.split(" ");
           CoinIndex[i] = Integer.parseInt(SplitName[0]);
           CoinName[i] = "";	CoinName_tmp[i] = "";
           coins[i] = new CoinInfo();
           
           for(int j=2; !isNumber(SplitName[j]);j++) {
        	   if(j!=2) {
        		   CoinName[i]+=" ";
        		   CoinName_tmp[i]+="-";
        	   }
        	   CoinName[i] += SplitName[j];
        	   CoinName_tmp[i] += SplitName[j];
        	   CoinCapIndexSplit = j+1;
           }
           CoinCap[i] = Double.parseDouble(SplitName[CoinCapIndexSplit]);
           History[i] = getHistory(CoinName_tmp[i]);
           //System.out.println(Double.parseDouble(SplitName[CoinCapIndexSplit+1].substring(1,SplitName[CoinCapIndexSplit+1].length())));
           //setting terminated
           coins[i].setCoinCap(CoinCap[i]);
           coins[i].setCoinIndex(i+1);
           coins[i].setCoinName(CoinName[i]);
           coins[i].setCoinHistory(History[i]);
           coins[i].setCurrent(Double.parseDouble(SplitName[CoinCapIndexSplit+1].substring(1,SplitName[CoinCapIndexSplit+1].length())));
           coins[i].setCoinVolume(SplitName[CoinCapIndexSplit+2]);
	    }
	    //this.printNames();
        System.out.println("HISTORY SET!");
	    return coins;
	}
	
	private void renew() {
		try {
			ParsedFile = Jsoup.connect("https://coinmarketcap.com").get();
	        ParsedElement = ParsedFile.select("table#currencies tbody tr");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private double[][] getHistory(String CoinName){
		double[][] CoinHistory = new double[Constants.COIN_HISTORY_LIMIT][4];
		String HistoryArray;
		String[] SplitHistory;
		Element ParsedHistory;
		Date today = new Date();
		Date old = new Date(new Date().getTime() - Constants.DAY*365 );
		String today_str = StatusFormat.format(today).substring(1);
		String old_str = StatusFormat.format(old).substring(1);
		try {
			ParsedFile_history = Jsoup.connect(("https://coinmarketcap.com/currencies/"+CoinName+
					"/historical-data/?start="+old_str+"&end="+today_str)).get();
			System.out.println("https://coinmarketcap.com/currencies/"+CoinName+
					"/historical-data/?start="+old_str+"&end="+today_str);
	        ParsedElement_history = ParsedFile_history.select("table.table tbody tr");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<Constants.COIN_HISTORY_LIMIT; i++) {
			ParsedHistory = ParsedElement_history.get(i);
			HistoryArray = ParsedHistory.text();
			SplitHistory = HistoryArray.split(" ");
			CoinHistory[Constants.COIN_HISTORY_LIMIT-1-i][0] = Double.parseDouble(SplitHistory[3]);
			CoinHistory[Constants.COIN_HISTORY_LIMIT-1-i][1] = Double.parseDouble(SplitHistory[4]);
			CoinHistory[Constants.COIN_HISTORY_LIMIT-1-i][2] = Double.parseDouble(SplitHistory[5]);
			CoinHistory[Constants.COIN_HISTORY_LIMIT-1-i][3] = Double.parseDouble(SplitHistory[6]);
		}
		return CoinHistory;
	}
	
	private static boolean isNumber(String str)  //해당 string이 숫자인지 판단해줌
	{  
	  try  
	  {  
	    double tmp = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	private void printNames() {
		for(int i=0; i<Constants.GET_COIN_NUM; i++) {
			System.out.print(CoinIndex[i]+" \t"+CoinName[i]+" \t");
			System.out.printf("%.0f $\n",CoinCap[i]);
			for(int j=0; j<Constants.COIN_HISTORY_LIMIT; j++)
				System.out.print(History[i][j] + " ");
			System.out.println();
		}
	}
}
