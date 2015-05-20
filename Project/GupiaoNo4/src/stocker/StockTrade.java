package stocker;

import java.util.ArrayList;
import java.util.List;

public class StockTrade {
	
	public String name;//��Ʊ����
	public String code;//��Ʊ����
	public double costprice;//�ɱ��ۣ�ƽ����
	public int num_buy;//�����Ʊ�������ۼƣ�
	public int num_sell;//������Ʊ�������ۼƣ�
	public double sumprice;//����ȥ���ܶ�
	public List<String> date = new ArrayList<String>();//����
	public List<Double> rate=new ArrayList<Double>();//������
	
	
	public StockTrade(String name,String code,double costprice,int num_buy,String date){
		this.name = name;
		this.code = code;
		this.costprice = costprice;
		this.num_buy = num_buy;
		this.date.add(date);
		
	}
	public void adddate(String s){
		this.date.add(s);
	}
	public void addrate(){
		
		double rate=(sumprice+costprice*(num_buy-num_sell))/(costprice*num_buy) -1;
		System.out.println("���ڣ�"+this.date.get(this.date.size()-1)+
				" ����:"+this.name+" ���룺"+this.code+" �۶"+this.sumprice
				+" �ɱ�:"+this.costprice+" ������"+this.num_buy+" ����:"+this.num_sell+" ӯ����:"+rate);
		this.rate.add(rate);
	}
	
	public String getname(){
		return this.name;
	}
	public String getcode(){
		return this.code;
	}
	public List<Double> getrate(){
		return rate;
	}
	public void setcostprice(double price){
		this.costprice = price;
	}
	public void setnum_buy(int num){
		this.num_buy =this.num_buy+num;
	}
	public void setsell(int num,double price){
		
		if(num_sell==0){
			this.num_sell=num;
			this.sumprice = num*price;
			
		}else{
		this.num_sell = this.num_sell+num;
		this.sumprice = this.sumprice+num*price;
		}
	}
	public void setname(String name){
		this.name = name;
	}
	public void setcode(String code){
		this.code = code;
	}

}
