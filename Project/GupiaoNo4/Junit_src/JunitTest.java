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
	 * ʵ����һ��table�ķ���
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
	 * ���԰�excel�û���Ϣ���뵽����table��
	 */
	@Test
	public void tablemakerTest() {
		
		
		String path_usrinfoTest = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\userinfoTest.xls";
		
		//ʵ����һ��table
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
			

		String fundTest = "2000";//����ֵ
		String fundtruevalue = tabletest.getItem(1).getText(0);//��ʵֵ
		assertEquals(fundTest, fundtruevalue);//���Է��� 
		
		String fundownTest = "2000";//����ֵ
		String fundowntruevalue = tabletest.getItem(1).getText(1);//��ʵֵ
		assertEquals(fundownTest, fundowntruevalue);//���Է���
		
		String fundsumTest = "2000";//����ֵ
		String fundsumtruevalue = tabletest.getItem(1).getText(2);//��ʵֵ
		assertEquals(fundsumTest, fundsumtruevalue);//���Է���
		
		String stocknumTest = "0";//����ֵ
		String stocknumtruevalue =tabletest.getItem(1).getText(3);//��ʵֵ
		assertEquals(stocknumTest, stocknumtruevalue);//���Է���
		
		String returnrateTest = "0.00%";//����ֵ
		String returnratetruevalue = tabletest.getItem(1).getText(4);//��ʵֵ
		assertEquals(returnrateTest, returnratetruevalue);//���Է���
			
	}
		
	/**
	 * ��������һ����Ʊ���ֹ�list
	 */
		 @Test
	    public void addStockTest(){
			 
			// ʵ����һ�����׼�¼
				Trade trade = new Trade();
				trade.set_name("���Թ�Ʊ");
				trade.set_code("000025");
				trade.set_date("2015/5/12");
				trade.set_num(600);
				trade.set_place("sz");
				trade.set_price(10.0);
				trade.set_trade_stytle("����");
				
				Stockown stk = new Stockown();
				
				//���÷���
				stk.addStock(trade);
				
				String expected1 = trade.get_name();//����ֵ
				String actual1 = stk.stockslist.get(0).getSocketname();//��ʵֵ
				assertEquals(expected1,actual1);//���Է���
				
				String expected2 = trade.get_code();//����ֵ
				String actual2 = stk.stockslist.get(0).getSocketcode();//��ʵֵ
				assertEquals(expected2,actual2);//���Է���
				
				String expected3 = trade.get_place();//����ֵ
				String actual3 = stk.stockslist.get(0).getplace();//��ʵֵ
				assertEquals(expected3,actual3);//���Է���
				
				String expected4 = Double.toString(trade.get_price());//����ֵ
				String actual4 = Double.toString(stk.stockslist.get(0).getcostprice());//��ʵֵ
				assertEquals(expected4,actual4);//���Է���
				
				String expected5 = Integer.toString(trade.get_num()+300);//����ֵ
				String actual5 = Integer.toString(stk.stockslist.get(0).getNum());//��ʵֵ
				//System.out.println(expected5);
				//System.out.println(actual5);
				assertEquals(expected5,actual5);//���Է���
				
			 
	    }
	/**
	 * ���Ա���ֹ�list��excel��
	 */
	@Test
	public void stockownsaverTest(){
		
		//ʵ����һ���ֹ�list
		Stocks stock =new Stocks("���Թ�Ʊ", "000025", "300");
		stock.setplace("sz");
		stock.setcostprice(10);
		List<Stocks> stockslist = new ArrayList<Stocks>();
		stockslist.add(stock);
		
		String path_chicang = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\stockownTest.xls";
		
		
		try {
			//���÷���
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
		 
		 
		String expected1 = stockslist.get(0).getSocketname();//����ֵ
		String actual1= sheet.getCell(0, 0).getContents();//��ʵֵ
		assertEquals(expected1, actual1);//���Է���
		
		String expected2 = stockslist.get(0).getSocketcode();//����ֵ
		String actual2= sheet.getCell(1, 0).getContents();//��ʵֵ
		assertEquals(expected2, actual2);//���Է���
		
		String expected3 = stockslist.get(0).getplace();//����ֵ
		String actual3= sheet.getCell(2, 0).getContents();//��ʵֵ
		assertEquals(expected3, actual3);//���Է���
		
		String expected4 = information[3];//����ֵ
		String actual4= sheet.getCell(3, 0).getContents();//��ʵֵ
		assertEquals(expected4, actual4);//���Է���
		
		double nowprice = Double.parseDouble(information[3]);
		double price = stockslist.get(0).getcostprice();
		double rate = (nowprice - price)/price;
		
		// �ٷ�����ʽ������λС��
		NumberFormat nf_precent = NumberFormat.getPercentInstance();
		nf_precent.setMinimumFractionDigits(2);
		// double��ʽ������λС��
		NumberFormat nf_price = NumberFormat.getNumberInstance();
		nf_price.setMinimumFractionDigits(2);
		
		String expected5 = nf_price.format(nowprice-price);//����ֵ
		String actual5 = sheet.getCell(4, 0).getContents();//��ʵֵ
		assertEquals(expected5, actual5);//���Է���
		
		String expected6 = Integer.toString(stockslist.get(0).getNum());//����ֵ
		String actual6 = sheet.getCell(5, 0).getContents();//��ʵֵ
		assertEquals(expected6, actual6);//���Է���
		
		String expected7 = Double.toString(nowprice*(double)stockslist.get(0).getNum());//����ֵ
		String actual7 = sheet.getCell(6, 0).getContents();//��ʵֵ
		assertEquals(expected7, actual7);//���Է���
		
		String expected8 =Double.toString(stockslist.get(0).getcostprice());//����ֵ
		String actual8 = sheet.getCell(7, 0).getContents();//��ʵֵ
		assertEquals(expected8, actual8);//���Է���
		
		String expected9 =nf_precent.format(rate);//����ֵ
		String actual9 = sheet.getCell(8, 0).getContents();//��ʵֵ
		assertEquals(expected9, actual9);//���Է���
		
		String expected10 =nf_price.format((nowprice-price)*(double)stockslist.get(0).getNum());//����ֵ
		String actual10 = sheet.getCell(9, 0).getContents();//��ʵֵ
		assertEquals(expected10, actual10);//���Է���
		 
		
	}
	
/**
 * ���Գֹ�excel���ʼ�����ֹ�list
 */
	@Test
	public void stockslist_initialTest(){
		
		
		//ʵ����һ��stockslist
		Stocks stock =new Stocks("���Թ�Ʊ", "000025", "300");
		stock.setplace("sz");
		stock.setcostprice(10.0);
		List<Stocks> stockslistexpected = new ArrayList<Stocks>();
		stockslistexpected.add(stock);//����ֵ
		
		String path_stockown = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\stockownTest.xls";
		
		List<Stocks> stockslistactual =null;
		try {
			//���÷���
			 stockslistactual = Stockown.stockslist_initial(path_stockown); //��ʵֵ
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String nameexpected = stock.getSocketname();
		String nameactual = stockslistactual.get(0).getSocketname();
		assertEquals(nameexpected ,nameactual);//���Է���
		
		String codeexpected = stock.getSocketcode();
		String codeactual = stockslistactual.get(0).getSocketcode();
		assertEquals(codeexpected ,codeactual);//���Է���
		
		String numexpected = Integer.toString(stock.getNum());
		String numactual = Integer.toString(stockslistactual.get(0).getNum());
		assertEquals(numexpected  ,numactual);//���Է���
		
		String placeexpected = stock.getplace();
		String placeactual = stockslistactual.get(0).getplace();
		assertEquals(placeexpected  ,placeactual);//���Է���
		
		String costpriceexpected = Double.toString(stock.getcostprice());
		String costpriceactual = Double.toString(stockslistactual.get(0).getcostprice());
		assertEquals(costpriceexpected  , costpriceactual);//���Է���		
		
	}
	
	

	 
	 
  /**
   *  �������һ�����׼�¼��excel���
   */
	@Test
	public void addtradeTest() {
		

		// ʵ����һ�����׼�¼
		Trade trade = new Trade();
		trade.set_name("����");
		trade.set_code("000000");
		trade.set_date("2015/5/12");
		trade.set_num(300);
		trade.set_place("sz");
		trade.set_price(888);
		trade.set_trade_stytle("����");

		
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
		String nameexpected = Cell1.getContents();//����ֵ
		String nametruevalue = trade.get_name();//��ʵֵ
		assertEquals(nameexpected, nametruevalue);//���Է���
		
		Cell Cell2 = Sheet.getCell(1, rows-1);
		String codeexpected = Cell2.getContents();//����ֵ
		String codetruevalue = trade.get_code();//��ʵֵ
		assertEquals(codeexpected, codetruevalue);//���Է���
		
		Cell Cell3 = Sheet.getCell(2, rows-1);
		String dateexpected = Cell3.getContents();//����ֵ
		String datetruevalue = trade.get_date();//��ʵֵ
		assertEquals(dateexpected, datetruevalue);//���Է���
		
		Cell Cell4 = Sheet.getCell(3, rows-1);
		String stytleexpected = Cell4.getContents();//����ֵ
		String stytletruevalue = trade.get_trade_stytle();//��ʵֵ
		assertEquals(stytleexpected,stytletruevalue);//���Է���
		
		Cell Cell5 = Sheet.getCell(4, rows-1);
		String priceexpected = Cell5.getContents();//����ֵ
		String pricetruevalue = Double.toString(trade.get_price());//��ʵֵ
		assertEquals(priceexpected,pricetruevalue);//���Է���
		
		Cell Cell6 = Sheet.getCell(5, rows-1);
		String numexpected = Cell6.getContents();//����ֵ
		String numtruevalue = Integer.toString(trade.get_num());//��ʵֵ
		assertEquals(numexpected, numtruevalue);//���Է���
		
		Cell Cell7 = Sheet.getCell(6, rows-1);
		String placeexpected = Cell7.getContents();//����ֵ
		String placetruevalue = trade.get_place();//��ʵֵ
		assertEquals(placeexpected,placetruevalue);//���Է���
		
		
		
	}
	
   /**
    * �����û���Ϣtable��ʼ��  �ʲ���ֵ ���й�Ʊ�� ӯ���������޸�
    */
	@Test
	public void userinfo_table_changeTest() {
		
		//ʵ����һ���ֹ�list
		Stocks stocktest = new Stocks("���Թ�Ʊ","000025","100");//���ƣ���Ʊ���룬����
		stocktest.setplace("sz");//������
		stocktest.setcostprice(10);//�ɱ���
		stockslist = new ArrayList<Stocks>();
		stockslist.add(stocktest);
		
		//ʵ����һ��table
		tableinfoTestcreat();
		TableItem tableItem1 = new TableItem(tabletest,SWT.NONE);
		String[] tableitem1text1 = new String[]{"��ʼ�ʽ�","�����ʽ�","�ʲ���ֵ","���й�Ʊ����","ӯ����"};
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
		String fundsumexpected = Double.toString(fundown + sumprice );//����ֵ
		String fundsumactual = tabletest.getItem(1).getText(2);//��ʵֵ
		assertEquals(fundsumexpected, fundsumactual);//���Է���
		
		String stocksnumexpecter = Integer.toString(stockslist.size());//����ֵ
		String stocksnumactual = tabletest.getItem(1).getText(3);//��ʵֵ
		assertEquals(stocksnumexpecter,stocksnumactual);//���Է���
		
		double returnrate=(Double.parseDouble(fundsumexpected)-fund)/fund;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		String returnrateexpected = nf.format(returnrate);//����ֵ
		String returnrateactual = tabletest.getItem(1).getText(4);//��ʵֵ
	    assertEquals(returnrateexpected,returnrateactual);//���Է���
		
	
	}
	
	/**
	 * ���Ա����û���Ϣ��excel��
	 */
	@Test
	public void userinfosaverTest() {

		// ʵ����һ���û���Ϣtable
		tableinfoTestcreat();
		TableItem tableItem1 = new TableItem(tabletest, SWT.NONE);
		String[] tableitem1text1 = new String[] { "��ʼ�ʽ�", "�����ʽ�", "�ʲ���ֵ",
				"���й�Ʊ��", "ӯ����" };
		tableItem1.setText(tableitem1text1);
		
		TableItem tableItem2 = new TableItem(tabletest, SWT.NONE);
		String[] tableitem1text2 = new String[] { "2000", "2000", "2000", "0",	"0.00%" };
		tableItem2.setText(tableitem1text2);
		
		String path_info_test = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\userinfoTest.xls";
		try {
			//���÷���
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
				
				String  expected= tabletest.getItem(i).getText(j);// ����ֵ
				String actual = sheet.getCell(j, i).getContents();// ��ʵֵ
				assertEquals(expected, actual);// ���Է���

			}

		}
	
		
	
	}


}
