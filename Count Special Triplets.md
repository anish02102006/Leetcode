# prefix sum of freq 2 passes->1 pass|beats 100%

# Intuition
Combinatorial problem, use the formula
cnt=∑ 
i=0..n−1,x=nums[i]
​
 prev[2x](freq[2x]−prev[2x]−(x==0))
where freq[x]= freq of x in nums&
prev[x]= the freq of x before the index i for current value nums[i]=x.

Note that [0]*N is the edge case
implementation: C, C++, Java, Python, Rust

# Approach
let mod=1e9+7 as int

let int M=100001, n=|nums|

Declare the arrays freq[M]={0}, prev[M]={0} filled with 0

count freq[x]++ for x in nums

Initialize cnt=0 as long long to avoid of overflowing & to save modular reductions

Set prev[nums[0]]++
Proceed the loop as follows:

for(int i=1; i<n-1; i++){
    const int x=nums[i], x2=x<<1;// set x, x2=x*2
    if (x2<M)// x2>=M is outside the boundary of the array prev[]
        //x==0 is evil which is the edge case
        // where prev[x2] is the number of freq of x2 before index i
        // freq[x2]-prev[x2]-(x==0) is the one of freq of x2 after i
        cnt+=(long long)prev[x2]*(freq[x2]-prev[x2]-(x==0));
    prev[x]++;// add 1 to prev[x]
}

return cnt%mod which the unique one operation for modular reduction.

Add a Python code which uses Counter

Add a C code whose complier is GCC with the elapsed time 33ms; when the arrays freq[], prev[] are put as global variables, the elapsed time is reduced to 15ms.

Add a Java code whose complier is supposed to be GCJ with the elapsed time 29ms

Add a Rust code with slight modification with freq[x]-=1 when it is used.

Thank to @hj-core mentioning the possiblity of 1-pass code, add a 1-pass solution. Use freq[x]=∣{j:nums[j]=x,i<k}∣ & Prev[x]=∣{(i,j):nums[i]=2x,nums[j]=x,i<j<k}∣ where k is the current index.
# Complexity
Time complexity:
O(n)

Space complexity:
O(M) where M=max(nums)

# Code
class Solution {


    final static int mod=(int)1e9+7, M=(int)1e5+1;
    public int specialTriplets(int[] nums) {
        final int  n=nums.length;
        int [] freq=new int[M];
        int [] prev=new int[M];
        for(int x: nums) freq[x]++;
        long cnt=0;
        prev[nums[0]]++;
        for(int i=1; i<n-1; i++){
            final int x=nums[i], x2=x<<1;
            if (x2<M)
                cnt+=(long)prev[x2]*(freq[x2]-prev[x2]-(x==0?1:0));
            prev[x]++;
        }
        return (int)(cnt%mod);
    }
}
