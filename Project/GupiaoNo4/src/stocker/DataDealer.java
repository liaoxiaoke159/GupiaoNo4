package stocker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import stock.homepage;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class DataDealer {

	public String path_file;
	public double limitfund;
	public double fund;
	public int tabitemindex;

	public DataDealer(String path_file, int index){
		this.path_file=path_file;
		this.tabitemindex = index;
	}
	//public DataDealer(){}
	
	public boolean creat() {
		// TODO Auto-generated method stub

		// ����ļ��Ƿ����Ҫ��
		if (!path_fileCheck(path_file)) {
			
			JOptionPane.showMessageDialog(null, "�ļ�����ȷ");
			return false;
		}

		
		
        //�ѽ��׼�¼excel��ת����Stocks��
		List<Stocks> stockslist = new ArrayList<Stocks>();
		   stockslist = stockslist_initial(path_file);
		 if(stockslist == null){
			   return false;//���׼�¼�Ƿ�
		   }
		 
		 if(true){
	
		// ��ʼ���û���Ϣtable��
		homepage.table_userinfos.get(tabitemindex).removeAll();
		
		String[] tablestr = new String[]{"��ʼ�ʽ�","�����ʽ�","�ʲ���ֵ","���й�Ʊ��","ӯ����"};
		TableItem tableitem1 = new TableItem(homepage.table_userinfos.get(tabitemindex),SWT.NONE);
		tableitem1.setText(tablestr);
		new TableItem(homepage.table_userinfos.get(tabitemindex),SWT.NONE);
		userinfo_table_change( homepage.table_userinfos.get(tabitemindex), stockslist);

		// ��ӡ�ֲֹ�Ʊ��Ϣ��table��
		table_chiang_initial(stockslist, homepage.table_chicangs.get(tabitemindex));
//		 for(int i=0;i<homepage.stkl.stockslist.size();i++){
//			 System.out.println(homepage.stkl.stockslist.get(i).getSocketname()+"(Importer 62)");
//			 }
		 
		 
		chiguANDshouyilv();
		}
		   return true;
	}
	


	public boolean path_fileCheck(String path_file) {
		Workbook wb = null;
		try {
			 wb = Workbook.getWorkbook(new FileInputStream(path_file));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet(0);
		return sheet.getColumns()==8&&sheet.getRows()>=2&&sheet.getCell(0, 1).getContents().equals("��Ʊ����")&&sheet.getCell(1, 1).getContents().equals("��Ʊ����")
				&&sheet.getCell(2, 1).getContents().equals("��������")&&sheet.getCell(3, 1).getContents().equals("��������")
				&&sheet.getCell(4, 1).getContents().equals("�ɽ���")&&sheet.getCell(5, 1).getContents().equals("�ɽ�����")
				&&sheet.getCell(6, 1).getContents().equals("������");
		
	}
	
	private List<Stocks> stockslist_initial(String path_file) {
		// TODO Auto-generated method stub
		List<Stocks>  stockslist = new ArrayList<Stocks>();
		
		Workbook wb = null;
		try {
			 wb = Workbook.getWorkbook(new FileInputStream(path_file));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Sheet sheet = wb.getSheet(0);
		int rows = sheet.getRows();
		fund = limitfund = Double.parseDouble(sheet.getCell(7, 0).getContents());//��ó�ʼ�ʽ�
		for(int rowsloop = 2; rowsloop <rows;++rowsloop){
			String name = sheet.getCell(0, rowsloop).getContents();
			String code = sheet.getCell(1,rowsloop).getContents();
			String place = sheet.getCell(6,rowsloop).getContents();
			String num = sheet.getCell(5,rowsloop).getContents();
			String price = sheet.getCell(4,rowsloop).getContents();
			String style = sheet.getCell(3,rowsloop).getContents();
			
			
			int TAG = -1;//-1��ʾû���ҵ���ͬ��Ʊ 1��ʾ�ҵ���ͬ
			for(int listloop =0; listloop < stockslist.size();listloop++){
				
				Stocks  stocks = stockslist.get(listloop);
				if(name.equals(stocks.getSocketname())
						&&code.equals(stocks.getSocketcode())
						&&place.equals(stocks.getplace())){
						
					if((style.equals("����")&&stocks.getNum()>0)||(style.equals("����")&&stocks.getNum()<0)){
						
						
						
						if(style.equals("����")&&(stocks.getNum()+Integer.parseInt(num))<0)
						{
							homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
							homepage.lbl_notice.setText("*����ʧ��  ԭ��:���׼�¼�Ƿ�");
							System.out.println("��ǰ���׷Ƿ�"+" ԭ�򣺹�Ʊ���벻��"+ " ��Ʊ����"+name + " excel������"+(rowsloop+1));
							return null;
						}//�쳣
						
						else if(style.equals("����")&&(stocks.getNum()+Integer.parseInt(num))>0){
							homepage.lbl_notice.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
							homepage.lbl_notice.setText("*����ʧ��  ԭ��:���׼�¼�Ƿ�");
							System.out.println("��ǰ���׷Ƿ�"+" ԭ�򣺹�Ʊ���ղ���"+ " ��Ʊ����"+name + " excel������"+(rowsloop+1));
							return null;
						}
						
						stockslist.get(listloop).setNum(Integer.parseInt(num));
						 if(stocks.getNum()==0)
						{stockslist.remove(listloop);}
							
						   TAG = 1;
							break;
					 }//�ж��������򲹲ּ�¼
				
					if((style.equals("����")&&stocks.getNum()>0)||(style.equals("����")&&stocks.getNum()<0)){
						
							//�ҵ���ͬ��Ʊ �������ù�Ʊ�ĳɱ���
						   DecimalFormat df = new DecimalFormat(".00");
						   double newcostprice = Double.parseDouble(df.format((stocks.getNum()* stocks.getcostprice()
							+Integer.parseInt(num)*Double.parseDouble((price)))/(stocks.getNum()+Integer.parseInt(num))));
							
						    stockslist.get(listloop).setcostprice(newcostprice);
							
							stockslist.get(listloop).setNum(Integer.parseInt(num));
							
							TAG = 1;
							break;
						
					}//�ж�����������ռ�¼
						
				}
				
			}
			
			//û���ҵ���ͬ��Ʊ ���ҽ��׼�¼�������򲹲� ���׼�¼�Ƿ�
			if(TAG==-1&&(style.equals("����")||style.equals("����"))){
				System.out.println("��ǰ���׷Ƿ�"+" ԭ�򣺹�Ʊ����򲹲ֲ���"+ " ��Ʊ����"+name + " excel������"+(rowsloop+1));
				return null;
			}
			//û���ҵ���ͬ��Ʊ ��������ղ��� ����һֻ��Ʊ
			else if(TAG == -1){
				Stocks stocktemp = new Stocks(name,code,num);
				stocktemp.setplace(place);
				stocktemp.setcostprice(Double.parseDouble(price));
				stockslist.add(stocktemp);
			}
			
			limitfund+=Double.parseDouble(price)*Integer.parseInt(num);//��������ʽ�
		}
		
//		for(int listloop =0; listloop < stockslist.size();++listloop){
//			System.out.println(stockslist.get(listloop).getSocketname()+" "+stockslist.get(listloop).getSocketcode()+
//					" "+stockslist.get(listloop).getcostprice()+" "+stockslist.get(listloop).getNum()+" "+stockslist.get(listloop).getplace());
//		}
		return stockslist;
	}

	private  void chiguANDshouyilv() {
		// TODO Auto-generated method stub
		List<Trade> tradelist = new ArrayList<Trade>();
		DataBuilder dd =new DataBuilder();
		try {
			tradelist =dd.tradelistmaker(path_file);
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

/*
		for(int i=0; i<tradelist.size();i++){
			System.out.println(tradelist.get(i).get_name()+" "+tradelist.get(i).get_code()
					+" "+tradelist.get(i).get_num()+" "+tradelist.get(i).get_trade_stytle()
					+" "+tradelist.get(i).get_price()+" "+tradelist.get(i).get_date());
		}
	*/
		long startMili=System.currentTimeMillis();// ��ǰʱ���Ӧ�ĺ�����
		System.out.println("��ʼ���������� ");
		
		Sumreturnrate sumreturnrate = new Sumreturnrate();
		sumreturnrate = dd.Sumreturnratemaker(tradelist);
		
		long endMili=System.currentTimeMillis();
		System.out.println("�ܺ�ʱΪ��"+(endMili-startMili)+"����"+"\n");
		
		Linechart linechart = new Linechart(sumreturnrate);
		linechart.getChartPanel("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data");
		
		homepage.lbl_shouyilvs.get(tabitemindex).setImage(new Image(Display.getDefault(), "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\shouyilv.png"));

//		for(int loop =0; loop <sumreturnrate.date.size();loop++){
//			System.out.println(sumreturnrate.date.get(loop)+" "+sumreturnrate.rate.get(loop) );
//		}
		long startMili2=System.currentTimeMillis();// ��ǰʱ���Ӧ�ĺ�����
		System.out.println("��ʼ����ֹ� ");
		
		List<HistoryStockown> HSOL = new ArrayList<HistoryStockown>();
		HSOL = dd.HistoryStockownmaker(tradelist);
		
		long endMili2=System.currentTimeMillis();
		System.out.println("�ܺ�ʱΪ��"+(endMili2-startMili2)+"����"+"\n");
		
		AreaJFreeChart AJC = new AreaJFreeChart(HSOL);
		AJC.getChartPanel("C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data");
		homepage.lbl_chigus.get(tabitemindex).setImage(new Image(Display.getDefault(), "C:\\Users\\Administrator\\Desktop\\GupiaoNo4\\Project\\GupiaoNo4\\data\\chigu.png"));
//		  for(int stockloop = 0 ;stockloop < HSOL.size(); stockloop++){
//			  System.out.println(HSOL.get(stockloop).name + " " + HSOL.get(stockloop).code+" :");
//			  for(int dateloop = 0;dateloop < HSOL.get(stockloop).date.size(); dateloop++){
//				  System.out.println(HSOL.get(stockloop).date.get(dateloop)
//						  +" "+HSOL.get(stockloop).number.get(dateloop));
//			  }
//		  }
		
	}

	private  void userinfo_table_change(Table table_userinfo, List<Stocks> stockslist) {
		// TODO Auto-generated method stub

		
		double sumprice=0;//������ֵ
		
		String[] information =new String[31];
		for(int i =0; i <stockslist.size();i++)
		{
			try {
				 
				information =Internet.share.Internet.getSharedata(stockslist.get(i).getplace(),
						stockslist.get(i).getSocketcode()); 
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "��������", "�쳣", JOptionPane.ERROR_MESSAGE);
				return;
				
			}
			  //ץȡ�ù�Ʊ��Ϣ
			
			double perprice = Double.parseDouble(information[3]);
			double num = (double)stockslist.get(i).getNum();
			
			sumprice = sumprice + perprice*num;
			
		}
		
		//�޸Ĺ�Ʊ��
		table_userinfo.getItem(1).setText(3, Integer.toString(stockslist.size()));
		
		//�޸ĳ�ʼ�ʽ�
		table_userinfo.getItem(1).setText(0,Double.toString(fund));
		
		//�޸Ŀ����ʽ�
		table_userinfo.getItem(1).setText(1,Double.toString(limitfund));
		
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);//���ñ���С��λ
		DecimalFormat df = new DecimalFormat(".00");
		
		//�޸��ʲ���ֵ
		table_userinfo.getItem(1).setText(2, df.format(limitfund+sumprice));
		
		double returnrate=(limitfund+sumprice-fund)/fund;//�����ӯ����
		
		//�޸�ӯ����
		table_userinfo.getItem(1).setText(4, nf.format(returnrate));
		
	}
	
	public  void table_chiang_initial(List<Stocks> stockslist, Table table_chicang) {
		// TODO Auto-generated method stub
		
		String[] information = new String[31];
		String[] strings = new String[10];
		
	
		int num = stockslist.size();
		
		//�Ȳ���ԭ�ȱ��
		table_chicang.removeAll();
		for(int i =0;i<num;i++){
		 //ץȡ�ù�Ʊ��Ϣ
		try {
			 
			information =Internet.share.Internet.getSharedata(stockslist.get(i).getplace(),
				stockslist.get(i).getSocketcode()); 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "��������", "�쳣", JOptionPane.ERROR_MESSAGE);
		}
		 

		
		strings[0]= stockslist.get(i).getSocketname();//��Ʊ����
		strings[1] = stockslist.get(i).getSocketcode();//��Ʊ����
		strings[2] = stockslist.get(i).getplace();//������
		strings[3] = information[3];//��ǰ��
		
		double nowprice = Double.parseDouble(information[3]);
		double price = stockslist.get(i).getcostprice();
		double rate = (nowprice - price)/price;
		
		//�ٷ�����ʽ������λС��
		NumberFormat nf_precent = NumberFormat.getPercentInstance();
		nf_precent.setMinimumFractionDigits(2);
		//double��ʽ������λС��
		NumberFormat nf_price = NumberFormat.getNumberInstance();
		nf_price.setMinimumFractionDigits(2);
		
		strings[4] = nf_price.format(nowprice-price);//�ǵ�
		strings[5] = Integer.toString(stockslist.get(i).getNum());//������
		strings[6] = nf_price.format(nowprice*(double)stockslist.get(i).getNum());//������ֵ
		strings[7] = Double.toString(stockslist.get(i).getcostprice());//�ɱ���
		strings[8] = nf_precent.format(rate);//����ӯ������
		strings[9] = nf_price.format((nowprice-price)*(double)stockslist.get(i).getNum());//����ӯ��
		
		
		TableItem tableItem = new TableItem(table_chicang,SWT.NONE);
		tableItem.setText(strings);
		
		}
	}
	
	public void changefund(String newfund){
		
		Workbook workBook = null;
		try {
			workBook = Workbook.getWorkbook(new FileInputStream(path_file));
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableWorkbook writeBook = null;
		try {
			writeBook = Workbook.createWorkbook(new File(path_file),workBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableSheet sheet = writeBook.getSheet(0);

		Label label = new Label(7,0,newfund);
		try {
			sheet.addCell(label);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 4����������ʼд�ļ�
		try {
			writeBook.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5���ر���
		try {
			writeBook.close();
		} catch (WriteException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tablemaker(Table table)  throws BiffException, IOException{
	// TODO Auto-generated method stub
	// 1������excel�ļ�����������

	InputStream is = new FileInputStream(path_file);

	// 2����������������
	Workbook rwb = Workbook.getWorkbook(is);
	// 3����ù������ĸ���,��Ӧ��һ��excel�еĹ��������
	rwb.getNumberOfSheets();

	Sheet oFirstSheet = rwb.getSheet(0);// ʹ��������ʽ��ȡ��һ��������Ҳ����ʹ��rwb.getSheet(sheetName);����sheetName��ʾ���ǹ����������
	// System.out.println("���������ƣ�" + oFirstSheet.getName());
	int rows = oFirstSheet.getRows();// ��ȡ�������е�������
	int columns = oFirstSheet.getColumns();// ��ȡ�������е�������
	String[] itemdata = new String[columns];

	for (int i = 1; i < rows; i++) {
		for (int j = 0; j < columns; j++) {
			Cell oCell = oFirstSheet.getCell(j, i);// ��Ҫע����������getCell�����Ĳ�������һ����ָ���ڼ��У��ڶ�����������ָ���ڼ���
			// System.out.println(oCell.getContents()+"\r\n");
			itemdata[j] = oCell.getContents();
		}
		TableItem tableItem = new TableItem(table,SWT.NONE);
		tableItem.setText(itemdata);
	}

}

}
