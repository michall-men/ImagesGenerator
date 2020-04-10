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
public class DirectionalLight extends Light implements LightSource  {
    private Vector direct;
    
     public DirectionalLight() {
       super();
        direct=new Vector();
    }

    public DirectionalLight(Color c, Vector v) {
        super(c);
        direct=new Vector(v);
    }

    public DirectionalLight(int red, int green, int blue ) {
      super( blue, green,red);
         direct=new Vector();
    }

    public DirectionalLight(DirectionalLight p) {
       super(p.getColor());
        direct=new Vector(p.getDirect());
    }

    public Vector getDirect() {return new Vector(direct); }
    public void setDirect(Vector v) {direct.setHead(v.getHead()); }
   public boolean equals(DirectionalLight d)
   { 
       return this.getColor().equals(d.getColor())&&direct.compareTo(d.getDirect());  
   }
    @Override
    public String toString()
    {
    String mashebali=this.printLight()+" "+direct.toString();
    return mashebali;
    }
    @Override
    public Color getIntensity() {
//IL = I0
		return color;}
   
    @Override
  public Color getIntensity(Point3D p) {return null; }

    @Override
    public Vector getL(Point3D p) {
     // L  וקטור הקרן  
        Point3D l=new Point3D(p); l.Substrct(direct.getHead());
        Vector L=new Vector(l);
        return L;
        }
    
    
    }


