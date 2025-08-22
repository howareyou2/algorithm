import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int N, d, k, c;
    private static int[] belt;
    private static int[] count;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        belt = new int[N];
        count = new int[d + 1];

        for (int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        int sushiType = 0;
        int max = 0;

        for (int i = 0; i < k; i++) {
            if (count[belt[i]] == 0) sushiType++;
            count[belt[i]]++;
        }
        max = sushiType + (count[c] == 0 ? 1 : 0);

        for (int i = 1; i < N; i++) {
            int out = belt[i - 1];
            count[out]--;
            if (count[out] == 0) sushiType--;

            int in = belt[(i + k - 1) % N];
            if (count[in] == 0) sushiType++;
            count[in]++;

            max = Math.max(max, sushiType + (count[c] == 0 ? 1 : 0));
        }

        System.out.println(max);
    }
}