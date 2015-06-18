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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import stock.Dia_buy;
import stock.Dia_cover;
import stock.Dia_sell;
import stock.Dia_shortsell;
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
				trade_buy = newtrade(Dia_buy.information[0].substring(21),Dia_buy.getstockcode().getText()
						,"����",Dia_buy.getprice().getText(),Integer.parseInt(Dia_buy.getnum().getText()),Dia_buy.place);
				
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
				
				
				//���������û���Ϣtable��
				userinfochange(Dia_buy.text_price.getText(),Dia_buy.text_number.getText()
						,homepage.table_userinfo,homepage.stkl.stockslist,0);
				
				//�������³ֲ�table��
				Importer.table_chiang_initial(homepage.stkl.stockslist,homepage.table_chicang);
				
				//���������ʺͳֹɹ���
				Importer.chiguANDshouyilv();
			
		
	}
	
	private void userinfochange(String price, String number, 
			Table table_userinfo, List<Stocks> stockslist,int TAG) {
		// TODO Auto-generated method stub
		
		double d1 = Double.parseDouble(
				table_userinfo.getItem(1).getText(1));//���table��Ŀ����ʽ�
		
		double d2 = Double.parseDouble(price);//ί�м۸�
		double d3 = Double.parseDouble(number);//ί������
		
		if(TAG==0){
			//������������������ղ���
			table_userinfo.getItem(1).setText(1, Double.toString(d1+d2*d3));
			}
		else{
			//���������������߲��ֲ���
			table_userinfo.getItem(1).setText(1, Double.toString(d1-d2*d3));
		}
		
		
		
		//�����û���Ϣtable��ķ������޸��ʲ���ֵ�����й�Ʊ����ӯ����
		Importer.userinfo_table_change(table_userinfo,stockslist);
	}
	
	
	private Trade newtrade(String name, String code, String style, 
			String price, int num, String place) {
		// TODO Auto-generated method stub
		Trade trade_buy = new Trade();
		
		trade_buy.set_name(name);
		trade_buy.set_code(code);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(new Date());
        
		trade_buy.set_date(date);
		trade_buy.set_trade_stytle(style);	
		trade_buy.set_price(Double.parseDouble(price));
		trade_buy.set_num(num);
		trade_buy.set_place(place);
		
		return trade_buy;
	}

/****************************��������**************************************************/
/****************************��������**************************************************/
/****************************��������**************************************************/


	public void update_sell() {
		// TODO Auto-generated method stub
		//System.out.println("ss");
		
		//����󴴽�һ��������
		Trade trade_sell = new Trade();
		trade_sell = newtrade(Dia_sell.information_sell[0].substring(21),Dia_sell.text_sharecode.getText()
				,"����",Dia_sell.text_price.getText(),-Integer.parseInt(Dia_sell.text_number.getText()),Dia_sell.place);
		
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
		userinfochange(Dia_sell.text_price.getText(),Dia_sell.text_number.getText()
				,homepage.table_userinfo,homepage.stkl.stockslist,1);
		
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
		
		//���������ʺͳֹɹ���
		Importer.chiguANDshouyilv();
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
		if (Integer.parseInt(sell_num) ==Integer.parseInt(table_chicang
				.getItem(tableindex).getText(5))) {

			stockslist.remove(tableindex);
		} else {

			stockslist.get(tableindex).setNum(
					stockslist.get(tableindex).getNum()
							- Integer.parseInt(sell_num));

		}
	}



	

/****************************���ղ���**************************************************/
/****************************���ղ���**************************************************/
/****************************���ղ���**************************************************/
	public void update_shortsell() {
		// TODO Auto-generated method stub
		//���պ󴴽�һ��������
				Trade trade_shortsell = new Trade();
				trade_shortsell =  newtrade(Dia_shortsell.information[0].substring(21),Dia_shortsell.text_sharecode.getText()
						,"����",Dia_shortsell.text_price.getText(),-Integer.parseInt(Dia_shortsell.text_number.getText()),Dia_shortsell.place);
				
				// ���պ���½��׼�¼excel��
				try {
					DataBuilder.addtrade(homepage.get_path_trade(), trade_shortsell);
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
				
				//���պ����stockslist
				homepage.stkl.addStock(trade_shortsell);
				
				//���պ�����û���Ϣtable_info
				userinfochange(Dia_shortsell.text_price.getText(),Dia_shortsell.text_number.getText()
						,homepage.table_userinfo,homepage.stkl.stockslist,0);
				
				
				//ˢ�¹�Ʊ�ֲ�table��
				Importer.table_chiang_initial(homepage.stkl.stockslist,homepage.table_chicang);
				
				//���������ʺͳֹɹ���
				Importer.chiguANDshouyilv();
	}



/****************************���ֲ���**************************************************/
/****************************���ֲ���**************************************************/
/****************************���ֲ���**************************************************/
	public void update_cover() {
		// TODO Auto-generated method stub
		// ���ֺ󴴽�һ��������
		Trade trade_cover = new Trade();
		trade_cover = newtrade(Dia_cover.information[0].substring(21),
				Dia_cover.text_sharecode.getText(), "����",
				Dia_cover.text_price.getText(),
				Integer.parseInt(Dia_cover.text_number.getText()), Dia_cover.place);

		// ���ֺ���½��׼�¼excel��
		try {
			DataBuilder.addtrade(homepage.get_path_trade(), trade_cover);
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

		// ���ֺ����ӳ��й�Ʊ��Stocks
		homepage.stkl.addStock(trade_cover);

		// ���ֺ�����û���Ϣtable_info
		userinfochange(Dia_cover.text_price.getText(),
				Dia_cover.text_number.getText(), homepage.table_userinfo,
				homepage.stkl.stockslist, 0);

		// �������³ֲ�table��
		Importer.table_chiang_initial(homepage.stkl.stockslist,
				homepage.table_chicang);

		//���������ʺͳֹɹ���
		Importer.chiguANDshouyilv();
	}

}
