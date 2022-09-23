import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    public int[][] merge(int[][] intervals) {
        // There should be checks but I'm not doing them now
        // Examples of checks:
        // 1. Make sure intervals has at least one element
        // 2. Make sure each interval has 2 values

        // Set up
        LinkedList<int[]> results = new LinkedList<>();
        // [1,3] --> [2,4] --> [6,7] --> [9,10]

        // results.getLast();

        // List<int[]> results2 = new ArrayList<>();

        // results2[results2.size() - 1];

        // (1) Sort the array
        // Note: Arrays.sort(...) sorts the intervals in place
        Arrays.sort(intervals, Arrays::compare);

        // (3) Run through each array
        for (int[] interval : intervals) {
            // (2) Add the first element
            // This is going to be done the first time
            if (results.isEmpty()) {
                results.add(interval);
                // If we get here, continue makes it skip the rest of this iteration of the for
                // loop
                // So essentially starting again at the top with the next interval
                continue;
            }

            // (a) if it overlaps with the most recent result, change the most recent result
            // to include the new elements
            // Example: if results = {[1,3] --> [2,4]}, it'll grab [2,4]
            int[] mostRecentResult = results.getLast();
            // Example: This is getting the 4 from [2,4]
            int endOfMostRecent = mostRecentResult[1];

            // Now get the starting int for the interval we're comparing
            int startOfComparing = interval[0];

            // This means they overlap
            if (endOfMostRecent >= startOfComparing) {
                int endOfComparing = interval[1];
                results.getLast()[1] = Math.max(endOfMostRecent, endOfComparing);
                // (b) otherwise, add the new element to the result
            } else {
                results.add(interval);
            }
        }

        return results.toArray(int[][]::new);

    }
}