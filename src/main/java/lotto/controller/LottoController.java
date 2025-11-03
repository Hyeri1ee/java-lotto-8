package lotto.controller;

import lotto.constants.Command;
import lotto.constants.Error;
import lotto.domain.Lotto;
import lotto.view.Input;
import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;
    private List<Lotto> lottos = new ArrayList<>();//처음 구매 로또 리스트

    private Lotto winningLotto;//당첨 번호
    private int bonusNumber;//보너스 번호

    //시작 함수
    public void run() {
        Input input = new Input();
        
        System.out.println(Command.COMMAND_BUY_PRICE.getMessage());
        int purchaseAmount = input.readPurchaseAmount();//가격
        
        int lottoCount = calculateLottoCount(purchaseAmount);
        generateLottos(lottoCount);
        printLottos(lottoCount);
        
        System.out.println(Command.COMMAND_WON_NUMBERS.getMessage());
        winningLotto = input.readWinningNumbers();
        
        System.out.println(Command.COMMAND_BONUS_NUMBERS.getMessage());
        bonusNumber = input.readBonusNumber();
        validateBonusNumber();
    }

    private void validateBonusNumber() {
        validateBonusNumberRange();
        validateBonusNumberDuplicate();
    }

    //똑같이 범위 적용
    private void validateBonusNumberRange() {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(Error.LOTTO_NUMBERS_RANGE.getMessage());
        }
    }

    //중복 제외
    private void validateBonusNumberDuplicate() {
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(Error.BONUS_NUMBER_DUPLICATE.getMessage());
        }
    }

    private int calculateLottoCount(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;//나머지는 버리기
    }

    //1~45 로또 번호 생성기
    private Lotto generateRandomLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }

    //count개수만큼 로또 생성기
    private void generateLottos(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(generateRandomLotto());
        }
    }

//로또 출력 함수
    private void printLottos(int lottoCount) {
        System.out.println(lottoCount + Command.COMMAND_BOUGHT.getMessage());
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getSortedNumbers());
        }
        //해당 부분 스트림으로 바꾸면 뭐가 더 나을까
        /*
        lottos.stream()
            .map(Lotto::getSortedNumbers)
            .forEach(System.out::println);
        */
    }
}
