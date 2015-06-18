package stock;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;

import stocker.PlaceOder;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;


public class Dia_sell {

	protected Shell shell;
	
	public static Text text_code;//股票代码
	public static Text text_upprice; //涨停价格
	public static Text text_downprice;//跌停价格
	public static Text text_limitnum;//可卖数量
	public static Text text_price;//委托价格
	public  static Text text_num;//委托数量
	
	private Button btn_placeorder;
	private Button btnNewButton_1;
	
	public  String[] information;
	public  String place;
	public String code;
	private int tabitemindex;
	private Text text_place;
	private Label lbl_notice;
	private Label lbl_code;
	private Label lbl_upprice;
	private Label lbl_downprice;
	private Label lbl_limitnum;
	private Label lbl_price;
	private Label lbl_num;
	private Label lbl_date;
	private DateTime text_dateTime;
	
	public Dia_sell(String[] information, String place, int tabitemindex) {
		// TODO Auto-generated constructor stub	
		this.information = information;
		this.place =place;
		this.tabitemindex = tabitemindex;
		this.code = Dia_info.getcode();
	}
	public Dia_sell(int tabitemindex){
		this.tabitemindex = tabitemindex;
	}
	

	/**
	 * Launch the application.
	 * @param args
	 * @return 
	 */

	
	public static void main(String[] args) {
		String[] s1 = null;
		String s2 =null;
		try {
			Dia_sell window = new Dia_sell(1);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		shell = new Shell(shell,SWT.SHELL_TRIM|SWT.APPLICATION_MODAL);
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chaogushenqi.png"));
		shell.setSize(488, 396);
		shell.setText("卖出");
		
		text_code = new Text(shell, SWT.BORDER);
		text_code.setBounds(241, 58, 73, 23);
		
		text_upprice = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_upprice.setBounds(241, 87, 73, 23);
		
		text_downprice = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_downprice.setBounds(241, 116, 73, 23);
		
		text_limitnum = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_limitnum.setBounds(241, 145, 73, 23);
		
		text_price = new Text(shell, SWT.BORDER);
		text_price.setBounds(241, 174, 73, 23);
		
		text_num = new Text(shell, SWT.BORDER);
		text_num.setBounds(241, 203, 73, 23);
		
		text_price.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.keyCode == 16777296 |e.keyCode == 13) {
					// e.detail = SWT.TRAVERSE_TAB_NEXT;
					e.doit = true;
					paceoder();
				}
			}
		});
		
		text_num.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.keyCode == 16777296 |e.keyCode == 13) {
					// e.detail = SWT.TRAVERSE_TAB_NEXT;
					e.doit = true;
					paceoder();
				}
			}
		});
		
		//下单，取消按钮
		btn_placeorder = new Button(shell, SWT.NONE);
		btn_placeorder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				paceoder();
                
			}
		});
		btn_placeorder.setBounds(129, 281, 80, 27);
		btn_placeorder.setText("\u4E0B\u5355");
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e1) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(264, 281, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		lbl_code = new Label(shell, SWT.NONE);
		lbl_code.setBounds(148, 61, 61, 17);
		lbl_code.setText("股票代码：");
		
		lbl_upprice = new Label(shell, SWT.NONE);
		lbl_upprice.setBounds(148, 90, 61, 17);
		lbl_upprice.setText("涨停价格：");
		
		lbl_downprice = new Label(shell, SWT.NONE);
		lbl_downprice.setBounds(148, 119, 61, 17);
		lbl_downprice.setText("跌停价格：");
		
		lbl_limitnum = new Label(shell, SWT.NONE);
		lbl_limitnum.setBounds(148, 148, 61, 17);
		lbl_limitnum.setText("可卖数量：");
		
		lbl_price = new Label(shell, SWT.NONE);
		lbl_price.setBounds(148, 177, 61, 17);
		lbl_price.setText("委托价格：");
		
		lbl_num = new Label(shell, SWT.NONE);
		lbl_num.setBounds(148, 206, 61, 17);
		lbl_num.setText("委托数量：");
		
		lbl_date = new Label(shell, SWT.NONE);
		lbl_date.setBounds(148, 239, 61, 17);
		lbl_date.setText("日      期：");
		
		text_dateTime = new DateTime(shell, SWT.BORDER);
		text_dateTime.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				try {
					text_limitnumSetter();
				} catch (BiffException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		text_dateTime.setBounds(241, 232, 88, 24);
		
		lbl_notice = new Label(shell, SWT.BORDER);
		lbl_notice.setBounds(337, 333, 125, 17);
		
		
		if (information == null) {

			final Label lbl_search = new Label(shell, SWT.NONE);
			lbl_search.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {

					lbl_searchEvent();
				}
			});
			lbl_search.addMouseTrackListener(new MouseTrackAdapter() {
				@Override
				public void mouseEnter(MouseEvent e) {
					lbl_search.setBackground(Display.getCurrent()
							.getSystemColor(SWT.COLOR_GREEN));
				}

				@Override
				public void mouseExit(MouseEvent e) {
					lbl_search
							.setBackground(Display
									.getCurrent()
									.getSystemColor(
											SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
				}
			});
			lbl_search
					.setImage(SWTResourceManager
							.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\检查.png"));
			lbl_search.setBounds(360, 61, 18, 18);

			text_place = new Text(shell, SWT.BORDER);
			text_place.setBounds(322, 58, 32, 23);

		} else {
			trade_sell();// 显示文本框内容
		}
	}

	protected void lbl_searchEvent() {
		// TODO Auto-generated method stub
		if(text_code.getText().isEmpty()){
			lbl_notice.setText("*请输入股票代码");
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		if(text_code.getText().length()!=6||!Userinfochange.isNumeric(text_code.getText())){
			lbl_notice.setText("*股票代码为6位数字");
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		if(text_place.getText().isEmpty()){
			lbl_notice.setText("*请输入股票交易所");
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		
		if(!text_place.getText().equals("sz") && !text_place.getText().equals("sh")){
			lbl_notice.setText("*交易所填写:sz或sh");
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		
		
		String[] informationtemp = null ;
		try {

			informationtemp = Internet.share.Internet.getSharedata(text_place.getText(), text_code.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "网络异常或者不存在该只股票", "异常",
					JOptionPane.ERROR_MESSAGE);

		}
		String name = informationtemp[0];
		if (name == null) {
			lbl_notice.setText("*不存在的股票");
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		String[] names = new String[2];
		System.out.println("搜索出的股票名:" + name + "(Dia_buy 297)");
		names = name.split("\"");
		name = names[1];
		if (name.length() == 0) {
			lbl_notice.setText("*不存在的股票");
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			return;
		}
		
		information = informationtemp;
		place = text_place.getText();
		code = text_code.getText();
		
		trade_sell();//显示文本框内容
		
		lbl_notice.setText("股票信息获取成功！");
		lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		
		trade_sell();//显示文本框内容
	}
	protected void paceoder() {
		// TODO Auto-generated method stub
		if(!homepage.isimport){
			JOptionPane.showMessageDialog(null, "先导入用户数据吧");
		}else if((text_num.getText().isEmpty())|(text_price.getText().isEmpty())){
    			
    		JOptionPane.showMessageDialog(null, "先输入数值喔");
    	}
    	else if(!Userinfochange.isNumeric(text_num.getText())
    			|!Userinfochange.isNumeric(text_price.getText())){
    		
    		JOptionPane.showMessageDialog(null, "输入数字喔");
    	}
    	else if( Integer.parseInt(Dia_sell.text_num.getText())<0
    			||Integer.parseInt(Dia_sell.text_num.getText())%100!=0){
				JOptionPane.showMessageDialog(null, "要输入整百股喔");
				
			}
    	      //可卖数量不足
    		else if(Integer.parseInt(Dia_sell.text_num.getText())>Integer.parseInt(Dia_sell.text_limitnum.getText())){
    			JOptionPane.showMessageDialog(null, "可卖数量不足喔");
    		}
    		else{	
    			MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
    			messagebox.setText("下单");
    			messagebox.setMessage("          确认是否下单");
  
    			int val=messagebox.open();
    			
    			if(val == SWT.YES){
    				
    			String date = Integer.toString(text_dateTime.getYear())+"/"+Integer.toString(text_dateTime.getMonth()+1)+
							"/"+Integer.toString(text_dateTime.getDay());
    			PlaceOder placeoder = new PlaceOder(tabitemindex,information[0].substring(21),text_code.getText(),
						"卖出",text_price.getText(),text_num.getText(),place,date);
    			
    			if(placeoder.update_trade()){
    			Messagedialo window = new Messagedialo();
    			window.open(shell);
    			}
    			else{
    				
    				homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
    				homepage.lbl_notice.setText("*卖出数量非法");
					
    				Messagedialofail window = new Messagedialofail();
        			window.open(shell);
    			}
    		
    			
    			}
    		
    	
    }
	}

	private void trade_sell() {
		// TODO Auto-generated method stub
		text_code.setText(code);
		DecimalFormat df=new DecimalFormat("#.00");
		text_upprice.setText(df.format(Double.parseDouble(information[2])*1.1));//涨停价
		
		text_downprice.setText(df.format(Double.parseDouble(information[2])*0.9));//跌停价
//		
//		Table table =  homepage.table_chicangs.get(tabitemindex);
//		for(int loop =0; loop < table.getItemCount();loop++){
//			if(text_code.getText().equals(table.getItem(loop).getText(1))
//					&&information[0].substring(21).equals(table.getItem(loop).getText(0))
//					&&place.equals(table.getItem(loop).getText(2))
//					&&Integer.parseInt(table.getItem(loop).getText(5))>0){
//				text_limitnum.setText(table.getItem(loop).getText(5) );//可卖数量
//				btn_placeorder.setEnabled(true);
//				break;
//			}
//		}
		
		try {
			text_limitnumSetter();
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void text_limitnumSetter() throws BiffException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		Workbook wb =  Workbook.getWorkbook(new FileInputStream(homepage.path_trade.get(tabitemindex)));
		Sheet sheet = wb.getSheet(0);
		int rows = sheet.getRows();
		String[] date_trade = new String[]{Integer.toString(text_dateTime.getYear()),Integer.toString(text_dateTime.getMonth()+1),Integer.toString(text_dateTime.getDay())};
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
			
			int excelindex = loop;
			
			int num=0;
			for(int i =2; i <excelindex-1;i++){
				if(sheet.getCell(1,i).getContents().equals(text_code.getText())
						&&sheet.getCell(6,i).getContents().equals(text_place.getText())
						&&(sheet.getCell(3,i).getContents().equals("买入")||sheet.getCell(2,i).getContents().equals("卖出"))){
					num+=Integer.parseInt(sheet.getCell(5,i).getContents());
				}
			}
			if(num>0){
				text_limitnum.setText(Integer.toString(num));
				btn_placeorder.setEnabled(true);
			}
			else{
				lbl_notice.setText("*日期不合法或用户没有该股票");
				lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				btn_placeorder.setEnabled(false);
			}
	}; 
	
}
