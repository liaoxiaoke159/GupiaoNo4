package stock;



import java.io.IOException;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import datadealer.DataBuilder;

public class Userinfochange {

	protected Shell shell;
	private Text text_fund;


	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			Userinfochange window = new Userinfochange();
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\chaogushenqi.png"));
		shell.setSize(458, 80);
		shell.setText("\u7528\u6237\u8D44\u91D1\u4FEE\u6539");
		
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(0, 0, 442, 48);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 61, 17);
		lblNewLabel.setText("\u521D\u59CB\u8D44\u91D1");
		
		text_fund = new Text(composite, SWT.BORDER);
		text_fund.setBounds(287, 7, 97, 23);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(text_fund.getText()==""){
					MessageBox messagebox = new MessageBox(shell, SWT.OK);
		            messagebox.setText("额..");
		            messagebox.setMessage("你不输入数字我怎么帮你改资金");
		            int val = messagebox.open();
				}
				else{
					//修改值大于初始资金才允许修改
					if(Double.parseDouble(text_fund.getText()) >=
							Double.parseDouble( homepage.get_table_userinfo().getItem(1).getText(0))){
						homepage.get_table_userinfo().getItem(1).setText(0, text_fund.getText());
					
						shell.close();
					}
					else{
						MessageBox messagebox = new MessageBox(shell, SWT.OK);
			            messagebox.setText("额..");
			            messagebox.setMessage("资金太少啦！");
			            int val = messagebox.open();
						
					}
					
					
				}
				
				
				
			}
		});
		btnNewButton.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\0a22e1a12280511a491f8376f6551c3c.png"));
		btnNewButton.setBounds(398, 3, 30, 30);

	}
}
