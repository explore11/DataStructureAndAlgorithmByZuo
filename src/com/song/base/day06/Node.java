package com.song.base.day06;

import java.util.ArrayList;

public class Node {
	public int value;
	// 入度
	public int in;
	// 出度
	public int out;
	public ArrayList<Node> nexts;
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
