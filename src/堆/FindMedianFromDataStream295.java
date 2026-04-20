package 堆;

import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数 困难
 *
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 *
 * 实现 MedianFinder 类:
 * MedianFinder() 初始化 MedianFinder 对象。
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 *
 * 示例 1：
 * 输入
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * 输出
 * [null, null, null, 1.5, null, 2.0]
 * 解释
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * 提示:
 * -105 <= num <= 105
 * 在调用 findMedian 之前，数据结构中至少有一个元素
 * 最多 5 * 104 次调用 addNum 和 findMedian
 */
public class FindMedianFromDataStream295 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}


class MedianFinder {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;


    public MedianFinder() {
        // 左边 大根堆
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // 右边 小根堆
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // 先推左边
        maxHeap.add(num);
        // 再把最大的推到右边，保证右边一定比左边大
        minHeap.add(maxHeap.poll());
        // 右边太多了，平衡一个最小的到左边，维持 左边数量 >= 右边数量
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        int size = maxHeap.size() + minHeap.size();
        if (maxHeap.isEmpty()) {
            return minHeap.peek() == null ? 0.0 : minHeap.peek();
        }
        if (minHeap.isEmpty()) {
            return maxHeap.peek();
        }

        if (size % 2 == 0) {
            Integer left = maxHeap.peek();
            Integer right = minHeap.peek();
            return  ((double)left + right) / 2;
        }  else {
            return maxHeap.peek();
        }
    }
}