//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 示例 1： 
//
// 输入：n = 2
//输出：2
// 
//
// 示例 2： 
//
// 输入：n = 7
//输出：21
// 
//
// 示例 3： 
//
// 输入：n = 0
//输出：1 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/ 
//
// 
// Related Topics 递归 
// 👍 166 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution10_2 {
    public int numWays(int n) {

        // 空间优化
        if (n == 0 || n == 1) {
            return 1;
        }

        int a = 1, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            b = a;
            a = sum;
        }
        return sum;

        /*if (n == 0 || n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= (Math.pow(10, 9) + 7);
        }
        return dp[n];*/
    }
}
//leetcode submit region end(Prohibit modification and deletion)
