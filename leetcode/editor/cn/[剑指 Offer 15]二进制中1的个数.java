//请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 
//9，则该函数输出 2。 
//
// 
//
// 示例 1： 
//
// 
//输入：00000000000000000000000000001011
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
// 
//
// 示例 2： 
//
// 
//输入：00000000000000000000000010000000
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
// 
//
// 示例 3： 
//
// 
//输入：11111111111111111111111111111101
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。 
//
// 
//
// 提示： 
//
// 
// 输入必须是长度为 32 的 二进制串 。 
// 
//
// 
//
// 注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/ 
// Related Topics 位运算 
// 👍 117 👎 0


import java.util.stream.IntStream;
import java.util.stream.Stream;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution15 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            // n & 1, 若n最右为1，则结果为1，否则为0
            ans += n & 1;
            n = n >>> 1;
        }
        System.out.println(ans);
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Main15 {

    public static void main(String[] args) {
        int n = 00000000000000000000000000001011;

        int ans = Integer.toBinaryString(n).chars().map(ch -> ch - '0').sum();
        System.out.println(ans);

    }

}