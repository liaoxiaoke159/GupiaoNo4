package stocker;

import java.io.FileInputStream;
import java.io.IOException;


import java.io.InputStream;
import java.util.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import stock.homepage;

public class Stockown {

	public static List<Stocks> stockslist=new ArrayList<Stocks>(); 
	
	public  Stockown(){
		return;
	}

	public  void addStock(Trade trade) {
		// TODO Auto-generated method stub

		for(int i=0;i<stockslist.size();i++){
			String codestr = stockslist.get(i).getSocketcode();
			String placestr = stockslist.get(i).getplace();
			double costpricestr = stockslist.get(i).getcostprice();
			if(codestr.equals(trade.get_code())
					&&placestr.equals(trade.get_place())
					&&costpricestr==trade.get_price()) {
				
				
				stockslist.get(i).setNum(stockslist.get(i).getNum()+trade.get_num());
				
				if(stockslist.get(i).getNum()==0){
					stockslist.remove(stockslist.get(i));
				}
				
				return;
			}
			
		}
		Stocks stocks = new Stocks(trade.get_name(), trade.get_code(),
				Integer.toString(trade.get_num()));// 股票名称 代码 买入量 

		stocks.setplace(trade.get_place());//交易所
		stocks.setcostprice(trade.get_price());//成本价
		
		this.stockslist.add(stocks);
	}

	


	public static List<Stocks> stockslist_initial(String path_file)
			throws BiffException, IOException{
		// TODO Auto-generated method stub
		InputStream is = new FileInputStream(path_file);
		 Workbook rwb = Workbook.getWorkbook(is);  
		 rwb.getNumberOfSheets();  
	     Sheet oFirstSheet = rwb.getSheet(0);
	     int rows = oFirstSheet.getRows();
	     

	     for(int i=0;i<rows;i++){
	    	 String name = oFirstSheet.getCell(0, i).getContents();
	    	 String code = oFirstSheet.getCell(1, i).getContents();
	    	 String num = oFirstSheet.getCell(5, i).getContents();
	    	 if(num.equals("0")){continue;}
	    	 String place = oFirstSheet.getCell(2, i).getContents();
	    	 String costprce = oFirstSheet.getCell(7, i).getContents();
	    	 Stocks temp=new Stocks(name,code,num);
	    	 temp.setcostprice(Double.parseDouble(costprce));
	    	 temp.setplace(place);
	    	 stockslist.add(temp);
	     }
	     return stockslist;
	     
	}
	
}

