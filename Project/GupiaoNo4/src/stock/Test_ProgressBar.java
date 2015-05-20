package stock;
import org.eclipse.swt.SWT;  
import org.eclipse.swt.layout.FillLayout;  
import org.eclipse.swt.widgets.Composite;  
import org.eclipse.swt.widgets.Display;  
import org.eclipse.swt.widgets.ProgressBar;  
import org.eclipse.swt.widgets.Shell;  
/** 
 * @author XiangJie 
 *  
 */  
public class Test_ProgressBar {  
    public void show_ProgressBar(Composite parent) {  
        final ProgressBar hBar = new ProgressBar(parent, SWT.HORIZONTAL  
                | SWT.SMOOTH);  
        hBar.setMinimum(0);  
        hBar.setMaximum(30);  
        ProgressBar hBar1 = new ProgressBar(parent, SWT.HORIZONTAL  
                | SWT.INDETERMINATE);  
        hBar1.setMinimum(0);  
        hBar1.setMaximum(30);  
        (new IncresingOperator(hBar)).start();  
    }  
    class IncresingOperator extends Thread {  
        private ProgressBar bar;  
        IncresingOperator(ProgressBar bar) {  
            this.bar = bar;  
        }  
        public void run() {  
            /** 
             * Maybe you don't understand why we should use this argument. I 
             * just say that we just can access UI by the UI thread. The reason 
             * I will explain in the blog later. 
             *  
             * @author jie.xiang 
             */  
            Display.getDefault().asyncExec(new Runnable() {  
                public void run() {  
                    // TODO Auto-generated method stub  
                    /** 
                     * Maybe it has been disposed. 
                     *  
                     * @author jie.xiang 
                     */  
                    if (bar.isDisposed())  
                        return;  
                    for (int i = 0; i < bar.getMaximum(); i++) {  
                        try {  
                            /** 
                             * In order to display the progress bar step by 
                             * step, and we let the thread sleep 100ms 
                             *  
                             * @author jie.xiang 
                             */  
                            Thread.sleep(10);  
                        } catch (InterruptedException e) {  
                            e.printStackTrace();  
                        }  
                        /** 
                         * Add the selection value. 
                         *  
                         * @author jie.xiang 
                         */  
                        bar.setSelection(bar.getSelection() + 1);  
                    }  
                }  
            });  
        }  
    }  
    /** 
     * create data: 2010-1-12</br> 
     *  
     * @author XiangJie 
     * @param args 
     *  
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        Display display = new Display();  
        Shell shell = new Shell(display);  
        shell.setText("ProgressBar Test:");  
        shell.setLayout(new FillLayout(SWT.VERTICAL));  
        (new Test_ProgressBar()).show_ProgressBar(shell);  
        shell.pack();  
        shell.open();  
        while (!shell.isDisposed()) {  
            if (!display.readAndDispatch())  
                display.sleep();  
        }  
        display.dispose();  
    }  
}  