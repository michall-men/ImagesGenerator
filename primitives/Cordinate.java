/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

/**
 *
 * @author michall menaged 315473355
 */
public class Cordinate {

    private double coordinate;

   public Cordinate() {
        coordinate = 0d;
    }

   public Cordinate(double num) {
        coordinate = num;
    }

   public Cordinate(Cordinate num) {
        coordinate = num.coordinate;
    }
/*
  public  Cordinate getCoor() {
        Cordinate c = new Cordinate(coordinate);
        return c;
    }
*/
 public  double getCoor() {
        double t2 = coordinate;
        return t2;
    }

    public void setCoor(double num) {
        coordinate = num;
    }

  public  boolean compareTo(Cordinate c1) {
        return coordinate == c1.getCoor();
    }

  public  String toSring() {
        String s=""+coordinate; return s;
    }

  public  double add(Cordinate c1) {
        coordinate = coordinate + c1.getCoor();
        return coordinate;
    }

   public double Substrct(Cordinate c1) {
        coordinate = coordinate - c1.getCoor();
        return coordinate;
    }


/*  public static void main(String[]args)
  {
  Cordinate c1; Cordinate c2;
  c1=new Cordinate(33);
  c2=new Cordinate(23);
  System.out.println(c1.add(c2));
   System.out.println(c1.Substrct(c2));
  }
    //that class working!!!*/
    
}

    
    
    
   