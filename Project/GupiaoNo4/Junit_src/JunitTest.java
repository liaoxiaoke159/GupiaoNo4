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
	 * ʵ����һ��table�ķ���
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
   *  �������һ�����׼�¼��excel���
   */
	@Test
	public void DataBuilder_addtradeTest() {
		

		// ʵ����һ�����׼�¼
		Trade trade = new Trade();
		trade.set_name("����");
		trade.set_code("000046");
		trade.set_date("2015/5/19");
		trade.set_num(3000);
		trade.set_place("sh");
		trade.set_price(888);
		trade.set_trade_stytle("����");

		
		DataBuilder db = new DataBuilder();
		String file_path_trade = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTest.xls";
		try {
			if(!db.addtrade(file_path_trade,trade)){
				System.out.println("���������Ƿ�");
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
		
		//�ҳ��������ڶ�Ӧ��һ�� ���������������е����һ�У�
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
		String nameexpected = Cell1.getContents();//����ֵ
		String nametruevalue = trade.get_name();//��ʵֵ
		assertEquals(nameexpected, nametruevalue);//���Է���
		
		Cell Cell2 = Sheet.getCell(1, excelindex);
		String codeexpected = Cell2.getContents();//����ֵ
		String codetruevalue = trade.get_code();//��ʵֵ
		assertEquals(codeexpected, codetruevalue);//���Է���
		
		Cell Cell3 = Sheet.getCell(2, excelindex);
		String dateexpected = Cell3.getContents();//����ֵ
		String datetruevalue = trade.get_date();//��ʵֵ
		assertEquals(dateexpected, datetruevalue);//���Է���
		
		Cell Cell4 = Sheet.getCell(3, excelindex);
		String stytleexpected = Cell4.getContents();//����ֵ
		String stytletruevalue = trade.get_trade_stytle();//��ʵֵ
		assertEquals(stytleexpected,stytletruevalue);//���Է���
		
		Cell Cell5 = Sheet.getCell(4, excelindex);
		String priceexpected = Cell5.getContents();//����ֵ
		String pricetruevalue = Double.toString(trade.get_price());//��ʵֵ
		assertEquals(priceexpected,pricetruevalue);//���Է���
		
		Cell Cell6 = Sheet.getCell(5, excelindex);
		String numexpected = Cell6.getContents();//����ֵ
		String numtruevalue = Integer.toString(trade.get_num());//��ʵֵ
		assertEquals(numexpected, numtruevalue);//���Է���
		
		Cell Cell7 = Sheet.getCell(6, excelindex);
		String placeexpected = Cell7.getContents();//����ֵ
		String placetruevalue = trade.get_place();//��ʵֵ
		assertEquals(placeexpected,placetruevalue);//���Է���
		
		//�ָ�excel
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
	 * ���Դ�excel ��ȡһ�����׼�¼
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
		Trade expected1 = new Trade("�����","000048","2015/05/17","����",18.0,300,"sz");
		Trade truevalue1 = tradelist.get(0);
		assertEquals(expected1.get_name(),truevalue1.get_name());//���Է���
		assertEquals(expected1.get_date(),truevalue1.get_date());
		assertEquals(expected1.get_code(),truevalue1.get_code());
		assertEquals(expected1.get_trade_stytle(),truevalue1.get_trade_stytle());
		assertEquals(Double.toString(expected1.get_price()),Double.toString(truevalue1.get_price()));
		assertEquals(expected1.get_num(),truevalue1.get_num());
		assertEquals(expected1.get_place(),truevalue1.get_place());
		
	}
	
	/**
	 * ���Ե���·���Ƿ�Ϸ�
	 */
	@Test
	public void DataDealer_path_fileCheckTest(){
		
		String pathRight = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTest.xls";  //��ȷ��·��
		String pathWrong = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTestWrong.xls";//�����·��
		
		DataDealer datadealer = new DataDealer("",-1);
		
		boolean expected = true;//����ֵ
		boolean truevalue = datadealer.path_fileCheck(pathRight);//��ʵֵ
		assertEquals(expected,truevalue);//���Է���
		
		boolean expected1 = false;//����ֵ
		boolean truevalue1 = datadealer.path_fileCheck(pathWrong );//��ʵֵ
		assertEquals(expected1,truevalue1);//���Է���
	}
	

	/**
	 * ��������һ�����׼�¼
	 */
	
	@Test
	public void PlaceOder_newtradeTest(){
		
		 PlaceOder placeoder = new  PlaceOder(0,"������","000000","��������","888","100","sz","2015/6/11");
		 
		 Trade tradetruevalue =placeoder.newtrade(placeoder.name, placeoder.code,
				 placeoder.style, placeoder.price, placeoder.num, placeoder.place,placeoder.date);//��ʵֵ
		 Trade tradeexpected = new Trade("������","000000","2015/6/11","��������",888,100,"sz");
		   assertEquals(tradeexpected.get_name(),tradetruevalue.get_name());//���Է���
			assertEquals(tradeexpected.get_date(),tradetruevalue.get_date());
			assertEquals(tradeexpected.get_code(),tradetruevalue.get_code());
			assertEquals(tradeexpected.get_trade_stytle(),tradetruevalue.get_trade_stytle());
			assertEquals(Double.toString(tradeexpected.get_price()),Double.toString(tradetruevalue.get_price()));
			assertEquals(tradeexpected.get_num(),tradetruevalue.get_num());
			assertEquals(tradeexpected.get_place(),tradetruevalue.get_place());
		 
		 
	}
	
	/**
	 * ����HistoryStockown���addnumber_date����
	 */
	@Test
	public void HistoryStockown_addnumber_dateTest(){
		HistoryStockown hs = new HistoryStockown("������","0000000","sz");
		
		hs.addnumber_date(100,"2015/6/11");//���÷��� ����
		int numexpected = 100;//����ֵ
		int numtruevalue = hs.number.get(0);//��ʵֵ
		assertEquals(numexpected,numtruevalue);//���Է���
		
		hs.addnumber_date(100,"2015/6/11");//���÷���  �������� ����������
		int numexpected1 = 200;//����ֵ
		int numtruevalue1 = hs.number.get(0);//��ʵֵ
		assertEquals(numexpected1,numtruevalue1);//���Է���
		
		hs.addnumber_date(100,"2015/6/12");//���÷���  ����һ������
		int numexpected2 = 200;//����ֵ
		int numtruevalue2 = hs.number.get(0);//��ʵֵ
		assertEquals(numexpected2,numtruevalue2);//���Է���
		
		int numexpected3 = 300;//����ֵ
		int numtruevalue3 = hs.number.get(1);//��ʵֵ
		assertEquals(numexpected3,numtruevalue3);//���Է���
		
	}
	
	/**
	 * ����homepage���savepath����
	 * @throws IOException 
	 * @throws BiffException 
	 * @throws WriteException 
	 */
	
	@Test
	public void homepage_savepathTest() throws BiffException, IOException, WriteException{
		String path = "��Ҫ������˻�·��";
		String path_account = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTestWrong.xls";//�����λ��
		
		homepage h = new homepage();
		h.savepath(path,path_account);
		
		Workbook wb = Workbook.getWorkbook(new File(path_account));
		Sheet sheet = wb.getSheet(0);
		int rows = sheet.getRows();
		
		String expected = path;//����ֵ
		String truevalue=sheet.getCell(0, rows-1).getContents(); ;//��ʵֵ
		assertEquals(expected,truevalue);//���Է���
		
		
		//�ָ�����
		WritableWorkbook wwb = Workbook.createWorkbook(new File(path_account),wb);
		WritableSheet sheet1  = wwb.getSheet(0);
		
		sheet1.removeRow(rows-1);
		
		wwb.write();
		wwb.close();
		
	}
	
	/**
	 * ����NewAccount��� newexcel����
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
		
		String expected ="100" ;//����ֵ
		String truevalue=sheet.getCell(7,0).getContents();//��ʵֵ
		assertEquals(expected,truevalue);//���Է���
		
		String expected1 ="��Ʊ����" ;//����ֵ
		String truevalue1=sheet.getCell(0,1).getContents();//��ʵֵ
		assertEquals(expected1,truevalue1);//���Է���
		
		String expected2 ="��Ʊ����" ;//����ֵ
		String truevalue2=sheet.getCell(1,1).getContents();//��ʵֵ
		assertEquals(expected2,truevalue2);//���Է���
		
		String expected3 ="��������" ;//����ֵ
		String truevalue3=sheet.getCell(2,1).getContents();//��ʵֵ
		assertEquals(expected3,truevalue3);//���Է���
		
		String expected4 ="��������" ;//����ֵ
		String truevalue4=sheet.getCell(3,1).getContents();//��ʵֵ
		assertEquals(expected4,truevalue4);//���Է���
		
		String expected5 ="�ɽ���";//����ֵ
		String truevalue5=sheet.getCell(4,1).getContents();//��ʵֵ
		assertEquals(expected5,truevalue5);//���Է���
		
		String expected6 ="�ɽ�����";//����ֵ
		String truevalue6=sheet.getCell(5,1).getContents();//��ʵֵ
		assertEquals(expected6,truevalue6);//���Է���
		
		String expected7 ="������";//����ֵ
		String truevalue7=sheet.getCell(6,1).getContents();//��ʵֵ
		assertEquals(expected7,truevalue7);//���Է���
		
		//ɾ���ļ� �ָ�����
		File file = new File(path);
		if (file.isFile() && file.exists()) {  
	        file.delete();  
	        }
	}

/**
 * �޸��û���ʼ�ʽ�
 * @throws IOException 
 * @throws FileNotFoundException 
 * @throws BiffException 
 * @throws WriteException 
 * @throws RowsExceededException 
 */
	@Test
	public void DataDealer_changefundTest() throws BiffException, FileNotFoundException, IOException, RowsExceededException, WriteException{
		
		
		String newfund = "123456";//����һ����ʼ�ʽ�
		String path = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\tradeTest.xls";//���Խ��׼�¼·��
		
		Workbook wb = Workbook.getWorkbook(new FileInputStream(path));
		Sheet sheet = wb.getSheet(0);
		String fund = sheet.getCell(7, 0).getContents();//��ԭ�ȵ��ʽ��ȱ���һ��
		
		//���÷���
		DataDealer dd = new DataDealer(path, -1);
		dd.changefund(newfund);
		
		Workbook wb2 = Workbook.getWorkbook(new FileInputStream(path));
		Sheet sheet2 = wb2.getSheet(0);
		String expected =newfund;//����ֵ
		String truevalue=sheet2.getCell(7,0).getContents();//��ʵֵ
		assertEquals(expected,truevalue);//���Է���
		
		//�ָ�excel
		WritableWorkbook wwb = Workbook.createWorkbook(new File(path), wb);
		WritableSheet writesheet = wwb.getSheet(0);
		Label label = new Label(7,0,fund);
		writesheet.addCell(label);
		
		wwb.write();
		wwb.close();
		
	}
	
	/**
	 * ����DataDealer���tablemaker����
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
			   
			    String expected =sheet.getCell(column, loop).getContents();//����ֵ
				String truevalue=table.getItem(loop-1).getText(column);//��ʵֵ
				assertEquals(expected,truevalue);//���Է���
		   }
	   }
   }

}
