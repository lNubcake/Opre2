
public class Frame 
{
	String Name;
	Integer Page;
	Integer lastUsage;
	boolean Frozen;
	
	Frame(String NameToSet, Integer us)
	{
		Name = NameToSet;
		Page = -1;
		lastUsage = us;
		Frozen = false;
	}
}
