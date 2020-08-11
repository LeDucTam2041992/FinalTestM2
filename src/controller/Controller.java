package controller;

import model.Person;

import java.io.*;
import java.util.LinkedList;

public class Controller {
    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    private static Controller instance;

    private Controller(){}

    public static Controller getInstance(){
        if (instance == null) {
            synchronized (Controller.class) {
                if (instance == null) {
                    instance = new Controller();
                }
            }
        }
        return instance;
    }

    public void openFileToWrite(String fileName) {
        try {
            file = new File(fileName);
            if (!file.exists()) file.createNewFile();
            fileWriter = new FileWriter(fileName,false);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterWrite(String fileName) {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFileToRead(String fileName) {
        try {
            file = new File(fileName);
            if (!file.exists()) file.createNewFile();
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterRead(String fileName) {
        try {
            bufferedReader.close();
            fileReader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void writePersonToFile(String fileName, Person person) {
        openFileToWrite(fileName);
        printWriter.println(person.getPhoneNumber() + "|" + person.getGroup() + "|" + person.getName() + "|" + person.getGender()
                + "|" + person.getAddress() + "|" + person.getBirthday() + "|" + person.getEmail());
        closeFileAfterWrite(fileName);
    }

    public void writePersonToFile(String fileName, LinkedList<Person> persons) {
        openFileToWrite(fileName);
        for (Person person: persons) {
            printWriter.println(person.getPhoneNumber() + "|" + person.getGroup() + "|" + person.getName() + "|" + person.getGender()
                    + "|" + person.getAddress() + "|" + person.getBirthday() + "|" + person.getEmail());
        }
        closeFileAfterWrite(fileName);
    }


    public LinkedList<Person> readPersonFromFile(String fileName) throws IOException {
        LinkedList<Person> persons = new LinkedList<>();
        String data;
        openFileToRead(fileName);
        while ((data = bufferedReader.readLine()) != null) {
            Person person = createPersonFromData(data);
            persons.add(person);
        }
        closeFileAfterRead(fileName);
        return persons;
    }

    private Person createPersonFromData(String data) {
        String[] datas = data.split("\\|");
        Person person = new Person(datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6]);
        return person;
    }

    public Person findPersonByPhoneNumber(String phoneNumber, LinkedList<Person> persons){
        for (Person p: persons) {
            if(p.getPhoneNumber().equals(phoneNumber)) return p;
        }
        return null;
    }

    public Person findPersonByName(String name, LinkedList<Person> persons) {
        for (Person p: persons) {
            if(p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }
}
