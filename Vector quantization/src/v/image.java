package v;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class image extends main 
{
	public static int widthOfBox=0 , heigthOfBox=0;
	public BufferedImage img;
	public BufferedImage img1;
	public static BufferedImage reConst;
	public int matrix[][];
	public int matrix1[][];
	
//**********************************************************	
	
	public void load(String s) throws IOException
	{
		img = ImageIO.read(new File(s));
		matrix=new int [img.getWidth()][img.getHeight()];
	}
	
	public void convertImageToMatrix()
	{ 
		for(int i=0;i<img.getWidth();i++)
		{
			for(int j=0;j<img.getHeight();j++)
			{
				int rgb=img.getRGB(i,j);
                int g = (rgb >> 8) & 0xff;
                matrix[i][j]=g;
			}
		}
	}
	
	public void spiltImage() 
	{
		for(int i=0;i<img.getWidth();i+=widthOfBox)
		{
			for(int j=0;j<img.getHeight();j+=heigthOfBox)
			{
				image e = new image();
				e.matrix1= new int [widthOfBox][heigthOfBox];
				for(int x=0;x<widthOfBox;x++)
				{	
					for(int y=0;y<heigthOfBox;y++)
					{
						e.matrix1[x][y]=matrix[x+i][y+j];
					}
				}
				v.add(e);
			}
		}
	}
	
	public void reConstruct( Vector<image> vDeComp ) throws IOException
    {  
		reConst = new BufferedImage(img.getWidth(),img.getHeight(), img.getType());
		for(int p = 0 ; p < vDeComp.size() ; p++)
		{
			for(int i=0;i<img.getWidth();i+=widthOfBox)
			{
				for(int j=0;j<img.getHeight();j+=heigthOfBox)
				{	
        			for(int x=0;x<widthOfBox;x++)
        			{	
        				for(int y=0;y<heigthOfBox;y++)
        				{
        					reConst.setRGB(x+i,y+j,vDeComp.get(p).matrix1[x][y]<<8);
        				}
        			}
        			p++;
				}
			}
		}
		ImageIO.write(reConst, "png", new File("f:/"+"ReConstructed"+".png"));
	}
	
	public void print() throws IOException
	{
		img1 = new BufferedImage(img.getWidth(),img.getHeight(), img.getType());
		for(int i=0;i<vDeComp.size();i++)
		{
			
			
			for(int x=0;x<widthOfBox;x++)
			{
				for(int y=0;y<heigthOfBox;y++)
				{
					img1.setRGB(x,y,vDeComp.get(i).matrix1[x][y]<<8);
				}	
			}
			ImageIO.write(img1, "png", new File("f:/os"+i+".png"));
			System.out.println("Done");
		}
	}
	
}

