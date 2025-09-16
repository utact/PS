/*
 * 배수 단위로 투자 가능
 * 호텔의 고객 C명 이상이라는 조건을 충족시키는 최소 금액
 * 
 * -> C의 최댓값은 1000이며, 고객 수는 최대 100명 단위
 */

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static final int MAX_C = 1101;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		// 초기화
		int[] dp = new int[MAX_C];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		// 바텀업
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int client = Integer.parseInt(st.nextToken());
			
			for (int j = client; j < dp.length; j++) {
				if (dp[j - client] == INF) continue;
				dp[j] = Math.min(dp[j], dp[j - client] + cost);
			}
		}
		
		// 고객 수가 C 이상인 최소 금액
		int ans = INF;
		
		for (int i = C; i < dp.length; i++) {
			ans = Math.min(ans, dp[i]);
		}
		
		System.out.println(ans);
	}
}