package stock;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;

public class Messagedialofail {

	protected Shell shell;
	protected Shell shell1;
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			Messagedialofail window = new Messagedialofail();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Open the window.
	 * @param shell2 
	 */
	public void open(Shell shell2) {
		this.shell1 = shell2;
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
		shell.setSize(388, 277);
		shell.setText("\u4E0B\u5355\u5931\u8D25");
		
		//·µ»Ø°´Å¥
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
				shell1.dispose();
			}
		});
		button.setBounds(138, 126, 55, 27);
		button.setText("\u8FD4\u56DE");
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel.setBounds(127, 37, 86, 32);
		lblNewLabel.setText("\u4E0B\u5355\u5931\u8D25\uFF01");
		
		lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_1.setBounds(83, 82, 189, 27);
		lblNewLabel_1.setText("\u8BF7\u786E\u8BA4\u662F\u5426\u6B63\u786E\u586B\u5199\u4FE1\u606F");
	}

}
