package HellDiverMacro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class HellDiverDAO {
	protected BufferedReader reader;
	protected InputStreamReader instream;
	protected FileInputStream finstream;
	
	protected BufferedWriter writer;
	protected OutputStreamWriter outstream;
	protected FileOutputStream foutstream;
	
	HellDiverDAO(){

	}
	
	private boolean checkHellDiverFolder() {
		File Folder = new File(System.getenv("APPDATA") + "\\HellDiver");
		boolean result = false;
		
		if (!Folder.exists()) {
			try{
				Folder.mkdir(); 
		    } 
		    catch(Exception e){
			    e.getStackTrace();
			}        
	     }
		else {
			result = true;
		}
		
		return result;
	}
	
	public String getHellDiverTag() {
		if(checkHellDiverFolder()) {
			String hellDiverTag = "";
			String path = System.getenv("APPDATA") + "\\HellDiver" + "\\hellDiverTag.txt";

			try {
				finstream = new FileInputStream(path);
				instream = new InputStreamReader(finstream, "UTF-8");
				reader = new BufferedReader(instream);
				
				String strline;
				
				while ((strline = reader.readLine()) != null) {
					hellDiverTag += strline + '\n';
				}
							
				reader.close();
			} catch (FileNotFoundException e) {
				System.out.println("경로를 찾을 수 업습니다.\n 경로: " + path);
			} catch (IOException e) {
				System.out.println("파일을 읽는 도중 문제가 발생 했습니다.\n" + e.getStackTrace());
			}
			
			return hellDiverTag;
		}
		else {
			return "";
		}
	}
	
	public void saveHellDiverTag(String tag) {
		String path = System.getenv("APPDATA") + "\\HellDiver" + "\\hellDiverTag.txt";
		
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();

		try {
			foutstream = new FileOutputStream(absolutePath);
			outstream = new OutputStreamWriter(foutstream, "UTF-8");
			writer = new BufferedWriter(outstream);
			
			writer.write(tag);
			
			writer.close();
		} catch (IOException e) {
			System.out.println("파일을 출력하는 도중 문제가 발생 했습니다.\n" + e.getStackTrace());
		}
	}
}
