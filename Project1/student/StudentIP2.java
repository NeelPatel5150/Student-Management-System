package student;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

 class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb"; // Update with your database URL
    private static final String USER = "root"; // Update with your MySQL username
    private static final String PASSWORD = ""; // Update with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


 public class StudentIP2 {
    private static final Scanner sc = new Scanner(System.in);

    // Fields
    String fullName;
    int roll_no;
    String Enrollment_no;
    String AadharNo;
    int BirthYear;
    String UserID;
    String Address;
    String password;
    String confirmpassword;
    String MN;
    int maths;
    int physics;
    int chemistry;
    int english;
    int computer;
    int AR;
    int fees;
    String branch;

    // Method to set student data
    void setData() {
        System.out.println("----------Welcome Student Management System----------");
        System.out.println("----------First Enter Personal Data----------");
        System.out.println("Enter First Name");
        String Fname = sc.next();
        System.out.println("Enter Middle Name");
        String Mname = sc.next();
        System.out.println("Enter Last Name");
        String Lname = sc.next();
        fullName = Fname + " " + Mname + " " + Lname;
        System.out.println("Enter Year Of Birth");
        BirthYear = getValidInt("Year of Birth");
        sc.nextLine();
        System.out.print("Enter Address: ");
        Address = sc.nextLine();
        boolean b = true;

        while (b) {
            System.out.println("Enter Password");
            password = sc.next();
            System.out.println("Enter Confirm Password");
            confirmpassword = sc.next();

            if (isValidPassword(password, confirmpassword)) {
                String[] name = fullName.split(" ");
                UserID = name[0] + BirthYear;
                System.out.println("Your Userid is:" + UserID);
                boolean boo = true;
                while (boo) {
                    System.out.println("Now enter your Userid for login:");
                    String uId = sc.next();
                    System.out.println("Now enter your password:");
                    String pass = sc.next();
                    if (uId.equals(UserID) && pass.equals(confirmpassword)) {
                        System.out.println("Login Successful....");
                        System.out.println("----------------Welcome Student Management System----------------");
                        b = false;
                        boo = false;
                    } else {
                        System.out.println("--> You have entered incorrect Userid or Password <--");
                    }
                }
            } else {
                System.out.println("Invalid Password. Please enter correctly.");
            }
        }

        boolean b1 = true;
        while (b1) {
            System.out.println("Enter Your Mobile Number");
            MN = getValidMobileNumber();
            System.out.println("Mobile Number Added Successfully");
            b1 = false;
        }

        System.out.print("Enter Marks of Maths: ");
        maths = getValidInt("Maths marks");
        System.out.print("Enter Marks of Physics: ");
        physics = getValidInt("Physics marks");
        System.out.print("Enter Marks of Chemistry: ");
        chemistry = getValidInt("Chemistry marks");
        System.out.print("Enter Marks of English: ");
        english = getValidInt("English marks");
        System.out.print("Enter Marks of Computer: ");
        computer = getValidInt("Computer marks");
        System.out.println("Enter ACPC Rank");
        AR = getValidInt("ACPC Rank");

        System.out.println("Select branch: ");
        System.out.println("Press 1 for Mechanical");
        System.out.println("Press 2 for Electrical");
        System.out.println("Press 3 for Computer Science&Engg");
        System.out.println("Press 4 for IT");
        System.out.println("Press 5 Civil");
        System.out.println("Enter your choice - ");
        int ch = getValidInt("branch choice");

        switch (ch) {
            case 1 -> branch = "Mechanical";
            case 2 -> branch = "Electrical";
            case 3 -> branch = "Computer Science&Engg";
            case 4 -> branch = "IT";
            case 5 -> branch = "Civil";
            default -> System.out.println("Invalid Choice");
        }
    }

    private boolean isValidPassword(String password, String confirmpassword) {
        return password.length() >= 8 && password.equals(confirmpassword) &&
                password.chars().anyMatch(Character::isUpperCase) &&
                password.chars().anyMatch(Character::isLowerCase) &&
                password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(ch -> "!@#$%^&*()_+[]{}|;:,.<>?/~`".indexOf(ch) >= 0);
    }

    private String getValidMobileNumber() {
        String mobileNumber;
        while (true) {
            mobileNumber = sc.next();
            if (mobileNumber.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Invalid mobile number. Please enter a 10-digit mobile number:");
            }
        }
        return mobileNumber;
    }

    private int getValidInt(String fieldName) {
        while (true) {
            try {
                System.out.print("Enter " + fieldName + ": ");
                return Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for " + fieldName + ":");
            }
        }
    }

    public void saveToDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO students (fullName, BirthYear, UserID, password, Address, MN, maths, physics, chemistry, english, computer, AR, branch, fees) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, fullName);
                stmt.setInt(2, BirthYear);
                stmt.setString(3, UserID);
                stmt.setString(4, password);
                stmt.setString(5, Address);
                stmt.setString(7, MN);
                stmt.setInt(8, maths);
                stmt.setInt(9, physics);
                stmt.setInt(10, chemistry);
                stmt.setInt(11, english);
                stmt.setInt(12, computer);
                stmt.setInt(13, AR);
                stmt.setString(14, branch);
                stmt.setInt(15, fees);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static StudentIP2 getStudentById(String userID) {
        StudentIP2 student = null;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE userID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, userID);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    student = new StudentIP2();
                    student.fullName = rs.getString("fullName");
                    student.BirthYear = rs.getInt("birthYear");
                    student.UserID = rs.getString("userID");
                    student.password = rs.getString("password");
                    student.Address = rs.getString("address");
                    student.AadharNo = rs.getString("aadharNo");
                    student.MN = rs.getString("mobileNumber");
                    student.maths = rs.getInt("maths");
                    student.physics = rs.getInt("physics");
                    student.chemistry = rs.getInt("chemistry");
                    student.english = rs.getInt("english");
                    student.computer = rs.getInt("computer");
                    student.AR = rs.getInt("acpcRank");
                    student.branch = rs.getString("branch");
                    student.fees = rs.getInt("fees");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Additional methods for updating and deleting records from the database can be added here
}


class Main {
    private static final List<StudentIP2> studentList = new ArrayList<>();
    private static final Map<String, StudentIP2> studentMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Exit");
            int choice = getValidInt("your choice");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> searchStudent();
                case 3 -> running = false;
                default -> System.out.println("Invalid choice. Please enter again.");
            }
        }

        sc.close();
    }

    private static void addStudent() {
        StudentIP2 student = new StudentIP2();
        student.setData();
        student.saveToDatabase();
        studentList.add(student);
        studentMap.put(student.UserID, student);
        System.out.println("Student added successfully.");
    }

    private static void searchStudent() {
        System.out.println("Enter UserID to search:");
        String userID = new Scanner(System.in).next();

        if (studentMap.containsKey(userID)) {
            StudentIP2 student = studentMap.get(userID);
            System.out.println("Student Details:");
            System.out.println("Full Name: " + student.fullName);
            System.out.println("UserID: " + student.UserID);
            // Display other details as needed
        } else {
            System.out.println("Student with UserID " + userID + " not found.");
        }
    }

    @SuppressWarnings("resource")
    private static int getValidInt(String fieldName) {
        while (true) {
            try {
                System.out.print("Enter " + fieldName + ": ");
                return Integer.parseInt(new Scanner(System.in).next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for " + fieldName + ":");
            }
        }
    }
}
