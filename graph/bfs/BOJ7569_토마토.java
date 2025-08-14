import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int M, N, H;
    private static int[][][] box;
    private static int[] dx = {-1, 1, 0, 0, 0, 0};
    private static int[] dy = {0, 0, -1, 1, 0, 0};
    private static int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int z = 0; z < H; z++) {
            for (int x = 0; x < N; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < M; y++) {
                    box[z][x][y] = Integer.parseInt(st.nextToken());
                    if (box[z][x][y] == 1) {
                        queue.offer(new int[]{z, x, y});
                    }
                }
            }
        }

        int days = bfs(queue);

        if (lastCheck()) {
            System.out.println(-1);
        } else {
            System.out.println(days);
        }
    }

    private static int bfs(Queue<int[]> queue) {
        int day = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean grown = false;

            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int z = cur[0];
                int x = cur[1];
                int y = cur[2];

                for (int i = 0; i < 6; i++) {
                    int nz = z + dz[i];
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nz < 0 || nz >= H || nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if (box[nz][nx][ny] != 0) continue;

                    box[nz][nx][ny] = 1;
                    grown = true;
                    queue.offer(new int[]{nz, nx, ny});
                }
            }

            if (grown) {
                day++;
            }
        }

        return day;
    }

    private static boolean lastCheck() {
        for (int z = 0; z < H; z++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (box[z][x][y] == 0) return true;
                }
            }
        }
        return false;
    }
}
