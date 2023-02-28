package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.shopping.Transaction;

import java.util.List;

public interface TransactionIService {
    Transaction addTransaction(Transaction transaction);
    void deleteTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    List<Transaction> getMyTransactions(long my_id);
}
