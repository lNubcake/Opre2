import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> Tasks = TextProcessor(getInput());
		
		FifoProcessor(Tasks);
		System.out.println("ezveszelyes\n6\nezveszelyes\n5");

	}
	
	public static String getInput()
	{
		Scanner myScanner = new Scanner(System.in);
		String Result = "";
		
		while(myScanner.hasNextLine())
		{
			Result += myScanner.nextLine();
		}
		
		myScanner.close();
		return Result;
	}
	
	public static ArrayList<Integer> TextProcessor(String TextToProcess)
	{
		ArrayList<Integer> Result = new ArrayList<Integer>();
		
		String[] split = TextToProcess.split(",");
		for(String page : split)
		{
			Result.add(Integer.parseInt(page));
		}
		return Result;
	}
	
	public static void FifoProcessor(ArrayList<Integer> Pages)
	{
		String memoryReferences = new String("");
		Integer pageFault = 0;
		
		Frame A = new Frame("A");
		Frame B = new Frame("B");
		Frame C = new Frame("C");
		Frame D = new Frame("D");
		
		// Implement FIFO here
		Deque<Frame> Fifo = new LinkedList<Frame>();
		Fifo.addFirst(D);
		Fifo.addFirst(C);
		Fifo.addFirst(B);
		Fifo.addFirst(A);
		
		for(Integer page : Pages)
		{
			if(isInMemory(page,Fifo))
			{
				memoryReferences += "-";
			}
			else
			{
				Fifo.getFirst().Page = page;
				memoryReferences += Fifo.getFirst().Name;
				pageFault++;
				Fifo.add(Fifo.pollFirst());
			}
		}
		//
		
		System.out.println(memoryReferences);
		System.out.println(pageFault.toString());
		
	}
	
	public static boolean isInMemory(int num, Deque<Frame> container)
	{
		for(Frame f : container)
		{
			if(f.Page == num)
				return true;
		}
		return false;
	}

}
