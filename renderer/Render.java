/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer; 

/** 
 *
 * @author menaged
 */
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import elements.Light;
//import geometries.Flatgeometries;
import geometries.geometries;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import Scene.Scene;
import elements.LightSource;
import java.util.ArrayList;
public class Render {

    private Scene Rscene;
    private ImageWriter imageWriter;

    int recursLevel = 3;
// ctor
    public Render(ImageWriter imageWriter1, Scene scene) {
        imageWriter = new ImageWriter(imageWriter1);
        Rscene =  scene; //  new Scene(scene);
        
      //  System.out.println("size is " + scene.getGmtrList().size());
    }
    public Render(Render r) {
        imageWriter = new ImageWriter(r.imageWriter);
        Rscene = new Scene(r.Rscene);
    } //cpy
    public Render() {
        imageWriter = new ImageWriter();
        Rscene = new Scene();
    }  //defult
  //get/set
  public Scene getSean(){return new Scene(Rscene);}
  public ImageWriter getImageWriter(){return new ImageWriter(imageWriter);}
  
  public void setSean(Scene s){Rscene=new Scene(s);}
  public void setImageWriter(ImageWriter im){imageWriter=new ImageWriter(im);}
  
  public boolean equals(Render r){return Rscene.equals(r.getSean())&&imageWriter.equals(r.getImageWriter());}
  @Override
  public String toString (){String s="scene: "+Rscene.toString()+" ImageWriter: "+imageWriter.toString(); return s; }
  
  ///////method
    public void renderImage() {
        System.out.println("Entered renderImage");
        int x = imageWriter.getNx(); //to short the ray ctor...
        int y = imageWriter.getNy();
        int width = imageWriter.getWidth();
        int hi = imageWriter.getHeight(); ////until ther

        for (double i = 0; i < hi; i++) {
            for (double j = 0; j < width; j++) {
               
Ray ray =Rscene.getCamera().constructRayThroughPixel(x, y, j, i, Rscene.getCamDistance(), width, hi);
  
              Entry<geometries,Point3D> entry = findClostInterct(ray);
      
                if (entry == null) {imageWriter.writePixel((int)j, (int)i, Rscene.getBack());} 
                else { imageWriter.writePixel((int)j,(int) i,calcColor(entry.getKey(), entry.getValue(), ray));}
            }
        }
        System.out.println("Exit renderImage");
    }
    public void printGrid(int interval) {   //מדפיסה ריבועים
 System.out.println("printGrid");
        int height = imageWriter.getHeight();
        int width = imageWriter.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, 255, 255, 255);
                } 

            }
        }
    } 
        private Map<geometries, List<Point3D>> getSceneRayIntersections(Ray ray) {

        Iterator<geometries> gmtr = Rscene.getGeometriesIterator();
        System.out.println("size is " + Rscene.getGmtrList().size());
   System.out.println("ther iterator");
      
        Map<geometries, List<Point3D>> intersectionPoints = new HashMap<>();
       /// List<Point3D> geometryIntersectionPoints=new ArrayList<>();
      
        while (gmtr.hasNext()) {  
            System.out.println("in while");
            geometries geometry = gmtr.next(); //System.out.println("geomtry="+geometry.getClass());
              List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
      
            System.out.println("list geometryIntersectionPoints: "+geometryIntersectionPoints.toString());
       
            if (!geometryIntersectionPoints.isEmpty()) {System.out.println("in if");
                intersectionPoints.put(geometry, geometryIntersectionPoints);
                System.out.println( intersectionPoints.toString());
               
            }
         //    gmtr.next();                                                                    
        }
System.out.println("end while");
        return intersectionPoints;
    }
     
    private Map<geometries, Point3D> getClosestPoint(
         Map<geometries, List<Point3D>> intersectionPoints) {

        double distance = Double.MAX_VALUE;
        Point3D P0 = Rscene.getCamera().getSenter();
        Map<geometries, Point3D> minDistancePoint = new HashMap<>();

        for (Entry<geometries, List<Point3D>> entry : intersectionPoints.entrySet()) {
            for (Point3D point : entry.getValue()) {
                double pointDistance = P0.distance(point);
                if (pointDistance < distance) {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = pointDistance;
                }
            } 
        }
        return minDistancePoint;
    }
        
    private Entry<geometries,Point3D> findClostInterct(Ray ray) {
//   System.out.println("enter find closest");
        Map<geometries, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
    System.out.println("finish getSceneRayIntersections");
        if (intersectionPoints.isEmpty()) 
            return null;
        
        Map<geometries, Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Entry<geometries, Point3D> entry = closestPoint.entrySet().iterator().next();
           System.out.println("exit find closest");
        return entry;
    }

    public void writeToImage() {imageWriter.writeToimage();}

   private Color calcColor(geometries geometry, Point3D point, Ray ray)
    {
        return calcColor(geometry, point, ray, 0);
    } 
    private Color calcColor(geometries geometry, Point3D point, Ray inRay, int level) {

        if (level == recursLevel) {
            return new Color(0, 0, 0);
        } 
        //return calcColor(geometry,point,inRay);

        Color amLight = Rscene.getAmbntLight().getIntensity();
        Color emLight = geometry.getEmLight();

        Color inherentColors = addColors(amLight, emLight);

        Iterator<LightSource> lights = Rscene.getLightsIterator();

        Color lightReflected = new Color(0, 0, 0);

        while (lights.hasNext()) {

           LightSource light = lights.next();

            if (!occluded(light, point, geometry)) {

                Color lightIntensity = light.getIntensity(point);

                Color lightDiffuse = calcDiffusiveComp(geometry.getMaterial().getKd(),
                        geometry.getNormal(point),
                        light.getL(point),
                        lightIntensity);
                  Point3D ezer=Rscene.getCamera().getSenter(); ezer.Substrct(point);  /// בגלל שהחיסור אצלי לא מחזיר ערך
                Color lightSpecular = calcSpecularComp(geometry.getMaterial().getKs(),new Vector (ezer),
                        geometry.getNormal(point),
                        light.getL(point),
                        geometry.getMaterial().getnShininess(),lightIntensity);

                lightReflected = addColors(lightDiffuse, lightSpecular);
            } 
        }
 
        Color I0 = addColors(inherentColors, lightReflected);

     //Recursive calls
        // Recursive call for a reflected ray
        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
        Entry<geometries, Point3D> reflectedEntry = findClostInterct(reflectedRay);

        Color reflected = new Color(0, 0, 0);
        if (reflectedEntry != null) {
            reflected = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
            double kr = geometry.getMaterial().getKr(); //kr בשורה הבאה צריך לכפול את כל הצבעים ב
            reflected = new Color((int) (reflected.getRed()*kr), (int) (reflected.getGreen()*kr), (int) (reflected.getBlue()));
        }

        // Recursive call for a refracted ray
       Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        Entry<geometries, Point3D> refractedEntry = findClostInterct(refractedRay);
        Color refracted = new Color(0, 0, 0);
        if (refractedEntry != null) {
            refracted = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
           double kt = geometry.getMaterial().getKt(); //kt בשורה הבאה צריך לכפול את כל הצבעים ב
            refracted = new Color((int) (refracted.getRed()*kt ), (int) (refracted.getGreen()*kt), (int) (refracted.getBlue()*kt));
        }

       // Recursive calls
        Color envColors = addColors(reflected, refracted);

        Color finalColor = addColors(envColors, I0);

        return finalColor;
    }
  /*  private Color calcColor(Point3D point) {
        //return Rscene.getAmbntLight().getIntensity();
                 
        //return calcColor(geometry,point,inRay);

        Color amLight = Rscene.getAmbntLight().getIntensity();
      //  Color emLight = geometry.getEmLight();
     Color finalColor=new Color(amLight.getBlue(),amLight.getGreen(),amLight.getRed());

        return finalColor;
    }*/
          
  private Ray constructRefractedRay(geometries geometry, Point3D point, Ray inRay) {

        Vector normal = geometry.getNormal(point);
        normal.scale(-2);
        point.add(normal.getHead());

     /*   if (geometry instanceof Flatgeometries) {
            return new Ray(point, inRay.getDirection());
        } else {*/
            // Here, Snell's law can be implemented.
            // The refraction index of both materials had to be derived
            return new Ray(point, inRay.getDirection());
        }

  private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay) {

        Vector v = inRay.getDirection();
        v.normalise();

        normal.scale(-2 * v.dotProduc(normal));
              
        v.add(normal);

        Vector R = new Vector(v);
        R.normalise();
        point.add(normal.getHead());

        Ray reflectedRay = new Ray(point, R);

        return reflectedRay;
    }
 
  private boolean occluded(LightSource light, Point3D point, geometries geometry) {

        Vector lightDirection = light.getL(point);
        lightDirection.scale(-1);
        lightDirection.normalise();

        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scale(2);
        geometryPoint.add(epsVector.getHead());

        Ray lightRay = new Ray(geometryPoint, lightDirection);
        Map<geometries, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);

    //   Flat geometry cannot self intersect 
      /*  if (geometry instanceof Flatgeometries) {
            intersectionPoints.remove(geometry);
        }*/

        for (Entry<geometries, List<Point3D>> entry : intersectionPoints.entrySet()) {
            if (entry.getKey().getMaterial().getKt() == 0) {
                return true;
            }
        }

        return false;

    } 

 private Color calcSpecularComp(double ks, Vector v, Vector normal,
            Vector l, double shininess, Color lightIntensity) {

        v.normalise();
        normal.normalise();
        l.normalise();

        normal.scale(-2 * l.dotProduc(normal));
        l.add(normal);
        Vector R = new Vector(l);
        R.normalise();

        double k = 0;

        if (v.dotProduc(R) > 0) // prevents glowing at edges
        {
            k = ks * Math.pow(v.dotProduc(R), shininess);
        }

        return new Color((int) (lightIntensity.getRed() * k),
                (int) (lightIntensity.getGreen() * k),
                (int) (lightIntensity.getBlue() * k));
    }

private Color calcDiffusiveComp(double kd, Vector normal,Vector l, Color lightIntensity) 
    {
        normal.normalise(); l.normalise();
        double k = Math.abs(kd * normal.dotProduc(l));
        return new Color((int) (lightIntensity.getRed() * k),
                (int) (lightIntensity.getGreen() * k),
                (int) (lightIntensity.getBlue() * k));
    }

private Color addColors(Color a, Color b) {

        int R = a.getRed() + b.getRed();
        if (R > 255) {
            R = 255;
        }

        int G = a.getGreen() + b.getGreen();
        if (G > 255) {
            G = 255;
        }

        int B = a.getBlue() + b.getBlue();
        if (B > 255) {
            B = 255;
        }

        Color I = new Color(R, G, B);

        return I;

    }





  /* private int[] calcColor(geometries key, Point3D value, Ray ray) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Color addColors(Color reflected, Color refracted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     
*/
}
