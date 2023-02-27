package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Critique;
import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.entities.shopping.Transaction;
import esprit.tunisiacamp.repositeries.CritiqueRepositery;
import esprit.tunisiacamp.repositeries.ShopRepositery;
import esprit.tunisiacamp.repositeries.ToolRepositery;
import esprit.tunisiacamp.repositeries.TransactionRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicesISK implements IServicesISK {
    @Autowired
    CritiqueRepositery cr_repo;
    @Autowired
    ToolRepositery to_repo;
    @Autowired
    TransactionRepositery tr_repo;
    @Autowired
    ShopRepositery sh_repo;

    //region tool
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

    //endregion tool

    //region transaction
    @Override
    public Transaction addTransaction(Transaction transaction) {
        return tr_repo.save(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        tr_repo.delete(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return tr_repo.save(transaction);
    }

    @Override
    public List<Transaction> getMyTransactions(long my_id) {
        List<Transaction> output = new ArrayList<Transaction>();
        for (Transaction tr:tr_repo.findAll()) {
            if (tr.getShopper().getIdUser()==my_id)output.add(tr);
        }
        return output;
    }

    //endregion transaction

    //region critique
    @Override
    public Critique addCritique(Critique critique) {
        return cr_repo.save(critique);
    }

    @Override
    public void deleteCritique(Critique critique) {
        cr_repo.delete(critique);
    }

    @Override
    public Critique updateCritique(Critique critique) {
        return cr_repo.save(critique);
    }

    @Override
    public List<Critique> getCritiquesOfTool(long tool_id) {
        List<Critique> output = new ArrayList<Critique>();
        for (Critique cr:cr_repo.findAll()) {
            if (cr.getTool().getIdTool()==tool_id)output.add(cr);
        }
        return output;
    }
    //endregion critique

    //region shop
    @Override
    public User getShopById(long shop_id) {
        User output=new User();
        for (User sh:sh_repo.findAll()) {
            if (sh.getRole().getRole().toString().equals("SHOP") && sh.getIdUser()==shop_id)
                output=sh;
        }
        return output;
    }

    @Override
    public List<User> getShops() {
        List<User> output = new ArrayList<User>();
        for (User sh:sh_repo.findAll()) {
            if (sh.getRole().getRole().toString().equals("SHOP"))output.add(sh);
        }
        return output;
    }
    //endregion shop
}
