package datadealer;


public class Trade {
	private String name; //��Ʊ����
	private String code;//��Ʊ����
	private String date;//��Ʊ����
	private String trade_stytle;//��������
	private double price;//�ɽ��۸�
	private int   num;//��������
	
	public Trade(){
		
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
