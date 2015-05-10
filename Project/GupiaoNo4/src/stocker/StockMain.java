package stocker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datadealer.DataBuilder;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import stock.homepage;

public class StockMain {
	private static List Stocks = new ArrayList();
	
	public  List get_list_stocks(){
		return this.Stocks;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
//		try {
//			DataBuilder
//					.userinfchanger("7000",
//							"C:\\Users\\Administrator\\workspace\\GupiaoNo4\\data\\用户信息.xls");
//		} catch (RowsExceededException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (WriteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		catch (BiffException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		 homepage window = new homepage();
		 window.open();
	}
      
	public static void addStock() {
		// TODO Auto-generated method stub
		
	}
	

}
