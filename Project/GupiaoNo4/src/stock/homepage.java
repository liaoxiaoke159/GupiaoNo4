package stock;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TableColumn;

import datadealer.DataBuilder;


public class homepage {

	//public static final Color bcolor = new Color(Display.getCurrent(),200,0,200);
	protected Shell shell;
	public  static   Text text_search;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	public String[] str= new String[2];
	public String place;
	public String code;
	public static Table table_chicang;
	private String path_file="";
	private static String path_userinfo="C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\用户信息.xls";
	private static String path_trade = "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\交易记录.xls";
	private static Table table_userinfo;
	
	
	public  String get_path_file(){
		
		return this.path_file;
	}
	
    public static   String get_path_userinfo(){
		
		return path_userinfo;
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
//	public static void main(String[] args) {
//		try {
//			homepage window = new homepage();
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
		
		//导入用户信息
		try {
			DataBuilder.tablemaker(path_userinfo,table_userinfo);
			
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		menuItem.setText("\u6587\u4EF6");
		
		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);
		
		
		//导入数据菜单项
		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Source Folder Selection");  
                fd.setFilterExtensions(new String[] {"*.xls"});
				path_file = fd.open();

				try {
					DataBuilder.tablemaker(path_file, table_chicang);
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}


		});
		
		
		mntmNewItem.setText("导入数据");
		
		MenuItem mntmNewItem_2 = new MenuItem(menu_1, SWT.SEPARATOR);
		
		//退出
		MenuItem mntmNewItem_3 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
				messagebox.setText("退出");
				
				if(table_chicang.getItemCount()==0){
				messagebox.setMessage("          没有持仓数据，那我要直接退出咯！");
				}
				else{
					messagebox.setMessage("          确认是否保存数据以退出");
				}
				
				int val = messagebox.open();
				if (val == SWT.YES) {
					// 导出数据

					// 导出持仓表
					if(table_chicang.getItemCount()>1){
					try {
						DataBuilder
								.tableoutputer(
										"C:\\Users\\Administrator\\workspace\\GupiaoNo4\\data\\ceshi.xls",
										table_chicang);
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}

					// 导出用户信息
					if(table_userinfo.getItemCount()==2){
					try {
						DataBuilder.tableoutputer(homepage.get_path_userinfo(),
								homepage.get_table_userinfo());
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
					// 退出程序
					shell.close();
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
				Dia_trade window = new Dia_trade();
				window.open();
			}
		});

		menuItem_1.setText("交易记录");
		
		MenuItem mntmNewCheckbox_3 = new MenuItem(menu_2, SWT.SEPARATOR);
		
		
		//买入
		MenuItem mntmNewCheckbox_1 = new MenuItem(menu_2, SWT.NONE);
		mntmNewCheckbox_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});
		mntmNewCheckbox_1.setText("买入");
		
		
		//卖出
		MenuItem mntmNewCheckbox_2 = new MenuItem(menu_2, SWT.NONE);
		mntmNewCheckbox_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Dia_sell window = new Dia_sell();
				window.open();
			}
		});
		mntmNewCheckbox_2.setText("卖出");
		
		
		//卖空
		MenuItem mntmNewCheckbox_4 = new MenuItem(menu_2, SWT.NONE);
		mntmNewCheckbox_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Dia_shortsell window = new Dia_shortsell();
				window.open();
			}
		});
		mntmNewCheckbox_4.setText("卖空");
		
		
		//补仓
		MenuItem mntmNewCheckbox_5 = new MenuItem(menu_2, SWT.NONE);
		mntmNewCheckbox_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Dia_cover window = new Dia_cover();
				window.open();
			}
		});
		mntmNewCheckbox_5.setText("补仓");
		
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
		
		//搜索按钮
		Button btnNewButton_4 = new Button(shell, SWT.NONE);
		btnNewButton_4.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\b3980112628e7679053ad3621d1ee3a61.png"));
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (text_search.getText().isEmpty()) {
					MessageBox messagebox = new MessageBox(shell, SWT.OK);
					messagebox.setText("提示");
					messagebox.setMessage("请填入交易所和股票代码→如：sh 000025");
					int val = messagebox.open();

				} else {
					str = text_search.getText().split(" ");
					place = str[0];
					code = str[1];
					Dia_info window = new Dia_info(place, code);
					window.open();
				}

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
		tabItem_chicang.setControl(table_chicang);
	//	formToolkit.paintBordersFor(table_chicang);
		table_chicang.setHeaderVisible(false);
		table_chicang.setLinesVisible(false);
		
		
		TableColumn Column_gupiao = new TableColumn(table_chicang, SWT.NONE);
		Column_gupiao.setWidth(74);
		Column_gupiao.setText("股票");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_chicang, SWT.NONE);
		tblclmnNewColumn_5.setWidth(72);
		tblclmnNewColumn_5.setText("股票代码");
		
		TableColumn Column_dangqianjia = new TableColumn(table_chicang, SWT.NONE);
		Column_dangqianjia.setWidth(60);
		Column_dangqianjia.setText("当前价");
		
		TableColumn Column_zhangdie = new TableColumn(table_chicang, SWT.NONE);
		Column_zhangdie.setWidth(100);
		Column_zhangdie.setText("涨跌");
		
		TableColumn Column_diaoluo = new TableColumn(table_chicang, SWT.NONE);
		Column_diaoluo.setWidth(122);
		Column_diaoluo.setText("掉落成本/持仓成本");
		
		TableColumn Column_chiyouliang = new TableColumn(table_chicang, SWT.NONE);
		Column_chiyouliang.setWidth(54);
		Column_chiyouliang.setText("持有量");
		
		TableColumn Column_chiyoushizhi = new TableColumn(table_chicang, SWT.NONE);
		Column_chiyoushizhi.setWidth(100);
		Column_chiyoushizhi.setText("持有市值");
		
		TableColumn Column_fudongyingkui = new TableColumn(table_chicang, SWT.NONE);
		Column_fudongyingkui.setWidth(100);
		Column_fudongyingkui.setText("+浮动盈亏");
		
		TableColumn Column_yingkui = new TableColumn(table_chicang, SWT.NONE);
		Column_yingkui.setWidth(100);
		Column_yingkui.setText("盈亏");
		
		
		
		
		TabItem tabItem_shouyilv = new TabItem(tabFolder, SWT.NONE);
		tabItem_shouyilv.setText("股票收益率");
		
		Composite composite_shouyilv = new Composite(tabFolder, SWT.NONE);
		tabItem_shouyilv.setControl(composite_shouyilv);
		formToolkit.paintBordersFor(composite_shouyilv);
		
		Label lbl_shouyilv = new Label(composite_shouyilv, SWT.NONE);
		lbl_shouyilv.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\shouyilv.png"));
		lbl_shouyilv.setBounds(75, 10, 641, 387);
		formToolkit.adapt(lbl_shouyilv, true, true);
		
		TabItem tabItem_chigu = new TabItem(tabFolder, SWT.NONE);
		tabItem_chigu.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				
			}
		});
		tabItem_chigu.setText("持股构成");
		
		Composite composite_chigu = new Composite(tabFolder, SWT.NONE);
		tabItem_chigu.setControl(composite_chigu);
		formToolkit.paintBordersFor(composite_chigu);
		
		Label lbl_chigu = new Label(composite_chigu, SWT.NONE);
		lbl_chigu.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\chigu.png"));
		lbl_chigu.setBounds(10, 10, 706, 395);
		formToolkit.adapt(lbl_chigu, true, true);
		
		//用户信息表
		table_userinfo = new Table(shell, SWT.FULL_SELECTION);
		table_userinfo.setBounds(112, 25, 393, 42);
//		formToolkit.adapt(table_info);
//		formToolkit.paintBordersFor(table_info);
		table_userinfo.setHeaderVisible(false);
		table_userinfo.setLinesVisible(true);

		
		TableColumn tblclmnNewColumn = new TableColumn(table_userinfo, SWT.NONE);
		tblclmnNewColumn.setWidth(85);
		tblclmnNewColumn.setText("New Column");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table_userinfo, SWT.NONE);
		tblclmnNewColumn_1.setWidth(73);
		tblclmnNewColumn_1.setText("New Column");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table_userinfo, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("New Column");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table_userinfo, SWT.NONE);
		tblclmnNewColumn_3.setWidth(76);
		tblclmnNewColumn_3.setText("New Column");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_userinfo, SWT.NONE);
		tblclmnNewColumn_4.setWidth(59);
		tblclmnNewColumn_4.setText("New Column");
		
		
	}
}
