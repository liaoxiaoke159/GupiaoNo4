package stock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;


public class Dia_sell {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_6;
	private Text text_sharecode;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_price;
	private Text text_number;
	private Button btnNewButton;
	private Button btnNewButton_1;
	/**
	 * Launch the application.
	 * @param args
	 */
	
//	public static void main(String[] args) {
//		try {
//			Dia_sell window = new Dia_sell();
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
		shell.setSize(488, 398);
		shell.setText("����");//���ô���
		
		//�����ı���
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setText("\u6DA8\u505C\u4EF7\u683C");
		text.setBounds(129, 87, 57, 23);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setText("\u8DCC\u505C\u4EF7\u683C");
		text_1.setBounds(129, 116, 57, 23);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setText("\u53EF\u5356\u6570\u91CF");
		text_2.setBounds(129, 145, 57, 23);
		
		text_3 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_3.setText("\u59D4\u6258\u4EF7\u683C");
		text_3.setBounds(129, 174, 57, 23);
		
		text_4 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_4.setText("\u59D4\u6258\u6570\u91CF");
		text_4.setBounds(129, 203, 57, 23);
		
		text_6 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_6.setText("\u80A1\u7968\u4EE3\u7801");
		text_6.setBounds(129, 58, 57, 23);
		
		text_sharecode = new Text(shell, SWT.BORDER);
		text_sharecode.setText("101324587");
		text_sharecode.setBounds(241, 58, 73, 23);
		
		text_8 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_8.setText("100");
		text_8.setBounds(241, 87, 73, 23);
		
		text_9 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_9.setText("1");
		text_9.setBounds(241, 116, 73, 23);
		
		text_10 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_10.setText("100");
		text_10.setBounds(241, 145, 73, 23);
		
		text_price = new Text(shell, SWT.BORDER);
		text_price.setBounds(241, 174, 73, 23);
		
		text_number = new Text(shell, SWT.BORDER);
		text_number.setBounds(241, 203, 73, 23);
		
		
		//�µ���ȡ����ť
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				MessageBox messagebox = new MessageBox(shell, SWT.YES | SWT.NO);
                messagebox.setText("�µ�");
                messagebox.setMessage("          ȷ���Ƿ��µ�");
              
               int val=messagebox.open();
                if(val == SWT.YES)
                {
                	if((text_number.getText().isEmpty())|(text_price.getText().isEmpty())
                			|(text_sharecode.getText().isEmpty())){
        			Messagedialofail window2 = new Messagedialofail();
            			window2.open();
                	}
                	else{
                		Messagedialo window = new Messagedialo();
                		window.open();
                	}
                }
			}
		});
		btnNewButton.setBounds(99, 267, 80, 27);
		btnNewButton.setText("\u4E0B\u5355");
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(267, 267, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");

	}
}
