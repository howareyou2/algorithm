import java.io.*;
import java.util.*;

public class Main {

    static class Gear {
        int[] teeth;

        Gear(String state) {
            teeth = new int[8];
            for (int i = 0; i < 8; i++) {
                teeth[i] = state.charAt(i) - '0';
            }
        }

        void rotate(int direction) {
            if (direction == 1) {
                // 시계 방향
                int temp = teeth[7];
                for (int i = 7; i > 0; i--) {
                    teeth[i] = teeth[i - 1];
                }
                teeth[0] = temp;
            } else {
                // 반시계 방향
                int temp = teeth[0];
                for (int i = 0; i < 7; i++) {
                    teeth[i] = teeth[i + 1];
                }
                teeth[7] = temp;
            }
        }

        int getLeft() {
            return teeth[6];
        }

        int getRight() {
            return teeth[2];
        }

        int getTop() {
            return teeth[0];
        }
    }

    private static BufferedReader br;
    private static StringTokenizer st;

    private static Gear[] gears = new Gear[4];
    private static int K;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            gears[i] = new Gear(br.readLine());
        }

        K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int gearIndex = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            int[] rotation = new int[4];
            rotation[gearIndex] = direction;

            // 왼쪽 방향
            for (int i = gearIndex - 1; i >= 0; i--) {
                if (gears[i].getRight() != gears[i + 1].getLeft()) {
                    rotation[i] = -rotation[i + 1];
                } else {
                    break;
                }
            }

            // 오른쪽 방향
            for (int i = gearIndex + 1; i < 4; i++) {
                if (gears[i - 1].getRight() != gears[i].getLeft()) {
                    rotation[i] = -rotation[i - 1];
                } else {
                    break;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (rotation[i] != 0) {
                    gears[i].rotate(rotation[i]);
                }
            }
        }

        System.out.println(calculateScore());
    }

    private static int calculateScore() {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i].getTop() == 1) {
                score += (1 << i);
            }
        }
        return score;
    }
}