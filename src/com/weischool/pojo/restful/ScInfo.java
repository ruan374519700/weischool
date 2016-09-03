package com.weischool.pojo.restful;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.bugframework.common.pojo.IdEntity;
/**
 * @author 煎鱼
 * @since 2016-8-10
 * Description	学校信息表
 */
@Entity
@Table(name = "sc_info")
public class ScInfo extends IdEntity implements java.io.Serializable {
	/**
	 * 主键
	 */
	private String Id;
	/**
	 * 学校名称
	 */
	private String name;
	/**
	 * 校长/馆长
	 */
	private String head_master;
	/**
	 * 联系手机号码
	 */
	private String m_phone;
	/**
	 * 固定电话
	 */
	private String t_phone;
	/**
	 * 简介
	 */
	private String remark;
	/**
	 * 启用/禁用
	 */
	private Short is_enable;
	
	/**  
	 * 获取id  
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
	 * @param id id  
	 */
	public void setId(String id) {
		Id = id;
	}
	
	/**  
	 * 获取学校名称  
	 * @return name 学校名称  
	 */
	@Column(name="name",length=50)
	public String getName() {
		return name;
	}
	/**  
	 * 设置学校名称  
	 * @param name 学校名称  
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**  
	 * 获取校长馆长  
	 * @return head_master 校长馆长  
	 */
	@Column(name="head_master",length=50)
	public String getHead_master() {
		return head_master;
	}
	/**  
	 * 设置校长馆长  
	 * @param head_master 校长馆长  
	 */
	public void setHead_master(String head_master) {
		this.head_master = head_master;
	}
	
	/**  
	 * 获取联系手机号码  
	 * @return m_phone 联系手机号码  
	 */
	@Column(name="m_phone",length=20)
	public String getM_phone() {
		return m_phone;
	}
	/**  
	 * 设置联系手机号码  
	 * @param m_phone 联系手机号码  
	 */
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	
	/**  
	 * 获取固定电话  
	 * @return t_phone 固定电话  
	 */
	@Column(name="t_phone",length=20)
	public String getT_phone() {
		return t_phone;
	}
	/**  
	 * 设置固定电话  
	 * @param t_phone 固定电话  
	 */
	public void setT_phone(String t_phone) {
		this.t_phone = t_phone;
	}
	
	/**  
	 * 获取简介  
	 * @return remark 简介  
	 */
	@Column(name="remark",length=200)
	public String getRemark() {
		return remark;
	}
	/**  
	 * 设置简介  
	 * @param remark 简介  
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**  
	 * 获取启用禁用  
	 * @return is_enable 启用禁用  
	 */
	@Column(name="is_enable",length=1)
	public Short getIs_enable() {
		return is_enable;
	}
	/**  
	 * 设置启用禁用  
	 * @param is_enable 启用禁用  
	 */
	public void setIs_enable(Short is_enable) {
		this.is_enable = is_enable;
	}
	
}
