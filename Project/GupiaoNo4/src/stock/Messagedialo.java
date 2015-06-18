package stock;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;


public class Messagedialo {

	protected Shell shell;
	private Label label;
	private Shell shell1;

	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			Messagedialo window = new Messagedialo();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Open the window.
	 * @param shell2 
	 */
	public void open(Shell shell1) {
		this.shell1 = shell1;
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
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chaogushenqi.png"));
		shell.setSize(388, 277);
		shell.setText("下单成功");
		
		//返回按钮
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose(); 
				shell1.dispose();
			}
		});
		button.setBounds(135, 126, 58, 33);
		button.setText("\u8FD4\u56DE");
		
		label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(125, 70, 80, 27);
		label.setText("\u4E0B\u5355\u6210\u529F\uFF01");

	}

}
