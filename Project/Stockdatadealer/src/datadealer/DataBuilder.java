package datadealer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;









import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.demo.Write;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;







public class DataBuilder {

	public static void tablemaker(String path_file, Table table)  throws BiffException, IOException{
		// TODO Auto-generated method stub
		// 1、构造excel文件输入流对象

		InputStream is = new FileInputStream(path_file);

		// 2、声明工作簿对象
		Workbook rwb = Workbook.getWorkbook(is);
		// 3、获得工作簿的个数,对应于一个excel中的工作表个数
		rwb.getNumberOfSheets();

		Sheet oFirstSheet = rwb.getSheet(0);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称
		// System.out.println("工作表名称：" + oFirstSheet.getName());
		int rows = oFirstSheet.getRows();// 获取工作表中的总行数
		int columns = oFirstSheet.getColumns();// 获取工作表中的总列数
		String[] itemdata = new String[columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell oCell = oFirstSheet.getCell(j, i);// 需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
				// System.out.println(oCell.getContents()+"\r\n");
				itemdata[j] = oCell.getContents();
				
			}
			TableItem tableItem = new TableItem(table,SWT.NONE);
			tableItem.setText(itemdata);
			//tableItem.setBackground(homepage.bcolor);
		}

	}
	
	
	public static void tableoutputer(String path_file, Table table)
			throws IOException, RowsExceededException, WriteException {

		// 1、创建工作簿(WritableWorkbook)对象，打开excel文件，若文件不存在，则创建文件
		WritableWorkbook writeBook = Workbook
				.createWorkbook(new File(path_file));

		// 2、新建工作表(sheet)对象，并声明其属于第几页
		WritableSheet firstSheet = writeBook.createSheet("第一个工作簿", 1);// 第一个参数为工作簿的名称，第二个参数为页数

		int columns = table.getColumnCount();
		int clows = table.getItemCount();
		
		for (int i = 0; i < clows; i++) {
			for (int j = 0; j < columns; j++) {
				// 3、创建单元格(Label)对象
				Label label = new Label(j, i, table.getItem(i).getText(j));// 第一个参数指定单元格的列数、第二个参数指定单元格的行数，第三个指定写的字符串内容
				firstSheet.addCell(label);
		
			}
		}

		// 4、打开流，开始写文件
		writeBook.write();
		// 5、关闭流
		writeBook.close();
	}


	public static void userinfchanger(String text_fund, String path_userinfo) throws IOException, RowsExceededException, WriteException, BiffException {

		// 1、创建工作簿(WritableWorkbook)对象，打开excel文件，若文件不存在，则创建文件
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_userinfo));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_userinfo),workBook);


		WritableSheet sheet = writeBook.getSheet(0);
		WritableCell cell = sheet.getWritableCell(0,0); 
		CellFormat cf = cell.getCellFormat();
		Label label = new Label(0,1,text_fund);
		label.setCellFormat(cf);
		sheet.addCell(label);
		
		
		
		// 4、打开流，开始写文件
		writeBook.write();
		// 5、关闭流
		writeBook.close();
	}

	
	//添加新的交易记录
	public static void addtrade(String path_trade, Trade trade) throws IOException, RowsExceededException, WriteException, BiffException{
		// TODO Auto-generated method stub
		
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_trade));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_trade),workBook);
		WritableSheet sheet = writeBook.getSheet(0);
		int rows = sheet.getRows();

		
		WritableCell cell_price = sheet.getWritableCell(4,1); 
		CellFormat cf_price = cell_price.getCellFormat();
		
		WritableCell cell_num = sheet.getWritableCell(5,1); 
		CellFormat cf_num = cell_num.getCellFormat();
		
		Label label1 = new Label(0,rows,trade.get_name());
		sheet.addCell(label1);//股票名称
		
		Label label2 = new Label(1,rows,trade.get_code());
		sheet.addCell(label2);//股票代码
		
		Label label3 = new Label(2,rows,trade.get_date());
		sheet.addCell(label3);//日期
		
		Label label4 = new Label(3,rows,trade.get_trade_stytle());
		sheet.addCell(label4);//类型
		
		Label label5 = new Label(4,rows,Double.toString(trade.get_price()));
		label5.setCellFormat(cf_price);
		sheet.addCell(label5);//价格
		
		Label label6 = new Label(5,rows,Integer.toString(trade.get_num()));
		label6.setCellFormat(cf_num);
		sheet.addCell(label6);//数量
		
		
		
		// 4、打开流，开始写文件
		writeBook.write();
		// 5、关闭流
		writeBook.close();
		
	}



	


	/*
*/


}
