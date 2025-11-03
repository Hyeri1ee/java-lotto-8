package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.Error;
import lotto.domain.Lotto;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Input {

    //로또 구입 금액을 입력
    public int readPurchaseAmount() {
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    //당첨 번호 입력 (쉼표 구분)
    public Lotto readWinningNumbers() {
        String input = Console.readLine();
        List<Integer> numbers = parseNumbers(input);
        return new Lotto(numbers);
    }

    //보너스 번호 입력
    public int readBonusNumber() {

        String input = Console.readLine();
        return Integer.parseInt(input);//
    }

//당첨번호 입력의 쉼표 구분 내장 함수
    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
                    
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Error.INVALID_NUMBER_FORMAT.getMessage());
        }
    }
}
