import java.io.*;
import java.util.*;

/*
 * 대각선 이동
 * map 크기는 최대 20 * 20
 */

public class Solution {
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, -1, 1 };

	static int str, stc, ans, N;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			ans = -1;

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			slv();
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}

	static void slv() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				str = i;
				stc = j;
				boolean[] vst = new boolean[101];
				vst[map[i][j]] = true;
				dfs(i, j, 0, 1, vst);
			}
		}
	}

	static void dfs(int r, int c, int dir, int cnt, boolean[] vst) {
		for (int d = dir; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            if (nr == str && nc == stc) {
                if (d == 3 && cnt >= 4) {
                    ans = Math.max(ans, cnt);
                }
                return;
            }

            if (vst[map[nr][nc]]) continue;

            // 재귀 호출
            vst[map[nr][nc]] = true;
            dfs(nr, nc, d, cnt + 1, vst);
            vst[map[nr][nc]] = false;
        }
	}
}
