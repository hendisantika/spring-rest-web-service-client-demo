package com.hendisantika;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "company-info", namespace = "com.hendisantika")
@XmlAccessorType(XmlAccessType.NONE)
public class Company {
	@XmlAttribute(name = "id")
	private Integer id;
	@XmlElement(name = "company-name")
	private String companyName;
	@XmlElement(name = "ceo-name")
	private String ceoName;
	@XmlElement(name = "no-emp")
	private Integer noEmp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public Integer getNoEmp() {
		return noEmp;
	}
	public void setNoEmp(Integer noEmp) {
		this.noEmp = noEmp;
	}
} 
