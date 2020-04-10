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
public class SpotLight extends PointLight {
private Vector direction;
	
	public SpotLight() {//default constructor
		super();
		this.direction =new Vector(); 
		
	}
	public SpotLight(SpotLight s) {//copy constructor 
		super(s);
		this.direction = s.direction;
	}
	public SpotLight(Color color, Point3D _position, double _Kc, double _Kl, double _Kq,Vector direction) {//parameter constructor 
		super( color,  _position,  _Kc,  _Kl,  _Kq);
		this.direction = direction;
	}

    public SpotLight(Color color, Point3D point3D, Vector ezer, int i, double d, double d0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	public Vector getDirection(){
		return new Vector (direction);
	}
	public void setDirection(Vector direction){
		this.direction = new Vector(direction);
	}

//	@Override
	public boolean equals(SpotLight sp) {
	      //      boolean bool= super(sp);
              if(this==sp)return true;
              if(!direction.compareTo(sp.getDirection()))       
                 return false;
	return !(Kc!=sp.getKc()||Kl!=sp.getKl()||Kq!=sp.getKq());
	}
	@Override
	public String toString() {
		return "SpotLight [direction=" + direction + "]";
	}

	@Override
	public Color getIntensity(Point3D point){ //IL =c* I0 / (Kc+kjd+kqd2)
	
		double c=Math.abs(direction.dotProduc(super.getL(point)));
		double d= point.distance(position);
		double k = Kc+Kl*d+Kq*d*d;
		if (k<1)    k=1;
		if (c>1)	c=1;
	    int Red=(int)(c*(color.getRed())/k);
	    int Green=(int)(c*(color.getGreen())/k);
	    int Blue=(int)(c*(color.getBlue())/k);
		return new Color (Red,Green,Blue);
	}
@Override
        public Vector getL(Point3D p){// L  וקטור הקרן  
        Point3D l=new Point3D(p); l.Substrct(position);
        Vector L=new Vector(l);
        return L;
        }        
  
}
