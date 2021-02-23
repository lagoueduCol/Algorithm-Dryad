class Solution(object):
    def reversePairs(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        if not nums or len(nums) == 0:
            return 0

        t = [0] * len(nums)
        cnt = [0]

        def msort(a, b, e, t, cnt):
            if b >= e or b + 1 >= e:
                return

            m = b + ((e-b)>>1)
            msort(a, b, m, t, cnt)
            msort(a, m, e, t, cnt)

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
                    cnt[0] += i - b

            for i in range(b, e):
                a[i] = t[i]
        msort(nums, 0, len(nums), t, cnt)

        return cnt[0]
