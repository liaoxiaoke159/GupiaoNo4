package stock;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;


	public class LineChartDemo1 {

		private JFreeChart chart;
	    /**
	     * Creates a new demo.
	     *
	     * @param title  the frame title.
	     */
	    public LineChartDemo1(final String title) {
	       
	        final CategoryDataset dataset = createDataset();
	        chart = createChart(dataset);
//	        final ChartPanel chartPanel = new ChartPanel(chart);
//	        chartPanel.setPreferredSize(new Dimension(500, 270));
//	        setContentPane(chartPanel);
	    }

	    /**
	     * Creates a sample dataset.
	     * 
	     * @return The dataset.
	     */
	    private CategoryDataset createDataset() {
	        
	        // row keys...
	        final String series1 = "�����";
	        final String series2 = "���֯A";
	        final String series3 = "Third";

	        // column keys...
	        final String type1 = "05/10";
	        final String type2 = "05/11";
	        final String type3 = "05/12";
	        final String type4 = "05/13";
	        final String type5 = "05/14";
	        final String type6 = "05/15";
	        final String type7 = "05/16";
	        final String type8 = "05/17";

	        // create the dataset...
	        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	        dataset.addValue(-0.01, series1, type1);
	        dataset.addValue(0.02, series1, type2);
	        dataset.addValue(0.04, series1, type3);
	        dataset.addValue(0.01, series1, type4);
	        dataset.addValue(0.06, series1, type5);
	        dataset.addValue(0.08, series1, type6);
	        dataset.addValue(0.06, series1, type7);
	        dataset.addValue(0.09, series1, type8);

	        dataset.addValue(0.01, series2, type1);
	        dataset.addValue(0.04, series2, type2);
	        dataset.addValue(0.05, series2, type3);
	        dataset.addValue(0.02, series2, type4);
	        dataset.addValue(-0.04, series2, type5);
	        dataset.addValue(0.02, series2, type6);
	        dataset.addValue(0.06, series2, type7);
	        dataset.addValue(.07, series2, type8);
	        
	//
//	        dataset.addValue(4.0, series3, type1);
//	        dataset.addValue(3.0, series3, type2);
//	        dataset.addValue(2.0, series3, type3);
//	        dataset.addValue(3.0, series3, type4);
//	        dataset.addValue(6.0, series3, type5);
//	        dataset.addValue(3.0, series3, type6);
//	        dataset.addValue(4.0, series3, type7);
//	        dataset.addValue(3.0, series3, type8);

	        return dataset;
	                
	    }
	    
	    /**
	     * Creates a sample chart.
	     * 
	     * @param dataset  a dataset.
	     * 
	     * @return The chart.
	     */
	    private JFreeChart createChart(final CategoryDataset dataset) {
	        
	        // create the chart...
	        final JFreeChart chart = ChartFactory.createLineChart(
	            "��Ʊ������",      // chart title
	            "date",                    // domain axis label
	            "Range",                   // range axis label
	            dataset,                   // data
	            PlotOrientation.VERTICAL,  // orientation
	            true,                      // include legend
	            true,                      // tooltips
	            false                      // urls
	        );

	        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//	        final StandardLegend legend = (StandardLegend) chart.getLegend();
	  //      legend.setDisplaySeriesShapes(true);
	    //    legend.setShapeScaleX(1.5);
	      //  legend.setShapeScaleY(1.5);
	        //legend.setDisplaySeriesLines(true);

	        chart.setBackgroundPaint(Color.white);

	        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
	        plot.setBackgroundPaint(Color.lightGray);
	        plot.setRangeGridlinePaint(Color.white);
	        
	        plot.setDomainGridlinesVisible(true);
//	        plot.setRangeAxis(new ValueAxis(0));
	/*        
	        void setDataset(CategoryDataset dataset) ��������2ά���ݱ� 
	        void setColumnRenderingOrder(SortOrder order) ���ݷ��������ʽ 
	        void setAxisOffset(Spacer offset) �����ᵽ�������ļ�� 
	        void setOrientation(PlotOrientation orientation) �������ķ���PlotOrientation.HORIZONTAL��PlotOrientation.VERTICAL�� 
	        void setDomainAxis(CategoryAxis axis) �������ķ����� 
	        void setDomainAxisLocation(AxisLocation location) �������λ�ã�����������org.jfree.chart.axis.AxisLocation���ж��壩 
	        void setDomainGridlinesVisible(boolean visible) �����������Ƿ�ɼ� 
	        void setDomainGridlinePaint(Paint paint) ����������������ɫ 
	        void setDomainGridlineStroke(Stroke stroke) ���������������ʴ� 
	        void setRangeAxis(ValueAxis axis) �������������� 
	        void setRangeAxisLocation(AxisLocation location) �������λ�ã�����������org.jfree.chart.axis.AxisLocation���ж��壩 
	        void setRangeGridlinesVisible(boolean visible) �����������Ƿ�ɼ� 
	        void setRangeGridlinePaint(Paint paint) ����������������ɫ 
	        void setRangeGridlineStroke(Stroke stroke) ���������������ʴ� 
	        void setRenderer(CategoryItemRenderer renderer) �������ı�ʾ�ߣ����Renderer�飩 
	        void addAnnotation(CategoryAnnotation annotation) ����������һ��ע�� 
	        void addRangeMarker(Marker marker,Layer layer) ����������һ����ֵ��Χ����
	*/

	/*
	  ���������ᣬ������Ҫ����x���Y�ܣ��ڲ�ͬ��ͼ��Ľз�����������ͬ����

	����x��ʹ�õ��ࣺCategoryAxis
	CategoryAxis domainAxis = (CategoryAxis)plot.getDomainAxis();

	 void setCategoryMargin(double margin) ������߾� 
	void setLowerMargin(double margin) �������£��󣩱߾� 
	void setUpperMargin(double margin) �������ϣ��ң��߾� 
	void setVerticalCategoryLabels(boolean flag) ����������Ƿ���ת����ֱ 
	void setMaxCategoryLabelWidthRatio(float ratio) ����������ǩ�������
	 
	        
	����Y��ʹ���ࣺNumberAxis
	NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

	 void setAutoRangeIncludesZero(boolean flag) �Ƿ�ǿ�����Զ�ѡ������ݷ�Χ�а���0
	void setAutoRangeStickyZero(boolean flag) �Ƿ�ǿ���������������а���0����ʹ0�������ݷ�Χ�� 
	void setNumberFormatOverride(NumberFormat formatter) ���������ݱ�ǩ����ʾ��ʽ 
	void setTickUnit(NumberTickUnit unit) ����������ݱ�ǩ����Ҫ��AutoTickUnitSelection��false��
	 
	 */
	  
	        // customise the range axis...
	        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        rangeAxis.setAutoRangeIncludesZero(false);
	        
	        //rangeAxis.setInverted(true);
	        rangeAxis.setAutoRange(true);
	        rangeAxis.setAutoRangeMinimumSize(0.01);
//	        rangeAxis.setFixedAutoRange(0.1);
	        rangeAxis.setAutoRangeIncludesZero(true);
//	        rangeAxis.setLowerBound(-0.2);
//	        rangeAxis.setUpperBound(0.2);
//	        rangeAxis.setTickMarksVisible(true);
	        rangeAxis.setTickUnit(new NumberTickUnit(0.01));//������ʾY�����ݼ��

	        // ****************************************************************************
	        // * JFREECHART DEVELOPER GUIDE                                               *
	        // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
	        // * to purchase from Object Refinery Limited:                                *
	        // *                                                                          *
	        // * http://www.object-refinery.com/jfreechart/guide.html                     *
	        // *                                                                          *
	        // * Sales are used to provide funding for the JFreeChart project - please    * 
	        // * support us so that we can continue developing free software.             *
	        // ****************************************************************************
	        
	        // customise the renderer...
	        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//	        renderer.setDrawShapes(true);
	        renderer.setItemLabelsVisible(true);

//	        renderer.setSeriesStroke(
//	            0, new BasicStroke(
//	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
//	                1.0f, new float[] {10.0f, 6.0f}, 0.0f
//	            )
//	        );
//	        renderer.setSeriesStroke(
//	            1, new BasicStroke(
//	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
//	                1.0f, new float[] {6.0f, 6.0f}, 0.0f
//	            )
//	        );
//	        renderer.setSeriesStroke(
//	            2, new BasicStroke(
//	                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
//	                1.0f, new float[] {2.0f, 6.0f}, 0.0f
//	            )
//	        );
	        // OPTIONAL CUSTOMISATION COMPLETED.
	        
	        return chart;
	    }
	    
	    
	    
	    /**
	     * Starting point for the demonstration application.
	     *
	     * @param args  ignored.
	     */
	    public static void main(final String[] args) {

//	        final LineChartDemo1 demo = new LineChartDemo1("Line Chart Demo");
//	        demo.pack();
//	        RefineryUtilities.centerFrameOnScreen(demo);
//	        demo.setVisible(true);
	        
	        LineChartDemo1 chart = new LineChartDemo1("Line Chart Demo");
	        Display display = new Display();
	        Shell shell = new Shell(display);
	        shell.setSize(600, 395);
	        shell.setText("Test for jfreechart running with SWT");
	        shell.setLayout(new FormLayout());
	        
	        final ChartComposite frame2 = new ChartComposite(shell, SWT.NONE, chart.chart,true);
	        frame2.setLayout(new FillLayout(SWT.HORIZONTAL));
	        FormData fd_frame2 = new FormData();
	        fd_frame2.bottom = new FormAttachment(0, 331);
	        fd_frame2.right = new FormAttachment(0, 574);
	        fd_frame2.top = new FormAttachment(0, 10);
	        fd_frame2.left = new FormAttachment(0, 10);
	        frame2.setLayoutData(fd_frame2);
	        try {
				ChartUtilities.saveChartAsPNG(new File("img/linchart.png"), chart.chart, 550, 250);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        frame2.pack();
	        shell.open();
	        while (!shell.isDisposed()) {
	            if (!display.readAndDispatch())
	                display.sleep();
	        }

	    }

	}

