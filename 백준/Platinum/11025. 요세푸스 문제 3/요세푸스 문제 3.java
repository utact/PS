import java.io.*;
import java.util.*;

/*
 * J(1, k) = 0
 * J(n, k) = J((n-1, k) + k) % n
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int vic = 0;

        for (int i = 2; i <= n; i++) {
            vic = (vic + k) % i;
        }

        System.out.println(vic + 1);
    }
}