/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import elements.AmbientLight;
import elements.Camera;
import elements.Light;
import elements.LightSource;
import geometries.geometries;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author menaged
 */
public class Scene {

    private String name;
    private Color background;
    private List<geometries> gmtrList;   //רשימת גיאומטריות שבונות סצטנה
    private List<LightSource> lightList;       //המאורות שבסצטנה
    private Camera scenCamera;           //המצלמה
    private double cameraDistance;       // מרחק מהצלמה
    private AmbientLight ambntLight;     // לכל הסצטנה
    
    //ctor
    public Scene(){              //deafult constractor
        name="no name";
        background=new Color(0,0,0);
        gmtrList=new ArrayList<>(); 
        lightList=new ArrayList<>(); 
        scenCamera=new Camera();
        cameraDistance=100;
        ambntLight=new AmbientLight();
    }
    public Scene(Scene s){              //copy constractor
        name=s.getName();
        background=s.getBack();
        gmtrList=s.getGmtrList(); 
        scenCamera=s.getCamera();
        cameraDistance=s.getCamDistance();
    }
    public Scene(String name1,Color back1,Camera camer1, double distns)
    {
        name=name1; background=new Color(back1.getBlue(),back1.getGreen(),back1.getRed());
        scenCamera=new Camera(camer1); cameraDistance=distns;
    }
    
    //Set/get לכל השדות
    public String getName(){String n=name; return n;}
    public Color getBack(){Color c=new Color(background.getBlue(),background.getGreen(),background.getRed()); return c;}
    public  List<geometries> getGmtrList(){return gmtrList; }
    public Camera getCamera(){Camera c=new Camera(scenCamera); return c;}
    public double getCamDistance(){return cameraDistance;}  //אם צריך אז להוסיף דאבל נוסף שאותו מחזירים
    public List<LightSource> getLightList(){return lightList;}
    public AmbientLight getAmbntLight(){return ambntLight;}
    
    public void setName(String n){name=n;}
    public void setBack(Color c){background=new Color(c.getBlue(),c.getGreen(),c.getRed());}
    public void setGmtrList(List<geometries> l){gmtrList=l; }
    public void setCamera(Camera c){scenCamera=new Camera(c); }
    public void setCamDistance(double d){cameraDistance=d;}  //אם צריך אז להוסיף דאבל נוסף שאותו מחזירים
    public void setLightList(List<LightSource> l){lightList=l;}
    public void setAmbntLight(AmbientLight k){ambntLight=new AmbientLight(k);}
    public void setAmbntLight(Color c,double k){ambntLight=new AmbientLight(c,k);}
   
    public boolean equals(Scene s)
    {return (name==s.getName()&&background.getRGB()==s.getBack().getRGB()
            &&gmtrList.equals(s.getGmtrList())&&scenCamera.equals(s.getCamera())
            &&cameraDistance==s.getCamDistance()&&ambntLight.equals(s.getAmbntLight()));
    }
    
    public void addGeometry(geometries g) {
    gmtrList.add(g);
    } 
          public void addLight(LightSource g) {
    lightList.add(g);
    } 
       
    public Iterator<geometries> getGeometriesIterator()
    {
        Iterator<geometries> it=gmtrList.iterator();
        return it;
    }
    public Iterator<LightSource> getLightsIterator(){
		return lightList.iterator();
	}
	
 /*   @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.background);
        hash = 47 * hash + Objects.hashCode(this.ambntLight);
        hash = 47 * hash + Objects.hashCode(this.gmtrList);
        hash = 47 * hash + Objects.hashCode(this.scenCamera);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.cameraDistance) ^ (Double.doubleToLongBits(this.cameraDistance) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Scene other = (Scene) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.background, other.background)) {
            return false;
        }
        if (!Objects.equals(this.gmtrList, other.gmtrList)) {
            return false;
        }
        if (!Objects.equals(this.lightList, other.lightList)) {
            return false;
        }
        if (!Objects.equals(this.scenCamera, other.scenCamera)) {
            return false;
        }
        if (!Objects.equals(this.ambntLight, other.ambntLight)) {
            return false;
        }
        return true;
    }*/


}
