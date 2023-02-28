package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.shopping.Transaction;
import esprit.tunisiacamp.repositeries.TransactionRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService implements TransactionIService {
    @Autowired
    TransactionRepositery tr_repo;

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
}
