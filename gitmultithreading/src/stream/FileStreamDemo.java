package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileStreamDemo {
	public static void main(String ar[]) throws IOException{
		try {
		System.out.println(System.getProperty("user.dir"));
		
		long start = System.currentTimeMillis();
		
		
		long wordCount = Files.readAllLines(Paths.get("FileContent.txt")).stream().flatMap(line->Arrays.stream(line.split(" "))).count();
		System.out.println(wordCount);
		System.out.println(System.currentTimeMillis()-start +" seconds" );
		
		start = System.currentTimeMillis();
		wordCount = Files.readAllLines(Paths.get("FileContent.txt")).parallelStream().flatMap(line->Arrays.stream(line.split(" "))).count();
		System.out.println(wordCount);
		
		System.out.println(System.currentTimeMillis()-start +" seconds" );
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
