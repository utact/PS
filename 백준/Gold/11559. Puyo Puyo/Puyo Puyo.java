import java.io.*;
import java.util.*;

/*
 * 14:51 시작
 * 
 * - 상하좌우 4개 이상 근접 시, 1연쇄
 * - 여러 그룹인 경우 한 번에 처리
 * - 2차원 배열 갱신
 * 
 * > 연쇄가 일어나지 않을 때까지 반복
 * > 총 깊이 수 출력하면 될 듯
 */

public class Main {
	// ., R, G, B, P, Y
	static int[][] map = new int[12][6];
	static int[][] vst = new int[12][6];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int ans = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < 6; j++) {
				char ch = line.charAt(j);
				int v = 0;
				
				if (ch == 'R') {
					v = 1;
				} else if (ch == 'G') {
					v = 2;
				} else if (ch == 'B') {
					v = 3;
				} else if (ch == 'P') {
					v = 4;
				} else if (ch == 'Y') {
					v = 5;
				}
				
				map[i][j] = v;
			}
		} // map
		
		while (true) {
			if (renew()) {
				ans++;
			} else {
				System.out.println(ans);
				return;
			}
		}
	} // main
	
	static ArrayList<int[]> bfs(int r, int c) {
		ArrayList<int[]> list = new ArrayList<>();
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {r, c});
		list.add(new int[] {r, c});
		vst[r][c] = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && vst[nr][nc] == 0 && map[nr][nc] == map[r][c]) {
					q.add(new int[] {nr, nc});
					list.add(new int[] {nr, nc});
					vst[nr][nc] = 1;
				}
			}
		}
		
		if (list.size() > 3) {
			return list;
		}
		
		return null;
	} // 근접 뿌요 카운트
	
	static boolean renew() {
		for (int i = 0; i < 12; i++) {
			Arrays.fill(vst[i], 0);
		}
		
		boolean isOk = false;
		int[] arr = new int[6];
		
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				// 가지치기
				if (map[i][j] == 0 || vst[i][j] == 1) {
					continue;
				}
				
				ArrayList<int[]> list = bfs(i, j);
				
				if (list != null) {
					isOk = true;
					
					while (!list.isEmpty()) {
						int[] tg = list.remove(0);
						map[tg[0]][tg[1]] = 0;
						arr[tg[1]]++;
					}
				}
			}
		} // 0 처리
		
		if (!isOk) {
			return false;
		}
		
		for (int i = 0; i < 6; i++) {
			if (arr[i] != 0) {
				ArrayList<Integer> list = new ArrayList<>();
				
				for (int j = 11; j >= 0; j--) {
					if (map[j][i] != 0) {
						list.add(map[j][i]);
					}
				}
				
				for (int j = 11; j >= 0; j--) {
					if (!list.isEmpty()) {						
						map[j][i] = list.remove(0);
					} else {
						map[j][i] = 0;
					}
				}
			}
		} // 열 갱신
		
		return true;
	}
}
