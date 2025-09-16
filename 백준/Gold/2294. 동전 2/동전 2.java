/*
 * 특정 금액을 충족하는 동전의 최소 개수
 * DP -> 바텀업
 */

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 초기화
		int[] dp = new int[k + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		// 바텀업
		for (int i = 0; i < n; i++) {
			int coin = Integer.parseInt(br.readLine());
			
			for (int j = coin; j < dp.length; j++) {
				if (dp[j - coin] == INF) continue;
				dp[j] = Math.min(dp[j], dp[j - coin] + 1);
			}
		}
		
		if (dp[k] == INF) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(dp[k]);
	}
}