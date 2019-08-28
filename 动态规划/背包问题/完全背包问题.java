package 背包问题;
/*
* 题目及评测链接： https://www.acwing.com/problem/content/3/
* 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
第 i 种物品的体积是 vi，价值是 wi。
求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
输出最大价值。
输入格式
第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 种物品的体积和价值。
输出格式
输出一个整数，表示最大价值。
数据范围
0<N,V≤1000
0<vi,wi≤1000
输入样例
4 5
1 2
2 4
3 4
4 5
输出样例：
10
* */
/*
 *
 * */

import java.util.Scanner;

public class 完全背包问题 {
    final int N = 1010;//物品的数目

    int n, m;//n表示物品数目，m表示背包容量
    int[] v = new int[N];//v[i] 表示第 i 个物品的体积
    int[] w = new int[N];//w[i] 表示第 i 个物品的价值
    //二维矩阵解法需要用到的状态矩阵
    int[][] f = new int[N][N];//f[i][j] 表示f(i,j)
    //一维矩阵解法需要用到的状态矩阵
    int[] g = new int[N];

    void initialization() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();//物品数目
        m = sc.nextInt();//背包体积
        //物品编号从下标1开始，保存在数组中也从1开始
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
    }

    //朴素版的完全背包问题解法，使用了三重循环，效率很低
    int dfs_row() {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //状态转移方程f[i,j] = max(f[i-1][j], f[i-1][j-v[i]]+w[i], f[i-1][j-2*v[i]]+2*w[i], ... , f[i-1][j-k*v[i]]+k*w[i])
                //对k循环实现上面的这个方程
                for (int k = 0; k * v[i] <= j; k++)
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - k * v[i]] + k * w[i]);
            }
        }
        return f[n][m];
    }

    //改进版的完全背包问题，状态转移方程由f[i,j] = max(f[i-1][j], f[i-1][j-v[i]]+w[i], f[i-1][j-2*v[i]]+2*w[i], ... , f[i-1][j-k*v[i]]+k*w[i])
    //优化成为f[i,j] = max(f[i-1][j],f[i][j-v[i]]+w[i])
    //优化过程见Overview.md
    int dfs_better() {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= v[i])
                    f[i][j] = Math.max(f[i][j], f[i][j - v[i]] + w[i]);
            }
        }
        return f[n][m];
    }

    //使用一维滚动数组优化，注意完全背包的内循环转移方向是从左往右更新的。（分析过程见Overview.md）
    //完全背包问题最终版本
    int dfs_best(){
        for(int i = 1; i<=n;i++){
            for(int j = v[i]; j<= m; j++)
                g[j] = Math.max(g[j],g[j-v[i]]+w[i]);
        }
        return g[m];
    }

    public static void main(String[] args) {
        完全背包问题 s = new 完全背包问题();
        s.initialization();
        int res = s.dfs_row();
        System.out.println(res);
    }
}

