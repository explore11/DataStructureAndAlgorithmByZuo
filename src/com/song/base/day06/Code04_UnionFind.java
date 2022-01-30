package com.song.base.day06;

import java.util.*;

public class Code04_UnionFind {

    public static class Node<V> {
        V value;
        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 给你一个节点，请你往上到不能再往上，把代表返回
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }
        public int sets() {
            return sizeMap.size();
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    // 最小生成树
    public static Set<Edge> kruskalMST(Graph graph) {
		List<com.song.base.day06.Node> values = (List<com.song.base.day06.Node>) graph.nodes.values();
		UnionFind unionFind = new UnionFind(values);
		// 最小堆 按照边的权重从小到大排序 添加到优先队列中
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        priorityQueue.addAll(graph.edges);

        // 返回的结果
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            // 判断两个节点是否在同一个集合内
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                // 如果不在 合并节点集和
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }
}
