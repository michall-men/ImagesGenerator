/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import java.awt.Color;
import java.util.List;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *
 * @author menaged
 */
public abstract class geometries {
    
   private Material material;
   private Color emmission;

     	public geometries(Material m, Color e) { //parameter construct
		//super();
            material = m;
		emmission = e;
        }
	public geometries(geometries geo) { //copy constructor
		//super();
		material = geo.getMaterial();
		emmission = geo.getEmLight();
	}
	
	public geometries() { //default constructor
		//super();
		material = new Material();
		emmission = Color.black;
	}
	public Material getMaterial() {
		return new Material(material);
	}
	
	public void setMaterial(Material material) {
		material = new Material( material );
	}
	public Color getEmLight() {
		return emmission;
	}
	public void setEmLight(Color em) {
		emmission = em;
	}
	@Override
	public String toString() {
		return "geometries [material=" + material + ", emmission=" + emmission + "]";
	}

  abstract  public List<Point3D> findIntersections(Ray r);
  abstract  public Vector getNormal(Point3D point);
}
