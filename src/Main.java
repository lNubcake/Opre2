import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> Tasks = TextProcessor(getInput());

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
		// TODO implement this
		ArrayList<Integer> Result = new ArrayList<Integer>();
		
		String[] split = TextToProcess.split(",");
		for(String page : split)
		{
			Result.add(Integer.parseInt(page));
		}
		return Result;
	}

}
