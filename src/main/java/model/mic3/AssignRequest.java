package model.mic3;

import model.mic3.part4.ProjectNumber;

public class AssignRequest {
    private int id;
    private ProjectNumber projectNumber;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setProjectNumber(ProjectNumber projectNumber) {
        this.projectNumber = projectNumber;
    }

    public ProjectNumber getProjectNumber() {
        return projectNumber;
    }
}
