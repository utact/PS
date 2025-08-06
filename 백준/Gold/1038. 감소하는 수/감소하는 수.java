import java.io.*;
import java.util.*;

public class Main {
	static List<Long> nums = new ArrayList<>();
			
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		tr(0, 10);
		nums.sort(Comparator.naturalOrder());
		
		if (N >= nums.size()) {
			System.out.println(-1);
		} else {
			System.out.println(nums.get(N));
		}
	}
	
	static void tr(long cur, long end) {
		if (cur == 9876543210L) {
			return;
		}
		
		long nCur = cur * 10;
		
		for (int i = 0; i < end; i++) {
			nums.add(nCur + i);
			tr(nCur + i, (nCur + i) % 10);
		}
	}
}