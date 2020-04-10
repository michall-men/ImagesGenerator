/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Color;
import primitives.Point3D;

/**
 *
 * @author menaged
 */
public abstract class Light {
    Color color;
    
    public Light(){ color = new Color(255, 255, 255);}
    public Light(Color c){
     color = new Color(c.getBlue(), c.getGreen(), c.getRed());
    }
    public Light(int red , int green, int blue) {
        color = new Color(blue, green, red);}
    public Color getColor() {
        return new Color(color.getBlue(), color.getGreen(), color.getRed());
    }
   public void setColor(int r,int g,int b) {
        color= new Color(r,g,b);
    }
      public void setColor(Color c) {
        color= c;
    }
    public String printLight() {
        String a = "Color: " + this.getColor().toString()+" ";
        return a;
    }
    /* public boolean equals(Light li)
     { return(color.equals(li));    
     }*/
        
         public abstract  Color getIntensity(); //abstract method
         
        
}
