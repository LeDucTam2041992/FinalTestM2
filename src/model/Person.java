package model;

public class Person {
    String name, gender, address, group, email, birthday, phoneNumber;

    public Person() {
    }

    public Person(String phoneNumber, String group, String name, String gender, String address, String birthday, String email) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.group = group;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void showInfo() {
        System.out.println("Phone number : " + phoneNumber);
        System.out.println("Group : " + group);
        System.out.println("Full Name : " + name);
        System.out.println("Gender : " + gender);
        System.out.println("Address : " + address);
    }
}
