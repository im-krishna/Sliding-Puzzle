import java.io.*;
import java.util.*;
import java.text.*;

public class Astar
{
    int n;
    int finalMatrix[][];
    double finalcode;
    int printu; 

    Astar(int n,int printu)
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
    
    int misplacedTiles(int mat[][])
    {
        int c=0;
        for (int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(mat[i][j]>0 && mat[i][j] != this.finalMatrix[i][j])
                    c++;
            }
        }
        return c;
    }
    
    boolean inLimit(int x, int y)
    {
        if (x>=0 && x<n && y>=0 && y<n)
            return true;
        return false;
    }
    
    void AstarSearch(Node root)
    {
        int row[] = {0,0,1,-1};
        int col[] = {1,-1,0,0};

        HashSet<Double> set= new HashSet<Double>();
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        root.dist = this.misplacedTiles(root.mat);
        pq.add(root);
        root.code = hash(root.mat);
        set.add(root.code);
    
        //A* algorithm
        while(!pq.isEmpty())
        {
            Node node = pq.poll();
            if (node.dist == 0)
            {
                if(printu==1)
                {
                    System.out.println("moves is "+node.moves+"\n");
                    node.printPath();
                }
                return;
            }   
            for (int i=0; i<4; i++)
            {
                if (inLimit(node.x+row[i],node.y+col[i]))
                {
                    Node newnode = node.newNode(row[i],col[i]);
                    if (set.contains(newnode.code))
                        continue;
                    set.add(newnode.code);
                    newnode.dist = this.misplacedTiles(newnode.mat);
                    pq.add(newnode);
                }
            }
        }
    }

    void search(Node slide)
    {
        long start = System.currentTimeMillis();
        this.AstarSearch(slide);
        long end = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time for Astar is " + formatter.format((end - start) / 1000d) + " seconds\n");
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

        Astar obj = new Astar(slide.mat.length,1);
        obj.search(slide);
    }
}
