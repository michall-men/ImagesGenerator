 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.Material;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector; 

/**
 *
 * @author menaged
   circle, ball*/ 
public class Sphere extends RadialGeometry {
    private Point3D center;
    
    public Sphere(){super(); center=new Point3D();}
    public Sphere(Point3D p){super(); center=new Point3D(p);}
    public Sphere(double num,Point3D p){super(num); center=new Point3D(p);}
    public Sphere(Sphere s){ super(s.getMaterial(),s.getEmLight(),s.getRadius()); center=new Point3D(s.getCenter());}
      
    public  Point3D getCenter(){return new Point3D(center);}
    @Override
    public String toString()
    {   
        String s="center: "+center.toString()+printRadius(); 
        return s;
    }
    @Override
   public List<Point3D> findIntersections(Ray r) {
       System.out.println("in findIntersections sphere");
       List<Point3D> list = new ArrayList<>(); 
        Point3D P0 = new Point3D(r.getStart());
        Vector v = new Vector(r.getDirection());
     /*
     Ray: P = P0 + tV
Sphere: |P - O|2 - r2 = 0, or:  |P-O| =r 
        P1: first Intersection point
L = O - P0
tm = L ・V
d = (|L|^2 - tm^2)^0.5
if d > r -> no intersections 
       
        P2: second Intersection point
th = (r^2 - d^2)^0.5
t1 = tm - th 
t2 = tm + th
P1 = P0 + t1V
P2 = P0 + t2V }take only t > 0
     */
    Vector helper=new Vector(new Point3D(center)); helper.subtract(new Vector(P0));
     double l=helper.length(); //L = O - P0
    
          Vector  tm=new Vector(v); tm.scale(l); //tm = L ・V
      
   
     double tml=tm.length();  
     if((tml-(int)tml)<0.001)tml=(int)tml; 
    
         l= Math.pow(Math.abs(l),2) ;//|L|^2
     double d= Math.sqrt(l-(Math.pow(tml,2))); // d = (|L|^2 - tm^2)^0.5
       
     if (d > this.getRadius())               //if d > r -> no intersections  
     { list.clear();  return list;   }
          
     double th=Math.sqrt(Math.pow(this.getRadius(),2)-Math.pow(d,2));
      
     double t1=tml-th;   System.out.println("t1= "+t1);
     double t2=tml+th;  System.out.println("t2= "+t2);
       Point3D P=new Point3D(); 
     if(t1>0)
     {  System.out.println("t1>0");P= new Point3D(P0); v.scale(t1); P.add(v.getHead());P.add(new Point3D(10,10,10)); list.add(P);}
     if (t2>0)
     {  System.out.println("t2>0"); P= new Point3D(P0); v.scale(t2); P.add(v.getHead()); P.add(new Point3D(10,10,10)); list.add(P);}
       System.out.println("list: "+list.toString());
       return list;
} 
     public Vector getNormal(Point3D point){
      /*   V = P - O  p-point, o-senter, v=vector
           N = V / ||V||*/
         Point3D p=new Point3D(point); p.Substrct(center);
         Vector n=new Vector(p); n.normalise();
         return n;}
   
}
