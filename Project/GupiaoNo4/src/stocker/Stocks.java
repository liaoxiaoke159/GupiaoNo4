package stocker;

public class Stocks {
	private String code;
	private String place;
	private String name;
	private double price;//��ǰ��
	private int num;//������
	private double sumprice;//������ֵ
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
