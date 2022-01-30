package com.song.base.day05;

public class Code10_PaperFolding {

	public static void printAllFolds(int N) {
		printProcess(1, N, true);
	}

	public static void printProcess(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		printProcess(i + 1, N, true);
		System.out.print(down ? "凹 " : "凸 ");
		printProcess(i + 1, N, false);
	}

	public static void main(String[] args) {
		int N = 3;
		printAllFolds(N);
	}
}
