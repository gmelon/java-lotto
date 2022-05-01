package lottoauto.domain;

import lottoauto.domain.LottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoGeneratorTest {

    @Test
    void lotto_valid_test(){
        assertThat(LottoGenerator.makeLotto().size()).isEqualTo(6);
    }

}