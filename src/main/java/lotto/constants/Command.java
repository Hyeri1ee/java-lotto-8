package lotto.constants;

public enum Command {
    COMMAND_BUY_PRICE("구입금액을 입력해 주세요."),
    COMMAND_BOUGHT("개를 구매했습니다."),
    COMMAND_WON_NUMBERS("당첨 번호를 입력해 주세요."),
    COMMAND_BONUS_NUMBERS("보너스 번호를 입력해 주세요."),
    COMMAND_STATISTICS("당첨 통계"),
    COMMAND_RESULT("총 수익률은 ");

    final String message;

    Command(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
