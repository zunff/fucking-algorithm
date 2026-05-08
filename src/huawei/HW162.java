package huawei;

import java.util.*;

public class HW162 {

    /**
     * down[i][j] 表示方向向下时，从 (0,0) 到 (i,j) 最少需要多少个转向
     * right[i][j] 表示方向向右时，从 (0,0) 到 (i,j) 最少需要多少个转向
     * 初始化：
     *  down和 right都初始化为int_max
     *  down[0][0] = 0
     *  right[0][0] = 0
     *  down[i][0] = grid[i][0] == 0 ? down[i - 1][0] : int_max
     *  right[0][j] = grid[0][j] == 0 ? right[0][j - 1] : int_max
     *
     * {
     *     grid[i][j] == 0 时：
     *          down[i][j] = min (
     *              min(down[i - 1][j], right[i - 1][j] + 1)  // 从上面来
     *              min(down[i][j - 1] + 2, right[i][j - 1])  // 从左边来
     *          )
     *          right[i][j] = min(
     *              min(right[i - 1][j] + 2, down[i - 1][j] + 1)  // 从上面来
     *              min(right[i][j - 1], down[i - 1][j] + 1)   // 从左边来
     *          )
     * }
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        if (grid[0][0] != 0 || grid[n - 1][m - 1] != 0) {
            System.out.println("-1");
            return;
        }

        int[][] down = new int[n][m];
        int[][] right = new int[n][m];
        int INF = Integer.MAX_VALUE / 2;

        for (int i = 0; i < n; i++) {
            Arrays.fill(down[i], INF);
            Arrays.fill(right[i], INF);
        }
        down[0][0] = 0;
        right[0][0] = 0;
        for (int i = 1; i < n; i++) {
            if (grid[i][0] != 0) {
                break;
            }
            down[i][0] = 0;
        }
        for (int j = 1; j < m; j++) {
            if (grid[0][j] != 0) {
                break;
            }
            right[0][j] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }
                down[i][j] = Math.min(
                        Math.min(down[i - 1][j], right[i - 1][j] + 1),
                        Math.min(down[i][j - 1] + 2, right[i][j - 1] + 1)
                );
                right[i][j] = Math.min(
                        Math.min(down[i - 1][j] + 1, right[i - 1][j] + 2),
                        Math.min(down[i][j - 1] + 1, right[i][j - 1])
                );
            }
        }

        int ans = Math.min(right[n - 1][m - 1], down[n - 1][m - 1]);
        System.out.println(ans >= INF ? -1 : ans);



//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(grid[i][j] + "\t");
//            }
//            System.out.println();
//        }

    }
}
