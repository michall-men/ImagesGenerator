/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import java.awt.Color;
import java.util.List;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;

/**
 *
 * @author menaged
 */
public abstract class RadialGeometry extends geometries {
    private double radius;
    
  public  RadialGeometry(){super();radius=0;}
  public  RadialGeometry(double num){super(); radius=num;}
  public  RadialGeometry(Material m,Color c,double num){super(m,c); radius=num;} 
  
  public void setRadius(double num){radius=num;}
   public double getRadius(){return radius;}
  public String printRadius()
  { String s="radius="+radius;
  return s;
  } 
  
    public boolean copareTo(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RadialGeometry other = (RadialGeometry) obj;
        return Double.doubleToLongBits(this.radius) == Double.doubleToLongBits(other.radius);
    }
    
    @Override
    public abstract  List<Point3D> findIntersections(Ray r);
}
