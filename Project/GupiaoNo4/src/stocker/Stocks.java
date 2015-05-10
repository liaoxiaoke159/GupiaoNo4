package stocker;

public class Stocks {
	private String code;
	private String place;
	private String name;
	private double price;//当前价
	private int num;//持有量
	private double sumprice;//持有市值
	public String getcode(){
		return this.code;
	}
	public String getname(){
		return this.name;
	}
	public Stocks(String code,String name,String place,int num){
		this.code=code;
		this.place=place;
		this.name=name;
		this.num=num;
	}

}
