package stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TableColumn;

import stocker.DataBuilder;
import stocker.Exporter;
import stocker.Importer;
import stocker.Stockown;
import stocker.Stocks;






import stocker.Trade;

import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Group;

public class homepage {

	//public static final Color bcolor = new Color(Display.getCurrent(),200,0,200);
	public Shell shell;
	public  static   Text text_search;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	public String[] str= new String[2];
	public String place;
	public String code;
	public static Table table_chicang;
	private String path_file="";
	public static String path_userinfo="C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\用户信息.xls";
	public static String path_trade = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\交易记录.xls";
	private static String path_chicang ="C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\当前持仓.xls";
	public static  String path_chigu = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data";
	public static Table table_userinfo;
	
	public static Label lbl_shouyilv;
	public static Label lbl_chigu;
	
	public static Stockown stkl = new Stockown();
	public static boolean isimport = false;
	public static int  tableindex;

	
	public static int getindex(){
		return tableindex;
	}
	public  String get_path_file(){
		
		return this.path_file;
	}
	
	
	
    public static   String get_path_userinfo(){
		
		return path_userinfo;
	}
    public static String get_path_chicang(){
    	return path_chicang;
    }
    public static   String get_path_trade(){
		
		return path_trade;
	}
	public static  Table get_table_chicang(){
		
		return table_chicang;
	}
	
	public static  Table get_table_userinfo(){
		
		return table_userinfo;
	}
	
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			homepage window = new homepage();
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
		

		

		
		shell.open();
		shell.layout();
		
//		Timer timer;
//		timer = new Timer();				
//		timer.schedule(new TimerTask(){
//			public void run() {  
//				
//				
//				
//        }  }, 1000,5*1000);
		
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
		shell.setToolTipText("");
		shell.setSize(770, 643);
		shell.setText("\u7092\u80A1\u795E\u5668v1.0");//设置首页窗口
		/*shell.setBackground(bcolor); //设置父控件的背景色
		  shell.setBackgroundMode(SWT.INHERIT_DEFAULT); */ //设置父控件的背景模式，即所有的子控件采用父控件的背景色
		
		Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);

		
		//产生菜单以及菜单项
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("文件");
		
		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);
		
		
		//导入数据菜单项
		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(isimport){
					JOptionPane.showMessageDialog(null, "不用重复导入数据喔");  
				}
				else{
						
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Source Folder Selection");  
                fd.setFilterExtensions(new String[] {"*.xls"});
				path_file = fd.open();
				
				if(path_file==null){
					JOptionPane.showMessageDialog(null, "请选择一个文件");
				}
				else{
				//创建一个导入数据类
				Importer importer = new Importer(path_file);
				  importer.creat();
				  }
				 
				  
				}									
			}
		});
		
		
		mntmNewItem.setText("导入数据");
		
		MenuItem mntmNewItem_4 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isimport){
		
					 
					Exporter exporter = new Exporter();
					exporter.creat();
					JOptionPane.showMessageDialog(null, "已保存用户数据");
				
				}
				else{
					JOptionPane.showMessageDialog(null, "还没导入数据喔");
				}
			}
		});
		mntmNewItem_4.setText("保存数据");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (isimport) {

					Importer.userinfo_table_change(table_userinfo,
							stkl.stockslist);
					// 持仓table初始化
					Importer.table_chiang_initial(stkl.stockslist,
							table_chicang);
					JOptionPane.showMessageDialog(null, "已更新到最新股情");

				} else {
					JOptionPane.showMessageDialog(null, "还没导入数据喔");
				}
				

			}
		});
		
		mntmNewItem_1.setText("刷新");
		
		MenuItem mntmNewItem_5 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(isimport){
					MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
				messagebox.setText("初始化");
					messagebox.setMessage("          确认是否保存数据以初始化界面");
					int val = messagebox.open();
				if (val == SWT.YES) {
					
					// 创建导出数据类
					Exporter export = new Exporter();
					export.creat();
					
					//擦除表格
					table_chicang.removeAll();
					table_userinfo.removeAll();
					
					//清空搜索框
					homepage.text_search.setText("");
					
					//清空stockslist表
					stkl.stockslist.removeAll(stkl.stockslist);
					
					lbl_shouyilv.setImage(new Image(Display.getDefault(), "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\qingchu.png"));
					lbl_chigu.setImage(new Image(Display.getDefault(), "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\qingchu.png"));
					//标记成没有导入数据
					isimport = false;
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "无需初始化界面");
				}
				
			}
		});
		mntmNewItem_5.setText("初始化界面");
		
		MenuItem mntmNewItem_2 = new MenuItem(menu_1, SWT.SEPARATOR);
		
		//退出
		MenuItem mntmNewItem_3 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
				messagebox.setText("退出");

				if (table_userinfo.getItemCount() == 0) {
					messagebox.setMessage("          没有用户数据，那我要直接退出咯！");
					int val = messagebox.open();
					if (val == SWT.YES){
						shell.dispose();
					}
					
				} else {
					messagebox.setMessage("          确认是否保存数据以退出");
					int val = messagebox.open();
					if (val == SWT.YES) {

						// 创建导出数据类以保存数据
						Exporter export = new Exporter();
						export.creat();
						// 退出程序
						shell.dispose();
					}
				}

			}
		});
		mntmNewItem_3.setText("退出");
		
		MenuItem mntmNewCheckbox = new MenuItem(menu, SWT.CASCADE);
		mntmNewCheckbox.setText("\u4EA4\u6613");
		
		Menu menu_2 = new Menu(mntmNewCheckbox);
		mntmNewCheckbox.setMenu(menu_2);
		
		MenuItem menuItem_1 = new MenuItem(menu_2, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(homepage.isimport){
					Dia_trade window = new Dia_trade();
				window.open();
				}
				else{
					JOptionPane.showMessageDialog(null, "请先导入数据");
				}
				
			}
		});

		menuItem_1.setText("交易记录");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.CASCADE);
		menuItem_2.setText("用户");
		
		Menu menu_3 = new Menu(menuItem_2);
		menuItem_2.setMenu(menu_3);
		
		MenuItem menuItem_3 = new MenuItem(menu_3, SWT.NONE);
		menuItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Userinfochange window = new Userinfochange();
				window.open();
			}
		});
		menuItem_3.setText("修改");
		
		//搜索文本框
		text_search = new Text(shell, SWT.BORDER | SWT.H_SCROLL | SWT.SEARCH
				| SWT.CANCEL);
		text_search.setTouchEnabled(true);
		text_search.setToolTipText("");
		text_search.setBounds(585, 29, 104, 23);
		
	    text_search.addTraverseListener(new TraverseListener() {  
	        public void keyTraversed(TraverseEvent e) {  
	        	if (e.keyCode == 16777296 |e.keyCode == 13) {  
	        				e.doit = true;
	                        searchevent();
	        	}
	        }  
	      });
		
		//搜索按钮
		Button btnNewButton_4 = new Button(shell, SWT.NONE);
		btnNewButton_4.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\b3980112628e7679053ad3621d1ee3a61.png"));
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
						searchevent();

			}
		});
		btnNewButton_4.setBounds(700, 25, 31, 30);
		
		Label lbl_logo = new Label(shell, SWT.NONE);
		lbl_logo.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\9a9a9420b933b340ec46a21eb5cd4303.png"));
		lbl_logo.setBounds(10, 7, 83, 78);
				
		
		//切换显示的几个tab按钮
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabFolder.setBounds(20, 96, 724, 464);
		
		TabItem tabItem_chicang = new TabItem(tabFolder, SWT.NONE);
		tabItem_chicang.setText("当前股票持仓");
		
		table_chicang = new Table(tabFolder, SWT.FULL_SELECTION);
		table_chicang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
				 Point pt = new Point(e.x, e.y); 
				 int i = 0;  
				 while(i<table_chicang.getItemCount()){
					 TableItem item = table_chicang.getItem(i);
					 for(int j = 0; j<table_chicang.getColumnCount();j++){
						 Rectangle rect = item.getBounds(j); 
						 if(rect.contains(pt)){
							 
							 tableindex = i;//获取该行数tableindex
							 break ; 
						 }
					 }
					 i++;	 
				 }
				 
				 Dia_info window = new Dia_info(table_chicang.getItem(tableindex).getText(2),
                    		table_chicang.getItem(tableindex).getText(1),1);
                     window.open();
	      
		}
		});

		
		Group group = new Group(shell, SWT.NONE);
		group.setText("用户信息");
		group.setBounds(127, 7, 434, 78);
		tabItem_chicang.setControl(table_chicang);
	//	formToolkit.paintBordersFor(table_chicang);
		table_chicang.setHeaderVisible(true);
		table_chicang.setLinesVisible(false);
		//用户信息表
		table_userinfo = new Table(group, SWT.FULL_SELECTION);
		table_userinfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(isimport==true){
				Dia_userinfo window = new Dia_userinfo();
				window.open();}
				else{
					JOptionPane.showMessageDialog(null, "还未导入数据喔");  
				}
			}
		});
		table_userinfo.setBounds(10, 22, 411, 42);
		table_userinfo.setHeaderVisible(false);
		table_userinfo.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table_userinfo, SWT.NONE);
		tblclmnNewColumn.setWidth(85);
		tblclmnNewColumn.setText("New Column");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table_userinfo,
				SWT.NONE);
		tblclmnNewColumn_1.setWidth(73);
		tblclmnNewColumn_1.setText("New Column");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table_userinfo,
				SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("New Column");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table_userinfo,
				SWT.NONE);
		tblclmnNewColumn_3.setWidth(76);
		tblclmnNewColumn_3.setText("New Column");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table_userinfo,
				SWT.NONE);
		tblclmnNewColumn_4.setWidth(77);
		tblclmnNewColumn_4.setText("New Column");

		TableColumn Column_gupiao = new TableColumn(table_chicang, SWT.NONE);
		Column_gupiao.setWidth(82);
		Column_gupiao.setText("股票");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_chicang, SWT.NONE);
		tblclmnNewColumn_5.setWidth(62);
		tblclmnNewColumn_5.setText("股票代码");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table_chicang, SWT.NONE);
		tblclmnNewColumn_7.setWidth(51);
		tblclmnNewColumn_7.setText("交易所");
		
		TableColumn Column_dangqianjia = new TableColumn(table_chicang, SWT.NONE);
		Column_dangqianjia.setWidth(70);
		Column_dangqianjia.setText("当前价");
		
		TableColumn Column_zhangdie = new TableColumn(table_chicang, SWT.NONE);
		Column_zhangdie.setWidth(50);
		Column_zhangdie.setText("涨跌");
		
		TableColumn Column_chiyouliang = new TableColumn(table_chicang, SWT.NONE);
		Column_chiyouliang.setWidth(61);
		Column_chiyouliang.setText("持有量");
		
		TableColumn Column_chiyoushizhi = new TableColumn(table_chicang, SWT.NONE);
		Column_chiyoushizhi.setWidth(100);
		Column_chiyoushizhi.setText("持有市值");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table_chicang, SWT.NONE);
		tblclmnNewColumn_6.setWidth(68);
		tblclmnNewColumn_6.setText("成本价");
		
		TableColumn Column_fudongyingkui = new TableColumn(table_chicang, SWT.NONE);
		Column_fudongyingkui.setWidth(88);
		Column_fudongyingkui.setText("浮动盈亏比例");
		
		TableColumn Column_yingkui = new TableColumn(table_chicang, SWT.NONE);
		Column_yingkui.setWidth(82);
		Column_yingkui.setText("盈亏");	
		
		TabItem tabItem_shouyilv = new TabItem(tabFolder, SWT.NONE);
		tabItem_shouyilv.setText("股票收益率");
		
		Composite composite_shouyilv = new Composite(tabFolder, SWT.NONE);
		tabItem_shouyilv.setControl(composite_shouyilv);
		formToolkit.paintBordersFor(composite_shouyilv);
		
	     lbl_shouyilv = new Label(composite_shouyilv, SWT.NONE);
		lbl_shouyilv.setBounds(0, 10, 716, 414);
		formToolkit.adapt(lbl_shouyilv, true, true);
		
		TabItem tabItem_chigu = new TabItem(tabFolder, SWT.NONE);

		tabItem_chigu.setText("持股构成");
		
		Composite composite_chigu = new Composite(tabFolder, SWT.NONE);
		tabItem_chigu.setControl(composite_chigu);
		formToolkit.paintBordersFor(composite_chigu);
		
		lbl_chigu = new Label(composite_chigu, SWT.NONE);
		lbl_chigu.setBounds(0, 10, 706, 414);
		formToolkit.adapt(lbl_chigu, true, true);
		
	

	}
	protected void searchevent() {
		// TODO Auto-generated method stub
		 
	            // e.detail = SWT.TRAVERSE_TAB_NEXT;  
	              
		
	            
				if (text_search.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "亲，总要输点东西吧");
				} 
				else if( text_search.getText().length() != 9
						||!( text_search.getText().substring(0, 2).equals("sz")|text_search.getText().substring(0, 2).equals("sh" ))
						||!Userinfochange.isNumeric(text_search.getText().substring(3, 9))){
					
					JOptionPane.showMessageDialog(null, "请按格式输入交易所(sz和sh)和股票代码→如：sh 000025");
				}else {
					//System.out.println( text_search.getText().substring(3, 9));
					str = text_search.getText().split(" ");
					place = str[0];
					code = str[1];
					Dia_info window = new Dia_info(place, code,0);//0用来标记买入操作
					window.open();
				}

			
	          }  
}
