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
public class Vector //extends Point3D
{

    private Point3D head;

    //constractor
    public Vector() {
        head = new Point3D();
    }

    public Vector(Point3D p) {
        Point3D p1 = new Point3D(p);
        head = p1;
    }

    public Vector(double n1, double n2, double n3) {
        Point3D p1 = new Point3D(n1, n2, n3);
        head = p1;
    }

    public Vector(Vector v) {
        head = v.getHead();
    }

    //get set
    public Point3D getHead() {
        Point3D p = new Point3D(head);
        return p;
    }

    public void setHead(double x1, double y1, double z1) {
        head.setX(x1);
        head.setY(y1);
        head.setZ(z1);
    }

    public void setHead(Point3D p) {
        head.setX(p.getX());
        head.setY(p.getY());
        head.setZ(p.getZ());
    }
    //else...

    public double dotProduc(Vector v) { // מכפלה סקלרית
        double helper = 0;
        helper += head.getX() * v.getHead().getX();
        helper += head.getY() * v.getHead().getY();
        helper += head.getZ() * v.getHead().getZ();
        return helper;
    }

    public double length() {
        Point3D start = new Point3D(0, 0, 0);
        double length1 = head.distance(start);
     
        if (length1 >0 && length1 < 1) {
            return 1;
        }
        return length1;
    }

    public void normalise() {
        double length1 = length();  
      
    
           if (length() == 0 || length() == 1) 
           {System.out.println("early normal");
          return; }
               
            head.setX((head.getX()) / length1);
            head.setY((head.getY()) / length1);
            head.setZ((head.getZ()) / length1);
    
    }

    public Vector crossProduct(Vector v) {  //מכפלה וקטורית
        Point3D helper = new Point3D((head.getY() * v.head.getZ() - head.getZ() * v.head.getY()), (head.getZ() * v.head.getX() - head.getX() * v.head.getZ()), (head.getX() * v.head.getY() - head.getY() * v.head.getX()));
        Vector help = new Vector(helper);
        return help;
    }

    public boolean compareTo(Vector v) {
        return head.compareTo(v.head);
    }

    @Override
    public String toString() {
        String s = head.toString() + "legth " + length();
//  System.out.println(s); 
        return (s);
    }

    public Vector subtract(Vector vector) {
        head.Substrct(vector.getHead());
        return this;
    }
  public void add(Vector vector) {
        head.add(vector.getHead());
    }
    
    public void scale(double scalFactor) {
        head.setX(scalFactor * head.getX());
        head.setY(scalFactor * head.getY());
        head.setZ(scalFactor * head.getZ());
       
    }

        
    //@SuppressWarnings("empty-statement")
    /*
   public static void main (String[]args)
 {
   Vector v=new Vector(new Point3D(3,5
           ,0)); 
   System.out.println(v.toString()); 
    try{v.normalise();} 
    catch(Exception e){System.out.println(e.getMessage());}
System.out.println(v.toString());
 }*/
}
