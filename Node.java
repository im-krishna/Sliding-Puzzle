import java.io.*;
import java.util.*;

class Node implements Comparable<Node>
{
    int mat[][];
    Node parent;
    int x,y;
    double code;
    int moves,dist;
    
    Node()
    {
        mat = null;
        x = 0;
        y = 0;
        parent = null;
    }
    
    Node(int arr[][], int a, int b)
    {
        mat = arr;
        x = a;
        y = b;
        moves = 0;
        parent = null;
    }

    public int compareTo(Node a)
    {
        return (this.dist+this.moves)-(a.dist+a.moves);
    }

    Node input() throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String inp[];
        System.out.println("Enter N:");
        int n = Integer.parseInt(in.readLine());
        int x=0,y=0;
        System.out.println("Enter initial matrix:");
        int mat[][] = new int[n][n];
        for (int i=0; i<n; i++)
        {
            inp = in.readLine().split(" ");
            for (int j=0; j<n; j++)
            {
                mat[i][j] = Integer.parseInt(inp[j]);
                if (mat[i][j]==0)
                {
                    x = i;
                    y = j;
                }
            }
        }
        return (new Node(mat, x, y));
    }
    
    double update(int a, int b)
    {
        double sum = this.code;
        int n = this.mat.length;
        sum += this.mat[this.x+a][this.y+b] * Math.pow(n*n, this.x*n + this.y);
        sum -= this.mat[this.x+a][this.y+b] * Math.pow(n*n, (this.x+a)*n + (this.y+b));
        return sum;
    }

    Node newNode(int a, int b)
    {
        int n = this.mat.length;
        int arr[][] = new int[n][n];
    
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                arr[i][j] = this.mat[i][j];
                
        int nx,ny;
        nx = this.x+a;
        ny = this.y+b;
    
        arr[this.x][this.y] = arr[nx][ny];
        arr[nx][ny] = 0;
        
        Node node = new Node(arr,nx,ny);
        node.moves = this.moves + 1;
        node.code = this.update(a,b);
        node.parent = this;
    
        return node;
    }
    
    void printPath()
    {
        if (this == null)
            return;
        if(this.parent!=null)
        this.parent.printPath();
        this.print();
        System.out.println();
    }
    
    void print()
    {
        int n = this.mat.length;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
                System.out.print(this.mat[i][j]+" ");
            System.out.println();
        }
    }
}