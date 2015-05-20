package stocker;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class Linechart {
	ChartPanel frame1;
	JFreeChart jfreechart;
    public Linechart(List<StockTrade> l){
        XYDataset xydataset = createDataset(l);
        jfreechart = ChartFactory.createTimeSeriesChart("股票收益率", "日期", "价格",xydataset, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("ddd-MMM-yyyy"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
 
    }
     private static XYDataset createDataset(List<StockTrade> l) {  //这个数据集有点多，但都不难理解
            TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
            for(StockTrade s:l){

            	@SuppressWarnings("deprecation")
				TimeSeries time=new TimeSeries(s.name,org.jfree.data.time.Day.class);

            	for(int i=0;i<s.date.size();i++){
            		String day=s.date.get(i);
            		String[] days=new String[3];
            		days=day.split("/");
            		int d=0,m=0,y=0;
            		d=Integer.parseInt(days[2]);
            		m=Integer.parseInt(days[1]);
            		y=Integer.parseInt(days[0]);
            		time.add(new Day(d,m,y),s.rate.get(i));
            	}
            	timeseriescollection.addSeries(time);
            	}
            return timeseriescollection;
            }
     public void getChartPanel(String path){
	    	try {
				ChartUtilities.saveChartAsPNG(new File(path+"\\shouyilv.png"), jfreechart, 550, 250);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    		         
	    }
}
            

