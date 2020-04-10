/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author menaged
 */

    package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO; //הוספתי קטור ברירת מחדל והשוואה

public class ImageWriter {

	private int _imageWidth;
	private int _imageHeight;
        private int _Ny;  private int _Nx;
	
	final String PROJECT_PATH = System.getProperty("user.dir");
	private String _imageName;
	
        private BufferedImage _image;
	
	
	
	// ***************** Constructors ********************** // 
		public ImageWriter(){   // אני הוספתי ברירת מחדל
		
		_imageWidth = 0;
		_imageHeight = 0;
		_Nx = 0;
		_Ny = 0;
		_imageName = "image";
		 
		_image = new BufferedImage(0,0,0);
	}
	public ImageWriter(String imageName, int width, int height, int Ny, int Nx){
		
		_Nx = Nx;
		_Ny = Ny;
		
		_imageWidth = width;
		_imageHeight = height;
		
		_imageName = imageName;
		
		_image = new BufferedImage(
				_imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);;
	}

	public ImageWriter (ImageWriter imageWriter){
		_Nx = imageWriter._Nx;
		_Ny = imageWriter._Ny;
		
		_imageWidth = imageWriter.getWidth();
		_imageHeight = imageWriter.getHeight();
		
		_imageName = imageWriter._imageName;
		
		_image = new BufferedImage(
				_imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);;
	}
	
	// ***************** Getters/Setters ********************** //
	
	public int getWidth()  { return _imageWidth;  }
	public int getHeight() { return _imageHeight; }

	public int getNy() { return _Ny; }
	public int getNx() { return _Nx; }
        public String getName(){return _imageName;}
        public BufferedImage getBuffer(){return _image;}
                
	public void setNy(int _Ny) { this._Ny = _Ny; }
	public void setNx(int _Nx) { this._Nx = _Nx; }
	
	// ***************** Operations ******************** // 
	
	public void writeToimage(){
		
		//File ouFile = new File(PROJECT_PATH + "/" + _imageName + ".jpg");
                File ouFile = new File( _imageName + ".jpg");

		try {
			ImageIO.write(_image, "jpg", ouFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/// לשים לב טוב טוב--- הפרמטרים של הצבע בסדר הפוך!!! זה צריך להיות ככה?
	public void writePixel(int xIndex, int yIndex, int r, int g, int b){
		
		int rgb = new Color(r, g, b).getRGB();
		_image.setRGB(xIndex, yIndex, rgb);
		
	}
	
	public void writePixel(int xIndex, int yIndex, int[] rgbArray){
		
		int rgb = new Color(rgbArray[0], rgbArray[1], rgbArray[2]).getRGB();
		_image.setRGB(xIndex, yIndex, rgb);
		
	}
	
	public void writePixel(int xIndex, int yIndex, Color color){
		
		_image.setRGB(xIndex, yIndex, color.getRGB());
		
	}
        
        
	
}
    

