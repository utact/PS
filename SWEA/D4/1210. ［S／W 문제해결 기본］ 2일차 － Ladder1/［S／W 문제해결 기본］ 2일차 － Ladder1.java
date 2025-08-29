import java.io.*;
import java.util.*;
 
public class Solution {
    static StringBuilder sb = new StringBuilder();
    static final int TESTCASE = 10;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        for (int i = 0; i < TESTCASE; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[100][100];
             
            for (int r = 0; r < map.length; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                 
                for (int c = 0; c < map[0].length; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }
             
            ArrayList<Integer> list = new ArrayList<>();
            int x = 0;
             
            for (int j = 0; j < map[0].length; j++) {
                if (map[99][j] != 0) list.add(j);
                if (map[99][j] == 2) x = j;
            }
             
            sb.append('#').append(N).append(' ').append(go(map, x, list)).append('\n');
        }
         
        System.out.print(sb);
    }
     
    static int go(int[][] map, int x, List<Integer> list) {
        int[] cur = {99, x};
        int ansIdx = 0;
         
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == x) {
                ansIdx = i;
                break;
            }
        }
         
        while (cur[0] > 0) {
            if (cur[1] - 1 >= 0 && map[cur[0]][cur[1] - 1] == 1) {
                cur = new int[] {cur[0], list.get(--ansIdx)};
            } else if (cur[1] + 1 < map[0].length && map[cur[0]][cur[1] + 1] == 1) {
                cur = new int[] {cur[0], list.get(++ansIdx)};
            }
             
            cur = new int[] {cur[0] - 1, cur[1]};
        }
         
        return cur[1];
    }
     
}