package com.meta;

import java.util.*;

/**
 * https://leetcode.com/problems/the-number-of-beautiful-subsets/description/
 * 2597. The Number of Beautiful Subsets Medium Accepted
 * You are given an array nums of positive integers and a positive integer k.
 * A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
 * Return the number of non-empty beautiful subsets of the array nums.
 * A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums.
 * Two subsets are different if and only if the chosen indices to delete are different.
 * Constraints:
 * 1 <= nums.length <= 20
 * 1 <= nums[i], k <= 1000
 * Output Limit Exceeded
 * 37 / 1308 testcases passed
 * Last Executed Input
 * Use Testcase
 * nums =
 * [57,33,26,76,14,67,24,90,72,37,30,83,12,44,87,16,19]
 * k =
 * 12
 */
public class BeautifulSubsets {

    public final static void main(String[] args) {
        BeautifulSubsets bs = new BeautifulSubsets();
        int[] nums = {2,4,6};
        int subsets = bs.beautifulSubsets(nums, 2);
        System.out.println(subsets);

    }

    public int beautifulSubsets(int[] nums, int k) {
        HashMap<Integer, Integer> tracker = new HashMap<Integer, Integer>();
        Counter counter = new Counter();
        dfs(nums, 0, k, tracker, counter);
        // if empty subset not count, then deduct 1
        return counter.count - 1;
    }

    private void dfs(int[] nums, int index, int k, Map<Integer, Integer> tracker, Counter counter) {

        if (index >= nums.length) {
            System.out.println(index + "-" + tracker);
            counter.count++;
            return;
        }

        // tree branch 1: exclude nums[index]
        dfs(nums, index+1, k, tracker, counter);

        // tree branch 2: include nums[index], check first
        boolean canInclude = (tracker.getOrDefault(nums[index] + k, 0 ) <= 0)
                && (tracker.getOrDefault(nums[index] - k, 0 ) <= 0);

        if( canInclude ) {
            tracker.put(nums[index], tracker.getOrDefault(nums[index], 0) + 1);
            dfs(nums, index+1, k, tracker, counter);
            tracker.put(nums[index], tracker.get(nums[index]) - 1);
        }

    }

    public static class Counter {
        public int count = 0;
    }


}
