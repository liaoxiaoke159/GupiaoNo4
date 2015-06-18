package stocker;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import stock.homepage;

public class PlaceOder {
	
	public int tabitemindex;
	public String name;
	public String code;
	public String style;
	public double price;
	public int num;
	public String place;
	public String date;
	
	public PlaceOder(int index, String name, String code, String style, 
			String price, String num, String place, String date){
		this.tabitemindex = index;
		this.name = name;
		this.code = code;
		this.style = style;
		this.price = Double.parseDouble(price);
		this.num = Integer.parseInt(num);
		this.place = place;
		this.date = date;
	}


	public boolean update_trade() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
				//买入后创建一个交易类
				Trade trade_buy = new Trade();
				trade_buy = newtrade(name,code,style,price,num,place,date);
				
				// 买入后更新交易记录excel表
				DataBuilder db = new DataBuilder();
				
			try {
				if(!db.addtrade(homepage.path_trade.get(tabitemindex), trade_buy))
							return false;
			} catch (WriteException | BiffException | IOException e) {
				
				e.printStackTrace();
				return false;
			}
					
				
				
				
				DataDealer datadealer = new DataDealer(homepage.path_trade.get(tabitemindex),tabitemindex);
				datadealer.creat();

				return true;
			
		
	}
	

	
	public Trade newtrade(String name, String code, String style, 
			double price, int num, String place, String date) {
		// TODO Auto-generated method stub
		
		Trade trade = new Trade();
		trade.set_name(name);
		trade.set_code(code);
		
		trade.set_date(date);
		trade.set_trade_stytle(style);	
		trade.set_price(price);
		
		if(style.equals("卖出")||style.equals("补仓"))
			 trade.set_num(-num);
		else trade.set_num(num);
		
		trade.set_place(place);
		
		return trade;
	}



}
