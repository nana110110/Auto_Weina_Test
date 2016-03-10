package Core;

public class Global {

	public static String logFolderPath;
	public static final String workspaceLocation = System.getProperty("user.dir");
	
	// Drivers
	public static final String ieDriverPath = "src\\main\\resources\\Drivers\\IEDriverServer.exe";
	public static final String chromeDriverPath = "src\\main\\resources\\Drivers\\chromedriver.exe";
	
	// Alphabetic | Numbers
	public static final String englishAlphabet = "abcdefghijklmnopqrstuvwxyz";
	public static final String englishAlphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String digits = "1234567890";
	public static final String englishAlphabetWithDigits = englishAlphabet+digits;
	
	//Email configurations
	public static final String localSMTPIpAddress = "10.0.0.25";
	
	
	
}
