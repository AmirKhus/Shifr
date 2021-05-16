package zd_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class Zadanie2 {

    static char[] arr_en = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static char[] perepeut = new char[arr_en.length];
    static String string;
    static char[] lettersString;
    static int Do;

    public static void main(String[] args) {
        start();
        Do = -1;
        while (Do != 0) {
            System.out.print("Введите действие:\n" +
                    "1)зашифровать\n" +
                    "2)расшифровать\n" +
                    "3)Генерация таблицы\n" +
                    "0)Выключить машину\n" +
                    "->");
            Scanner Do1 = new Scanner(System.in);
            Do = Do1.nextInt();
            switch (Do) {
                case (1)://Зашифровка
                    write();
                    shifrovan();
                    break;
                case (2): //Расшифровка
                    write();
                    rasshifrovka();
                    break;
                case (3)://«Генерация таблицы»
                    newShifr();
                    System.out.println("Генерация успешно выполнена");
                    break;
                default:
                    break;
            }
        }
    }
//  Ввод текста
    private static void write() {
        System.out.print("Введите текст ->");
        Scanner string1 = new Scanner(System.in);
        string = string1.nextLine();
        lettersString = new char[string.length()];
    }


    //Расшифровка
    private static void rasshifrovka() {
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i)) && Character.isUpperCase(string.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (toUpperCase(perepeut[j]) == string.charAt(i)) {
                        lettersString[i] = toUpperCase(arr_en[j]);
                    }
                }
            } else if (Character.isLetter(string.charAt(i)) && Character.isLowerCase(string.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (perepeut[j] == string.charAt(i)) {
                        lettersString[i] = arr_en[j];
                    }
                }
            } else {
                lettersString[i] = string.charAt(i);
            }
        }
        System.out.println(lettersString);
    }

//Зашифровка
    private static void shifrovan() {
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i)) && Character.isUpperCase(string.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (toUpperCase(arr_en[j]) == string.charAt(i)) {
                        lettersString[i] = toUpperCase(perepeut[j]);
                    }
                }
            } else if (Character.isLetter(string.charAt(i)) && Character.isLowerCase(string.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (arr_en[j] == string.charAt(i)) {
                        lettersString[i] = perepeut[j];
                    }
                }
            } else {
                lettersString[i] = string.charAt(i);
            }
        }
        System.out.println(lettersString);
    }

    //Счиытвания сгенерированного алфавита
    private static void start() {
        try {
            File file = new File("Zamena.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            for (int i = 0; i < perepeut.length; i++) {
                perepeut[i] = line.charAt(i);
            }
            while (line != null) {
                System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Создание нового алфавита с сопуствующим сохрпнение в  файл
    private static void newShifr() {
        Random rnd = new Random();
        System.arraycopy(arr_en, 0, perepeut, 0, arr_en.length);
        for (int i = perepeut.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            char a = perepeut[index];
            perepeut[index] = perepeut[i];
            perepeut[i] = a;
        }
        try (FileWriter writer = new FileWriter("Zamena.txt", false)) {
            // запись всей строки
            writer.write(perepeut);
            // запись по символам
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

