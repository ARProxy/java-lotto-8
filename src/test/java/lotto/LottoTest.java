package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호의_개수가_6개_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 6개여야 합니다");
    }

    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호가_1보다_작으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다");
    }

    @Test
    void 로또_번호가_45보다_크면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다");
    }

    @Test
    void 당첨_통계를_정확히_계산한다_3개_일치() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        List<LottoBuyResponse> lottos = List.of(
                new LottoBuyResponse(List.of(1, 2, 3, 10, 11, 12))
        );

        Map<Winner, Long> statistics = Lotto.getStatistics(winningNumbers, bonusNumber, lottos);

        assertThat(statistics.get(Winner.FIFTH)).isEqualTo(1L);
        assertThat(statistics.get(Winner.FOURTH)).isEqualTo(0L);
    }

    @Test
    void 당첨_통계를_정확히_계산한다_5개_일치_보너스_일치() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        List<LottoBuyResponse> lottos = List.of(
                new LottoBuyResponse(List.of(1, 2, 3, 4, 5, 7))
        );

        Map<Winner, Long> statistics = Lotto.getStatistics(winningNumbers, bonusNumber, lottos);

        assertThat(statistics.get(Winner.SECOND)).isEqualTo(1L);
        assertThat(statistics.get(Winner.THIRD)).isEqualTo(0L);
    }
}
