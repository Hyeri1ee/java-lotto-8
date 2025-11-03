package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class Input {

    //로또 구입 금액을 입력
    public int readPurchaseAmount() {
        String input = Console.readLine();
        return Integer.parseInt(input);
    }
}
