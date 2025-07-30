import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        getMax(arr, 0);
        System.out.println(max);
    }

    static void getMax(int[] arr, int b) {
        for (int i = b; i < N; i++) {
            if (i + 1 == N || !(arr[i + 1] > arr[i])) {
                max = Math.max(max, arr[i] - arr[b]);
                getMax(arr, i + 1);

                return;
            }
        }
    }
}
