package stock;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class Dia_userinfo {

	protected Shell shell;
	private int index;


	public Dia_userinfo(int selectionIndex) {
		// TODO Auto-generated constructor stub
		this.index =  selectionIndex;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			Dia_userinfo window = new Dia_userinfo();
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
		shell.setSize(366, 300);
		shell.setText("用户信息");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(74, 28, 61, 17);
		lblNewLabel.setText("初始资金：");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(74, 61, 61, 17);
		lblNewLabel_1.setText("可用资金：");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(74, 96, 61, 17);
		lblNewLabel_2.setText("资产总值：");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(74, 133, 61, 17);
		lblNewLabel_3.setText("持有股票：");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(74, 170, 61, 17);
		lblNewLabel_4.setText("   盈利率：");
		
		Label lbl_fund = new Label(shell, SWT.NONE);
		lbl_fund.setBounds(150, 28, 99, 17);
		lbl_fund.setText(homepage.table_userinfos.get(index).getItem(1).getText(0));
		
		Label lbl_fundown = new Label(shell, SWT.NONE);
		lbl_fundown.setText(homepage.table_userinfos.get(index).getItem(1).getText(1));
		lbl_fundown.setBounds(150, 61, 99, 17);
		
		Label label_fundsum = new Label(shell, SWT.NONE);
		label_fundsum.setText(homepage.table_userinfos.get(index).getItem(1).getText(2));
		label_fundsum.setBounds(150, 96, 99, 17);
		
		Label label_num = new Label(shell, SWT.NONE);
		label_num.setText(homepage.table_userinfos.get(index).getItem(1).getText(3));
		label_num.setBounds(150, 133, 99, 17);
		
		Label label_raturnrate = new Label(shell, SWT.NONE);
		label_raturnrate.setText(homepage.table_userinfos.get(index).getItem(1).getText(4));
		label_raturnrate.setBounds(150, 170, 99, 17);
		
		

	}
}
