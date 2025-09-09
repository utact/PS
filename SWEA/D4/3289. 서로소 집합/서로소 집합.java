import java.io.*;
import java.util.*;

public class Solution {
	static int ans;
	static int n, m;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			arr = new int[n + 1];
			
			for (int i = 0; i <= n; i++) {
				arr[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				ans = 0;

				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (cmd == 0) {
					union(a, b);
				}
				else if (cmd == 1) {
					check(a, b);
					sb.append(ans);
				}
			}
			
			sb.append('\n');
		}

		System.out.print(sb);
	}
	
	static int find(int a) {
		if (arr[a] == a) {
			return a;
		}
		// 경로 압축
		return arr[a] = find(arr[a]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA != rootB) {
			arr[rootB] = rootA;
		}
	}
	
	static void check(int a, int b) {
		if (find(a) == find(b)) ans = 1;
	}
}