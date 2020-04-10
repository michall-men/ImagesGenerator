/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *
 * @author menaged
 */
public class Plane extends geometries implements FlatGeometry{
 private  Point3D p1;
 private  Vector normal;

public Plane(){p1=new Point3D(); normal=new Vector();}
public Plane(Point3D t1,Vector v1) {p1=new Point3D(t1);normal=new Vector(v1);}
   
public Vector getNormal(){return normal;}
public Point3D getPoint(){return p1;}
 
public boolean compareTo(Plane plane1){return (p1.compareTo(plane1.getPoint())&&normal.compareTo(plane1.getNormal()));}
 @Override
 public String toString(){String s="point on the plane:"; s+=p1.toString(); s+=" normal to the plane:"+normal.toString(); return s;}
    
 @Override
    public List<Point3D> findIntersections(Ray r) {
        List<Point3D> list = new ArrayList<>();

        //Ray: P = P0 + tV
        // Plane: N・(P - Q) = 0. Q0=r.start, N=this.normal
        //  N・(P0 + tV - Q) = 0. t=
        //Ray: P = P0 + tV
        // Plane: N・(P - Q) = 0. Q0=r.start, N=this.normal
        //  N・(P0 + tV - Q) = 0. t=
        Vector v = new Vector(r.getDirection());
        Point3D P0 = new Point3D(r.getStart());
        Point3D Q0 = new Point3D(p1);
        Vector n = new Vector(normal);
        P0.Substrct(Q0); //P0-Q0 
        //   t = - N・(P0 - Q)/(N・V)
        Vector p0 = new Vector(P0); // p0=vector, P0=point
        double t = n.dotProduc(p0); //t=N*(P0-Q)
        t = t / (n.dotProduc(v));   // t =N・(P0 - Q)/(N・V) 
        t = t * (-1);
        v.scale(t);// tV
        P0.add(r.getDirection().getHead());
        Point3D P = new Point3D(P0);    //Ray: P = P0 + tV
        if (t >= 0) {
            /*ray_direc.scalarMult(t);//
			Vector v1=new Vector(ray_direc);
                        ray_P0.add(v1);
		    Point3D p1=new Point3D(ray_P0);
			list.add(p1);
		}*/
            v.scale(t);
            P.add(v.getHead()); 
           // P.add(new Point3D(10,10,10));
            //    v.setHead(P);
            list.add(P);
        }
//if(list.isEmpty())return null;
      
return list;
    }
 @Override
 public Vector getNormal(Point3D point){return getNormal();}
} 