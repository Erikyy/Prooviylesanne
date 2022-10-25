package ee.erik.core.domain.entities.participant;

public enum PaymentMethod {
    BankTransfer("BANK_TRANSFER"),
    Cash("CASH");

    public final String value;

    PaymentMethod(String value) {
        this.value = value;
    }
}
