import java.io.*;

public class Main {
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        arr = new int[1000001];
        
        for (int i = 2; i <= 1000; i++) {
            if (arr[i] == 1) {
                continue;
            }
            for (int j = i * i; j <= 1000000; j += i ) {
                arr[j] = 1;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            long S = Long.parseLong(br.readLine());

            scr(S);
        }

        System.out.print(sb);
    }

    static void scr(Long S) {
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (S % i == 0) {
                    sb.append("NO").append("\n");

                    return;
                }
            }
        }

        sb.append("YES").append("\n");
    }
}
