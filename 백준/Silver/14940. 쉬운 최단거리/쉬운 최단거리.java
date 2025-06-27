import java.io.*;
import java.util.*;

public class Main {
    static int[][] map, res, visited;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] goal = new int[2];
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        res = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(st.nextToken());

                if (value == 2) {
                    goal[0] = i;
                    goal[1] = j;
                }
                
                map[i][j] = value;
            }
        }

        bfs(goal);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && res[i][j] == 0) {
                    res[i][j] = -1;
                }
                sb.append(res[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }

    static void bfs(int[] goal) {
        Queue<int[]> q = new LinkedList<>(); // {row, col, dist}
        q.add(new int[]{goal[0], goal[1], 0});
        visited[goal[0]][goal[1]] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nc < 0 || nr >= n || nc >= m || visited[nr][nc] == 1) {
                    continue;
                }

                if (map[nr][nc] == 1) {
                    int dist = cur[2] + 1;
                    res[nr][nc] = dist;

                    q.add(new int[]{nr, nc, dist});
                    visited[nr][nc] = 1;
                }
            }
        }
    }
}