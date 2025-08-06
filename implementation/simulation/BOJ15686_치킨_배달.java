import java.io.*;
import java.util.*;

public class Main {

    private static class Point {
        int row, col;
        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static BufferedReader br;
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] map;
    private static List<Point> homes = new ArrayList<>();
    private static List<Point> chickens = new ArrayList<>();
    private static int result = Integer.MAX_VALUE;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    homes.add(new Point(r, c));
                } else if (map[r][c] == 2) {
                    chickens.add(new Point(r, c));
                }
            }
        }

        visited = new boolean[chickens.size()];
        dfs(0, 0);
        System.out.println(result);
    }

    private static void dfs(int idx, int count) {
        if (count == M) {
            result = Math.min(result, calculate());
            return;
        }

        for (int i = idx; i < chickens.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }

    private static int calculate() {
        int sum = 0;
        for (Point home : homes) {
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < chickens.size(); i++) {
                if (visited[i]) {
                    Point chicken = chickens.get(i);
                    int dist = Math.abs(home.row - chicken.row) + Math.abs(home.col - chicken.col);
                    minDist = Math.min(minDist, dist);
                }
            }
            sum += minDist;
        }
        return sum;
    }
}