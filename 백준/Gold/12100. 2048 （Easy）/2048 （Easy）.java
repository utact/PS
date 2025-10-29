import java.io.*;
import java.util.*;

/*
 * 5번 이동하여 만들 수 있는 가장 큰 블록의 값
 * '왼쪽으로 밀기' 하나만 구현하고, 맵을 90도씩 회전시키기 -> 구현의 편의성
 */

public class Main {
	static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[][] oriMap = new int[N][N]; // 원본 맵

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				oriMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0; 
		game(0, oriMap);

		System.out.println(ans);
	}
	
	static void game(int depth, int[][] board) {
		if (depth == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans = Math.max(ans, board[i][j]);
				}
			}
			return;
		}

		int[][] r1 = rotate(board);
		int[][] r2 = rotate(r1);
		int[][] r3 = rotate(r2);

		// 4가지 경우 모두 재귀
		game(depth + 1, move(board));
		game(depth + 1, move(r1));
		game(depth + 1, move(r2));
		game(depth + 1, move(r3));
	}

	// 왼쪽으로 미는 메서드
	static int[][] move(int[][] map) {
		int[][] newMap = new int[N][N];
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			q.clear();
			
			// 숫자 넣기
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					q.offer(map[i][j]);
				}
			}

			// 합치기
			int col = 0;
			while (!q.isEmpty()) {
				int val = q.poll();

				if (newMap[i][col] == 0) {
					newMap[i][col] = val;
				} else if (newMap[i][col] == val) {
					newMap[i][col] *= 2;
					col++;
				} else {
					col++;
					newMap[i][col] = val;
				}
			}
		}
		return newMap;
	}

	// 90도 회전 메서드
	static int[][] rotate(int[][] map) {
		int[][] newMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[j][N - 1 - i] = map[i][j];
			}
		}
		return newMap;
	}
}