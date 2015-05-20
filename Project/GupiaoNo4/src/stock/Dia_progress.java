package stock;

import org.eclipse.swt.SWT;
  import org.eclipse.swt.layout.GridData;
  import org.eclipse.swt.layout.GridLayout;
 import org.eclipse.swt.widgets.Display;
  import org.eclipse.swt.widgets.ProgressBar;
  import org.eclipse.swt.widgets.Shell;
  
  
 public class Dia_progress {
     public static void main(String[] args){
         Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout());
        //���ƽ���Ľ�����
       ProgressBar pb1 = new ProgressBar(shell,SWT.HORIZONTAL|SWT.SMOOTH);
         pb1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        //��ʾ����������Сֵ
        pb1.setMinimum(0);
        //���ý����������ֵ
         pb1.setMaximum(30);
         //����Զ������Ľ�����
        ProgressBar pb2 = new ProgressBar(shell,SWT.HORIZONTAL|SWT.INDETERMINATE);
         //����̣߳����߳��д���ʱ������񣬲����շ�ӳ��ƽ����������
        new LongRunningOperation(display,pb1).start();
         shell.open();
         while(!shell.isDisposed()){
             if(!display.readAndDispatch()){
                 display.sleep();
            }
        }
     }
 } 
 class LongRunningOperation extends Thread{
     private Display display;
     private ProgressBar progressBar;     
    public LongRunningOperation(Display display,ProgressBar progressBar){
         this.display = display;
         this.progressBar = progressBar;
    }
     
     public void run(){
        //ģ�³�ʱ�������
         for(int i = 0;i<30;i++){
             try{
                 Thread.sleep(100);
             }catch(InterruptedException e){
                
           }
             display.asyncExec(new Runnable(){
                public void run(){
                    if(progressBar.isDisposed()) return;
                    //����������
            }
            });
         }
  }
    
 }