import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14502
// 지도의 크기 8 * 8, 시간제한 2초

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int N, M, safe, virusSize;
	static int[][] map;
	static boolean[][] vst;
	static ArrayList<int[]> viruses = new ArrayList<>();
	static ArrayList<int[]> walls = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 안전 영역의 크기 = N * M - 벽의 수 - 바이러스 크기 총합
		safe = N * M - 3; // 앞으로 세우게 될 3개의 벽 미리 차감
		virusSize = N * M;
		
		map = new int[N][M];
		vst = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int v = Integer.parseInt(st.nextToken());
				if (v == 2) viruses.add(new int[] {i, j});
				else if (v == 1) safe--; // 벽을 반영하여 안전 영역 차감
				map[i][j] = v;
			}
		}
		
		peekWall();
		
		System.out.println(safe - virusSize);
	}
	
	static void peekWall() {
		// 기저조건: 벽 3개 골랐으면 계산
		if (walls.size() == 3) {
			cal();
			return;
		}
		
		// 벽 고르기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && !vst[i][j]) {
					vst[i][j] = true;
					walls.add(new int[] {i, j});
					peekWall();
					vst[i][j] = false;
					walls.remove(walls.size() - 1);
				}
			}
		}
	}
	
	static void cal() {
		// 체크할 맵
		int[][] tmpMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < 3; i++) {
			tmpMap[walls.get(i)[0]][walls.get(i)[1]] = 1;
		}
				
		// 바이러스 BFS
		boolean[][] tmpVst = new boolean[N][M];
		int tmpVirusSize = viruses.size();
		
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < viruses.size(); i++) {
			q.add(viruses.get(i));
		}
		
		while (!q.isEmpty()) {
			if (tmpVirusSize > virusSize) return;
			
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || tmpVst[nr][nc] || tmpMap[nr][nc] != 0) {
					continue;
				}
				
				tmpVirusSize++;
				tmpVst[nr][nc] = true;
				q.add(new int[] {nr, nc});
			}
		}
		
		virusSize = Math.min(virusSize, tmpVirusSize);
	}
}
