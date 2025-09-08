import java.io.*;
import java.util.*;

public class Solution {
	static int ans;
	static int N, M, R, C, L;
	static int[][] map, vst;
	
    // 상(0), 하(1), 좌(2), 우(3)
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
    // 각 파이프 타입(인덱스)이 연결된 방향(0,1,2,3)을 문자열로 저장
    // 0: null, 1: 상하좌우, 2: 상하, 3: 좌우, 4: 상우, 5: 하우, 6: 하좌, 7: 상좌
	static String[] types = { null, "0123", "01", "23", "03", "13", "12", "02" };
	
    // 현재 이동방향(인덱스 0,1,2,3)에 대해, 다음 칸이 가져야 할 입구 방향
	static int[] opposite = { 1, 0, 3, 2 }; 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
            vst = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
            if (map[R][C] != 0) {
			    bfs();
            } else {
                ans = 0;
            }
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{R, C});
		vst[R][C] = 1;
		ans = 1;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			int time = vst[r][c];
			
			if (time == L) continue;

			int curType = map[r][c];
			String validDirs = types[curType];

			for (int i = 0; i < validDirs.length(); i++) {
				int d = validDirs.charAt(i) - '0';
				
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
                if (vst[nr][nc] != 0) continue; 
                
                if (map[nr][nc] == 0) continue;

				int nextType = map[nr][nc];
				int requiredDir = opposite[d];
				
				if (types[nextType].contains(String.valueOf(requiredDir))) {
					q.add(new int[]{nr, nc});
					vst[nr][nc] = time + 1;
					ans++;
				}
			}
		}
	}
}