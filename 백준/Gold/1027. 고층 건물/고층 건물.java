import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static int[] see;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		see = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		see();

		int max = 0;

		for (int i = 0; i < N; i++) {
			max = Math.max(max, see[i]);
		}

		System.out.println(max);
	}

	static void see() {
		for (int i = 0; i < N - 1; i++) {
			double cur = Integer.MIN_VALUE;

			for (int j = i + 1; j < N; j++) {
				double tmp = (double) (arr[j] - arr[i]) / (j - i);

				if (tmp > cur) {
					cur = tmp;
					see[i]++;
					see[j]++;
				}
			}
		}
	}
}
