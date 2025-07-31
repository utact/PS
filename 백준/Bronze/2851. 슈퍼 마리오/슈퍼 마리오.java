import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int s = 0;

        for (int i = 0; i < 10; i++) {
            s += arr[i];

            if (s >= 100) {
                if (Math.abs(100 - (s - arr[i])) < Math.abs(100 - s)) {
                    System.out.println(s - arr[i]);
                } else {
                    System.out.println(s);
                }

                return;
            }
        }

        System.out.println(s);
    }
}
