import java.io.*;
import java.util.*;

// 0: 빈 칸, 1: 집, 2: 치킨집
// 집의 수 최대 100개, 치킨집의 수 최대 13개

public class Main {
	static int N, M, ans = 10000;
	static ArrayList<int[]> homes, chickens;
	static boolean[] vst;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		homes = new ArrayList<>();
		chickens = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int v = Integer.parseInt(st.nextToken());
				if (v == 1) {
					homes.add(new int[] {i, j});
				} else if (v == 2) {
					chickens.add(new int[] {i, j});
				}
			}
		}
		
		vst = new boolean[chickens.size()];
		dfs(0, 0);
		System.out.println(ans);
	}
	
	// 치킨집 M개 뽑는 메서드
	static void dfs(int depth, int idx) {
		// 치킨집 M개 뽑았으면 계산 후 종료
		if (depth == M) {
			int tmp = getDist();
			ans = Math.min(ans, tmp);
			return;
		}
		
		// 현재 치킨집부터 재귀호출
		for (int i = idx; i < chickens.size(); i++) {
			if (vst[i]) continue;
			vst[i] = true;
			dfs(depth + 1, i + 1);
			vst[i] = false;
		}
	}
	
	// M개 고른 경우 총 거리 계산
	static int getDist() {
		int total = 0;
		
		// 각 집에서 가장 가까운 치킨집까지의 거리
		for (int[] home : homes) {
			int minDist = 100;
			
			for (int i = 0; i < chickens.size(); i++) {
				// 방문처리 된 곳이 선택된 치킨집
				if (vst[i]) {
					int tmp = Math.abs(home[0] - chickens.get(i)[0]) + Math.abs(home[1] - chickens.get(i)[1]);
					minDist = Math.min(minDist, tmp);
				}
			}
			
			// 이번 집에 대한 최소거리 합산
			total += minDist;
		}
		
		// 최종 치킨 거리 반환
		return total;
	}
}
