package lottery;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @ParameterizedTest
    @CsvSource(value={"500:0", "1000:1", "14000:14", "14500:14"}, delimiter = ':')
    void purchase(int cashAmount, int purchasedLotteryCount) {
        Customer customer = new Customer();
        customer.purchase(cashAmount);

        assertThat(customer.getLotteryWallet().getLotteries()).hasSize(purchasedLotteryCount);
    }

}