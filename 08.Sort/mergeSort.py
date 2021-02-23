class Solution(object):
    def mergeSort(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        if not nums or len(nums) == 0:
            return

        t = [0] * len(nums)

        def msort(a, b, e, t):
            if b >= e or b + 1 >= e:
                return

            m = b + ((e-b)>>1)
            msort(a, b, m, t)
            msort(a, m, e, t)

            i = b
            j = m
            to = b

            while i < m or j < e:
                if j >= e or (i < m and a[i] <= a[j]):
                    t[to] = a[i]
                    to += 1
                    i += 1
                else:
                    t[to] = a[j]
                    to += 1
                    j += 1

            for i in range(b, e):
                a[i] = t[i]
        msort(nums, 0, len(nums), t)
