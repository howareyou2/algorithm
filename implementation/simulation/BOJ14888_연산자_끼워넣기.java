import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] numbers;
    static int[] operators = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, numbers[0]);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int index, int currentResult) {
        if (index == N) {
            max = Math.max(max, currentResult);
            min = Math.min(min, currentResult);
            return;
        }

        for (int op = 0; op < 4; op++) {
            if (operators[op] > 0) {
                operators[op]--;

                int next = applyOperator(currentResult, numbers[index], op);
                dfs(index + 1, next);

                operators[op]++;
            }
        }
    }

    static int applyOperator(int a, int b, int op) {
        switch (op) {
            case 0: return a + b;
            case 1: return a - b;
            case 2: return a * b;
            case 3: return a / b;
        }
        return 0;
    }
}