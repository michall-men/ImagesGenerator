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

    int RECURSION_LEVEL = 3;
// ctor
    public Render(ImageWriter imageWriter1, Scene scene) {
    	this._imageWriter = new ImageWriter(imageWriter1);
    	this._Rscene = new Scene(scene);
    }
    public Render(Render r) {
    	this._imageWriter = new ImageWriter(r.imageWriter);
    	this._Rscene = new Scene(r.Rscene);
    } //cpy
    public Render() {
    	this._imageWriter = new ImageWriter();
    	this._Rscene = new Scene();
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
       
        int x = imageWriter.getNx(); //to short the ray ctor...
        int y = imageWriter.getNy();
        int Width = imageWriter.getWidth();
        int Height = imageWriter.getHeight(); ////until ther

        for (double i = 0; i < Height; i++) {
            for (double j = 0; j < Width; j++) {
               
            	ArrayList<Ray> five_rays = this.Rsene.getmyCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), j, i,Rsene.getScreenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight());
                Color temp_c=new Color(0,0,0);//new Color(Rsene.getBackground().getRed(),Rsene.getBackground().getGreen(),Rsene.getBackground().getBlue());
                Color final_color=new Color(0,0,0);
                ArrayList<Color> colors_all_rays=new ArrayList<Color>();
                for(int k=0; k<9; k++)//5
                {                	
                	Map<Geometry,List<Point3D>> intersectionPoints =getSceneRayIntersections(five_rays.get(k));
                	
                	if (intersectionPoints.isEmpty())
                	{
                		temp_c=Rsene.getBackground();
                		colors_all_rays.add(temp_c);
                		//_imageWriter.writePixel(j, i, Rsene.getBackground());
                	}
                	else
                	{
                		Map<Geometry,Point3D> closestPoint = getClosestPoint(intersectionPoints);
                		for(Entry<Geometry,Point3D> g:closestPoint.entrySet())
                		{
                			temp_c=calcColor(g.getKey(),g.getValue(),five_rays.get(k));
                			colors_all_rays.add(temp_c);
                			//_imageWriter.writePixel(j, i, calcColor(g.getKey(),g.getValue(),five_rays.get(k)));
                		}
                	}
                	
                }
               
                //take the color of the center of the pixel: 
                double red=0.5*colors_all_rays.get(0).getRed();
                double green=0.5*colors_all_rays.get(0).getGreen();
                double blue=0.5*colors_all_rays.get(0).getBlue();
                //take the others:
                for(int q=1; q<9; q++)
                {
                red+=0.0625*colors_all_rays.get(q).getRed();
                green+=0.0625*colors_all_rays.get(q).getGreen();
                blue+=0.0625*colors_all_rays.get(q).getBlue();                
                }
                
                if(red>255)
                	red=255;
                if(green>255)
                	green=255;
                if(blue>255)
                	blue=255;
                
                final_color=new Color((int)red,(int)green,(int)blue);
                _imageWriter.writePixel(j, i, final_color);    
        }
     }
    }
    private List<Point3D> getSceneRayIntersections(Ray ray)throws Exception
        {
            Iterator<geometries> geometries1 = Rsene.getGeometriesIterator();
            List<Point3D> intersectionPoints = new ArrayList<Point3D>();
            while (geometries1.hasNext())
            {
            	geometries geometry = geometries1.next();
                List<Point3D> geometryIntersectionPoints    =geometry.findIntersections(ray);
                intersectionPoints.addAll(geometryIntersectionPoints);//?
                
            }
            return intersectionPoints;
        }
 
    public void printGrid(int interval) {   //׳�׳“׳₪׳™׳¡׳” ׳¨׳™׳‘׳•׳¢׳™׳�
 System.out.println("printGrid");
        int height = imageWriter.getHeight();
        int width = imageWriter.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
            {
                if(i%interval==0||j%interval==0)//50
                    _imageWriter.writePixel(j ,i , Color.white);//255
                _imageWriter.writePixel(j,_imageWriter.getHeight()-1, Color.white);
                _imageWriter.writePixel(_imageWriter.getWidth()-1,i, Color.white);            
            } 

            }
        }
     
    private Map<geometries, List<Point3D>> getSceneRayIntersections(Ray ray) {

        Iterator<geometries> gmtr = Rscene.getGeometriesIterator();
        Map<geometries, List<Point3D>> intersectionPoints = new HashMap<geometries, List<Point3D>>();
    
        while (gmtr.hasNext()) {  
          
            geometries geometry = gmtr.next(); //System.out.println("geomtry="+geometry.getClass());
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
      
            if (!geometryIntersectionPoints.isEmpty())
                intersectionPoints.put(geometry, geometryIntersectionPoints);
            }
        return intersectionPoints;                                                                
        }

     
    private Map<geometries, Point3D> getClosestPoint(
         Map<geometries, List<Point3D>> intersectionPoints) {

        double distance = Double.MAX_VALUE;
        Map<geometries, Point3D> minDistancePoint = new HashMap<geometries,Point3D>();      
        elements.Camera c=scene.getmyCamera();        
        Point3D P0 = Rscene.getCamera().getSenter();
      
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

        if (level == RECURSION_LEVEL) {
            return new Color(0, 0, 0);
        } 
        Color amLight = Rscene.getAmbntLight().getIntensity(); //ambient-Light
        Color emLight = geometry.getEmLight(); //emission-Light
        Color diffuseLight=new Color(0,0,0);
        Color specularLight=new Color(0,0,0);
        Color deffuse=null;
        Color specular=null;
       // Color inherentColors = addColors(amLight, emLight);

        Iterator<LightSource> lights = Rscene.getLightsIterator();

       // Color lightReflected = new Color(0, 0, 0);

        while (lights.hasNext()) {

           LightSource light = lights.next();

            if (!occluded(light, point, geometry)) {

                Color lightIntensity = light.getIntensity(point);
                //deffuse calc
                 deffuse = calcDiffusiveComp(geometry.getMaterial().getKd(),
                        geometry.getNormal(point),
                        light.getL(point),
                        lightIntensity);
                 int RD=diffuseLight.getRed()+diffuse.getRed();
                 int GD=diffuseLight.getGreen()+diffuse.getGreen();
                 int BD=diffuseLight.getBlue()+diffuse.getBlue();
                 if(RD>255)
                     RD=255;
                 if(GD>255)
                     GD=255;
                 if(BD>255)
                     BD=255;
             
                 diffuseLight=new Color(RD,GD,BD);
               //specular calc:
                 specular = calcSpecularComp(g.getMaterial().getKs(),
                             new Vector(p, Rsene.getmyCamera().getP0()),
                             g.getNormal(p), light.getL(p), g.getMaterial().getnShininess(),lightIntensity);
                     int RS=specularLight.getRed()+specular.getRed();
                     int GS=specularLight.getGreen()+specular.getGreen();
                     int BS=specularLight.getBlue()+specular.getBlue();
                     if(RS>255)
                         RS=255;
                     if(GS>255)
                         GS=255;
                     if(BS>255)
                         BS=255;
                 
                     specularLight=new Color(RS,GS,BS);
            }
            } 
        //a ray (= beam of light) that is reflected from a surface
        //reflected:
        double kr = geometry.getMaterial().getKr();
        Color reflected = new Color(0, 0, 0);  //reflected light
     //   Color I0 = addColors(inherentColors, lightReflected);

        if(kr!=0)
        {
     	   Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);//reflected ray
            Map<geomtries, Point3D> reflectedEntry = getClosestPoint(getSceneRayIntersections(reflectedRay));
      for(Entry<geomtries,Point3D> b: reflectedEntry.entrySet()){
     	   
        Color reflectedColor = calcColor(b.getKey(), b.getValue(), reflectedRay, level + 1);//geometry, point
        reflectedLight= new Color (reflectedLight.getRed()+(int)kr *reflectedColor.getRed(),
     		   					reflectedLight.getGreen()+(int)kr * reflectedColor.getGreen(),
     		   					reflectedLight.getBlue()+(int)kr * reflectedColor.getBlue());
      }
        }
        else reflectedLight=new Color(0,0,0);
      //refracted:קרן העוברת שינוי במהירות ובכיוון, כתוצאה מאינטראקציה עם המדיום החומרי שבו הוא נוסע
        double kt = geometry.getMaterial().getKt();
        Color refractedLight=new Color(0,0,0);
        if(kt!=0)
        {
        Ray refractedRay = constructRefractedRay(g, p, inRay);
        Map<geomtries, Point3D> refractedEntry = getClosestPoint(getSceneRayIntersections(refractedRay));
        for(Entry<geomtries,Point3D> b: refractedEntry.entrySet()){
     	   
        Color refractedColor = calcColor(b.getKey(), b.getValue(), refractedRay, level + 1);
        
        refractedLight = new Color (refractedLight.getRed()+(int)kt * refractedColor.getRed(),
     		   					refractedLight.getGreen()+(int)kt * refractedColor.getGreen(),
     		   					refractedLight.getBlue()+(int)kt * refractedColor.getBlue());
        	}
        }
        
        int R=amLight.getRed() + emLight.getRed()+diffuseLight.getRed()+specularLight.getRed()+ reflectedLight.getRed() + refractedLight.getRed();
        if(R>255)
            R=255;
        int G=amLight.getGreen() + emLight.getGreen()+diffuseLight.getGreen()+specularLight.getGreen()+ reflectedLight.getGreen() + refractedLight.getGreen();
        if(G>255)
            G=255;
        int B=amLight.getBlue() + emLight.getBlue()+diffuseLight.getBlue()+specularLight.getBlue()+ reflectedLight.getBlue() + refractedLight.getBlue();
        if(B>255)
            B=255;           
            
        Color I0 = new Color (R,G,B);
        return I0; 
        
   }
       
  private Ray constructRefractedRay(geometries geometry, Point3D point, Ray inRay) {//שבירה
	  Vector g_normal=geometry.getNormal(point);
	  g_normal.scalarMult(-2);
	  point.add(g_normal);
	  	 
	  return new Ray(point, inRay.getDirection());
		 
        }

  private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay) {

	  Vector d=inRay.getDirection();//direction of inRay=the ray that got out of the camera and hit the geometry
	   d.normalise();//nirmul of inRay direction
	   g_normal.scalarMult(-2*d.dotProduct(g_normal));//g_normal=-2*d*normal_of_geometry
	   d.add(g_normal);//	   
	   Vector d_R=new Vector(d);//d_R=d
	   d_R.normalise();//nirmul of d_R	   
	   p.add(g_normal);//p=point+normal_of_geometry
	   Ray reflectedRay=new Ray(p,d_R);//reflected ray is the ray that comes from the point in the direction of d(=d_R)
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

        if (geometry instanceof FlatGeometry)
        {
            intersectionPoints.remove(geometry);
        }
        for (Entry<geometries, List<Point3D>> entry: intersectionPoints.entrySet())
        	if (entry.getKey().getMaterial().getKt() == 0)
        		return true;
        	return false;/// return !intersectionPoints.isEmpty();
    } 

 private Color calcSpecularComp(double ks, Vector v, Vector normal,
            Vector l, double shininess, Color lightIntensity) {
     L.normalise();
     N.normalise();
     Vector R=new Vector(L);//R=L=D
     N.scale(-2*L.dotProduc(N));//N*(-2)*(L*N)
     R.add(N);//R=L-N*(2)*(L*N)
     V.normalise();
     double a=Math.abs(Ks*Math.pow(V.dotProduc(R),shininess));
		
     int blue=(int)(lightIntensity.getBlue()*a);
     int red=(int)(lightIntensity.getRed()*a);
     int green=(int)(lightIntensity.getGreen()*a);
     
     if(blue>255)
         blue=255;
     if(red>255)
         red=255;
     if(green>255)
         green=255;            
     return new Color(red,green,blue);       
 }


private Color calcDiffusiveComp(double kd, Vector normal,Vector l, Color lightIntensity) 
    {
	        normal.normalise();//
	        L.normalise();
	        double k=Math.abs(Kd*normal.dotProduc(L));
	        int R=(int)(lightIntensity.getRed()*k);
	        if(R>255)
	            R=255;
	        int G=(int)(lightIntensity.getGreen()*k);
	        if(G>255)
	            G=255;
	        int B=(int)(lightIntensity.getBlue()*k);
	        if(B>255)
	            B=255;
	        return new Color(R,G,B);
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

}
public void writeToImage()
	{
		ImageWriter.writeToimage();
	}
