import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.junit.Test;

import stock.homepage;
import stocker.DataBuilder;
import stocker.Exporter;
import stocker.Importer;
import stocker.Stockown;
import stocker.Stocks;
import stocker.Trade;


public class JunitTest {
	
	private  Shell shell;
	private Table tabletest;
	List<Stocks> stockslist;
	
	/**
	 * 实例化一个table的方法
	 */
	public void tableinfoTestcreat(){
		shell =new Shell();
		tabletest = new Table(shell, SWT.FULL_SELECTION);
		tabletest.setHeaderVisible(false);
		tabletest.setLinesVisible(true);
		TableColumn tblclmnNewColumn = new TableColumn(tabletest, SWT.NONE);
		tblclmnNewColumn.setWidth(85);
		tblclmnNewColumn.setText("New Column");

		TableColumn tblclmnNewColumn_1 = new TableColumn(tabletest,
				SWT.NONE);
		tblclmnNewColumn_1.setWidth(73);
		tblclmnNewColumn_1.setText("New Column");

		TableColumn tblclmnNewColumn_2 = new TableColumn(tabletest,
				SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("New Column");

		TableColumn tblclmnNewColumn_3 = new TableColumn(tabletest,
				SWT.NONE);
		tblclmnNewColumn_3.setWidth(76);
		tblclmnNewColumn_3.setText("New Column");

		TableColumn tblclmnNewColumn_4 = new TableColumn(tabletest,
				SWT.NONE);
		tblclmnNewColumn_4.setWidth(77);
		tblclmnNewColumn_4.setText("New Column");
		
	}
	
	

	/**
	 * 测试把excel用户信息导入到界面table上
	 */
	@Test
	public void tablemakerTest() {
		
		
		String path_usrinfoTest = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\userinfoTest.xls";
		
		//实例化一个table
		tableinfoTestcreat();
		try {
			DataBuilder.tablemaker(path_usrinfoTest, tabletest);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		String fundTest = "2000";//期望值
		String fundtruevalue = tabletest.getItem(1).getText(0);//真实值
		assertEquals(fundTest, fundtruevalue);//断言方法 
		
		String fundownTest = "2000";//期望值
		String fundowntruevalue = tabletest.getItem(1).getText(1);//真实值
		assertEquals(fundownTest, fundowntruevalue);//断言方法
		
		String fundsumTest = "2000";//期望值
		String fundsumtruevalue = tabletest.getItem(1).getText(2);//真实值
		assertEquals(fundsumTest, fundsumtruevalue);//断言方法
		
		String stocknumTest = "0";//期望值
		String stocknumtruevalue =tabletest.getItem(1).getText(3);//真实值
		assertEquals(stocknumTest, stocknumtruevalue);//断言方法
		
		String returnrateTest = "0.00%";//期望值
		String returnratetruevalue = tabletest.getItem(1).getText(4);//真实值
		assertEquals(returnrateTest, returnratetruevalue);//断言方法
			
	}
		
	/**
	 * 测试增加一个股票到持股list
	 */
		 @Test
	    public void addStockTest(){
			 
			// 实例化一个交易记录
				Trade trade = new Trade();
				trade.set_name("测试股票");
				trade.set_code("000025");
				trade.set_date("2015/5/12");
				trade.set_num(600);
				trade.set_place("sz");
				trade.set_price(10.0);
				trade.set_trade_stytle("买入");
				
				Stockown stk = new Stockown();
				
				//调用方法
				stk.addStock(trade);
				
				String expected1 = trade.get_name();//期望值
				String actual1 = stk.stockslist.get(0).getSocketname();//真实值
				assertEquals(expected1,actual1);//断言方法
				
				String expected2 = trade.get_code();//期望值
				String actual2 = stk.stockslist.get(0).getSocketcode();//真实值
				assertEquals(expected2,actual2);//断言方法
				
				String expected3 = trade.get_place();//期望值
				String actual3 = stk.stockslist.get(0).getplace();//真实值
				assertEquals(expected3,actual3);//断言方法
				
				String expected4 = Double.toString(trade.get_price());//期望值
				String actual4 = Double.toString(stk.stockslist.get(0).getcostprice());//真实值
				assertEquals(expected4,actual4);//断言方法
				
				String expected5 = Integer.toString(trade.get_num()+300);//期望值
				String actual5 = Integer.toString(stk.stockslist.get(0).getNum());//真实值
				//System.out.println(expected5);
				//System.out.println(actual5);
				assertEquals(expected5,actual5);//断言方法
				
			 
	    }
	/**
	 * 测试保存持股list到excel表
	 */
	@Test
	public void stockownsaverTest(){
		
		//实例化一个持股list
		Stocks stock =new Stocks("测试股票", "000025", "300");
		stock.setplace("sz");
		stock.setcostprice(10);
		List<Stocks> stockslist = new ArrayList<Stocks>();
		stockslist.add(stock);
		
		String path_chicang = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\stockownTest.xls";
		
		
		try {
			//调用方法
			Exporter.stockownsaver(stockslist,path_chicang);
		} catch (WriteException | BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(new FileInputStream(path_chicang));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rwb.getNumberOfSheets();

		Sheet sheet = rwb.getSheet(0);
		String[] information = new String[31];
		
		 try {
			information = Internet.share.Internet.getSharedata("sz",stock.getSocketcode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		String expected1 = stockslist.get(0).getSocketname();//期望值
		String actual1= sheet.getCell(0, 0).getContents();//真实值
		assertEquals(expected1, actual1);//断言方法
		
		String expected2 = stockslist.get(0).getSocketcode();//期望值
		String actual2= sheet.getCell(1, 0).getContents();//真实值
		assertEquals(expected2, actual2);//断言方法
		
		String expected3 = stockslist.get(0).getplace();//期望值
		String actual3= sheet.getCell(2, 0).getContents();//真实值
		assertEquals(expected3, actual3);//断言方法
		
		String expected4 = information[3];//期望值
		String actual4= sheet.getCell(3, 0).getContents();//真实值
		assertEquals(expected4, actual4);//断言方法
		
		double nowprice = Double.parseDouble(information[3]);
		double price = stockslist.get(0).getcostprice();
		double rate = (nowprice - price)/price;
		
		// 百分数格式：有两位小数
		NumberFormat nf_precent = NumberFormat.getPercentInstance();
		nf_precent.setMinimumFractionDigits(2);
		// double格式：有两位小数
		NumberFormat nf_price = NumberFormat.getNumberInstance();
		nf_price.setMinimumFractionDigits(2);
		
		String expected5 = nf_price.format(nowprice-price);//期望值
		String actual5 = sheet.getCell(4, 0).getContents();//真实值
		assertEquals(expected5, actual5);//断言方法
		
		String expected6 = Integer.toString(stockslist.get(0).getNum());//期望值
		String actual6 = sheet.getCell(5, 0).getContents();//真实值
		assertEquals(expected6, actual6);//断言方法
		
		String expected7 = Double.toString(nowprice*(double)stockslist.get(0).getNum());//期望值
		String actual7 = sheet.getCell(6, 0).getContents();//真实值
		assertEquals(expected7, actual7);//断言方法
		
		String expected8 =Double.toString(stockslist.get(0).getcostprice());//期望值
		String actual8 = sheet.getCell(7, 0).getContents();//真实值
		assertEquals(expected8, actual8);//断言方法
		
		String expected9 =nf_precent.format(rate);//期望值
		String actual9 = sheet.getCell(8, 0).getContents();//真实值
		assertEquals(expected9, actual9);//断言方法
		
		String expected10 =nf_price.format((nowprice-price)*(double)stockslist.get(0).getNum());//期望值
		String actual10 = sheet.getCell(9, 0).getContents();//真实值
		assertEquals(expected10, actual10);//断言方法
		 
		
	}
	
/**
 * 测试持股excel表初始化到持股list
 */
	@Test
	public void stockslist_initialTest(){
		
		
		//实例化一个stockslist
		Stocks stock =new Stocks("测试股票", "000025", "300");
		stock.setplace("sz");
		stock.setcostprice(10.0);
		List<Stocks> stockslistexpected = new ArrayList<Stocks>();
		stockslistexpected.add(stock);//期望值
		
		String path_stockown = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\stockownTest.xls";
		
		List<Stocks> stockslistactual =null;
		try {
			//调用方法
			 stockslistactual = Stockown.stockslist_initial(path_stockown); //真实值
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String nameexpected = stock.getSocketname();
		String nameactual = stockslistactual.get(0).getSocketname();
		assertEquals(nameexpected ,nameactual);//断言方法
		
		String codeexpected = stock.getSocketcode();
		String codeactual = stockslistactual.get(0).getSocketcode();
		assertEquals(codeexpected ,codeactual);//断言方法
		
		String numexpected = Integer.toString(stock.getNum());
		String numactual = Integer.toString(stockslistactual.get(0).getNum());
		assertEquals(numexpected  ,numactual);//断言方法
		
		String placeexpected = stock.getplace();
		String placeactual = stockslistactual.get(0).getplace();
		assertEquals(placeexpected  ,placeactual);//断言方法
		
		String costpriceexpected = Double.toString(stock.getcostprice());
		String costpriceactual = Double.toString(stockslistactual.get(0).getcostprice());
		assertEquals(costpriceexpected  , costpriceactual);//断言方法		
		
	}
	
	

	 
	 
  /**
   *  测试添加一个交易记录到excel表格
   */
	@Test
	public void addtradeTest() {
		

		// 实例化一个交易记录
		Trade trade = new Trade();
		trade.set_name("测试");
		trade.set_code("000000");
		trade.set_date("2015/5/12");
		trade.set_num(300);
		trade.set_place("sz");
		trade.set_price(888);
		trade.set_trade_stytle("买入");

		
		String file_path_trade = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTest.xls";
		try {
			DataBuilder.addtrade(file_path_trade,trade);
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
		
		
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(new FileInputStream(file_path_trade));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rwb.getNumberOfSheets();

		Sheet Sheet = rwb.getSheet(0);
		int rows = Sheet.getRows();
		
		Cell Cell1 = Sheet.getCell(0, rows-1);
		String nameexpected = Cell1.getContents();//期望值
		String nametruevalue = trade.get_name();//真实值
		assertEquals(nameexpected, nametruevalue);//断言方法
		
		Cell Cell2 = Sheet.getCell(1, rows-1);
		String codeexpected = Cell2.getContents();//期望值
		String codetruevalue = trade.get_code();//真实值
		assertEquals(codeexpected, codetruevalue);//断言方法
		
		Cell Cell3 = Sheet.getCell(2, rows-1);
		String dateexpected = Cell3.getContents();//期望值
		String datetruevalue = trade.get_date();//真实值
		assertEquals(dateexpected, datetruevalue);//断言方法
		
		Cell Cell4 = Sheet.getCell(3, rows-1);
		String stytleexpected = Cell4.getContents();//期望值
		String stytletruevalue = trade.get_trade_stytle();//真实值
		assertEquals(stytleexpected,stytletruevalue);//断言方法
		
		Cell Cell5 = Sheet.getCell(4, rows-1);
		String priceexpected = Cell5.getContents();//期望值
		String pricetruevalue = Double.toString(trade.get_price());//真实值
		assertEquals(priceexpected,pricetruevalue);//断言方法
		
		Cell Cell6 = Sheet.getCell(5, rows-1);
		String numexpected = Cell6.getContents();//期望值
		String numtruevalue = Integer.toString(trade.get_num());//真实值
		assertEquals(numexpected, numtruevalue);//断言方法
		
		Cell Cell7 = Sheet.getCell(6, rows-1);
		String placeexpected = Cell7.getContents();//期望值
		String placetruevalue = trade.get_place();//真实值
		assertEquals(placeexpected,placetruevalue);//断言方法
		
		
		
	}
	
   /**
    * 测试用户信息table初始化  资产总值 持有股票数 盈利率有所修改
    */
	@Test
	public void userinfo_table_changeTest() {
		
		//实例化一个持股list
		Stocks stocktest = new Stocks("测试股票","000025","100");//名称，股票代码，数量
		stocktest.setplace("sz");//交易所
		stocktest.setcostprice(10);//成本价
		stockslist = new ArrayList<Stocks>();
		stockslist.add(stocktest);
		
		//实例化一个table
		tableinfoTestcreat();
		TableItem tableItem1 = new TableItem(tabletest,SWT.NONE);
		String[] tableitem1text1 = new String[]{"初始资金","可用资金","资产总值","持有股票数量","盈利率"};
		tableItem1.setText(tableitem1text1);	
		TableItem tableItem2 = new TableItem(tabletest,SWT.NONE);
		String[] tableitem1text2 = new String[]{"2000","2000","2000","0","0"};
		tableItem2.setText(tableitem1text2);	
		
		Importer.userinfo_table_change(tabletest,stockslist);
		
		
		String[] information = new String[31];
		try {
			 information = Internet.share.Internet.getSharedata("sz","000025");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double fund = Double.parseDouble(tabletest.getItem(1).getText(0));
		double fundown = Double.parseDouble(tabletest.getItem(1).getText(1));
		
	
		
		double sumprice = Double.parseDouble(information[3])*stocktest.getNum();
		String fundsumexpected = Double.toString(fundown + sumprice );//期望值
		String fundsumactual = tabletest.getItem(1).getText(2);//真实值
		assertEquals(fundsumexpected, fundsumactual);//断言方法
		
		String stocksnumexpecter = Integer.toString(stockslist.size());//期望值
		String stocksnumactual = tabletest.getItem(1).getText(3);//真实值
		assertEquals(stocksnumexpecter,stocksnumactual);//断言方法
		
		double returnrate=(Double.parseDouble(fundsumexpected)-fund)/fund;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		String returnrateexpected = nf.format(returnrate);//期望值
		String returnrateactual = tabletest.getItem(1).getText(4);//真实值
	    assertEquals(returnrateexpected,returnrateactual);//断言方法
		
	
	}
	
	/**
	 * 测试保存用户信息到excel表
	 */
	@Test
	public void userinfosaverTest() {

		// 实例化一个用户信息table
		tableinfoTestcreat();
		TableItem tableItem1 = new TableItem(tabletest, SWT.NONE);
		String[] tableitem1text1 = new String[] { "初始资金", "可用资金", "资产总值",
				"持有股票数", "盈利率" };
		tableItem1.setText(tableitem1text1);
		
		TableItem tableItem2 = new TableItem(tabletest, SWT.NONE);
		String[] tableitem1text2 = new String[] { "2000", "2000", "2000", "0",	"0.00%" };
		tableItem2.setText(tableitem1text2);
		
		String path_info_test = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\userinfoTest.xls";
		try {
			//调用方法
			Exporter.userinfosaver(tabletest,path_info_test);
			
		} catch (WriteException | BiffException | IOException e) {
			e.printStackTrace();
		}

		
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(new FileInputStream(path_info_test));
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		

		Sheet sheet = rwb.getSheet(0);
		int rows = sheet.getRows();
		int columns = sheet.getColumns();
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				
				String  expected= tabletest.getItem(i).getText(j);// 期望值
				String actual = sheet.getCell(j, i).getContents();// 真实值
				assertEquals(expected, actual);// 断言方法

			}

		}
	
		
	
	}


}
