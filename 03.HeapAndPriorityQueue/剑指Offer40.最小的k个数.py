import Queue
from Queue import PriorityQueue

class Solution(object):
    def getLeastNumbers(self, arr, k):
        """
        :type arr: List[int]
        :type k: int
        :rtype: List[int]
        """

        if k <= 0 or (not arr) or len(arr) == 0:
            return []
        
        Q = PriorityQueue()
        for x in arr:
            Q.put(-x)
            while Q.qsize() > k:
                Q.get()
        
        ans = []
        while not Q.empty():
            ans.append(-Q.get())
        return ans
