package stocker;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


public class Pie {

	JFreeChart chart;
	 ChartPanel frame1;
	
	    public Pie(List<Stocks> s ){
	          DefaultPieDataset data = getDataSet(s);
	          chart = ChartFactory.createPieChart3D("�ֹɹ���",data,true,false,false);
	        //���ðٷֱ�
	          PiePlot pieplot = (PiePlot) chart.getPlot();
	          DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������
	          NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����
	          StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//���StandardPieSectionLabelGenerator����
	          pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�
	       
	      //û�����ݵ�ʱ����ʾ������
	          pieplot.setNoDataMessage("��������ʾ");
	          pieplot.setCircular(false);
	          pieplot.setLabelGap(0.02D);
	       
	          pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ
	          pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ
	         frame1=new ChartPanel (chart,true);
	          chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
	          PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������
	          piePlot.setLabelFont(new Font("����",Font.BOLD,10));//�������
	          chart.getLegend().setItemFont(new Font("����",Font.BOLD,10));
	          
	    }
	    private static DefaultPieDataset getDataSet(List<Stocks> s) {
	        DefaultPieDataset dataset = new DefaultPieDataset();
	        for(Stocks a:s){
	        	dataset.setValue(a.getSocketname(), a.getNum());
	        }
//	        dataset.setValue("ƻ��",100);
//	        dataset.setValue("����",200);
//	        dataset.setValue("����",300);
//	        dataset.setValue("�㽶",400);
//	        dataset.setValue("��֦",500);
	        return dataset;
	}
	    public void getChartPanel(String path){
	    	try {
				ChartUtilities.saveChartAsPNG(new File(path+"\\chigu.png"), chart, 550, 300);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    		         
	    }
}
