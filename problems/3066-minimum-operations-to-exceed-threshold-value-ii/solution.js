class MinHeap {
    constructor(arr = []) {
        this.heap = arr;
        this.heapify();  // Convert array into a valid heap
    }

    heapify() {
        if (this.heap.length) {
            for (let i = Math.floor(this.heap.length / 2); i >= 0; i--) {
                this.sinkDown(i);
            }
        }
    }

    push(val) {
        this.heap.push(val);
        this.bubbleUp(this.heap.length - 1);
    }

    pop() {
        if (this.heap.length === 1) return this.heap.pop();
        const min = this.heap[0];
        this.heap[0] = this.heap.pop();
        this.sinkDown(0);
        return min;
    }

    bubbleUp(index) {
        while (index > 0) {
            let parent = Math.floor((index - 1) / 2);
            if (this.heap[parent] <= this.heap[index]) break;
            [this.heap[parent], this.heap[index]] = [this.heap[index], this.heap[parent]];
            index = parent;
        }
    }

    sinkDown(index) {
        let left, right, smallest;
        while (true) {
            left = 2 * index + 1;
            right = 2 * index + 2;
            smallest = index;

            if (left < this.heap.length && this.heap[left] < this.heap[smallest]) smallest = left;
            if (right < this.heap.length && this.heap[right] < this.heap[smallest]) smallest = right;

            if (smallest !== index) {
                [this.heap[index], this.heap[smallest]] = [this.heap[smallest], this.heap[index]];
                index = smallest;
            } else break;
        }
    }

    peek() {
        return this.heap[0];
    }

    size() {
        return this.heap.length;
    }
}

var minOperations = function(nums, k) {
    let count = 0;
    const minHeap = new MinHeap(nums); // Convert array into MinHeap

    while (minHeap.peek() < k) {
        let min1 = minHeap.pop();
        let min2 = minHeap.pop();
        minHeap.push(min1 * 2 + min2);
        count++;
    }

    return count;
};
