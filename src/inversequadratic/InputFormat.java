package inversequadratic;


import java.util.ArrayList;

public class InputFormat {

    private   static  String formatMult(String str){
        String[] split = str.split("\\^",2);
        String left = split[0];
        String right =split[1];
        char c =left.charAt(left.length() - 1);
        char c1 = right.charAt(0);
        int i = Integer.parseInt(String.valueOf(c1));
        String newRight = right.split(String.valueOf(right.charAt(0)),2)[1];
        String newLeft ="";
        for (int i1 = 0; i1 < left.length()-1; i1++) {

               newLeft+=String.valueOf(left.charAt(i1));

        }
        String mid= "";
        for (int i1 = 0; i1 < i; i1++) {
            if (i1!=0){
                mid+="*"+String.valueOf(c);
            }else {
                mid+=String.valueOf(c);
            }
        }
        String result  = newLeft+mid+newRight;
        return result;
    }
    public  static String format(String str){
      int n = count(str,'^');
        for (int i = 0; i < n; i++) {
            str = formatMult(str);
        }
        return str;
    }
    private static int count(String string,Character character) {
        ArrayList<Character> list = new ArrayList<>();
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            list.add(aChar);
        }

         int count = 0;
            for (int j = 0; j < chars.length; j++) {
                char aChar = chars[j];
                if (character.equals(aChar)) {
                    count++;
                }

        }
        return count;
    }

}
