package stocker;


public class Trade {
	private String name; //股票名称
	private String code;//股票代码
	private String date;//股票日期
	private String trade_stytle;//交易类型
	private double price;//成交价格
	private int   num;//交易数量
	private String place;//交易所
	

	public Trade(){}
	public Trade(String name,String code,String date,String style,double price,int num,String place){
		this.name = name;
		this.code = code;
		this.date = date;
		this.trade_stytle = style;
		this.price = price;
		this.num = num;
		this.place = place;
	}
 	public  void set_name(String name){
		this.name = name;
	}
	public void set_code(String code) {
		this.code = code;
	}
	public void set_date(String date){
		this.date = date;
	}
	public void set_trade_stytle(String trade_stytle){
		this.trade_stytle = trade_stytle;
	}
	public void set_price(double price){
		this.price = price;
	}
	
	public void set_num(int num){
		this.num = num;
	}
	public void set_place(String place){
		this.place = place;
	}
	public String get_place(){
		return this.place;
	}
	public String get_name(){
		return this.name;
	}
	public String get_code(){
		return this.code; 
	}
	public String get_date(){
		return this.date;
	}
	public String get_trade_stytle(){
		return this.trade_stytle;
	}
	public double get_price(){
		return this.price;
	}
	public int get_num(){
		return this.num;
		
	}
}
