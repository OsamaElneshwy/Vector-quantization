package v;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class main 
{
	public static Vector<image> v =new Vector<image>();
	public static Vector<image> vDeComp =new Vector<image>();
	public static Vector<node> aV =new Vector<node>();
	public static Vector<Integer> comp = new Vector<Integer>();
	
	
	public static void main(String[] args) throws IOException 
	{
		
		node root = new node();
		image i = new image();
		Scanner in = new Scanner(System.in);
		System.out.print("Put your Photo Path:");
		String s=in.nextLine();
		i.load(s);
		System.out.print("Put your Block Width:");
		i.widthOfBox=in.nextInt();
		System.out.print("Put your Block heigth:");
		i.heigthOfBox=in.nextInt();
		i.convertImageToMatrix();
		i.spiltImage();
		
		
		root = root.root(root);
		root.spiltRoot(root);
		root.arrange(root);

	
		root.Child(root.left);
		root.Child(root.right);
		root.spiltRoot(root.left);
		root.spiltRoot(root.right);
		root.arrangeChild(root.left);
		root.arrangeChild(root.right);
			
		
		
		
		root.Child(root.left.left);
		root.Child(root.left.right);
		root.Child(root.right.left);
		root.Child(root.right.right);
		
		root.spiltRoot(root.left.left);
		root.spiltRoot(root.left.right);
		root.spiltRoot(root.right.left);
		root.spiltRoot(root.right.right);
		
		root.arrangeChild(root.left.left);
		root.arrangeChild(root.left.right);
		root.arrangeChild(root.right.left);
		root.arrangeChild(root.right.right);
		
		aV.add(root.left.left.left);
		aV.add(root.left.left.right);
		aV.add(root.left.right.left);
		aV.add(root.left.right.right);
		
		aV.add(root.right.right.right);
		aV.add(root.right.right.left);
		aV.add(root.right.left.right);
		aV.add(root.right.left.left);
		
	    root.Comp(aV, v, comp);
	    root.Decompression(aV, vDeComp, comp);
	    
	    i.reConstruct(vDeComp);
	    System.out.println("DONE");

		
		
	}

}
