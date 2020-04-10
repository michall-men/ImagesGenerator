/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *
 * @author menaged
 */
public interface LightSource {
	
public Color getIntensity(Point3D p); // עוצמת האור בנקודה 
public Vector getL(Point3D p);        // L  וקטור הקרן 


}
