import java.io.*;
import java.util.*;

/*
 * 15:28 ㅅㅈ
 * 
 * 1월부터 12월까지 테이블 만들고
 * 순회하면서 비용, 다음달 스킵 여부 넘겨주면서 재귀 돌리기
 */

public class Solution {
	static int N, ans;
	static int[] price = new int[4];
	static int[] plan = new int[12];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;

			// 가격 받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			// 계획 받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			slv(0, 0, 0);
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}

	static void slv(int curIdx, int sum, int nextFreeCnt) {
		if (curIdx == 12) {
			ans = Math.min(ans, sum);
			return;
		}
		
		if (plan[curIdx] != 0) {
			if (nextFreeCnt == 0) {				
				slv(curIdx + 1, sum + plan[curIdx] * price[0], nextFreeCnt);
				slv(curIdx + 1, sum + price[1], nextFreeCnt);
				slv(curIdx + 1, sum + price[2], nextFreeCnt + 2);
				slv(curIdx + 1, sum + price[3], nextFreeCnt + 12);
			} else {
				slv(curIdx + 1, sum, nextFreeCnt - 1);
			}
		} else {
			slv(curIdx + 1, sum, nextFreeCnt);
		}
	}
}
