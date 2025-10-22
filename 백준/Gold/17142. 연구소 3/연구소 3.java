import java.io.*;
import java.util.*;

// 백준 https://www.acmicpc.net/problem/17142

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int N, M, minT = 2500, emptys = 0;
	static int[][] map;
	static ArrayList<int[]> virus = new ArrayList<>();
	static ArrayList<int[]> selected = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int v = Integer.parseInt(st.nextToken());
				if (v == 2) {
					virus.add(new int[] {i, j});
				} else if (v == 0) {
					emptys++;
				}
				map[i][j] = v;
			}
		}
		
		peekV(0, 0); // 바이러스 M개 고른 후 시뮬레이션
		
		if (minT == 2500) minT = -1;
		System.out.println(minT);
	}
	
	static void peekV(int stt, int cnt) {
		if (cnt == M) {
			cal();
			return;
		}
		
		// 바이러스 리스트에서 뽑으며 조합
		for (int i = stt; i < virus.size(); i++) {
			selected.add(virus.get(i));
			peekV(i + 1, cnt + 1);
			selected.remove(selected.size() - 1);
		}
	}
	
	static void cal() {
		// 시간 측정용 맵 초기화
		int[][] tmpMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(tmpMap[i], -1);
		}
				
		// 바이러스 BFS
		Queue<int[]> q = new ArrayDeque<>();
		for (int[] cur : selected) {
			q.add(cur);
			tmpMap[cur[0]][cur[1]] = 0;
		}
		
		int maxT = 0; // 마지막 감염 시간
		int vCnt = 0; // 새로 감염된 칸의 수
		
		while (!q.isEmpty()) {
			if (maxT > minT) break;	
			
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1)
					continue;
				if (tmpMap[nr][nc] != -1) //
					continue;
				
				tmpMap[nr][nc] = tmpMap[r][c] + 1;
				q.add(new int[] {nr, nc});
				
				if (map[nr][nc] == 0) { //
					vCnt++;
					maxT = Math.max(maxT, tmpMap[nr][nc]);
				}
			}
		}
		
		if (vCnt == emptys)
			minT = Math.min(minT, maxT);
	}
}
