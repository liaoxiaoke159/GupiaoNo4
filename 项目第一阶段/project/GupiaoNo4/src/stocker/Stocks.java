package stocker;


public class Stocks {
	private String name;//��Ʊ����
	private String code;
	private String place;
	private int num;//������
	private double costprice ;//�ɱ�
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
