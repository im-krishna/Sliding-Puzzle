import java.io.*;
public class solver
{
	public static void main(String args[])throws IOException
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

        IDAstar obj1 = new IDAstar(slide.mat.length,1);
        obj1.search(slide);
        Astar obj2 = new Astar(slide.mat.length,0);
        obj2.search(slide);
        IDDFS obj3 = new IDDFS(slide.mat.length,0);
        obj3.search(slide);
        BFS obj4 = new BFS(slide.mat.length,0);
        obj4.search(slide);
	}
}