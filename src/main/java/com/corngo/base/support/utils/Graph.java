package com.corngo.base.support.utils;

import java.util.*;

/**
 * 最短路径算法工具类 Title: Graph Description:
 * 
 * @author sunshanwei
 * @date 2017年3月16日 上午10:37:03
 */
public class Graph {

	private final Map<Integer, List<Vertex>> vertices;

	public Graph() {
		this.vertices = new HashMap<Integer, List<Vertex>>();
	}

	public void addVertex(Integer character, List<Vertex> vertex) {
		this.vertices.put(character, vertex);
	}

	public List<Integer> getShortestPath(Integer start, Integer finish) {
		final Map<Integer, Integer> distances = new HashMap<Integer, Integer>();
		final Map<Integer, Vertex> previous = new HashMap<Integer, Vertex>();
		PriorityQueue<Vertex> nodes = new PriorityQueue<Vertex>();

		for (Integer vertex : vertices.keySet()) {
			if (vertex == start) {
				distances.put(vertex, 0);
				nodes.add(new Vertex(vertex, 0, "", 0));
			} else {
				distances.put(vertex, Integer.MAX_VALUE);
				nodes.add(new Vertex(vertex, Integer.MAX_VALUE, "", 0));
			}
			previous.put(vertex, null);
		}

		while (!nodes.isEmpty()) {
			Vertex smallest = nodes.poll();
			if (smallest.getId() == finish) {
				final List<Integer> path = new ArrayList<Integer>();
				while (previous.get(smallest.getId()) != null) {
					path.add(smallest.getId());
					smallest = previous.get(smallest.getId());
				}
				return path;
			}

			if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
				break;
			}

			for (Vertex neighbor : vertices.get(smallest.getId())) {
				Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
				System.out.println(alt);

				if (alt < distances.get(neighbor.getId())) {
					distances.put(neighbor.getId(), alt);
					previous.put(neighbor.getId(), smallest);

					forloop: for (Vertex n : nodes) {
						if (n.getId() == neighbor.getId()) {
							nodes.remove(n);
							n.setDistance(alt);
							nodes.add(n);
							break forloop;
						}
					}
				}
			}
		}

		return new ArrayList<Integer>(distances.keySet());
	}

	/**
	 * 得到最短路径的线路
	 *  returnType: List<Integer> Description:
	 * 
	 * @author sunshanwei
	 * @date 2017年3月16日 下午4:42:16
	 */
	public List<Integer> getShortPath(List<Integer> idList) {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer tmp : idList) {
			for (Map.Entry<Integer, List<Vertex>> entry : vertices.entrySet()) {
				if (tmp == entry.getKey()) {
					if (entry.getValue().size() != 0) {
						list.add(entry.getValue().get(0).getRoadId());
					}

				}

			}
		}
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 1; j > i; j--) {

				if (list.get(i) == (list.get(j))) {
					list.remove(j);
				}

			}
		}
		return list;
	}

}