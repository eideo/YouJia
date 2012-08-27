package com.arc.entity;

/**
 * Module entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Module implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer parentId;
	private String name;
	private String url;
	private String functions;
	private Integer priority;
	private Integer display;
	private Integer level;
	private String remark;

	// Constructors

	/** default constructor */
	public Module() {
	}

	/** full constructor */
	public Module(Integer parentID, String name, String url, String functions,
			Integer priority, Integer display, Integer level, String remark) {
		this.parentId = parentID;
		this.name = name;
		this.url = url;
		this.functions = functions;
		this.priority = priority;
		this.display = display;
		this.level = level;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFunctions() {
		return this.functions;
	}

	public void setFunctions(String functions) {
		this.functions = functions;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getDisplay() {
		return this.display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}