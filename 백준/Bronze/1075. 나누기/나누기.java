import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()) / 100 * 100;
        int F = Integer.parseInt(br.readLine());

        for (int i = 0; i < 100; i++) {
            if ((N + i) % F == 0) {
                if (i < 10) {
                    System.out.println("0" + i);
                    break;
                }
                System.out.println(i);
                break;
            }
        }
    }
}
