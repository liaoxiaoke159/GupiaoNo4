package stock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

import stocker.DataBuilder;
import stocker.DataDealer;
import stocker.Stocks;
import stocker.Trade;

public class Dia_tradechange {

	protected Shell shell;
	private Text text_num;
	private Text text_place;
	private int row;
	private int column;
	private Table table;
	private Text text_price;
	private Text text_style;
	private Text text_date;
	private Text text_code;
	private Text text_name;
	private Label lblNewLabel;
	private Label label;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label lbl_notice;
	private Button btn_change;
	private Button btn_undo;
	private int tabitemindex;
	private List<Trade> trades =new ArrayList<Trade>();
	private String text_date_value;
	private int changedindex;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Table table = null;
		try {
			Dia_tradechange window = new Dia_tradechange(table,1,1,1);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Open the window.
	 * @param tabitemindex 
	 */
	
	public Dia_tradechange(Table table,int row,int column, int tabitemindex){
		this.row = row;
		this.column = column;
		this.table = table;
		this.tabitemindex = tabitemindex;
		this.changedindex = row-1;
		DataBuilder dd  = new DataBuilder();
		try {
			this.trades = dd.tradelistmaker(homepage.path_trade.get(tabitemindex));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(shell,SWT.SHELL_TRIM|SWT.APPLICATION_MODAL);
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chaogushenqi.png"));
		shell.setSize(467, 138);
		shell.setText("\u4FEE\u6539\u4EA4\u6613\u8BB0\u5F55");
		
		Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		text_num = new Text(shell, SWT.BORDER);
		text_num.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lostEvent();
			}
		});
		text_num.setBounds(310, 33, 73, 23);
		
		text_place = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_place.setBounds(383, 33, 41, 23);
		
		  btn_change = new Button(shell, SWT.NONE);
		btn_change.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				changeEvent();
				
			}
		});
		btn_change.setBounds(219, 69, 53, 27);
		btn_change.setText("修改");
		
		Button btn_cancel = new Button(shell, SWT.NONE);
		btn_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				shell.dispose();
			}
		});
		btn_cancel.setBounds(278, 69, 53, 27);
		btn_cancel.setText("取消");
		
		text_price = new Text(shell, SWT.BORDER);
		text_price.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lostEvent();
			}
		});
		text_price.setBounds(257, 33, 53, 23);
		
		text_style = new Text(shell, SWT.BORDER);
		text_style.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lostEvent();
			}
		});
		text_style.setBounds(216, 33, 41, 23);
		
		text_date = new Text(shell, SWT.BORDER);
		text_date.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lostEvent();
			}
		});
		text_date.setBounds(143, 33, 73, 23);
		
		text_code = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_code.setBounds(83, 33, 60, 23);
		
		text_name = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_name.setBounds(10, 33, 73, 23);
		
		btn_undo = new Button(shell, SWT.NONE);
		btn_undo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				undoEvent();
			}
		});
		btn_undo.setBounds(337, 69, 80, 27);
		btn_undo.setText("撤销此次交易");
		
		 lbl_notice = new Label(shell, SWT.BORDER);
		lbl_notice.setBounds(10, 73, 180, 17);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 48, 17);
		lblNewLabel.setText("\u80A1\u7968\u540D\u79F0");
		
		label = new Label(shell, SWT.NONE);
		label.setBounds(83, 10, 48, 17);
		label.setText("\u80A1\u7968\u4EE3\u7801");
		
		lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(143, 10, 24, 17);
		lblNewLabel_1.setText("\u65E5\u671F");
		
		lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(219, 10, 24, 17);
		lblNewLabel_2.setText("\u7C7B\u578B");
		
		label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(257, 10, 36, 17);
		label_1.setText("\u6210\u4EA4\u4EF7");
		
		label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(310, 10, 24, 17);
		label_2.setText("\u6570\u91CF");
		
		label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(383, 10, 36, 17);
		label_3.setText("\u4EA4\u6613\u6240");
		
		textSettext();

	}

	


	protected void undoEvent() {
		// TODO Auto-generated method stub
		lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lbl_notice.setText("正在修改记录...");
		
		Workbook wb=null;
		try {
			 wb= Workbook.getWorkbook(new File(homepage.path_trade.get(tabitemindex)));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableWorkbook wwb =null;
		try {
			wwb = Workbook.createWorkbook(new File(homepage.path_trade.get(tabitemindex)),wb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WritableSheet sheet = wwb.getSheet(0);
		sheet.removeRow(row+1);
		try {
			wwb.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wwb.close();
		} catch (WriteException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			DataDealer  dd = new DataDealer(homepage.path_trade.get(tabitemindex),tabitemindex);
			dd.tablemaker(Dia_trade.table_trade);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DataDealer datadealer = new DataDealer(homepage.path_trade.get(tabitemindex),tabitemindex);
		datadealer.creat();
		homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		homepage.lbl_notice.setText("交易记录修改完成！");
		shell.dispose();
		
	}



	protected void lostEvent() {
		// TODO Auto-generated method stub
		boolean boolchange = CheckChange(1);
		boolean boolundo =  CheckChange(2);
		if(boolchange&&boolundo){
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
			lbl_notice.setText("可以修改或可以撤销交易记录");
			btn_change.setEnabled(true);
			btn_undo.setEnabled(true);
		}
		else if(!boolchange&&boolundo){
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
			lbl_notice.setText("不可以修改但可以撤销交易记录");
			btn_change.setEnabled(false);
			btn_undo.setEnabled(true);
		}
		else if(boolchange&&!boolundo){
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
			lbl_notice.setText("可以修改但不可以撤销交易记录");
			btn_change.setEnabled(true);
			btn_undo.setEnabled(false);
		}else if(!boolchange&&!boolundo){
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			lbl_notice.setText("不可以修改也不可以撤销交易记录");
			btn_change.setEnabled(false);
			btn_undo.setEnabled(false);
		}
	}



	private void textSettext() {
		// TODO Auto-generated method stub
		text_name.setText(table.getItem(row).getText(0));
		text_code.setText(table.getItem(row).getText(1));
		text_date.setText(table.getItem(row).getText(2));
		text_style.setText(table.getItem(row).getText(3));
		text_price.setText(table.getItem(row).getText(4));
		text_num.setText(table.getItem(row).getText(5));
		text_place.setText(table.getItem(row).getText(6));
		this.text_date_value = text_date.getText();
	}



	protected void changeEvent() {
		// TODO Auto-generated method stub
		
		lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lbl_notice.setText("正在修改记录...");
		
		Workbook wb=null;
		try {
			 wb= Workbook.getWorkbook(new File(homepage.path_trade.get(tabitemindex)));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableWorkbook wwb =null;
		try {
			wwb = Workbook.createWorkbook(new File(homepage.path_trade.get(tabitemindex)),wb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WritableSheet sheet = wwb.getSheet(0);
		
		jxl.write.Label label_date = new jxl.write.Label(2,row+1,text_date.getText());
		try {
			sheet.addCell(label_date);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jxl.write.Label label_style = new jxl.write.Label(3,row+1,text_style.getText());
		try {
			sheet.addCell(label_style);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jxl.write.Label label_price = new jxl.write.Label(4,row+1,text_price.getText());
		try {
			sheet.addCell(label_price);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jxl.write.Label label_num = new jxl.write.Label(5,row+1,text_num.getText());
		try {
			sheet.addCell(label_num);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			wwb.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wwb.close();
		} catch (WriteException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			DataDealer  dd = new DataDealer(homepage.path_trade.get(tabitemindex),tabitemindex);
			dd.tablemaker(Dia_trade.table_trade);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DataDealer datadealer = new DataDealer(homepage.path_trade.get(tabitemindex),tabitemindex);
		datadealer.creat();
		homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		homepage.lbl_notice.setText("交易记录修改完成！");
		shell.dispose();
		
	}
	
	protected boolean CheckChange(int checkTAG) {
       List<Stocks>  stockslist = new ArrayList<Stocks>();
       //日期有修改 则重排交易记录
		if(!text_date.getText().equals(text_date_value)){
			this.text_date_value = text_date.getText();
			Trade tradetemp = trades.get(changedindex );
			trades.remove(changedindex);
			String[] date_text = text_date.getText().split("/");
			int loop = 0;
			for(;loop < trades.size();++loop){
				String[] date_trade = trades.get(loop).get_date().split("/");
				if(Integer.parseInt(date_text[0])>Integer.parseInt(date_trade[0])){
					continue;
				}else {
					if(Integer.parseInt(date_text[1])>Integer.parseInt(date_trade[1])){
						continue;
					}else {
						if(Integer.parseInt(date_text[2])>=Integer.parseInt(date_trade[2])){
							continue;
						}else break;
						}
					}
			}
			if(loop<trades.size()){
				
				trades.add(loop, tradetemp);
			}
			else if(loop==trades.size()){
				trades.add(tradetemp);
			}
			changedindex = loop;
		}
		for(int loop = 0; loop <trades.size();++loop){
			String name=null;
			String code=null;
			String place=null;
			String num=null;
			String price=null;
			String style=null;
			if(loop == changedindex){
				if(checkTAG == 2){
					continue;
				}
				if(checkTAG == 1){
					name = text_name.getText();
					code = text_code.getText();
					price = text_price.getText();
					place = text_place.getText();
					num = text_num.getText();
					style = text_style.getText();
				}
			}
			else{ 
			 name = trades.get(loop).get_name();
			 code = trades.get(loop).get_code();
			 place = trades.get(loop).get_place();
			 num = Integer.toString(trades.get(loop).get_num());
			 price = Double.toString( trades.get(loop).get_price());
			 style = trades.get(loop).get_trade_stytle();
			 }
			
			int TAG = -1;//-1表示没有找到相同股票 1表示找到相同
			for(int listloop =0; listloop < stockslist.size();listloop++){
				
				Stocks  stocks = stockslist.get(listloop);
				if(name.equals(stocks.getSocketname())
						&&code.equals(stocks.getSocketcode())
						&&place.equals(stocks.getplace())){
						
					if((style.equals("卖出")&&stocks.getNum()>0)||(style.equals("补仓")&&stocks.getNum()<0)){
						
						
						
						if(style.equals("卖出")&&(stocks.getNum()+Integer.parseInt(num))<0)
						{
							//homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
							//homepage.lbl_notice.setText("*导入失败  原因:交易记录非法");
							System.out.println("当前交易非法"+" 原因：股票买入不足"+ " 股票名："+name + " excel行数："+(loop+1));
							return false;
						}//异常
						
						else if(style.equals("补仓")&&(stocks.getNum()+Integer.parseInt(num))>0){
							//homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
							//homepage.lbl_notice.setText("*导入失败  原因:交易记录非法");
							System.out.println("当前交易非法"+" 原因：股票卖空不足"+ " 股票名："+name + " excel行数："+(loop+1));
							return false;
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
				return false;
			}
			//没有找到相同股票 买入或卖空操作 新增一只股票
			else if(TAG == -1){
				Stocks stocktemp = new Stocks(name,code,num);
				stocktemp.setplace(place);
				stocktemp.setcostprice(Double.parseDouble(price));
				stockslist.add(stocktemp);
			}
			
		}
		

		return true;
	}

}
