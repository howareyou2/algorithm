import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;
    private static int N, result;
    private static int[] score;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        score = new int[N + 1];
        dp = new int[N + 1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            score[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = score[1];
        if(N >= 2){
            dp[2] = score[1] + score[2];
        }

        for(int i = 3; i <= N; i++){
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + score[i - 1]) + score[i];
        }

        result = dp[N];
        System.out.println(result);
    }
}