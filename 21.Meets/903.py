#  https://www.lintcode.com/problem/903/description

class Solution:
    """
    @param length: the length of the array
    @param updates: update operations
    @return: the modified array after all k operations were executed
    """
    def get_modified_array(self, length, updates):
        # Write your code here
        A = [0] * length;
        for x in updates:
            startIndex = x[0]
            endIndex = x[1]
            value = x[2]

            if startIndex > endIndex:
                continue
            if startIndex >= length:
                continue
            if endIndex < 0:
                continue

            if startIndex < 0:
                startIndex = 0
            A[startIndex] += value

            if endIndex + 1 < length:
                A[endIndex + 1] -= value

        pre = 0
        for i in range(0, length):
            pre += A[i]
            A[i] = pre

        return A

