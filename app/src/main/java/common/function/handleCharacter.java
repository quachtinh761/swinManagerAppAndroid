package common.function;

public class handleCharacter {
    /**
     * input char is '[' => 'â'
     **/
    public static char decodeChar(char input){
        char result = 0;
        switch (input){
            case ' ':
                break;
            default:
                result = input;
                break;
        }
        return result;
    }

    /**
     * input char is 'â' => '['
     **/
    public static char encodeChar(char input){
        char result = 0;
        switch (input){
            case 'â':
                break;
            default:
                result = input;
                break;
        }
        return result;
    }
}
