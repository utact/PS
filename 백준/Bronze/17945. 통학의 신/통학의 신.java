import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int i = -1000; i <= 1000; i++) {
            if (i * i + 2 * A * i + B == 0) {
                sb.append(i).append(' ');
            }
        }

        System.out.print(sb);
    }
}
