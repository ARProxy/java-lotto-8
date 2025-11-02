package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
    }

    @Test
    void 구입_금액에_맞는_개수의_로또를_구매한다() {
        List<LottoBuyResponse> lottos = lottoService.buyLotto(8000);
        assertThat(lottos).hasSize(8);
    }

    @Test
    void 일등_당첨_시_수익률을_계산한다() {
        List<LottoBuyResponse> lottos = List.of(
                new LottoBuyResponse(List.of(1, 2, 3, 4, 5, 6))
        );
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoStaticsResponse response = lottoService.staticsLotto(winningNumbers, bonusNumber, lottos);

        // 2,000,000,000원 / 1,000원 * 100 = 200,000,000%
        assertThat(response.rateOfReturn()).isEqualTo(200_000_000.0);
    }

    @Test
    void 오등_당첨_시_수익률을_계산한다() {
        List<LottoBuyResponse> lottos = List.of(
                new LottoBuyResponse(List.of(1, 2, 3, 10, 11, 12))
        );
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoStaticsResponse response = lottoService.staticsLotto(winningNumbers, bonusNumber, lottos);

        // 5,000원 / 1,000원 * 100 = 500%
        assertThat(response.rateOfReturn()).isEqualTo(500.0);
    }

    @Test
    void 당첨되지_않으면_수익률이_0이다() {
        List<LottoBuyResponse> lottos = List.of(
                new LottoBuyResponse(List.of(10, 11, 12, 13, 14, 15))
        );
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoStaticsResponse response = lottoService.staticsLotto(winningNumbers, bonusNumber, lottos);

        assertThat(response.rateOfReturn()).isEqualTo(0.0);
    }
}
