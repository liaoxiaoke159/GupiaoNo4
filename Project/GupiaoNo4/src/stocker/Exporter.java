package stocker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import stock.homepage;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Exporter {

	public void creat() {
		// TODO Auto-generated method stub
		
		 try {
			 
			userinfosaver(homepage.table_userinfo,homepage.get_path_userinfo());
			
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
		 
		 try {
			 
			stockownsaver(homepage.stkl.stockslist,homepage.get_path_chicang());
			
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
		 
		 
	}

	
	//����ֲ���Ϣ
	public static void stockownsaver(List<Stocks> stockslist, String path_chicang) throws IOException, RowsExceededException, WriteException, BiffException{
		// TODO Auto-generated method stub
		
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_chicang));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_chicang),workBook);
		WritableSheet sheet = writeBook.getSheet(0);
		
		//Label label1 = new Label(0,1,table_info.getItem(1).getText(0));
		//sheet.addCell(label1);//��ʼ�ʽ�
		
		String[] information = new String[31];

		int num = stockslist.size();
		for(int i =0; i<num; i++){
			
		
		try {
			 
			information =Internet.share.Internet.getSharedata(stockslist.get(i).getplace(),
				stockslist.get(i).getSocketcode()); 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "����ʧ�ܣ���������", "�쳣", JOptionPane.ERROR_MESSAGE);
			return;
			
		}
		  //ץȡ�ù�Ʊ��Ϣ

		Label label1 = new Label(0,i,stockslist.get(i).getSocketname());
		sheet.addCell(label1);//��Ʊ����
		Label label2 = new Label(1,i,stockslist.get(i).getSocketcode());
		sheet.addCell(label2);//��Ʊ����
		Label label3 = new Label(2,i,stockslist.get(i).getplace());
		sheet.addCell(label3);////������
		Label label4 = new Label(3,i,information[3]);
		sheet.addCell(label4);////��ǰ��
		
		double nowprice = Double.parseDouble(information[3]);
		double price = stockslist.get(i).getcostprice();
		double rate = (nowprice - price)/price;
		
		//�ٷ�����ʽ������λС��
		NumberFormat nf_precent = NumberFormat.getPercentInstance();
		nf_precent.setMinimumFractionDigits(2);
		//double��ʽ������λС��
		NumberFormat nf_price = NumberFormat.getNumberInstance();
		nf_price.setMinimumFractionDigits(2);
		
		Label label5 = new Label(4,i,nf_price.format(nowprice-price));
		sheet.addCell(label5);////�ǵ�
		Label label6 = new Label(5,i,Integer.toString(stockslist.get(i).getNum()));
		sheet.addCell(label6);////������
		Label label7 = new Label(6,i,Double.toString(nowprice*(double)stockslist.get(i).getNum()));
		sheet.addCell(label7);////������ֵ
		Label label8 = new Label(7,i,Double.toString(stockslist.get(i).getcostprice()));
		sheet.addCell(label8);////�ɱ���
		Label label9 = new Label(8,i,nf_precent.format(rate));
		sheet.addCell(label9);////����ӯ������
		Label label10 = new Label(9,i,nf_price.format((nowprice-price)*(double)stockslist.get(i).getNum()));
		sheet.addCell(label10);////����ӯ��
		
		}
		
		// 4����������ʼд�ļ�
		writeBook.write();
		// 5���ر���
		writeBook.close();
	}

	//�����û���Ϣ
	public static void userinfosaver(Table table_info, String path_userinfo) throws IOException, RowsExceededException, WriteException, BiffException{
		// TODO Auto-generated method stub
		
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_userinfo));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_userinfo),workBook);
		WritableSheet sheet = writeBook.getSheet(0);
		
		
		Label label1 = new Label(0,1,table_info.getItem(1).getText(0));
		sheet.addCell(label1);//��ʼ�ʽ�
		Label label2 = new Label(1,1,table_info.getItem(1).getText(1));
		sheet.addCell(label2);//�����ʽ�
		Label label3 = new Label(2,1,table_info.getItem(1).getText(2));
		sheet.addCell(label3);//�ʲ���ֵ
		Label label4 = new Label(3,1,table_info.getItem(1).getText(3));
		sheet.addCell(label4);//���й�Ʊ��
		Label label5 = new Label(4,1,table_info.getItem(1).getText(4));
		sheet.addCell(label5);//ӯ����
		
		// 4����������ʼд�ļ�
		writeBook.write();
		// 5���ر���
		writeBook.close();
	}

}
