//striver
class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        ArrayList<int []> list = new ArrayList<>();
        Arrays.sort(intervals , (a,b) -> a[0]-b[0]);

        int left = intervals[0][0];
        int right = intervals[0][1];
        int max = right;
        int idx = 1;

        while(idx < n){
            int a = intervals[idx][0];
            int b = intervals[idx][1];

            if(a <= max){
                max = Math.max(b, max);
            }
            else {
                list.add(new int []{left , max});
                left = intervals[idx][0];
                max = intervals[idx][1];
            }
            idx++;
        }
        list.add(new int[]{left , max});
        return list.toArray(new int [0][]);
    }
}
