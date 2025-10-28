import java.io.*;
import java.util.*;

/*
 * 행으로 만들어진 길의 수는 입력과 동시에 검증할 수 있으나,
 * 구현 시 가독성을 해칠 뿐 아니라 그로 얻는 최적화 이점도 미미하다고 생각되어 적용하지 아니함.
 */

public class Main {
	static int N, L, ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		getAns();
		System.out.println(ans);
	}

	static void getAns() {
		for (int c = 0; c < map.length; c++) {
			ans += colCheck(map[0][c], c);
		}
		for (int r = 0; r < map.length; r++) {
			ans += rowCheck(map[r][0], r);
		}
	}

	static int colCheck(int base, int c) {
		int stk = 1;

		for (int r = 1; r < map.length; r++) {
			if (Math.abs(base - map[r][c]) > 1) {
				return 0;
			}
			// 베이스 칸 대비 1 오른 경우
			else if (base + 1 == map[r][c]) {
				if (stk < L) {
					return 0;
				}
				base++;
				stk = 1;
			}
			// 베이스 칸 대비 1 내린 경우
			else if (base - 1 == map[r][c]) {
				base--;
				if (r + L - 1 >= N) {
					return 0;
				}
				for (int nr = r; nr < r + L; nr++) {
					if (map[nr][c] != base) {
						return 0;
					}
				}
				stk = 0;
				r += L - 1;
			}
			// 베이스 칸과 같은 경우 스트릭 연장
			else if (base == map[r][c]) {
				stk++;
			}
		}
		
		return 1;
	}
	
	static int rowCheck(int base, int r) {
		int stk = 1;

		for (int c = 1; c < map.length; c++) {
			if (Math.abs(base - map[r][c]) > 1) {
				return 0;
			}
			// 베이스 칸 대비 1 오른 경우
			else if (base + 1 == map[r][c]) {
				if (stk < L) {
					return 0;
				}
				base++;
				stk = 1;
			}
			// 베이스 칸 대비 1 내린 경우
			else if (base - 1 == map[r][c]) {
				base--;
				if (c + L - 1 >= N) {
					return 0;
				}
				for (int nc = c; nc < c + L; nc++) {
					if (map[r][nc] != base) {
						return 0;
					}
				}
				stk = 0;
				c += L - 1;
			}
			// 베이스 칸과 같은 경우 스트릭 연장
			else if (base == map[r][c]) {
				stk++;
			}
		}
		
		return 1;
	}
}
