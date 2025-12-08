class Solution {
    public int countTriples(int n) {
        int tripleCount = 0;

        // Iterate through all possible values for 'a'
        for (int a = 1; a < n; a++) {
            //iterate throgh all possible values for 'b'
            for (int b = 1; b < n; b++) {
                //calculate the sum of sqaures: a^2 + b^2
                int sumOfSquares = a * a + b * b;

                //calculate the potential values of c using square root
                int c = (int) Math.sqrt(sumOfSquares);

                //check if c is within bounds and fores a perfect square 
                // c^2 == sumofsquares ensure it is a pythagoreous theorem
                if (c <= n && c * c == sumOfSquares) {
                    tripleCount++;
                }
            }
        }
        return tripleCount;
    }
}
