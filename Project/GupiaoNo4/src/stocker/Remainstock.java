package stocker;

public class Remainstock {
	private String name;//股票名称
	private String code;//股票代码
	private String place;//交易所
	private int number;//买入剩余或者卖空有多的股票数量
	
	public  Remainstock(String name,String code,String place,int number){
		this.name = name;
		this.code=code;
		this.place = place;
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = this.number+number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	

}
