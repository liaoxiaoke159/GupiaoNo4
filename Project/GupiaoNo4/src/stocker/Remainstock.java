package stocker;

public class Remainstock {
	private String name;//��Ʊ����
	private String code;//��Ʊ����
	private String place;//������
	private int number;//����ʣ����������ж�Ĺ�Ʊ����
	
	public  Remainstock(String name,String code,String place,int number){
		this.name = name;
		this.code=code;
		this.place = place;
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = this.number+number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	

}
