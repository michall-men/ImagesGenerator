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
public class Ray {

    private Point3D start;
    private Vector direction;

    public Ray() {
        start = new Point3D();
        direction = new Vector();
    }

    public Ray(Point3D p, Vector v) {
        start=new Point3D(p);
        Vector v2=new Vector(v);
          try{v2.normalise();} 
    catch(Exception e){   direction=new Vector(v2);}
        direction=new Vector(v2);
    }

    public Ray(Point3D p, Point3D v) {
        start=new Point3D(p);
        direction = new Vector(v);
    }
    /////get

    public Point3D getStart() {
        return start;
    }

    public Vector getDirection() {
        return direction;
    }
    ////set

    public void setStart(Point3D p) {
        start = new Point3D(p);
    }
  public void setStart(double x,double y,double z ) {
        start = new Point3D(x,y,z);
    }
    public void setDirection(Vector v) {
             Vector v2=new Vector(v);
        v2.normalise(); 
        System.out.println("normal");
        direction = new Vector(v2);
    }
    ///enother

    public boolean compareTo(Point3D p, Vector v) {
        return start.compareTo(p) && direction.compareTo(v);
    }
@Override
    public String toString() {
       String s=start.toString()+direction.toString();
       return s;
    }

       
 
   
    
}
