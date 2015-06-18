package stocker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Sumreturnrate {
	
	public double sumprice_out;//卖出或者卖空的总额  赚的
	public double sumprice_in;//买入或者补仓的总额  成本
	public List<Remainstock> remainstocklist = new ArrayList<Remainstock>();//买入或者卖空多余的股票
	public List<String> date = new ArrayList<String>();//日期
	public List<Double> rate=new ArrayList<Double>();//收益率

	public   void addSumprice_out(double price,int number){
		
			this.sumprice_out = this.sumprice_out+price*number;
	}
	
	public  void addSumprice_in(double price,int number){
		this.sumprice_in = this.sumprice_in+price*number;
	}

	public void addrate(String dateitem){
		
		 if (!this.date.isEmpty()&&this.date.get(this.date.size() - 1).equals(dateitem)) {
			return;
		}
		
		String[] information = new String[31];
		double remainsumprice_out=0;
		double remainsumprice_in = 0;
		for(int remainloop=0; remainloop<remainstocklist.size();remainloop++){
			
			try {
				information = Internet.share.Internet.getSharedata(remainstocklist.get(remainloop).getPlace(),
						remainstocklist.get(remainloop).getCode());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "请检查网络", "异常", JOptionPane.ERROR_MESSAGE);
			}
			double nowprice = Double.parseDouble(information[3]);
			if(remainstocklist.get(remainloop).getNumber()>0){
				remainsumprice_out+=remainstocklist.get(remainloop).getNumber()*nowprice;
			}
			else if(remainstocklist.get(remainloop).getNumber()<0){
				remainsumprice_in+=(-remainstocklist.get(remainloop).getNumber())*nowprice;
			}
			
		}
		
		DecimalFormat df = new DecimalFormat(".000");
		
		String rateitemstr = df.format(( this.sumprice_out + remainsumprice_out )/(this.sumprice_in+remainsumprice_in)-1);
		double rateitem = Double.parseDouble(rateitemstr);
		
		this.date.add(dateitem);
		this.rate.add(rateitem);
		
	}


}
