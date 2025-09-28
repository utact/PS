import java.io.*;
import java.util.*;

/*
 * (0, 0) -> (N-1, M-1) 최단경로
 * ㄴ 시작하는 칸과 끝나는 칸 포함
 * ㄴ 벽을 한 번 부술 수 있으며, 도착 불가능하면 -1 반환
 */

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int n, m;
    static int[][] map;
    static int[][][] vst;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        vst = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        } // 맵 초기화

        bfs();
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0, 0});
        vst[0][0][0] = 1;

        int cnt = 1;

        while (!q.isEmpty()) {
            int rep = q.size();

            while (rep-- > 0) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int brk = cur[2];

                if (r == n - 1 && c == m - 1) {
                    System.out.println(cnt);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                    // 벽이 없으니, brk 그대로 방문 체크
                    if (map[nr][nc] == 0 && vst[nr][nc][brk] == 0) {
                        vst[nr][nc][brk] = 1;
                        q.add(new int[] {nr, nc, brk});
                    }
                    // 벽이 있으니, brk 잔여 체크 && 1(부숨) 방문 체크
                    else if (map[nr][nc] == 1 && brk == 0 && vst[nr][nc][1] == 0) {
                        vst[nr][nc][1] = 1;
                        q.add(new int[] {nr, nc, 1});
                    }
                }
            }

            cnt++;
        }

        System.out.println(-1);
    }
}