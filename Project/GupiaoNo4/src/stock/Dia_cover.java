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

public class Dia_cover {

	protected Shell shell;
	public  static Text text_sharecode;
	private Text text_upprice;
	private Text text_downprice;
	private Text text_limitnum;
	public static Text text_price;
	public static Text text_number;
	public static String[] information;
	public static String place;
	private Label lblNewLabel;
	private Label label;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			Dia_cover window = new Dia_cover();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	
	public Dia_cover(String[] information,String place) {
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
		shell.setSize(488, 398);
		shell.setText("\u8865\u4ED3");
		
		text_sharecode = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_sharecode.setText("102648567");
		text_sharecode.setBounds(237, 62, 73, 23);
		
		text_upprice = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_upprice.setText("100");
		text_upprice.setBounds(237, 91, 73, 23);
		
		text_downprice = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_downprice.setText("1");
		text_downprice.setBounds(237, 120, 73, 23);
		
		text_limitnum = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_limitnum.setText("100");
		text_limitnum.setBounds(237, 149, 73, 23);
		
		text_price = new Text(shell, SWT.BORDER);
		text_price.setBounds(237, 178, 73, 23);
		
		text_number = new Text(shell, SWT.BORDER);
		text_number.setBounds(237, 207, 73, 23);
		
		//下单，取消按钮
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				paceoder();
			}
		});
		btnNewButton.setBounds(140, 274, 48, 27);
		btnNewButton.setText("\u4E0B\u5355");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(251, 274, 48, 27);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(140, 65, 61, 17);
		lblNewLabel.setText("\u80A1\u7968\u4EE3\u7801");
		
		label = new Label(shell, SWT.NONE);
		label.setText("\u6DA8\u505C\u4EF7\u683C");
		label.setBounds(140, 94, 61, 17);
		
		label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u8DCC\u505C\u4EF7\u683C");
		label_1.setBounds(140, 123, 61, 17);
		
		label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u53EF\u8865\u6570\u91CF");
		label_2.setBounds(140, 152, 61, 17);
		
		label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u59D4\u6258\u6570\u91CF");
		label_3.setBounds(140, 210, 61, 17);
		
		label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u59D4\u6258\u4EF7\u683C");
		label_4.setBounds(140, 181, 61, 17);

		
		trade_cover();//文本框显示
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
    	else if( Integer.parseInt(Dia_cover.text_number.getText())<0
    			||Integer.parseInt(Dia_cover.text_number.getText())%100!=0){
				JOptionPane.showMessageDialog(null, "要输入整百股喔");
				
			}
    	    
    		else{	
    			MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
    			messagebox.setText("下单");
    			messagebox.setMessage("          确认是否下单");
  
    			int val=messagebox.open();
    			
    			if(val == SWT.YES){
                
    			PlaceOder placeoder = new PlaceOder();
    			placeoder.update_cover();
    		
    			Messagedialo window = new Messagedialo();
    			window.open(shell);}}
	}

	private void trade_cover() {
		// TODO Auto-generated method stub
		text_sharecode.setText(Dia_info.getcode());
		DecimalFormat df=new DecimalFormat("#.00");
		text_upprice.setText(df.format(Double.parseDouble(information[2])*1.1));//涨停价
		
		text_downprice.setText(df.format(Double.parseDouble(information[2])*0.9));//跌停价
		
		
		text_limitnum.setText((homepage.table_chicang.getItem(homepage.tableindex).getText(5)).substring(1) );//可补数量
	}
}
