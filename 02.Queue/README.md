# 02 队列

## 01 二叉树的层次遍历

二叉树比较原版的层次遍历我们已经在正文例题中进行了介绍。这里给出下面一些例题的解答。其实都是基于
原来的二叉树的层次遍历的简单的改写。

### 练习1 每一层的平均值

这个题只需要在`二叉树的层次遍历`的基础上，将每一层的平均值求出来就可以了。

代码: [Java](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/637.二叉树的层平均值.java)/[C++](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/637.二叉树的层平均值.c++)/[Java](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/637.二叉树的层平均值.py)

### 练习2 N叉树的层次遍历

在层次遍历的基础上，这里只是将`left, right`两个左右子结点改成了`children`。

代码：[Java](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/429.n-叉树的层序遍历.java)/[C++](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/429.n-叉树的层序遍历.cpp)/[Python](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/429.n-叉树的层序遍历.py)

### 层数最深叶子节点的和


这里只需要处理最后一层，然后将其和返回就可以了。

代码：[Java](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/1302.层数最深叶子节点的和.java)/[C++](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/1302.层数最深叶子节点的和.cpp)/[Python](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/1302.层数最深叶子节点的和.py)


### 二叉树最大宽度

这里采用了分层遍历的思想来拿到最大宽度。

需要在入栈的时候，对结点进行编号。在编号的时候，如果父结点编号为x

- 左子结点为 2 * x
- 右子结点为 2 * x + 1

然后这个编号需要和结点一起入队。

代码：[Java](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/662.二叉树最大宽度.java)/[C++](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/662.二叉树最大宽度.cpp)/[Python](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/662.二叉树最大宽度.py)

### 二叉树的锯齿形层次遍历

在层次遍历的时候，只需要利用一个`boolean`变量来指示是否要将当前遍历的结果进行`reverse`操作。

代码：[Java](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/103.二叉树的锯齿形层序遍历.java)/[C++](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/103.二叉树的锯齿形层序遍历.cpp)/[Python](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/103.二叉树的锯齿形层序遍历.py)

### 二叉树的层次遍历 II

只需要遍历完成之后，将最后的结果`reverse`一下就可以了。

代码：[Java](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/107.二叉树的层序遍历-ii.java)/[C++](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/107.二叉树的层序遍历-ii.cpp)/[Python](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/107.二叉树的层序遍历-ii.py)

### N 叉树的最大深度

和二叉树的最大深度一样来处理。

代码：[Java](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/559.n-叉树的最大深度.java)/[C++](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/559.n-叉树的最大深度.cpp)/[Python](https://github.com/lagoueduCol/Algorithm-Dryad/blob/main/02.Queue/559.n-叉树的最大深度.py)