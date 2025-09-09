import java.io.*;
import java.util.*;

public class Solution {
	static int ans;
	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N + 1];
			
			// 독립적인 관계로 초기화
			ans = N;
			
			for (int i = 0; i <= N; i++) {
				arr[i] = i;
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}
	
	static int find(int a) {
		if (arr[a] == a) {
			return a;
		}
		
		return arr[a] = find(arr[a]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		// 한 무리로 합친 경우 무리 수 감소
		if (rootA != rootB) {
			arr[rootB] = rootA;
			ans--;
		}
	}
}