import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();
        int l = 0, mxLen = 0;

        for (int r = 0; r < N; r++) {
            map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);

            while (map.size() > 2) {
                int cnt = map.get(arr[l]) - 1;

                if (cnt == 0) {
                    map.remove(arr[l]);
                } else {
                    map.put(arr[l], cnt);
                }

                l++;
            }

            mxLen = Math.max(mxLen, r - l + 1);
        }

        System.out.println(mxLen);
    }
}