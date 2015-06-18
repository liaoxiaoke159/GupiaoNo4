package stock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

import stocker.DataDealer;

public class Accountmanage {

	protected Shell shell;
	private List<String> path_trade = new ArrayList<String>();
    private List<Composite> composites = new ArrayList<Composite>();
    private Label lbl_cancel;
    private boolean booladd;
    private Composite composite_adder;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> path= new ArrayList<String>();
		try {
			Accountmanage window = new Accountmanage(path);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Accountmanage(List<String> path_trade){
		this.path_trade = path_trade;
	}
	/**
	 * Open the window.
	 */
	public boolean open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return booladd;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(shell,SWT.BORDER);
		shell.setSize(388,47*path_trade.size()+136 );
		shell.setText("SWT Application");
		
		Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		Label lbl_accounter = new Label(shell, SWT.NONE);
		lbl_accounter.setBounds(56, 10, 24, 17);
		lbl_accounter.setText("账户");
		
		Label lbl_manage = new Label(shell, SWT.NONE);
		lbl_manage.setBounds(199, 10, 24, 17);
		lbl_manage.setText("操作");
		
		accountCompositeMaker();
		
		
		
		 composite_adder = new Composite(shell, SWT.BORDER);
		
		composite_adder.setBounds(26, 43+47*path_trade.size(), 279, 41);
		
		final Label lbl_add = new Label(composite_adder, SWT.SHADOW_NONE | SWT.CENTER);
		lbl_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				booladd =true;
				shell.dispose();
			}
		});
		lbl_add.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				lbl_add.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				 lbl_add.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			}
			@Override
			public void mouseExit(MouseEvent e) {
				lbl_add.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				lbl_add.setBackground(SWTResourceManager.getColor(240,240,240));
			}
		});
		lbl_add.setBounds(115, 10, 59, 17);
		lbl_add.setText("新增");
		
		
		
		
		
		
		 lbl_cancel = new Label(shell, SWT.CENTER);
		lbl_cancel.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				lbl_cancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				 lbl_cancel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			}
			@Override
			public void mouseExit(MouseEvent e) {

				lbl_cancel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				lbl_cancel.setBackground(SWTResourceManager.getColor(240,240,240));
			}
		});
		lbl_cancel.setBounds(318, 94+47*path_trade.size(), 40, 17);
		lbl_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				booladd = false;
				shell.dispose();
			}
		});
		lbl_cancel.setText("返回");

	}

	

	private void accountCompositeMaker() {
		// TODO Auto-generated method stub
		for(int loop=0;loop<path_trade.size();loop++){
			
			String str = path_trade.get(loop);
			Composite composite_account = new Composite(shell, SWT.BORDER);
			composite_account.setBounds(26, 43+47*loop, 279, 41);
			
			Label lbl_account = new Label(composite_account, SWT.NONE);
			lbl_account.setBounds(10, 10, 116, 17);
			lbl_account.setText(str.substring(str.lastIndexOf("\\")+1,str.indexOf(".xls")));
			
			final Label lbl_delete = new Label(composite_account, SWT.CENTER);
			
			lbl_delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					
					try {
						deleteEvent(composites.indexOf(lbl_delete.getParent()));
					} catch (BiffException | WriteException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			lbl_delete.addMouseTrackListener(new MouseTrackAdapter() {
				@Override
				public void mouseEnter(MouseEvent e) {
					 lbl_delete.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
					 lbl_delete.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				@Override
				public void mouseExit(MouseEvent e) {
					
					lbl_delete.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
					lbl_delete.setBackground(SWTResourceManager.getColor(240,240,240));
				}
			});
			lbl_delete.setBounds(169, 10, 39, 17);
			lbl_delete.setText("删除");
			
			composites.add(composite_account);
			
		}
		
	}

	protected void deleteEvent(int index) throws BiffException, IOException, WriteException {
		// TODO Auto-generated method stub
		
		composites.get(index).dispose();
		composites.remove(index);
		
		Workbook wb = Workbook.getWorkbook(new File(homepage.path_account));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(homepage.path_account),wb);
		WritableSheet sheet = wwb.getSheet(0);
		sheet.removeRow(index);
		wwb.write();
		wwb.close();
		
		homepage.ctabitems.get(index).dispose();
		homepage.ctabitems.remove(index);
		
		homepage.table_chicangs.remove(index);
		homepage.table_userinfos.remove(index);
		path_trade.remove(index);
		homepage.lbl_shouyilvs.remove(index);
		homepage.lbl_chigus.remove(index);
		homepage.importnum--;
		if(homepage.table_chicangs.size()==0){
			homepage.isimport = false;
		}
		
		for(int i = 0;i<path_trade.size();i++){
			composites.get(i).setBounds(26, 43+47*i, 279, 41);
		}
		composite_adder.setBounds(26, 43+47*path_trade.size(), 279, 41);
		shell.setSize(388,47*path_trade.size()+136 );
		lbl_cancel.setBounds(318, 94+47*path_trade.size(), 40, 17);
	}
}
