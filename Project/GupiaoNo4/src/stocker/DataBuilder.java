package stocker;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;





public class DataBuilder {






	
	
	/**
	 * ���½��׼�¼excel��
	 * @param path_trade
	 * @param trade
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws BiffException
	 */
	public  boolean addtrade(String path_trade, Trade trade) throws IOException, RowsExceededException, WriteException, BiffException{
		// TODO Auto-generated method stub
		
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_trade));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_trade),workBook);
		WritableSheet sheet = writeBook.getSheet(0);
		int rows = sheet.getRows();


		//�ҳ��������ڶ�Ӧ��һ�� ���������������е����һ�У�
		int excelindex;
		String[] date_trade = trade.get_date().split("/");
		int loop = 2;
		for(; loop <rows;++loop){
			String[] date_excel = sheet.getCell(2,loop).getContents().split("/");
			if (Integer.parseInt(date_trade[0]) > Integer.parseInt(date_excel[0])) {
				continue;
			} else {
				if (Integer.parseInt(date_trade[1]) > Integer.parseInt(date_excel[1])) {
					continue;
				} else {
					if (Integer.parseInt(date_trade[2]) >= Integer.parseInt(date_excel[2])) {
						continue;
					} else break;
						
				}
			}

			}
		
			excelindex = loop;
			
		
		//�ж����������Ƿ���ڸ�����֮ǰ������������  ���������Ƿ���ڸ�����֮ǰ���ܿɲ�����
			if(trade.get_trade_stytle().equals("����")||trade.get_trade_stytle().equals("����")){
				int num = 0;
				 int  TAG = -1;
				for(int i = 2; i <excelindex;i++){
				if(trade.get_trade_stytle().equals("����")&&sheet.getCell(0, i).getContents().equals(trade.get_name())
						&&(sheet.getCell(3, i).getContents().equals("����")||sheet.getCell(3, i).getContents().equals("����"))
						&&sheet.getCell(1, i).equals(trade.get_code())
						&&sheet.getCell(6, i).equals(trade.get_place())){
					num+=Integer.parseInt((sheet.getCell(5, i).getContents()));
					TAG = 1;
				}
				if(trade.get_trade_stytle().equals("����")&&sheet.getCell(0, i).getContents().equals(trade.get_name())
						&&(sheet.getCell(3, i).getContents().equals("����")||sheet.getCell(3, i).getContents().equals("����"))
						&&sheet.getCell(1, i).equals(trade.get_code())
						&&sheet.getCell(6, i).equals(trade.get_place())){
					num+=Integer.parseInt((sheet.getCell(5, i).getContents()));
					TAG = 1;
				}
				
			  }
				if (TAG == 1) {
					if (Math.abs(trade.get_num()) > Math.abs(num)) {

						writeBook.write();
						writeBook.close();
						return false;
					}
					
				}
			}
		
		
		//��������һ�е���һ��
		if(excelindex==rows){
			Label label1 = new Label(0,excelindex,trade.get_name());
			sheet.addCell(label1);//��Ʊ����
			
			Label label2 = new Label(1,excelindex,trade.get_code());
			sheet.addCell(label2);//��Ʊ����
			
			Label label3 = new Label(2,excelindex,trade.get_date());
			sheet.addCell(label3);//����
			
			Label label4 = new Label(3,excelindex,trade.get_trade_stytle());
			sheet.addCell(label4);//����
			
			Label label5 = new Label(4,excelindex,Double.toString(trade.get_price()));
			//label5.setCellFormat(cf_price);
			sheet.addCell(label5);//�۸�
			
			Label label6 = new Label(5,excelindex,Integer.toString(trade.get_num()));
			//label6.setCellFormat(cf_rate);
			sheet.addCell(label6);//����
			
			Label label7 = new Label(6,excelindex,trade.get_place());
		    sheet.addCell(label7);//������
		}
		
		if (excelindex < rows){
			for (int excelLoop = excelindex; excelLoop < rows + 1; excelLoop++) {
				
				Trade tradetemp = null;
				// ���浱ǰ��
				//�������в��������һ��
				if(excelLoop<rows){
					tradetemp = new Trade(sheet.getCell(0, excelLoop).getContents(), 
						sheet.getCell(1, excelLoop).getContents(), 
						sheet.getCell(2, excelLoop).getContents(), 
						sheet.getCell(3, excelLoop).getContents(), 
						Double.parseDouble(sheet.getCell(4,excelLoop).getContents()), 
						Integer.parseInt(sheet.getCell(5, excelLoop).getContents()), 
						sheet.getCell(6,excelLoop).getContents());
				}
				

				Label label1 = new Label(0, excelLoop, trade.get_name());
				sheet.addCell(label1);// ��Ʊ����

				Label label2 = new Label(1, excelLoop, trade.get_code());
				sheet.addCell(label2);// ��Ʊ����

				Label label3 = new Label(2, excelLoop, trade.get_date());
				sheet.addCell(label3);// ����

				Label label4 = new Label(3, excelLoop, trade.get_trade_stytle());
				sheet.addCell(label4);// ����

				Label label5 = new Label(4, excelLoop, Double.toString(trade
						.get_price()));
				// label5.setCellFormat(cf_price);
				sheet.addCell(label5);// �۸�

				Label label6 = new Label(5, excelLoop, Integer.toString(trade
						.get_num()));
				// label6.setCellFormat(cf_rate);
				sheet.addCell(label6);// ����

				Label label7 = new Label(6, excelLoop, trade.get_place());
				sheet.addCell(label7);// ������

				trade = tradetemp;

			}

		}
		
		
		writeBook.write();
		writeBook.close();
		
		return true;
		
	}



	/**
	 * �ѽ��׼�¼����tradelist������
	 * @param path_trade
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public List<Trade> tradelistmaker(String path_trade) throws BiffException, IOException {
		// TODO Auto-generated method stub
		InputStream is = new FileInputStream(path_trade);

		Workbook rwb = Workbook.getWorkbook(is);
		rwb.getNumberOfSheets();

		Sheet sheet = rwb.getSheet(0);
		int rows = sheet.getRows();
		List<Trade> tradelist = new ArrayList<Trade>();
		
		for(int i=2; i<rows; i++){
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


	/**
	 * �����Ʊ������
	 * @param tradelist
	 * @return
	 */
	
	public  Sumreturnrate Sumreturnratemaker(List<Trade> tradelist){
		
		Sumreturnrate sumreturnrate = new Sumreturnrate();
		
		int tradelength = tradelist.size();//��Ʊ��¼����
		
		for(int loop = 0;loop < tradelength;loop++){
			
			//�����������߲��ּ�¼
			if(tradelist.get(loop).get_trade_stytle().equals("����")
					||tradelist.get(loop).get_trade_stytle().equals("����")){
				
				sumreturnrate.addSumprice_in(tradelist.get(loop).get_price(),
						tradelist.get(loop).get_num());
				sumreturnrate = ramainstockdealer(sumreturnrate,loop,tradelist);
				sumreturnrate.addrate(tradelist.get(loop).get_date());
			}
			
			//����������������ռ�¼
			if(tradelist.get(loop).get_trade_stytle().equals("����")
					||tradelist.get(loop).get_trade_stytle().equals("����")){
				
				sumreturnrate.addSumprice_out(tradelist.get(loop).get_price(),
						-tradelist.get(loop).get_num());
				ramainstockdealer(sumreturnrate,loop,tradelist);
				sumreturnrate.addrate(tradelist.get(loop).get_date());
			}
		}
		return sumreturnrate;
		
	}

public   Sumreturnrate ramainstockdealer(Sumreturnrate sumreturnrate, int loop, List<Trade> tradelist) {
	// TODO Auto-generated method stub
	

	
	int TAG = 0;//����Ƿ��ҵ���ͬ��Ʊ  0û���ҵ� 1�ҵ�
	for(int remainloop =0; remainloop<sumreturnrate.remainstocklist.size();remainloop++){
		
		
		if((tradelist.get(loop).get_trade_stytle().equals("����")||tradelist.get(loop).get_trade_stytle().equals("����"))
				&&sumreturnrate.remainstocklist.get(remainloop).getCode().equals(tradelist.get(loop).get_code())
				&&sumreturnrate.remainstocklist.get(remainloop).getPlace().equals(tradelist.get(loop).get_place())
				&&((sumreturnrate.remainstocklist.get(remainloop).getNumber())^(tradelist.get(loop).get_num()))>0){
			    
			    int sumnum = sumreturnrate.remainstocklist.get(remainloop).getNumber()+tradelist.get(loop).get_num();
			     sumreturnrate.remainstocklist.get(remainloop).setNumber(sumnum);
			     TAG = 1;
			     break;
		       }
		else if((tradelist.get(loop).get_trade_stytle().equals("����")||tradelist.get(loop).get_trade_stytle().equals("����"))
				&&sumreturnrate.remainstocklist.get(remainloop).getCode().equals(tradelist.get(loop).get_code())
				&&sumreturnrate.remainstocklist.get(remainloop).getPlace().equals(tradelist.get(loop).get_place())
				&&((sumreturnrate.remainstocklist.get(remainloop).getNumber())^(tradelist.get(loop).get_num()))<0){
			    
			    int sumnum = sumreturnrate.remainstocklist.get(remainloop).getNumber()+tradelist.get(loop).get_num();
			     sumreturnrate.remainstocklist.get(remainloop).setNumber(sumnum);
			     TAG = 1;
			     break;
		       }
	}
	
	//���û���ҵ���ͬ�Ĺ�Ʊ
	if(TAG == 0){
	Remainstock remainstock = new Remainstock(tradelist.get(loop).get_name(),tradelist.get(loop).get_code(),
					tradelist.get(loop).get_place(),tradelist.get(loop).get_num());
			sumreturnrate.remainstocklist.add(remainstock);
	}
	
	return sumreturnrate;
}

/**
 * ����ֹɹ���
 * @param tradelist
 * @return
 */
	public  List<HistoryStockown> HistoryStockownmaker(List<Trade> tradelist) {

		List<HistoryStockown> HSOL = new ArrayList<HistoryStockown>();//����һ����ʷ��Ʊ������
		HistoryStockown HSO ;//��ʷ��Ʊ��
		
		//����ÿ����¼
		for (int tradeloop = 0; tradeloop < tradelist.size(); tradeloop++) {
		
			int TAG = 0;//����Ƿ��ҵ���ͬ��Ʊ  0��ʾû���ҵ� 1��ʾ�ҵ�
			for(int stockownloop =0; stockownloop < HSOL.size();stockownloop++){
				if( (tradelist.get(tradeloop).get_trade_stytle().equals("����")|| tradelist.get(tradeloop).get_trade_stytle().equals("����"))
						&&tradelist.get(tradeloop).get_name().equals(HSOL.get(stockownloop).name)
				  &&tradelist.get(tradeloop).get_code().equals(HSOL.get(stockownloop).code)
				  &&tradelist.get(tradeloop).get_place().equals(HSOL.get(stockownloop).place)
				 &&HSOL.get(stockownloop).number.get(HSOL.get(stockownloop).number.size()-1)<=0){
					
				HSOL.get(stockownloop).addnumber_date(tradelist.get(tradeloop).get_num(), tradelist.get(tradeloop).get_date());
				TAG = 1;
				break;
				}
				else if( (tradelist.get(tradeloop).get_trade_stytle().equals("����")|| tradelist.get(tradeloop).get_trade_stytle().equals("����"))
						&&tradelist.get(tradeloop).get_name().equals(HSOL.get(stockownloop).name)
				  &&tradelist.get(tradeloop).get_code().equals(HSOL.get(stockownloop).code)
				  &&tradelist.get(tradeloop).get_place().equals(HSOL.get(stockownloop).place)
				 &&HSOL.get(stockownloop).number.get(HSOL.get(stockownloop).number.size()-1)>=0){
					
				HSOL.get(stockownloop).addnumber_date(tradelist.get(tradeloop).get_num(), tradelist.get(tradeloop).get_date());
				TAG = 1;
				break;
				}
				
				if(TAG == 0){
					HSOL.get(stockownloop).number_date(HSOL.get(stockownloop).number.get(HSOL.get(stockownloop).number.size()-1),tradelist.get(tradeloop).get_date());
				}
			}
			if(TAG == 0){
				HSO = new HistoryStockown(tradelist.get(tradeloop).get_name(),tradelist.get(tradeloop).get_code(),
						tradelist.get(tradeloop).get_place());
				HSO.addnumber_date(tradelist.get(tradeloop).get_num(), tradelist.get(tradeloop).get_date());
				HSOL.add(HSO);
			}
		}

		return HSOL;

	}

}
