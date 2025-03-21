package model.mic3.part2;

import java.util.ArrayList;
import model.mic3.part4.Project;
import model.mic3.part4.ProjectNumber;

public class Architect {
    private int id;
    private ArrayList<Project> projects;
    private ArrayList<ProjectNumber> projectNumbers;

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Project> projects() {
        return projects;
    }

    public void setProjectNumbers(ArrayList<ProjectNumber> projectNumbers) {
        this.projectNumbers = projectNumbers;
    }

    public ArrayList<ProjectNumber> getProjectNumbers() {
        return projectNumbers;
    }
}


