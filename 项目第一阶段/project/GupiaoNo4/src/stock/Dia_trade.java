package stock;
import java.io.IOException;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import stocker.DataBuilder;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;


public class Dia_trade {

	protected Shell shlSw;
	public static Table table_trade;
	private String path_file=homepage.get_path_trade();
	protected int row;
	protected int column;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Dia_trade window = new Dia_trade();
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
		tablemaker();
		shlSw.open();
		shlSw.layout();
		while (!shlSw.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	
	private void tablemaker() {
		// TODO Auto-generated method stub
		try {
			DataBuilder.tablemaker(path_file, table_trade);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSw = new Shell();
		shlSw.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\chaogushenqi.png"));
		shlSw.setSize(596, 455);
		shlSw.setText("\u4EA4\u6613\u8BB0\u5F55");
		
		table_trade = new Table(shlSw, SWT.FULL_SELECTION);
		table_trade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
				Point pt = new Point(e.x, e.y); 
				 int i = 0;  
				 while(i<table_trade.getItemCount()){
					 TableItem item = table_trade.getItem(i);
					 for(int j = 0; j<table_trade.getColumnCount();j++){
						 Rectangle rect = item.getBounds(j); 
						 if(rect.contains(pt)){
							 
							 row = i;
							 column = j;
							 break ; 
							 
						 }
					 }
					 i++;	 
				 }
				 
				 if(row != 0 && column!=0 && column!=1){
					 
					 Dia_tradechange window = new Dia_tradechange(table_trade.getItem(row).getText(column)
						,row,column);
				                window.open();
				 }
				 if(row == table_trade.getItemCount()-1 &&column==0){
					 MessageBox messagebox = new MessageBox(shlSw, SWT.YES | SWT.NO);
						messagebox.setText("初始化");
							messagebox.setMessage("          确认是否撤销此次交易记录");
							int val = messagebox.open();
						if (val == SWT.YES) {
							
							
							for(int tableloop = 0;tableloop < homepage.table_chicang.getItemCount();tableloop++){
								if(homepage.table_chicang.getItem(tableloop).getText(1).
										equals(table_trade.getItem(row).getText(1))){
									homepage.table_chicang.remove(tableloop);
								}
							}
							table_trade.remove(row);
							
							homepage.table_userinfo.getItem(1).setText(3, Integer.toString(homepage.table_chicang.getItemCount()));
						}
				 }
				
			}
		});
		table_trade.setBounds(0, 0, 579, 417);
		table_trade.setHeaderVisible(false);
		table_trade.setLinesVisible(false);
		
		TableColumn tableColumn = new TableColumn(table_trade, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("股票名称");
		
		TableColumn tableColumn_2 = new TableColumn(table_trade, SWT.CENTER);
		tableColumn_2.setWidth(99);
		tableColumn_2.setText("股票编号");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table_trade, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("日期");
		
		TableColumn tableColumn_1 = new TableColumn(table_trade, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("类型");
		
		TableColumn tableColumn_4 = new TableColumn(table_trade, SWT.CENTER);
		tableColumn_4.setWidth(78);
		tableColumn_4.setText("价格");
		
		TableColumn tableColumn_5 = new TableColumn(table_trade, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("数量");
		

		

	}
}
