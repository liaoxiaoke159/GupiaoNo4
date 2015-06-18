package stock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TableColumn;

import stocker.DataDealer;

import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;


public class homepage {

	public Shell shell=new Shell();
	//private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	public String[] str= new String[2];
	public String place;
	public String code;
	
	public static CTabFolder tabFolder_sum;
	public static List<String> path_trade = new ArrayList<String>(); //"C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\交易记录.xls";
	public static  String path_chigu = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data";
	public static List<Table> table_userinfos = new ArrayList<Table>();
	public static List<Table> table_chicangs = new ArrayList<Table>();
	public  Table  table_userinfo ;
	public  Table  table_chicang;
	public static boolean isimport = false;
	public static int importnum = 0;
	public static int  tableindex;
	public static List<Label> lbl_shouyilvs = new ArrayList<Label>();
	public static List<Label> lbl_chigus = new ArrayList<Label>();
	public static int getindex(){
		return tableindex;
	}
	public Text text_search;
	public static Label lbl_notice;
	public Display display;
	public static String path_account = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\path_account.xls";
	public static List<CTabItem> ctabitems = new ArrayList<CTabItem>();
	private CTabItem tabItem;
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
		 display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		Account_instital();
		 //tabFolder_sum.set;
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		
	}

	private void Account_instital() {
		// TODO Auto-generated method stub

		Workbook wrb = null;

		try {
			wrb = Workbook.getWorkbook(new File(path_account));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return;
		}

		Sheet sheet = wrb.getSheet(0);
		for (int loop = 0; loop < sheet.getRows(); loop++) {
			String path_file = sheet.getCell(0, loop).getContents();
			path_trade.add(path_file);
			importnum++;
			tabitemAdder();
			// 创建一个导入数据类
			DataDealer importer = new DataDealer(path_file,
					tabFolder_sum.getSelectionIndex());
			if (importer.creat()) {
				lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
				lbl_notice.setText("数据导入成功！");
			    isimport = true;	
			}
			else{
				lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				lbl_notice.setText("*账户"+(tabFolder_sum.getSelectionIndex()+1)+"导入失败：数据非法");
			}
			
		}

		
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chaogushenqi.png"));
		shell.setToolTipText("");
		shell.setSize(759, 633);
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
		
		MenuItem menuItem_file = new MenuItem(menu, SWT.CASCADE);
		menuItem_file.setText("账户");
		
		Menu menu_file = new Menu(menuItem_file);
		menuItem_file.setMenu(menu_file);
		
		
		//导入数据菜单项
		MenuItem mntm_im = new MenuItem(menu_file, SWT.NONE);
		mntm_im.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				importEvent();
													
			}
		});
		
		
		mntm_im.setText("导入账户");
		
		MenuItem mntm_newaccount = new MenuItem(menu_file, SWT.NONE);
		mntm_newaccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				newAccountEvent();
				}
		});
		mntm_newaccount.setText("创建账户");
		
		MenuItem mntm_manageaccount = new MenuItem(menu_file, SWT.NONE);
		mntm_manageaccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Accountmanage account = new Accountmanage(path_trade);
				if(account.open()){
					newAccountEvent();
				}
			}
		});
		mntm_manageaccount.setText("管理");
		
		MenuItem mntm_refresh = new MenuItem(menu_file, SWT.NONE);
		mntm_refresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (isimport) {
					lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					lbl_notice.setText("正在更新...");
					DataDealer datadealer = new DataDealer(path_trade.get(tabFolder_sum.getSelectionIndex()),tabFolder_sum.getSelectionIndex());
					datadealer.creat();
					lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
					  lbl_notice.setText("更新成功！");

				} else {
					lbl_notice.setText("*没有导入数据");
					lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				
				}
				

			}
		});
		
		mntm_refresh.setText("刷新");
		
		@SuppressWarnings("unused")
		MenuItem mntmNewItem_separator = new MenuItem(menu_file, SWT.SEPARATOR);
		
		//退出
		MenuItem mntm_exit = new MenuItem(menu_file, SWT.NONE);
		mntm_exit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
				messagebox.setText("退出");

				if (table_userinfos.size()==0||table_userinfos.get(tabFolder_sum.getSelectionIndex()).getItemCount() == 0) {
					messagebox.setMessage("          没有用户数据，那我要直接退出咯！");
					int val = messagebox.open();
					if (val == SWT.YES){
						shell.dispose();
					}
					
				} else {
					messagebox.setMessage("          确认是否保存数据以退出");
					int val = messagebox.open();
					if (val == SWT.YES) {

						
						// 退出程序
						shell.dispose();
					}
				}

			}
		});
		mntm_exit.setText("退出");
		
		MenuItem menu_trade = new MenuItem(menu, SWT.CASCADE);
		menu_trade.setText("交易");
		
		Menu menu_1 = new Menu(menu_trade);
		menu_trade.setMenu(menu_1);
		
		MenuItem mnItem_traderecord = new MenuItem(menu_1, SWT.NONE);
		mnItem_traderecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isimport){
				Dia_trade window = new Dia_trade(tabFolder_sum.getSelectionIndex());
				window.open();
				}
				else{
					lbl_notice.setText("*没有导入数据");
					lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
			}
		});
		mnItem_traderecord.setText("交易记录");
		
		@SuppressWarnings("unused")
		MenuItem mnItem_separator = new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mnItem_buy = new MenuItem(menu_1, SWT.NONE);
		mnItem_buy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isimport){
				Dia_buy window = new Dia_buy(tabFolder_sum.getSelectionIndex());
				window.open();
				}
				else{
					lbl_notice.setText("*没有导入数据");
					lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
			}
		});
		mnItem_buy.setText("买入");
		
		MenuItem mnItem_sell = new MenuItem(menu_1, SWT.NONE);
		mnItem_sell.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isimport){
					Dia_sell window = new Dia_sell(tabFolder_sum.getSelectionIndex());
					window.open();
					}
				else{
					lbl_notice.setText("*没有导入数据");
					lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
			}
		});
		mnItem_sell.setText("卖出");
		
		MenuItem mnItem_shortsell = new MenuItem(menu_1, SWT.NONE);
		mnItem_shortsell.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isimport){
					Dia_shortsell window = new Dia_shortsell(tabFolder_sum.getSelectionIndex());
					window.open();
					}else{
						lbl_notice.setText("*没有导入数据");
						lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					}
			}
		});
		mnItem_shortsell.setText("卖空");
		
		MenuItem mnItem_cover = new MenuItem(menu_1, SWT.NONE);
		mnItem_cover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isimport){
				Dia_cover window = new Dia_cover(tabFolder_sum.getSelectionIndex());
				window.open();
				}
				else{
					lbl_notice.setText("*没有导入数据");
					lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				
			}
		});
		mnItem_cover.setText("补仓");
		
		MenuItem menu_change = new MenuItem(menu, SWT.CASCADE);
		menu_change.setText("修改");
		
		Menu menu_2 = new Menu(menu_change);
		menu_change.setMenu(menu_2);
		
		MenuItem mnItem_fundchange = new MenuItem(menu_2, SWT.NONE);
		mnItem_fundchange.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isimport){
				Userinfochange window = new Userinfochange(tabFolder_sum.getSelectionIndex());
				window.open();
				}
				else{
					lbl_notice.setText("*没有导入数据");
					lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				
			}
		});
		mnItem_fundchange.setText("修改资金");
	    
		tabFolder_sum = new CTabFolder(shell, SWT.NONE);
	    tabFolder_sum.setBounds(0, 0, 741, 545);
	    tabFolder_sum.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
	    
	    lbl_notice = new Label(shell, SWT.BORDER);
	    lbl_notice.setBounds(565, 551, 168, 17);
	    
	   
	     	
       Listener listener = new Listener() {  
           boolean drag = false;  
           boolean exitDrag = false;  
           CTabItem dragItem;  
           Cursor cursorSizeAll = new Cursor(null, SWT.CURSOR_SIZEALL);  
           Cursor cursorIbeam = new Cursor(null, SWT.CURSOR_NO);  
           Cursor cursorArrow = new Cursor(null, SWT.CURSOR_ARROW);  
 
           public void handleEvent(Event e) {  
               Point p = new Point(e.x, e.y);  
               if (e.type == SWT.DragDetect) {  
                   p = tabFolder_sum.toControl(display.getCursorLocation()); // see eclipse bug 43251  
               }  
               switch (e.type) {  
                   // 拖拉Tab  
                   case SWT.DragDetect: {  
                       CTabItem item = tabFolder_sum.getItem(p);  
                       if (item == null) {  
                           return;  
                       }  
                         
                       drag = true;  
                       exitDrag = false;  
                       dragItem = item;  
                         
                       // 换鼠标形状  
                       tabFolder_sum.getShell().setCursor(cursorIbeam);  
                       break;  
                   }  
                   // 鼠标进入区域  
                   case SWT.MouseEnter:  
                       if (exitDrag) {  
                           exitDrag = false;  
                           drag = e.button != 0;  
                       }  
                       break;  
                   // 鼠标离开区域  
                   case SWT.MouseExit:  
                       if (drag) {  
                           tabFolder_sum.setInsertMark(null, false);  
                           exitDrag = true;  
                           drag = false;  
                             
                           // 换鼠标形状  
                           tabFolder_sum.getShell().setCursor(cursorArrow);  
                       }  
                       break;  
                   // 松开左键  
                   case SWT.MouseUp: {  
                       if (!drag) {  
                           return;  
                       }  
                       tabFolder_sum.setInsertMark(null, false);  
                       CTabItem item = tabFolder_sum.getItem(new Point(p.x, 1));  
                         
                       if (item != null) {  
                           int index = tabFolder_sum.indexOf(item);  
                           int newIndex = tabFolder_sum.indexOf(item);  
                           int oldIndex = tabFolder_sum.indexOf(dragItem);  
                           if (newIndex != oldIndex) {  
                               boolean after = newIndex > oldIndex;  
                               index = after ? index + 1 : index/* - 1*/;  
                               index = Math.max(0, index);  
                                 
                               CTabItem newItem = new CTabItem(tabFolder_sum, SWT.NONE, index);  
                               newItem.setText(dragItem.getText());  
                                 
                               Control c = dragItem.getControl();  
                               dragItem.setControl(null);  
                               newItem.setControl(c);  
                               dragItem.dispose();  
                                 
                               tabFolder_sum.setSelection(newItem);  
                                 
                           }  
                       }  
                       drag = false;  
                       exitDrag = false;  
                       dragItem = null;  
                         
                       // 换鼠标形状  
                       tabFolder_sum.getShell().setCursor(cursorArrow);  
                       break;  
                   }  
                   // 鼠标移动  
                   case SWT.MouseMove: {  
                       if (!drag) {  
                           return;  
                       }  
                       CTabItem item = tabFolder_sum.getItem(new Point(p.x, 2));  
                       if (item == null) {  
                           tabFolder_sum.setInsertMark(null, false);  
                           return;  
                       }  
                       Rectangle rect = item.getBounds();  
                       boolean after = p.x > rect.x + rect.width / 2;  
                       tabFolder_sum.setInsertMark(item, after);  
                         
                       // 换鼠标形状  
                       tabFolder_sum.getShell().setCursor(cursorSizeAll);  
                       break;  
                   }  
               }  
           }  
       };  
       tabFolder_sum.addListener(SWT.DragDetect, listener);  
       tabFolder_sum.addListener(SWT.MouseUp, listener);  
       tabFolder_sum.addListener(SWT.MouseMove, listener);  
       tabFolder_sum.addListener(SWT.MouseExit, listener);  
       tabFolder_sum.addListener(SWT.MouseEnter, listener);  
       
	}

	
	

	protected void newAccountEvent() {
		// TODO Auto-generated method stub
		NewAccount na = new NewAccount();
		String path_file = na.open();
		if (path_file != null) {
			

			path_trade.add(path_file);
			importnum++;
			tabitemAdder();
			// 创建一个导入数据类
			DataDealer importer = new DataDealer(path_file,
					tabFolder_sum.getSelectionIndex());
			if (importer.creat()) {
				lbl_notice.setForeground(SWTResourceManager
						.getColor(SWT.COLOR_GREEN));
				lbl_notice.setText("创建成功！");
			}

			isimport = true;
			savepath(path_file,path_account);
		}
		
	}
	

	protected void importEvent() {
		// TODO Auto-generated method stub
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
		fd.setText("选择一个账户数据");  
        fd.setFilterExtensions(new String[] {"*.xls"});
		String path_file = fd.open();
		
		if(path_file==null){
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			lbl_notice.setText("*请选择一个账户数据");
		}
		else{
			
			lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			lbl_notice.setText("正在导入数据...");
			
			path_trade.add(path_file);
			importnum++;	
			tabitemAdder();
		  //创建一个导入数据类
		  DataDealer importer = new DataDealer(path_file,tabFolder_sum.getSelectionIndex());
		 if( importer.creat()){
		  lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		  lbl_notice.setText("数据导入成功！");
		  
		  isimport =true;
		  
		  //把路径保存起来
		  savepath(path_file,path_account);
		 }
		 else{
			 lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			  lbl_notice.setText("*账户"+(tabFolder_sum.getSelectionIndex()+1)+"导入失败：数据非法");
		 }
		 
		  }
	}

	public void tabitemAdder() {
		
	     tabItem = new CTabItem(tabFolder_sum, SWT.NONE);
	   String str = path_trade.get(importnum-1);
	    tabItem.setText(str.substring(str.lastIndexOf("\\")+1,str.indexOf(".xls")));
	    tabFolder_sum.setSelection(tabItem);
	    Composite composite = new Composite(tabFolder_sum, SWT.NONE);
	    tabItem.setControl(composite);
	    ctabitems.add(tabItem);
	   
	   
	    Label label = new Label(composite, SWT.NONE);
	    label.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\钱袋.png"));
	    label.setBounds(10, 10, 83, 78);
	    
	    Group group = new Group(composite, SWT.NONE);
	    group.setText("\u7528\u6237\u4FE1\u606F");
	    group.setBounds(107, 10, 434, 78);
	    
	     table_userinfo = new Table(group, SWT.FULL_SELECTION);
	     table_userinfo.addMouseListener(new MouseAdapter() {
	     	@Override
	     	public void mouseDown(MouseEvent e) {
	     		Dia_userinfo window = new Dia_userinfo(tabFolder_sum.getSelectionIndex());
	     		window.open();
	     	}
	     });
	   
	    table_userinfo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
	    table_userinfo.setLinesVisible(true);
	    table_userinfo.setHeaderVisible(false);
	    table_userinfo.setBounds(10, 26, 411, 42);
	    
	    TableColumn tableColumn = new TableColumn(table_userinfo, SWT.NONE);
	    tableColumn.setWidth(85);
	    tableColumn.setText("New Column");
	    
	    TableColumn tableColumn_1 = new TableColumn(table_userinfo, SWT.NONE);
	    tableColumn_1.setWidth(73);
	    tableColumn_1.setText("New Column");
	    
	    TableColumn tableColumn_2 = new TableColumn(table_userinfo, SWT.NONE);
	    tableColumn_2.setWidth(100);
	    tableColumn_2.setText("New Column");
	    
	    TableColumn tableColumn_3 = new TableColumn(table_userinfo, SWT.NONE);
	    tableColumn_3.setWidth(76);
	    tableColumn_3.setText("New Column");
	    
	    TableColumn tableColumn_4 = new TableColumn(table_userinfo, SWT.NONE);
	    tableColumn_4.setWidth(77);
	    tableColumn_4.setText("New Column");
	    table_userinfos.add(table_userinfo);
	    
	     text_search = new Text(composite, SWT.BORDER | SWT.H_SCROLL | SWT.SEARCH | SWT.CANCEL);
	    text_search.setTouchEnabled(true);
	    text_search.setToolTipText("");
	    text_search.setBounds(570, 45, 104, 23);
	    
	    Button btn_search = new Button(composite, SWT.NONE);
	    btn_search.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseDown(MouseEvent e) {
	    		
	    		btn_researchEvent(text_search);
	    	}
	    });
	    btn_search.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\搜索.png"));
	    btn_search.setBounds(680, 41, 31, 30);
	    
	    CTabFolder tabFolder = new CTabFolder(composite, SWT.NONE);
	    tabFolder.setBounds(10, 110, 717, 397);
	    tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
	    
	    CTabItem tabItem_chicang = new CTabItem(tabFolder, SWT.NONE);
	    tabItem_chicang.setText("当前持仓");
	    tabFolder.setSelection(tabItem_chicang);
	    Table table_chicang = new Table(tabFolder, SWT.FULL_SELECTION);
	    tabItem_chicang.setControl(table_chicang);
	    table_chicang.setHeaderVisible(true);
	    table_chicang.setLinesVisible(false);
	   
	    table_chicang.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
	    
	    
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
		Column_yingkui.setWidth(76);
		Column_yingkui.setText("盈亏");	
		table_chicangs.add(table_chicang);
		
		table_chicangs.get(tabFolder_sum.getSelectionIndex()).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
				 Point pt = new Point(e.x, e.y); 
				 int i = 0;  
				 while(i<table_chicangs.get(tabFolder_sum.getSelectionIndex()).getItemCount()){
					 TableItem item = table_chicangs.get(tabFolder_sum.getSelectionIndex()).getItem(i);
					 for(int j = 0; j<table_chicangs.get(tabFolder_sum.getSelectionIndex()).getColumnCount();j++){
						 Rectangle rect = item.getBounds(j); 
						 if(rect.contains(pt)){
							 
							 tableindex = i;//获取该行数tableindex
							 break ; 
						 }
					 }
					 i++;	 
				 }
				 
				 Dia_info window = new Dia_info(table_chicangs.get(tabFolder_sum.getSelectionIndex()).getItem(tableindex).getText(2),
						 table_chicangs.get(tabFolder_sum.getSelectionIndex()).getItem(tableindex).getText(1),1,tabFolder_sum.getSelectionIndex());
                     window.open();
	      
		}
		});
		CTabItem tabItem_shouyilv = new CTabItem(tabFolder, SWT.NONE);
		tabItem_shouyilv.setText("股票收益率");
		
		Composite composite_shouyilv = new Composite(tabFolder, SWT.NONE);
		tabItem_shouyilv.setControl(composite_shouyilv);
		
	    Label lbl_shouyilv = new Label(composite_shouyilv, SWT.NONE);
		lbl_shouyilv.setBounds(0, 0, 713, 369);
		 lbl_shouyilvs.add(lbl_shouyilv);
		 
		CTabItem tabItem_chigu = new CTabItem(tabFolder, SWT.NONE);
		tabItem_chigu.setText("持股构成");
		
		Composite composite_chigu = new Composite(tabFolder, SWT.NONE);
		tabItem_chigu.setControl(composite_chigu);
		
		Label lbl_chigu = new Label(composite_chigu, SWT.NONE);
		lbl_chigu.setBounds(0, 0, 709, 369);
		lbl_chigus.add(lbl_chigu);
	     		
	}





	public void btn_researchEvent(Text text_search) {
		// TODO Auto-generated method stub
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
			Dia_info window = new Dia_info(place, code,0, tabFolder_sum.getSelectionIndex());//0用来标记买入操作
			window.open();
		}
		
	}
	
	// 保存路径
	public  void savepath(String path_file,String path_account) {
		// TODO Auto-generated method stub
		WritableWorkbook wrb = null;
		Workbook wb = null;
		try {
            wb = Workbook.getWorkbook(new File(path_account));
			wrb = Workbook.createWorkbook(new File(path_account), wb);
		} catch (IOException | BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WritableSheet sheet = wrb.getSheet(0);
		jxl.write.Label label = new jxl.write.Label(0, sheet.getRows(),path_file);

		try {
			sheet.addCell(label);
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			wrb.write();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			wrb.close();
		} catch (WriteException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
