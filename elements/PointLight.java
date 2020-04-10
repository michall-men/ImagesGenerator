/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *
 * @author menaged
 */ 
public class PointLight extends Light implements LightSource {

	protected Point3D position;  //נקודת מרכז האור 
	protected double Kc, Kl, Kq; // גורמי הנחתה של האור


public PointLight() {//default constructor
		super();
		this.position =new Point3D();
		this.Kc = 0;
		this.Kl = 0;
		this.Kq = 0;

	} 

	public PointLight(PointLight l) {//copy constructor 
		super(l.getColor());
		this.position = l.position;
		this.Kc = l.Kc;
		this.Kl = l.Kl;
		this.Kq = l.Kq;
	}

	public PointLight(Color color, Point3D position, double Kc, double Kl, double Kq) {
	super(color);
	this.position = position;
	this.Kc = Kc;
	this.Kl = Kl;
	this.Kq = Kq;
}

	public Point3D getposition() {
		return new Point3D(position);
	}
	public void setposition(Point3D position) {
		this.position = new Point3D(position);
	}
	public double getKc() {
		return Kc;
	}
	public void setKc(double Kc) {
		this.Kc = Kc;
	}
	public double getKl() {
		return Kl;
	}
	public void setKl(double Kl) {
		this.Kl = Kl;
	}
        public double getKq() {
		return Kq;
	}
	public void setKq(double Kq) {
		this.Kq = Kq;
	}

	@Override
	public String toString() {
		return "PointLight [position=" + position + ", Kc=" + Kc + ", Kl=" + Kl + ", Kq=" + Kq + "]";
	}

	//@Override
	public boolean equals(PointLight po) {
         
		if (!position.compareTo(po.getposition()))
			return false;
	return !(Kc!=po.getKc()||Kl!=po.getKl()||Kq!=po.getKq());
	}
        
    @Override
	public Vector getL(Point3D p )
	{ Point3D l=new Point3D(p); l.Substrct(position);
        Vector L=new Vector(l);
        return L;
        } 

        @Override //מחזירה צבע של הנקודה 
	public Color getIntensity(Point3D point) {//IL = I0 / (Kc+kjd+kqd2)
		double d= point.distance(position);
		double k = Kc+Kl*d+Kq*d*d;
		if (k<1) k=1;
		int Red=(int)((color.getRed())/(k));
	    int Green=(int)((color.getGreen())/(k));
	    int Blue=(int)((color.getBlue())/(k)) ;
	    if(Red>255) Red=255;
	    if(Green>255)Green=255;
	    if(Blue>255) Blue=255;
		return new Color (Red,Green,Blue);
	}
        @Override
        public Color getIntensity(){return null;}

    
}//end of "PointLight" class



