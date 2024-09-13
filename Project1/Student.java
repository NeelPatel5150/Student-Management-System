import java.sql.*;
import java.util.Scanner;

public class Student {
    Scanner sc = new Scanner(System.in);
    String fullName;
    int roll_no;
    String Enrollment_no;
    String AadharNo;
    int BirthYear;
    String UserID;
    String Address;
    String password;
    String MN;
    int maths;
    int physics;
    int chemistry;
    int english;
    int computer;
    int AR;
    int fees;
    String branch;

    // Database connection settings
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Method to get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

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
        BirthYear = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Address: ");
        Address = sc.nextLine();

        boolean b = true;
        while (b) {
            System.out.println("Enter Password");
            password = sc.next();
            System.out.println("Enter Confirm Password");
            String confirmpassword = sc.next();
            if (password.length() >= 8) {
                if (password.equals(confirmpassword)) {
                    String name[] = fullName.split(" ");
                    UserID = name[0] + BirthYear;
                    System.out.println("Your UserID is: " + UserID);
                    boolean boo = true;
                    while (boo) {
                        System.out.println("Now enter your UserID for login:");
                        String uId = sc.next();
                        System.out.println("Now enter your password:");
                        String pass = sc.next();
                        if (uId.equals(UserID) && pass.equals(confirmpassword)) {
                            System.out.println("Login Successful....");
                            System.out.println("----------------Welcome Student Management System----------------");
                            b = false;
                            boo = false;
                        } else {
                            System.out.println("You have entered incorrect UserID or Password");
                        }
                    }
                } else {
                    System.out.println("Sorry... Password & Confirm Password Must be Same. Please enter correctly.");
                }
            } else {
                System.out.println("Sorry. Kindly enter valid password. Password length must be greater than or equal to 8.");
            }
        }

        boolean b1 = true;
        while (b1) {
            System.out.println("Enter Your Mobile Number");
            MN = sc.next();
            if (MN.length() == 10 && MN.matches("\\d{10}")) {
                System.out.println("Mobile Number Added Successfully");
                b1 = false;
            } else {
                System.out.println("Invalid Mobile Number Enter Again");
            }
        }

        System.out.print("Enter Marks of Maths: ");
        maths = sc.nextInt();
        System.out.print("Enter Marks of Physics: ");
        physics = sc.nextInt();
        System.out.print("Enter Marks of Chemistry: ");
        chemistry = sc.nextInt();
        System.out.print("Enter Marks of English: ");
        english = sc.nextInt();
        System.out.print("Enter Marks of Computer: ");
        computer = sc.nextInt();
        System.out.println("Enter ACPC Rank");
        AR = sc.nextInt();

        System.out.println("Select branch: ");
        System.out.println("Press 1 for Mechanical");
        System.out.println("Press 2 for Electrical");
        System.out.println("Press 3 for Computer Science&Engg");
        System.out.println("Press 4 for IT");
        System.out.println("Press 5 Civil");
        System.out.println("Enter your choice - ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                branch = "Mechanical";
                break;
            case 2:
                branch = "Electrical";
                break;
            case 3:
                branch = "Computer Science&Engg";
                break;
            case 4:
                branch = "IT";
                break;
            case 5:
                branch = "Civil";
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }

        setFees();
        saveData();
    }

    void saveData() {
        String query = "INSERT INTO students (fullName, roll_no, Enrollment_no, AadharNo, BirthYear, UserID, Address, password, MN, maths, physics, chemistry, english, computer, AR, fees, branch) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, fullName);
            stmt.setInt(2, roll_no);
            stmt.setString(3, Enrollment_no);
            stmt.setString(4, AadharNo);
            stmt.setInt(5, BirthYear);
            stmt.setString(6, UserID);
            stmt.setString(7, Address);
            stmt.setString(8, password);
            stmt.setString(9, MN);
            stmt.setInt(10, maths);
            stmt.setInt(11, physics);
            stmt.setInt(12, chemistry);
            stmt.setInt(13, english);
            stmt.setInt(14, computer);
            stmt.setInt(15, AR);
            stmt.setInt(16, fees);
            stmt.setString(17, branch);
            stmt.executeUpdate();
            System.out.println("Student data saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setFees() {
        String temp = branch.toLowerCase();
        if (temp.equals("mechanical") || temp.equals("electrical") || temp.equals("civil")) {
            fees = 95000;
        } else if (temp.equals("computer science&engg") || temp.equals("it")) {
            fees = 135000;
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
        System.out.println("Press 10 to update ACPC Rank ");
        System.out.println("Enter your choice: ");
        int ch = sc.nextInt();
        sc.nextLine(); // Consume newline

        String query = "";
        switch (ch) {
            case 1:
                System.out.println("Enter new Full name");
                fullName = sc.nextLine();
                query = "UPDATE students SET fullName = ? WHERE UserID = ?";
                break;
            case 2:
                System.out.println("Enter new Year of Birth");
                BirthYear = sc.nextInt();
                query = "UPDATE students SET BirthYear = ? WHERE UserID = ?";
                break;
            case 3:
                boolean boo = true;
                while (boo) {
                    System.out.println("Enter New Mobile Number");
                    MN = sc.next();
                    if (MN.length() == 10 && MN.matches("\\d{10}")) {
                        System.out.println("Mobile Number Added Successfully");
                        boo = false;
                        query = "UPDATE students SET MN = ? WHERE UserID = ?";
                    } else {
                        System.out.println("Mobile Number Must be 10 digits only");
                    }
                }
                break;
            case 4:
                System.out.println("Enter new Address");
                Address = sc.nextLine();
                query = "UPDATE students SET Address = ? WHERE UserID = ?";
                break;
            case 5:
                System.out.println("Enter new Marks of Maths");
                maths = sc.nextInt();
                query = "UPDATE students SET maths = ? WHERE UserID = ?";
                break;
            case 6:
                System.out.println("Enter new Marks of Physics");
                physics = sc.nextInt();
                query = "UPDATE students SET physics = ? WHERE UserID = ?";
                break;
            case 7:
                System.out.println("Enter new Marks of Chemistry");
                chemistry = sc.nextInt();
                query = "UPDATE students SET chemistry = ? WHERE UserID = ?";
                break;
            case 8:
                System.out.println("Enter new Marks of English");
                english = sc.nextInt();
                query = "UPDATE students SET english = ? WHERE UserID = ?";
                break;
            case 9:
                System.out.println("Enter new Marks of Computer");
                computer = sc.nextInt();
                query = "UPDATE students SET computer = ? WHERE UserID = ?";
                break;
            case 10:
                System.out.println("Enter new ACPC Rank");
                AR = sc.nextInt();
                query = "UPDATE students SET AR = ? WHERE UserID = ?";
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            switch (ch) {
                case 1:
                case 4:
                    stmt.setString(1, fullName);
                    break;
                case 2:
                    stmt.setInt(1, BirthYear);
                    break;
                case 3:
                    stmt.setString(1, MN);
                    break;
                case 5:
                    stmt.setInt(1, maths);
                    break;
                case 6:
                    stmt.setInt(1, physics);
                    break;
                case 7:
                    stmt.setInt(1, chemistry);
                    break;
                case 8:
                    stmt.setInt(1, english);
                    break;
                case 9:
                    stmt.setInt(1, computer);
                    break;
                case 10:
                    stmt.setInt(1, AR);
                    break;
            }
            stmt.setString(2, UserID);
            stmt.executeUpdate();
            System.out.println("Student information updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void Search(Student s[]) {
        System.out.println("Enter UserID to search: ");
        String userID = sc.next();
        String query = "SELECT * FROM students WHERE UserID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Full Name: " + rs.getString("fullName"));
                System.out.println("Roll No: " + rs.getInt("roll_no"));
                System.out.println("Enrollment No: " + rs.getString("Enrollment_no"));
                System.out.println("Aadhar No: " + rs.getString("AadharNo"));
                System.out.println("Birth Year: " + rs.getInt("BirthYear"));
                System.out.println("User ID: " + rs.getString("UserID"));
                System.out.println("Address: " + rs.getString("Address"));
                System.out.println("Password: " + rs.getString("password"));
                System.out.println("Mobile Number: " + rs.getString("MN"));
                System.out.println("Maths: " + rs.getInt("maths"));
                System.out.println("Physics: " + rs.getInt("physics"));
                System.out.println("Chemistry: " + rs.getInt("chemistry"));
                System.out.println("English: " + rs.getInt("english"));
                System.out.println("Computer: " + rs.getInt("computer"));
                System.out.println("ACPC Rank: " + rs.getInt("AR"));
                System.out.println("Fees: " + rs.getInt("fees"));
                System.out.println("Branch: " + rs.getString("branch"));
            } else {
                System.out.println("No student found with UserID: " + userID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void displayData() {
        // Display data logic if needed
        // For example, display all students or specific information
    }

    public void saveToDatabase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveToDatabase'");
    }
}



 class Main {
    public static void main(String[] args) {
        // Load MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("How many Student's data you want to enter");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline

        Student[] students = new Student[n];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            students[i].setData();
        }

        // Example of searching a student
        if (n > 0) {
            students[0].Search(students);
        }

        sc.close();
    }
}
