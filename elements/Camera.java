/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package elements;

import primitives.Ray;
import primitives.Vector;
import primitives.Point3D;
/**
 *
 * @author menaged
 */
public class Camera {

    private Point3D P0;
    private Vector vUP;
    private Vector vRight;
    private Vector vToward;

    public Camera() {
        P0 = new Point3D();
        Point3D p;
        p = new Point3D(1,0,0);
       vRight = new Vector(p);
        p.setX(0); p.setY(1);
        vUP = new Vector(p); 
        p.setY(0); p.setZ(1);
        vToward = new Vector(p);
    }

    public Camera(Point3D s) {
        P0 = new Point3D(s);
        Point3D p;
        p = new Point3D(1, 0, 0);
       vRight = new Vector(p);
        p.setX(0);
        p.setY(1);
        vUP = new Vector(p); 
        p.setY(0);
        p.setZ(1);
        vToward = new Vector(p);
    }
    public Camera(Camera c)
    {
        P0 = new Point3D(c.getSenter());
        vUP = new Vector(c.getUP());
        vRight = new Vector(c.getRight());
        vToward = new Vector(c.getToward());        
    }

    // get
    public Point3D getSenter() {
        Point3D p = new Point3D(P0);
        return p;
    }

    public Vector getUP() {
        Vector v = new Vector(vUP);
        return v;
    }

    public Vector getRight() {
        Vector v = new Vector(vRight);
        return v;
    }

    public Vector getToward() {
        Vector v = new Vector(vToward);
        return v;
    }

    //set 
   /* public void setSenter(Point3D u) {
        P0.setX(u.getX(1));
        P0.setY(u.getY(0));
        P0.setZ(u.getZ(0));
    }*/
public void setSenter(Point3D u) {
     Camera c=new Camera(u);
     P0=new Point3D(u);
     vUP=new Vector(c.getUP());
     vRight=new Vector(c.getRight());
     vToward=new Vector(c.getToward());
    }

    private boolean IsNormal(Vector v1, Vector v2,Vector v3) throws Exception{  //בודקת עם הוקטור הקורא לה מאונך לאחרים
        Vector v; 
        v = new Vector(v1.crossProduct(v2));
         if(!v3.compareTo(v))
          {  throw new Exception("not normal to the other");}
        
          return true;
       
    }

    public void setUP(Vector c) {
         try{IsNormal(vToward,vRight,vUP);} catch(Exception e){System.out.println(e.getMessage());}
        vUP.setHead(c.getHead()); }
  
    public void setRight(Vector c) {
          try{IsNormal(vUP,vToward,vRight);} catch(Exception e){System.out.println(e.getMessage());}
        vRight.setHead(c.getHead()); }

    public void setToward(Vector c) throws Exception {
        try{IsNormal(vUP,vRight,vToward);} catch(Exception e){System.out.println(e.getMessage());}
        vToward.setHead(c.getHead()); }
// other
  public boolean equals(Camera c)
  {
  return(P0.compareTo(c.getSenter())&&vUP.compareTo(c.getUP())&&vRight.compareTo(c.getRight())&&vToward.compareTo(c.getToward()));
  }
    @Override
    public String toString()
{
    String s="P0="+P0.toString()+" ";
    s=s+"UP="+vUP.toString()+" ";
    s=s+"RIGHT="+vRight.toString()+" ";
    s=s+"TOWARD"+vToward.toString()+" ";
    System.out.println(s);
    return s;
}
public Ray constructRayThroughPixel  
         (int Nx, int Ny,//כמה פיקסלים על כל ציר
                 double x, double y,double sDist
                 , int sWidth,int sHeight // של המסך
         )
{ 
    // עליי לחשב את נקודת וכיוון הוקטור האנך למצלמה ובעזרתו את הכיוון והנקודה מהפיקסל 
    // הקרן הסופית יוצאת מפיקסל על המסך בכיוון הוקטור מהמצלמה למסך
    //המסך הוא לא ממומש- הוא רק הפרמטרים שקיבלתי
  Point3D Pc=new Point3D(P0);
    Vector v=new Vector(vToward);
    v.scale(sDist); //וקטור הכיוון מוכפל במרחק מהמסך 
   // Ray r=new Ray();
  //  r.setDirection(v);
  //  r.setStart(x,y,0);
           //calculate p=pc+[A*Vright - B*Vup];
        double Rx=sWidth/Nx;
        double A=(x-(Nx/2))*Rx+(Rx/2);
        Vector Vright=new Vector(vRight);
        Vright.scale(A);
        double Ry=sWidth/Ny;
        double B=(y-(Ny/2))*Ry+(Ry/2);
        Vector Vup=new Vector(vUP);
        Vup.scale(B);
        Vright.subtract (vUP);//Vright-Vup        
        Point3D point=new Point3D(Pc);
        point.add(Vright.getHead());
        Vector directionRay=new Vector( point);
        directionRay.normalise();
        
        return new Ray(this.P0,directionRay);//point
}
/*
     public static void main(String[]args)
     { 
     Camera c=new Camera();
     c.toString();
     Point3D p0=new Point3D(1,1,1);
     p0.toString();
     c.setSenter(p0);
     c.toString();
        String h = c.constructRayThroughPixel(1, 1, 4).toString();
     System.out.print(h);
     Ray r=new Ray();
     Vector v=new Vector(1,0,0);
     r.setDirection(v);
     p0.setX(6);
     v.setHead(p0);
     try
     { c.setToward(v);} catch(Exception e){System.out.println(e.getMessage());}
     c.toString();
     }*/
         
}
