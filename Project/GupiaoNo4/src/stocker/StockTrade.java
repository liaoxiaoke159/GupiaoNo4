package stocker;

import java.util.ArrayList;
import java.util.List;

public class StockTrade {
	
	public String name;//股票名称
	public String code;//股票代码
	public double costprice;//成本价（平均）
	public int num_buy;//买入股票数量（累计）
	public int num_sell;//卖出股票数量（累计）
	public double sumprice;//卖出去的总额
	public List<String> date = new ArrayList<String>();//日期
	public List<Double> rate=new ArrayList<Double>();//收益率
	
	
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
		System.out.println("日期："+this.date.get(this.date.size()-1)+
				" 名称:"+this.name+" 代码："+this.code+" 售额："+this.sumprice
				+" 成本:"+this.costprice+" 买数："+this.num_buy+" 卖数:"+this.num_sell+" 盈利率:"+rate);
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
