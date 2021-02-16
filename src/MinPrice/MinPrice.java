package MinPrice;

import java.awt.*;
import java.util.Arrays;

public class MinPrice {
    static int run = 0;
    public static void main(String[] args) {
        Node[][] mat = {
                {new Node(1, 5), new Node(4, 1), new Node(0, 6), new Node(4, 2)},
                {new Node(4, 7), new Node(2, 5), new Node(0, 3), new Node(1, 5)},
                {new Node(1, 0), new Node(2, 0), new Node(0, 0), new Node(6, 3)},
                {new Node(1, 1), new Node(5, 2), new Node(1, 1), new Node(1, 1)}
        };

        System.out.println(minPrice(mat));
        System.out.println(minPriceBetweenTwoPoints(mat, new Point(1, 5), new Point(2, 5)));
    }

    /**
     * O(n*m)
     */
    public static String minPrice(Node[][] mat) {
        int n = mat.length, m = mat[0].length;
        mat[0][0].price = 0;
        mat[0][0].numOfPaths = 1;

        //init the row and col
        for (int i = 1; i < Math.max(m, n); i++) {
            if (m > i) {
                mat[0][i].price = mat[0][i - 1].price + mat[0][i - 1].x;
                mat[0][i].numOfPaths = 1;
            }
            if (n > i) {
                mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].y;
                mat[i][0].numOfPaths = 1;
            }
        }
        PrintMatrix(mat);

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int fromDown = mat[i - 1][j].price + mat[i - 1][j].y;
                int fromLeft = mat[i][j - 1].price + mat[i][j - 1].x;
                mat[i][j].price = Math.min(fromDown, fromLeft);
                if (fromDown < fromLeft) {
                    mat[i][j].numOfPaths = mat[i - 1][j].numOfPaths;
                } else if (fromDown > fromLeft) {
                    mat[i][j].numOfPaths = mat[i][j - 1].numOfPaths;
                } else {
                    mat[i][j].numOfPaths = mat[i - 1][j].numOfPaths + mat[i][j - 1].numOfPaths;
                }
            }
        }
        System.out.println("the price is: " + mat[n - 1][m - 1].price);
        System.out.println("the number of paths is: " + mat[n - 1][m - 1].numOfPaths);
        return path(mat);
    }

    /**
     * O(P) : P = the length of the path
     **/
    private static String path(Node[][] mat) {
        int i = mat.length - 1, j = mat[0].length - 1;
        String ans = "";
        while (i != 0 && j != 0) {
            if (mat[i - 1][j].price + mat[i - 1][j].y < mat[i][j - 1].price + mat[i][j - 1].x) {
                ans = checkRun(ans,"down");
                i--;
            }else {
                ans = checkRun(ans,"right");
                j--;
            }
            run++;
        }
        while (i != 0) {
            ans = "down -> " + ans;
            i--;
        }
        while (j != 0) {
            ans = "right -> " + ans;
            j--;
        }
        return ans;
    }

    private static String checkRun(String ans, String str) {
        if (run == 0) {
            ans = str + " " + ans;
        } else {
            ans = str + " -> " + ans;
        }
        return ans;
    }

    /**
     * min Price Between Two Points from Tests
     */
    public static int minPriceBetweenTwoPoints(Node[][] mat, Point p1, Point p2) {
        int n = p2.y - (p1.y + 1), m = p2.x - (p1.x + 1);
        mat[p1.y][p1.x].price = 0;
        for (int i = p1.y + 1; i < p1.y + n; i++) {
            mat[i][p1.x].price = mat[i - 1][p1.x].price + mat[i - 1][p1.x].y;
        }
        for (int i = p1.x + 1; i < p1.x + m; i++) {
            mat[p1.y][i].price = mat[p1.y][i - 1].price + mat[p1.y][i - 1].x;
        }
        for (int i = p1.y + 1; i < p1.y + n; i++) {
            for (int j = p1.x + 1; j < p1.x + m; j++) {
                mat[i][j].price = Math.min(mat[i - 1][j].price + mat[i - 1][j].y, mat[i][j - 1].price + mat[i][j - 1].x);
            }
        }
        return mat[p2.y][p2.x].price;
    }

    /**
     * check if Two points are in the minimum path
     */
    public static boolean isOnMinPath(Node[][] mat, Point p1, Point p2) {
        if (p2.x <= p1.x && p2.y <= p1.y) {
            Point t = p1;
            p1 = p2;
            p2 = t;
        }
        if (p1.x <= p2.x && p1.y <= p2.y) {
            int allPrice = minPriceBetweenTwoPoints(mat, new Point(0, 0), new Point(mat.length - 1, mat[0].length - 1));
            int toP1 = minPriceBetweenTwoPoints(mat, new Point(0, 0), p1);
            int p1toP2 = minPriceBetweenTwoPoints(mat, p1, p2);
            int p2to = minPriceBetweenTwoPoints(mat, p2, new Point(mat.length - 1, mat[0].length - 1));
            if (allPrice == toP1 + p1toP2 + p2to) return true;
            else return false;
        } else return false;
    }

    /**
     * Print Matrix
     */
    public static void PrintMatrix(Node[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.println(i+" => " + Arrays.deepToString(mat[i]));
        }
    }

}

class Node {
    int x, y, price, numOfPaths;

    public Node(int x, int y) {
        this.x = x;// right
        this.y = y;// up
    }

    @Override
    public String toString() {
        return "" + price;
    }
}
