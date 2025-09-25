import java.io.*;
import java.util.*;

public class Main {
	static String A, B;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine();
		B = br.readLine();
		
		solve();
		System.out.print(sb);
	}
	
	static void solve() {
		int aLen = A.length(), bLen = B.length();
		
		int[][] dp = new int[aLen + 1][bLen + 1];
		
		// 최장 길이 구하기
		for (int i = 0; i < aLen; i++) { // A 문자열의 i 인덱스
			for (int j = 0; j < bLen; j++) { // B 문자열의 j 인덱스
				if (A.charAt(i) == B.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				}
			}
		}
		
		sb.append(dp[aLen][bLen]).append('\n');
		
		// 특이 케이스 처리하기
		if (dp[aLen][bLen] == 0) return;
		
		// 문자열 역추적하기
		Stack<Character> stk = new Stack<>();
		
		int aIdx = aLen - 1, bIdx = bLen - 1; // 문자열 기준 인덱스
		
		while (aIdx >= 0 && bIdx >= 0) {
			if (A.charAt(aIdx) == B.charAt(bIdx)) {
				stk.push(A.charAt(aIdx));
				aIdx--;
				bIdx--;
			} else {
				if (dp[aIdx][bIdx + 1] > dp[aIdx + 1][bIdx]) {
					aIdx--;
				} else {
					bIdx--;
				}
			}
		}
		
		while (!stk.isEmpty()) {
			sb.append(stk.pop());
		}
	}
}