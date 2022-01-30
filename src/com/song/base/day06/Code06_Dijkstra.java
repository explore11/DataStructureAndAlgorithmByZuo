package com.song.base.day06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

// no negative weight
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description  迪克斯特拉算法  从一个制定点出发到所有点的最小路径   适用范围：没有权值为负数的边
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra1(Node head) {
        // 从一个制定点出发到所有点的最小路径
        // key   从head出发到达的节点
        // value 从head出发到达key的最小距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // 添加头节点
        distanceMap.put(head, 0);
        // 已经锁定的节点
        HashSet<Node> selectedNodes = new HashSet<>();
        // 获取最小距离的节点 不被锁定的（不在selectedNodes中出现的）
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            // 获取到最小路径节点的长度
            int distance = distanceMap.get(minNode);
            // 获取最小路径节点的所有邻接的边
            for (Edge edge : minNode.edges) {
                // 获取边的to节点
                Node toNode = edge.to;
                // 判断在map中是否存在
                if (!distanceMap.containsKey(toNode)) {
                    // 不存在则添加
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    // 如果存在 则与原来的路径相比 更新路径最短的放进去
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            // 一个节点的所有边都处理完成之后，这个节点进入锁节点集和中
            selectedNodes.add(minNode);
            // 再次挑选不在锁集和中的节点且距离最短的节点
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap,
                                                       HashSet<Node> touchedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        // 循环map
        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes;
        private HashMap<Node, Integer> heapIndexMap;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

}
