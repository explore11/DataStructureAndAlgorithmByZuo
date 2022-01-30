package com.song.base.day07;

import java.util.Arrays;
import java.util.Comparator;


/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description  要求会议室进行的宣讲的场次最多。 返回这个最多的宣讲场次。
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间(给你一个数组，里面是一个个具体的项目)，
 * 你来安排宣讲的日程，
 * 要求会议室进行的宣讲的场次最多。 返回这个最多的宣讲场次。
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code04_BestArrange {

	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	// 以结束时间最早进行排序
	public static int bestArrange(Program[] programs, int start) {
		//以结束时间最早进行排序
		Arrays.sort(programs, new ProgramComparator());
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			// 开始时间点 小于等于 结束时间最早的会议的开始时间
			if (start <= programs[i].start) {
				//次数+1
				result++;
				//更新时间点
				start = programs[i].end;
			}
		}
		return result;
	}

	public static void main(String[] args) {

	}

}
