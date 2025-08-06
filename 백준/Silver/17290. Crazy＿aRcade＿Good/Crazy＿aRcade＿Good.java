import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	static int[][] vst = new int[10][10];

	static Set<Integer> rs = new HashSet<Integer>();
	static Set<Integer> cs = new HashSet<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] cur = { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 };

		for (int i = 0; i < 10; i++) {
			String l = br.readLine();

			for (int j = 0; j < 10; j++) {
				if (l.charAt(j) == 'o') {
					rs.add(i);
					cs.add(j);
				}
			}
		}

		if (!check(cur)) {
			System.out.println(0);
			return;
		}

		int[] tg = bfs(cur);
		System.out.println(Math.abs(tg[0] - cur[0]) + Math.abs(tg[1] - cur[1]));
	}

	static int[] bfs(int[] cur) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(cur);
		vst[cur[0]][cur[1]] = 1;

		while (!q.isEmpty()) {
			cur = q.poll();

			for (int j = 0; j < 4; j++) {
				int[] tmp = { cur[0] + dr[j], cur[1] + dc[j] };

				if (tmp[0] < 0 || tmp[0] > 9 || tmp[1] < 0 || tmp[1] > 9 || vst[tmp[0]][tmp[1]] == 1) {
					continue;
				}

				if (check(tmp)) {
					q.add(tmp);
					vst[tmp[0]][tmp[1]] = 1;
				} else {
					return tmp;
				}
			}
		}

		return null;
	}

	static boolean check(int[] tmp) {
		if (rs.contains(tmp[0]) || cs.contains(tmp[1])) {
			return true;
		}

		return false;
	}

}
