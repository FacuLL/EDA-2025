package ar.edu.itba.eda;

public class Main {
    public static void main(String[] args) {

        URLfy urlfy = new URLfy();

        char [] ejemplo = new char[] { 'e', 's', ' ', 'u', 'n', ' ', 'e', 'j', 'e', 'm', 'p', 'l', 'o', '\0', '\0', '\0', '\0'};
        urlfy.reemplazarEspacios(ejemplo);
        System.out.println(ejemplo);


        ejemplo= new char [] {'a', ' ', 'b', ' ', 'c', ' ', 'd', ' ', 'e', ' ', 'f', ' ', 'g', ' ', 'h', 'o', 'l', 'a', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
        urlfy.reemplazarEspacios(ejemplo);
        System.out.println(ejemplo);


        ejemplo= new char [] {' ', ' ', 'e', 's', 'p', 'a', 'c', 'i', 'o', 's', ' ', ' ', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
        urlfy.reemplazarEspacios(ejemplo);
        System.out.println(ejemplo);

    }
}