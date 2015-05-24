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
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

import stocker.PlaceOder;


public class Dia_shortsell {



	protected Shell shell;
	public static Text text_sharecode;
	private Text text_uprice;
	private Text text_downprice;
	public static Text text_price;
	public static Text text_number;
	public static String[] information;
	public static String place;
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			Dia_shortsell window = new Dia_shortsell();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public Dia_shortsell(String[] information,String place) {
		// TODO Auto-generated constructor stub
		this.information = information;
		this.place =place;
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
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\chaogushenqi.png"));
		shell.setSize(467, 398);
		shell.setText("\u5356\u7A7A");
		
		text_sharecode = new Text(shell, SWT.BORDER);
		text_sharecode.setBounds(225, 88, 73, 23);
		
		text_uprice = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_uprice.setText("100");
		text_uprice.setBounds(225, 117, 73, 23);
		
		text_downprice = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_downprice.setText("1");
		text_downprice.setBounds(225, 146, 73, 23);
		
		text_price = new Text(shell, SWT.BORDER);
		text_price.setBounds(225, 178, 73, 23);
		
		text_number = new Text(shell, SWT.BORDER);
		text_number.setBounds(225, 207, 73, 23);
		
		//下单，取消按钮
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				paceoder();
			}
		});
		btnNewButton.setBounds(116, 284, 58, 27);
		btnNewButton.setText("\u4E0B\u5355");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(225, 284, 58, 27);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(116, 91, 48, 17);
		lblNewLabel.setText("\u80A1\u7968\u4EE3\u7801");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(116, 120, 48, 17);
		lblNewLabel_1.setText("\u6DA8\u505C\u4EF7\u683C");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(116, 152, 48, 17);
		lblNewLabel_2.setText("\u8DCC\u505C\u4EF7\u683C");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(116, 181, 48, 17);
		lblNewLabel_4.setText("\u59D4\u6258\u4EF7\u683C");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(116, 210, 48, 17);
		lblNewLabel_5.setText("\u59D4\u6258\u6570\u91CF");
		
		trade_shortsell();
		
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
    	else if( Integer.parseInt(Dia_shortsell.text_number.getText())<0
    			||Integer.parseInt(Dia_shortsell.text_number.getText())%100!=0){
				JOptionPane.showMessageDialog(null, "要输入整百股喔");
				
			}
    	    
    		else{	
    			MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
    			messagebox.setText("下单");
    			messagebox.setMessage("          确认是否下单");
  
    			int val=messagebox.open();
    			
    			if(val == SWT.YES){
                
    			PlaceOder placeoder = new PlaceOder();
    			placeoder.update_shortsell();
    		
    			Messagedialo window = new Messagedialo();
    			window.open(shell);}
    		
    	
    }
		
	}

	private void trade_shortsell() {
		// TODO Auto-generated method stub
		text_sharecode.setText(Dia_info.getcode());
		
		DecimalFormat df=new DecimalFormat("#.00");
		text_uprice.setText(df.format(Double.parseDouble(information[2])*1.1) );//涨停价
		
		text_downprice.setText(df.format(Double.parseDouble(information[2])*0.9));//跌停价
		
		//getfundown().setText(homepage.get_table_userinfo().getItem(1).getText(1));//可用资金
		
		//double d1 = Double.parseDouble(homepage.get_table_userinfo().getItem(1).getText(1));
		//double d2 = Double.parseDouble(information[3]);
		//text_limitnum.setText(Integer.toString( (int)(d1/d2) )  );//可卖数量
		

	}
}
