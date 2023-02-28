package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.repositeries.ToolRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService implements ToolIService {
    @Autowired
    ToolRepositery to_repo;

    @Override
    public Tool addTool(Tool tool) {
        return to_repo.save(tool);
    }

    @Override
    public void deleteTool(Tool tool) {
        to_repo.delete(tool);
    }

    @Override
    public Tool updateTool(Tool tool) {
        return to_repo.save(tool);
    }

    @Override
    public List<Tool> getTools() {
        return (List<Tool>) to_repo.findAll();
    }
}
