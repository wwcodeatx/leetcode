class Solution:
    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        triplet_count = 0

        for num in nums:
            if num - diff in nums and num + diff in nums:
                triplet_count += 1

        return triplet_count