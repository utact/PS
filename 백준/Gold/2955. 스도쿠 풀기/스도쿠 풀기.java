import java.io.*;

public class Main {
    static char[][] map = new char[9][9];
    static boolean isErr = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 입력된 스도쿠 검증
        if (!isOk()) {
            System.out.println("ERROR");
            return;
        }

        // Cross-hatching 반복
        while (true) {
            boolean isChanged = false;

            // 1부터 9까지의 숫자를 순회
            for (char n = '1'; n <= '9'; n++) {
                // 9개의 3x3 박스를 순회
                for (int r = 0; r < 9; r += 3) {
                    for (int c = 0; c < 9; c += 3) {

                        // 현재 박스에 시도
                        boolean placed = tryNum(n, r, c);
                        if (placed) {
                            isChanged = true;
                        }

                        if (isErr) {
                            System.out.println("ERROR");
                            return;
                        }
                    }
                }
            }

            if (!isChanged) {
                break;
            }
        }

        printMap();
    }

    private static boolean tryNum(char num, int sr, int sc) {
        boolean isExist = false;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (map[sr + r][sc + c] == num) {
                    isExist = true;
                    break;
                }
            }
        }

        if (isExist) {
            return false;
        }

        int possCnt = 0;
        int lastPossR = -1;
        int lastPossC = -1;

        for (int r_offset = 0; r_offset < 3; r_offset++) {
            for (int c_offset = 0; c_offset < 3; c_offset++) {
                int curR = sr + r_offset;
                int curC = sc + c_offset;

                if (map[curR][curC] == '.') {
                    if (isPoss(num, curR, curC)) {
                        possCnt++;
                        lastPossR = curR;
                        lastPossC = curC;
                    }
                }
            }
        }

        if (possCnt == 0) { // 숫자가 박스에 없는데 들어갈 곳도 없는 경우 -> ERROR
            isErr = true;
            return false;
        } else if (possCnt == 1) { // 후보지가 유일한 경우
            map[lastPossR][lastPossC] = num;
            return true;
        }

        // 후보지가 여러 개인 경우 -> ERROR
        return false;
    }

    private static boolean isPoss(char num, int r, int c) {
        for (int i = 0; i < 9; i++) {
            if (map[r][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (map[i][c] == num) {
                return false;
            }
        }

        return true;
    }

    private static boolean isOk() {
        for (int i = 0; i < 9; i++) {
            boolean[] rCheck = new boolean[10];
            boolean[] cCheck = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (map[i][j] != '.') {
                    int num = map[i][j] - '0';
                    if (rCheck[num]) return false; // 중복
                    rCheck[num] = true;
                }

                if (map[j][i] != '.') {
                    int num = map[j][i] - '0';
                    if (cCheck[num]) return false; // 중복
                    cCheck[num] = true;
                }
            }
        }

        // 3x3 박스 검사
        for (int br = 0; br < 9; br += 3) {
            for (int bc = 0; bc < 9; bc += 3) {
                boolean[] boxCheck = new boolean[10];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        if (map[br + r][bc + c] != '.') {
                            int num = map[br + r][bc + c] - '0';
                            if (boxCheck[num]) return false; // 중복
                            boxCheck[num] = true;
                        }
                    }
                }
            }
        }

        return true;
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            sb.append(map[i]).append('\n');
        }
        System.out.print(sb);
    }
}