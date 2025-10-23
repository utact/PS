import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] A;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        if (N == 0) {
            System.out.println(0);
            return;
        }

        A = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dpInc = new int[N];
        int[] dpDec = new int[N];
        
        // LIS (왼쪽 -> 오른쪽)
        calLIS(dpInc);
        
        // LDS (오른쪽 -> 왼쪽)
        calLDS(dpDec);
        
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            int curLen = dpInc[i] + dpDec[i] - 1;
            if (maxLen < curLen) {
                maxLen = curLen;
            }
        }
        
        System.out.println(maxLen);
    }

    private static void calLIS(int[] dp) {
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
    }

    private static void calLDS(int[] dp) {
        for (int i = N - 1; i >= 0; i--) {
            dp[i] = 1;
            
            for (int j = N - 1; j > i; j--) {
                if (A[j] < A[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
    }
}
