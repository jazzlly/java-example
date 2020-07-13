package com.leetcode.tree.middle;

import com.leetcode.tree.TreeNode;
import com.leetcode.tree.utils.TreeUtils;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 *
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 *  
 *
 * 提示：
 *
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L863KthDistNode {

    /**
     * 1. 深度优先搜索建立node->parent的映射关系
     * 2. 广度优先搜索left, right, parent三个方向，将第K层所有节点加入结果集
     */
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();

        if (root == null || target == null || K < 0) {
            return ans;
        }

        buildParentMapDfs(root, null);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);

        int k = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (k == K) {
                for (int i = 0; i < size; i++) {
                    ans.add(queue.poll().val);
                }
                return ans;
            }

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null && !visited.contains(node.left)) {
                    queue.add(node.left);
                    visited.add(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    queue.add(node.right);
                    visited.add(node.right);
                }
                TreeNode parent = parentMap.getOrDefault(node, null);
                if (parent != null && !visited.contains(parent)) {
                    queue.add(parent);
                    visited.add(parent);
                }
            }
            k++;
        }

        return ans;
    }

    private void buildParentMapDfs(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        parentMap.put(node, parent);
        buildParentMapDfs(node.left, node);
        buildParentMapDfs(node.right, node);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.makeBinaryTree(Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                null, null, null, null, null, null, null, 13, null, null, 14));

        System.out.println(root.val);

        L863KthDistNode test = new L863KthDistNode();
//        test.buildParentMapDfs(root, null);
//        System.out.println(test.parentMap.toString());
         List<Integer> ans = test.distanceK(root, root.left, 2);
         System.out.println(ans);
    }

    // --------------------------------------------------------------------------
     /*
    简化： 先找到节点，然后向下递归K层
    找到子节点，并将路径放到一个队列中，并将方向信息保存在另外一个队列
    子问题： 向下某个方向找n层

     */
    LinkedList<TreeNode> ancestors = new LinkedList<>();
    LinkedList<Integer> directions = new LinkedList<>();
    public List<Integer> distanceK1(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();

        findTarget(root, target);
        int k = K;
        while (!ancestors.isEmpty() && !directions.isEmpty() && k >= 0) {
            TreeNode node = ancestors.removeLast();
            Integer direction = directions.removeLast();
            if (direction.equals(0)) {
                searchRecursion(node, true, true, k, ans);
            } else if (direction.equals(-1)) {
                searchRecursion(node, false, true, k, ans);
            } else {
                searchRecursion(node, true, false, k, ans);
            }
            k--;
        }

        return ans;
    }

    boolean findTarget(TreeNode parent, TreeNode target) {
        if (parent == null || target == null) {
            return false;
        }

        if (parent == target) {
            ancestors.addLast(target);
            directions.addLast(0);
            return true;
        }

        if (parent.left != null) {
            ancestors.addLast(parent);
            directions.addLast(-1);
            if (findTarget(parent.left, target)) {
                return true;
            }
            ancestors.removeLast();
            directions.removeLast();
        }
        if (parent.right != null) {
            ancestors.addLast(parent);
            directions.addLast(1);
            if (findTarget(parent.right, target)) {
                return true;
            }
            ancestors.removeLast();
            directions.removeLast();
        }

        return false;
    }

    void searchRecursion(TreeNode node, boolean left, boolean right,
                                int level, List<Integer> answer) {
        if (node == null || level < 0) {
            return;
        }
        if (level == 0) {
            answer.add(node.val);
            return;
        }
        if (left) {
            searchRecursion(node.left, true, true, level - 1, answer);
        }
        if (right) {
            searchRecursion(node.right, true, true,level - 1, answer);
        }
    }



}
