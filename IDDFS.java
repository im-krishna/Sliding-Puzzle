import java.io.*;
import java.util.*;
import java.text.*;

public class IDDFS
{
    int n;
    int finalMatrix[][];
    double finalcode;
    int printu;
    
    IDDFS(int n,int printu)
    {
        this.n = n;
        finalMatrix = new int[n][n];
        for(int i=0;i<n;i++)
            for(int j=0; j<n; j++)
                finalMatrix[i][j] = i*n + j + 1;
        finalMatrix[n-1][n-1] = 0;
        finalcode = hash(finalMatrix);
        this.printu=printu;
    }
    
    double hash(int arr[][])
    {
        double sum=0,c=1;
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                sum += arr[i][j] * c;
                c *= n*n;
            }
        }
        return sum;
    }
    boolean inLimit(int x, int y)
    {
        if (x>=0 && x<n && y>=0 && y<n)
            return true;
        return false;
    }
    
    int row[] = {0,0,1,-1};
    int col[] = {1,-1,0,0};

    boolean IDDFSearch(Node root,int depth,int max_dep,HashSet<Double> set)
    {
        if(finalcode==root.code)
        {
            if(printu==1)
            {
                System.out.println("Moves is "+root.moves+"\n");
                root.printPath();
            }
            return true;
        }
        if(depth==max_dep)
        {
            return false;
        }
        set.add(root.code);
        for(int i=0;i<4;i++)
        {
            if(inLimit(root.x+row[i],root.y+col[i]))
            {
                Node x=root.newNode(row[i],col[i]);
                if(!set.contains(x.code))
                {
                    if(IDDFSearch(x,depth+1,max_dep,set))
                        return true;
                }
            }
        }
        set.remove(root.code);
        return false;
    }
    
    void search(Node slide)
    {
        long start = System.currentTimeMillis();
        int t=0;
        HashSet<Double> set=new HashSet<Double>();
        slide.code=this.hash(slide.mat);//code set hai moves is zero wo bhi set hai
        while(!this.IDDFSearch(slide,0,t,set))
        {
            set.clear();
            t++;
        }
        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time for IDDFS is " + formatter.format((end - start) / 1000d) + " seconds\n");
    }

    public static void main(String args[]) throws IOException
    {
        Node slide = new Node();
        slide = slide.input();

        Check ob=new Check();
        if(!ob.check(slide.mat,slide.x))
        {
            System.out.println("Solution Not Possible");
            return;
        }
        else
        {
            System.out.println("Solution is Possible");
        }

        IDDFS obj = new IDDFS(slide.mat.length,1);
        obj.search(slide);
        //code start runninf from here
    }
}