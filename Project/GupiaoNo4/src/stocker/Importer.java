package stocker;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Importer {

	private String path_file;
	

	public Importer(String path_file){
		this.path_file=path_file;
	}
	
	public void creat() {
		// TODO Auto-generated method stub

		String strpath = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\当前持仓.xls";
		// 检查文件是否符合要求
		if (!path_file.equals(strpath)) {
			
			JOptionPane.showMessageDialog(null, "请导入股票持仓excel");
			return ;
		}

		//导入用户信息
		try {
						
			DataBuilder.tablemaker(homepage.path_userinfo,homepage.table_userinfo);
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// 把持仓excel表转化成Stocks类
		try {
			 homepage.stkl.stockslist_initial(path_file);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// DataBuilder.Excel_chicang_update(StockMain.getStocksList(),homepage.get_path_chicang());

		// 更新用户信息table表
		userinfo_table_change( homepage.table_userinfo, homepage.stkl.stockslist);

		// 导出持仓股票信息exce表到table表
		table_chiang_initial(homepage.stkl.stockslist,homepage.table_chicang);
		 for(int i=0;i<homepage.stkl.stockslist.size();i++){
			 System.out.println(homepage.stkl.stockslist.get(i).getSocketname()+"(Importer 62)");
			 }
		 
		homepage.isimport = true; // 标记已导入数据
		
		 
		
	
		
		
		chiguANDshouyilv();
		
		
		
		
	}
	


	public static void chiguANDshouyilv() {
		// TODO Auto-generated method stub
		List<Trade> tradelist = new ArrayList<Trade>();
		try {
			tradelist = DataBuilder.tradelistmaker(homepage.path_trade);
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
		Sumreturnrate sumreturnrate = new Sumreturnrate();
		sumreturnrate = DataBuilder.Sumreturnratemaker(tradelist);
		
		Linechart linechart = new Linechart(sumreturnrate);
		linechart.getChartPanel("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data");
		
		homepage.lbl_shouyilv.setImage(new Image(Display.getDefault(), "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\shouyilv.png"));

//		for(int loop =0; loop <sumreturnrate.date.size();loop++){
//			System.out.println(sumreturnrate.date.get(loop)+" "+sumreturnrate.rate.get(loop) );
//		}
		
		List<HistoryStockown> HSOL = new ArrayList<HistoryStockown>();
		HSOL = DataBuilder.HistoryStockownmaker(tradelist);
		AreaJFreeChart AJC = new AreaJFreeChart(HSOL);
		AJC.getChartPanel("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data");
		homepage.lbl_chigu.setImage(new Image(Display.getDefault(), "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chigu.png"));
//		  for(int stockloop = 0 ;stockloop < HSOL.size(); stockloop++){
//			  System.out.println(HSOL.get(stockloop).name + " " + HSOL.get(stockloop).code+" :");
//			  for(int dateloop = 0;dateloop < HSOL.get(stockloop).date.size(); dateloop++){
//				  System.out.println(HSOL.get(stockloop).date.get(dateloop)
//						  +" "+HSOL.get(stockloop).number.get(dateloop));
//			  }
//		  }
		
	}

	public static void userinfo_table_change(Table table_userinfo, List<Stocks> stockslist) {
		// TODO Auto-generated method stub
//		if(homepage.stkl.stockslist.size()==0){
//			return;
//		}
		
		if(stockslist.isEmpty()){
			
			return;
		}
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
		
		double fund = Double.parseDouble(table_userinfo.getItem(1).getText(0));//获得初始资金
		double fundown = Double.parseDouble(table_userinfo.getItem(1).getText(1));//获得可用资金
		//修改资产总值
		table_userinfo.getItem(1).setText(2, Double.toString(fundown+sumprice));
		
		double returnrate=(fundown+sumprice-fund)/fund;//计算出盈利率
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);//设置保留小数位
		//修改盈利率
		table_userinfo.getItem(1).setText(4, nf.format(returnrate));
		
	}
	
	public static void table_chiang_initial(List<Stocks> stockslist, Table table_chicang) {
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
}
