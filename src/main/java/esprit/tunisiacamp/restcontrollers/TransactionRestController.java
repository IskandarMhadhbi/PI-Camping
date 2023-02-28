package esprit.tunisiacamp.restcontrollers;

import esprit.tunisiacamp.entities.shopping.Transaction;
import esprit.tunisiacamp.services.TransactionIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionRestController {
    @Autowired
    TransactionIService transactionservice;

    @PostMapping("/transaction/addtransaction")
    public Transaction addTransaction(@RequestBody Transaction transaction){return transactionservice.addTransaction(transaction);}
    @DeleteMapping("/transaction/deletetransaction")
    public void deleteTransaction(@RequestBody Transaction transaction){transactionservice.deleteTransaction(transaction);}
    @PutMapping("/transaction/updatetransaction")
    public Transaction updateTransaction(@RequestBody Transaction transaction){return transactionservice.updateTransaction(transaction);}
    @GetMapping("/transaction/getmytransactions")
    public List<Transaction> getMyTransaction(@RequestParam long my_id){return transactionservice.getMyTransactions(my_id);}
}
