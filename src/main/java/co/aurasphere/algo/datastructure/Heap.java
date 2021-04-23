package co.aurasphere.algo.datastructure;

import java.util.Arrays;

public class Heap {

	private int size = 16;

	private Integer[] array = new Integer[size];

	private int currentSize = 0;

//	 Implement a max-heap:
//		 insert
//		 sift_up - needed for insert
//		 get_max - returns the max item, without removing it
//		 get_size() - return number of elements stored
//		 is_empty() - returns true if heap contains no elements
//		 extract_max - returns the max item, removing it
//		 sift_down - needed for extract_max
//		 remove(i) - removes item at index x
//		 heapify - create a heap from an array of elements, needed for heap_sort
//		 heap_sort() - take an unsorted array and turn it into a sorted array in-place using a max heap or min heap

	public Heap(Integer[] array) {
		this.array = array;
	}
	
	public Heap() {}
	
	public void insert(int e) {
		currentSize++;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = e;
				siftUp(i);
				return;
			}
		}

		// Max size reached, copy everything in a bigger array
		// TODO

	}

	private static int getLeftChildIndex(int currentPointer) {
		return 2 * currentPointer + 1;
	}

	private static int getRightChildIndex(int currentPointer) {
		return 2 * currentPointer + 2;
	}

	private static int getParentIndex(int currentPointer) {
		return (int) Math.floor((currentPointer - 1) / 2);
	}

	private void siftUp(int index) {
		if (index == 0) {
			return;
		}
		int current = array[index];
		int parentElement = array[getParentIndex(index)];
		if (parentElement < current) {
			swap(index, getParentIndex(index));
			siftUp(getParentIndex(index));
		}
	}

	private int siftDown(int index) {
		int rightChildIndex = getRightChildIndex(index);
		int leftChildIndex = getLeftChildIndex(index);
		if (rightChildIndex >= array.length || leftChildIndex >= array.length) {
			throw new IllegalAccessError();
		}

		Integer rightChild = array[rightChildIndex];
		Integer leftChild = array[leftChildIndex];
		Integer current = array[index];

		if (rightChild == null) {
			if (leftChild != null && leftChild > current) {
				swap(index, getLeftChildIndex(index));
				return siftDown(getLeftChildIndex(index));
			}
			return index;
		}
		if (leftChild == null) {
			if (rightChild != null && rightChild > current) {
				swap(index, getRightChildIndex(index));
				return siftDown(getRightChildIndex(index));
			}
			return index;
		}

		int smallerChildrenIndex = rightChild > leftChild ? getLeftChildIndex(index) : getRightChildIndex(index);
		swap(index, smallerChildrenIndex);
		return siftDown(smallerChildrenIndex);
	}

	private void swap(int a, int b) {
		int tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}

	public void remove(int index) {
		int newIndex = siftDown(index);
		array[newIndex] = null;
		currentSize--;
	}

	public int getMax() {
		return array[0];
	}

	public int getSize() {
		return this.currentSize;
	}

	public boolean isEmpty() {
		return this.currentSize == 0;
	}

	public int extractMax() {
		int max = getMax();
		remove(0);
		return max;
	}

	public static Heap heapify(Integer[] array) {
		for (int i = array.length / 2; i < 0; i--) {
			maxHeapify(array, i);
		}
		return new Heap(array);
	}

	private static void maxHeapify(Integer[] array, int index) {
		int leftChildIndex = getLeftChildIndex(index);
		int rightChildIndex = getRightChildIndex(index);

		if (rightChildIndex >= array.length || leftChildIndex >= array.length) {
			throw new IllegalAccessError();
		}

		Integer rightChild = array[rightChildIndex];
		Integer leftChild = array[leftChildIndex];
		Integer current = array[index];

		int biggestIndex = rightChild > leftChild ? rightChildIndex : leftChildIndex;

		if (array[biggestIndex] > current) {
			int tmp = array[biggestIndex];
			array[biggestIndex] = array[index];
			array[index] = tmp;
			maxHeapify(array, biggestIndex);
		}
	}

	public static void heapSort(int[] array) {
	}
	
	public static void main(String[] args) {
		Heap heap = new Heap();
		heap.insert(3);
		heap.insert(15);
		heap.insert(2);
		heap.insert(7);
		heap.insert(199);
		heap.insert(45);
		heap.insert(1);
		heap.insert(1000000);
		heap.insert(100040);
		
		Integer[] heapArray = new Integer[] {3, 15, 2, 7, 199, 45, 1, 1000000 , 100040};
		Heap heapified = heapify(heapArray);
		
		System.out.println(Arrays.toString(heap.array));
		System.out.println(Arrays.toString(heapified.array));
	}

}