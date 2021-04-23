package co.aurasphere.algo.sorting;

import java.util.Arrays;

// Stable
// Time Worst/Average O(n^2)
// Time best O(n) (all elements are already sorted)
// Space Worst O(1) (in place)
public class BubbleSort {

	public static void main(String[] args) {
		int[] arrayToSort = { 17, 31, 6, 15, 89, 0, 2, 182, 15 };
		System.out.println("Pre sort:");
		System.out.println(Arrays.toString(arrayToSort));

		bubbleSort(arrayToSort);

		System.out.println("Post sort:");
		System.out.println(Arrays.toString(arrayToSort));
	}

	private static void bubbleSort(int[] array) {
		boolean switchHappened = false;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j + 1);
					switchHappened = true;
				}
			}
			
			// The array is already sorted
			if (!switchHappened) {
				return;
			}
		}
	}

	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

}