package stocker;

import java.util.ArrayList;
import java.util.List;

public class HistoryStockown {
	
	public String name;//股票名称
	public String code;//股票代码
	public String place;//交易所
	
	public List<Integer> number = new ArrayList<Integer>();//股票数量
	
	public List<String> date = new ArrayList<String>();//日期
	
	public HistoryStockown(String name,String code, String place){
		this.name = name;
		this.code = code;
		this.place = place;
	}
	public void addnumber_date(int num, String date){
		
		if(this.date.isEmpty()||!this.date.get(this.date.size()-1).equals(date)){
			this.date.add(date);
			this.number.add(num);
		}
		
		else{
			this.number.set(this.number.size()-1, this.number.get(this.number.size()-1)+num);
		}
	}
}
