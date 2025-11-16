class Solution {
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);
        int i = 1;
        while (set.contains(i)) i++;
        return i;
    }
}
