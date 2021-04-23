package co.aurasphere.algo.sorting;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] arrayToSort = { 17, 31, 6, 15, 89, 0, 2, 182, 15 };
		System.out.println("Pre sort:");
		System.out.println(Arrays.toString(arrayToSort));

		arrayToSort = mergeSort(arrayToSort);

		System.out.println("Post sort:");
		System.out.println(Arrays.toString(arrayToSort));
	}

	private static int[] mergeSort(int[] arrayToSort) {
		if (arrayToSort.length == 1) {
			return arrayToSort;
		}
		int[][] subArrays = split(arrayToSort);
		subArrays[0] = mergeSort(subArrays[0]);
		subArrays[1] = mergeSort(subArrays[1]);
		return merge(subArrays[0], subArrays[1]);
	}

	public static int[][] split(int[] original) {
		int newSize = original.length / 2;
		int[] first = new int[newSize];
		int[] second = new int[original.length - newSize];

		for (int i = 0; i < original.length; i++) {
			if (i < newSize) {
				first[i] = original[i];
			} else {
				second[i - newSize] = original[i];
			}
		}
		return new int[][] { first, second };
	}

	public static int[] merge(int[] first, int[] second) {
		int[] result = new int[first.length + second.length];
		int firstPointer = 0;
		int secondPointer = 0;
		int resultPointer = 0;

		while (firstPointer + secondPointer < result.length) {
			if (firstPointer == first.length) {
				while (secondPointer < second.length)
					result[resultPointer++] = second[secondPointer++];
				break;
			}
			if (secondPointer == second.length) {
				while (firstPointer < first.length)
					result[resultPointer++] = first[firstPointer++];
				break;
			}
			
			if (first[firstPointer] > second[secondPointer]) {
				result[resultPointer++] = second[secondPointer++];
			} else {
				result[resultPointer++] = first[firstPointer++];
			}
		}
		return result;
	}

}