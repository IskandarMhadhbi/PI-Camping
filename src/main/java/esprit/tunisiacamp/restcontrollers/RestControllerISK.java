package esprit.tunisiacamp.restcontrollers;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.entities.shopping.Transaction;
import esprit.tunisiacamp.entities.shopping.Critique;
import esprit.tunisiacamp.services.IServicesISK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestControllerISK {
    @Autowired
    IServicesISK service;

    //region tool
    @PostMapping("/ISK/addtool")
    public Tool addTool(@RequestBody Tool tool){return service.addTool(tool);}
    @DeleteMapping("/ISK/deletetool")
    public void deleteTool(@RequestBody  Tool tool){service.deleteTool(tool);}
    @PutMapping("/ISK/updatetool")
    public Tool updateTool(@RequestBody Tool tool){return service.updateTool(tool);}
    @GetMapping("/ISK/gettools")
    public List<Tool> getTools(){return service.getTools();}
    //endregion tool

    //region transaction
    @PostMapping("/ISK/addtransaction")
    public Transaction addTransaction(@RequestBody Transaction transaction){return service.addTransaction(transaction);}
    @DeleteMapping("/ISK/deletetransaction")
    public void deleteTransaction(@RequestBody Transaction transaction){service.deleteTransaction(transaction);}
    @PutMapping("/ISK/updatetransaction")
    public Transaction updateTransaction(@RequestBody Transaction transaction){return service.updateTransaction(transaction);}
    @GetMapping("/ISK/getmytransactions")
    public List<Transaction> getMyTransaction(@RequestParam long my_id){return service.getMyTransactions(my_id);}
    //endregion transaction

    //region critique
    @PostMapping("/ISK/addcritique")
    public Critique addCritique(@RequestBody Critique critique){return service.addCritique(critique);}
    @DeleteMapping("/ISK/deletecritique")
    public void deleteCritique(@RequestBody Critique critique){service.deleteCritique(critique);}
    @PutMapping("/ISK/updatecritique")
    public Critique updateCritique(@RequestBody Critique critique){return service.updateCritique(critique);}
    @GetMapping("/ISK/getcritiqueoftool")
    public List<Critique> getCritiqueOfTool(@RequestParam long tool_id){return service.getCritiquesOfTool(tool_id);}
    //endregion critique

    //region shop
    @GetMapping("/ISK/getshopbyid")
    public User getShopById(@RequestParam long shop_id){return service.getShopById(shop_id);}
    @GetMapping("/ISK/getshops")
    public List<User> getShops(){return service.getShops();}
    //endregion shop
}
