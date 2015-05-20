package stocker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;



import java.io.ObjectInputStream.GetField;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
	


	public static void userinfchanger(String text_fund, String path_userinfo) throws IOException, RowsExceededException, WriteException, BiffException {

		// 1������������(WritableWorkbook)���󣬴�excel�ļ������ļ������ڣ��򴴽��ļ�
		/*Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_userinfo));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_userinfo),workBook);


		WritableSheet sheet = writeBook.getSheet(0);
		WritableCell cell = sheet.getWritableCell(0,0); 
		CellFormat cf = cell.getCellFormat();
		Label label = new Label(0,1,text_fund);
		label.setCellFormat(cf);
		sheet.addCell(label);*/
		
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_userinfo));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_userinfo),workBook);
		WritableSheet sheet = writeBook.getSheet(0);
		
		Label label1 = new Label(0,1,text_fund);
		sheet.addCell(label1);
		
		
		// 4����������ʼд�ļ�
		writeBook.write();
		// 5���ر���
		writeBook.close();
	}

	
	
	//���½��׼�¼excel��
	public static void addtrade(String path_trade, Trade trade) throws IOException, RowsExceededException, WriteException, BiffException{
		// TODO Auto-generated method stub
		
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_trade));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_trade),workBook);
		WritableSheet sheet = writeBook.getSheet(0);
		int rows = sheet.getRows();

//		
//		WritableCell cell_price = sheet.getWritableCell(3,1); 
//		CellFormat cf_price = cell_price.getCellFormat();
//		
//		WritableCell cell_rate = sheet.getWritableCell(4,1); 
//		CellFormat cf_rate = cell_rate.getCellFormat();
		
		Label label1 = new Label(0,rows,trade.get_name());
		sheet.addCell(label1);//��Ʊ����
		
		Label label2 = new Label(1,rows,trade.get_code());
		sheet.addCell(label2);//��Ʊ����
		
		Label label3 = new Label(2,rows,trade.get_date());
		sheet.addCell(label3);//����
		
		Label label4 = new Label(3,rows,trade.get_trade_stytle());
		sheet.addCell(label4);//����
		
		Label label5 = new Label(4,rows,Double.toString(trade.get_price()));
		//label5.setCellFormat(cf_price);
		sheet.addCell(label5);//�۸�
		
		Label label6 = new Label(5,rows,Integer.toString(trade.get_num()));
		//label6.setCellFormat(cf_rate);
		sheet.addCell(label6);//����
		
		Label label7 = new Label(6,rows,trade.get_place());
		sheet.addCell(label7);//������
		
		
		
		// 4����������ʼд�ļ�
		writeBook.write();
		// 5���ر���
		writeBook.close();
		
	}


	
	//���³ֲ�excel��
	public static void Excel_chicang_update(List<Stocks> stocksList,
			String path_chicang) throws Exception{
		// TODO Auto-generated method stub
		
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_chicang));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_chicang),workBook);
		WritableSheet sheet = writeBook.getSheet(0);
		int stocksnum = stocksList.size();
	
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);//���ñ���С��λ

		 String[] information = new String[31];
		for(int i = 1;i<stocksnum+1;i++){
			
			try {
				 
				information =Internet.share.Internet.getSharedata(stocksList.get(i).getplace(),
					stocksList.get(i).getSocketcode()); 

			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "��������", "�쳣", JOptionPane.ERROR_MESSAGE);
			}
			  //ץȡ�ù�Ʊ��Ϣ
			   
			     
				
				Label label1 = new Label(0,i,stocksList.get(i).getSocketname());
				sheet.addCell(label1);//��Ʊ����
				Label label2 = new Label(1,i,stocksList.get(i).getSocketcode());
				sheet.addCell(label2);//��Ʊ����
				Label label3 = new Label(2,i,stocksList.get(i).getplace());
				sheet.addCell(label3);//������
				


				Label label4 = new Label(3,i,information[3]);
				sheet.addCell(label4);//��ǰ��
				
				double nowprice = Double.parseDouble(information[3]);//��ǰ��
				double yesterprice = Double.parseDouble(information[2]);//���ռ�
				double rateprice;
				if(nowprice==yesterprice){rateprice = 0.00;}
				else{rateprice = (nowprice-yesterprice)/yesterprice;}
				
				Label label5 = new Label(4,i,nf.format(rateprice));
				sheet.addCell(label5);//�ǵ�
				
				Label label6 = new Label(5,i,Integer.toString(stocksList.get(i).getNum()));
				sheet.addCell(label6);//������
				
				double sumprice = nowprice*stocksList.get(i).getNum();
				Label label7 = new Label(6,i,Double.toString(sumprice));
				sheet.addCell(label7);//������ֵ
				
				Label label8 = new Label(7,i,Double.toString(stocksList.get(i).getcostprice()));
				sheet.addCell(label8);//�ɱ���
				
				Label label9 = new Label(8,i,"0.00(0.00%)");
				sheet.addCell(label9);//����ӯ��
				
				double costprice = stocksList.get(i).getcostprice()*stocksList.get(i).getNum();//�ֲֳɱ����������ѣ�
				double returnprice = sumprice - costprice;//ӯ��
				double returnrate = returnprice/costprice;//ӯ����
				Label label10 = new Label(8,i,Double.toString(returnprice)+"("+nf.format(returnrate)+")");
				sheet.addCell(label10);//����ӯ��
			
		}
		
		// 4����������ʼд�ļ�
		writeBook.write();
		// 5���ر���
		writeBook.close();
	}



	//�ѽ��׼�¼����tradelist������
	public static List<Trade> tradelistmaker(String path_trade) throws BiffException, IOException {
		// TODO Auto-generated method stub
		InputStream is = new FileInputStream(path_trade);

		Workbook rwb = Workbook.getWorkbook(is);
		rwb.getNumberOfSheets();

		Sheet sheet = rwb.getSheet(0);
		int rows = sheet.getRows();
		List<Trade> tradelist = new ArrayList<Trade>();
		
		for(int i=1; i<rows; i++){
			Trade trade = new Trade();//trade�Ķ������Ž�ѭ������
			trade.set_name(sheet.getCell(0, i).getContents());
			trade.set_code(sheet.getCell(1, i).getContents());
			trade.set_date(sheet.getCell(2, i).getContents());
			trade.set_trade_stytle(sheet.getCell(3, i).getContents());
			trade.set_price(Double.parseDouble(sheet.getCell(4, i).getContents()));
			trade.set_num(Integer.parseInt(sheet.getCell(5, i).getContents()));
			trade.set_place(sheet.getCell(6, i).getContents());
			
			tradelist.add(trade);
		}	
		
		return tradelist;
		
	}


   //����tradelist���ؽ���stocktradelist
	public static List<StockTrade> stockreturnratemaker(List<Trade> tradelist) {
		
		if(tradelist.isEmpty()){return null;}
		
		List<StockTrade> stocktradelist = new ArrayList<StockTrade>();
		

		int biaoji = 0;		
		for (int i = 0; i < tradelist.size(); i++) {

			for (int j = 0; j < stocktradelist.size(); j++) {
				
				if (tradelist.get(i).get_name().equals(stocktradelist.get(j).getname())
						& tradelist.get(i).get_code().equals(stocktradelist.get(j).getcode())) {

					biaoji = 1;
					
					//����������¼
					if(tradelist.get(i).get_trade_stytle().equals("����")
							||tradelist.get(i).get_trade_stytle().equals("����")){
						
						stocktradelist.get(j).setcostprice(tradelist.get(i).get_price());
						stocktradelist.get(j).setnum_buy(tradelist.get(i).get_num());
						stocktradelist.get(j).adddate(tradelist.get(j).get_date());
						stocktradelist.get(j).addrate();
					}
					else{
						stocktradelist.get(j).setsell(tradelist.get(i).get_num(), tradelist.get(i).get_price());
						stocktradelist.get(j).adddate(tradelist.get(i).get_date());
						stocktradelist.get(j).addrate();
					}
					
					
					break;
					
				}
				
				biaoji =0;
			}
			
			if(biaoji==0){
				
			    StockTrade stocktrade = new StockTrade(tradelist.get(i).get_name(),tradelist.get(i).get_code()
			    		,tradelist.get(i).get_price(),tradelist.get(i).get_num(),tradelist.get(i).get_date());
				
				stocktrade.addrate();
				stocktradelist.add(stocktrade);
			}

		}
		return stocktradelist;
		
	}



	
}
