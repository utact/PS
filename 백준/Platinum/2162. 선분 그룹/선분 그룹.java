import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Line {
        Point p1, p2;

        Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    static int[] parent; // 분리 집합의 부모 노드
    static int[] gSize; // 각 그룹의 크기

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        parent = new int[N];
        gSize = new int[N];
        Line[] lines = new Line[N];

        // 각 선분은 자기 자신을 부모로 가지는 별개의 그룹으로 초기화
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            gSize[i] = 1;
            st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            lines[i] = new Line(new Point(x1, y1), new Point(x2, y2));
        }

        // 모든 선분 쌍에 대한 교차 검사
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // 두 선분이 교차하면 union
                if (isIntersect(lines[i], lines[j])) {
                    union(i, j);
                }
            }
        }

        int gCnt = 0;
        int maxSize = 0;
        for (int i = 0; i < N; i++) {
            // 부모가 자기 자신 -> 하나의 그룹
            if (parent[i] == i) {
                gCnt++;
                maxSize = Math.max(maxSize, gSize[i]);
            }
        }

        System.out.println(gCnt);
        System.out.println(maxSize);
    }

    // Counter-Clockwise 메서드
    public static int ccw(Point p1, Point p2, Point p3) {
        long result = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
        // 1 (반시계), -1 (시계), 0 (일직선)
        if (result > 0) return 1;
        if (result < 0) return -1;
        return 0;
    }

    // 교차 여부 판별 메서드
    public static boolean isIntersect(Line l1, Line l2) {
        Point p1 = l1.p1, p2 = l1.p2;
        Point p3 = l2.p1, p4 = l2.p2;

        int ccw123 = ccw(p1, p2, p3);
        int ccw124 = ccw(p1, p2, p4);
        int ccw341 = ccw(p3, p4, p1);
        int ccw342 = ccw(p3, p4, p2);

        boolean isParallel = (ccw123 * ccw124 == 0) && (ccw341 * ccw342 == 0);

        // 일직선 상에 있는 경우 -> 겹치는지 체크
        if (isParallel) {
            // x, y 좌표를 비교하기 편하도록 정렬
            if (Math.min(p1.x, p2.x) > Math.max(p3.x, p4.x) || Math.max(p1.x, p2.x) < Math.min(p3.x, p4.x) ||
                    Math.min(p1.y, p2.y) > Math.max(p3.y, p4.y) || Math.max(p1.y, p2.y) < Math.min(p3.y, p4.y)) {
                return false;
            }
        }

        // 일반적인 교차 조건
        return (ccw123 * ccw124 <= 0) && (ccw341 * ccw342 <= 0);
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // 더 작은 루트를 부모로 합침
            if (rootX < rootY) {
                parent[rootY] = rootX;
                gSize[rootX] += gSize[rootY];
            } else {
                parent[rootX] = rootY;
                gSize[rootY] += gSize[rootX];
            }
        }
    }
}