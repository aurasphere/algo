package co.aurasphere.algo.sorting;

import java.util.Arrays;

// Unstable
// Time Best/Average O(n log(n))
// Time Worst O(n^2) (each partition contains 1 element)
// Space Worst O(log(n)) (the stack)
// In place
public class QuickSort {

	public static void main(String[] args) {
		int[] arrayToSort = { 17, 31, 6, 15, 89, 0, 2, 182, 15 };
		System.out.println("Pre sort:");
		System.out.println(Arrays.toString(arrayToSort));

		quickSort(arrayToSort);

		System.out.println("Post sort:");
		System.out.println(Arrays.toString(arrayToSort));
	}

	private static void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int pivot = partition(array, low, high);
			quickSort(array, low, pivot - 1);
			quickSort(array, pivot + 1, high);
		}
	}

	private static int partition(int[] array, int low, int high) {
		int pivot = array[high];
		int lowPointer = low;
		for (int i = low; i < high; i++) {
			if (pivot > array[i]) {
				swap(array, i, lowPointer);
				lowPointer++;
			}
		}
		// Adds the pivot at the current pointer position
		swap(array, high, lowPointer);
		
		// Returns the pivot position
		return lowPointer;
	}

	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

}