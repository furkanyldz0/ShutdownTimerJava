import java.io.IOException;import javax.swing.JOptionPane;

public class Shutdown {
	
	static String OPERATING_SYSTEM= System.getProperty("os.name");
	
	
	public static void shutdown() throws RuntimeException, IOException {
		String shutdownCommand;
		
		if(OPERATING_SYSTEM.equals("Linux") || OPERATING_SYSTEM.equals("Mac OS X")) 
			shutdownCommand = "shutdown -h now";
		
		else if(OPERATING_SYSTEM.contains("Windows")) //işletim sistemleri windows x şeklinde gözüküyor
			shutdownCommand = "shutdown.exe -s -t 0";
		
		else
			throw new RuntimeException("Desteklenmeyen işletim sistemi!");
		
		
		Runtime.getRuntime().exec(shutdownCommand);
		System.exit(0);
	}
	
	public static void restart() throws RuntimeException, IOException  {
		String restartCommand;
		
		if(OPERATING_SYSTEM.equals("Linux") || OPERATING_SYSTEM.equals("Mac OS X")) 
			restartCommand = "shutdown -r now";
		
		else if(OPERATING_SYSTEM.contains("Windows")) //işletim sistemleri windows x şeklinde gözüküyor
			restartCommand = "shutdown.exe -r -t 0";
		
		else
			throw new RuntimeException("Desteklenmeyen işletim sistemi!");
		
		Runtime.getRuntime().exec(restartCommand);
		System.exit(0);
	}
	
	public static void sleep() throws RuntimeException, IOException  {
		
		Runtime.getRuntime().exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
		System.exit(0);
	}

}
