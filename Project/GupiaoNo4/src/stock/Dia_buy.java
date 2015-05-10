package stock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;

import stocker.StockMain;
import datadealer.Trade;
import datadealer.DataBuilder;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Dia_buy  {

	protected Shell shell;
	private static Text text_code;
	private static Text text_upprice;
	private static Text text_downprice;
	private static Text text_limitnum;
	private static Text text_fundown;
	private static Text text_price;
	private static Text text_num;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Label label;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private String[] information;
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			Dia_buy window = new Dia_buy();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	public Dia_buy(String[] information1) {
		// TODO Auto-generated constructor stub
		this.information = information1;
	}

	public static Text getstockcode() {
		// TODO Auto-generated method stub
		return text_code;
	}
	public static Text getupprice() {
		// TODO Auto-generated method stub
		return text_upprice;
	}
	
	
	public static Text getdownprice() {
		// TODO Auto-generated method stub
		return text_downprice;
	}
	public static Text getlimitnum() {
		// TODO Auto-generated method stub
		return text_limitnum;
	}
	public static Text getfundown() {
		// TODO Auto-generated method stub
		return text_fundown;
	}
	public static Text getprice() {
		// TODO Auto-generated method stub
		return text_price;
	}
	public static Text getnum() {
		// TODO Auto-generated method stub
		return text_num;
	}
	/**
	 * Open the window.
	 */
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\chaogushenqi.png"));
		shell.setSize(488, 398);
		shell.setText("买入");//设置窗口
		

		
		text_code = new Text(shell, SWT.BORDER);
		text_code.setBounds(169, 40, 73, 23);
		
		text_upprice = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_upprice.setBounds(169, 69, 73, 23);
		
		text_downprice = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_downprice.setBounds(169, 98, 73, 23);
		
		text_limitnum = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_limitnum.setBounds(169, 127, 73, 23);
		
		text_fundown = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_fundown.setBounds(169, 156, 73, 23);
		
		text_price = new Text(shell, SWT.BORDER);
		text_price.setBounds(169, 185, 73, 23);
		
		text_num = new Text(shell, SWT.BORDER);
		text_num.setBounds(169, 214, 73, 23);
		
		//下单以及取消按钮
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
                messagebox.setText("下单");
                messagebox.setMessage("          确认是否下单");
              
               int val=messagebox.open();
                if(val == SWT.YES)
                {
                	if((text_code.getText().isEmpty())|(text_num.getText().isEmpty())|(text_price.getText().isEmpty())
                			|(text_upprice.getText().isEmpty())){
               
                		Messagedialofail window2 = new Messagedialofail();
            			window2.open();
                	}
                	else{
                		update_buy();
                		Messagedialo window = new Messagedialo();
                		window.open();
                	}
                }
			}


		});
		btnNewButton.setBounds(83, 276, 80, 27);
		btnNewButton.setText("\u4E0B\u5355");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(258, 276, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(102, 159, 61, 17);

		lblNewLabel.setText("\u53EF\u7528\u8D44\u91D1\uFF1A");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(102, 42, 61, 17);
		lblNewLabel_1.setText("\u80A1\u7968\u4EE3\u7801\uFF1A");
		
		label = new Label(shell, SWT.NONE);
		label.setText("\u6DA8\u505C\u4EF7\u683C\uFF1A");
		label.setBounds(102, 72, 61, 17);

		
		label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u8DCC\u505C\u4EF7\u683C\uFF1A");
		label_1.setBounds(102, 101, 61, 17);

		
		label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u53EF\u4E70\u6570\u91CF\uFF1A");
		label_2.setBounds(102, 127, 61, 17);

		
		label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u59D4\u6258\u6570\u91CF\uFF1A");
		label_3.setBounds(102, 217, 61, 17);

		
		label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u59D4\u6258\u4EF7\u683C\uFF1A");
		label_4.setBounds(102, 188, 61, 17);


		trade_buy();//显示文本框内容
		
	}




	

	private void trade_buy() {
		// TODO Auto-generated method stub
		Dia_buy.getstockcode().setText(Dia_info.getcode());
		
		DecimalFormat df=new DecimalFormat("#.00");
		getupprice().setText(df.format(Double.parseDouble(information[2])*1.1) );//涨停价
		
		getdownprice().setText(df.format(Double.parseDouble(information[2])*0.9));//跌停价
		
		getfundown().setText(homepage.get_table_userinfo().getItem(1).getText(1));//可用资金
		
		double d1 = Double.parseDouble(homepage.get_table_userinfo().getItem(1).getText(1));
		double d2 = Double.parseDouble(information[3]);
		getlimitnum().setText(Integer.toString( (int)(d1/d2) )  );//可买数量
		
		
		
		
	}
	
	protected void update_buy() {
		// TODO Auto-generated method stub
		userinfochange_buy();// 买入后用户信息改变
		
		Trade trade_buy = new Trade();
		trade_buy = newtrade();
		
		// 买入后增加一条新的交易记录
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

		StockMain.addStock();
	}

	private Trade newtrade() {
		// TODO Auto-generated method stub
		Trade trade_buy = new Trade();
		
		trade_buy.set_name(information[0]);
		trade_buy.set_code(getstockcode().getText());
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(new Date());
        
		trade_buy.set_date(date);
		trade_buy.set_trade_stytle("买入");
		trade_buy.set_price(Double.parseDouble(getprice().getText()));
		trade_buy.set_num(Integer.parseInt(getnum().getText()));
		return trade_buy;
	}

	private void userinfochange_buy() {
		// TODO Auto-generated method stub
		
		double d1 = Double.parseDouble(
				homepage.get_table_userinfo().getItem(1).getText(1));//获得table表的可用资金
		
		double d2 = Double.parseDouble(getprice().getText());//委托价格
		double d3 = Double.parseDouble(getnum().getText());//委托数量
		
		//修改可用资金
		homepage.get_table_userinfo().getItem(1).setText(1, Double.toString(d1-d2*d3));
		
		int d4= Integer.parseInt(
				homepage.get_table_userinfo().getItem(1).getText(3));//获得持有股票数
		//修改股票数
		homepage.get_table_userinfo().getItem(1).setText(3, Integer.toString(d4+(int)d3));
	}
	


}
