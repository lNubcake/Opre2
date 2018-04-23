import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> Tasks = TextProcessor(getInput());
		
		FifoProcessor(Tasks);
		LRUProcessor(Tasks);
		LRUPageLockingProcessor(Tasks);
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
		
		Frame A = new Frame("A",0);
		Frame B = new Frame("B",0);
		Frame C = new Frame("C",0);
		Frame D = new Frame("D",0);
		
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
	
	public static void LRUProcessor(ArrayList<Integer> Pages)
	{
		String memoryReferences = new String("");
		Integer pageFault = 0;
		
		Frame A = new Frame("A",4);
		Frame B = new Frame("B",3);
		Frame C = new Frame("C",2);
		Frame D = new Frame("D",1);
		
		ArrayList<Frame> LRU = new ArrayList<Frame>();
		LRU.add(A);
		LRU.add(B);
		LRU.add(C);
		LRU.add(D);
		
		for(Integer page: Pages)
		{
		
			
			if(isInMemory(page,LRU))
			{
				memoryReferences += "-";
			}
			else
			{
				LRU.get(0).Page = page;
				memoryReferences += LRU.get(0).Name;
				pageFault++;
				LRU.get(0).lastUsage = 1;
				for(int i = 1; i < LRU.size(); i++)
				{
					LRU.get(i).lastUsage++;
				}
			}
			
			Collections.sort(LRU, new Comparator<Frame>() {
				@Override
				public int compare(Frame lhs, Frame rhs){
					return lhs.lastUsage > rhs.lastUsage ? -1 : (lhs.lastUsage < rhs.lastUsage ? 1 : 0);
				}
			});
		}
		
		System.out.println(memoryReferences);
		System.out.println(pageFault.toString());
	}
	
	public static void LRUPageLockingProcessor(ArrayList<Integer> Pages)
	{

		String memoryReferences = new String("");
		Integer pageFault = 0;
		
		Frame A = new Frame("A",5);
		Frame B = new Frame("B",4);
		Frame C = new Frame("C",3);
		Frame D = new Frame("D",2);
		
		ArrayList<Frame> LRU = new ArrayList<Frame>();
		LRU.add(A);
		LRU.add(B);
		LRU.add(C);
		LRU.add(D);
		
		for(Integer page: Pages)
		{	
			for(Frame s : LRU)
			{
				if(s.lastUsage < 5)
				{
					s.Frozen = true;
				}
			}
			
			if(isInMemory(page,LRU) )
			{
				memoryReferences += "-";
			}
			else if(LRU.get(0).lastUsage < 5)
			{
				memoryReferences += "*";
				pageFault++;
				for(Frame f : LRU)
				{
					f.lastUsage++;
				}
			}
			else if(LRU.get(0).lastUsage >= 5)
			{
				LRU.get(0).Page = page;
				memoryReferences += LRU.get(0).Name;
				pageFault++;
				LRU.get(0).lastUsage = 1;
				for(int i = 1; i < LRU.size(); i++)
				{
					LRU.get(i).lastUsage++;
				}
			}

			Collections.sort(LRU, new Comparator<Frame>() {
				@Override
				public int compare(Frame lhs, Frame rhs){
					return lhs.lastUsage > rhs.lastUsage ? -1 : (lhs.lastUsage < rhs.lastUsage ? 1 : 0);
				}
			});
			
		}
		
		System.out.println(memoryReferences);
		System.out.println(pageFault.toString());
	}
	
	public static boolean isInMemory(int num, ArrayList<Frame> container)
	{
		boolean Result = false;
		for(Frame f : container)
		{
			if(f.Page == num)
			{
				f.lastUsage = 0;
				Result = true;
			}
			f.lastUsage++;
		}
		return Result;
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
