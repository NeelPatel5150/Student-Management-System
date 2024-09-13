import java.util.Scanner;

class Student {
    Scanner sc = new Scanner(System.in);
    String fullName;
    int roll_no;
    String enrollmentNo;
    String aadharNo;
    int birthYear;
    String userID;
    String address;
    String password;
    String confirmPassword;
    String mobileNumber;
    int maths;
    int physics;
    int chemistry;
    int english;
    int computer;
    int acpcRank;
    int fees;
    String branch;

    void setData() {
        System.out.println("----------Welcome to Student Management System----------");

        System.out.println("Enter First Name:");
        String fName = getValidString("first name");
        System.out.println("Enter Middle Name:");
        String mName = getValidString("middle name");
        System.out.println("Enter Last Name:");
        String lName = getValidString("last name");
        fullName = fName + " " + mName + " " + lName;

        System.out.println("Enter Year Of Birth:");
        birthYear = getValidInt("year of birth");

        System.out.println("Enter Address:");
        address = getValidString("address");

        setPassword();

        System.out.println("Enter Mobile Number:");
        mobileNumber = getValidMobileNumber();

        System.out.println("Enter Marks of Maths:");
        maths = getValidInt("marks of Maths");
        System.out.println("Enter Marks of Physics:");
        physics = getValidInt("marks of Physics");
        System.out.println("Enter Marks of Chemistry:");
        chemistry = getValidInt("marks of Chemistry");
        System.out.println("Enter Marks of English:");
        english = getValidInt("marks of English");
        System.out.println("Enter Marks of Computer:");
        computer = getValidInt("marks of Computer");

        System.out.println("Enter ACPC Rank:");
        acpcRank = getValidInt("ACPC Rank");

        selectBranch();
    }

    String getValidString(String fieldName) {
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.matches(".*\\d.*")) {
                System.out.println("Invalid " + fieldName + ". Please enter a valid " + fieldName + ":");
            } else {
                return input;
            }
        }
    }

    int getValidInt(String fieldName) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid " + fieldName + ". Please enter a valid " + fieldName + ":");
            }
        }
    }

    String getValidMobileNumber() {
        String mobile;
        while (true) {
            mobile = sc.nextLine();
            if (mobile.matches("[6-9]\\d{9}")) {
                return mobile;
            } else {
                System.out.println("Invalid mobile number. Please enter a valid mobile number:");
            }
        }
    }

    void setPassword() {
        while (true) {
            System.out.println("Enter Password:");
            password = sc.nextLine();
            System.out.println("Confirm Password:");
            confirmPassword = sc.nextLine();

            if (password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*")
                    && password.matches(".*\\d.*") && password.matches(".*[@#$%^&+=].*")) {
                if (password.equals(confirmPassword)) {
                    String[] name = fullName.split(" ");
                    userID = name[0] + birthYear;
                    System.out.println("Your UserID is: " + userID);
                    break;
                } else {
                    System.out.println("Passwords do not match. Please try again.");
                }
            } else {
                System.out.println("Password must be at least 8 characters long, contain an uppercase letter, "
                        + "a lowercase letter, a digit, and a special character.");
            }
        }
    }

    void selectBranch() {
        System.out.println("Select branch:");
        System.out.println("Press 1 for Mechanical");
        System.out.println("Press 2 for Electrical");
        System.out.println("Press 3 for Computer Science & Engineering");
        System.out.println("Press 4 for IT");
        System.out.println("Press 5 for Civil");
        System.out.println("Enter your choice:");
        int ch = getValidInt("branch choice");

        switch (ch) {
            case 1:
                branch = "Mechanical";
                break;
            case 2:
                branch = "Electrical";
                break;
            case 3:
                branch = "Computer Science & Engineering";
                break;
            case 4:
                branch = "IT";
                break;
            case 5:
                branch = "Civil";
                break;
            default:
                System.out.println("Invalid Choice");
                selectBranch(); // Recurse until a valid choice is made
        }
    }

    void setFees() {
        switch (branch.toLowerCase()) {
            case "mechanical":
            case "electrical":
            case "civil":
                fees = 95000;
                break;
            case "computer science & engineering":
            case "it":
                fees = 135000;
                break;
        }
    }

    void updateInformation() {
        System.out.println("Press 1 to update Full name");
        System.out.println("Press 2 to update Year of Birth");
        System.out.println("Press 3 to update Mobile Number");
        System.out.println("Press 4 to update Address");
        System.out.println("Press 5 to update Marks of Maths");
        System.out.println("Press 6 to update Marks of Physics");
        System.out.println("Press 7 to update Marks of Chemistry");
        System.out.println("Press 8 to update Marks of English");
        System.out.println("Press 9 to update Marks of Computer");
        System.out.println("Press 10 to update ACPC Rank");
        System.out.println("Enter your choice:");
        int ch = getValidInt("choice");

        switch (ch) {
            case 1:
                System.out.println("Enter new Full name:");
                fullName = getValidString("full name");
                break;
            case 2:
                System.out.println("Enter new Year of Birth:");
                birthYear = getValidInt("year of birth");
                break;
            case 3:
                System.out.println("Enter new Mobile Number:");
                mobileNumber = getValidMobileNumber();
                break;
            case 4:
                System.out.println("Enter new Address:");
                address = getValidString("address");
                break;
            case 5:
                System.out.println("Enter new Marks of Maths:");
                maths = getValidInt("marks of Maths");
                break;
            case 6:
                System.out.println("Enter new Marks of Physics:");
                physics = getValidInt("marks of Physics");
                break;
            case 7:
                System.out.println("Enter new Marks of Chemistry:");
                chemistry = getValidInt("marks of Chemistry");
                break;
            case 8:
                System.out.println("Enter new Marks of English:");
                english = getValidInt("marks of English");
                break;
            case 9:
                System.out.println("Enter new Marks of Computer:");
                computer = getValidInt("marks of Computer");
                break;
            case 10:
                System.out.println("Enter new ACPC Rank:");
                acpcRank = getValidInt("ACPC Rank");
                break;
            default:
                System.out.println("Invalid Choice");
                updateInformation(); // Recurse until a valid choice is made
        }
    }

    void search(Student[] students) {
        if (students.length == 0) {
            System.out.println("No students available to search.");
            return;
        }

        System.out.println("Enter User ID you want to search:");
        String uid = sc.nextLine();

        for (Student student : students) {
            if (uid.equals(student.userID)) {
                while (true) {
                    System.out.println("Press 1 to View Details");
                    System.out.println("Press 2 to Display Fees");
                    System.out.println("Press 3 to Update Information");
                    System.out.println("Press 4 to Exit");
                    System.out.println("Enter your choice:");
                    int ch = getValidInt("choice");

                    switch (ch) {
                        case 1:
                            student.displayData();
                            break;
                        case 2:
                            System.out.println("Branch: " + student.branch);
                            System.out.println("Fees: " + student.fees);
                            break;
                        case 3:
                            student.updateInformation();
                            break;
                        case 4:
                            System.out.println("Thank you for using the Student Management System.");
                            return;
                        default:
                            System.out.println("Invalid choice");
                    }
                }
            }
        }
        System.out.println("No such student available.");
    }

    void displayData() {
        System.out.println("Full name: " + fullName);
        System.out.println("Year of Birth: " + birthYear);
        System.out.println("User ID: " + userID);
        System.out.println("Address: " + address);
        System.out.println("Aadhar Number: " + aadharNo);
        System.out.println("Mobile Number: " + mobileNumber);
        System.out.println("Marks of Maths: " + maths);
        System.out.println("Marks of Physics: " + physics);
        System.out.println("Marks of Chemistry: " + chemistry);
        System.out.println("Marks of English: " + english);
        System.out.println("Marks of Computer: " + computer);
        System.out.println("ACPC Rank: " + acpcRank);
        System.out.println("Branch: " + branch);
        System.out.println("Fees: " + fees);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many students' data do you want to enter?");
        int n = getValidInt("number of students");
        sc.close();
        Student[] students = new Student[n];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            students[i].setData();
            students[i].setFees();
            students[i].displayData();
        }

        if (students.length > 0) {
            students[0].search(students);
        }
    }

    static int getValidInt(String fieldName) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid " + fieldName + ". Please enter a valid " + fieldName + ":");
                sc.close();
            }
        }
    }
}