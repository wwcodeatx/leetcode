class Solution {
    public int maxArea(int[] height) {
        // two pointer - left and right
        int left = 0;
        int right = height.length - 1;
        
        // max water that can be stored
        int water = 0;
        
        // move in the one that is smaller and keep calculating the water
        while (left < right) {
            int currWater = (right - left) * Math.min(height[left], height[right]);
            water = Math.max(water, currWater);
            
            // move left
            if (height[right] < height[left]) {
                right--;
            } else {
                // move right
                left++;
            }
        }
        
        return water;
    }
}
