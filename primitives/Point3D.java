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
public class Point3D extends Point
{
     private Cordinate z;
  
     public Point3D(){super(); z=new Cordinate(0d);}
    public Point3D(double num1,double num2,double num3){super(num1,num2); z=new Cordinate(num3);}
    public Point3D( Point3D p){super(p.getX(),p.getY()); z=new Cordinate(p.getZ()); }

    public Point3D(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   // z
 // public  double getZ(int t){return z.getCoor(1);}
   public double getZ(){return z.getCoor();}
   public void setZ(double num){z.setCoor(num);}
  
   
 // every body

 public boolean compareTo(Point3D c1){
     return ((this.getX()==c1.getX())&&(this.getY()==c1.getY()) &&(z.getCoor()==c1.getZ()));}
 
 
     @Override
     public String toString()
{
    String s= "("+super.toString()+","+getZ()+")";
   // System.out.println(s);
    return s;
}

   //void add(Point3D c1){this.setX((this.getX(1))+c1.getX(1)); y=y+c1.getY(); z=z+c1.getZ();  }
  
   
public void add(Point3D c1){
        Point ezer=new Point(c1.getX(),c1.getY());
      this.add(ezer);
  //   this.setZ(this.getZ()+c1.getZ());
      setZ(z.getCoor()+c1.getZ());
   }
   
 public  void Substrct (Point3D c1){
       this.setX(getX()-(c1.getX()));
       this.setY(getY()-(c1.getY()));
            setZ(z.getCoor()-c1.getZ()); }
  
  public double distance(Point3D c){ 
   //  if(this.compareTo(c))return 0;
  
      double ezer= this.getX()-c.getX();
       double ezer1= this.getY()-c.getY();
       double ezer2= this.getZ()-c.getZ();
     ezer=Math.pow(ezer, 2); ezer1=Math.pow(ezer1, 2);  ezer2=Math.pow(ezer2, 2);
   
       return (Math.sqrt(ezer+ezer1+ezer2)) ;
   }
   
 /*  public static void main(String[]args)
   {
       Point3D p1= new Point3D(4d,5d,5d);
       Point3D p2= new Point3D(p1);
              p1.toSring();  p2.toSring();
             
          double r= p1.distance(p2);
              System.out.println(r);
              p1.toSring();

   }  */         
           
           
           
}

