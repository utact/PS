import java.io.*;
import java.util.*;

/*
 * 블록 수거하기 -> 2초
 * 블록 설치하기 -> 1초
 * 
 * 시작 시 B개의 블록 가진 채 시작
 * >> 최소 시간, 땅의 높이 구하기
 * 
 * 1. 최초 범위 (0 <= h <= 256)
 * 2. 탐색 범위 줄이기 (최소 값 <= h <= 입력된 전체 블록 + 인벤토리 블록)
 */

public class Main {
	static int N, M, B;
	static int[][] map;

	// 스코프 범위
	static int stt, end;
	
	// 정답
	static int ansT, ansH;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		int sum = 0;
		int min = 256;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int v = Integer.parseInt(st.nextToken());
				map[i][j] = v;
				
				sum += v;
				min = Math.min(min, v);
			}
		}
		
		stt = (sum + B) / (N * M);
		end = min;
		
		ansT = Integer.MAX_VALUE;
		ansH = -1;
		
		for (int i = stt; i >= end; i--) {
			getAns(i);
		}
		
		System.out.println(ansT + " " + ansH);
	}
	
	static void getAns(int h) {
		int t = 0;

		int add = B;
		int rmv = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cur = map[i][j];
				
				if (cur > h) {
					add += (cur - h);
					t += (cur - h) * 2;
				}
				else if (cur < h) {
					rmv += (h - cur);
					t += (h - cur) * 1;
				}
			}
		}
		
		if (rmv > add || t >= ansT) return;
		
		ansT = t;
		ansH = h;
	}
}