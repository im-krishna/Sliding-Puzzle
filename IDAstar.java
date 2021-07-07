import java.io.*;
import java.util.*;
import java.text.*;

public class IDAstar
{
    int n;
    int finalMatrix[][];
    double finalcode;//code of final matrix for checking
    int pos[][];//actual position of numbers in final matirx
    int bound;
    int min=Integer.MAX_VALUE;
    int printu;

    IDAstar(int n,int printu)
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
    
    int manhatten(int matrix[][])
    {
        int res=0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                int num=matrix[i][j];
                if(num==0)
                    continue;
                res+=Math.abs(i-pos[num][0])+Math.abs(j-pos[num][1]);
            }
        }
        return res;
    }
    
    boolean inLimit(int x, int y)
    {
        if (x>=0 && x<n && y>=0 && y<n)
            return true;
        return false;
    }
    
    int row[] = {0,0,1,-1};
    int col[] = {1,-1,0,0};

    boolean IDAstarSearch(Node root,int depth,HashSet<Double> set)
    {
        if(root.code==finalcode)
        {
            if(printu==1)//only if printu is one we will be printing
            {
                System.out.println("Moves is "+root.moves+"\n");
                root.printPath();
            }
            return true;
        }
        int f=root.moves+root.dist;//anytine f becomes greater than bound we don't want to explore more
        if(f>bound)
        {
            if(f<min)
                min=f;
            return false;
        }

        if(depth==bound)
        {
            return false;
        }

        set.add(root.code);//marking visited

        PriorityQueue<Node> pq=new PriorityQueue<Node>();
        for(int i=0;i<4;i++)
        {
            if(inLimit(root.x+row[i],root.y+col[i]))
            {
                Node x=root.newNode(row[i],col[i]);//moves ar code set hai isme toh bcha kya? mahnatten

                if(!set.contains(x.code))
                {
                    x.dist=manhatten(x.mat);
                    pq.add(x);
                }
            }
        }
        while(!pq.isEmpty())
        {
            if(IDAstarSearch(pq.poll(),depth+1,set))
                return true;
        }
        set.remove(root.code);
        return false;
    }

    public void Ida(Node root)
    {
        //IDAstar search
        HashSet<Double> set=new HashSet<Double>();
        root.dist=manhatten(root.mat);//manhatten distance
        bound=root.dist;//initial bound (moves+distance)//global variable

        while(!IDAstarSearch(root,0,set))
        {
            bound=min;
            min=Integer.MAX_VALUE;
            set.clear();
        }
    }

    public void fillpos()
    {
        pos=new int[n*n][2];
        int k=1;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(k==n*n)
                    continue;
                pos[k][0]=i;
                pos[k][1]=j;
                k++;
            }
        }
    }

    void search(Node slide)
    {
        fillpos();
        long start = System.currentTimeMillis();
        slide.code=this.hash(slide.mat);//code set hai moves is zero wo bhi set hai
        this.Ida(slide);
        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time for IDAstar is " + formatter.format((end - start) / 1000d) + " seconds\n");
    }

    public static void main(String args[]) throws IOException
    {

        Node slide = new Node();
        slide = slide.input();//isme x y aar matrix set hai

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

        IDAstar obj = new IDAstar(slide.mat.length,1);//finalcode set hua
        obj.search(slide);
    }
}