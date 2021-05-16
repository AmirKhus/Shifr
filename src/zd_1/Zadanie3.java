package zd_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Character.toUpperCase;

public class Zadanie3 {
    static char[] arr_en = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static char[] textAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static char[] concolAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static char[] zashifrovanni = {'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'v'};

    static double[] textChastotaLetter = new double[arr_en.length];
    static double[] consolChastotaLetter = new double[arr_en.length];
    static ArrayList<Character> textString = new ArrayList<>();
    static String consolString;
    static char[] lettersString;
    static double textAllLetter;
    static double concolAllLetter;
    static int quantityOfMtchesConcol;
    static int quantityOfMtches;
    static int a;
    static int Do;
    static final String file = "Data.txt";
    public static void main(String[] args) {
        start();
        consolMenu();
    }

//  вывод меню в консоль
    public static void consolMenu(){
        Do = -1;
        while (Do != 0) {
            System.out.print("Введите действие:\n" +
                    "1)Иследовать\n" +
                    "2)Дерасшифровать\n" +
                    "0)Выключить машину\n" +
                    "->");
            Scanner Do1 = new Scanner(System.in);
            if (Do1.hasNextInt()) {
            Do = Do1.nextInt();
            switch (Do) {
                case (1)://Иследовать
                    clearFile();
                    System.out.println("Введите количесва символов, которое нажно взять символов за один шаг,Он не должен превышать кол-во символов в тектсте ");
                    Scanner quantityOfMtchesConcol1 = new Scanner(System.in);
                        if (quantityOfMtchesConcol1.hasNextInt()) {
                            System.out.println(!quantityOfMtchesConcol1.hasNextLine());
                            quantityOfMtchesConcol = quantityOfMtchesConcol1.nextInt();
                            writeTextConsole();
                        } else {
                            System.out.println("Вы ввели неккоректные данные!");
                            System.out.println();
                        }
                    break;
                case (2): //Взлом
                    writeTextConsole();
                    break;
                default:
                    break;
            }
            } else {
                System.out.println("Вы ввели неккоректные данные");
                System.out.println();
            }
        }
    }
// очистить файл
    public static void clearFile() {
        try {
            FileWriter fstream1 = new FileWriter(file);// конструктор с одним параметром - для перезаписи
            BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
            out1.write(""); // очищаем, перезаписав поверх пустую строку
            out1.close(); // закрываем
        } catch (Exception e) {
            System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }

//    метод для  взлома
    public static void mixingrequency() {
//        Выявления процента
        for (int j = 0; j < textChastotaLetter.length; j++) {
            textChastotaLetter[j] = textChastotaLetter[j] / textAllLetter * 100;
        }

        for (int j = 0; j < consolChastotaLetter.length; j++) {
            consolChastotaLetter[j] = consolChastotaLetter[j] / concolAllLetter * 100;
        }

//        упорядочить алфавит консоли по возрастанию
        for (int i = arr_en.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (consolChastotaLetter[j] > consolChastotaLetter[j + 1]) {
                    double tmp = consolChastotaLetter[j];
                    consolChastotaLetter[j] = consolChastotaLetter[j + 1];
                    consolChastotaLetter[j + 1] = tmp;
                    char letter = concolAlphabet[j];
                    concolAlphabet[j] = concolAlphabet[j + 1];
                    concolAlphabet[j + 1] = letter;
                }
            }
        }
        //        упорядочить алфавит консоли по возрастанию
        for (int i = arr_en.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (textChastotaLetter[j] > textChastotaLetter[j + 1]) {
                    double tmp = textChastotaLetter[j];
                    textChastotaLetter[j] = textChastotaLetter[j + 1];
                    textChastotaLetter[j + 1] = tmp;
                    char letter = textAlphabet[j];
                    textAlphabet[j] = textAlphabet[j + 1];
                    textAlphabet[j + 1] = letter;
                }
            }
        }
//     Замена символов
        for (int i = 0; i < consolString.length(); i++) {
            if (Character.isLetter(consolString.charAt(i)) && Character.isUpperCase(consolString.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (toUpperCase(concolAlphabet[j]) == consolString.charAt(i)) {
                        lettersString[i] = toUpperCase(textAlphabet[j]);
                    }
                }
            } else if (Character.isLetter(consolString.charAt(i)) && Character.isLowerCase(consolString.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (concolAlphabet[j] == consolString.charAt(i)) {
                        lettersString[i] = textAlphabet[j];
                    }
                }
            } else {
                lettersString[i] = consolString.charAt(i);
            }
        }

        System.out.println("Результат частотного шифрования");
        System.out.println(lettersString);
        System.out.println("Хотите заменить символы для точной дешифровки нажимте 1, иначе 0");
        Scanner Do1 = new Scanner(System.in);
        int Do2 = Do1.nextInt();
        if (Do2 == 1 ) {
            replacementCharacters();
        } else{
            consolMenu();
        }
    }

    //Замена символов для приближения к оригинальному
    public static void replacementCharacters() {
        Scanner Do1 = new Scanner(System.in);
        String s;
        System.out.print("Введите букву которую хотите заменить и ЧЕРЕЗ ПРОБЕЛ букву, на которую нужно ее заменить \n ->");
        s = Do1.nextLine();
        int b = 0;
        for (int i = 0; i < textAlphabet.length; i++) {
            if (s.charAt(0) == textAlphabet[i]) {
                textAlphabet[i]= s.charAt(2);
                System.out.println(s.charAt(2));
            }
        }
        mixingrequency();
    }


    //Сведение частот
    public static void mixingFrequency() {
//        Выявления процента
        for (int j = 0; j < textChastotaLetter.length; j++) {
            textChastotaLetter[j] = textChastotaLetter[j] / textAllLetter * 100;
        }

        for (int j = 0; j < consolChastotaLetter.length; j++) {
            consolChastotaLetter[j] = consolChastotaLetter[j] / concolAllLetter * 100;
        }

        for (int j = 0; j < textChastotaLetter.length; j++) {
            System.out.println(arr_en[j] + " -> " + textChastotaLetter[j] + "  " + consolChastotaLetter[j]);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        for (int j = 0; j < consolChastotaLetter.length; j++) {
            System.out.println(arr_en[j] + " -> " + consolChastotaLetter[j]);
        }
        for (int i = arr_en.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (consolChastotaLetter[j] > consolChastotaLetter[j + 1]) {
                    double tmp = consolChastotaLetter[j];
                    consolChastotaLetter[j] = consolChastotaLetter[j + 1];
                    consolChastotaLetter[j + 1] = tmp;
                    char letter = concolAlphabet[j];
                    concolAlphabet[j] = concolAlphabet[j + 1];
                    concolAlphabet[j + 1] = letter;
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        for (int j = 0; j < consolChastotaLetter.length; j++) {
            System.out.println(concolAlphabet[j] + " -> " + consolChastotaLetter[j]);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        for (int i = arr_en.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (textChastotaLetter[j] > textChastotaLetter[j + 1]) {
                    double tmp = textChastotaLetter[j];
                    textChastotaLetter[j] = textChastotaLetter[j + 1];
                    textChastotaLetter[j + 1] = tmp;
                    char letter = textAlphabet[j];
                    textAlphabet[j] = textAlphabet[j + 1];
                    textAlphabet[j + 1] = letter;
                }
            }
        }

        for (int j = 0; j < textChastotaLetter.length; j++) {
            System.out.println(textAlphabet[j] + " -> " + textChastotaLetter[j]);
        }

        for (int i = 0; i < consolString.length(); i++) {
            if (Character.isLetter(consolString.charAt(i)) && Character.isUpperCase(consolString.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (toUpperCase(concolAlphabet[j]) == consolString.charAt(i)) {
                        lettersString[i] = toUpperCase(textAlphabet[j]);
                    }
                }
            } else if (Character.isLetter(consolString.charAt(i)) && Character.isLowerCase(consolString.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (concolAlphabet[j] == consolString.charAt(i)) {
                        lettersString[i] = textAlphabet[j];
                    }
                }
            } else {
                lettersString[i] = consolString.charAt(i);
            }
        }
////        System.out.println(a);
//        System.out.print(arr_en);

        for (int j = 0; j < textChastotaLetter.length; j++) {
            System.out.println(textAlphabet[j] + " -> " + concolAlphabet[j]);
        }

        quantityOfMtches = 0;

        for (int i = 0; i < arr_en.length; i++) {
            for (int j = 0; j < arr_en.length; j++) {
                if (arr_en[i] == textAlphabet[j]) {
                    if (zashifrovanni[i] == concolAlphabet[j]) {
                        quantityOfMtches++;
                        System.out.println(zashifrovanni[i] + "->" + concolAlphabet[j]);
                    }
                }
            }
        }
        System.out.println("Количесво символов -> Процент совпадения");
//double procent = quantityOfMtches/arr_en.length*100; !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Почему так ответ 0.0.
        double procent = quantityOfMtches;
        double procent1 = arr_en.length;
        double procent2 = procent / procent1 * 100;
        System.out.println(quantitySimvol + " - >" + procent2);
        writeDate(quantitySimvol, procent2);

        System.out.println("Результат частотного шифрования");
        System.out.println(lettersString);
        System.out.println("************************************************************************************************************************");
    }

//    Количество верных совпадения
//    public static void numberOfCorrectMatches(){
//        for (int i = 0; i <arr_en.length ; i++) {
//            for (int j = 0; j < arr_en.length; j++) {
//                if (arr_en[i] == textAlphabet[j]){
//                    if (zashifrovanni[i]== concolAlphabet[j]) {
//                        quantityOfMtches++;
//                    }
//                }
//            }
//        }
//        System.out.println("Количесво совпадения"+quantityOfMtches);
//    }

//Записать данные в файл
    public static void writeDate(int quantitySimvol, double procent2) {
        System.out.println("Данные заисаны в файл.");
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(quantitySimvol + "," + (int) procent2 + "\n");
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    static int quantitySimvol = 0;

    //Считывания теста из консоли
    public static void writeTextConsole() {
        System.out.print("Введите текст ->");
        Scanner string1 = new Scanner(System.in);
        consolString = string1.nextLine();
        lettersString = new char[consolString.length()];
        if(Do == 1) {
            for (int i = 0; i < consolString.length() / quantityOfMtchesConcol; i++) {
                quantitySimvol += quantityOfMtchesConcol;
                encryption(quantitySimvol);
                System.out.println("Количесво взятых символов - >" + quantitySimvol);
            }
        }else{
            encryption(5800);
        }
    }

    //    Подчет символов из консоли
    public static void encryption(int quantitySimvol) {
        a = 0;
        obnylyatorConsol();
        for (int i = 0; i < quantitySimvol; i++) {
            a++;
            if (Character.isLetter(consolString.charAt(i)) && Character.isUpperCase(consolString.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (toUpperCase(arr_en[j]) == consolString.charAt(i)) {
                        consolChastotaLetter[j]++;
                    }
                    concolAllLetter++;
                }
            } else if (Character.isLetter(consolString.charAt(i)) && Character.isLowerCase(consolString.charAt(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (arr_en[j] == consolString.charAt(i)) {
                        consolChastotaLetter[j]++;
                    }
                }
            }
        }
        if(Do == 1) {
            podchet();
            quantityOfMtches = 0;
            mixingFrequency();
        }else{
            podchet();
            mixingrequency();
        }
    }

    private static void obnylyatorConsol() {
        concolAllLetter = 0;
        for (int j = 0; j < consolChastotaLetter.length; j++) {
            consolChastotaLetter[j] = 0;
        }
        System.arraycopy(arr_en, 0, concolAlphabet, 0, arr_en.length);
    }


    //            Подчет символов из текста
    private static void podchet() {
        obnylyatorText();
        for (int i = 0; i < textString.size(); i++) {
            if (Character.isLetter(textString.get(i)) && Character.isUpperCase(textString.get(i))) {
                for (int j = 0; j < arr_en.length; j++) {
                    if (toUpperCase(arr_en[j]) == textString.get(i)) {
                        textChastotaLetter[j] = textChastotaLetter[j] + 1;
                    }
                    textAllLetter++;
                }
            } else if (Character.isLetter(textString.get(i)) && Character.isLowerCase(textString.get(i))) {

                for (int j = 0; j < arr_en.length; j++) {
                    if (arr_en[j] == textString.get(i)) {
                        textChastotaLetter[j] = textChastotaLetter[j] + 1;
                    }
                    textAllLetter++;
                }
            }
        }
    }

    //          Обнулятор
    private static void obnylyatorText() {
        textAllLetter = 0;
        for (int j = 0; j < textChastotaLetter.length; j++) {
            textChastotaLetter[j] = 0;
        }
        System.arraycopy(arr_en, 0, textAlphabet, 0, arr_en.length);
    }

    //Счиытвания частоты из файла
    private static void start() {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("voyna-i-mir-tom-1.txt")));

            int c;
            while ((c = reader.read()) != -1) {
                if (Character.isLetter(c))
                    textString.add((char) c);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
