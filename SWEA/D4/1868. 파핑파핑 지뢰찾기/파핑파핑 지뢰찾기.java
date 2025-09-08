import java.io.*;
import java.util.*;

/*
 * N은 최대 300
 * 지뢰 유무 -> * 또는 .
 * 
 * 1.
 * >> 8방향 탐색 후 지뢰가 없다면 8방향에 대해 다시 재탐색 -> 1클릭으로 간주
 * >> 순차적으로 .인 곳만 클릭한다고 치되, 고려해야 할 건 그게 최적의 선택이 아닐 수 있음
 * >> 0을 우선 탐색해야 할 듯 함
 * 
 * 2.
 * >> 0인 영역을 우선 탐색하기 위해 전체 맵을 순회 -> 주변 지뢰 개수를 담은 숫자 맵 생성
 * >> 0을 우선 탐색하며 연쇄 작업 후 1카운트, 나머지 고립된 숫자는 개별 카운트
 */

public class Solution {
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	static int N, ans;
	static int[][] map, vst;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = 0;
			map = new int[N][N];
			vst = new int[N][N];

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
				}
			}

			// 맵 변환
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != '.')
						continue;

					int cnt = 0;

					for (int k = 0; k < 8; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;

						if (map[nr][nc] == '*')
							cnt++;
					}

					map[i][j] = cnt;
				}
			}

			bfs();
			
			// 0을 초과하는 경우 독립적으로 카운팅
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != '*' && map[i][j] != 0 && vst[i][j] == 0) ans++;
				}
			}

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}

	static void bfs() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 지뢰거나 방문한 곳은 스킵
				if (map[i][j] == '*' || vst[i][j] == 1)
					continue;

				// 0인 경우 BFS 이후 카운팅
				if (map[i][j] == 0) {
					Queue<int[]> q = new ArrayDeque<>();
					q.add(new int[] {i, j});
					vst[i][j] = 1;

					while (!q.isEmpty()) {
						int[] cur = q.poll();
						int r = cur[0];
						int c = cur[1];

						for (int k = 0; k < 8; k++) {
							int nr = r + dr[k];
							int nc = c + dc[k];

							if (nr < 0 || nr >= N || nc < 0 || nc >= N || vst[nr][nc] == 1 || map[nr][nc] == '*')
								continue;
							
							if (map[nr][nc] == 0) q.add(new int[] {nr, nc});	
							vst[nr][nc] = 1;
						}
					}
					
					ans++;
				}
			}
		}
	}
}