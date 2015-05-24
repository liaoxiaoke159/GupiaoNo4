package stocker;


public class Stocks {
	private String name;//股票名称
	private String code;
	private String place;
	private int num;//持有量
	private double costprice ;//成本
	public Stocks(String name,String code,String num)
	{
		this.name=name;
		this.code=code;
		this.num=Integer.parseInt(num);
	}

	public String getSocketname(){
		return this.name;
	}
	public String getSocketcode(){
		return this.code;
	}
	public void setNum(int x){
		this.num=x;
	}
	public int getNum(){
		return this.num;
	}
	public void setcostprice(double x){
		this.costprice=x;
	}
	public double getcostprice(){
		return this.costprice;
	}
	public void setplace(String place1){
		this.place = place1;
	}
	public String getplace(){
		return this.place;
	}


}
