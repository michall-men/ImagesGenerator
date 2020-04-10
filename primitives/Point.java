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
public class Point  // extends Cordinate 
{
    
  private Cordinate x;
  private Cordinate y;
  
 // constractor
  public Point(){ x=new Cordinate(); y=new Cordinate();}
   public Point(double num1,double num2){x=new Cordinate(num1); y=new Cordinate(num2);}
 public Point(Cordinate c1,Cordinate c2 ){ y.setCoor(c2.getCoor()); x.setCoor(c1.getCoor());}
    /// x

  public double getX(){return x.getCoor();}
  public void setX(double num){x.setCoor(num);} 
  
  // y
  
 public double getY(){return y.getCoor();}
  public void setY(double num){y.setCoor(num);} 
  
   
   // they bouth
   
 public boolean compareTo(Point p){
     return (y.getCoor()==p.getY())&&(x.getCoor()==p.getX());}
 
  @Override
 public String toString(){ 
     String s=x.toSring();
  s+=","+y.toSring();
   return s; }
 
  public void add(Point c1){x.add(c1.x); y.add(c1.y); }
  public void Substrct (Point c1){
       
       x.Substrct(c1.x); y.Substrct(c1.y); }
/*
 public static void main(String[]args)
  {
  Point c1; Point c2;
  c1=new Point(); System.out.println(c1.toString());
  c2=new Point(20,3);
  c1.add(c2); System.out.println(c1.toString());
  c2.Substrct(c1); System.out.println(c2.toString()); 
  

  }  */
    
    
}
   