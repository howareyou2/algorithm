import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;
    private static int R, C, N;
    private static char[][] map;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
        }

        if(N == 1){
            sb = new StringBuilder();
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.print(sb.toString());
            return;
        }

        if(N % 2 == 0){
            fillWithBomb();
            sb = new StringBuilder();
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.print(sb.toString());
            return;
        }

        char[][] after3 = simulate(map);
        char[][] after5 = simulate(after3);

        sb = new StringBuilder();
        
        if(N % 4 == 3){
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    sb.append(after3[i][j]);
                }
                sb.append("\n");
            }
        }else{
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    sb.append(after5[i][j]);
                }
                sb.append("\n");
            }
        }
        
        System.out.print(sb.toString());
    }

    private static char[][] simulate(char[][] input) {
        char[][] result = new char[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                result[i][j] = 'O';
            }
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(input[i][j] == 'O'){
                    result[i][j] = '.';
                    for(int d = 0; d < 4; d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr >= 0 && nr < R && nc >= 0 && nc < C){
                            result[nr][nc] = '.';
                        }
                    }
                }
            }
        }
        return result;
    }

    private static void fillWithBomb() {
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                map[i][j] = 'O';
            }
        }
    }
}
