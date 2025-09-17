package ar.edu.itba.eda;

public class URLfy {

    public void reemplazarEspacios(char [] str) {
        int i = str.length - 1;
        while (str[i] == '\0') i--;
        int j = str.length - 1;
        while (i >= 0) {
            if (str[i] != ' ') {
                str[j--] = str[i--];
            } else {
                str[j--] = '0';
                str[j--] = '2';
                str[j--] = '%';
                i--;
            }
        }
    }
}
