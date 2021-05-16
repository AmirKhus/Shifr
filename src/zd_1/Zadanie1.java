package zd_1;

import java.text.MessageFormat;
import java.util.Scanner;

public class Zadanie1 {
    static int Do;

    public static void main(String[] args) {
        Do = -1;
        while (Do != 0) {
            System.out.print("Введите действие:\n" +
                    "1)зашифровать\n" +
                    "2)расшифровать\n" +
                    "3)взломать шифр\n" +
                    "0)Выключить машину\n" +
                    "->");
            Scanner Do1 = new Scanner(System.in);
            Do = Do1.nextInt();
            switch (Do) {
                case (1)://Зашифровка
                    startWord();
                    encryption();
                    break;
                case (2): //Расшифровка
                    startWord();
                    decryption();
                    break;
                case (3)://Взлом
                    startWord();
                    hacking();
                    break;
                default:
                    break;
            }
        }
    }

    static String string;
    static int key;
    static char[] lettersString;

    public static void startWord() {
        System.out.print("Введите текст ->");
        Scanner string1 = new Scanner(System.in);
        string = string1.nextLine();
        if (Do != 3) {
            System.out.print("Введите шаг ->");
            Scanner key1 = new Scanner(System.in);
            key = key1.nextInt();
        }
        lettersString = new char[string.length()];
    }

    //Зашифрка
    public static void encryption() {
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i)) && Character.isUpperCase(string.charAt(i)))
            {
                System.out.println(string.charAt(i));
                lettersString[i] = (char) (((((int) (string.charAt(i))) - 'A' + key) % 26) + 'A');
            } else if (Character.isLetter(string.charAt(i)) && Character.isLowerCase(string.charAt(i)))
            {
                lettersString[i] = (char) (((((int) (string.charAt(i))) - 'a' + key) % 26) + 'a');
            } else
                {
                lettersString[i] = string.charAt(i);
            }
        }
        System.out.print("Результат зашифровки->");
        System.out.println(lettersString);
    }

    //Расшифровка
    public static void decryption() {
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i)) && Character.isUpperCase(string.charAt(i))) {
                lettersString[i] = (char) (((((int) (string.charAt(i))) - 'A' - key + 26) % 26) + 'A');
            } else if (Character.isLetter(string.charAt(i)) && Character.isLowerCase(string.charAt(i))) {
                lettersString[i] = (char) (((((int) (string.charAt(i))) - 'a' - key + 26) % 26) + 'a');
            } else {
                lettersString[i] = string.charAt(i);
            }
        }

        System.out.print("Результат расшифровки->");
        System.out.println(lettersString);
    }

    //Взлом
    public static void hacking() {
        if (string.length() > 20) {
            hackingText(20);
        }else {
            hackingText(string.length());
    }
}

    private static void hackingText(int length) {
        int hacKey = 1;
        while (hacKey != 27) {
            for (int i = 0; i < length; i++) {
                if (Character.isLetter(string.charAt(i)) && Character.isUpperCase(string.charAt(i))) {
                    lettersString[i] = (char) (((((int) (string.charAt(i))) - 'A' - hacKey + 26) % 26) + 'A');
                } else if (Character.isLetter(string.charAt(i)) && Character.isLowerCase(string.charAt(i))) {
                    lettersString[i] = (char) (((((int) (string.charAt(i))) - 'a' - hacKey + 26) % 26) + 'a');
                } else {
                    lettersString[i] = string.charAt(i);
                }
            }
            System.out.print("Шаг зашифровки -> " + hacKey + " Текст -> ");
            System.out.println(lettersString);
            hacKey++;
        }
        System.out.println("Выберите нужный шаг");
        Scanner keуHack1 = new Scanner(System.in);
        key = keуHack1.nextInt();
        decryption();
    }
}
