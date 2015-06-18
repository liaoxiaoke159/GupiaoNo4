package stock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
public class NewAccount {

	protected Shell shell;
	private Text text_directory;
	private Text text_name;
	private Text text_fund;
	private Label lbl_fund;
	private Label lbl_namenotice;
	private Label lbl_fundnotice;
	protected String path;
	private Button btn_new;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NewAccount window = new NewAccount();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @return 
	 */
	public String open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				
				display.sleep();
				
			}
		}
		
		return path;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell=new Shell(SWT.NONE|SWT.APPLICATION_MODAL);
		shell.setSize(432, 300);
		shell.setText("�½��˻�");
		
		Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
	    text_directory = new Text(shell, SWT.BORDER);
		text_directory.setBounds(88, 77, 236, 23);
		text_directory.setText("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data");
		
		Label lbl_directory = new Label(shell, SWT.NONE);
		lbl_directory.setBounds(21, 80, 61, 17);
		lbl_directory.setText("����Ŀ¼��");
		
		text_name = new Text(shell, SWT.BORDER);
		text_name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(text_name.getText().isEmpty()){
					
					lbl_namenotice.setText("*�ʻ�������Ϊ��");
					lbl_namenotice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					btn_new.setEnabled(false);
					
				}else if(new File(text_directory.getText()+"\\"+text_name.getText()+".xls").exists()){
					lbl_namenotice.setText("*���˻����Ѵ���");
					lbl_namenotice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					btn_new.setEnabled(false);
					
				}
				else{
					lbl_namenotice.setText("ok");
					lbl_namenotice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
					btn_new.setEnabled(true);
				}
			}
		});
		text_name.setBounds(88, 106, 122, 23);
		
		text_fund = new Text(shell, SWT.BORDER);
		text_fund.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
		
				 if(text_fund.getText().isEmpty()){
					lbl_fundnotice.setText("*��ʼ�ʽ���Ϊ��");
					lbl_fundnotice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					btn_new.setEnabled(false);
					
				}	else if(!Userinfochange.isNumeric(text_fund.getText()) || Double.parseDouble(text_fund.getText())<=0 ){
					lbl_fundnotice.setText("*��ʼ�ʽ���ȷ");
					lbl_fundnotice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					btn_new.setEnabled(false);
				}
				else{
					lbl_fundnotice.setText("ok");
					lbl_fundnotice.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
					btn_new.setEnabled(true);
				}
			
			}
		});
		
		text_fund.setBounds(88, 135, 73, 23);
		
		Label lbl_name = new Label(shell, SWT.NONE);
		lbl_name.setBounds(21, 112, 61, 17);
		lbl_name.setText("�˻�����");
		
		lbl_fund = new Label(shell, SWT.NONE);
		lbl_fund.setBounds(21, 141, 61, 17);
		lbl_fund.setText("��ʼ�ʽ�");
		
		 btn_new = new Button(shell, SWT.NONE);
		btn_new.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(lbl_namenotice.getText().equals("ok")&&lbl_fundnotice.getText().equals("ok"))
				   if(newexcel(text_directory.getText()+"\\"+text_name.getText()+".xls",text_fund.getText())){
					   path = text_directory.getText()+"\\"+text_name.getText()+".xls";
				       shell.dispose();
				   }
				   else{
					   path = null;
				       shell.dispose();
				   }
			
				
			}
		});
		btn_new.setBounds(254, 250, 56, 27);
		btn_new.setText("�½�");
		
		Button btn_cancel = new Button(shell, SWT.NONE);
		btn_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btn_cancel.setBounds(323, 250, 50, 27);
		btn_cancel.setText("ȡ��");
		
		Button btn_changge = new Button(shell, SWT.NONE);
		btn_changge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DirectoryDialog folderdlg=new DirectoryDialog(shell);
		        folderdlg.setText("Ŀ¼ѡ��");
		        folderdlg.setFilterPath("SystemDrive");
		        folderdlg.setMessage("��ѡ����Ӧ���ļ���");
		        String selecteddir=folderdlg.open();
		        if(selecteddir==null){   
		            return ;
		        }
		        else{
		        	text_directory.setText(selecteddir);
		        }       
			}
		});
		btn_changge.setBounds(330, 73, 43, 27);
		btn_changge.setText("����");
		
		lbl_namenotice = new Label(shell, SWT.NONE);
		lbl_namenotice.setBounds(229, 112, 163, 17);
		
		lbl_fundnotice = new Label(shell, SWT.NONE);
		lbl_fundnotice.setBounds(178, 141, 195, 17);

	}

	public boolean newexcel(String path,String fund) {
		//���ļ�  
        WritableWorkbook book = null;
		try {
			 book = Workbook.createWorkbook(new File(path));
		 
        //����һ����Ϊ����һҳ���Ĺ�������0����ʾ��һҳ  
        WritableSheet sheet = book.createSheet("��һҳ",0); 
        
           jxl.write.Label label = new jxl.write.Label(7,0,fund);  
			sheet.addCell(label);
			jxl.write.Label label0 = new jxl.write.Label(0,1,"��Ʊ����");  
			sheet.addCell(label0);
			jxl.write.Label label1 = new jxl.write.Label(1,1,"��Ʊ����");  
			sheet.addCell(label1);
			jxl.write.Label label2 = new jxl.write.Label(2,1,"��������");  
			sheet.addCell(label2);
			jxl.write.Label label3 = new jxl.write.Label(3,1,"��������");  
			sheet.addCell(label3);
			jxl.write.Label label4 = new jxl.write.Label(4,1,"�ɽ���");  
			sheet.addCell(label4);
			jxl.write.Label label5 = new jxl.write.Label(5,1,"�ɽ�����");  
			sheet.addCell(label5);
			jxl.write.Label label6 = new jxl.write.Label(6,1,"������");  
			sheet.addCell(label6);
			
			book.write();
			book.close();
			
		} catch (IOException  | WriteException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
