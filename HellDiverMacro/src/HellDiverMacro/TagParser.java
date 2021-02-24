package HellDiverMacro;


public class TagParser {
	static public String getValue(String tagFile, String tag) {	
		String value = "";
		
		String startTag = "<" + tag + ">";
		String endTag = "</" + tag + ">";
		
		int startIndex = tagFile.indexOf(startTag) + startTag.length();
		int endIndex = tagFile.indexOf(endTag)-1;
		
		value = tagFile.substring(startIndex, endIndex).trim();
		return value;
	}
}
