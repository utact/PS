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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());
        Line l1 = new Line(new Point(x1, y1), new Point(x2, y2));

        st = new StringTokenizer(br.readLine());
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());
        Line l2 = new Line(new Point(x3, y3), new Point(x4, y4));

        if (isIntersect(l1, l2)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
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
}