import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (R == 0 && C == 0) return;
            prt(R, C);
        }
    }

    static void prt(int R, int C) throws Exception {
        int[][] map = new int[R][C];
        int[][] mine = new int[R + 2][C + 2];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < R; i++) {
            String cmd = br.readLine();

            for (int j = 0; j < C; j++) {
                char tmp = cmd.charAt(j);

                if (tmp == '*') {
                    for (int k = 0; k < 8; k++) {
                        mine[1 + i + dr[k]][1 + j + dc[k]]++;
                    }

                    map[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    sb.append('*');
                } else {
                    sb.append(mine[i + 1][j + 1]);
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
