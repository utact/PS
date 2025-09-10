import java.io.*;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/*
 * 출발지 0, 0
 * 도착지 N - 1, N - 1
 */

public class Solution {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int N, ans;
	static int[][] map, dist;
	
	static class Cell implements Comparable<Cell> {
		int r, c, w;

		public Cell(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Cell o) {
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());		

		for (int tc = 1; tc <= T; tc++) {
			// 초기화
			ans = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dist = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			// 경로 탐색
			dijk();
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}
	
	static void dijk() {
		PriorityQueue<Cell> pq = new PriorityQueue<>();
		pq.add(new Cell(0, 0, 0));
		dist[0][0] = 0;
		
		while (!pq.isEmpty()) {
			Cell cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w;
			
			if (r == N - 1 && c == N - 1) {
				ans = w;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || w > dist[r][c]) {
					continue;
				}
				
				int nw = dist[r][c] + map[nr][nc];
				
				if (nw >= dist[nr][nc]) {
					continue;
				}
				
				pq.add(new Cell(nr, nc, nw));
				dist[nr][nc] = nw;
			}
		}
	}
}