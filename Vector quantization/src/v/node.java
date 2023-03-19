package v;

import java.util.Vector;

public class node extends image 
{
	int mat[][];
	Vector<node> vN ;
	node left ,right;
	
//******************************************
	
	public node()
	{  
		mat= new int [widthOfBox][widthOfBox];
		vN=new Vector<node>();
		left=right=null;
	}
	
	public node root(node n)
	{
		n = new node();
		 for(int x=0;x<widthOfBox;x++)
	        {
	            for(int y=0;y<heigthOfBox;y++)
	            {
	                for(int i=0;i<v.size();i++)
	                {
	                	
	                    n.mat[x][y]+=v.get(i).matrix1[x][y];
	                }
	                n.mat[x][y] = n.mat[x][y]/v.size();
	            }
	        }
		 return n;
	}
	
	public void spiltRoot(node n)
    {
		node n1 =new node();
		node n2 =new node();
		for(int i = 0 ; i < widthOfBox ; i++)
		{
			for(int j = 0 ; j < heigthOfBox ; j++)
			{
				n1.mat[i][j]=(n.mat[i][j])+1;
				n2.mat[i][j]=(n.mat[i][j])-1;
			}
		}
		n.left=n1;
		n.right=n2;
    }
	
	public void arrange(node n)
	{
		for(int x=0;x<v.size();x++)
		{
			int diffL=0 , diffR=0;
			node z = new node(); 
			for(int i = 0 ; i < widthOfBox ; i++)
			{
				for(int j = 0 ; j < heigthOfBox ; j++)
				{
					diffL += Math.pow((v.get(x).matrix1[i][j] - n.left.mat[i][j]) , 2);
					diffR += Math.pow((v.get(x).matrix1[i][j] - n.right.mat[i][j]) , 2);
				}
			}
			z.mat=v.get(x).matrix1;
			if( Math.sqrt(diffL) < Math.sqrt(diffR) )
			{
				n.left.vN.add(z);
			}
			else
			{
				n.right.vN.add(z);
			}
		}
	}
	
	public void arrangeChild(node n)
		{
			
			for(int x=0;x<n.vN.size();x++)
			{
				int diffL=0 , diffR=0;
				node z = new node(); 
				for(int i = 0 ; i < widthOfBox ; i++)
				{
					for(int j = 0 ; j < heigthOfBox ; j++)
					{
						diffL += Math.pow((n.vN.get(x).mat[i][j] - n.left.mat[i][j]) , 2);
						diffR += Math.pow((n.vN.get(x).mat[i][j] - n.right.mat[i][j]) , 2);
					}
				}
				z.mat=n.vN.get(x).mat;
				if( Math.sqrt(diffL) < Math.sqrt(diffR) )
				{
					n.left.vN.add(z);
				}
				else
				{
					n.right.vN.add(z);
				}
			}
		
	}
	
	public void Child(node n)
    {
        for(int x=0;x<widthOfBox;x++)
        {
            for(int y=0;y<heigthOfBox;y++)
            {
                n.mat[x][y]=0;
            }
        }

         for(int x=0;x<widthOfBox;x++)
            {
                for(int y=0;y<heigthOfBox;y++)
                {
                    for(int i=0;i<n.vN.size();i++)
                    {
                        n.mat[x][y]+=n.vN.get(i).mat[x][y];
                    }
                    if(n.vN.size() != 0)
                    {
                    	n.mat[x][y] = n.mat[x][y]/n.vN.size();
                    }
                }
            }
    }
	
	public void Comp(Vector<node> aV,Vector<image> v ,Vector<Integer> comp)
    { 
            for(int i = 0 ; i < v.size() ; i++)
            {
                for(int j = 0 ; j < aV.size() ; j++)
                {
                	for(int k = 0 ; k < aV.get(j).vN.size() ; k++)
                	{
                		if(aV.get(j).vN.get(k).mat == v.get(i).matrix1)
	                    {
	                    	comp.add(j);
	                    }
                	}
                }
            }
    }

	public void Decompression(Vector<node> aV, Vector<image> v ,Vector<Integer> comp)
	{
		for(int i = 0 ; i < comp.size() ; i++)
		{
			image x = new image();
			node y = new node();
			y = aV.get(comp.get(i));
			x.matrix1 = y.mat;
			v.add(x);
		}
	}
}
