import java.io.*;
import java.util.*;
 
public class Solution
{
    static StringBuilder sb = new StringBuilder();
    static int[] nums;
    static int max;
    static int min;
    static int N;
    /*
     * 0: 더하기
     * 1: 빼기
     * 2: 곱하기
     * 3: 나누기
     */
     
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int T = Integer.parseInt(br.readLine());
 
        for (int i = 1; i <= T; i++) {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
             
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            int[] opers = new int[4];
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                opers[j] = Integer.parseInt(st.nextToken());
            }
             
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                nums[j] = Integer.parseInt(st.nextToken());
            }
             
            dfs(nums[0], 1, opers);
            sb.append("#").append(i).append(" ").append(max - min).append('\n');
        }
         
        System.out.print(sb);
    }
     
    static void dfs(int cur, int idx, int[] opers) {
        if (idx == N) {
            max = Math.max(max, cur);
            min = Math.min(min, cur);
            return;
        }
         
        if (opers[0] > 0) {
            int[] nOps = opers.clone();
            nOps[0]--;  
            dfs(cur + nums[idx], idx + 1, nOps);
        }
         
        if (opers[1] > 0) {
            int[] nOps = opers.clone();
            nOps[1]--;  
            dfs(cur - nums[idx], idx + 1, nOps);
        }
         
        if (opers[2] > 0) {
            int[] nOps = opers.clone();
            nOps[2]--;  
            dfs(cur * nums[idx], idx + 1, nOps);
        }
         
        if (opers[3] > 0) {
            int[] nOps = opers.clone();
            nOps[3]--;  
            dfs(cur / nums[idx], idx + 1, nOps);
        }
    }
}