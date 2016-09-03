package com.weischool.pojo.restful;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * <p>
 * Title:RestFulInfo
 * </p>
 * <p>
 * RestFul的实体类:
 * </p>
 * 
 * @author 许增飞
 * @date 上午10:45:53
 */
@Entity
@Table(name = "restful_info")
public class RestfulInfo {
	/**
	 * 主键
	 */
	private String Id;
	/**
	 * 名称
	 */
	private String Name;
	/**
	 * 创建时间
	 */
	private Long ct;

	/**
	 * 有参构造函数
	 * 
	 * @param id
	 *            主键
	 * @param name
	 *            名称
	 * @param ct
	 *            创建时间
	 */
	public RestfulInfo(String id, String name, Long ct) {
		this.Id = id;
		this.Name = name;
		this.ct = ct;
	}

	/**
	 * 无参构造函数
	 */
	public RestfulInfo() {

	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"Id\":\"" + Id + "\",\"Name\":\"" + Name + "\",\"ct\":\""
				+ ct + "\"}";
	}

	/**
	 * 获取id
	 * 
	 * @return id id
	 */
	@Id
	@GeneratedValue(generator="hibernate-uuid")
	@GenericGenerator(name="hibernate-uuid", strategy="uuid")
	@Column(name="id",length=36)
	public String getId() {
		return Id;
	}

	/**
	 * 设置id
	 * 
	 * @param id
	 *            id
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * 获取name
	 * 
	 * @return name name
	 */
	@Column(name="name",length=10)
	public String getName() {
		return Name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return ct 创建时间
	 */
	@Column(name="ct")
	public Long getCt() {
		return ct;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param ct
	 *            创建时间
	 */
	public void setCt(Long ct) {
		this.ct = ct;
	}

}
