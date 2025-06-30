import java.io.*;
import java.util.*;

public class Main {
    static int odr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        dvd(N, r, c);
        System.out.print(odr);
    }

    static void dvd(int N, int r, int c) {
        if (N == 1) {
            return;
        }

        int hf = N / 2;

        if (r < hf && c < hf) {
        } else if (r < hf) {
            odr += hf * hf;
            c -= hf;
        } else if (c < hf) {
            odr += hf * hf * 2;
            r -= hf;
        } else {
            odr += hf * hf * 3;
            r -= hf;
            c -= hf;
        }

        dvd(hf, r, c);
    }
}