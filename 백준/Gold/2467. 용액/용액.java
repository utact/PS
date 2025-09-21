import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    static int base = Integer.MAX_VALUE;
    static int ans1 = 0, ans2 = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        solve();
        System.out.println(ans1 + " " + ans2);
    }

    static void solve() {
        int left = 0;
        int right = N - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(sum) < Math.abs(base)) {
                base = sum;
                ans1 = arr[left];
                ans2 = arr[right];
            }

            if (sum > 0) {
                right--;
            }
            else if (sum < 0) {
                left++;
            }
            else {
                break;
            }
        }

    }

}
