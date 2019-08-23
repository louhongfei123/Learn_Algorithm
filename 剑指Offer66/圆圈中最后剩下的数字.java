/*
* 题目：0，1，n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
* */

/*首先我们定义一个关于n和m的方程（n，m），表示每次在n个数字0，
1，n-1中删除第m个数字最后剩下的数字。
在这n个数字中，第一个被删除的数字是(m-1)%n。为了简单起见，我们把(m-1)%n记为k，那么删除k之后剩下的n-1个数字为0，1…，
k-1，k+1…，n-1，并且下一次删除从数字k+1开始计数。相当于在剩下的序列中，k+1排在最前面，从而形成k+1，n-1，0，1…k-1。
该序列最后剩下的数字也应该是关于n和m的函数。由于这个序列的规律和前面最初的序列不一样（最初的序列是从0开始的连续序列），
因此该函数不同于前面的函数，记为f'(n-1，m)。最初序列最后剩下的数字f(n，m)一定是删除一个数字之后的序列最后剩下的数字，
即f(n，m)=f(n-l，m)。

接下来我们把剩下的这n-1个数字的序列k+1…，n-1，0，1…k-1进行映射，映射的结果是形成一个0~n-2的序列。
k+1→0
k+2→1
...
n-1→n-k-2
0→n-k-1
1→n-k
...
k-1→n-2
我们把映射定义为p，则p(x)-(x-k-1)%n。它表示如果映射前的数字是x，那么映射后的数字是(x-k-1)%n。该映射的逆映射是
q(x)=(x+k+1)%n。由于映射之后的序列和最初的序列具有同样的形式，即都是从0开始的连续序列，因此仍然可以用函数f来表示，
记为f(n-1,m)。根据我们的映射规则，映射之前的序列中最后剩下的数字f'(n-1，m)=q[(n-1，m)]=[f(n-1，m）+k+1]%n，
把k=(m-1)%n代入得到f(n，m)=f'(n-1，m)=[f(n-1，m)+m]%n。

经过上面复杂的分析，我们终于找到了一个递归公式。要得到n个数字的序列中最后剩下的数字，只需要得到n-1个数字的序列中最
后剩下的数字，并以此类推。当n=1时，也就是序列中开始只有一个数字0，那么很显然最后剩下的数字就是0。我们把这种关系表示为：
    f(n, m)= 0                    当n = 1时
    f(n,m) = [f(n-1，m)+m]%n      当n > 1时
*/
public class 圆圈中最后剩下的数字 {
    public int LastRemaining_Solution(int n, int m) {
        if(n<1||m<2)
            return -1;
        int last = 0;
        for(int i = 2; i<=n;i++)
            last = (last + m)%i;
        return last;
    }
}
