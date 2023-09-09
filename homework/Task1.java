package homework;


import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
// import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        System.setIn(new FileInputStream(FileDescriptor.in));
        
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            System.out.print("Введите данные (Фамилия Имя Отчество Датарождения Номертелефона Пол): ");
            String input = scanner.nextLine();

            String[] data = input.split(" ");
            if (data.length != 6) {
                System.out.println("Ошибка: неверное количество компонентов");
                return;
            }

            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];

            Date birthDate;
            try {
                birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(data[3]);
            } catch (ParseException e) {
                System.out.println("Ошибка: неверный формат даты рождения");
                return;
            }

            long phoneNumber;
            try {
                phoneNumber = Long.parseLong(data[4]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат номера телефона");
                return;
            }

            String gender = data[5];
            if (!gender.equals("m") && !gender.equals("f")) {
                System.out.println("Ошибка: неверный формат пола");
                return;
            }

            String filename = surname + ".txt";
            try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename, true), "UTF-8")) {
                String line = "<" + surname + "><" + name + "><" + patronymic + "><" +
                        new SimpleDateFormat("dd.MM.yyyy").format(birthDate) + "> <" +
                        phoneNumber + "><" + gender + ">\n";
                writer.write(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

