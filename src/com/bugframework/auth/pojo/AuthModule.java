package com.bugframework.auth.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.bugframework.common.pojo.IdEntity;
import com.bugframework.common.utility.DataUtils;

@Entity
@Table(name="auth_module")
public class AuthModule extends IdEntity implements java.io.Serializable{

	private String pid;
	private String name;//姓名
	private String url;
	private String icon;//图标名称
    private Short floor;
    private Short isAdmin;
    @Column(name="pid",length=36,nullable=false)
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	@Column(name="name",length=20,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="url",length=100)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="floor",length=1)
	public Short getFloor() {
		return floor;
	}
	public void setFloor(Short floor) {
		this.floor = floor;
	}
	@Column(name="isadmin",length=1)
	public Short getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Short isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Column(name="icon",length=100)
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
