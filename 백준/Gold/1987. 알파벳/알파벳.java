import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int R, C, ans = 0;
    static int[][] map;
    static boolean[] apb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        apb = new boolean[26];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j) - 'A';
            }
        }

        dfs(0, 0, 1);
        System.out.println(ans);
    }

    static void dfs(int r, int c, int cnt) {
        apb[map[r][c]] = true;
        ans = Math.max(ans, cnt);

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C || apb[map[nr][nc]]) continue;
            dfs(nr, nc, cnt + 1);
        }

        apb[map[r][c]] = false;
    }
}
