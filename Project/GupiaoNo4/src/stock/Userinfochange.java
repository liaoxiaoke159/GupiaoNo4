package stock;
import javax.swing.JOptionPane;
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
import org.eclipse.swt.graphics.Rectangle;
import stocker.DataDealer;

public class Userinfochange {

	protected Shell shell;
	private Text text_fund;
	private int tabitemindex;

  public Userinfochange(int selectionIndex) {
		// TODO Auto-generated constructor stub
	  this.tabitemindex = selectionIndex;
	}



	//判断字符串是否为数字
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
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
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chaogushenqi.png"));
		shell.setSize(458, 80);
		shell.setText("\u7528\u6237\u8D44\u91D1\u4FEE\u6539");
		
		Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
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
				if(text_fund.getText()=="" || !isNumeric(text_fund.getText())){
					JOptionPane.showMessageDialog(null, "请输入正确数值", "异常", JOptionPane.ERROR_MESSAGE);
				}
				else{
					//修改值大于初始资金才允许修改
					if(Double.parseDouble(text_fund.getText()) >=
							Double.parseDouble( homepage.table_userinfos.get(tabitemindex).getItem(1).getText(0))){
						
						DataDealer datadealer = new DataDealer(homepage.path_trade.get(tabitemindex),tabitemindex);
						datadealer.changefund(text_fund.getText());
						double fund =Double.parseDouble(homepage.table_userinfos.get(tabitemindex).getItem(1).getText(0));//获得初始资金
						double fundown = Double.parseDouble(homepage.table_userinfos.get(tabitemindex).getItem(1).getText(1));//获得可用资金
						
						double newfundown = fundown + ( Double.parseDouble(text_fund.getText())-fund);//重新计算 可用资金 = 原来可用资金+（输入的初始资金-原来的初始资金）
						
						homepage.table_userinfos.get(tabitemindex).getItem(1).setText(1, Double.toString(newfundown));
						homepage.table_userinfos.get(tabitemindex).getItem(1).setText(0, text_fund.getText());
						
					
						shell.close();
					}
					else{
						MessageBox messagebox = new MessageBox(shell, SWT.OK);
			            messagebox.setText("额..");
			            messagebox.setMessage("资金太少啦！");
			            messagebox.open();
						
					}
					
					
				}
				
				
			}

			
			
			
		});
		btnNewButton.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\\u786E\u8BA4.png"));
		btnNewButton.setBounds(398, 3, 30,30 );

	}
}
