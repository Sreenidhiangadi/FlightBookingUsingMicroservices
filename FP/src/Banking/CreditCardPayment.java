package Banking;

public class CreditCardPayment implements Payment {

    private String cardNumber;
    private String password;

    public CreditCardPayment(String cardNumber, String password) {
        this.cardNumber = cardNumber;
        this.password = password;
    }

    @Override
    public void processPayment(int amt) throws TransactionFailedException {
        try {
            if (amt <= 0) throw new InvalidAmountException("Amount must be positive!");
            if (!"pass123".equals(password)) throw new InvalidCredentialsException("Invalid Card Credentials!");

            System.out.println("Credit Card Payment of ₹" + amt + " processed successfully!");
        } catch (InvalidAmountException | InvalidCredentialsException e) {
            throw new TransactionFailedException("Credit Card Transaction Failed");
        }
    }
}
