import java.io.*;
import java.util.*;

/*
 * 14:47
 * 다익스트라
 * 
 * grid -> graph
 * 인접리스트를 따로 정의하지 않고 델타 탐색을 활용할 것
 */

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int tc = 0;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int N;
	static int[][] dist;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			tc++;
			
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			
			dist = new int[N][N];
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve();
			sb.append("Problem").append(' ').append(tc).append(':').append(' ').append(dist[N - 1][N - 1]).append('\n');
		}
		
		System.out.print(sb);
	}
	
	static void solve() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		pq.add(new Node(0, 0, map[0][0]));
		dist[0][0] = map[0][0];
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int cost = cur.cost;
			
			if (cost > dist[r][c]) continue;
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				int ncost = cost + map[nr][nc];
				
				if (ncost < dist[nr][nc]) {
					pq.add(new Node(nr, nc, ncost));
					dist[nr][nc] = ncost;
				}
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int r, c, cost;

		public Node(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
