/*
 * 00:28
 *
 * LinkedList: 삽입, 삭제
 */

import java.io.*;
import java.util.*;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static List<Integer> list = new LinkedList<>();
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        for (int i = 1; i <= 10; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                String cmd = st.nextToken();

                if (cmd.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());

                    for (int j = 0; j < y; j++) {
                        list.add(x++, Integer.parseInt(st.nextToken()));
                    }
                }

                else if (cmd.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());

                    for (int j = 0; j < y; j++) {
                        list.remove(x);
                    }
                }

                else if (cmd.equals("A")) {
                    int y = Integer.parseInt(st.nextToken());

                    for (int j = 0; j < y; j++) {
                        list.add(Integer.parseInt(st.nextToken()));
                    }
                }
            }

            sb.append('#').append(i).append(' ');
            for (int j = 0; j < 10; j++) {
                sb.append(list.get(j)).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}