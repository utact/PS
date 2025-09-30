import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr, ans;
	static long diff = Long.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		arr = new int[N];
		ans = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		getAns();
	}
	
	static void getAns() {
		// 기준 용액 잡고 투포인터 돌리기
		for (int i = 0; i < N - 2; i++) {
			int lt = i + 1, rt = N - 1;
			
			while (lt < rt) {
				long sum = (long) arr[i] + arr[lt] + arr[rt];
				
				if (Math.abs(sum) < diff) {
					diff = Math.abs(sum);
					ans[0] = arr[i];
					ans[1] = arr[lt];
					ans[2] = arr[rt];
				}
				
				if (sum < 0) {
					lt++;
				} else {
					rt--;
				}
			}
		}
		
		Arrays.sort(ans);
		System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
	}
}
