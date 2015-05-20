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
	
	

/****************************���벿��**************************************************/
/****************************���벿��**************************************************/
/****************************���벿��**************************************************/

	public void update_buy() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
	
				
				
				//����󴴽�һ��������
				Trade trade_buy = new Trade();
				trade_buy = newtrade_buy();
				
				// �������½��׼�¼excel��
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
				
				//���򲻿ɱ�
				//��������ӳ��й�Ʊ��Stocks
				homepage.stkl.addStock(trade_buy);
				
				
				
				// ���������û���Ϣtable_info
				userinfochange_buy();
				
				//�������³ֲ�table��
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
		
		if(num<=numlitmit){trade_buy.set_trade_stytle("����");}
		else{trade_buy.set_trade_stytle("����");}
		
		trade_buy.set_price(Double.parseDouble(Dia_buy.getprice().getText()));
		trade_buy.set_num(Integer.parseInt(Dia_buy.getnum().getText()));
		trade_buy.set_place(Dia_buy.place);
		
		return trade_buy;
	}

	private void userinfochange_buy() {
		// TODO Auto-generated method stub
		
		double d1 = Double.parseDouble(
				homepage.get_table_userinfo().getItem(1).getText(1));//���table��Ŀ����ʽ�
		
		double d2 = Double.parseDouble(Dia_buy.getprice().getText());//ί�м۸�
		double d3 = Double.parseDouble(Dia_buy.getnum().getText());//ί������
		
		//�޸Ŀ����ʽ�
		homepage.get_table_userinfo().getItem(1).setText(1, Double.toString(d1-d2*d3));
		
		
		
		//�����û���Ϣtable��ķ������޸��ʲ���ֵ�����й�Ʊ����ӯ����
		Importer.userinfo_table_change(homepage.table_userinfo,homepage.stkl.stockslist);
		
	}


/****************************��������**************************************************/
/****************************��������**************************************************/
/****************************��������**************************************************/


	public void update_sell() {
		// TODO Auto-generated method stub
		//System.out.println("ss");
		
		//����󴴽�һ��������
		Trade trade_sell = new Trade();
		trade_sell = newtrade_sell();
		
		// �������½��׼�¼excel��
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
		
		//���������stockslist
		stockslistchangge_sell(homepage.table_chicang,homepage.tableindex,homepage.stkl.stockslist,Dia_sell.text_number.getText());
		
		//����������û���Ϣtable_info
		userinfochange_sell(Dia_sell.text_price.getText(),Dia_sell.text_number.getText()
				,homepage.table_userinfo,homepage.stkl.stockslist);
		
		//���³ֲ�excel��
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
		
		//ˢ�¹�Ʊ�ֲ�table��
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
				homepage.get_table_userinfo().getItem(1).getText(1));//���table��Ŀ����ʽ�
		if(d1>0){trade_sell.set_trade_stytle("����");}
		else{trade_sell.set_trade_stytle("����");}
		
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
		
		// ���ȫ��������ɾ���ֲ�excel���е�Ԫ��
		if (Integer.parseInt(number) == Integer
				.parseInt(table_chicang.getItem(tableindex)
						.getText(5))) {

			sheet.removeRow(tableindex);
		} else {
			
			int lessnum =stockslist.get(tableindex).getNum()- Integer.parseInt(number) ;
			Label label = new Label(5,tableindex,Integer.toString(lessnum));
			sheet.addCell(label);//�޸�����


		}
		// 4����������ʼд�ļ�
		writeBook.write();
		// 5���ر���
		writeBook.close();
	}



	private void stockslistchangge_sell(Table table_chicang, int tableindex, List<Stocks> stockslist, String sell_num) {
		// TODO Auto-generated method stub
		
		// ���ȫ��������ɾ����stockslist�е�Ԫ��
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
				table_userinfo.getItem(1).getText(1));//���table��Ŀ����ʽ�
		
		double d2 = Double.parseDouble(price);//ί�м۸�
		double d3 = Double.parseDouble(number);//ί������
		
		//�޸Ŀ����ʽ�
		table_userinfo.getItem(1).setText(1, Double.toString(d1+d2*d3));
		
//		int d4= Integer.parseInt(
//				table_userinfo.getItem(1).getText(3));//��ó��й�Ʊ��
		
		//�����û���Ϣtable��ķ������޸��ʲ���ֵ�����й�Ʊ����ӯ����
		Importer.userinfo_table_change(table_userinfo,stockslist);
	}

}
