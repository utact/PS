import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Character> ioi = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ioi.add('I');
        while (N-- > 0) {
            ioi.add('O');
            ioi.add('I');
        }

        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int count = 0;
        int index = 0;

        for (int i = 0; i < M; i++) {
            if (S.charAt(i) == ioi.get(index)) {
                index++;
            } else {
                if (index > 0) {
                    i--;
                }
                index = 0;
            }
            if (index == ioi.size()) {
                count++;
                i = i - (ioi.size() - 2);
                index = 0;
            }
        }

        System.out.println(count);
    }
}