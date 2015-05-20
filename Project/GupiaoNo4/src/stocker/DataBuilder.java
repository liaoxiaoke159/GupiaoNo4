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
	


	public static void userinfchanger(String text_fund, String path_userinfo) throws IOException, RowsExceededException, WriteException, BiffException {

		// 1、创建工作簿(WritableWorkbook)对象，打开excel文件，若文件不存在，则创建文件
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
		
		
		// 4、打开流，开始写文件
		writeBook.write();
		// 5、关闭流
		writeBook.close();
	}

	
	
	//更新交易记录excel表
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
		sheet.addCell(label1);//股票名称
		
		Label label2 = new Label(1,rows,trade.get_code());
		sheet.addCell(label2);//股票代码
		
		Label label3 = new Label(2,rows,trade.get_date());
		sheet.addCell(label3);//日期
		
		Label label4 = new Label(3,rows,trade.get_trade_stytle());
		sheet.addCell(label4);//类型
		
		Label label5 = new Label(4,rows,Double.toString(trade.get_price()));
		//label5.setCellFormat(cf_price);
		sheet.addCell(label5);//价格
		
		Label label6 = new Label(5,rows,Integer.toString(trade.get_num()));
		//label6.setCellFormat(cf_rate);
		sheet.addCell(label6);//数量
		
		Label label7 = new Label(6,rows,trade.get_place());
		sheet.addCell(label7);//交易所
		
		
		
		// 4、打开流，开始写文件
		writeBook.write();
		// 5、关闭流
		writeBook.close();
		
	}


	
	//更新持仓excel表
	public static void Excel_chicang_update(List<Stocks> stocksList,
			String path_chicang) throws Exception{
		// TODO Auto-generated method stub
		
		Workbook workBook = Workbook.getWorkbook(new FileInputStream(path_chicang));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(path_chicang),workBook);
		WritableSheet sheet = writeBook.getSheet(0);
		int stocksnum = stocksList.size();
	
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);//设置保留小数位

		 String[] information = new String[31];
		for(int i = 1;i<stocksnum+1;i++){
			
			try {
				 
				information =Internet.share.Internet.getSharedata(stocksList.get(i).getplace(),
					stocksList.get(i).getSocketcode()); 

			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "请检查网络", "异常", JOptionPane.ERROR_MESSAGE);
			}
			  //抓取该股票信息
			   
			     
				
				Label label1 = new Label(0,i,stocksList.get(i).getSocketname());
				sheet.addCell(label1);//股票名称
				Label label2 = new Label(1,i,stocksList.get(i).getSocketcode());
				sheet.addCell(label2);//股票代码
				Label label3 = new Label(2,i,stocksList.get(i).getplace());
				sheet.addCell(label3);//交易所
				


				Label label4 = new Label(3,i,information[3]);
				sheet.addCell(label4);//当前价
				
				double nowprice = Double.parseDouble(information[3]);//当前价
				double yesterprice = Double.parseDouble(information[2]);//昨收价
				double rateprice;
				if(nowprice==yesterprice){rateprice = 0.00;}
				else{rateprice = (nowprice-yesterprice)/yesterprice;}
				
				Label label5 = new Label(4,i,nf.format(rateprice));
				sheet.addCell(label5);//涨跌
				
				Label label6 = new Label(5,i,Integer.toString(stocksList.get(i).getNum()));
				sheet.addCell(label6);//持有量
				
				double sumprice = nowprice*stocksList.get(i).getNum();
				Label label7 = new Label(6,i,Double.toString(sumprice));
				sheet.addCell(label7);//持有市值
				
				Label label8 = new Label(7,i,Double.toString(stocksList.get(i).getcostprice()));
				sheet.addCell(label8);//成本价
				
				Label label9 = new Label(8,i,"0.00(0.00%)");
				sheet.addCell(label9);//浮动盈亏
				
				double costprice = stocksList.get(i).getcostprice()*stocksList.get(i).getNum();//持仓成本（无手续费）
				double returnprice = sumprice - costprice;//盈亏
				double returnrate = returnprice/costprice;//盈亏率
				Label label10 = new Label(8,i,Double.toString(returnprice)+"("+nf.format(returnrate)+")");
				sheet.addCell(label10);//浮动盈亏
			
		}
		
		// 4、打开流，开始写文件
		writeBook.write();
		// 5、关闭流
		writeBook.close();
	}



	//把交易记录读成tradelist并返回
	public static List<Trade> tradelistmaker(String path_trade) throws BiffException, IOException {
		// TODO Auto-generated method stub
		InputStream is = new FileInputStream(path_trade);

		Workbook rwb = Workbook.getWorkbook(is);
		rwb.getNumberOfSheets();

		Sheet sheet = rwb.getSheet(0);
		int rows = sheet.getRows();
		List<Trade> tradelist = new ArrayList<Trade>();
		
		for(int i=1; i<rows; i++){
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


   //传入tradelist返回交易stocktradelist
	public static List<StockTrade> stockreturnratemaker(List<Trade> tradelist) {
		
		if(tradelist.isEmpty()){return null;}
		
		List<StockTrade> stocktradelist = new ArrayList<StockTrade>();
		

		int biaoji = 0;		
		for (int i = 0; i < tradelist.size(); i++) {

			for (int j = 0; j < stocktradelist.size(); j++) {
				
				if (tradelist.get(i).get_name().equals(stocktradelist.get(j).getname())
						& tradelist.get(i).get_code().equals(stocktradelist.get(j).getcode())) {

					biaoji = 1;
					
					//如果是买入记录
					if(tradelist.get(i).get_trade_stytle().equals("买入")
							||tradelist.get(i).get_trade_stytle().equals("卖空")){
						
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
