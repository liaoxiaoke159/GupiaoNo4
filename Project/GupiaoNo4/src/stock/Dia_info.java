package stock;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import stocker.DataBuilder;



public class Dia_info {

	protected Shell shell;
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private static String code;//代码				
	public String place;//交易所 sh sz
	private String path=new String("C:\\Users\\Administrator\\Desktop\\搜索包");//保存的路径
	private String spath;
	public String[] information=new String[31];
	private int i;
	/**
	 * Launch the application.
	 * @param args
	 */
	public Dia_info(String place ,String code,int i){
		this.place=place;
		this.code=code;
		this.i=i;
		this.spath=code+".jpg";
		
		System.out.println(this.place+" "+this.code+"(Dia_fo 57)");
		System.out.println();
		
		try {
			
			information=Internet.share.Internet.getSharedata(place, code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "请检查网络", "异常", JOptionPane.ERROR_MESSAGE);
		    
		}
	}
	
	public static String getcode() {
		// TODO Auto-generated method stub
		if(code!=null){
		return code;
		}
		else{
			return "000000";
		}
		
		
	}
	
//	public static void main(String[] args) {
//		String place="sz";
//		String code="000025";
//		try {
//			Dia_info window = new Dia_info(place,code,1);
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//   }

	
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
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\\u641C\u7D22\u5305\\chaogushenqi.png"));

		shell.setSize(585, 514);
		shell.setText("\u80A1\u7968\u4FE1\u606F");
		


		
		String name=information[0];
		String[] names=new String[2];
		System.out.println("搜索出的股票名:"+name+"(Dia_info 126)");
		if(name==null){
			
			return;
		}
		names=name.split("\"");
		name=names[1];
			
		Label lbl_sharename = new Label(shell, SWT.NONE);
		lbl_sharename.setBounds(10, 10, 61, 17);
		lbl_sharename.setText(name);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String time = df.format(new Date());
		
		Label lbl_price = new Label(shell, SWT.NONE);
		lbl_price.setBounds(10, 33, 61, 17);
		lbl_price.setText(information[3]);
		
		Button btn_trade = new Button(shell, SWT.NONE);
		btn_trade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {	
				
				if(i==0){
					Dia_buy window = new Dia_buy(information,place);
				window.open();
				}
				else{
					Dia_sell window = new Dia_sell(information,place);
					window.open();
				}
				
			}
		});
		btn_trade.setBounds(113, 5, 80, 27);
		btn_trade.setText("\u4E70\u5356");
		
		Label lbl_open = new Label(shell, SWT.NONE);
		lbl_open.setBounds(10, 56, 61, 17);
		lbl_open.setText("\u4ECA\u65E5\u5F00\u76D8\u4EF7");
		
		Label lbl_close = new Label(shell, SWT.NONE);
		lbl_close.setBounds(10, 79, 61, 17);
		lbl_close.setText("\u6628\u65E5\u6536\u76D8\u4EF7");
		
		Label lbl_openprice = new Label(shell, SWT.NONE);
		lbl_openprice.setBounds(77, 56, 61, 17);
		lbl_openprice.setText(information[1]);
		
		Label lbl_closeprice = new Label(shell, SWT.NONE);
		lbl_closeprice.setBounds(77, 79, 61, 17);
		lbl_closeprice.setText(information[2]);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(164, 56, 61, 17);
		lblNewLabel.setText("\u4ECA\u65E5\u6700\u9AD8\u4EF7");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(164, 79, 61, 17);
		lblNewLabel_1.setText("\u4ECA\u65E5\u6700\u4F4E\u4EF7");
		
		Label lbl_maxprice = new Label(shell, SWT.NONE);
		lbl_maxprice.setBounds(237, 56, 61, 17);
		lbl_maxprice.setText(information[4]);
		
		Label lbl_minprice = new Label(shell, SWT.NONE);
		lbl_minprice.setBounds(231, 79, 61, 17);
		lbl_minprice.setText(information[5]);
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 127, 549, 327);
		
		TabItem tab_daily = new TabItem(tabFolder, SWT.NONE);
		tab_daily.setText("\u65E5k\u7EBF\u56FE");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tab_daily.setControl(composite);
		
		ImageHyperlink lbl_daily = formToolkit.createImageHyperlink(composite, SWT.NONE);
		try {
			Internetpic.share.Internetpic.downLoad("daily", place, code, path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lbl_daily.setImage(SWTResourceManager.getImage(path+"\\daily"+spath));
		
		lbl_daily.setBounds(0, 0, 531, 286);
		formToolkit.paintBordersFor(lbl_daily);
		//lbl_daily.setText("New ImageHyperlink");
		
		
		//Image image = new Image()
		//lbl_daily.setBackgroundImage(image);
		
		TabItem tab_min = new TabItem(tabFolder, SWT.NONE);
		tab_min.setText("\u5206\u65F6\u56FE");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tab_min.setControl(composite_1);
		
		ImageHyperlink lbl_min = formToolkit.createImageHyperlink(composite_1, SWT.NONE);
		lbl_min.setBounds(0, 0, 531, 297);
		formToolkit.paintBordersFor(lbl_min);
		//lbl_min.setText("New ImageHyperlink");
		try {
			Internetpic.share.Internetpic.downLoad("min", place, code, path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lbl_min.setImage(SWTResourceManager.getImage(path+"\\min"+spath));
		
		TabItem tab_weekly = new TabItem(tabFolder, SWT.NONE);
		tab_weekly.setText("\u5468k\u7EBF\u56FE");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tab_weekly.setControl(composite_2);
		
		ImageHyperlink lbl_weekly = formToolkit.createImageHyperlink(composite_2, SWT.NONE);
		lbl_weekly.setBounds(0, 0, 531, 297);
		formToolkit.paintBordersFor(lbl_weekly);
		//lbl_weekly.setText("New ImageHyperlink");
		try {
			Internetpic.share.Internetpic.downLoad("weekly", place, code, path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lbl_weekly.setImage(SWTResourceManager.getImage(path+"\\weekly"+spath));
		
		TabItem tab_monthly = new TabItem(tabFolder, SWT.NONE);
		tab_monthly.setText("\u6708k\u7EBF\u56FE");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tab_monthly.setControl(composite_3);
		
		ImageHyperlink lbl_monthly = formToolkit.createImageHyperlink(composite_3, SWT.NONE);
		lbl_monthly.setBounds(0, 0, 531, 297);
		formToolkit.paintBordersFor(lbl_monthly);
		//lbl_monthly.setText("122");
		try {
			Internetpic.share.Internetpic.downLoad("monthly", place, code, path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lbl_monthly.setImage(SWTResourceManager.getImage(path+"\\monthly"+spath));
		
		Label lbl_time = new Label(shell, SWT.NONE);
		lbl_time.setBounds(383, 460, 156, 17);
		lbl_time.setText(time);
		
		

	}
}
