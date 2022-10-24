/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function(height) {
    var left = 0;
    var right = height.length - 1;
    var water = 0;
    
    while (left < right) {
        var currWater = (right - left) * Math.min(height[left], height[right]);
        water = Math.max(water, currWater);
        
        height[right] < height[left] ? right-- : left++;
    }
    
    return water;
};
