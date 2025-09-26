import java.io.*;
import java.util.*;

public class Main {
	static int N, K, W;
	static int[] cost, inDeg, dp;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {	
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			cost = new int[N + 1];
			inDeg = new int[N + 1];
			dp = new int[N + 1];
			adj.clear();
			for (int i = 0; i < N + 1; i++) {
				adj.add(new ArrayList<>());
			} // 초기화
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N + 1; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			} // 건설 시간 입력
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj.get(from).add(to);
				inDeg[to]++;
			} // 건물 순서 입력
			
			W = Integer.parseInt(br.readLine());
			getDp();
		}
		
		System.out.print(sb);
	}
	
	static void getDp() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i < N + 1; i++) {
			if (inDeg[i] == 0) {
				q.add(i);
				dp[i] = cost[i];
			}
		} // 시작 건물 동시 건설
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < adj.get(cur).size(); i++) {
				int next = adj.get(cur).get(i);
				
				dp[next] = Math.max(dp[next], dp[cur] + cost[next]);
				inDeg[next]--;
				
				if (inDeg[next] == 0) {
					q.add(next);
				}
			}
		} // 바텀업
		
		sb.append(dp[W]).append('\n');
	}
}