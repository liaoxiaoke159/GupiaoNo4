package stock;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Dia_tradechange {

	protected Shell shell;
	private Text text_nowvalue;
	private Text text_changevalue;
	private String nowvalue;
	private String changevalue;
	private int row;
	private int column;
	
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			Dia_tradechange window = new Dia_tradechange();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Open the window.
	 */
	
	public Dia_tradechange(String nowvalue,int row,int column){
		this.nowvalue = nowvalue;
		this.row = row;
		this.column = column;
	}
	
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
		shell.setSize(497, 103);
		shell.setText("\u4FEE\u6539\u4EA4\u6613\u8BB0\u5F55");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 26, 36, 17);
		lblNewLabel.setText("当前值");
		
		text_nowvalue = new Text(shell, SWT.BORDER);
		text_nowvalue.setText(nowvalue);
		text_nowvalue.setBounds(69, 23, 99, 23);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(191, 26, 36, 17);
		lblNewLabel_1.setText("修改为");
		
		text_changevalue = new Text(shell, SWT.BORDER);
		text_changevalue.setBounds(243, 23, 99, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				changeEvent();
				
			}
		});
		btnNewButton.setBounds(360, 21, 53, 27);
		btnNewButton.setText("确定");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(419, 21, 53, 27);
		btnNewButton_1.setText("取消");

	}

	protected void changeEvent() {
		// TODO Auto-generated method stub
		changevalue = text_changevalue.getText();
		
		if(Dia_trade.table_trade.getItem(row).getText(3).equals("买入")
				&column==5){
		String name = Dia_trade.table_trade.getItem(row).getText(0);
		String code = Dia_trade.table_trade.getItem(row).getText(1);
		int sumber = 0;
		int rows =Dia_trade.table_trade.getItemCount();
		
		for(int tableloop = 0; tableloop<rows; tableloop++){
			if(Dia_trade.table_trade.getItem(tableloop).getText(0).equals(name)
				&Dia_trade.table_trade.getItem(tableloop).getText(1).equals(code)
				&Dia_trade.table_trade.getItem(tableloop).getText(3).equals("卖出")){
				sumber+= -Integer.parseInt(Dia_trade.table_trade.getItem(tableloop).getText(5));
			}
		}
		
		if(Integer.parseInt(changevalue)<sumber){
			JOptionPane.showMessageDialog(null, "买入数量不能小于历史卖出数量");
		}
		}
		else{
		Dia_trade.table_trade.getItem(row).setText(column, changevalue);
		shell.dispose();
		}
	}
}
