/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import java.util.List;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * גליל
 * @author menaged
 */
public class Cylinder extends RadialGeometry {
    private Point3D axisPoint;
    private Vector axistDirection;
    
 public Cylinder(){super(); axisPoint=new Point3D(); axistDirection=new Vector();}   
 public Cylinder(Point3D p, Vector v){super(); axisPoint=new Point3D(p); axistDirection=new Vector(v);} 
//public List<Point3D> findIntersections(){}  
 public  Point3D getAxisPoint(){return new Point3D(axisPoint);}
    @Override
     public String toString()
    {   
        String s="axis-Point: "+axisPoint.toString()+"axist-Direction: "+axistDirection.toString()+this.printRadius();
        return s;
    }
    @Override
      public List<Point3D> findIntersections(Ray r){ 
          throw new UnsupportedOperationException("Not supported yet.");
      } //To change body of generated methods, choose Tools | Templates.
    @Override
       public Vector getNormal(Point3D point){return null;}
}
