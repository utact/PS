import java.io.*;
import java.util.*;

/*
 * 13:40
 * 
 * 1회에 한해 벽 부수기 가능
 * 최단 경로 구하기
 * 
 * 0은 빈 칸, 1은 벽, 탈출 불가능하다면 -1
 */

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int ans = -1;
	static int depth = 0;
	
	static int N, M;
	static int[][] map;
	static int[][][] vst; // 행, 열, 부수기 여부
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		vst = new int[N][M][2];
		
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int sc = Integer.parseInt(st.nextToken()) - 1;
		
		st = new StringTokenizer(br.readLine());
		int er = Integer.parseInt(st.nextToken()) - 1;
		int ec = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(sr, sc, er, ec);
		System.out.println(ans);
	}
	
	static void bfs(int sr, int sc, int er, int ec) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {sr, sc, 1}); // 행, 열, 부수기
		vst[sr][sc][0] = 1;
		
		while (!q.isEmpty()) {
			int rep = q.size();
			
			while (rep-- > 0) {				
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				
				if (r == er && c == ec) {
					ans = depth;
					return;
				}
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
						continue;
					}
					
					// 벽 && 부수기 가능
					if (map[nr][nc] == 1 && cur[2] == 1 && vst[nr][nc][1] == 0) {
						vst[nr][nc][1] = 1;
						q.add(new int[] {nr, nc, 0});
					}
					// 빈 칸
					else if (map[nr][nc] == 0 && vst[nr][nc][cur[2]] == 0) {
						vst[nr][nc][cur[2]] = 1;				
						q.add(new int[] {nr, nc, cur[2]});
					}
				}
			}
			
			depth++;
		}
	}
}