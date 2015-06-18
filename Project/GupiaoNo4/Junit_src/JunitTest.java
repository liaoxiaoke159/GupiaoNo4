import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.junit.Test;

import stock.NewAccount;
import stock.homepage;
import stocker.DataBuilder;
import stocker.DataDealer;
import stocker.HistoryStockown;
import stocker.PlaceOder;
import stocker.Stocks;
import stocker.Trade;


public class JunitTest {
	
	private  Shell shell;
	List<Stocks> stockslist;
	
	/**
	 * 实例化一个table的方法
	 * @return 
	 */
	public Table tableinfoTestcreat(){
		Table tabletest;
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
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(tabletest,
				SWT.NONE);
		tblclmnNewColumn_5.setWidth(77);
		tblclmnNewColumn_5.setText("New Column");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(tabletest,
				SWT.NONE);
		tblclmnNewColumn_6.setWidth(77);
		tblclmnNewColumn_6.setText("New Column");
		
		return tabletest;
	}
	
	
	 
	 
  /**
   *  测试添加一个交易记录到excel表格
   */
	@Test
	public void DataBuilder_addtradeTest() {
		

		// 实例化一个交易记录
		Trade trade = new Trade();
		trade.set_name("测试");
		trade.set_code("000046");
		trade.set_date("2015/5/19");
		trade.set_num(3000);
		trade.set_place("sh");
		trade.set_price(888);
		trade.set_trade_stytle("卖出");

		
		DataBuilder db = new DataBuilder();
		String file_path_trade = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTest.xls";
		try {
			if(!db.addtrade(file_path_trade,trade)){
				System.out.println("卖出数量非法");
				return;
			}
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
		
		//找出交易日期对应的一行 （该日期所有行中的最后一行）
		int excelindex;
		String[] date_trade = trade.get_date().split("/");
		int loop = 2;
		for(; loop <rows;++loop){
			String[] date = Sheet.getCell(2,loop).getContents().split("/");
				if(Integer.parseInt(date_trade[0])>=Integer.parseInt(date[0]))
				{
							if(Integer.parseInt(date_trade[1])>=Integer.parseInt(date[1])){
								if(Integer.parseInt(date_trade[2])>=Integer.parseInt(date[2])){
									
									continue;
								}else break;
								
							}else break;
						}else break;
						
						}
		excelindex = loop-1;			
					
		Cell Cell1 = Sheet.getCell(0, excelindex);
		String nameexpected = Cell1.getContents();//期望值
		String nametruevalue = trade.get_name();//真实值
		assertEquals(nameexpected, nametruevalue);//断言方法
		
		Cell Cell2 = Sheet.getCell(1, excelindex);
		String codeexpected = Cell2.getContents();//期望值
		String codetruevalue = trade.get_code();//真实值
		assertEquals(codeexpected, codetruevalue);//断言方法
		
		Cell Cell3 = Sheet.getCell(2, excelindex);
		String dateexpected = Cell3.getContents();//期望值
		String datetruevalue = trade.get_date();//真实值
		assertEquals(dateexpected, datetruevalue);//断言方法
		
		Cell Cell4 = Sheet.getCell(3, excelindex);
		String stytleexpected = Cell4.getContents();//期望值
		String stytletruevalue = trade.get_trade_stytle();//真实值
		assertEquals(stytleexpected,stytletruevalue);//断言方法
		
		Cell Cell5 = Sheet.getCell(4, excelindex);
		String priceexpected = Cell5.getContents();//期望值
		String pricetruevalue = Double.toString(trade.get_price());//真实值
		assertEquals(priceexpected,pricetruevalue);//断言方法
		
		Cell Cell6 = Sheet.getCell(5, excelindex);
		String numexpected = Cell6.getContents();//期望值
		String numtruevalue = Integer.toString(trade.get_num());//真实值
		assertEquals(numexpected, numtruevalue);//断言方法
		
		Cell Cell7 = Sheet.getCell(6, excelindex);
		String placeexpected = Cell7.getContents();//期望值
		String placetruevalue = trade.get_place();//真实值
		assertEquals(placeexpected,placetruevalue);//断言方法
		
		//恢复excel
		WritableWorkbook writableWorkbook = null;
		try {
			writableWorkbook = Workbook.createWorkbook(new File(file_path_trade),rwb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableSheet sheet =writableWorkbook.getSheet(0);
		sheet.removeRow(excelindex);
		try {
			writableWorkbook.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writableWorkbook.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 测试从excel 读取一个交易记录
	 */
	@Test
	public void DataBuilder_tradelistmakerTest(){
		
		List<Trade> tradelist = new ArrayList<Trade>();
		DataBuilder dd = new DataBuilder();
		try {
			tradelist=dd.tradelistmaker("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTest.xls");
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Trade expected1 = new Trade("康达尔","000048","2015/05/17","买入",18.0,300,"sz");
		Trade truevalue1 = tradelist.get(0);
		assertEquals(expected1.get_name(),truevalue1.get_name());//断言方法
		assertEquals(expected1.get_date(),truevalue1.get_date());
		assertEquals(expected1.get_code(),truevalue1.get_code());
		assertEquals(expected1.get_trade_stytle(),truevalue1.get_trade_stytle());
		assertEquals(Double.toString(expected1.get_price()),Double.toString(truevalue1.get_price()));
		assertEquals(expected1.get_num(),truevalue1.get_num());
		assertEquals(expected1.get_place(),truevalue1.get_place());
		
	}
	
	/**
	 * 测试导入路径是否合法
	 */
	@Test
	public void DataDealer_path_fileCheckTest(){
		
		String pathRight = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTest.xls";  //正确的路径
		String pathWrong = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTestWrong.xls";//错误的路径
		
		DataDealer datadealer = new DataDealer("",-1);
		
		boolean expected = true;//期望值
		boolean truevalue = datadealer.path_fileCheck(pathRight);//真实值
		assertEquals(expected,truevalue);//断言方法
		
		boolean expected1 = false;//期望值
		boolean truevalue1 = datadealer.path_fileCheck(pathWrong );//真实值
		assertEquals(expected1,truevalue1);//断言方法
	}
	

	/**
	 * 测试生成一个交易记录
	 */
	
	@Test
	public void PlaceOder_newtradeTest(){
		
		 PlaceOder placeoder = new  PlaceOder(0,"测试名","000000","测试类型","888","100","sz","2015/6/11");
		 
		 Trade tradetruevalue =placeoder.newtrade(placeoder.name, placeoder.code,
				 placeoder.style, placeoder.price, placeoder.num, placeoder.place,placeoder.date);//真实值
		 Trade tradeexpected = new Trade("测试名","000000","2015/6/11","测试类型",888,100,"sz");
		   assertEquals(tradeexpected.get_name(),tradetruevalue.get_name());//断言方法
			assertEquals(tradeexpected.get_date(),tradetruevalue.get_date());
			assertEquals(tradeexpected.get_code(),tradetruevalue.get_code());
			assertEquals(tradeexpected.get_trade_stytle(),tradetruevalue.get_trade_stytle());
			assertEquals(Double.toString(tradeexpected.get_price()),Double.toString(tradetruevalue.get_price()));
			assertEquals(tradeexpected.get_num(),tradetruevalue.get_num());
			assertEquals(tradeexpected.get_place(),tradetruevalue.get_place());
		 
		 
	}
	
	/**
	 * 测试HistoryStockown类的addnumber_date方法
	 */
	@Test
	public void HistoryStockown_addnumber_dateTest(){
		HistoryStockown hs = new HistoryStockown("测试名","0000000","sz");
		
		hs.addnumber_date(100,"2015/6/11");//调用方法 新增
		int numexpected = 100;//期望值
		int numtruevalue = hs.number.get(0);//真实值
		assertEquals(numexpected,numtruevalue);//断言方法
		
		hs.addnumber_date(100,"2015/6/11");//调用方法  增加数量 不增加日期
		int numexpected1 = 200;//期望值
		int numtruevalue1 = hs.number.get(0);//真实值
		assertEquals(numexpected1,numtruevalue1);//断言方法
		
		hs.addnumber_date(100,"2015/6/12");//调用方法  增加一个日期
		int numexpected2 = 200;//期望值
		int numtruevalue2 = hs.number.get(0);//真实值
		assertEquals(numexpected2,numtruevalue2);//断言方法
		
		int numexpected3 = 300;//期望值
		int numtruevalue3 = hs.number.get(1);//真实值
		assertEquals(numexpected3,numtruevalue3);//断言方法
		
	}
	
	/**
	 * 测试homepage类的savepath方法
	 * @throws IOException 
	 * @throws BiffException 
	 * @throws WriteException 
	 */
	
	@Test
	public void homepage_savepathTest() throws BiffException, IOException, WriteException{
		String path = "需要保存的账户路径";
		String path_account = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTestWrong.xls";//保存的位置
		
		homepage h = new homepage();
		h.savepath(path,path_account);
		
		Workbook wb = Workbook.getWorkbook(new File(path_account));
		Sheet sheet = wb.getSheet(0);
		int rows = sheet.getRows();
		
		String expected = path;//期望值
		String truevalue=sheet.getCell(0, rows-1).getContents(); ;//真实值
		assertEquals(expected,truevalue);//断言方法
		
		
		//恢复环境
		WritableWorkbook wwb = Workbook.createWorkbook(new File(path_account),wb);
		WritableSheet sheet1  = wwb.getSheet(0);
		
		sheet1.removeRow(rows-1);
		
		wwb.write();
		wwb.close();
		
	}
	
	/**
	 * 测试NewAccount类的 newexcel方法
	 * @throws IOException 
	 * @throws BiffException 
	 */
	@Test
	public void  NewAccount_newexcelTest() throws BiffException, IOException{
		String path = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\newtradeTset.xls";
		NewAccount na = new NewAccount();
		na.newexcel(path,"100");
		Workbook wb = Workbook.getWorkbook(new File(path ));
		Sheet sheet = wb.getSheet(0);
		
		String expected ="100" ;//期望值
		String truevalue=sheet.getCell(7,0).getContents();//真实值
		assertEquals(expected,truevalue);//断言方法
		
		String expected1 ="股票名称" ;//期望值
		String truevalue1=sheet.getCell(0,1).getContents();//真实值
		assertEquals(expected1,truevalue1);//断言方法
		
		String expected2 ="股票代码" ;//期望值
		String truevalue2=sheet.getCell(1,1).getContents();//真实值
		assertEquals(expected2,truevalue2);//断言方法
		
		String expected3 ="交易日期" ;//期望值
		String truevalue3=sheet.getCell(2,1).getContents();//真实值
		assertEquals(expected3,truevalue3);//断言方法
		
		String expected4 ="交易类型" ;//期望值
		String truevalue4=sheet.getCell(3,1).getContents();//真实值
		assertEquals(expected4,truevalue4);//断言方法
		
		String expected5 ="成交价";//期望值
		String truevalue5=sheet.getCell(4,1).getContents();//真实值
		assertEquals(expected5,truevalue5);//断言方法
		
		String expected6 ="成交数量";//期望值
		String truevalue6=sheet.getCell(5,1).getContents();//真实值
		assertEquals(expected6,truevalue6);//断言方法
		
		String expected7 ="交易所";//期望值
		String truevalue7=sheet.getCell(6,1).getContents();//真实值
		assertEquals(expected7,truevalue7);//断言方法
		
		//删除文件 恢复环境
		File file = new File(path);
		if (file.isFile() && file.exists()) {  
	        file.delete();  
	        }
	}

/**
 * 修改用户初始资金
 * @throws IOException 
 * @throws FileNotFoundException 
 * @throws BiffException 
 * @throws WriteException 
 * @throws RowsExceededException 
 */
	@Test
	public void DataDealer_changefundTest() throws BiffException, FileNotFoundException, IOException, RowsExceededException, WriteException{
		
		
		String newfund = "123456";//构造一个初始资金
		String path = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTest.xls";//测试交易记录路径
		
		Workbook wb = Workbook.getWorkbook(new FileInputStream(path));
		Sheet sheet = wb.getSheet(0);
		String fund = sheet.getCell(7, 0).getContents();//把原先的资金先保存一下
		
		//调用方法
		DataDealer dd = new DataDealer(path, -1);
		dd.changefund(newfund);
		
		Workbook wb2 = Workbook.getWorkbook(new FileInputStream(path));
		Sheet sheet2 = wb2.getSheet(0);
		String expected =newfund;//期望值
		String truevalue=sheet2.getCell(7,0).getContents();//真实值
		assertEquals(expected,truevalue);//断言方法
		
		//恢复excel
		WritableWorkbook wwb = Workbook.createWorkbook(new File(path), wb);
		WritableSheet writesheet = wwb.getSheet(0);
		Label label = new Label(7,0,fund);
		writesheet.addCell(label);
		
		wwb.write();
		wwb.close();
		
	}
	
	/**
	 * 测试DataDealer类的tablemaker方法
	 * @throws IOException 
	 * @throws BiffException 
	 */
   @Test
   public void DataDealer_tablemakerTest() throws BiffException, IOException{
	   
	   String path = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTest.xls";
	   DataDealer  dd = new DataDealer(path,-1);
	   Table table =tableinfoTestcreat();
	   dd.tablemaker(table);
	   
	   Workbook wb = Workbook.getWorkbook(new FileInputStream(path));
	   Sheet sheet = wb.getSheet(0);
	  
	   for(int loop = 1; loop<sheet.getRows()-1;loop++){
		   for(int column = 0; column < sheet.getColumns();column++){
			   
			    String expected =sheet.getCell(column, loop).getContents();//期望值
				String truevalue=table.getItem(loop-1).getText(column);//真实值
				assertEquals(expected,truevalue);//断言方法
		   }
	   }
   }

}
