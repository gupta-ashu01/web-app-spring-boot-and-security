package com.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "depreciation_methods")
public class DepreciationMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "method_short", nullable = false, length = 60)
	private String methodShort;

	@Column(name = "method_name", nullable = false, length = 60)
	private String methodName;

	@Column(name = "method_description", nullable = false, length = 300)
	private String methodDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMethodShort() {
		return methodShort;
	}

	public void setMethodShort(String methodShort) {
		this.methodShort = methodShort;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodDescription() {
		return methodDescription;
	}

	public void setMethodDescription(String methodDescription) {
		this.methodDescription = methodDescription;
	}

}
