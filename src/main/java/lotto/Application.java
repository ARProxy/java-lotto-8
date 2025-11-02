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

        var statistics = LottoController.getInstance().getStatics(winningNumbers, bonusNumber, buyLotto);

        printStatistics(statistics);
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

    private static void printStatistics(LottoStaticsResponse statistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.printf("3개 일치 (5,000원) - %d개%n", statistics.fifth());
        System.out.printf("4개 일치 (50,000원) - %d개%n", statistics.fourth());
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", statistics.third());
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", statistics.second());
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", statistics.first());
        System.out.printf("총 수익률은 %.1f%%입니다.%n", statistics.rateOfReturn());
    }
}
