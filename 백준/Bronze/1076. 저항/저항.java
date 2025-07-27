import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();

        map.put("black", 0);
        map.put("brown", 1);
        map.put("red", 2);
        map.put("orange", 3);
        map.put("yellow", 4);
        map.put("green", 5);
        map.put("blue", 6);
        map.put("violet", 7);
        map.put("grey", 8);
        map.put("white", 9);

        StringBuilder sb = new StringBuilder();
        sb.append(map.get(br.readLine()) * 10 + map.get(br.readLine()));

        int r = map.get(br.readLine());

        for (int i = 0; i < r; i++) {
                sb.append('0');
        }

        System.out.println(Long.parseLong(sb.toString()));
    }
}
