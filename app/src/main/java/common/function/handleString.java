package common.function;

public class handleString {

    public static String encodeString(String input){
        String result = "";
        for (int i = 0; i < input.length(); i++){
            result += encodeChar(input.charAt(i));
        }
        return result;
    }

    public static String encodeChar(char input){
        String result = "";
        switch (input) {
            case 'á':
                result = "1a";
                break;
            case 'à':
                result = "2a";
                break;
            case 'ả':
                result = "3a";
                break;
            case 'ã':
                result = "4a";
                break;
            case 'ạ':
                result = "5a";
                break;
            case 'ă':
                result = "6b";
                break;
            case 'ắ':
                result = "1b";
                break;
            case 'ằ':
                result = "2b";
                break;
            case 'ẳ':
                result = "3b";
                break;
            case 'ẵ':
                result = "4b";
                break;
            case 'ặ':
                result = "5b";
                break;
            case 'â':
                result = "6c";
                break;
            case 'ấ':
                result = "1c";
                break;
            case 'ầ':
                result = "2c";
                break;
            case 'ẩ':
                result = "3c";
                break;
            case 'ẫ':
                result = "4c";
                break;
            case 'ậ':
                result = "5c";
                break;
            case 'đ':
                result = "1d";
                break;
            case 'é':
                result = "1e";
                break;
            case 'è':
                result = "2e";
                break;
            case 'ẻ':
                result = "3e";
                break;
            case 'ẽ':
                result = "4e";
                break;
            case 'ẹ':
                result = "5e";
                break;
            case 'ê':
                result = "6f";
                break;
            case 'ế':
                result = "1f";
                break;
            case 'ề':
                result = "2f";
                break;
            case 'ể':
                result = "3f";
                break;
            case 'ễ':
                result = "4f";
                break;
            case 'ệ':
                result = "5f";
                break;
            case 'í':
                result = "1i";
                break;
            case 'ì':
                result = "2i";
                break;
            case 'ỉ':
                result = "3i";
                break;
            case 'ĩ':
                result = "4i";
                break;
            case 'ị':
                result = "5i";
                break;
            case 'ó':
                result = "1o";
                break;
            case 'ò':
                result = "2o";
                break;
            case 'ỏ':
                result = "3o";
                break;
            case 'õ':
                result = "4o";
                break;
            case 'ọ':
                result = "5o";
                break;
            case 'ô':
                result = "6p";
                break;
            case 'ố':
                result = "1p";
                break;
            case 'ồ':
                result = "2p";
                break;
            case 'ổ':
                result = "3p";
                break;
            case 'ỗ':
                result = "4p";
                break;
            case 'ộ':
                result = "5p";
                break;
            case 'ơ':
                result = "6q";
                break;
            case 'ớ':
                result = "1q";
                break;
            case 'ờ':
                result = "2q";
                break;
            case 'ở':
                result = "3q";
                break;
            case 'ỡ':
                result = "4q";
                break;
            case 'ợ':
                result = "5q";
                break;
            case 'ú':
                result = "1u";
                break;
            case 'ù':
                result = "2u";
                break;
            case 'ủ':
                result = "3u";
                break;
            case 'ũ':
                result = "4u";
                break;
            case 'ụ':
                result = "5u";
                break;
            case 'ư':
                result = "6v";
                break;
            case 'ứ':
                result = "1v";
                break;
            case 'ừ':
                result = "2v";
                break;
            case 'ử':
                result = "3v";
                break;
            case 'ữ':
                result = "4v";
                break;
            case 'ự':
                result = "5v";
                break;
            case 'ý':
                result = "1y";
                break;
            case 'ỳ':
                result = "2y";
                break;
            case 'ỷ':
                result = "3y";
                break;
            case 'ỹ':
                result = "4y";
                break;
            case 'ỵ':
                result = "5y";
                break;
            default:
                result = "0" + input;
                break;
        }
        return result;
    }

    public static String decodeString(String input){
        String result = "";
        for (int i = 0; i < input.length(); i += 2){
            result += decodeChar(input.substring(i, i + 2));
        }
        return result;
    }

    public static String decodeChar(String input){
        String result = "";
        switch (input) {
            case "1a":
                result = "á";
                break;
            case "2a":
                result = "à";
                break;
            case "3a":
                result = "ả";
                break;
            case "4a":
                result = "ã";
                break;
            case "5a":
                result = "ạ";
                break;
            case "6b":
                result = "ă";
                break;
            case "1b":
                result = "ắ";
                break;
            case "2b":
                result = "ằ";
                break;
            case "3b":
                result = "ẳ";
                break;
            case "4b":
                result = "ẵ";
                break;
            case "5b":
                result = "ặ";
                break;
            case "6c":
                result = "â";
                break;
            case "1c":
                result = "ấ";
                break;
            case "2c":
                result = "ầ";
                break;
            case "3c":
                result = "ẩ";
                break;
            case "4c":
                result = "ẫ";
                break;
            case "5c":
                result = "ậ";
                break;
            case "1d":
                result = "đ";
                break;
            case "1e":
                result = "é";
                break;
            case "2e":
                result = "è";
                break;
            case "3e":
                result = "ẻ";
                break;
            case "4e":
                result = "ẽ";
                break;
            case "5e":
                result = "ẹ";
                break;
            case "6f":
                result = "ê";
                break;
            case "1f":
                result = "ế";
                break;
            case "2f":
                result = "ề";
                break;
            case "3f":
                result = "ể";
                break;
            case "4f":
                result = "ễ";
                break;
            case "5f":
                result = "ệ";
                break;
            case "1i":
                result = "í";
                break;
            case "2i":
                result = "ì";
                break;
            case "3i":
                result = "ỉ";
                break;
            case "4i":
                result = "ĩ";
                break;
            case "5i":
                result = "ị";
                break;
            case "1o":
                result = "ó";
                break;
            case "2o":
                result = "ò";
                break;
            case "3o":
                result = "ỏ";
                break;
            case "4o":
                result = "õ";
                break;
            case "5o":
                result = "ọ";
                break;
            case "6p":
                result = "ô";
                break;
            case "1p":
                result = "ố";
                break;
            case "2p":
                result = "ồ";
                break;
            case "3p":
                result = "ổ";
                break;
            case "4p":
                result = "ỗ";
                break;
            case "5p":
                result = "ộ";
                break;
            case "6q":
                result = "ơ";
                break;
            case "1q":
                result = "ớ";
                break;
            case "2q":
                result = "ờ";
                break;
            case "3q":
                result = "ở";
                break;
            case "4q":
                result = "ỡ";
                break;
            case "5q":
                result = "ợ";
                break;
            case "1u":
                result = "ú";
                break;
            case "2u":
                result = "ù";
                break;
            case "3u":
                result = "ủ";
                break;
            case "4u":
                result = "ũ";
                break;
            case "5u":
                result = "ụ";
                break;
            case "6v":
                result = "ư";
                break;
            case "1v":
                result = "ứ";
                break;
            case "2v":
                result = "ừ";
                break;
            case "3v":
                result = "ử";
                break;
            case "4v":
                result = "ữ";
                break;
            case "5v":
                result = "ự";
                break;
            case "1y":
                result = "ý";
                break;
            case "2y":
                result = "ỳ";
                break;
            case "3y":
                result = "ỷ";
                break;
            case "4y":
                result = "ỹ";
                break;
            case "5y":
                result = "ỵ";
                break;
            default:
                result = input.substring(1, 2);
                break;
        }
        return result;
    }

}
