package stocker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import stock.homepage;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class DataDealer {

	public String path_file;
	public double limitfund;
	public double fund;
	public int tabitemindex;

	public DataDealer(String path_file, int index){
		this.path_file=path_file;
		this.tabitemindex = index;
	}
	//public DataDealer(){}
	
	public boolean creat() {
		// TODO Auto-generated method stub

		// 检查文件是否符合要求
		if (!path_fileCheck(path_file)) {
			
			JOptionPane.showMessageDialog(null, "文件不正确");
			return false;
		}

		
		
        //把交易记录excel表转化成Stocks类
		List<Stocks> stockslist = new ArrayList<Stocks>();
		   stockslist = stockslist_initial(path_file);
		 if(stockslist == null){
			   return false;//交易记录非法
		   }
		 
		 if(true){
	
		// 初始化用户信息table表
		homepage.table_userinfos.get(tabitemindex).removeAll();
		
		String[] tablestr = new String[]{"初始资金","可用资金","资产总值","持有股票数","盈利率"};
		TableItem tableitem1 = new TableItem(homepage.table_userinfos.get(tabitemindex),SWT.NONE);
		tableitem1.setText(tablestr);
		new TableItem(homepage.table_userinfos.get(tabitemindex),SWT.NONE);
		userinfo_table_change( homepage.table_userinfos.get(tabitemindex), stockslist);

		// 打印持仓股票信息到table表
		table_chiang_initial(stockslist, homepage.table_chicangs.get(tabitemindex));
//		 for(int i=0;i<homepage.stkl.stockslist.size();i++){
//			 System.out.println(homepage.stkl.stockslist.get(i).getSocketname()+"(Importer 62)");
//			 }
		 
		 
		chiguANDshouyilv();
		}
		   return true;
	}
	


	public boolean path_fileCheck(String path_file) {
		Workbook wb = null;
		try {
			 wb = Workbook.getWorkbook(new FileInputStream(path_file));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet(0);
		return sheet.getColumns()==8&&sheet.getRows()>=2&&sheet.getCell(0, 1).getContents().equals("股票名称")&&sheet.getCell(1, 1).getContents().equals("股票代码")
				&&sheet.getCell(2, 1).getContents().equals("交易日期")&&sheet.getCell(3, 1).getContents().equals("交易类型")
				&&sheet.getCell(4, 1).getContents().equals("成交价")&&sheet.getCell(5, 1).getContents().equals("成交数量")
				&&sheet.getCell(6, 1).getContents().equals("交易所");
		
	}
	
	private List<Stocks> stockslist_initial(String path_file) {
		// TODO Auto-generated method stub
		List<Stocks>  stockslist = new ArrayList<Stocks>();
		
		Workbook wb = null;
		try {
			 wb = Workbook.getWorkbook(new FileInputStream(path_file));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Sheet sheet = wb.getSheet(0);
		int rows = sheet.getRows();
		fund = limitfund = Double.parseDouble(sheet.getCell(7, 0).getContents());//获得初始资金
		for(int rowsloop = 2; rowsloop <rows;++rowsloop){
			String name = sheet.getCell(0, rowsloop).getContents();
			String code = sheet.getCell(1,rowsloop).getContents();
			String place = sheet.getCell(6,rowsloop).getContents();
			String num = sheet.getCell(5,rowsloop).getContents();
			String price = sheet.getCell(4,rowsloop).getContents();
			String style = sheet.getCell(3,rowsloop).getContents();
			
			
			int TAG = -1;//-1表示没有找到相同股票 1表示找到相同
			for(int listloop =0; listloop < stockslist.size();listloop++){
				
				Stocks  stocks = stockslist.get(listloop);
				if(name.equals(stocks.getSocketname())
						&&code.equals(stocks.getSocketcode())
						&&place.equals(stocks.getplace())){
						
					if((style.equals("卖出")&&stocks.getNum()>0)||(style.equals("补仓")&&stocks.getNum()<0)){
						
						
						
						if(style.equals("卖出")&&(stocks.getNum()+Integer.parseInt(num))<0)
						{
							homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
							homepage.lbl_notice.setText("*导入失败  原因:交易记录非法");
							System.out.println("当前交易非法"+" 原因：股票买入不足"+ " 股票名："+name + " excel行数："+(rowsloop+1));
							return null;
						}//异常
						
						else if(style.equals("补仓")&&(stocks.getNum()+Integer.parseInt(num))>0){
							homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
							homepage.lbl_notice.setText("*导入失败  原因:交易记录非法");
							System.out.println("当前交易非法"+" 原因：股票卖空不足"+ " 股票名："+name + " excel行数："+(rowsloop+1));
							return null;
						}
						
						stockslist.get(listloop).setNum(Integer.parseInt(num));
						 if(stocks.getNum()==0)
						{stockslist.remove(listloop);}
							
						   TAG = 1;
							break;
					 }//判断是卖出或补仓记录
				
					if((style.equals("买入")&&stocks.getNum()>0)||(style.equals("卖空")&&stocks.getNum()<0)){
						
							//找到相同股票 重新设置股票的成本价
						   DecimalFormat df = new DecimalFormat(".00");
						   double newcostprice = Double.parseDouble(df.format((stocks.getNum()* stocks.getcostprice()
							+Integer.parseInt(num)*Double.parseDouble((price)))/(stocks.getNum()+Integer.parseInt(num))));
							
						    stockslist.get(listloop).setcostprice(newcostprice);
							
							stockslist.get(listloop).setNum(Integer.parseInt(num));
							
							TAG = 1;
							break;
						
					}//判断是买入或卖空记录
						
				}
				
			}
			
			//没有找到相同股票 并且交易记录是卖出或补仓 交易记录非法
			if(TAG==-1&&(style.equals("卖出")||style.equals("补仓"))){
				System.out.println("当前交易非法"+" 原因：股票买入或补仓不足"+ " 股票名："+name + " excel行数："+(rowsloop+1));
				return null;
			}
			//没有找到相同股票 买入或卖空操作 新增一只股票
			else if(TAG == -1){
				Stocks stocktemp = new Stocks(name,code,num);
				stocktemp.setplace(place);
				stocktemp.setcostprice(Double.parseDouble(price));
				stockslist.add(stocktemp);
			}
			
			limitfund+=Double.parseDouble(price)*Integer.parseInt(num);//计算可用资金
		}
		
//		for(int listloop =0; listloop < stockslist.size();++listloop){
//			System.out.println(stockslist.get(listloop).getSocketname()+" "+stockslist.get(listloop).getSocketcode()+
//					" "+stockslist.get(listloop).getcostprice()+" "+stockslist.get(listloop).getNum()+" "+stockslist.get(listloop).getplace());
//		}
		return stockslist;
	}

	private  void chiguANDshouyilv() {
		// TODO Auto-generated method stub
		List<Trade> tradelist = new ArrayList<Trade>();
		DataBuilder dd =new DataBuilder();
		try {
			tradelist =dd.tradelistmaker(path_file);
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

/*
		for(int i=0; i<tradelist.size();i++){
			System.out.println(tradelist.get(i).get_name()+" "+tradelist.get(i).get_code()
					+" "+tradelist.get(i).get_num()+" "+tradelist.get(i).get_trade_stytle()
					+" "+tradelist.get(i).get_price()+" "+tradelist.get(i).get_date());
		}
	*/
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		System.out.println("开始计算收益率 ");
		
		Sumreturnrate sumreturnrate = new Sumreturnrate();
		sumreturnrate = dd.Sumreturnratemaker(tradelist);
		
		long endMili=System.currentTimeMillis();
		System.out.println("总耗时为："+(endMili-startMili)+"毫秒"+"\n");
		
		Linechart linechart = new Linechart(sumreturnrate);
		linechart.getChartPanel("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data");
		
		homepage.lbl_shouyilvs.get(tabitemindex).setImage(new Image(Display.getDefault(), "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\shouyilv.png"));

//		for(int loop =0; loop <sumreturnrate.date.size();loop++){
//			System.out.println(sumreturnrate.date.get(loop)+" "+sumreturnrate.rate.get(loop) );
//		}
		long startMili2=System.currentTimeMillis();// 当前时间对应的毫秒数
		System.out.println("开始计算持股 ");
		
		List<HistoryStockown> HSOL = new ArrayList<HistoryStockown>();
		HSOL = dd.HistoryStockownmaker(tradelist);
		
		long endMili2=System.currentTimeMillis();
		System.out.println("总耗时为："+(endMili2-startMili2)+"毫秒"+"\n");
		
		AreaJFreeChart AJC = new AreaJFreeChart(HSOL);
		AJC.getChartPanel("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data");
		homepage.lbl_chigus.get(tabitemindex).setImage(new Image(Display.getDefault(), "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chigu.png"));
//		  for(int stockloop = 0 ;stockloop < HSOL.size(); stockloop++){
//			  System.out.println(HSOL.get(stockloop).name + " " + HSOL.get(stockloop).code+" :");
//			  for(int dateloop = 0;dateloop < HSOL.get(stockloop).date.size(); dateloop++){
//				  System.out.println(HSOL.get(stockloop).date.get(dateloop)
//						  +" "+HSOL.get(stockloop).number.get(dateloop));
//			  }
//		  }
		
	}

	private  void userinfo_table_change(Table table_userinfo, List<Stocks> stockslist) {
		// TODO Auto-generated method stub

		
		double sumprice=0;//持有市值
		
		String[] information =new String[31];
		for(int i =0; i <stockslist.size();i++)
		{
			try {
				 
				information =Internet.share.Internet.getSharedata(stockslist.get(i).getplace(),
						stockslist.get(i).getSocketcode()); 
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "请检查网络", "异常", JOptionPane.ERROR_MESSAGE);
				return;
				
			}
			  //抓取该股票信息
			
			double perprice = Double.parseDouble(information[3]);
			double num = (double)stockslist.get(i).getNum();
			
			sumprice = sumprice + perprice*num;
			
		}
		
		//修改股票数
		table_userinfo.getItem(1).setText(3, Integer.toString(stockslist.size()));
		
		//修改初始资金
		table_userinfo.getItem(1).setText(0,Double.toString(fund));
		
		//修改可用资金
		table_userinfo.getItem(1).setText(1,Double.toString(limitfund));
		
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);//设置保留小数位
		DecimalFormat df = new DecimalFormat(".00");
		
		//修改资产总值
		table_userinfo.getItem(1).setText(2, df.format(limitfund+sumprice));
		
		double returnrate=(limitfund+sumprice-fund)/fund;//计算出盈利率
		
		//修改盈利率
		table_userinfo.getItem(1).setText(4, nf.format(returnrate));
		
	}
	
	public  void table_chiang_initial(List<Stocks> stockslist, Table table_chicang) {
		// TODO Auto-generated method stub
		
		String[] information = new String[31];
		String[] strings = new String[10];
		
	
		int num = stockslist.size();
		
		//先擦除原先表格
		table_chicang.removeAll();
		for(int i =0;i<num;i++){
		 //抓取该股票信息
		try {
			 
			information =Internet.share.Internet.getSharedata(stockslist.get(i).getplace(),
				stockslist.get(i).getSocketcode()); 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "请检查网络", "异常", JOptionPane.ERROR_MESSAGE);
		}
		 

		
		strings[0]= stockslist.get(i).getSocketname();//股票名称
		strings[1] = stockslist.get(i).getSocketcode();//股票代码
		strings[2] = stockslist.get(i).getplace();//交易所
		strings[3] = information[3];//当前价
		
		double nowprice = Double.parseDouble(information[3]);
		double price = stockslist.get(i).getcostprice();
		double rate = (nowprice - price)/price;
		
		//百分数格式：有两位小数
		NumberFormat nf_precent = NumberFormat.getPercentInstance();
		nf_precent.setMinimumFractionDigits(2);
		//double格式：有两位小数
		NumberFormat nf_price = NumberFormat.getNumberInstance();
		nf_price.setMinimumFractionDigits(2);
		
		strings[4] = nf_price.format(nowprice-price);//涨跌
		strings[5] = Integer.toString(stockslist.get(i).getNum());//持有量
		strings[6] = nf_price.format(nowprice*(double)stockslist.get(i).getNum());//持有市值
		strings[7] = Double.toString(stockslist.get(i).getcostprice());//成本价
		strings[8] = nf_precent.format(rate);//浮动盈亏比例
		strings[9] = nf_price.format((nowprice-price)*(double)stockslist.get(i).getNum());//浮动盈亏
		
		
		TableItem tableItem = new TableItem(table_chicang,SWT.NONE);
		tableItem.setText(strings);
		
		}
	}
	
	public void changefund(String newfund){
		
		Workbook workBook = null;
		try {
			workBook = Workbook.getWorkbook(new FileInputStream(path_file));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableWorkbook writeBook = null;
		try {
			writeBook = Workbook.createWorkbook(new File(path_file),workBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableSheet sheet = writeBook.getSheet(0);

		Label label = new Label(7,0,newfund);
		try {
			sheet.addCell(label);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 4、打开流，开始写文件
		try {
			writeBook.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5、关闭流
		try {
			writeBook.close();
		} catch (WriteException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tablemaker(Table table)  throws BiffException, IOException{
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

	for (int i = 1; i < rows; i++) {
		for (int j = 0; j < columns; j++) {
			Cell oCell = oFirstSheet.getCell(j, i);// 需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
			// System.out.println(oCell.getContents()+"\r\n");
			itemdata[j] = oCell.getContents();
		}
		TableItem tableItem = new TableItem(table,SWT.NONE);
		tableItem.setText(itemdata);
	}

}

}
