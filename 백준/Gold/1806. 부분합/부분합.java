import java.io.*;
import java.util.*;

/*
 * [슬라이딩 윈도우]
 * 1. 가변 크기 윈도우, 2. 고정 크기 윈도우
 * 
 * BOJ 1806. 부분합 (S 이상인 것 중 가장 짧은 구간합의 길이)
 * >> 윈도우의 크기가 정해져 있지 않고, 특정 조건이 제시됨 -> for-while 구조
 */

public class Main {
	static int N, S, len;
	static int[] nums;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
        
        sdwd();
    }
    
    static void sdwd() {
    	int lt = 0, sum = 0, len = Integer.MAX_VALUE;
    	
    	// 슬라이딩 윈도우
    	for (int rt = 0; rt < N; rt++) {
			sum += nums[rt];
			
			if (sum < S) continue;
			
			while (sum >= S) {
				len = Math.min(len, rt - lt + 1);
				sum -= nums[lt++];
			}
		}
    	
    	// 결과 출력
    	if (len == Integer.MAX_VALUE) {
    		System.out.println(0);
    	} else {
    		System.out.println(len);
    	}
    }
}