package model.mic3;

import model.mic3.part2.Architect;
import model.mic3.part2.Architects;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.mic3.part4.Project;

public class Mic3Service {
    private static final String PATH = "src/main/resources/Architects.json";
    Response response = new Response();

    Architects architects;

    public Response register(RegisterRequest c) {
        try {
            Architects architect = deserialize();
        } catch(Exception e) {
            response.setResponse(-1);
            return response;
        }

        if (architects.getArchitects().size() > 2) {
            response.setResponse(3);
            return response;
        }

        for (int i = 0; i< architects.getArchitects().size(); i++) {
            if (architects.getArchitects().get(i).getId() == c.getId()) {
                response.setResponse(4);
                return response;
            }
        }
        ArrayList<Architect> newList = new ArrayList<>();
        Architect newArchitect = new Architect();
        newArchitect.setId(c.getId());
        newList.add(newArchitect);
        architects.setArchitects(newList);

        try {
            serialize(architects);
        } catch(Exception e) {
            response.setResponse(-1);
            return response;
        }

        response.setResponse(0);
        return response;
    }

    public Response assign(AssignRequest c) {
        try {
            Architects architects = deserialize();

            if (architects == null) {
                response.setResponse(5);
                return response;
            }

            Architect targetArchitect = null;
            for (Architect architect : architects.getArchitects()) {
                if (architect.getId() == c.getId()) {
                    targetArchitect = architect;
                }
            }

            if (targetArchitect == null) {
                response.setResponse(6);
                return response;
            }

            if (targetArchitect.projects() == null) {
                targetArchitect.setProjects(new ArrayList<>());
            }
            if (targetArchitect.getProjectNumbers() == null) {
                targetArchitect.setProjectNumbers(new ArrayList<>());
            }

            for (Project existingProject: targetArchitect.projects()) {
                if (existingProject.getProjectNumber() == c.getProjectNumber()) {
                    response.setResponse(7);
                    return response;
                }
            }

            if (targetArchitect.getProjectNumbers().size() >= 2) {
                response.setResponse(3);
                return response;
            }

            Project project = new Project();
            project.setProjectNumber(c.getProjectNumber());

            targetArchitect.projects().add(project);
            targetArchitect.getProjectNumbers().add(c.getProjectNumber());

            serialize(architects);

            response.setResponse(0);
            return response;
        } catch (Exception e) {
            response.setResponse(-1);
            return response;
        }
    }

    public Response check(CheckRequest c) {
        try {
            Architects architects = deserialize();

            if (architects == null) {
                response.setResponse(-1);
                return response;
            }


            boolean projectFound = false;
            for (Architect architect : architects.getArchitects()) {
                if (architect.projects() != null) {
                    for (Project project : architect.projects()) {
                        if (project.getProjectNumber() == c.getProjectNumber()) {
                            projectFound = true;
                            break;
                        }
                    }
                }
            }

            if (projectFound == false) {
                response.setResponse(8);
                return response;
            }

            response.setResponse(0);
            return response;
        } catch (Exception e) {
            response.setResponse(-1);
            return response;
        }
    }

    private Architects deserialize() throws Exception {
        Path filePath = Paths.get(PATH);
        if (Files.exists(filePath)) {
            FileInputStream fstream = new FileInputStream(PATH);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            String jsonString4 = "", strLine = null;
            while((strLine = bf.readLine()) != null) jsonString4 += strLine;
            ObjectMapper mapper = new ObjectMapper();
            Architects architect = mapper.readValue(jsonString4, Architects.class);
            return architect;
        } else {
            return null;
        }
    }

    private void serialize(Architects architects) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(architects);
        BufferedWriter output = new BufferedWriter(new FileWriter(PATH));
        output.write(jsonString);
        output.close();
    }
}
