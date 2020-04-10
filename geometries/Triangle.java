/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import primitives.Material;
import primitives.Vector;
import primitives.Point3D;
import primitives.Ray;
 
/**
 *
 * @author menaged
 */
public class Triangle extends geometries{
  private  Point3D p1;
   private  Point3D p2;
    private  Point3D p3;
    
    public Triangle(){super(); p1=new Point3D();p2=new Point3D();p3=new Point3D(); }
    public Triangle(Point3D t1,Point3D t2, Point3D t3) {super(); p1=new Point3D(t1);p2=new Point3D(t2);p3=new Point3D(t3);}
    public  Triangle(Material m,Color c,Point3D t1,Point3D t2, Point3D t3){super(m,c);p1=new Point3D(t1);p2=new Point3D(t2);p3=new Point3D(t3);} 
    public  Triangle(Triangle t){super(t.getMaterial(),t.getEmLight()); p1=t.getPoint1(); p2=t.getPoint2(); p3=t.getPoint3(); }


   public Vector getNormal(){
       Vector v1 = new Vector(p1);
       Vector v2=new Vector(p2);
       Vector v3=new Vector(p3);
     //asiosativ case
      {Point3D help=new Point3D(1,0,0);
       if (p1.getX()==0&&p2.getX()==0&&p3.getX()==0) return new Vector(help);}
        {Point3D help=new Point3D(0,1,0);
       if (p1.getY()==0&&p2.getY()==0&&p3.getY()==0) return new Vector(help);}
          {Point3D help=new Point3D(0,0,1);
       if (p1.getZ()==0&&p2.getZ()==0&&p3.getZ()==0) return new Vector(help);}
          //else....
        Vector normal12=new Vector(v1.subtract(v2));
        Vector normal13=new Vector(v1.subtract(v3));
       Vector normalToTri=new Vector(normal12.crossProduct(normal13));
     normalToTri.normalise(); normalToTri.scale(-1);
       return normalToTri;
   } 

public Point3D getPoint1(){return p1;}
public Point3D getPoint2(){return p2;}
public Point3D getPoint3(){return p3;}

public boolean compareTo(Triangle tri){return p1.compareTo(tri.getPoint1())&&p2.compareTo(tri.getPoint2())&&p3.compareTo(tri.getPoint3());}

    @Override
    public List<Point3D> findIntersections(Ray r){
 System.out.println("in findIntersections triangle"); // if((tml-(int)tml)<0.001)tml=(int)tml; 
     
/*For each side of the triangle:
V1=T1-P0
V2=T2-P0
N1=(V2xV1)/|V2xV1|
Then,
if sign((P-P0)・N1) == sign((P-P0)・N2) == sign((P-P0)・N3)
-> return true */
 
Plane plane=new Plane(p2,getNormal());  //the plane of the triangle

   List<Point3D> list = new ArrayList<>();
        list=plane.findIntersections(r);
     
  if(list.isEmpty()) return list;
   Point3D P=new Point3D(list.get(0)); //      פה הבעיה: אינדקס לא קיים ברשימה ריקה
    Point3D saveP=new Point3D(P);
   
    Point3D P0 = r.getStart();
 
    System.out.println("po=start ray:"+P0.toString());
    Point3D t1 = new Point3D(p1); t1.Substrct(P0);//V1=T1-P0
      Point3D t2 = new Point3D(p2); t2.Substrct(P0);
      Point3D t3 = new Point3D(p3); t3.Substrct(P0);
      Vector v1 = new Vector(t1); Vector v2 = new Vector(t2);  Vector v3 = new Vector(t3);
   P.Substrct(P0);
  Vector PminP0=new Vector(P);
 
// side 1- 1&2 
Vector n1=v2.crossProduct(v1); //N1=(V2xV1)
   n1.normalise(); //N1=(V2xV1)/|V2xV1|
     double sign1 = n1.dotProduc(PminP0)*(-1);
 
     /// side 2 - 1&3
 Vector n2=v2.crossProduct(v3); //N1=(V2xV1)
   n2.normalise(); //N1=(V2xV1)/|V2xV1|

 //   if(n2.dotProduc(PminP0)!=sign){ list.clear();  return list;}
     double sign2 = n2.dotProduc(PminP0)*(-1);
   
     /// side 2 - 2&3
   Vector n3=v3.crossProduct(v1); //N1=(V2xV1)
      n3.normalise();  //N1=(V2xV1)/|V2xV1|

  //   if(n3.dotProduc(PminP0)!=sign)list.clear(); 
     double sign3 = n3.dotProduc(PminP0)*(-1);
     // finish.....
     
     if((sign1<0 && sign2<0 && sign3<0)||(sign1>0 && sign2>0 && sign3>0))
     list.add(saveP);
      return list;
    }

  @Override
 public Vector getNormal(Point3D point){
   /* V1 = P2 - P1
      V2 = P3 - P1
      N = ( V1 x V2 ) / || V1 x V2 ||*/
    Point3D p=new Point3D(p2); p.Substrct(p1);
    Vector v1=new Vector (p);
   
    p=new Point3D(p3);p.Substrct(p1);
   Vector v2=new Vector (p);
    Vector n=v1.crossProduct(v2); n.normalise();
     return n;}


}