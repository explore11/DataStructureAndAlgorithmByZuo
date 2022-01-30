package com.song.base.day07;


/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 前缀树
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code01_TrieTree {

	public static class TrieNode {
		public int pass;
		public int end;
		public TrieNode[] nexts;
		//如果字符串比较多的话 可以用map
//		public HashMap<String, TrieNode> nexts;

		public TrieNode() {
			pass = 0;
			end = 0;
			nexts = new TrieNode[26];
		}
	}

	public static class Trie {
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		// 插入前缀树
		public void insert(String word) {
			if (word == null) {
				return;
			}
			// 获取所有字符
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				// 判断是否存在路 如果没有则重建
				if (node.nexts[index] == null) {
					node.nexts[index] = new TrieNode();
				}
				// 判断是否存在路 如果有则pass+1
				node = node.nexts[index];
				node.pass++;
			}
			// 最后一个字符 end+1
			node.end++;
		}

		//删除字符
		public void delete(String word) {
			// 判断是否存在该字符串
			if (search(word) != 0) {
				//获取所有的字符
				char[] chs = word.toCharArray();
				TrieNode node = root;
				node.pass--;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = chs[i] - 'a';
					// 如果当前节点的下一个节点的某一个路的pass值为0
					if (--node.nexts[index].pass == 0) {
						//则删除这个路的节点
						node.nexts[index] = null;
						return;
					}
					//指针下移
					node = node.nexts[index];
				}
				node.end--;
			}
		}

		// 搜索字符串
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			//获取所有的字符
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				// 判断是否存在路 如果没有则不存在返回0
				if (node.nexts[index] == null) {
					return 0;
				}
				// 指针下移
				node = node.nexts[index];
			}
			// end为0 则不存在 大于0则存在该字符串
			return node.end;
		}

		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		System.out.println((trie.prefixNumber("zuoa")));
		trie.delete("zuoab");
		System.out.println(trie.prefixNumber("zuoa"));

	}

}
