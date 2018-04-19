import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		TextProcessor(getInput());

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
	
	public static void/*??*/ TextProcessor(String TextToProcess)
	{
		// TODO implement this
	}

}
