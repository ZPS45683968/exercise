package CheckCarNum;

public class CheckCardNum {
    boolean checkCardNumber (String cardNumber) {
        if(cardNumber.length() < 13 || cardNumber.length() > 19) {
            return false;
        }
        switch (cardNumber.charAt(0)) {
            case '4':
                if(cardNumber.length() != 13 && cardNumber.length() != 16 && cardNumber.length() != 19) {
                    return false;
                }
                break;
            case '3':
                if(cardNumber.length() != 15) {
                    return false;
                }
                if(cardNumber.charAt(1) != '4' && cardNumber.charAt(1) != '7') {
                    return false;
                }
                break;
            case '5':
                if(cardNumber.length() != 16) {
                    return false;
                }
                int num = Integer.parseInt(cardNumber.substring(0, 2));
                if(num < 51 || num > 55) {
                    return false;
                }
                break;
            case '2':
                if(cardNumber.length() != 16) {
                    return false;
                }
                int num2 = Integer.parseInt(cardNumber.substring(0, 4));
                if(num2 < 2221 || num2 > 2720) {
                    return false;
                }
                break;
            default:
                return false;
        }
        int sum = 0;
        int[] cardNumberArray = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            cardNumberArray[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
        }
        for (int i = 0; i < cardNumberArray.length; i++) {
            if (i % 2 != 0) {
                cardNumberArray[i] = cardNumberArray[i] * 2;
                if (cardNumberArray[i] > 9) {
                    cardNumberArray[i] = cardNumberArray[i] - 9;
                }
            }
            sum = sum + cardNumberArray[i];
        }
        if (sum % 10 == 0){
            return true;
        } else {
            return false;
        }
    }
}
