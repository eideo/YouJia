package com.arc.entity;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Module entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class Role implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String remark;
	private Set<Module> modules = Sets.newHashSet();

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String name, String remark) {
		this.name = name;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
}