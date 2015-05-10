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
		// 1������excel�ļ�����������

		InputStream is = new FileInputStream(path_file);

		// 2����������������
		Workbook rwb = Workbook.getWorkbook(is);
		// 3����ù������ĸ���,��Ӧ��һ��excel�еĹ��������
		rwb.getNumberOfSheets();

		Sheet oFirstSheet = rwb.getSheet(0);// ʹ��������ʽ��ȡ��һ��������Ҳ����ʹ��rwb.getSheet(sheetName);����sheetName��ʾ���ǹ����������
		// System.out.println("���������ƣ�" + oFirstSheet.getName());
		int rows = oFirstSheet.getRows();// ��ȡ�������е�������
		int columns = oFirstSheet.getColumns();// ��ȡ�������е�������
		String[] itemdata = new String[columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell oCell = oFirstSheet.getCell(j, i);// ��Ҫע����������getCell�����Ĳ�������һ����ָ���ڼ��У��ڶ�����������ָ���ڼ���
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

		// 1������������(WritableWorkbook)���󣬴�excel�ļ������ļ������ڣ��򴴽��ļ�
		WritableWorkbook writeBook = Workbook
				.createWorkbook(new File(path_file));

		// 2���½�������(sheet)���󣬲����������ڵڼ�ҳ
		WritableSheet firstSheet = writeBook.createSheet("��һ��������", 1);// ��һ������Ϊ�����������ƣ��ڶ�������Ϊҳ��

		int columns = table.getColumnCount();
		int clows = table.getItemCount();
		
		for (int i = 0; i < clows; i++) {
			for (int j = 0; j < columns; j++) {
				// 3��������Ԫ��(Label)����
				Label label = new Label(j, i, table.getItem(i).getText(j));// ��һ������ָ����Ԫ����������ڶ�������ָ����Ԫ���������������ָ��д���ַ�������
				firstSheet.addCell(label);
		
			}
		}

		// 4����������ʼд�ļ�
		writeBook.write();
		// 5���ر���
		writeBook.close();
	}


	public static void userinfchanger(String text_fund, String path_userinfo) throws IOException, RowsExceededException, WriteException, BiffException {

		// 1������������(WritableWorkbook)���󣬴�excel�ļ������ļ������ڣ��򴴽��ļ�
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_userinfo));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_userinfo),workBook);


		WritableSheet sheet = writeBook.getSheet(0);
		WritableCell cell = sheet.getWritableCell(0,0); 
		CellFormat cf = cell.getCellFormat();
		Label label = new Label(0,1,text_fund);
		label.setCellFormat(cf);
		sheet.addCell(label);
		
		
		
		// 4����������ʼд�ļ�
		writeBook.write();
		// 5���ر���
		writeBook.close();
	}

	
	//����µĽ��׼�¼
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
		sheet.addCell(label1);//��Ʊ����
		
		Label label2 = new Label(1,rows,trade.get_code());
		sheet.addCell(label2);//��Ʊ����
		
		Label label3 = new Label(2,rows,trade.get_date());
		sheet.addCell(label3);//����
		
		Label label4 = new Label(3,rows,trade.get_trade_stytle());
		sheet.addCell(label4);//����
		
		Label label5 = new Label(4,rows,Double.toString(trade.get_price()));
		label5.setCellFormat(cf_price);
		sheet.addCell(label5);//�۸�
		
		Label label6 = new Label(5,rows,Integer.toString(trade.get_num()));
		label6.setCellFormat(cf_num);
		sheet.addCell(label6);//����
		
		
		
		// 4����������ʼд�ļ�
		writeBook.write();
		// 5���ر���
		writeBook.close();
		
	}



	


	/*
*/


}
