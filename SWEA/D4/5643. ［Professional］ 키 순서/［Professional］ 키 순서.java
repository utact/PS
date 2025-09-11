import java.io.*;
import java.util.*;

public class Solution {
	static final int MAX_N = 500;
	static int ans;
	static int N, M;
	static BitSet[] taller = new BitSet[MAX_N + 1];
	static BitSet[] shorter = new BitSet[MAX_N + 1];

	// static 초기화 블록으로 객체 생성
	static {
		for (int i = 0; i <= MAX_N; i++) {
			taller[i] = new BitSet(MAX_N + 1);
			shorter[i] = new BitSet(MAX_N + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			// 필요한 만큼만 clear()로 초기화
			for (int i = 1; i <= N; i++) {
				taller[i].clear();
				shorter[i].clear();
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				taller[a].set(b);
				shorter[b].set(a);
			}
			
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					if (taller[i].get(k)) {
						taller[i].or(taller[k]);
					}
					if (shorter[i].get(k)) {
						shorter[i].or(shorter[k]);
					}
				}
			}
			
			for (int i = 1; i <= N; i++) {
				if (taller[i].cardinality() + shorter[i].cardinality() == N - 1) {
					ans++;
				}
			}
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.print(sb);
	}
}