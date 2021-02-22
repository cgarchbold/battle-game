package utilities;

import java.awt.AWTError;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.print.DocFlavor.URL;

import board.Square;


public class ImageObject {
		public static final int NORTH=0, NORTH_EAST=45, EAST=90, SOUTH_EAST=135, SOUTH=180, SOUTH_WEST=225, WEST=270, NORTH_WEST=315;
		public static final int[] directions = {NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST};
		public static final String[] directionNames={"NORTH", "NORTH EAST", "EAST", "SOUTH EAST", "SOUTH", "SOUTH WEST", "WEST", "NORTH WEST"};
		
		protected Image pic;
		private static final Color tint = new Color(5,5,5);
		private int direction;
		private static Image defaultImg;
		
		public ImageObject(boolean toTint){
			//use this defulat image if you can't find the correct image
			if(defaultImg==null)//only load it once
				defaultImg = Toolkit.getDefaultToolkit().getImage(  getClass().getResource("./src/Toad.png") );
			//pic = loadImage("./src/"+this.getFileName(), toTint);
			//this.fileName() is something like --  soldiers.Warrior
		
			pic = loadImage(this.getFileName(), toTint);
		}
		
		public int getDirection(){return direction;}
		public void setDirection(int d){direction = d%360;}
		
		public Image getPic(){return pic;}
		
		public void draw(Graphics g){
			//real images are facing to the right
			//BUT, I need 0 degrees to be north	
			
			int adDir = direction -90;
					
			if(pic!=null){
				if(direction <= 180){
					((Graphics2D)g).rotate( adDir*Math.PI/180, Square.WIDTH/2, Square.HEIGHT/2);
					g.drawImage(pic, 0, 0, Square.WIDTH, Square.HEIGHT, null);
					((Graphics2D)g).rotate( -adDir*Math.PI/180, Square.WIDTH/2, Square.HEIGHT/2);
				}
				else{//if he is west, nw , or sw -- don't want him standing on his head!!  need to mirror the image and rotate it -(360-direction)
					((Graphics2D)g).rotate( -(360-direction-90)*Math.PI/180, Square.WIDTH/2, Square.HEIGHT/2);
					g.drawImage(pic, Square.WIDTH, 0, -1*Square.WIDTH, Square.HEIGHT, null);
					((Graphics2D)g).rotate( (360-direction-90)*Math.PI/180, Square.WIDTH/2, Square.HEIGHT/2);				
				}
			}
		}
		
		public Image loadImage( String imgFile, boolean tinted ){
			Image i=null;
			try{		
			    //System.out.println("/images/"+imgFile+" URL : "+getClass().getResource("/images/"+imgFile));
				//System.out.println("?"+getClass().getResource("src.images"));//+imgFile));
				i = Toolkit.getDefaultToolkit().getImage(  getClass().getResource("/images/"+imgFile) );				
				MediaTracker mt = new MediaTracker( new Component(){});
				mt.addImage(i, 0);
				mt.waitForAll();				
				
			}
			catch(AWTError np){ 
				System.out.println("Can't find image");
				np.printStackTrace();	
			}
			catch(Exception ex){
				System.out.println("HELLO?");
				ex.printStackTrace();	
			}
			if(i==null)
				i=defaultImg;
			//return i;
			if(tinted)
				return tintImage(i);
			else
				return i;
		}

		public BufferedImage tintImage(Image original){
			//http://stackoverflow.com/questions/14225518/tinting-image-in-java-improvement
			int r=tint.getRed();
			int g=tint.getGreen();
			int b=tint.getBlue();
			
		    int width = original.getWidth(null);
		    int height = original.getHeight(null);
		    BufferedImage untinted = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);//BufferedImage.TRANSLUCENT);
		    Graphics2D graphics = (Graphics2D) untinted.getGraphics();
		    graphics.drawImage(original, 0, 0, width, height, null);	   
		    Color c = new Color(r,g,b,128);
		    graphics.setColor(c);
		    Color n = new Color(0,0,0,0);
		    BufferedImage tint = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);//BufferedImage.TRANSLUCENT);
		    for(int i = 0 ; i < width ; i++){
		        for(int j = 0 ; j < height ; j++){
		        	if( alpha(untinted.getRGB(i,j))!=0 ){//untinted.getRGB(i, j) != n.getRGB()){
		        		tint.setRGB(i, j, darken(untinted.getRGB(i,j)) );//combine(alpha,red,0,0));
		            }
		        }
		    }
		    graphics.drawImage(tint, 0, 0, null);
		    graphics.dispose();
		    //return untinted;
		    return tint;
		}
		
		public int darken( int pixel){
			int a = alpha(pixel);
			int r = howRed(pixel);
			int g = howGreen(pixel);
			int b = howBlue(pixel);
			return combine( a, r-25, g-75, b-75);
		}
		public int alpha(int pixel){return (pixel&0xFF000000)>>24;}
		public int howRed(int pixel){return (pixel&0xFF0000)>>16;}
		public int howGreen(int pixel){return (pixel&0x00FF00)>>8;}
		public int howBlue(int pixel){ return pixel&0xFF; }
		private int combine(int a, int r, int g, int b){
			r=Math.max(0, r);
			g=Math.max(0, g);
			b=Math.max(0, b);
			a=a<<24;
			r=r<<16;
			g=g<<8;
			return (a|r|g|b);
		}
		
		public String getFileName(){
			String nm = this.getClass().toString(); //this gives "class soldiers.Knight" for example
			
			try{
				String withoutTheClass = nm.substring(nm.indexOf(" ")+1); //now soldiers.Knight
				String withoutThePackage = withoutTheClass.substring(withoutTheClass.indexOf(".")+1); //now just Knight
				//System.out.println("returning: "+withoutThePackage);
				return withoutThePackage+".png";
				
			}
			catch(Exception ex){//knight will be the default
				return "images/Knight.png";
			}
		}
		
}
