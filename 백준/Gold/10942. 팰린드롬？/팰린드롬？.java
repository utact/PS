import java.io.*;
import java.util.*;

/*
 * 자연수 N개를 적고, 질문 총 M번 진행
 * 원 베이스 인덱스 S부터 E까지 팰린드롬 여부
 * 
 * >> 수열의 크기는 2,000이고 질문의 개수는 1,000,000개
 * >> 0.5초 제한에 질문을 여러번 하는 것을 보아 메모이제이션 필요할 듯..
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// DP 테이블 선언
		int[][] dp = new int[N][N];

		// 중심점 잡기
		for (int i = 0; i < N; i++) {
			inner:
			for (int v = 0; i - v >= 0 && i + v < N; v++) {
				if (nums[i - v] == nums[i + v]) {
					dp[i - v][i + v] = 1;					
				} else {
					break inner;
				}
			}
			
			// 짝수 체크 가능하다면
			if (i > 0) {
				inner2:
				for (int v = 0; i - 1 - v >= 0 && i + v < N; v++) {
					if (nums[i - 1 - v] == nums[i + v]) {
						dp[i -1 - v][i + v] = 1;
					} else {
						break inner2;
					}
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(dp[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1]).append('\n');
		}

		System.out.print(sb);
	}
}