import java.io.*;
import java.util.*;

/*
 * 100일 동안 치즈 갉아먹는 요정
 * X번째 날 -> X만큼 맛있는 칸 전부 먹기
 * 
 * >> 100일 중 치즈 덩어리가 가장 많을 때의 개수 구하기
 * >> 해당 일자 이하인 곳을 만나면 탐색 중지 -> 카운팅
 */

public class Solution {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int N, ans;
	static int[][] map, vst;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            ans = 1;
            map = new int[N][N];
            
            for (int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
            	for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
            
            for (int i = 1; i < 100; i++) {
                vst = new int[N][N];
            	slv(i);
			}
            
            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        
        System.out.print(sb);
    }
    
    static void slv(int day) {
    	int cnt = 0;
    	
    	for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (vst[i][j] == 1 || map[i][j] <= day) continue;
				bfs(i, j, day);
				cnt++;
			}
		}
    	
    	ans = Math.max(ans, cnt);
    }
    
    static void bfs(int r, int c, int day) {
    	Queue<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {r, c});
    	vst[r][c] = 1;
    	
    	while (!q.isEmpty()) {
    		int[] tmp = q.poll();
    		int tr = tmp[0];
        	int tc = tmp[1]; 
    		
    		for (int i = 0; i < 4; i++) {
    			int nr = tr + dr[i];
    			int nc = tc + dc[i];
    			
    			if (nr < 0 || nr >= N || nc < 0 || nc >= N || vst[nr][nc] == 1 || map[nr][nc] <= day) continue;
    			
    			q.add(new int[] {nr, nc});
    			vst[nr][nc] = 1;
    		}
    	}
    }
}