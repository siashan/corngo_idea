package com.corngo.base.support.utils;

/**
 * 最短路径算法节点路径存储类
 *  Title: Vertex Description:
 * 
 * @author sunshanwei
 * @date 2017年3月16日 上午10:37:27
 */
public class Vertex implements Comparable<Vertex> {

	public Integer getRoadId() {
		return roadId;
	}

	public void setRoadId(Integer roadId) {
		this.roadId = roadId;
	}

	private Integer id;
	private Integer distance;
	private String stationName;
	private Integer roadId;
	public Vertex(Integer id, Integer distance,String stationName,Integer roadId) {
		super();
		this.id = id;
		this.distance = distance;
		this.stationName = stationName;
		this.roadId=roadId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Integer getId() {
		return id;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [id=" + id + ", distance=" + distance + "]";
	}

	@Override
	public int compareTo(Vertex o) {
		if (this.distance < o.distance)
			return -1;
		else if (this.distance > o.distance)
			return 1;
		else
			return this.getId().compareTo(o.getId());
	}

}