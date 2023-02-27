package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.entities.shopping.Critique;
import esprit.tunisiacamp.entities.shopping.Transaction;

import java.util.List;

public interface IServicesISK {

    //region tool
    Tool addTool(Tool tool);
    void deleteTool(Tool tool);
    Tool updateTool(Tool tool);
    List<Tool> getTools();
    //endregion tool

    //region transaction
    Transaction addTransaction(Transaction transaction);
    void deleteTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    List<Transaction> getMyTransactions(long my_id);
    //endregion transaction

    //region critique
    Critique addCritique(Critique critique);
    void deleteCritique(Critique critique);
    Critique updateCritique(Critique critique);
    List<Critique> getCritiquesOfTool(long tool_id);
    //endregion critique

    //region shop
    User getShopById(long shop_id);
    List<User> getShops();
    //endregion shop

}
