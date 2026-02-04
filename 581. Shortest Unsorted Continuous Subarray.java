// Two pointers approach


public static int findUnsortedSubarray(int[] arr) {

        // Lets visualize whole solution

        // Array ->  2  4  1  8  6  7
        // Index ->  0  1  2  3  4  5
        //           s              e

        int start =0;
        int end = arr.length-1;

        //Step 1 : Find Start of unsorted array

        // While loop until your current element <= next element.


        while (start+1<arr.length && arr[start] <= arr[start+1])
        {
            start++;
        }

        // It will look like that
        // Array ->  2  4  1  8  6  7
        // Index ->  0  1  2  3  4  5
        //              s           e

        //If by any chance s  points to last index that means array is sorted.
        // Hence return 0.
        if(start == arr.length-1)
        {
            return 0;
        }

        //Step 2 : Find end of unsorted array

        while (end-1>=0 && arr[end-1] <= arr[end])
        {
            end--;
        }

        // It will look like that
        // Array ->  2  4  1  8  6  7
        // Index ->  0  1  2  3  4  5
        //              s        e

        // if we sort our s and e.
        //It will look like
        
        // 2 1 4 6 8 7
        
        //If we see clearly it is still not sorted.
        
        //Step 3 : Find Min and Max of Sub-array.
        
        //For handling that case we will find min and max value of sub-array.
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = start; i <=end ; i++) {
            min=Math.min(min,arr[i]);
            max = Math.max(max,arr[i]);
        }
        // 1 4 6 8
        
        //Min = 1 
        //Max = 8

        // 2 1 4 6 8 7
        //   s     e
        
        // Step 4 : Check for element greater than sub-array min.
        
        //We will go back and see if elements that are previous from start is greater than min 
        //That means they are also the part of sub-array.
        
        while (start -1 >=0 && arr[start-1] > min)
        {
            start--;
        }
        
        //Because 2 is less than 1 .Start decreases
        
        // 2 1 4 6 8 7
        // s       e

        // Step 5 : Check for element lesser than sub-array max.

        //We will go forward and see if elements that are ahead of end is lesser than max 
        //That means they are also the part of sub-array.
        
        while(end+1<arr.length && arr[end+1]<max)
        {
            end++;
        }
        
        //Because 7 is less than 8 .end increases

        // 2 1 4 6 8 7
        // s         e
        
        //Now if we sort s and e array will look like this
        //1 2 4 6 7 8
        //0 1 2 3 4 5
        //s         e

        // 5 - 0 + 1;
        
        //Q : Why +1?
        //A : Because of 0 Based Indexing.
        return end-start+1;
    }
