package Entities;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class Payment {
    public static void main(String[] args) {
// Set your secret key here
        Stripe.apiKey = "sk_test_51OphDrASsnDruPyJOySx5PqgM1hr93yUE8nqPgaEd6cADAEW9x5ec18RJRcDnIYyJAuj1I0sqCau0UCAqqIzzacu00LqAp5eol";

        try {
// Retrieve your account information
            Account account = Account.retrieve();
            System.out.println("Account ID: " + account.getId());
// Print other account information as needed
        } catch (StripeException e) {
            e.printStackTrace();
        }

    }}