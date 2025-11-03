package lotto.controller;

import lotto.constants.Command;
import lotto.constants.Error;
import lotto.constants.Rank;
import lotto.domain.Lotto;
import lotto.view.Input;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import camp.nextstep.edu.missionutils.Randoms;

//너무 더러운 거 같음......
public class LottoController {
    private static final int LOTTO_PRICE = 1000;//상수 (로또 1개의 가격)
    private List<Lotto> lottos = new ArrayList<>();//처음 구매 로또 리스트

    private int purchaseAmount;//구입 금액
    private Lotto winningLotto;//당첨 번호
    private int bonusNumber;//보너스 번호

    //시작 함수
    public void run() {
        Input input = new Input();
        
        System.out.println(Command.COMMAND_BUY_PRICE.getMessage());
        purchaseAmount = input.readPurchaseAmount();//가격
        
        int lottoCount = calculateLottoCount(purchaseAmount);
        generateLottos(lottoCount);
        printLottos(lottoCount);
        
        System.out.println(Command.COMMAND_WON_NUMBERS.getMessage());
        winningLotto = input.readWinningNumbers();
        
        System.out.println(Command.COMMAND_BONUS_NUMBERS.getMessage());
        bonusNumber = input.readBonusNumber();
        validateBonusNumber();
        
        printStatistics();
    }

//통계 출력
    private void printStatistics() {
        System.out.println(Command.COMMAND_STATISTICS.getMessage());
        System.out.println("---");
        
        Map<Rank, Integer> rankCount = calculateRankCount();
        printRankStatistics(rankCount);
        printProfitRate(rankCount);
    }

    private void printProfitRate(Map<Rank, Integer> rankCount) {
        long totalPrize = calculateTotalPrize(rankCount);
        double profitRate = calculateProfitRate(totalPrize);
        System.out.println(Command.COMMAND_RESULT.getMessage() + formatProfitRate(profitRate) + "%입니다.");
    }

    private long calculateTotalPrize(Map<Rank, Integer> rankCount) {
        long totalPrize = 0;
        for (Rank rank : Rank.values()) {
            if (rank != Rank.NONE) {
                totalPrize += (long) rank.getPrize() * rankCount.get(rank);
            }
        }
        return totalPrize;
    }

    private double calculateProfitRate(long totalPrize) {
        return (double) totalPrize / purchaseAmount * 100;
    }

    private String formatProfitRate(double profitRate) {
        return String.format("%.1f", Math.round(profitRate * 10) / 10.0);
    }

    //랭크 순서 계산
    private Map<Rank, Integer> calculateRankCount() {
        Map<Rank, Integer> rankCount = new HashMap<>();
        
        for (Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }
        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatchingNumbers(winningLotto);
            boolean hasBonus = lotto.getNumbers().contains(bonusNumber);
            Rank rank = Rank.valueOf(matchCount, hasBonus);
            rankCount.put(rank, rankCount.get(rank) + 1);
        }
        
        return rankCount;
    }

    private void printRankStatistics(Map<Rank, Integer> rankCount) {
        printRank(Rank.FIFTH, rankCount);
        printRank(Rank.FOURTH, rankCount);
        printRank(Rank.THIRD, rankCount);
        printRank(Rank.SECOND, rankCount);
        printRank(Rank.FIRST, rankCount);
    }
//위 함수 내장 함수
    private void printRank(Rank rank, Map<Rank, Integer> rankCount) {
        if (rank != Rank.NONE) {
            String message = rank.getDescription() + " (" + formatPrize(rank.getPrize()) + "원) - " + rankCount.get(rank) + "개";
            System.out.println(message);
        }
    }

    private String formatPrize(int prize) {
        return String.format("%,d", prize);
    }

    private void validateBonusNumber() {
        validateBonusNumberRange();
        validateBonusNumberDuplicate();
    }

    //로또 번호와 똑같이 범위 적용
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
