package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        int pay = readPurchaseAmount();
        var buyLotto = LottoController.getInstance().buyLotto(pay);

        System.out.println();
        System.out.println(buyLotto.size() + "개를 구매했습니다.");
        buyLotto.forEach(System.out::println);

        List<Integer> winningNumbers = readWinningNumbers();
        int bonusNumber = readBonusNumber(winningNumbers);

        var staticsLotto = LottoController.getInstance().getStatics(winningNumbers, bonusNumber, buyLotto);

        System.out.printf(
                """
                        당첨 통계
                        ---
                        3개 일치 (5,000원) - %d개
                        4개 일치 (50,000원) - %d개
                        5개 일치 (1,500,000원) - %d개
                        5개 일치, 보너스 볼 일치 (30,000,000원) - %d개
                        6개 일치 (2,000,000,000원) - %d개
                        총 수익률은 %.1f%%입니다.
                        %n""", staticsLotto.fifth(),
                staticsLotto.fourth(),
                staticsLotto.third(),
                staticsLotto.second(),
                staticsLotto.first(),
                staticsLotto.rateOfReturn()
        );
    }

    private static int readPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        while (true) {
            try {
                return InputValidator.validatePurchaseAmount(Console.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        while (true) {
            try {
                return InputValidator.validateWinningNumbers(Console.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int readBonusNumber(List<Integer> winningNumbers) {
        System.out.println("보너스 번호를 입력해 주세요.");
        while (true) {
            try {
                return InputValidator.validateBonusNumber(Console.readLine(), winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
