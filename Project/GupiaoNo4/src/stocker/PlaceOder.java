package stocker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import stock.Dia_buy;
import stock.Dia_sell;
import stock.homepage;

public class PlaceOder {
	
	

/****************************买入部分**************************************************/
/****************************买入部分**************************************************/
/****************************买入部分**************************************************/

	public void update_buy() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
	
				
				
				//买入后创建一个交易类
				Trade trade_buy = new Trade();
				trade_buy = newtrade_buy();
				
				// 买入后更新交易记录excel表
				try {
					DataBuilder.addtrade(homepage.get_path_trade(), trade_buy);
				} catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//次序不可变
				//买入后增加持有股票类Stocks
				homepage.stkl.addStock(trade_buy);
				
				
				
				// 买入后更新用户信息table_info
				userinfochange_buy();
				
				//买入后更新持仓table表
				Importer.table_chiang_initial(homepage.stkl.stockslist,homepage.table_chicang);
				
				
			
			 	 Pie pie = new Pie( homepage.stkl.stockslist);
				 pie.getChartPanel(homepage.path_chigu);
				 homepage.lbl_chigu.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chigu.png"));
				 
		
	}
	

	private Trade newtrade_buy() {
		// TODO Auto-generated method stub
		Trade trade_buy = new Trade();
		
		trade_buy.set_name(Dia_buy.information[0].substring(21));
		trade_buy.set_code(Dia_buy.getstockcode().getText());
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(new Date());
        
		trade_buy.set_date(date);
		
		int numlitmit = Integer.parseInt(Dia_buy.getlimitnum().getText());
		int num = Integer.parseInt(Dia_buy.getnum().getText());
		
		if(num<=numlitmit){trade_buy.set_trade_stytle("买入");}
		else{trade_buy.set_trade_stytle("卖空");}
		
		trade_buy.set_price(Double.parseDouble(Dia_buy.getprice().getText()));
		trade_buy.set_num(Integer.parseInt(Dia_buy.getnum().getText()));
		trade_buy.set_place(Dia_buy.place);
		
		return trade_buy;
	}

	private void userinfochange_buy() {
		// TODO Auto-generated method stub
		
		double d1 = Double.parseDouble(
				homepage.get_table_userinfo().getItem(1).getText(1));//获得table表的可用资金
		
		double d2 = Double.parseDouble(Dia_buy.getprice().getText());//委托价格
		double d3 = Double.parseDouble(Dia_buy.getnum().getText());//委托数量
		
		//修改可用资金
		homepage.get_table_userinfo().getItem(1).setText(1, Double.toString(d1-d2*d3));
		
		
		
		//调用用户信息table表的方法，修改资产总值，持有股票数，盈利率
		Importer.userinfo_table_change(homepage.table_userinfo,homepage.stkl.stockslist);
		
	}


/****************************卖出部分**************************************************/
/****************************卖出部分**************************************************/
/****************************卖出部分**************************************************/


	public void update_sell() {
		// TODO Auto-generated method stub
		//System.out.println("ss");
		
		//买入后创建一个交易类
		Trade trade_sell = new Trade();
		trade_sell = newtrade_sell();
		
		// 买入后更新交易记录excel表
		try {
			DataBuilder.addtrade(homepage.get_path_trade(), trade_sell);
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//卖出后更新stockslist
		stockslistchangge_sell(homepage.table_chicang,homepage.tableindex,homepage.stkl.stockslist,Dia_sell.text_number.getText());
		
		//卖出后更新用户信息table_info
		userinfochange_sell(Dia_sell.text_price.getText(),Dia_sell.text_number.getText()
				,homepage.table_userinfo,homepage.stkl.stockslist);
		
		//更新持仓excel表
		try {
			Excel_chicang_change_sell(Dia_sell.text_number.getText(),homepage.table_chicang
					,homepage.tableindex,homepage.stkl.stockslist);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//刷新股票持仓table表
		Importer.table_chiang_initial(homepage.stkl.stockslist,homepage.table_chicang);
		
		 Pie pie = new Pie( homepage.stkl.stockslist);
		 pie.getChartPanel(homepage.path_chigu);
		 homepage.lbl_chigu.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chigu.png"));
		 
	}



	private Trade newtrade_sell() {
		// TODO Auto-generated method stub
		Trade trade_sell = new Trade();
		
		trade_sell.set_name(Dia_sell.information_sell[0].substring(21));
		trade_sell.set_code(Dia_sell.text_sharecode.getText());
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(new Date());
        
		trade_sell.set_date(date);

		double d1 = Double.parseDouble(
				homepage.get_table_userinfo().getItem(1).getText(1));//获得table表的可用资金
		if(d1>0){trade_sell.set_trade_stytle("卖出");}
		else{trade_sell.set_trade_stytle("补仓");}
		
		trade_sell.set_price(Double.parseDouble(Dia_sell.text_price.getText()));
		trade_sell.set_num(Integer.parseInt(Dia_sell.text_number.getText()));
		trade_sell.set_place(Dia_sell.place);
		
		return trade_sell;
	}


	private void Excel_chicang_change_sell(String number, Table table_chicang, int tableindex, List<Stocks> stockslist)throws IOException, RowsExceededException, WriteException, BiffException {
		// TODO Auto-generated method stub
		
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(homepage.get_path_chicang()));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(homepage.get_path_chicang()),workBook);
		WritableSheet sheet = writeBook.getSheet(0);
		
		// 如果全部卖完则删除持仓excel行中的元素
		if (Integer.parseInt(number) == Integer
				.parseInt(table_chicang.getItem(tableindex)
						.getText(5))) {

			sheet.removeRow(tableindex);
		} else {
			
			int lessnum =stockslist.get(tableindex).getNum()- Integer.parseInt(number) ;
			Label label = new Label(5,tableindex,Integer.toString(lessnum));
			sheet.addCell(label);//修改数量


		}
		// 4、打开流，开始写文件
		writeBook.write();
		// 5、关闭流
		writeBook.close();
	}



	private void stockslistchangge_sell(Table table_chicang, int tableindex, List<Stocks> stockslist, String sell_num) {
		// TODO Auto-generated method stub
		
		// 如果全部卖完则删除该stockslist中的元素
		if (Integer.parseInt(sell_num) == Integer.parseInt(table_chicang
				.getItem(tableindex).getText(5))) {

			stockslist.remove(tableindex);
		} else {

			stockslist.get(tableindex).setNum(
					stockslist.get(tableindex).getNum()
							- Integer.parseInt(sell_num));

		}
	}



	private void userinfochange_sell(String price, String number, Table table_userinfo, List<Stocks> stockslist) {
		// TODO Auto-generated method stub
		
		double d1 = Double.parseDouble(
				table_userinfo.getItem(1).getText(1));//获得table表的可用资金
		
		double d2 = Double.parseDouble(price);//委托价格
		double d3 = Double.parseDouble(number);//委托数量
		
		//修改可用资金
		table_userinfo.getItem(1).setText(1, Double.toString(d1+d2*d3));
		
//		int d4= Integer.parseInt(
//				table_userinfo.getItem(1).getText(3));//获得持有股票数
		
		//调用用户信息table表的方法，修改资产总值，持有股票数，盈利率
		Importer.userinfo_table_change(table_userinfo,stockslist);
	}

}
