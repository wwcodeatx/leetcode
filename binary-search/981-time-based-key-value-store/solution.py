import math

class TimeMap:

    def __init__(self):
        # Initialize a map that holds the key -> [(timestamp, value)]
        # Map Key -> List[(timestamp, value)])
        self.map = {}
        

    def set(self, key: str, value: str, timestamp: int) -> None:
        # If the map doesn't have the key, add a key pointing to a list
        if not key in self.map:
            self.map[key] = []
        
        # Add the new (timestamp, value) pair
        self.map[key].append((timestamp, value))
        

    def get(self, key: str, timestamp: int) -> str:
        # If the key doesn't exist, return ""
        if not key in self.map:
            return ""
        
        array_of_pairs = self.map[key]
        
        # If the timestamp < smallest number in the list, return ""
        # Get the first element in the list and then get the 
        # timestamp for that
        # self.map[key] -> timestamp
        # Array of pairs 
        if timestamp < array_of_pairs[0][0]:
            return ""
        
        # Run a binary search and return the number we find
        low = 0
        high = len(array_of_pairs) - 1
        
        # [1 2 4 5]
        # Looking for 3
        # Low = 0, High = 3, Mid = (3+0)/2 = 1.5
        # CEILING -> Mid = 2
        # 3<4
        #
        while low < high:
            mid = math.ceil((low + high)/2)
            
            timestamp_at_midpoint = array_of_pairs[mid][0]
            
            # If we found the value, return it
            if timestamp_at_midpoint == timestamp:
                return array_of_pairs[mid][1]
            
            # If what we are looking for is less than the midpoint
            # Look for 2
            #  [1 2 3 4 5]
            
            # Low = 0 High = 4 
            
            # ROUND 1
            # Mid = (4+0)/2 = 2
            # Value_at_mid = 3
            # timestamp<mid 2<3
            # We want to look at [1 2 3] not [3 4 5]
            # high = mid-1
            
            elif timestamp < timestamp_at_midpoint:
                high = mid - 1
                
            # If the timestamp is greater than the timestamp_at_midpoint
            # timestamp_at_midpoint < timestamp 
            else:
                low = mid
                
        # If we haven't returned anything
        return array_of_pairs[low][1]
                
            
        
        


# Your TimeMap object will be instantiated and called as such:
# obj = TimeMap()
# obj.set(key,value,timestamp)
# param_2 = obj.get(key,timestamp)