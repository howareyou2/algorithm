import java.io.*;
import java.util.*;

public class Main {	
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1;testCase<T+1;testCase++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			for(int i=0; i<M; i++) {
				br.readLine().split(" ");
			}
			sb.append(N-1).append("\n");
		}
		System.out.println(sb);

		long end = System.currentTimeMillis();
		System.out.println("TIME: " + (end - start) + " ms");
		
	}
}