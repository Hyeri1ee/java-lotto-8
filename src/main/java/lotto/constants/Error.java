package lotto.constants;

public enum Error {
    LOTTO_SIZE_6("[ERROR]", "로또 번호는 6개여야 합니다."),
    LOTTO_NUMBERS_DUPLICATE("[ERROR]", "로또 값은 중복이 불가합니다."),
    LOTTO_NUMBERS_RANGE("[ERROR]", "로또값은 1~45 값을 보유합니다."),
    INVALID_NUMBER_FORMAT("[ERROR]", "유효하지 않은 숫자 형식입니다."),
    BONUS_NUMBER_DUPLICATE("[ERROR]", "보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    final String debugLevel;
    final String message;
    final String sentence;

    Error(String debugLevel, String message) {
        this.debugLevel = debugLevel;
        this.message = message;
        this.sentence = this.debugLevel +" " + this.message;
    }

    public String getMessage(){
        return this.sentence;
    }
}
