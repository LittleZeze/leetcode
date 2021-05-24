//找出数组中重复的数字。 
//
// 
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
//找出数组中任意一个重复的数字。 
//
// 示例 1： 
//
// 输入：
//[2, 3, 1, 0, 2, 5, 3]
//输出：2 或 3 
// 
//
// 
//
// 限制： 
//
// 2 <= n <= 100000 
// Related Topics 数组 哈希表 
// 👍 422 👎 0


import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution03 {
    public int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                return n;
                // map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }

        return -1;
    }

   /* public static void main(String[] args) {
        int[] nums = new int[] {2, 3, 1, 0, 2, 5, 3};
        findRepeatNumber(nums);
    }*/

}
//leetcode submit region end(Prohibit modification and deletion)
