import java.io.*;
import java.util.*;

/*
 * 우리 배열 N + 1
 * 
 * 구간합 조건을 만족하는 케이스 출력하기
 * ㄴ 햄스터가 가장 많은 경우 우선
 * ㄴ 햄스터 수가 동일한 경우 사전 순 우선
 * 
 * 햄스터 최대 10마리, 배열 크기 최대 6 -> 완전탐색 가능
 */

public class Solution {
	static int N, X, M;
	static int[] hams;
	static int stt, end, sum;
	static int[][] cond;
	static int hamCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 우리 수
			X = Integer.parseInt(st.nextToken()); // 총 햄스터 수
			M = Integer.parseInt(st.nextToken()); // 조건 수

			hams = new int[N + 1];
			Arrays.fill(hams, -1);

			cond = new int[M][3];

			hamCnt = -1;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				stt = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				sum = Integer.parseInt(st.nextToken());

				cond[i] = new int[] { stt, end, sum };
			}

			makeArr(hams, 1); // 1번 우리부터 채우기

			sb.append('#').append(tc).append(' ');
			if (hams[1] == -1) {
				sb.append(-1);
			} else {
				for (int i = 1; i < hams.length; i++) {
					sb.append(hams[i]).append(' ');
				}
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}

	static void makeArr(int[] arr, int idx) {
		if (idx == N + 1) {
			slv(arr);
			return;
		}

		int[] tmpArr = Arrays.copyOf(arr, arr.length);

		for (int i = 0; i <= X; i++) {
			tmpArr[idx] = i;
			makeArr(tmpArr, idx + 1);
		}
	}

	// 갱신 메서드
	static void slv(int[] arr) {
		int tmpHamCnt = 0;

		for (int i = 1; i < arr.length; i++) {
			tmpHamCnt += arr[i];
		}

		if (check(arr) && tmpHamCnt > hamCnt) {
			hams = Arrays.copyOf(arr, arr.length);
			hamCnt = tmpHamCnt;
		}
	}

	// 조건 체크 메서드
	static boolean check(int[] arr) {
		for (int i = 0; i < cond.length; i++) {
			int[] curCond = cond[i];
			int stt = curCond[0];
			int end = curCond[1];
			int sum = curCond[2];

			int tmpSum = 0;

			for (int j = stt; j <= end; j++) {
				tmpSum += arr[j];
			}

			if (sum != tmpSum) {
				return false;
			}
		}

		return true;
	}
}
