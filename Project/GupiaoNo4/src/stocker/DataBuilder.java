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
	 * 更新交易记录excel表
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


		//找出交易日期对应的一行 （该日期所有行中的最后一行）
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
			
		
		//判断卖出数量是否大于该日期之前的总买入数量  补仓数量是否大于该日期之前的总可补数量
			if(trade.get_trade_stytle().equals("卖出")||trade.get_trade_stytle().equals("补仓")){
				int num = 0;
				 int  TAG = -1;
				for(int i = 2; i <excelindex;i++){
				if(trade.get_trade_stytle().equals("卖出")&&sheet.getCell(0, i).getContents().equals(trade.get_name())
						&&(sheet.getCell(3, i).getContents().equals("买入")||sheet.getCell(3, i).getContents().equals("卖出"))
						&&sheet.getCell(1, i).equals(trade.get_code())
						&&sheet.getCell(6, i).equals(trade.get_place())){
					num+=Integer.parseInt((sheet.getCell(5, i).getContents()));
					TAG = 1;
				}
				if(trade.get_trade_stytle().equals("补仓")&&sheet.getCell(0, i).getContents().equals(trade.get_name())
						&&(sheet.getCell(3, i).getContents().equals("卖空")||sheet.getCell(3, i).getContents().equals("补仓"))
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
		
		
		//如果是最后一行的下一行
		if(excelindex==rows){
			Label label1 = new Label(0,excelindex,trade.get_name());
			sheet.addCell(label1);//股票名称
			
			Label label2 = new Label(1,excelindex,trade.get_code());
			sheet.addCell(label2);//股票代码
			
			Label label3 = new Label(2,excelindex,trade.get_date());
			sheet.addCell(label3);//日期
			
			Label label4 = new Label(3,excelindex,trade.get_trade_stytle());
			sheet.addCell(label4);//类型
			
			Label label5 = new Label(4,excelindex,Double.toString(trade.get_price()));
			//label5.setCellFormat(cf_price);
			sheet.addCell(label5);//价格
			
			Label label6 = new Label(5,excelindex,Integer.toString(trade.get_num()));
			//label6.setCellFormat(cf_rate);
			sheet.addCell(label6);//数量
			
			Label label7 = new Label(6,excelindex,trade.get_place());
		    sheet.addCell(label7);//交易所
		}
		
		if (excelindex < rows){
			for (int excelLoop = excelindex; excelLoop < rows + 1; excelLoop++) {
				
				Trade tradetemp = null;
				// 保存当前行
				//仅当该行不超过最后一行
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
				sheet.addCell(label1);// 股票名称

				Label label2 = new Label(1, excelLoop, trade.get_code());
				sheet.addCell(label2);// 股票代码

				Label label3 = new Label(2, excelLoop, trade.get_date());
				sheet.addCell(label3);// 日期

				Label label4 = new Label(3, excelLoop, trade.get_trade_stytle());
				sheet.addCell(label4);// 类型

				Label label5 = new Label(4, excelLoop, Double.toString(trade
						.get_price()));
				// label5.setCellFormat(cf_price);
				sheet.addCell(label5);// 价格

				Label label6 = new Label(5, excelLoop, Integer.toString(trade
						.get_num()));
				// label6.setCellFormat(cf_rate);
				sheet.addCell(label6);// 数量

				Label label7 = new Label(6, excelLoop, trade.get_place());
				sheet.addCell(label7);// 交易所

				trade = tradetemp;

			}

		}
		
		
		writeBook.write();
		writeBook.close();
		
		return true;
		
	}



	/**
	 * 把交易记录读成tradelist并返回
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
			Trade trade = new Trade();//trade的定义必须放进循环里面
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
	 * 计算股票收益率
	 * @param tradelist
	 * @return
	 */
	
	public  Sumreturnrate Sumreturnratemaker(List<Trade> tradelist){
		
		Sumreturnrate sumreturnrate = new Sumreturnrate();
		
		int tradelength = tradelist.size();//股票记录条数
		
		for(int loop = 0;loop < tradelength;loop++){
			
			//如果是买入或者补仓记录
			if(tradelist.get(loop).get_trade_stytle().equals("买入")
					||tradelist.get(loop).get_trade_stytle().equals("补仓")){
				
				sumreturnrate.addSumprice_in(tradelist.get(loop).get_price(),
						tradelist.get(loop).get_num());
				sumreturnrate = ramainstockdealer(sumreturnrate,loop,tradelist);
				sumreturnrate.addrate(tradelist.get(loop).get_date());
			}
			
			//如果是卖出或者卖空记录
			if(tradelist.get(loop).get_trade_stytle().equals("卖出")
					||tradelist.get(loop).get_trade_stytle().equals("卖空")){
				
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
	

	
	int TAG = 0;//标记是否找到相同股票  0没有找到 1找到
	for(int remainloop =0; remainloop<sumreturnrate.remainstocklist.size();remainloop++){
		
		
		if((tradelist.get(loop).get_trade_stytle().equals("买入")||tradelist.get(loop).get_trade_stytle().equals("买入"))
				&&sumreturnrate.remainstocklist.get(remainloop).getCode().equals(tradelist.get(loop).get_code())
				&&sumreturnrate.remainstocklist.get(remainloop).getPlace().equals(tradelist.get(loop).get_place())
				&&((sumreturnrate.remainstocklist.get(remainloop).getNumber())^(tradelist.get(loop).get_num()))>0){
			    
			    int sumnum = sumreturnrate.remainstocklist.get(remainloop).getNumber()+tradelist.get(loop).get_num();
			     sumreturnrate.remainstocklist.get(remainloop).setNumber(sumnum);
			     TAG = 1;
			     break;
		       }
		else if((tradelist.get(loop).get_trade_stytle().equals("卖出")||tradelist.get(loop).get_trade_stytle().equals("补仓"))
				&&sumreturnrate.remainstocklist.get(remainloop).getCode().equals(tradelist.get(loop).get_code())
				&&sumreturnrate.remainstocklist.get(remainloop).getPlace().equals(tradelist.get(loop).get_place())
				&&((sumreturnrate.remainstocklist.get(remainloop).getNumber())^(tradelist.get(loop).get_num()))<0){
			    
			    int sumnum = sumreturnrate.remainstocklist.get(remainloop).getNumber()+tradelist.get(loop).get_num();
			     sumreturnrate.remainstocklist.get(remainloop).setNumber(sumnum);
			     TAG = 1;
			     break;
		       }
	}
	
	//如果没有找到相同的股票
	if(TAG == 0){
	Remainstock remainstock = new Remainstock(tradelist.get(loop).get_name(),tradelist.get(loop).get_code(),
					tradelist.get(loop).get_place(),tradelist.get(loop).get_num());
			sumreturnrate.remainstocklist.add(remainstock);
	}
	
	return sumreturnrate;
}

/**
 * 计算持股构成
 * @param tradelist
 * @return
 */
	public  List<HistoryStockown> HistoryStockownmaker(List<Trade> tradelist) {

		List<HistoryStockown> HSOL = new ArrayList<HistoryStockown>();//定义一个历史股票类链表
		HistoryStockown HSO ;//历史股票类
		
		//遍历每条记录
		for (int tradeloop = 0; tradeloop < tradelist.size(); tradeloop++) {
		
			int TAG = 0;//标记是否找到相同股票  0表示没有找到 1表示找到
			for(int stockownloop =0; stockownloop < HSOL.size();stockownloop++){
				if( (tradelist.get(tradeloop).get_trade_stytle().equals("卖空")|| tradelist.get(tradeloop).get_trade_stytle().equals("补仓"))
						&&tradelist.get(tradeloop).get_name().equals(HSOL.get(stockownloop).name)
				  &&tradelist.get(tradeloop).get_code().equals(HSOL.get(stockownloop).code)
				  &&tradelist.get(tradeloop).get_place().equals(HSOL.get(stockownloop).place)
				 &&HSOL.get(stockownloop).number.get(HSOL.get(stockownloop).number.size()-1)<=0){
					
				HSOL.get(stockownloop).addnumber_date(tradelist.get(tradeloop).get_num(), tradelist.get(tradeloop).get_date());
				TAG = 1;
				break;
				}
				else if( (tradelist.get(tradeloop).get_trade_stytle().equals("买入")|| tradelist.get(tradeloop).get_trade_stytle().equals("卖出"))
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
