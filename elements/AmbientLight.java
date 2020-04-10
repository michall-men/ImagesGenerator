/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;
import java.awt.Color;
/**
 *
 * @author menaged
 */
public class AmbientLight extends Light{

    private double ka;    //עוצמת האור בין אפס לאחת 
                          //attenuation factor
    public AmbientLight() {
       super();
        ka = 1;
    }

    public AmbientLight(Color c, double k) {
        super(c);
        ka = k;
    }

    public AmbientLight(int red, int green, int blue ) {
      super(blue, green,  red);
        ka = 0.1;
    }

    public AmbientLight(AmbientLight p) {
       super(p.getColor());
        ka = p.ka;
    }

    public double getKapacity() {
        double c = ka;
        return c;
    }
    public void setKapacity(double k) {
        ka=k;
    }

    public boolean equals(AmbientLight p) {
        return (this.getColor().equals(p.getColor()) && ka == p.getKapacity());
    }

    @Override
    public String toString() {
        String a =printLight()+ " Kapacity: " + getKapacity();
        return a;
    }

    @Override
    public Color getIntensity() {
        double b, g, r;
        b = (double) this.getColor().getBlue() * ka;
        g = (double) this.getColor().getGreen() * ka;
        r = (double) this.getColor().getRed() * ka;
        return new Color( (int) b, (int) g,(int) r);
    }
  }

