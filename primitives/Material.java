/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 *
 * @author menaged
 */
public class Material {
    	public double  Kd;  // גורמי הרחקה
	public double  Ks; ///גורמי הרחקה
	public int  nShininess;  //מידת מבהיקות של החומר
	public double Kr;//reflected  מקדם השתקפות
	public double Kt;//retracted   מקדם שקיפות
	
	
	public Material(double  d, double  s, int shin,double  r,double  t) {//parameter construct
		super();
		this.Kd =  d;
		this.Ks =  s;
		 nShininess = shin;
		Kr= r;
		 Kt= t;
	}
	
	public Material() {//default constructor
		super();
		this.Kd =1;
		this.Ks =1;
		this.nShininess =1;
		this.Kr=1;
		this.Kt=0;
	}

	public Material(Material m) {//copy constructor
		//super();
		 Kd = m.getKd();
		 Ks = m.getKs();
		 nShininess = m.getnShininess();
		 Kr = m.getKr();
		 Kt = m.getKt();
	}
	

	public double getKd() {return Kd;}

	public void setKd(double Kd) {this.Kd = Kd;}
	
	public double getKr() {return Kr;}

	public void setKr(double Kr) {this.Kr = Kr;}
	public double getKt() {return Kt;}

	public void setKt(double Kt) {this.Kt = Kt;}

	public double getKs() {return Ks;}

	public void setKs(double Ks) {this.Ks = Ks;}

	public int getnShininess() {return nShininess;}

	public void setnShininess(int nShininess) {this.nShininess = nShininess;}
	@Override
	public String toString() {
		return "Material: Kd=" + Kd + ", Ks=" + Ks + ", nShininess=" + nShininess + ", Kr=" + Kr + ", Kt=" + Kt+ " ";
	}

	public boolean CompareTo(Material m) {
           return Kd==m.getKd()&&Ks==m.Ks&&Kr==m.Kr&&Kt==m.Kt&&nShininess==m.getnShininess();
	}

}
