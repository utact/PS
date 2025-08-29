import java.io.*;
import java.util.*;

public class Solution {
	static int[][] map;
	static int N, ans;
	
	static boolean[] vst;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			vst = new boolean[N];
			
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			comb(0, 0);
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.print(sb);
	}

	static void comb(int depth, int cnt) {
		if (cnt == N / 2) {
			cal();
			return;
		}

		if (depth == N) {
			return;
		}

		vst[depth] = true;
		comb(depth + 1, cnt + 1);

		vst[depth] = false;
		comb(depth + 1, cnt);
	}

	static void cal() {
		int a = 0;
		int b = 0;

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (vst[i] && vst[j]) {
					a += map[i][j] + map[j][i];
				} else if (!vst[i] && !vst[j]) {
					b += map[i][j] + map[j][i];
				}
			}
		}
		
		ans = Math.min(ans, Math.abs(a - b));
	}
}