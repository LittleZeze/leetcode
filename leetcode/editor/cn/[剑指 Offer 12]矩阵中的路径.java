//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/ 
// Related Topics 深度优先搜索 
// 👍 327 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution12 {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();

        // 深度优先
        // 查找开始位置
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                // 判断word是否存在矩阵中
                if (dfs(board, i, j, words, 0)) {
                    return true;
                }
            }

        return false;
    }

    public boolean dfs(char[][] board, int i, int j, char[] words, int k) {
        // 判断是否越界、字符是否相等
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != words[k]) {
            return false;
        }
        if (k >= words.length - 1) {
            return true;
        }

        // 当前字符与目标字符匹配，防止回溯，置为'\0'
        board[i][j] = '\0';

        // 搜索
        boolean res = dfs(board, i, j + 1, words, k + 1)
                || dfs(board, i + 1, j, words, k + 1)
                || dfs(board, i, j - 1, words, k + 1)
                || dfs(board, i - 1, j, words, k + 1);

        // 还原
        board[i][j] = words[k];

        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Main12 {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        String word = "ABCCED";
        Solution12 solution = new Solution12();
        boolean res = solution.exist(board, word);
        System.out.println(res);
    }

}