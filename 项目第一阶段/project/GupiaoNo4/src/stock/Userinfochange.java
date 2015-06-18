package stock;



import java.io.IOException;

import javax.swing.JOptionPane;

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
import org.eclipse.swt.graphics.Rectangle;

import stocker.DataBuilder;
import stocker.Importer;

public class Userinfochange {

	protected Shell shell;
	private Text text_fund;

  //�ж��ַ����Ƿ�Ϊ����
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
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\chaogushenqi.png"));
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
					JOptionPane.showMessageDialog(null, "��������ȷ��ֵ", "�쳣", JOptionPane.ERROR_MESSAGE);
				}
				else{
					//�޸�ֵ���ڳ�ʼ�ʽ�������޸�
					if(Double.parseDouble(text_fund.getText()) >=
							Double.parseDouble( homepage.get_table_userinfo().getItem(1).getText(0))){
						
						double fundown = Double.parseDouble(homepage.get_table_userinfo().getItem(1).getText(1));//��ÿ����ʽ�
						double fund =Double.parseDouble(homepage.get_table_userinfo().getItem(1).getText(0));//��ó�ʼ�ʽ�
						double newfundown = fundown + ( Double.parseDouble(text_fund.getText())-fund);//���¼��� �����ʽ� = ԭ�������ʽ�+������ĳ�ʼ�ʽ�-ԭ���ĳ�ʼ�ʽ�
						
						homepage.table_userinfo.getItem(1).setText(1, Double.toString(newfundown));
						homepage.get_table_userinfo().getItem(1).setText(0, text_fund.getText());
						
						Importer.userinfo_table_change(homepage.table_userinfo,homepage.stkl.stockslist);
					
						shell.close();
					}
					else{
						MessageBox messagebox = new MessageBox(shell, SWT.OK);
			            messagebox.setText("��..");
			            messagebox.setMessage("�ʽ�̫������");
			            int val = messagebox.open();
						
					}
					
					
				}
				
				
			}

			
			
			
		});
		btnNewButton.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\asggh.png"));
		btnNewButton.setBounds(398, 3, 30,30 );

	}
}
