import java.io.*;
import java.util.*;

/*
 * 09:12 - 9:47
 * 
 * 시작점으로부터 3번만 움직여 이동경로를 합산한다면?
 */

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int ans = 0;
	
	static int N, M;
	static int[][] map;
	static int[][] vst;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		vst = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 탐색 시작
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				vst[i][j] = 1;
				checkGen(1, i, j, map[i][j]);
				vst[i][j] = 0;
				checkSpe(i, j);
			}
		}
		
		System.out.println(ans);
	}
	
	// 4종류의 테트로미노 체크
	static void checkGen(int cnt, int r, int c, int sum) {
		if (cnt > 3) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || vst[nr][nc] == 1) {
				continue;
			}
			
			vst[nr][nc] = 1;
			checkGen(cnt + 1, nr, nc, sum + map[nr][nc]);
			vst[nr][nc] = 0;
		}
	}
	
	// 특수한 테트로미노 체크
	static void checkSpe(int r, int c) {
		// ㅗ
		if (r - 1 >= 0 && c + 2 < M) {
			int tmp = map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r - 1][c + 1];
			ans = Math.max(ans, tmp);
		}
		
		// ㅜ
		if (r + 1 < N && c + 2 < M) {
			int tmp = map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r + 1][c + 1];
			ans = Math.max(ans, tmp);
		}
		
		// ㅏ
		if (r + 2 < N && c + 1 < M) {
			int tmp = map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 1][c + 1];
			ans = Math.max(ans, tmp);
		}
		
		// ㅓ
		if (r + 2 < N && c - 1 >= 0) {
			int tmp = map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 1][c - 1];
			ans = Math.max(ans, tmp);
		}
	}
}
