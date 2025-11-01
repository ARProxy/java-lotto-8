package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입 금액을 입력해 주세요.");
        //유효성 검사 : 천원단위인가?
        int pay = Integer.parseInt(Console.readLine());

        var lottoController = LottoController.getInstance().buyLotto(pay);

        System.out.println(lottoController);

        System.out.println("당첨 번호를 입력해 주세요.");
        String winningNumber = Console.readLine();

        System.out.println("보너스 번호를 입력해 주세요.");
        int bonusNumber = Integer.parseInt(Console.readLine());
    }
}
