import java.io.*;
import java.util.*;

public class Main {
	static int N = 9;
	static int[][] map = new int[N][N];
	static boolean[][] rowCheck = new boolean[N][10];
	static boolean[][] colCheck = new boolean[N][10];
	static boolean[][] squareCheck = new boolean[N][10];
	static ArrayList<Pos> emptyPos = new ArrayList<>();

	static int COUNT;

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				int v = line.charAt(j);

				if (v == '0') {
					map[i][j] = -1;
					emptyPos.add(new Pos(i, j));
				} else {
					v -= '0';
					
					map[i][j] = v;
					rowCheck[i][v] = true;
					colCheck[j][v] = true;
					squareCheck[(i / 3) * 3 + j / 3][v] = true;
				}
			}
		}

		COUNT = emptyPos.size();
		solve(0);
	}

	static void solve(int idx) {
		if (idx == COUNT) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
			System.out.println(sb);
			System.exit(0);
		}

		Pos p = emptyPos.get(idx);
		int r = p.r;
		int c = p.c;
		int s = (r / 3) * 3 + (c / 3);

		for (int tmp = 1; tmp <= 9; tmp++) {
			if (rowCheck[r][tmp] || colCheck[c][tmp] || squareCheck[s][tmp]) {
				continue;
			}

			map[r][c] = tmp;
			rowCheck[r][tmp] = true;
			colCheck[c][tmp] = true;
			squareCheck[s][tmp] = true;

			solve(idx + 1);

			map[r][c] = -1;
			rowCheck[r][tmp] = false;
			colCheck[c][tmp] = false;
			squareCheck[s][tmp] = false;
		}
	}
}