package view;

import controller.Controller;
import model.Person;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        final String FILE_NAME = "Data.CSV";
        String phoneNumber,group,name,gender,address,birthday,email;
        String EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        String DAY_PATTERN =  "^\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}$";
        Scanner sc = new Scanner(System.in);
        Controller controller = Controller.getInstance();
        LinkedList<Person> persons = controller.readPersonFromFile(FILE_NAME);
        int choice = -1;
        do {
            System.out.println("-----CHUONG TRINH QUAN LY DANH BA-----");
            System.out.println("Chon chuc nang theo so ( de tiep tuc) :");
            System.out.println("1. Xem danh sach");
            System.out.println("2. Them moi");
            System.out.println("3. Cap nhat");
            System.out.println("4. Xoa");
            System.out.println("5. Tim kiem");
            System.out.println("6. Doc tu file");
            System.out.println("7. Ghi vao file");
            System.out.println("8. Thoat");
            System.out.println("Chon chuc nang : ");
            try {
                choice = sc.nextInt();
                sc.nextLine();
            }catch (InputMismatchException e) {
                System.out.println("Data invalid");
                sc.nextLine();
            }
            switch (choice) {
                case 1 :
                    for (Person p: persons) {
                        p.showInfo();
                        String enter = sc.nextLine();
                    }
                    break;
                case 2 :
                    boolean check = true;
                    do {
                        System.out.println("Nhap so dien thoai : ");
                        phoneNumber = sc.nextLine();
                        check = Pattern.matches("[0-9]{9,11}",phoneNumber);
                        if(!check) System.out.println("Data invalid! Please check again!");
                    }while (!check);
                    System.out.println("Nhap nhom danh ba : ");
                    group = sc.nextLine();
                    System.out.println("Nhap ho va ten : ");
                    name = sc.nextLine();
                    System.out.println("Nhap gio tinh : male, female or none");
                    gender = sc.nextLine();
                    System.out.println("Nhap dia chi : ");
                    address = sc.nextLine();
                    do {
                        System.out.println("Nhap ngay sinh : ");
                        birthday = sc.nextLine();
                        check = Pattern.matches(DAY_PATTERN,birthday);
                        if(!check) System.out.println("Data invalid! Please check again!");
                    }while (!check);
                    do {
                        System.out.println("Nhap email : ");
                        email = sc.nextLine();
                        check = Pattern.matches(EMAIL_PATTERN,email);
                        if(!check) System.out.println("Data invalid! Please check again!");
                    }while (!check);
                    Person person = new Person(phoneNumber,group,name,gender,address,birthday,email);
                    System.out.println("Them vao danh ba thanh cong!");
                    persons.add(person);
                    break;
                case 3:
                    do {
                        System.out.println("Nhap so dien thoai danh ba can sua : ");
                        phoneNumber = sc.nextLine();
                        check = Pattern.matches("[0-9]{9,11}",phoneNumber);
                        if(!check) System.out.println("Data invalid! Please check again!");
                    }while (!check);
                    person = controller.findPersonByPhoneNumber(phoneNumber,persons);
                    if (person == null) {
                        System.out.println("Khong tim duoc danh ba voi sdt tren!");
                    } else {
                        System.out.println("Nhap lai nhom danh ba : ");
                        group = sc.nextLine();
                        System.out.println("Nhap lai ho va ten : ");
                        name = sc.nextLine();
                        System.out.println("Nhap lai gio tinh : male, female or none");
                        gender = sc.nextLine();
                        System.out.println("Nhap lai dia chi : ");
                        address = sc.nextLine();
                        do {
                            System.out.println("Nhap lai ngay sinh : ");
                            birthday = sc.nextLine();
                            check = Pattern.matches(DAY_PATTERN,birthday);
                            if(!check) System.out.println("Data invalid! Please check again!");
                        }while (!check);
                        do {
                            System.out.println("Nhap lai email : ");
                            email = sc.nextLine();
                            check = Pattern.matches(EMAIL_PATTERN,email);
                            if(!check) System.out.println("Data invalid! Please check again!");
                        }while (!check);
                        person.setGroup(group);
                        person.setName(name);
                        person.setGender(gender);
                        person.setAddress(address);
                        person.setBirthday(birthday);
                        person.setEmail(email);
//                        controller.writePersonToFile(FILE_NAME,persons);
                        System.out.println("Cap nhat thanh cong !");
                        person = null;
                    }
                    break;
                case 4:
                    do {
                        System.out.println("Nhap so dien thoai danh ba can xoa : ");
                        phoneNumber = sc.nextLine();
                        check = Pattern.matches("[0-9]{9,11}",phoneNumber);
                        if(!check) System.out.println("Data invalid! Please check again!");
                    }while (!check);
                    person = controller.findPersonByPhoneNumber(phoneNumber,persons);
                    if (person == null) {
                        System.out.println("Khong tim duoc danh ba voi sdt tren!");
                    }else {
                        System.out.println("Chon Y de xac nhan xoa danh ba tren !");
                        char choose = sc.nextLine().charAt(0);
                        if(choose == 'Y') {
                            persons.remove(person);
//                            controller.writePersonToFile(FILE_NAME,persons);
                        }
                        person = null;
                    }
                    break;
                case 5:
                    System.out.println("Nhap ho va ten hoac so dien thoai de tim kiem : ");
                    String dataFind = sc.nextLine();
                    check = Pattern.matches("[0-9]{9,11}",dataFind);
                    if(check){
                        person = controller.findPersonByPhoneNumber(dataFind,persons);
                    }else {
                        person = controller.findPersonByName(dataFind,persons);
                    }
                    if (person == null) {
                        System.out.println(" Khong ton tai nguoi dung ");
                    } else person.showInfo();
                    person = null;
                    break;
                case 6:
                    System.out.println("Cap nhat lai bo nho se khong luu tat ca thay doi ban vua thao tac!");
                    System.out.println("Chon Y de xac nhan :");
                    char choose = sc.nextLine().charAt(0);
                    if(choose == 'Y') {
                        persons = controller.readPersonFromFile(FILE_NAME);
                        System.out.println("Cap nhat thanh cong !");
                    }
                    choose = 'N';
                    break;
                case 7:
                    System.out.println("Luu tat ca  cac thay doi vua thao tac vao danh ba!");
                    System.out.println("Chon Y de xac nhan :");
                    choose = sc.nextLine().charAt(0);
                    if(choose == 'Y') {
                        controller.writePersonToFile(FILE_NAME,persons);
                        System.out.println("Luu lai thanh cong !");
                    }
                    choose = 'N';
                    break;
            }
        } while (choice!=8);

    }
}
