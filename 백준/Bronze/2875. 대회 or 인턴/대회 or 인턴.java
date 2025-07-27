import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int tmp = (N + M - K) / 3;

        if (N >= 2 * tmp && M >= tmp) {
            System.out.println(tmp);
        } else if (M >= tmp) {
            System.out.println(N / 2);
        } else if (N >= 2 * tmp) {
            System.out.println(M);
        }
    }
}
