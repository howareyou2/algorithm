import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int n, w, l;
    private static int[] trucks;
    private static Queue<Integer> bridge;
    private static int time, totalWeight;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        trucks = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        bridge = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            bridge.offer(0);
        }

        int index = 0;
        time = 0;
        totalWeight = 0;

        while (index < n) {
            time++;

            totalWeight -= bridge.poll();

            if (totalWeight + trucks[index] <= l) {
                bridge.offer(trucks[index]);
                totalWeight += trucks[index];
                index++;
            } else {
                bridge.offer(0);
            }
        }

        time += w;

        System.out.println(time);
    }
}