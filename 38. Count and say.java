class Solution {
    /**
     * Generates the nth term of the Count-and-Say sequence.
     * The sequence starts with "1" and each subsequent term describes the previous term.
     * 
     * @param n The position in the sequence to generate (1-indexed)
     * @return The nth term of the Count-and-Say sequence
     */
    public String countAndSay(int n) {
        // Initialize with the first term of the sequence
        String currentSequence = "1";
      
        // Generate each subsequent term until we reach the nth term
        while (--n > 0) {
            StringBuilder nextSequence = new StringBuilder();
          
            // Process the current sequence to generate the next one
            for (int currentIndex = 0; currentIndex < currentSequence.length();) {
                // Find the end position of consecutive identical digits
                int endIndex = currentIndex;
                while (endIndex < currentSequence.length() && 
                       currentSequence.charAt(endIndex) == currentSequence.charAt(currentIndex)) {
                    endIndex++;
                }
              
                // Append the count of consecutive digits
                int count = endIndex - currentIndex;
                nextSequence.append(count);
              
                // Append the digit itself
                nextSequence.append(currentSequence.charAt(currentIndex));
              
                // Move to the next group of digits
                currentIndex = endIndex;
            }
          
            // Update the current sequence for the next iteration
            currentSequence = nextSequence.toString();
        }
      
        return currentSequence;
    }
}
