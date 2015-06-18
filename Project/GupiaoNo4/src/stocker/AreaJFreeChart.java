package stocker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
 


import java.io.File;
import java.io.IOException;
import java.util.List;

 




import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.VerticalAlignment;
import org.jfree.util.UnitType;

public class AreaJFreeChart {
	public  JFreeChart chart;
	public AreaJFreeChart(List<HistoryStockown> l){
		CategoryDataset dataset = createDataset(l);
        chart = createChart(dataset);
	}
	public  void getChartPanel(String path){//save to png
    	try {
			ChartUtilities.saveChartAsPNG(new File(path+"\\chigu.png"), chart, 700, 350);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    		         
    }
	private JFreeChart createChart(CategoryDataset dataset) {
		 
        
        JFreeChart chart = ChartFactory.createAreaChart(
            "持股构成图",             // chart title
            "Stocks",               // domain axis label
            "Number",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setForegroundAlpha(0.5f);
         
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelAngle(0 * Math.PI / 2.0);
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
        // OPTIONAL CUSTOMISATION COMPLETED.
         
        return chart;
	}
	private CategoryDataset createDataset(List<HistoryStockown> l) {
		 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(HistoryStockown h:l){
        	for(int i=0;i<h.date.size();i++){
        		dataset.addValue(h.number.get(i), h.name.toString(), h.date.get(i));
        	}
        }
        
//        dataset.addValue(10.0, "Series 19", "Type 10");
//        dataset.addValue(4.0, "Series 1", "Type 2");
//        dataset.addValue(3.0, "Series 1", "Type 3");
//        dataset.addValue(5.0, "Series 1", "Type 4");
//        dataset.addValue(5.0, "Series 1", "Type 5");
//        dataset.addValue(7.0, "Series 1", "Type 6");
//        dataset.addValue(7.0, "Series 1", "Type 7");
//        dataset.addValue(8.0, "Series 1", "Type 8");
//        dataset.addValue(5.0, "Series 2", "Type 1");
//        dataset.addValue(7.0, "Series 2", "Type 2");
//        dataset.addValue(6.0, "Series 2", "Type 3");
//        dataset.addValue(8.0, "Series 2", "Type 4");
//        dataset.addValue(4.0, "Series 2", "Type 5");
//        dataset.addValue(4.0, "Series 2", "Type 6");
//        dataset.addValue(2.0, "Series 2", "Type 7");
//        dataset.addValue(1.0, "Series 2", "Type 8");
//        dataset.addValue(4.0, "Series 3", "Type 1");
//        dataset.addValue(3.0, "Series 3", "Type 2");
//        dataset.addValue(2.0, "Series 3", "Type 3");
//        dataset.addValue(3.0, "Series 3", "Type 4");
//        dataset.addValue(6.0, "Series 3", "Type 5");
//        dataset.addValue(3.0, "Series 3", "Type 6");
//        dataset.addValue(4.0, "Series 3", "Type 7");
//        dataset.addValue(3.0, "Series 3", "Type 8");
        return dataset;
     
}

}
