package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.shopping.Tool;

import java.util.List;

public interface ToolIService {
    Tool addTool(Tool tool);
    void deleteTool(Tool tool);
    Tool updateTool(Tool tool);
    List<Tool> getTools();
}
