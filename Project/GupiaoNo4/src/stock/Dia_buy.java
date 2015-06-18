package stock;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.DateTime;

import stocker.PlaceOder;

public class Dia_buy  {

	protected Shell shell;
	
	public  Text text_code;
	public  Text text_upprice;
	public Text text_downprice;
	public  Text text_limitnum;
	public  Text text_fundown;
	public  Text text_price;
	public  Text text_num;
	
	private Label lbl_upprice;
	private Label lbl_downprice;
	private Label lbll_limitnum;
	private Label label_num;
	private Label lbl_price;
	
	public  String[] information;
	public  String place;
	private String code;
	private int tabitemindex;
	DateTime text_dateTime;
	private Text text_place;
	private Label lbl_notice;

	
	/**
	 * Launch the application.
	 * @param tabitemindex 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Dia_buy window = new Dia_buy(1);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public  Dia_buy(int tabitemindex){
		this.tabitemindex = tabitemindex;
		
	}
	
	public Dia_buy(String[] information,String place, int tabitemindex) {
		// TODO Auto-generated constructor stub
		this.information = information;
		this.place =place;
		this.tabitemindex = tabitemindex;
		this.code = Dia_info.getcode();
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
		shell.setSize(437, 398);
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

		//下单以按钮
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
		
              
                
				paceoder();
			}
		});
		btnNewButton.setBounds(72, 291, 80, 27);
		btnNewButton.setText("\u4E0B\u5355");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(226, 291, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		Label lbl_limitfund = new Label(shell, SWT.NONE);
		lbl_limitfund.setBounds(102, 159, 61, 17);

		lbl_limitfund.setText("\u53EF\u7528\u8D44\u91D1\uFF1A");
		
		Label lbl_code = new Label(shell, SWT.NONE);
		lbl_code.setBounds(102, 42, 61, 17);
		lbl_code.setText("\u80A1\u7968\u4EE3\u7801\uFF1A");
		
		lbl_upprice = new Label(shell, SWT.NONE);
		lbl_upprice.setText("\u6DA8\u505C\u4EF7\u683C\uFF1A");
		lbl_upprice.setBounds(102, 72, 61, 17);

		
		lbl_downprice = new Label(shell, SWT.NONE);
		lbl_downprice.setText("\u8DCC\u505C\u4EF7\u683C\uFF1A");
		lbl_downprice.setBounds(102, 101, 61, 17);

		
		lbll_limitnum = new Label(shell, SWT.NONE);
		lbll_limitnum.setText("\u53EF\u4E70\u6570\u91CF\uFF1A");
		lbll_limitnum.setBounds(102, 127, 61, 17);

		
		label_num = new Label(shell, SWT.NONE);
		label_num.setText("\u59D4\u6258\u6570\u91CF\uFF1A");
		label_num.setBounds(102, 217, 61, 17);

		
		lbl_price = new Label(shell, SWT.NONE);
		lbl_price.setText("\u59D4\u6258\u4EF7\u683C\uFF1A");
		lbl_price.setBounds(102, 188, 61, 17);
		
		
		
	    text_dateTime = new DateTime(shell, SWT.BORDER);
		text_dateTime.setBounds(169, 243, 88, 24);
		
		Label lbl_date = new Label(shell, SWT.NONE);
		lbl_date.setBounds(102, 248, 56, 17);
		lbl_date.setText("日      期：");
		
		lbl_notice = new Label(shell, SWT.BORDER);
		lbl_notice.setBounds(286, 333, 125, 17);
		
		


		if(information==null){
			
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
					lbl_search.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));
				}
				@Override
				public void mouseExit(MouseEvent e) {
					lbl_search.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
				}
			});
			lbl_search.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\检查.png"));
			lbl_search.setBounds(290, 43, 18, 18);
			
			text_place = new Text(shell, SWT.BORDER);
			text_place.setBounds(252, 40, 32, 23);
			
			
			
		}
		else{
			trade_buy();//显示文本框内容
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
		trade_buy();//显示文本框内容
		
		lbl_notice.setText("股票信息获取成功！");
		lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		
	}


	public void paceoder() {
		// TODO Auto-generated method stub
		if(!homepage.isimport){
			JOptionPane.showMessageDialog(null, "先导入用户数据吧");
		}else if ( (text_num.getText().isEmpty())|| (text_price.getText().isEmpty())) {
			
			JOptionPane.showMessageDialog(null, "亲，总要输点东西吧");
			
		} else if(!Userinfochange.isNumeric(text_num.getText())
    			|!Userinfochange.isNumeric(text_price.getText())){
    		
    		JOptionPane.showMessageDialog(null, "输入数字喔");
    	}
		else if(Integer.parseInt(text_num.getText())>Integer.parseInt(text_limitnum.getText())){
			JOptionPane.showMessageDialog(null, "委托数量不能高于可买数量");
		}
		else if (Integer.parseInt(text_num.getText())<100 ||Integer.parseInt(text_num.getText()) % 100 != 0) {
				JOptionPane.showMessageDialog(null, "要输入整百股喔");

			} else {
				MessageBox messagebox = new MessageBox(shell, SWT.YES
						| SWT.NO);
				messagebox.setText("下单");
				messagebox.setMessage("          确认是否下单");

				int val = messagebox.open();

				
				if (val == SWT.YES) {
					
					String date = Integer.toString(text_dateTime.getYear())+"/"+Integer.toString(text_dateTime.getMonth()+1)+
							"/"+Integer.toString(text_dateTime.getDay());
					PlaceOder placeoder = new PlaceOder(tabitemindex,information[0].substring(21),text_code.getText(),
							"买入",text_price.getText(),text_num.getText(),place,date);
					if(placeoder.update_trade()){
					Messagedialo window = new Messagedialo();
					window.open(shell);
					}
					else{
						homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    				homepage.lbl_notice.setText("*买入失败");
						Messagedialofail window = new Messagedialofail();
						window.open(shell);
					}
					
				}
			}
		}
	
	

	private void trade_buy() {
		// TODO Auto-generated method stub
		text_code.setText(code);
		DecimalFormat df=new DecimalFormat("#.00");
		text_upprice.setText(df.format(Double.parseDouble(information[2])*1.1) );//涨停价
		
		text_downprice.setText(df.format(Double.parseDouble(information[2])*0.9));//跌停价
		
		text_fundown.setText(homepage.table_userinfos.get(tabitemindex).getItem(1).getText(1));//可用资金
		
		double d1 = Double.parseDouble(homepage.table_userinfos.get(tabitemindex).getItem(1).getText(1));
		double d2 = Double.parseDouble(information[3]);
		text_limitnum.setText(Integer.toString( (int)(d1/d2) )  );//可买数量
		

	}
}
