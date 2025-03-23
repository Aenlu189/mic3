package mic3;

import mic3.part2.Architect;
import mic3.part2.Architects;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import mic3.part4.Project;

public class Mic3Service {
    private static final String PATH = "src/main/resources/Architects.json";

    public Response register(RegisterRequest c) {
        Response response = new Response();

        try {
            Architects architects;
            architects = deserialize();

            if (architects == null) {
                architects = new Architects();
                architects.setArchitects(new ArrayList<>());
            } else if (architects.getArchitects() == null) {
                architects.setArchitects(new ArrayList<>());
            }

            if (architects.getArchitects().size() > 2) {
                response.setCode(3);
                return response;
            }

            for (int i = 0; i < architects.getArchitects().size(); i++) {
                if (architects.getArchitects().get(i).getId() == c.getId()) {
                    response.setCode(4);
                    return response;
                }
            }
            
            Architect newArchitect = new Architect();
            newArchitect.setId(c.getId());
            architects.getArchitects().add(newArchitect);

            serialize(architects);
            response.setCode(0);

        } catch(Exception e) {
            response.setCode(-1);
            return response;
        }

        return response;
    }

    public Response assign(AssignRequest c) {
        Response response = new Response();

        try {
            Architects architects;
            architects = deserialize();

            if (architects == null) {
                response.setCode(5);
                return response;
            }

            Architect targetArchitect = null;
            for (Architect architect: architects.getArchitects()) {
                if (architect.getId() == c.getId()) {
                    targetArchitect = architect;
                }
            }

            if (targetArchitect == null) {
                response.setCode(6);
                return response;
            }

            if (targetArchitect.getProjects() == null) {
                targetArchitect.setProjects(new ArrayList<>());
            }
            if (targetArchitect.getProjectNumbers() == null) {
                targetArchitect.setProjectNumbers(new ArrayList<>());
            }

            for (Project existingProject: targetArchitect.getProjects()) {
                if (existingProject.getProjectNumber() == c.getProjectNumber()) {
                    response.setCode(7);
                    return response;
                }
            }

            if (targetArchitect.getProjectNumbers().size() >= 2) {
                response.setCode(3);
                return response;
            }

            Project project = new Project();
            project.setProjectNumber(c.getProjectNumber());

            targetArchitect.getProjects().add(project);
            targetArchitect.getProjectNumbers().add(c.getProjectNumber());

            serialize(architects);

            response.setCode(0);
            return response;
        } catch (Exception e) {
            response.setCode(-1);
            return response;
        }
    }

    public Response check(CheckRequest c) {
        Response response = new Response();
        try {
            Architects architects = deserialize();

            if (architects == null) {
                response.setCode(-1);
                return response;
            }


            boolean projectFound = false;
            for (Architect architect : architects.getArchitects()) {
                if (architect.getProjects() != null) {
                    for (Project project : architect.getProjects()) {
                        if (project.getProjectNumber() == c.getProjectNumber()) {
                            projectFound = true;
                            break;
                        }
                    }
                }
            }

            if (projectFound == false) {
                response.setCode(8);
                return response;
            }

            response.setCode(0);
            return response;
        } catch (Exception e) {
            response.setCode(-1);
            return response;
        }
    }

    private Architects deserialize() throws Exception {
        Path filePath = Paths.get(PATH);
        if (Files.exists(filePath)) {
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(reader, Architects.class);
            }
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
