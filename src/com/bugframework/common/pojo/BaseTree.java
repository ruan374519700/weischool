package com.bugframework.common.pojo;

public class BaseTree {

	private String id ;
	private String pId;
	private String name;
	private String file;
	private boolean open =false;
	private boolean checked =false;
	
	
	/**
	 * 
	 * @param id
	 * @param pId
	 * @param name 名字
	 * @param file 访问url
	 * @param open  是否展开
	 * @param checked 是否选中
	 */
	public BaseTree(String id, String pId, String name,String file, boolean open,
			boolean checked) {
		this.id = id;
		this.pId = pId;
		this.name=name;
		this.file = file;
		this.open = open;
		this.checked = checked;
	}
	/**
	 * 
	 * @param id
	 * @param pId
	 * @param name 名字
	 * @param open  是否展开
	 * @param checked 是否选中
	 */
	public BaseTree(String id, String pId, String name,boolean open,boolean checked) {
		this.id = id;
		this.pId = pId;
		this.name=name;
		this.open = open;
		this.checked = checked;
	}

	/**
	 * 
	 * @param id
	 * @param pId
	 * @param name 名字
	 * @param file 访问url
	 * @param booleanv 是否打开还是关闭标记   true:打开    false  关闭
	 * @param statues  true 或者 false  和booleanvc一起使用
	 */
	public BaseTree(String id, String pId, String name, String file, boolean booleanv,String statues) {
		this.id = id;
		this.pId = pId;
		this.name=name;
		this.file = file;
		if(statues.equals("open")){
			this.open = booleanv;
		}else{
			this.checked = booleanv;
		}
	}
	

	 


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
