//输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
//
// 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7]
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
// Related Topics 树 递归 
// 👍 453 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution07 {

    private Map<Integer, Integer> map;
    public TreeNode reverseTreeNode(int[] preorder, int preorderLeft, int preorderRight,
                                    int[] inorder, int inorderLeft, int inorderRight) {

        // 递归结束条件
        if (preorderLeft > preorderRight) {
            return null;
        }

        // 根节点的值就是preorder[preorderLeft]
        int rootValue = preorder[preorderLeft];
        // 找到根节点在inorder数组中的下标
        int inorderRootLocation = map.get(rootValue);

        // 构造根节点
        TreeNode root = new TreeNode(rootValue);

        // 根据inorderRootLocation计算出左子树和右子树的长度
        int leftTreeSize = inorderRootLocation - inorderLeft;
        int rightTreeSize = inorderRight - inorderRootLocation;

        // 递归左构造树
        root.left = reverseTreeNode(preorder,preorderLeft + 1, preorderLeft + leftTreeSize,
                inorder, inorderLeft, inorderRootLocation - 1);

        // 构造右子树
        root.right = reverseTreeNode(preorder, preorderLeft + leftTreeSize + 1, preorderRight,
                inorder, inorderRootLocation + 1, inorderRight);

        return root;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        int len = preorder.length;
        map = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }

        return reverseTreeNode(preorder, 0, len - 1,
                inorder, 0, len - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
}

class Test {

    public static void main(String[] args) {
        int[] preorder = new int[] {3,9,20,15,7};
        int[] inorder = new int[] {9,3,15,20,7};
        Solution07 solution = new Solution07();
        solution.buildTree(preorder, inorder);
    }
}