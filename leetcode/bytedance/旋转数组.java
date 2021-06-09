package bytedance;

import java.util.Arrays;

public class 旋转数组 {

    public static void main(String[] args) {

        int[] nums = new int[] {1, 0, 1, 1, 1};
        int[] nums2 = new int[] {2,5,6,0,0,1,2};
        boolean ans = search(nums2, 3);
        System.out.println(ans);
    }

    public static boolean search(int[] nums, int target) {
        Arrays.sort(nums);
        return binSearch(nums, 0, nums.length - 1, target);
    }

    public static boolean binSearch(int[] nums, int left, int right, int target) {
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int midLocation = (left + right) / 2;
        int midValue = nums[midLocation];
        if (midValue == target) {
            return true;
        }
        if (left < right && midValue > target) {
            return binSearch(nums, left, midLocation - 1, target);
        }
        if (left < right && midValue < target) {
            return binSearch(nums, midLocation + 1, right, target);
        }
        return false;
    }

}


