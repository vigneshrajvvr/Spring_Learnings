package com.practice.vvr.springboot.Entity;

public class Details {

	private String projectName;
	private String concept;
	private int assignmentNumber;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public int getAssignmentNumber() {
		return assignmentNumber;
	}

	public void setAssignmentNumber(int assignmentNumber) {
		this.assignmentNumber = assignmentNumber;
	}

	public Details(String projectName, String concept, int assignmentNumber) {
		super();
		this.projectName = projectName;
		this.concept = concept;
		this.assignmentNumber = assignmentNumber;
	}

}
