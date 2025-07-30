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

        getMax(arr);
        System.out.println(max);
    }

    static void getMax(int[] arr) {
        int b = arr[0];

        for (int i = 1; i < N; i++) {
            if (arr[i] > arr[i - 1]) {
                max = Math.max(max, arr[i] - b);
            } else {
                b = arr[i];
            }
        }
    }
}
