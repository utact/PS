import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int G = Integer.parseInt(br.readLine());
            int[] arr = new int[G];

            for (int i = 0; i < G; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            for (int i = 1; i < 1000000; i++) {
                Set<Integer> st = new HashSet<>();
                boolean pass = true;

                for (int j = 0; j < G; j++) {
                    if (!st.add(arr[j] % i)) {
                        pass = false;
                        break;
                    }
                }

                if (pass) {
                    sb.append(i).append('\n');
                    break;
                }
            }
        }

        System.out.print(sb);
    }
}
