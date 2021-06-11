# 栈的操作

普通栈的性质

```cpp
// LeetCode 32
class Solution {
   public:
    int longestValidParentheses(string s) {
        int ans = 0, start = -1;
        stack<int> t;

        for (int i = 0; i < s.length(); i++) {
            const char c = s[i];
            if (c == ')') {
                if (t.empty()) {
                    start = i;
                    continue;
                } else {
                    t.pop();
                    const int b = t.empty() ? start : t.top();
                    ans = max(ans, i - b);
                }
            } else {
                t.push(i);
            }
        }

        return ans;
    }
};
```



单调栈的性质，找到右边比我小的数的位置

```Cpp
vector<int> findRightSmall(vector<int> &A) {
  if (A.empty()) {
    return {};
  }
  // 结果数组
  vector<int> ans(A.size());

  // 注意，栈中的元素记录的是下标
  stack<int> t;

  for (size_t i = 0; i < A.size(); i++) {
    const int x = A[i];
    // 每个元素都向左遍历栈中的元素完成消除动作
    while (!t.empty() && A[t.top()] > x) {
      // 消除的时候，记录一下被谁消除了
      ans[t.top()] = i;
      // 消除时候，值更大的需要从栈中消失
      t.pop();
    }
    // 剩下的入栈
    t.push(i);
  }
  // 栈中剩下的元素，由于没有人能消除他们，因此，只能将结果设置为-1。
  while (!t.empty()) {
    ans[t.top()] = -1;
    t.pop();
  }

  return ans;
}
```

单调栈的性质

```Cpp
// LeetCode 84
class Solution {
   public:
    int largestRectangleArea(vector<int>& A) {
        const int N = A.size();

        int ans = 0;
        stack<int> t;
        for (int i = 0; i <= N; i++) {
            const int x = i == N ? -1 : A[i];
            while (!t.empty() && A[t.top()] > x) {
                auto idx = t.top();
                t.pop();
                auto height = A[idx];
                const int rightPos = i;
                const int leftPos = t.empty() ? -1 : t.top();
                const int width = rightPos - leftPos - 1;
                const int area = height * width;
                ans = max(ans, area);
            }
            t.push(i);
        }

        return ans;
    }
};
```

# 队列

循环队列的写法，需要k+1个空间。

```Cpp

class MyCircularQueue {
    // 队列的头部元素所在位置
    int front = 0;
    // 队列的尾巴
    // 注意我们采用的是前开后闭原则
    // [front, rear)
    int rear = 0;
    vector<int> a;
    int capacity = 0;

   public:
    // 初始化队列，注意此时队列中元素个数为k+1
    MyCircularQueue(int k) : capacity(k + 1) { a.resize(k + 1); }

    bool enQueue(int value) {
        // 如果已经满了，无法入队
        if (isFull()) {
            return false;
        }
        // 把元素放到rear位置
        a[rear] = value;
        // rear向后移动
        rear = (rear + 1) % capacity;
        return true;
    }

    bool deQueue() {
        // 如果为空，无法出队
        if (isEmpty()) {
            return false;
        }
        // 出队之后，front要向前移
        front = (front + 1) % capacity;
        return true;
    }

    // 如果能取出第一个元素，取a[front];
    int Front() { return isEmpty() ? -1 : a[front]; }

    // 由于我们使用的是前开后闭原则
    // [front, rear)
    // 所以在取最后一个元素时，应该是
    // (rear - 1 + capacity) % capacity;
    int Rear() {
        int tail = (rear - 1 + capacity) % capacity;
        return isEmpty() ? -1 : a[tail];
    }

    // 队列是否为空
    bool isEmpty() { return front == rear; }

    // rear与front之间至少有一个空格
    // 当rear指向这个最后的一个空格时，
    // 队列就已经放满了!
    bool isFull() { return (rear + 1) % capacity == front; }
};
```

循环队列的第二种实现，采用used变量。

```Cpp

class MyCircularQueue {
    // 已经使用的元素个数
    int used = 0;
    // 第一个元素所在位置
    int front = 0;
    // rear是enQueue可在存放的位置
    // 注意开闭原则
    // [front, rear)
    int rear = 0;
    // 循环队列最多可以存放的元素个数
    int capacity = 0;
    // 循环队列的存储空间
    vector<int> a;

   public:
    MyCircularQueue(int k) {
        // 初始化循环队列
        capacity = k;
        a.resize(k);
    }

    bool enQueue(int value) {
        // 如果已经放满了
        if (used == capacity) {
            return false;
        }
        // 如果没有放满，那么a[rear]用来存放新进来的元素
        a[rear] = value;
        // rear注意取模
        rear = (rear + 1) % capacity;
        // 已经使用的空间
        used++;
        // 存放成功!
        return true;
    }

    bool deQueue() {
        // 如果是一个空队列，当然不能出队
        if (used == 0) {
            return false;
        }
        // 第一个元素取出
        int ret = a[front];
        // 注意取模
        front = (front + 1) % capacity;
        // 已经存放的元素减减
        used--;
        // 取出元素成功
        return true;
    }

    int Front() {
        // 如果为空，不能取出队首元素
        if (used == 0) {
            return -1;
        }
        // 取出队首元素
        return a[front];
    }

    int Rear() {
        // 如果为空，不能取出队尾元素
        if (used == 0) {
            return -1;
        }
        // 注意：这里不能使用rear - 1
        // 需要取模
        int tail = (rear - 1 + capacity) % capacity;
        return a[tail];
    }

    // 队列是否为空
    bool isEmpty() { return used == 0; }

    // 队列是否满了
    bool isFull() { return used == capacity; }
};
```

单调队列

```Cpp
// LeetCode 239
class Solution {
    // 单调队列使用双端队列来实现
    deque<int> Q;
    // 入队的时候，last方向入队，但是入队的时候
    // 需要保证整个队列的数值是单调的
    // (在这个题里面我们需要是递减的)
    // 并且需要注意，这里是Q.getLast() < val
    void push(int val) {
        while (!Q.empty() && Q.back() < val) {
            Q.pop_back();
        }
        // 将元素入栈
        Q.push_back(val);
    }

    // 出队的时候，要相等的时候才会出队
    void pop(int val) {
        if (!Q.empty() && Q.front() == val) {
            Q.pop_front();
        }
    }

   public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> ans;
        for (int i = 0; i < nums.size(); i++) {
            push(nums[i]);
            // 如果队列中的元素还少于k个
            // 那么这个时候，还不能去取最大值
            if (i < k - 1) {
                continue;
            }
            // 队首元素就是最大值
            ans.push_back(Q.front());
            // 尝试去移除元素
            pop(nums[i - k + 1]);
        }
        return ans;
    }
};
```

堆的c++实现

```Cpp
class Heap {
  public:
    vector<int> a;
    int n = 0;

    void sink(int i) {
        int j = 0;
        int t = a[i];
        while ((j = (i << 1) + 1) < n) {
            if (j < n - 1 && a[j] < a[j + 1]) {
                j++;
            }
            if (a[j] > t) {
                a[i] = a[j];
                i = j;
            } else {
                break;
            }
        }
        a[i] = t;
    }

    void swim(int i) {
        int par = 0;
        int t = a[i];
        while (i > 0) {
            par = (i - 1) >> 1;
            if (a[par] < t) {
                a[i] = a[par];
                i = par;
            } else {
                break;
            }
        }
        a[i] = t;
    }

    void push(int x) {
        a[n++] = x;
        swim(n - 1);
    }

    int pop() {
        int ret = a[0];
        a[0] = a[--n];
        sink(0);
        return ret;
    }

    int size() const { return n; }
};
```

# 链表

链表的几种基本操作

```Cpp
class MyLinkedList {
    struct ListNode {
        int val = 0;
        ListNode *next = nullptr;
        ListNode() {}
        ListNode(int x) { val = x; }
    };

    ListNode dummy;
    ListNode *tail = nullptr;
    int length = 0;

   public:
    /** Initialize your data structure here. */
    MyLinkedList() { tail = &dummy; }

    ListNode *getPreNode(int index) {
        auto front = dummy.next;
        auto back = &dummy;
        for (int i = 0; i < index; i++) {
            back = front;
            front = front->next;
        }
        return back;
    }

    // 获取链表中第 index 个节点的值。如果索引无效，则返回-1。
    int get(int index) {
        if (index < 0 || index >= length) {
            return -1;
        }
        return getPreNode(index)->next->val;
    }

    // 在链表的第一个元素之前添加一个值为 val 的节点。
    // 插入后，新节点将成为链表的第一个节点。
    void addAtHead(int val) {
        ListNode *p = new ListNode(val);
        p->next = dummy.next;
        dummy.next = p;
        // NOTE change tail
        if (tail == &dummy) {
            tail = p;
        }
        length++;
    }

    // 将值为 val 的节点追加到链表的最后一个元素。
    void addAtTail(int val) {
        tail->next = new ListNode(val);
        tail = tail->next;
        length++;
    }

    // 在链表中的第 index 个节点之前添加值为 val  的节点。
    // 1. 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
    // 2. 如果 index 大于链表长度，则不会插入节点。
    // 3. 如果index小于0，则在头部插入节点。
    void addAtIndex(int index, int val) {
        if (index > length) {
            return;
        } else if (index == length) {
            addAtTail(val);
            return;
        } else if (index <= 0) {
            addAtHead(val);
            return;
        }
        ListNode *pre = getPreNode(index);
        ListNode *p = new ListNode(val);
        p->next = pre->next;
        pre->next = p;
        // NOTE: here tail has been changed
        length++;
    }

    void deleteAtIndex(int index) {
        if (index < 0 || index >= length) {
            return;
        }
        ListNode *pre = getPreNode(index);

        // NOTE: delete -> change tail
        if (tail == pre->next) {
            tail = pre;
        }
        length--;

        pre->next = pre->next->next;
    }
};
```

链表的拆分：尽量分成前后长度相等的两段，如果长度为奇数，那么前面比后面长。

```Cpp
    ListNode* split(ListNode* head) {
        auto pre = head;
        auto s1 = head;
        auto s2 = head;

        while (s2 && s2->next) {
            pre = s1;
            s1 = s1->next;
            s2 = s2->next->next;
        }

        return s2 ? s1 : pre;
    }
```

链表的反转

```Cpp
    ListNode* reverse(ListNode* head) {
        ListNode dummy;

        auto p = head;

        while (p) {
            auto back = p->next;

            p->next = dummy.next;
            dummy.next = p;

            p = back;
        }

        return dummy.next;
    }
```



# 二叉树操作

## 后序遍历-栈

```Cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    vector<int> postorderTraversal(TreeNode* t) {
        vector<int> ans;
        TreeNode *pre = nullptr;
        stack<TreeNode*> s;
        while (!s.empty() || t) {
            while (t) {
                s.push(t);
                t = t->left;
            }
            t = s.top();
            if (!t->right || t->right == pre) {
                ans.push_back(t->val);
                s.pop();
                pre = t;
                t = nullptr;
            } else {
                t = t->right;
            }
        }
        return ans;
    }
};
```

## 后序遍历-栈 多叉树

```Cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

int __x__ = []() ->int{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    return 1010;
}();

class Solution {
public:
    vector<int> postorder(Node* root) {
        stack<pair<Node*,int>> s;
        Node *pre = nullptr;

        vector<int> ans;

        while (!s.empty() || root) {
            // 这里都是新节点入栈
            while (root) {
                s.push(make_pair(root, 0));
                
                auto &vs = root->children;
                root = nullptr;
                if (vs.size()) {
                    root = vs[0];
                }
            }
            
            auto p = s.top();
          
            root = p.first;
            auto &vs = root->children;
            auto idx = p.second;
						// 注意判断是否已经打印完
            if (vs.empty() || idx >= vs.size() || vs.back() == pre) {
                ans.push_back(root->val);
                s.pop();
                pre = root;
                root = nullptr;
            } else {
                s.top().second++; // 注意修改栈顶的索引
                root = root->children[idx+1];
            }
        }
        
        return ans;
    }
};
```



## 后序遍历-Morris

```Cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

// 实际上这里就是一个链表的反转，只不过这里需要记录下to->right
void reverse(TreeNode *from, TreeNode *to) {
    if (from == to) {
        return;
    }
    auto last = to->right;
    TreeNode dummy(0);
    auto p = from;
    dummy.right = last;
    
    while (p != last) {
        auto back = p->right;
        p->right = dummy.right;
        dummy.right = p;
        p = back;
    }
}

void print(TreeNode *from, TreeNode *to, vector<int> &ans) {
    auto last = to->right; // 记录下不打印的结点, 这个结点实际上是父结点
    reverse(from, to);
    
  	// 从头到尾打印链表
    auto p = to;
    while (p != last) {
        ans.push_back(p->val);
        p = p->right;
    }

    reverse(to, from);
}
class Solution {

public:
    vector<int> postorderTraversal(TreeNode* root) {
        vector<int> ans;
        TreeNode dummy(0);
        dummy.left = root;

        auto cur = &dummy;
        
        while (cur) {
            if (cur->left) {
                auto pre = cur->left;
                while (pre->right && pre->right != cur) {
                    pre = pre->right;
                }
                if (!pre->right) {
                    pre->right = cur;
                    cur = cur->left;
                } else {
                    // print cur->left to pre;
                    print(cur->left, pre, ans);
                    pre->right = nullptr;
                    cur = cur->right;
                }
            } else {
                cur = cur->right;
            }
        }
        
        return ans;
    }
};
```

## 二叉树字符串化

```Cpp
/**
 * 整个树的结构是x(left-tree)(right-tree)
 * 如果左子树为空，那么需要是x()(right-tree)
 * 如果右子树为空，那么可以省略，写成x(left-tree)
 * 如果为空树，返回""
 */
class Solution {
public:
    string tree2str(TreeNode* root) {
        if (!root) {
            return "";
        }
        if (!root->left && !root->right) {
            return to_string(root->val);
        }
        string ret = to_string(root->val) + "(" + tree2str(root->left) + ")";
        if (root->right) {
            ret = ret + "(" + tree2str(root->right) + ")";
        }
        return ret;
    }
};
```

##二叉树添加一层

```Cpp
class Solution {
public:
    TreeNode* addOneRow(TreeNode* root, int v, int d) {
        if (!root) {
            return new TreeNode(v);
        }
        
        if (d <= 1) {
            auto t = new TreeNode(v);
            t->left = root;
            return t;
        }
        
        vector<TreeNode*> cur{root};
        int level = 1;
        
        while (!cur.empty()) {
            vector<TreeNode*> tmp;
            
            if (level + 1 == d) {
                for (auto &t: cur) {
                		auto back = t->left;
                    t->left = new TreeNode(v);
                    t->left->left = back;
            
                    back = t->right;
                    t->right = new TreeNode(v);
                    t->right->right = back;
                }
                return root;
            } else {
                for (auto &t: cur) {
                    if (t->left) {
                        tmp.push_back(t->left);
                    }
                    if (t->right) {
                        tmp.push_back(t->right);
                    }
                }
            }
            
            level++;
            cur.swap(tmp);
        }
        
        return root;
    }
};
```

## 二叉树的逆时钟打印周边

```Cpp
// Boundary of Binary Tree
// https://www.lintcode.com/problem/boundary-of-binary-tree/description
class Solution {
public:
    /**
     * @param root: a TreeNode
     * @return: a list of integer
     */
    void pre(TreeNode *root, TreeNode *last, bool &meet, vector<int> &ans) {
        if (!root) {
            return;
        }
        if (!root->left && !root->right) {
            if (root == last) {
                meet = true;
                return;
            }
            if (meet) {
                ans.push_back(root->val);
            }
            return;
        }
        
        pre(root->left, last, meet, ans);
        pre(root->right, last, meet, ans);
    }
    
    void pos(TreeNode *root, TreeNode *last, bool &meet, vector<int> &ans) {
        if (!root) {
            return;
        }
        if (!root->left && !root->right) {
            if (root == last) {
                meet = true;
                return;
            }
            if (!meet) {
                ans.push_back(root->val);
            }
            return;
        }
        
        pos(root->left, last, meet, ans);
        pos(root->right, last, meet, ans);
    }
    vector<int> boundaryOfBinaryTree(TreeNode * root) {
        // write your code here
        if (!root) {
            return {};
        }
        vector<int> ans{root->val};
        
        // 拿到左边界
        TreeNode *last = nullptr;
        auto t = root->left;
        // 一个连通域下去就可以了
        // 记住最后一个叶子结点
        while (t) {
            last = t;
            ans.push_back(t->val);
            if (t->left) {
                t = t->left;
            } else if (t->right) {
                t = t->right;
            } else {
                break;
            }
        }
        
        // 前序遍历左边界
        bool meet = false;
        pre(root->left, last, meet, ans);
        
        // 拿到右边界
        t = root->right;
        last = nullptr;
        vector<int> tmp;
        while (t) {
            tmp.push_back(t->val);
            last = t;
            if (t->right) {
                t = t->right;
            } else if (t->left) {
                t = t->left;
            } else {
                break;
            }
        }
        
        // 遍历右子树
        meet = false;
        pos(root->right, last, meet, ans);
        
        copy(tmp.rbegin(), tmp.rend(), back_inserter(ans));
        
        return ans;
        
    }
};
```

## BST树的后继

```Cpp

// 要点：如果p结点有右子树，那么找到这个右子树的最左结点
// 否则把根到p结点的整个路径记录下来。然后逆序遍历，如果x是y的左子树，那么返回y->val
class Solution {
public:
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    TreeNode *left_most(TreeNode *root) {
        assert (root);
        
        auto t = root;
        while (t && t->left) {
            t = t->left;
        }
        return t;
    }
    TreeNode * inorderSuccessor(TreeNode * root, TreeNode * p) {
        // write your code here
        // find path to p
        if (!root || !p) {
            return nullptr;
        }

        if (p->right) {
            return left_most(p->right);
        }
        
        vector<TreeNode*> path;
        auto t = root;
        while (t) {
            path.push_back(t);
            if (t == p) {
                break;
            } else if (t->val == p->val) {
                return t;
            } else if (t->val < p->val) {
                t = t->right;
            } else {
                t = t->left;
            }
        }
        
        for (int i = path.size() - 2; i >= 0; i--) {
            auto &t = path[i];
            auto &pre = path[i+1];
            if (pre == t->left) {
                return t;
            }
        }
        
        return nullptr;
    }
};
```

## 字符串构建二叉树

```Cpp
/**
Input: "-4(2(3)(1))(6(5))"
Output: {-4,2,6,3,1,5}
Explanation:
The output is look like this:
      -4
     /  \
    2    6
   / \   / 
  3   1 5  
*/

class Solution {
public:
    /**
     * @param s: a string
     * @return: a root of this tree
     */
    TreeNode *build(string &s, int b, int e, vector<int> &pos) {
        if (b >= e) {
            return nullptr;
        }
        string number;
        while (b < e && s[b] != '(') {
            number.push_back(s[b++]);
        }

        auto root = new TreeNode(atoi(number.c_str()));
        if (b >= e) {
            return root;
        }
        
        root->left = build(s, b+1, pos[b], pos);
        root->right = build(s, pos[b]+2, e-1, pos);
        
        return root;
    }

    TreeNode * str2tree(string &s) {
        // write your code here
        // if string is empty.
        if (s.empty()) {
            return nullptr;
        }
        
        vector<int> pos(s.length());
        stack<int> st;
        for (int i = 0; i < s.length(); i++) {
            if (s[i] == '(') {
                st.push(i);
            } else if (s[i] == ')') {
                const int idx = st.top();
                st.pop();
                pos[idx] = i;
                pos[i] = idx;
            }
        }
        
        return build(s, 0, s.length(), pos);
    }
};
```

## N叉树转二叉树

```Cpp
由于多叉树转二叉树的方法是"左儿子右兄弟",因此 二叉树中X节点的高=二叉树中X的父节点的高+X是第几个儿子-1
```

## 二叉树的编解码

```Cpp
class Codec {
    void encode(TreeNode *root, ostringstream &ostr) {
        if (!root) {
            ostr << " # ";
        } else {
            ostr << root->val << " ";
            encode(root->left, ostr);
            encode(root->right, ostr);
        }
    }

    TreeNode *decode(istringstream &istr) {
        string val;
        istr >> val;

        if (val.empty() || val[0] == '#') {
            return nullptr;
        }

        auto root = new TreeNode(atoi(val.c_str()));
        root->left = decode(istr);
        root->right = decode(istr);
        return root;
    }
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        ostringstream ostr;
        encode(root, ostr);
        return ostr.str();
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        istringstream istr(data);
        return decode(istr);
    }
};
```

## BST树的序列化

```Cpp
/**
 * 前序遍历
 * 直接写到一个数组中，然后利用min, cur, max的值来决定如何取值
 */
class Codec {
    void encode(TreeNode *root, vector<int> &ans) {
        if (!root) {
            return;
        }

        ans.push_back(root->val);
        encode(root->left, ans);
        encode(root->right, ans);
    }

    TreeNode *decode(const int *a, int n, int &pos, int small, int large) {
        if (pos >= n) {
            return nullptr;
        }
        const int v = a[pos];
        if (v < small || v > large) {
            return nullptr;
        }

        auto root = new TreeNode(v);
        pos++;

        root->left = decode(a, n, pos, small, v);
        root->right = decode(a, n, pos, v, large);

        return root;
    }
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        vector<int> ans;
        encode(root, ans);
        string ret((const char *)ans.data(), ans.size()<<2);
        return ret;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        const int *in = (const int *)data.c_str();
        const int n = data.size() >> 2;
        int pos = 0;
        return decode(in, n, pos, INT_MIN, INT_MAX);
    }
};
```

## N叉树转二叉树

```Cpp
/*
// Definition for a Node.
class Node {
public:
    int val = NULL;
    vector<Node*> children;

    Node() {}

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Codec {
    void _dec(TreeNode *root, Node *parent) {
        if (!root) {
            return;
        }

        // root->right链上的点都需要放到parent的children里面
        // root本身要接在parent->children[0]这里
        // c0是和root同级别的节点
        auto c0 = new Node(root->val);
        parent->children.push_back(c0);

        if (!root->left && !root->right) {
            return;
        }

        // 处理root的左子树
        _dec(root->left, c0);

        // 处理root的右子树
        auto p = root->right;
        while (p) {
            auto cx = new Node(p->val);
            parent->children.push_back(cx);
            _dec(p->left, cx);
            p = p->right;
        }
    }
public:

    // Encodes an n-ary tree to a binary tree.
    TreeNode* encode(Node* root) {
        if (!root) {
            return nullptr;
        }

        auto t = new TreeNode(root->val);
        if (!root->children.size()) {
            return t;
        }
        t->left = encode(root->children[0]);

        auto pre = t->left;
        for (int i = 1; i < root->children.size(); i++) {
            pre->right = encode(root->children[i]);
            pre = pre->right;
        }
        return t;
    }

    // Decodes your binary tree to an n-ary tree.
    Node* decode(TreeNode* root) {
        if (!root) {
            return nullptr;
        }
        auto p = new Node(root->val);
        _dec(root->left, p);
        return p;
    }
};
```

## N叉树的序列化和反序化

```Cpp
class Codec {
    void encode(Node *root, ostringstream &ostr) {
        if (!root) {
            ostr << " # " ;
        } else {
            ostr << root->val << " ";
            ostr << root->children.size() << " " ;
            for (auto &c: root->children) {
                encode(c, ostr);
            }
        }
    }

    Node *decode(istringstream &istr) {
        string val;
        istr >> val;
        if (val.empty() || val[0] == '#') {
            return nullptr;
        }

        auto root = new Node(atoi(val.c_str()));
        istr >> val;
        const int n = atoi(val.c_str());
        for (int i = 0; i < n; i++) {
            root->children.push_back(decode(istr));
        }
        return root;
    }
public:

    // Encodes a tree to a single string.
    string serialize(Node* root) {
        ostringstream ostr;
        encode(root, ostr);
        return ostr.str();
    }

    // Decodes your encoded data to tree.
    Node* deserialize(string data) {
        istringstream istr(data);
        return decode(istr);
    }
};
```

## 二叉树按从叶子结点一层一层访问结点

```Cpp
/**
 * 剥洋葱的方式来处理，只需要求出每个结点到叶子结点距离，然后放到相应的距离vector[dist]上。
 */

int __x__ = []() ->int{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    return 1010;
}();

class Solution {
    int dist(TreeNode *root, vector<vector<int>> &ans) {
        if (!root) {
            return -1;
        }

        // 左结点到叶子结点的距离
        const int l = dist(root->left, ans);
        // 右结点到叶子结点的距离
        const int r = dist(root->right, ans);
        // root结点到叶子结点的距离
        const int d = max(l,r) + 1;
        
        // 放到相应的层次上
        if (d >= ans.size()) {
            ans.resize(d+1);
        }
        ans[d].push_back(root->val);
        
        return d;
        
    }
public:
    vector<vector<int>> findLeaves(TreeNode* root) {
        // 求得每个眯到叶子结点的距离
        vector<vector<int>> ans;
        dist(root, ans);
        return ans;
    }
};
```

## 二叉树中的最大BST

```Cpp
int __x__ = []() ->int{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    return 1010;
}();

// ERROR: 当结点数目为1的时候的处理
// ERROR: BST要求里面没有重复的元素

struct trs {
    // 一棵树中序遍历的尾巴
    TreeNode *tail = nullptr;
    // 一棵树中序遍历的头
    TreeNode *head = nullptr;
    // 一棵树中的最小值
    int small = INT_MIN;
    // 一棵树中的最大值
    int large = INT_MAX;
    // 一棵树中的结点数
    int num = 0;
    // 这棵树是否有序
    bool seq = false;
};
class Solution {
    struct trs mid(TreeNode *root, int &ans) {
        struct trs ret;
        if (!root) {
            return ret;
        }
        
        // 如果只有一个结点
        if (!root->left && !root->right) {
            ret.tail = ret.head = root;
            ret.small = ret.large = root->val;
            ret.num = ret.seq = 1;
            
            ans = max(ans, 1);
            
            return ret;
        }
        
        // 如果左子树为空
        if (!root->left) {
            auto r = mid(root->right, ans);
            // 如果右子树没有排好序，那么当前这棵树也不用再看了
            if (!r.seq) {
                ret.seq = false;
                return ret;
            }
            // 右子树是有序的
            ret.seq = root->val < r.head->val;
            if (!ret.seq) {
                // 如果值大于右子树的最左结点，那么直接返回false.
                return ret;
            }
            // 如果根结点的值小于右子树
            ret.head = root;
            ret.tail = r.tail;
            ret.small = root->val;
            ret.large = r.large;
            ret.num = r.num + 1;
            
            ans = max(ans, ret.num);

            return ret;
        }
        
        // 如果右子树为空
        if (!root->right) {
            auto r = mid(root->left, ans);
            // 如果右子树没有排好序，那么当前这棵树也不用再看了
            if (!r.seq) {
                ret.seq = false;
                return ret;
            }
            // 右子树是有序的
            ret.seq = root->val > r.tail->val;
            if (!ret.seq) {
                // 如果值大于右子树的最左结点，那么直接返回false.
                return ret;
            }
            // 如果根结点的值小于右子树
            ret.tail = root;
            ret.head = r.head;
            ret.small = r.small;
            ret.large = root->val;
            ret.num = r.num + 1;
            
            ans = max(ans, ret.num);

            return ret;
        }
        
        // 如果左右子树都不空
        auto l = mid(root->left, ans);
        auto r = mid(root->right, ans);
        
        // 如果左右子树中某个不是有序，那么直接返回
        if (!(l.seq && r.seq)) {
            ret.seq = false;
            return ret;
        }
        
        // 如果左右子树都有序，但是root->val不对劲
        if (!(l.large < root->val && root->val < r.small)) {
            ret.seq = false;
            return ret;
        }
        
        ret.head = l.head;
        ret.tail = r.tail;
        ret.small = l.small;
        ret.large = r.large;
        ret.num = l.num + r.num + 1;
        ret.seq = true;
        
        ans = max(ans, ret.num);
        
        return ret;
        
    }
public:
    int largestBSTSubtree(TreeNode* root) {
        int ans = 0;
        mid(root, ans);
        return ans;
    }
};
```

## 二叉树中从根往叶子递增的路径最长长度

```Cpp
// 只能是父结点到子结点
// 并且要求是必须递增
// append的时候，就把长度算出来
class Solution {
    void pre(TreeNode *root, vector<pair<TreeNode*, int>> &path, int &ans) {
        if (!root) {
            return;
        }

        if (path.empty()) {
            path.emplace_back(root, 1);
            ans = max(ans, 1);
        } else {
            const int v = path.back().first->val;
            if (root->val == v + 1) {
                path.emplace_back(root, path.back().second + 1);
                ans = max(ans, path.back().second);
            } else {
                path.emplace_back(root, 1);
                ans = max(ans, 1);
            }
        }

        pre(root->left, path, ans);
        pre(root->right, path, ans);

        path.pop_back();
    }
public:
    int longestConsecutive(TreeNode* root) {
        vector<pair<TreeNode*, int>> path;
        int ans = 0;
        pre(root, path, ans);
        return ans;
    }
};
```

## 找到二叉树中离target最近的K个值

```Cpp
/**
 * 思路，就是中序遍历，然后维护一个最长长度为k的区间，新进来的数与头比较abs,如果更小就pop_head
 */

class Solution {
public:
    vector<int> closestKValues(TreeNode* root, double target, int k) {
        list<int> res;
        stack<TreeNode*> s;
        auto t = root;

        while (!s.empty() || t) {
            while (t) {
                s.push(t);
                t = t->left;
            }

            t = s.top();
            s.pop();

            if (res.size() < k) {
                res.push_back(t->val);
            } else {
                // 有新的值t->val要进来
                // 看一下与res里面的差距
                const double diff = abs(t->val - target);
                if (diff < abs(*res.begin() - target)) {
                    res.erase(res.begin());
                    res.push_back(t->val);
                } else {
                    break;
                }
            }

            t = t->right;
        }

        return vector<int>(res.begin(), res.end());
    }
};
```

## 验证前序遍历是不是BST

```Cpp
/*
 * 思路，假设现在我们已经知道了一个根结点的值rvalue.
 * 并且这个根结点左子树已经被处理过了。
 * 1. 现在突然来了一个rvalue对应的右子树 x
 *   肯定要求 x > rvalue
 * 2. 接着验证这个右子树的左子树，左子树直接入栈即可，因为左子树的结点的值都比x小。
 *   那么当左子树在入栈的时候，也是要求所有的值都是比rvalue要大的。
 *   因为x是rvalue的右子树。
 * 3. 那么当x的右子树入栈的时候，此时必然更新rvalue = x,并且依次要求x的右子树必须大于等于rvalue.
 */
class Solution {
public:
    bool verifyPreorder(vector<int>& preorder) {
        // 这是一个根结点的值
        // 最开始，可以认为把整棵树都放到了一个X->val = INT_MIN结点的x->right == root上。
        // rval表示一棵右子树对应的根结点的值
        int rval = INT_MIN;

        // iter指向已经存放值的地方
        int iter = -1;

        // 这里要确保的时候，如果一个结点做为右子树，那么其值一定要比rvalue大。
        for (auto &x: preorder) {
            if (x < rval) {
                return false;
            }
            // 当新进来的值比栈中元素大的时候，
            // x必然位于右子树
            // 这个时候，需要找到：刚好比x小，但是是最大值的那个元素rval
            // 并且把这个新的rval做为x的根结点，x就是rval的右结点
            while (iter != -1 && preorder[iter] < x) {
                rval = preorder[iter];
                iter--;
            }
            preorder[++iter] = x;
        }

        return true;
    }
};
```

## 二叉树的最低公共结点

```Cpp
// 思路，首先是因为已经给了前题，就是p和q肯定是出现在这棵子树里面的。
// 所以用前序遍历就可以了。这个断言一定要成立。所以在一开始，如果前序遍历的时候发现roo == p || root == q所以就直接返回了
class Solution {
public:
  TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {    
    if (!root || root == p || root == q) return root;
    TreeNode* l = lowestCommonAncestor(root->left, p, q);
    TreeNode* r = lowestCommonAncestor(root->right, p, q);
    if (l && r) return root;
    return l ? l : r;
  }
};
```

## 二叉树中的最大和路径 

```Cpp
class Solution {
    int64_t mps(TreeNode *root, int64_t &ans) {
        if (!root) {
            return 0;
        }
        auto l = mps(root->left, ans);
        auto r = mps(root->right, ans);
				// 注意这里的max
        ans = max<int64_t>({ans, l+r+root->val, l+root->val, r+root->val, root->val});
				// 注意这里的max
        return max<int64_t>({root->val, l+root->val, r+root->val});
    }
public:
    int maxPathSum(TreeNode* root) {
        int64_t ans = INT64_MIN;
        mps(root, ans);
        return ans;
    }
};
```

# 并查集

```Cpp
// 并查集数组
vector<int> F;
// 记录并查集中集合的个数
int count = 0;
// 记录集合中点的个数，比如要知道i所在集合的点有多少个: C[Find(i)]
// 注意：这里不能直接使用C[i]
// 因为只有根结点的统计才是正确的
vector<int> Cnt;

// 并查集的初始化
void Init(int n) {
    F.resize(n);
    Cnt.resize(n);
    for (int i = 0; i < n; i++) {
        F[i] = i;
        Cnt[i] = 1;
    }
    count = n;
}

int Find(int x) { return x == F[x] ? x : F[x] = Find(F[x]); }

void Union(int x, int y) {
    int xpar = Find(x);
    int ypar = Find(y);
    // 将x所在集合，合并到y所在集合
    if (xpar != ypar) {
        F[xpar] = ypar;
        // y集合里面的个数要增加
        Cnt[ypar] += Cnt[xpar];
        count--;
    }
}

int Size(int i) { return Cnt[Find(i)]; }
```

# 排序算法

## 合并排序

```Cpp
class Solution {
  void msort(vector<int> &a, int b, int e, vector<int> &t) {
    if (b >= e || b + 1 >= e) {
      return;
    }

    const int m = b + ((e - b) >> 1);

    msort(a, b, m, t);
    msort(a, m, e, t);

    int i = b;
    int j = m;
    int to = b;

    while (i < m || j < e) {
      if (j >= e || i < m && a[i] <= a[j]) {
        t[to++] = a[i++];
      } else {
        t[to++] = a[j++];
      }
    }

    for (int i = b; i < e; i++) {
      a[i] = t[i];
    }
  }

public:
  void mergeSort(vector<int> &nums) {
    vector<int> t(nums.size());
    msort(nums, 0, nums.size(), t);
  }
};
```

三路切分

```Cpp
// LeetCode 75
class Solution {
public:
    void sortColors(vector<int>& A) {
        const int N = A.size();
        int l = 0, i = 0, r = N - 1;
        // [0..l)[l...i) i... (r..N)
        while (i <= r) {
            if (A[i] == 0) swap(A[l++], A[i++]);
            else if (A[i] == 1) i++;
            else swap(A[r--], A[i]);
        }
    }
};
```

三路切分快排

```Cpp
class Solution
{
  // 将数组[b, e)范围的元素进行排序
  void qsort(vector<int> &A, int b, int e) {
    // 像二叉树一样，如果空树/只有一个结点，那么不需要再递归了
    // 如果给定的区间段为空，或者只有一个结点。
    if (b >= e || b + 1 >= e) {
      return;
    }

    // 取数组中间的元素作为x
    const int m = b + ((e - b) >> 1);
    const int x = A[m];

    // 三路切分,这部分代码在例 3详细介绍!
    int l = b, i = b, r = e - 1;

    while (i <= r) {
      if (A[i] < x) {
        swap(A[l++], A[i++]);
      } else if (A[i] == x) {
        i++;
      } else {
        swap(A[r--], A[i]);
      }
    }

    // 像二叉树的前序遍历一样，分别遍历左子树与右子树。
    qsort(A, b, l);
    qsort(A, i, e);
  }

public:
  void sortColors(vector<int>& nums) {
      qsort(nums, 0, nums.size());
  }
};
```

普通的两路切分的快排

```Cpp
// LeetCode 75
class Solution {
    void qsort(vector<int> &A, int b, int e) {
        if (b >= e || b + 1 >= e) {
            return;
        }

        const int m = b + ((e-b) >> 1);
        const int x = A[m];

        int l = b, i = b, r = e - 1;

        while (i <= r) {
            if (A[i] < x) swap(A[l++], A[i++]);
            else if (A[i] == x) i++;
            else swap(A[r--], A[i]);
        }

        qsort(A, b, l);
        qsort(A, i, e);
    }
public:
    void sortColors(vector<int>& A) {
        qsort(A, 0, A.size());
    }
};
```

# 二分搜索

lower_bound

```Cpp
int lowerBound(int *A, int n, long target) {
  int l = 0, r = n;
  while (l < r) {
    final int m = l + ((r - l) >> 1);
    if (A[m] < target) {
      l = m + 1;
    } else {
      r = m;
    }
  }
  return l;
}
```

upper_bound

```Cpp
int upperBound(int *A, int n, long target) {
  int l = 0, r = n;
  while (l < r) {
    final int m = l + ((r - l) >> 1);
    if (A[m] <= target) {
      l = m + 1;
    } else {
      r = m;
    }
  }
  return l;
}
```

# 双指针

## 最长区间

```Cpp
int maxLength(int* A) {
  int N = A.length;
  // 区间的左指针
  int left = -1;
  int ans = 0;
  for (int i = 0; i < N; i++) {
    // assert 在加入A[i]之前，(left, i-1]是一个合法有效的区间
    // step 1. 直接将A[i]加到区间中，形成(left, i]
    // step 2. 将A[i]加入之后，惰性原则
    while (check((left, i]))/*TODO 检查区间状态是否满足条件*/) {
      ++left; // 如果不满足条件，移动左指针
      // TODO 修改区间的状态
    }
    // assert 此时(left, i]必然满足条件
    ans = max(ans, i - left);
  }
  return ans; // 返回最优解
}
```

## 定长区间

```Cpp
int fixedLength(int* A, int windowSize) {
  const int N = A == null ? 0 : A.length;
  int left = -1;
  for (int i = 0; i < N; i++) {
    // step 1. 直接将A[i]加到区间中，形成(left, i]
    // TODO 修改区间的状态

    // 如果滑动窗口还太小
    if (i - left < windowSize) {
      continue;
    }

    // assert 此时(left, i]长度必然等于windowSize
    // TODO 判断区间的状态是否满足约束条件
    left++;
    // step 2. 移除A[left]
    // TODO 修改区间状态
  }
  return ans; // 返回
}
```

## 最短区间

```Cpp
int minimalRange(int[] A) {
  const int N = A == null ? 0 : A.length;
  // 子串的左边，采用左开右闭原则(left, i]表示一个子串
  int left = -1;
  // 记录最短的子串的长度
  int ans = A.length + 1;
  for (int i = 0; i < N; i++) {
    // 注意 在加入A[i]之前，(left, i-1]可能不满足条件!
    // step 1. 直接将A[i]加到区间中，形成(left, i]
    // step 2. TODO 更新区间的状态
    while (区间超出/满足条件) {
      ans = Math.min(ans, i - left);
      // step 3. 移除A[++left];
      // step 4. TODO 更新区间的状态
    }
    // assert ! 区间(left, i]到这里肯定不满足条件
  }
  return ans;
}
```

# 贪心算法

相互不覆盖的区间的数目

```Cpp
int nonOverlapIntervals(vector<vector<int>> &A) {
  const int N = A.size();

  sort(A.begin(), A.end(), [](const vector<int> &a, const vector<int> &b) {
    return a[1] != b[1] ? a[1] < b[1] : a[0] < b[0];
  });

  int preEnd = INT_MIN;
  int ans = 0;
  for (int i = 0; i < N; i++) {
    const int start = A[i][0];
    const int end = A[i][1];
    if (preEnd <= start) {
      ans++;
      preEnd = end;
    }
  }

  return ans;
}
```

青蛙跳算法的模板

```Cpp
// LeetCode 55
class Solution {
   public:
    bool canJump(vector<int>& A) {
        const int N = A.size();
        int i = 0;
        while (i < N && i + A[i] < N - 1) {
            const int old = i + A[i];
            int maxPos = old;
            int j = i + 1;
            while (j <= old) {
                if (j + A[j] > maxPos) {
                    i = j;
                    maxPos = j + A[j];
                }
                j++;
            }

            if (maxPos == old) {
                return false;
            }
        }

        return true;
    }
};
```

# 回溯

```Cpp
void backtrace(int[] A,
               int i, /*第i个人*/
               Box s, /*箱子*/
               answer/*存放所有的答案*/) {
  final int N = A == null ? 0 : A.length;
  if (状态满足要求) {
    answer.add(s);
  }
 
  if ([i, ...., 后面）的人都没有任何选项了) {
    return;
  }
  for 宝石 in {第i个人当前所有宝石选项} {
    s.push(宝石);
    backtrace(A, i + 1, s, answer);
    s.pop();
  }
}
```

无重复元素的全排列

```Cpp
class Solution {
    void backtrack(vector<int> &A, int i, vector<bool> &used, vector<int> &box,
                   vector<vector<int>> &ans) {
        const int N = A.size();
        // 如果状态已经满足要求
        if (box.size() == N) {
            ans.push_back(box);
        }

        // 我们总是从第0个人开始，那么一共有N个元素
        // 那么当到第N个人的时候，已经没有东西可以选了。
        // [N ~ inf) 后面所有的人都没有东西可以选了。
        if (i >= N) {
            return;
        }

        // 第i个人的选择范围就是[0, N)
        // 但是不能选择box中已经用过的元素
        for (int j = 0; j < N; j++) {
            if (!used[j]) {
                box.push_back(A[j]);
                used[j] = true;
                backtrack(A, i + 1, used, box, ans);
                used[j] = false;
                box.pop_back();
            }
        }
    }

   public:
    vector<vector<int>> permute(vector<int> &A) {
        const int N = A.size();
        if (N == 0) {
            return {};
        }

        vector<int> box;
        vector<vector<int>> ans;
        vector<bool> used(N, false);

        backtrack(A, 0, used, box, ans);
        return ans;
    }
};
```

带重复元素的全排列

```Cpp
class Solution
{
  void backtrace(vector<int>& box, int start, vector<vector<int>>& ans)
  {
    const int N = box.size();
    if (start >= N) {
      ans.push_back(box);
      return;
    }

    unordered_set<int> s;
    for (int j = start; j < N; j++) {
      if (!s.count(box[j])) {
        swap(box[j], box[start]);
        backtrace(box, start + 1, ans);
        swap(box[j], box[start]);
        s.insert(box[j]);
      }
    }
  }

public:
  vector<vector<int>> permuteUnique(vector<int>& A)
  {
    const int N = A.size();
    vector<int> box;
    vector<vector<int>> ans;
    backtrace(A, 0, ans);
    return ans;
  }
};
```

无重复元素的子集

```Cpp
class Solution {
    void backtrack(vector<int> &A, int start, vector<int> &box,
                   vector<vector<int>> &ans) {
        const int N = A.size();
        ans.push_back(box);

        if (start >= N) {
            return;
        }

        for (int j = start; j < N; j++) {
            box.push_back(A[j]);
            backtrack(A, j + 1, box, ans);
            box.pop_back();
        }
    }

   public:
    vector<vector<int>> subsets(vector<int> &A) {
        vector<int> box;
        vector<vector<int>> ans;
        backtrack(A, 0, box, ans);
        return ans;
    }
};
```

有重复元素的子集

```Cpp
class Solution
{
  void backtrace(vector<int>& A,
                 int i,     /*第i个人*/
                 int start, /*第i个人的选择范围[start, end]*/
                 int end,
                 vector<int>& box,
                 vector<vector<int>>& ans)
  {
    const int N = A.size();
    ans.push_back(box);

    if (start >= N) {
      return;
    }

    for (int j = start; j < N; j++) {
      if (j > start && A[j] == A[j - 1]) {
        continue;
      }
      box.push_back(A[j]);
      backtrace(A, i + 1, j + 1, end, box, ans);
      box.pop_back();
    }
  }

public:
  vector<vector<int>> subsetsWithDup(vector<int>& A)
  {
    const int N = A.size();
    sort(A.begin(), A.end());
    vector<int> box;
    vector<vector<int>> ans;
    backtrace(A, 0, 0, N, box, ans);
    return ans;
  }
};
```

# BFS

方法1

```Cpp
 
bfs(s) { // s表示出发点
  q = new queue()
  q.push(s), visited[s] = true // 标记s为已访问
  while (!q.empty()) {
    u = q.pop() // 拿到当前结点 
    for next in getNext(u) { // 拿到u的后继next
      if (!visited[next]) { // 如果next还没有访问过 
        q.push(next)
        visited[next] = true
      }
    }
  }
}
```

方法2

```Cpp
 
bfs(s) { // s表示出发点
  cur = {s};
  visited[s] = true; // 标记s为已访问
  while (!cur.empty()) {
    next = [];
    for c in cur {
      for next in getNext(c) {
        if (!visited[next]) { // 如果next还没有访问过 
          next.append(next);
          visited[next] = true;
        }
      }
    }
    cur = next;
  }
}
```

# DFS

```Cpp
boolean vis[N];
void DFS(int start) {
  if start == end {
      success = true
      return
  }
  // 遍历当前可以做出的选择
  for opt in getOptions(start) {
      if (vis[opt]) continue;
      vis[opt] = true;
      dfs(opt);
      if success {
          return;
      }
  }
}
```

收集所有的满足条件的解

```Cpp
void dfs(A,
         int start, /* start 表示出发点*/
         vis,  /* 记录每个点是否已经访问 */
         path, /* 路径*/
         answer/*存放最优的答案*/) {
  final int N = A == null ? 0 : A.length;
 
  if (状态满足要求) { // 是更好的解吗？
    if (s better_than ans) {
        ans = s
    }
    return;
  }
  
  for next in {start点的后继点} {
    if !vis[next] {
      path.append(next);
      vis[next] = true;
      dfs(A, next, vis, path, answer);
      path.pop();
      vis[next] = false;
    }
  }
}
```

# Dijkstra算法

```Cpp
class Solution {
public:
    int networkDelayTime(vector<vector<int>>& es, int N, int K) {
        vector<vector<pair<int,int>>> G(N+1);
        for (auto &e: es) {
            const int from = e[0], to = e[1], cost = e[2];
            G[from].push_back({to, cost});
        }

        constexpr int INF = INT_MAX >> 2;

        vector<int> ans(N+1, INF);
        ans[K] = 0;

        auto cmp = [&](const int i, const int j) {
            return ans[i] > ans[j];
        };

        priority_queue<int, vector<int>, decltype(cmp)> Q(cmp);
        Q.push(K);

        while (!Q.empty()) {
            auto cur = Q.top();
            Q.pop();

            for (auto n: G[cur]) {
                const int dist = ans[cur] + n.second;
                if (dist < ans[n.first]) {
                    ans[n.first] = dist;
                    Q.push(n.first);
                }
            }
        }

        int max_value = *max_element(ans.begin() + 1, ans.end());
        return max_value >= INF ? -1 : max_value; 
    }
};
```

# KMP

```Cpp

void make_next(char *t, int *next) {
    int i = 0;
    int j = -1;
    next[0] = -1;

    while (t[i]) {
        if (-1 == j || t[i] == t[j]) {
            ++i;
            ++j;
            // 注意这优化，在往后退的时候，如果发现和下一个退回
            // 去的地方的字符是相等的，那么再往后退一下。
            // 否则就退到下一个要比较的地方
            next[i] = t[i] == t[j] ? next[j] : j;
        } else {
            j = next[j];
        }
    }
}

int strStr(char * s, char * t){
    if (!t || !*t) {
        return 0;
    }

    if (!s) {
        return -1;
    }

    const int tlen = strlen(t);

    int next[tlen + 1];
    make_next(t, next);

    int i = 0, j = 0;
    while (s[i] && j < tlen) {
        if (-1 == j || s[i] == t[j]) {
            i++;
            j++;
        } else {
            j = next[j];
        }
    }

    return j == tlen ? i - j : -1;
}
```



