package Banking;

public class NetBankingPayment implements Payment {

    private String bankId;
    private String password;

    public NetBankingPayment(String bankId, String password) {
        this.bankId = bankId;
        this.password = password;
    }

    @Override
    public void processPayment(int amt) throws TransactionFailedException {
        try {
            if (amt <= 0) throw new InvalidAmountException("Amount must be positive!");
            if (!"net123".equals(password)) throw new InvalidCredentialsException("Invalid Bank Credentials!");

            System.out.println("NetBanking Payment of " + amt + " processed successfully!");
        } catch (InvalidAmountException | InvalidCredentialsException e) {
            throw new TransactionFailedException("NetBanking Transaction Failed");
        }
    }
}
