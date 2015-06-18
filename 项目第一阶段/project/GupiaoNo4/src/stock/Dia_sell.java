package stock;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;

import stocker.PlaceOder;


public class Dia_sell {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_6;
	
	public static Text text_sharecode;//股票代码
	public static Text text_8; //涨停价格
	public static Text text_9;//跌停价格
	public static Text text_10;//可卖数量
	public static Text text_price;//委托价格
	public  static Text text_number;//委托数量
	
	private Button btnNewButton;
	private Button btnNewButton_1;
	public static String[] information_sell;
	public static String place;
	
	public Dia_sell(String[] information, String place) {
		// TODO Auto-generated constructor stub	
		this.information_sell = information;
		this.place =place;
	}

	/**
	 * Launch the application.
	 * @param args
	 * @return 
	 */

	
//	public static void main(String[] args) {
//		String[] s1 = null;
//		String s2 =null;
//		try {
//			Dia_sell window = new Dia_sell(s1,s2);
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	
	
	
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
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\chaogushenqi.png"));
		shell.setSize(488, 398);
		shell.setText("卖出");//设置窗口
		
		//各种文本框
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setText("\u6DA8\u505C\u4EF7\u683C");
		text.setBounds(129, 87, 57, 23);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setText("\u8DCC\u505C\u4EF7\u683C");
		text_1.setBounds(129, 116, 57, 23);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setText("\u53EF\u5356\u6570\u91CF");
		text_2.setBounds(129, 145, 57, 23);
		
		text_3 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_3.setText("\u59D4\u6258\u4EF7\u683C");
		text_3.setBounds(129, 174, 57, 23);
		
		text_4 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_4.setText("\u59D4\u6258\u6570\u91CF");
		text_4.setBounds(129, 203, 57, 23);
		
		text_6 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_6.setText("\u80A1\u7968\u4EE3\u7801");
		text_6.setBounds(129, 58, 57, 23);
		
		text_sharecode = new Text(shell, SWT.BORDER);
		text_sharecode.setText("101324587");
		text_sharecode.setBounds(241, 58, 73, 23);
		
		text_8 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_8.setText("100");
		text_8.setBounds(241, 87, 73, 23);
		
		text_9 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_9.setText("1");
		text_9.setBounds(241, 116, 73, 23);
		
		text_10 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_10.setText("100");
		text_10.setBounds(241, 145, 73, 23);
		
		text_price = new Text(shell, SWT.BORDER);
		text_price.setBounds(241, 174, 73, 23);
		
		text_number = new Text(shell, SWT.BORDER);
		text_number.setBounds(241, 203, 73, 23);
		
		text_price.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.keyCode == 16777296 |e.keyCode == 13) {
					// e.detail = SWT.TRAVERSE_TAB_NEXT;
					e.doit = true;
					paceoder();
				}
			}
		});
		
		text_number.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.keyCode == 16777296 |e.keyCode == 13) {
					// e.detail = SWT.TRAVERSE_TAB_NEXT;
					e.doit = true;
					paceoder();
				}
			}
		});
		
		//下单，取消按钮
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				paceoder();
                
			}
		});
		btnNewButton.setBounds(99, 267, 80, 27);
		btnNewButton.setText("\u4E0B\u5355");
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e1) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(267, 267, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");

		trade_sell();//显示文本框内容
	}

	protected void paceoder() {
		// TODO Auto-generated method stub
		if(!homepage.isimport){
			JOptionPane.showMessageDialog(null, "先导入用户数据吧");
		}else if((text_number.getText().isEmpty())|(text_price.getText().isEmpty())){
    			
    		JOptionPane.showMessageDialog(null, "先输入数值喔");
    	}
    	else if(!Userinfochange.isNumeric(text_number.getText())
    			|!Userinfochange.isNumeric(text_price.getText())){
    		
    		JOptionPane.showMessageDialog(null, "输入数字喔");
    	}
    	else if( Integer.parseInt(Dia_sell.text_number.getText())<0
    			||Integer.parseInt(Dia_sell.text_number.getText())%100!=0){
				JOptionPane.showMessageDialog(null, "要输入整百股喔");
				
			}
    	      //可卖数量不足
    		else if(Integer.parseInt(Dia_sell.text_number.getText())>Integer.parseInt(Dia_sell.text_10.getText())){
    			JOptionPane.showMessageDialog(null, "可卖数量不足喔");
    		}
    		else{	
    			MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
    			messagebox.setText("下单");
    			messagebox.setMessage("          确认是否下单");
  
    			int val=messagebox.open();
    			
    			if(val == SWT.YES){
                
    			PlaceOder placeoder = new PlaceOder();
    			placeoder.update_sell();
    		
    			Messagedialo window = new Messagedialo();
    			window.open(shell);}
    		
    	
    }
	}

	private void trade_sell() {
		// TODO Auto-generated method stub
		text_sharecode.setText(Dia_info.getcode());
		DecimalFormat df=new DecimalFormat("#.00");
		text_8.setText(df.format(Double.parseDouble(information_sell[2])*1.1));//涨停价
		
		text_9.setText(df.format(Double.parseDouble(information_sell[2])*0.9));//跌停价
		
		
		text_10.setText(homepage.table_chicang.getItem(homepage.tableindex).getText(5) );//可卖数量
	}
}
