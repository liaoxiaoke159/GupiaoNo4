package stocker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Sumreturnrate {
	
	public double sumprice_out;//�����������յ��ܶ�  ׬��
	public double sumprice_in;//������߲��ֵ��ܶ�  �ɱ�
	public List<Remainstock> remainstocklist = new ArrayList<Remainstock>();//����������ն���Ĺ�Ʊ
	public List<String> date = new ArrayList<String>();//����
	public List<Double> rate=new ArrayList<Double>();//������

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
				JOptionPane.showMessageDialog(null, "��������", "�쳣", JOptionPane.ERROR_MESSAGE);
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
