package esprit.tunisiacamp.restcontrollers;

import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.services.ToolIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToolRestController {
    @Autowired
    ToolIService toolservice;

    @PostMapping("/tool/addtool")
    public Tool addTool(@RequestBody Tool tool){return toolservice.addTool(tool);}
    @DeleteMapping("/tool/deletetool")
    public void deleteTool(@RequestBody  Tool tool){toolservice.deleteTool(tool);}
    @PutMapping("/tool/updatetool")
    public Tool updateTool(@RequestBody Tool tool){return toolservice.updateTool(tool);}
    @GetMapping("/tool/gettools")
    public List<Tool> getTools(){return toolservice.getTools();}
}
