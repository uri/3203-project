import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.events.Characters;

class Controller {

	private static ArrayList<Character> singleCharArgs = new ArrayList<Character>();
	private static ArrayList<String> singleStringArgs = new ArrayList<String>();
	static {
		// Here is an example
//		singleCharArgs.add('t');
//		singleStringArgs.add("test-suite");
	}
	
	HashMap<Character, String> charArgs;
	HashMap<String, String> stringArgs;

	public Controller() {
		charArgs = new HashMap<Character, String>();
		stringArgs = new HashMap<String, String>();
	}

	public Controller(String[] args) {
		this();

		// Parse the string
		
		for (int i = 0; i < args.length; i++) {
			// Char param
			String s = args[i];
			
			if (s.charAt(0) == '-' && s.length() > 1) {
				
				char currChar = s.charAt(1);
				if (!singleCharArgs.contains(currChar)) {
					charArgs.put(s.charAt(1), args[i+ 1]);
					i++;
				} else {
					charArgs.put(s.charAt(1), "");
				}

			} else if (s.length() > 2 && s.substring(0, 2).equals("--")) {
				String currStr = s.substring(2);
				if (!singleStringArgs.contains(currStr)) {
					stringArgs.put(currStr, args[i + 1]);
					i++;
				} else {
					stringArgs.put(currStr, "");
				}
			}
		}
		
		// 
		execute();
	}
	
	private void execute() {
		
		// Char args
		
		// -n number of nodes to generate
		if (charArgs.containsKey('n')) {
			// TODO: Add try blcok
			int value = Integer.parseInt(charArgs.get('n'));
			
			// TODO: Do something with this value	
		}
	}

}