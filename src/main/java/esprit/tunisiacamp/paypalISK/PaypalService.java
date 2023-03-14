package esprit.tunisiacamp.paypalISK;

import com.paypal.api.payments.*;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.PayPalRESTException;
import esprit.tunisiacamp.repositeries.TransactionRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService  {
    @Autowired
    private APIContext apiContext;

    public Payment createPayment( Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws PayPalRESTException, com.paypal.base.rest.PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(String.valueOf(apiContext));
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException, com.paypal.base.rest.PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(String.valueOf(apiContext), paymentExecute);
    }
    // *****************************
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    @Autowired
    private TransactionRepositery transactionRepositery;

    public RedirectView payment(@RequestBody esprit.tunisiacamp.entities.shopping.Transaction transaction) {
        //
        System.out.println(transaction);
        try {
            Payment payment = createPayment((double)transaction.getPrice(), "USD", "paypal",
                    "SALE", " description sample ", "http://localhost:1111/rest/" + CANCEL_URL,
                    "http://localhost:1111/rest/" + SUCCESS_URL);

            System.out.println("dd");
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    System.out.println("dc");

                    transactionRepositery.save(transaction);
                    System.out.println("success");
                    return new RedirectView(link.getHref());
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        } catch (com.paypal.base.rest.PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("http://localhost:1111/rest/cancel");
    }



}
