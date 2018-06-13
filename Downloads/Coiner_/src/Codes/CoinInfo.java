package Codes;

public class CoinInfo {
	private String CoinName;		//코인 이름
	private int Coin_index;			//코인 순위
	private String Coin_volume;		//거래량
	private double Coin_cap;		//코인 시가 총액
	private double[][] CoinHistory;	//코인 최근 x일 간의 시가/종가 기록
	private double CurrentValue;	//현가
	private int max_historysize;
	
	public int getsize() {
		return this.max_historysize;
	}
	
	public void setsize(int max) {
		this.max_historysize = max;
	}
	
	public double getCurrent() {
		return this.CurrentValue;
	}
	
	public void setCurrent(double curr) {
		this.CurrentValue = curr;
	}
	
	public void setCoinVolume(String vol) {
		this.Coin_volume = vol;
	}
	
	public String getCoinVolume() {
		return this.Coin_volume;
	}
	public void setCoinName(String name) {
		this.CoinName = name;
	}
	
	public String getCoinName() {
		return this.CoinName;
	}
	
	public void setCoinIndex(int index) {
		this.Coin_index = index;
	}
	
	public int getCoinIndex() {
		return this.Coin_index;
	}
	
	public void setCoinCap(double cap) {
		this.Coin_cap = cap;
	}
	
	public double getCoinCap() {
		return this.Coin_cap;
	}
	
	public void setCoinHistory(double[][] data) {
		this.CoinHistory = data;
	}
	
	public double[][] getCoinHistory() {
		return this.CoinHistory;
	}
}
