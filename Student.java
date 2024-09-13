import java.sql.*;
import java.util.Scanner;

public class Student {

    private static Scanner scanner = new Scanner(System.in);
    static  String phoneNumber;
    int id;
    String name;
    String branch;
    String batch;
    String email;
    String phone;

    public Student(int id , String name , String branch , String batch , String email , String phone)
    {
        this.id=id;
        this.name=name;
        this.branch=branch;
        this.batch=batch;
        this.email=email;
        this.phone=phone;

    }

    @Override // LinkList Methods
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", branch=" + branch + ", batch=" + batch + ", email=" + email
                + ", phone=" + phone + "]";
    }

    public static void main(String[] args) {
        try {
            // Step 1: Load and register the driver
            String driverName = "com.mysql.cj.jdbc.Driver";
           Class.forName(driverName);
            System.out.println("Driver Loaded Successfully.");

            // Step 2: Create connection to the database
            String dburl = "jdbc:mysql://localhost:3306/studentmanagementsystem";
            String dbuser = "root";
            String dbpass = "";

            Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);

            if (con != null) {
                System.out.println("Connection Successful.\n");
            } else {
                System.out.println("Connection Failed.\n");
                return;
            }

        Scanner sc = new Scanner(System.in);
        LinkedListStudent students = new LinkedListStudent();

        // Method to check login credentials
        System.out.println("1. Login");
        System.out.println("2. Create New User");
        System.out.print("Enter your choice: ");
        int choice1 = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println();
        

        if (choice1 == 1) {
            // Login
            System.out.print("Enter User ID: ");
            String userID = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            // Check login credentials
            if (login(userID, password))
            {
                System.out.println("Login successful!\n");
            } else
            {
                System.out.println("Login failed! Invalid credentials.\n");
                System.exit(0);
            }
        } else if (choice1 == 2)
        {
            // Create new user
            System.out.print("Enter New User ID: ");
            String userID = scanner.nextLine();
            System.out.print("Enter New Password: ");
            String password = scanner.nextLine();
            System.out.print("Confirm Password: ");
            String confirmPassword = scanner.nextLine();

            if (password.equals(confirmPassword)) {
                createUser(userID, password);
            } else {
                System.out.println("Passwords do not match. Try again.");
            }
        }else
        {
            System.out.println("Invalid choice. Please select 1 or 2.\n");
        }

            System.out.println("Press 1 To Admin Page");
            System.out.println("Press 2 To Student Page");
            int ch = getValidInt("Enter Your Choice: ");
            System.out.println();

            switch (ch) {
                case 1:
                // ADMIN PAGE
                boolean b = true;
                while (b) {
                    System.out.println("\n-----------------Admin Page-----------------");
                    System.out.println("1. Add Student");
                    System.out.println("2. Update Student");
                    System.out.println("3. Delete Student");
                    System.out.println("4. Search Student / Linklist View Data");
                    System.out.println("5. Add Course");
                    System.out.println("6. Add Enrollment");
                    System.out.println("7. Add Fee");
                    System.out.println("8. Add Batch");
                    System.out.println("9. Add Branch");
                    System.out.println("10. Add Attendance");
                    System.out.println("11. Add Exam");
                    System.out.println("12. Sign out from Admin Page");
                    System.out.println("13. Exit from System\n");
                    System.out.println();
                    System.out.print("Enter Your Choice: ");
                        
                    int ch1 = sc.nextInt();
                    System.out.println();
                       
                    switch (ch1) {
                        // Student functions
                        
                        case 1:
                            // Add Student
                            int id = getValidInt("Enter Student Id: ");
                            String name = getValidString("Enter Student Name: ");
                            String branch = getValidString("Enter Branch: ");
                            System.out.print("Enter Batch: ");
                            String batch = sc.next();
                            System.out.print("Enter Email: ");
                            String email = sc.next();
                            
                            boolean b1 = true;
                            while(b1)
                            {
                                System.out.print("Enter Your Mobile Number: ");
                                phoneNumber = sc.next();
                                int i = 0;
                                int count = 0;
                                if(phoneNumber.length() == 10)
                                {
                                    for(i =0;i<1;i++)
                                    {
                                        if(phoneNumber.charAt(i)>='6' && phoneNumber.charAt(i)<='9')
                                        {
                                            count++;
                                            continue;
                                        }
                                        else
                                        {
                                            break;
                                        }
                                    }
                                
                                    for(i = 1;i<10;i++)
                                    {
                                        if(phoneNumber.charAt(i)>='0' && phoneNumber.charAt(i)<='9')
                                        {
                                            count++;
                                            continue;
                                        }
                                        else
                                        {
                                            break;
                                        }
                                    }
                                }
                                if(count == 10)
                                {
                                    b1 = false;
                                }
                                else
                                {
                                    System.out.println("Invalid Mobile Number Enter Again");
                                }

                                students.addLast(new Student(id, name, branch, batch, email, email));
                            }    
        
                            String sqlStudent = "INSERT INTO student (student_id, name, branch, batch, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";    
                            try  {
    
                                PreparedStatement pst = con.prepareStatement(sqlStudent);
    
                                pst.setInt(1, id);
                                pst.setString(2, name);
                                pst.setString(3, branch);
                                pst.setString(4, batch);
                                pst.setString(5, email);
                                pst.setString(6, phoneNumber);
    
                                int rStudent = pst.executeUpdate();
    
                                if (rStudent > 0) {
                                    System.out.println("Student Data Inserted Successfully.\n");
                                } else {
                                    System.out.println("Student Data Not Inserted.\n");
                                }
                            }catch(SQLIntegrityConstraintViolationException e){
                                System.out.println("Error: Duplicate entry detected.");
                            } catch (SQLException e) {
                                System.out.println("SQL Error: " + e.getMessage());
                            }
                            break;
                        
                           
    
                        case 2:
                            // UPDATE STUDENT
                            System.out.print("Enter Search Student Id: ");
                            int uid = sc.nextInt();
    
                            
                            String name1 = getValidString("Enter New Student Name: ");
                            String branch1 = getValidString("Enter New Student Branch: ");
                            String batch1 = getValidString("Enter New Student Batch: ");
                            System.out.print("Enter New Student Email: ");
                            String email1 = sc.next();
                            System.out.print("Enter New Student Phone Number: ");
                            Long number1 = sc.nextLong();
    
                            Statement st = con.createStatement();
    
                            con.setAutoCommit(false);
    
                            String sql1 = "update student set name = '"+name1+"',branch = '"+branch1+"',batch = '"+batch1+"',email = '"+email1+"',phone_number = "+number1+" where student_id="+uid+"";
    
                            int r1 = st.executeUpdate(sql1);
                            if(r1>0)
                            {
                                System.out.println("Data Updated Succesfully.\n");
                            }
                            else{
                                System.out.println("Not Updated.\n");
                            }
    
                                int temp;
                                System.out.println("Enter 1 For Commit\nEnter 2 For RollBack\nEnter 3 For Exit");
                                temp = sc.nextInt();
    
                                switch (temp) {
                                    case 1:
                                            con.commit();
                                            System.out.println("Commit");
                                            break;
                                        
                                    case 2:
                                            con.rollback();
                                            System.out.println("Rollback");
                                        break;
                                
                                }
    
                            
                        break;
    
                    case 3:
                            //DELETE STUDENT
                            System.out.print("Enter Student Id You Want To Remove: ");
                            int f1id = sc.nextInt();
                            
                            con.setAutoCommit(false);
    
                            String sql2 = "delete from student where student_id ="+f1id+"";
                            Statement st8 = con.createStatement();
    
                            int r2 = st8.executeUpdate(sql2);
                            if(r2>0)
                            {
                                System.out.println("Data Deleted Succesfully.\n");
                            }
                            else{
                                System.out.println("Not Deleted.\n");
                            }
    
                                int temp1;
                                System.out.println("Enter 1 For Commit\nEnter 2 For Rollback\nEnter 3 For Exit");
                                temp1=sc.nextInt();
                                switch (temp1)
                                {
                                    case 1:
                                            con.commit();
                                            System.out.println("Commit");
                                            break;
                                        
                                    case 2:
                                            con.rollback();
                                            System.out.println("RollBack");
                                            break;
                                
                                }
                    break;
    
                    case 4:
                            // SEARCH STUDENT
                            
                                System.out.println("\nList of Students:");
                                for (Student student : students.getAllStudents()) {
                                    System.out.println(student);
                                }
                            
                            System.out.print("Enter Your Id: ");
                            int id1 = sc.nextInt();
                            System.out.println();

                            String sql4 = "select * from student where student_id="+id1+"";
    
                            Statement st9 = con.createStatement();
                            ResultSet rs1 = st9.executeQuery(sql4);
    
                            while (rs1.next()) {
                                System.out.print("ID: "+rs1.getInt(1)+"\n");
    
                                System.out.print("NAME: "+rs1.getString(2)+"\n");
    
                                System.out.print("BRANCH: "+rs1.getString(3)+"\n");
    
                                System.out.print("BATCH: "+rs1.getString(4)+"\n");
    
                                System.out.print("EMAIL: "+rs1.getString(5)+"\n");
    
                                System.out.print("PHONE-NUMBER: "+rs1.getLong(6)+"\n");
    
                                System.out.println();
    
                            }
                    break;
    
    
                        case 5:
                            // Add Course functionality
                            String courseName = getValidString("Enter Course(Subject) Name: ");
                            int credits = getValidInt("Enter Credits: ");
    
                            String sqlCourse = "INSERT INTO courses (course_name, credits) VALUES (?, ?)";
                            try  {
                                PreparedStatement pstmt = con.prepareStatement(sqlCourse);
    
                                pstmt.setString(1, courseName);
                                pstmt.setInt(2, credits);
    
                                int rCourse = pstmt.executeUpdate();
                                if (rCourse > 0) {
                                    System.out.println("Course Data Inserted Successfully.\n");
                                    
                                } else {
                                    System.out.println("Course Data Not Inserted.\n");
                                }
                            }catch(SQLIntegrityConstraintViolationException e){
                                System.out.println("Error: Duplicate entry detected.");
                            }
                             catch (SQLException e) {
                                System.out.println("SQL Error: " + e.getMessage());
                            }
                            break;
    
    
                        case 6:
                            // Add Enrollment functionality
                            System.out.print("Enter Enrollment Id: ");
                            String enrollmentid = sc.next();
                            int studentIdForEnrollment = getValidInt("Enter Student Id: ");
                            int courseIdForEnrollment = getValidInt("Enter Course Id: ");
                            System.out.print("Enter Grade: ");
                            String grade = sc.next();
    
                            String sqlEnrollment = "INSERT INTO enrollments (enrollment_id,student_id, course_id, grade) VALUES (?,?, ?, ?)";
    
                            try  {
    
                                PreparedStatement pstmt = con.prepareStatement(sqlEnrollment);
                                
                                pstmt.setString(1, enrollmentid);
                                pstmt.setInt(2, studentIdForEnrollment);
                                pstmt.setInt(3, courseIdForEnrollment);
                                pstmt.setString(4, grade);
    
                                int rEnrollment = pstmt.executeUpdate();
                                if (rEnrollment > 0) {
                                    System.out.println("Enrollment Data Inserted Successfully.\n");
                                } else {
                                    System.out.println("Enrollment Data Not Inserted.\n");
                                }
                            }catch(SQLIntegrityConstraintViolationException e){
                                System.out.println("Error: Duplicate entry detected.");
                            }
                             catch (SQLException e) {
                                System.out.println("SQL Error: " + e.getMessage());
                            }
                            break;
    
    
                        case 7:
                            // Add Fee functionality
                            int studentIdForFee = getValidInt("Enter Student Id: ");
                            System.out.print("Enter Amount: ");
                            double amount = sc.nextDouble();
                            System.out.print("Enter Due Date (YYYY-MM-DD): ");
                            String dueDate = sc.next();
                            
    
                            String sqlFee = "INSERT INTO fees (student_id, amount, due_date) VALUES (?, ?, ?)";
    
                            try  {
                                PreparedStatement pstmt = con.prepareStatement(sqlFee);
                                
                                pstmt.setInt(1, studentIdForFee);
                                pstmt.setDouble(2, amount);
                                pstmt.setDate(3, Date.valueOf(dueDate));
                                
                                int rFee = pstmt.executeUpdate();
    
                                if (rFee > 0) {
                                    System.out.println("Fee Data Inserted Successfully.\n");
                                } else {
                                    System.out.println("Fee Data Not Inserted.\n");
                                }
                            }catch(SQLIntegrityConstraintViolationException e){
                                System.out.println("Error: Duplicate entry detected.");
                            }
                             catch (SQLException e) {
                                System.out.println("SQL Error: " + e.getMessage());
                            }
                            break;
    
    
                        case 8:
                            // Add Batch functionality
                            System.out.print("Enter Batch Name: ");
                            String batchName = sc.next();
                            System.out.print("Enter Start Date (YYYY-MM-DD): ");
                            String startDate = sc.next();
                            System.out.print("Enter End Date (YYYY-MM-DD): ");
                            String endDate = sc.next();
    
                            String sqlBatch = "INSERT INTO batches (batch_name, start_date, end_date) VALUES (?, ?, ?)";
    
                            try  {
                                PreparedStatement pstmt = con.prepareStatement(sqlBatch);
                                
                                pstmt.setString(1, batchName);
                                pstmt.setDate(2, Date.valueOf(startDate));
                                pstmt.setDate(3, Date.valueOf(endDate));
    
                                int rBatch = pstmt.executeUpdate();
                                if (rBatch > 0) {
                                    System.out.println("Batch Data Inserted Successfully.\n");
                                } else {
                                    System.out.println("Batch Data Not Inserted.\n");
                                }
                            }catch(SQLIntegrityConstraintViolationException e){
                                System.out.println("Error: Duplicate entry detected.");
                            }
                             catch (SQLException e) {
                                System.out.println("SQL Error: " + e.getMessage());
                            }
                            break;
    
    
                        case 9:
                            // Add Branch functionality
                            String branchName = getValidString("Enter Branch Name: ");
    
                            String sqlBranch = "INSERT INTO branches (branch_name) VALUES (?)";
    
                            try  {
    
                                PreparedStatement pstmt = con.prepareStatement(sqlBranch);
                                
                                pstmt.setString(1, branchName);
    
                                int rBranch = pstmt.executeUpdate();
    
                                if (rBranch > 0) {
                                    System.out.println("Branch Data Inserted Successfully.\n");
                                } else {
                                    System.out.println("Branch Data Not Inserted.\n");
                                }
                            }catch(SQLIntegrityConstraintViolationException e){
                                System.out.println("Error: Duplicate entry detected.");
                            } 
                            catch (SQLException e) {
                                System.out.println("SQL Error: " + e.getMessage());
                            }
                            break;
    
    
                        case 10:
                            // Add Attendance functionality
                            int studentIdForAttendance = getValidInt("Enter Student Id: ");
                            System.out.print("Enter Date (YYYY-MM-DD): ");
                            String attendanceDate = sc.next();
                            String attendanceStatus = getValidString("Enter Status(Present/Absent): ");
    
                            String sqlAttendance = "INSERT INTO attendance (student_id, date, status) VALUES (?, ?, ?)";
    
                            try  {
    
                                PreparedStatement pstmt = con.prepareStatement(sqlAttendance);
                                
                                pstmt.setInt(1, studentIdForAttendance);
                                pstmt.setDate(2, Date.valueOf(attendanceDate));
                                pstmt.setString(3, attendanceStatus);
    
                                int rAttendance = pstmt.executeUpdate();
                                if (rAttendance > 0) {
                                    System.out.println("Attendance Data Inserted Successfully.\n");
                                } else {
                                    System.out.println("Attendance Data Not Inserted.\n");
                                }
                            }catch(SQLIntegrityConstraintViolationException e){
                                System.out.println("Error: Duplicate entry detected.");
                            }
                             catch (SQLException e) {
                                System.out.println("SQL Error: " + e.getMessage());
                            }
                            break;
    
                        case 11:
                            // Add Exam functionality
                            int courseIdForExam = getValidInt("Enter Course Id: ");
                            int studentid = getValidInt("Enter Student Id: ");
                            System.out.print("Enter Date (YYYY-MM-DD): ");
                            String examDate = sc.next();
                            int totalMarks = getValidInt("Enter Total Marks: ");
                            if(totalMarks>=100)
                            {
                                System.out.println("Enter 100 In Marks Only.");
                            }
    
                            String sqlExam = "INSERT INTO exams ( course_id, date, total_marks,studentid) VALUES(?, ?, ?,?)";
    
                            try  {
    
                                PreparedStatement pstmt = con.prepareStatement(sqlExam);
                                
                                pstmt.setInt(1, courseIdForExam);
                                pstmt.setDate(2, Date.valueOf(examDate));
                                pstmt.setInt(3, totalMarks);
                                pstmt.setInt(4, studentid);
    
                                int rExam = pstmt.executeUpdate();
                                if (rExam > 0) {
                                    System.out.println("Exam Data Inserted Successfully.\n");
                                } else {
                                    System.out.println("Exam Data Not Inserted.\n");
                                }
                            }catch(SQLIntegrityConstraintViolationException e){
                                System.out.println("Error: Duplicate entry detected.");
                            } 
                            catch (SQLException e) {
                                System.out.println("SQL Error: " + e.getMessage());
                            }
                            break;

                        case 12:
                                System.out.println("Signing out from Admin Page.");
                                break;

                        case 13:
                                System.out.println("Exiting Page.\nThank You!!!!");
                                b = false;
                                System.exit(0);
                                break;

                        default:
                                System.out.println("Invalid choice. Try again.");
                                 break;
                                
                        }
                        if(ch1==12)
                        {
                            break;
                        }
                    }
                    
                    break;
                
                case 2:
                    // STUDENT PAGE
                     // All Display
                boolean b1 =true;
                while (b1) {
                System.out.println("\n-----------------Student Page-----------------");
                System.out.println("1. View All Students");
                System.out.println("2. View All Courses");
                System.out.println("3. View All Enrollments");
                System.out.println("4. View All Fees");
                System.out.println("5. View All Batches");
                System.out.println("6. View All Branches");
                System.out.println("7. View All Attendance");
                System.out.println("8. View All Exams");
                System.out.println("9. Sign out from Student Page");
                System.out.println("10. Exit from System\n");
                System.out.println();
                System.out.print("Enter Your Choice: ");
                int choice = sc.nextInt();
                System.out.println();

    switch (choice) {
                    case 1:
                            // View All Students
                          String sqlViewStudents = "{call ReadAllData()}";

                                try  {
                                    CallableStatement cst = con.prepareCall(sqlViewStudents);
                                    ResultSet rs = cst.executeQuery(sqlViewStudents);

                                        while (rs.next()) {
                                        System.out.print("Student ID: " + rs.getInt(1) + "\n");
                                        System.out.print("Name: " + rs.getString(2) + "\n");
                                        System.out.print("Branch: " + rs.getString(3) + "\n");
                                        System.out.print("Batch: " + rs.getString(4) + "\n");
                                        System.out.print("Email: " + rs.getString(5) + "\n");
                                        System.out.print("Phone Number: " + rs.getLong(6)+"\n");
                                        System.out.println();
                                    }

                                } catch (SQLException e) {
                                    System.out.println("SQL Error: " + e.getMessage());
                                }
                                break;
                        
                    case 2:
                            // View All Courses functionality
                                String sqlViewCourses = "SELECT * FROM courses";

                                try {
                                    Statement st1 = con.createStatement();
                                    ResultSet rs = st1.executeQuery(sqlViewCourses);

                                    while (rs.next()) {
                                        System.out.print("Course ID: " + rs.getInt(1) + "\n");
                                        System.out.print("Course Name: " + rs.getString(2) + "\n");
                                        System.out.print("Credits: " + rs.getInt(3)+"\n");
                                        System.out.println();
                                    }
                                } catch (SQLException e) {
                                    System.out.println("SQL Error: " + e.getMessage());
                                }
                                break;


                        case 3:
                                // View All Enrollments functionality
                                String sqlViewEnrollments = "SELECT * FROM enrollments";

                                try {

                                    Statement st2 = con.createStatement();
                                    ResultSet rs = st2.executeQuery(sqlViewEnrollments);

                                    while (rs.next()) {
                                        System.out.print("Enrollment ID: " + rs.getString(1) + "\n");
                                        System.out.print("Student ID: " + rs.getInt(2) + "\n");
                                        System.out.print("Course ID: " + rs.getInt(3) + "\n");
                                        System.out.print("Grade: " + rs.getString(4)+"\n");
                                        System.out.println();
                                    }
                                } catch (SQLException e) {
                                    System.out.println("SQL Error: " + e.getMessage());
                                }
                                break;

                        case 4:
                                // View All Fees functionality
                                String sqlViewFees = "SELECT * FROM fees";

                                try  {

                                    Statement st3 = con.createStatement();
                                    ResultSet rs = st3.executeQuery(sqlViewFees);

                                    while (rs.next()) {
                                        System.out.print("Fee ID: " + rs.getInt(1) + "\n");
                                        System.out.print("Student ID: " + rs.getInt(2) + "\n");
                                        System.out.print("Amount: " + rs.getDouble(3) + "\n");
                                        System.out.print("Due Date: " + rs.getDate(4) + "\n");
                                        System.out.println();
                                    }
                                } catch (SQLException e) {
                                    System.out.println("SQL Error: " + e.getMessage());
                                }
                                break;

                        case 5:
                                // View All Batches functionality
                                String sqlViewBatches = "SELECT * FROM batches";

                                try  {

                                    Statement st4 = con.createStatement();
                                    ResultSet rs = st4.executeQuery(sqlViewBatches);

                                    while (rs.next()) {
                                        System.out.print("Batch ID: " + rs.getInt(1) + "\n");
                                        System.out.print("Batch Name: " + rs.getString(2) + "\n");
                                        System.out.print("Start Date: " + rs.getDate(3) + "\n");
                                        System.out.print("End Date: " + rs.getDate(4)+"\n");
                                        System.out.println();
                                    }
                                } catch (SQLException e) {
                                    System.out.println("SQL Error: " + e.getMessage());
                                }
                                break;

                        case 6:
                                // View All Branches functionality
                                String sqlViewBranches = "SELECT * FROM branches";

                                try  {

                                    Statement st5 = con.createStatement();
                                    ResultSet rs = st5.executeQuery(sqlViewBranches);

                                    while (rs.next()) {
                                        System.out.print("Branch ID: " + rs.getInt(1) + "\n");
                                        System.out.print("Branch Name: " + rs.getString(2)+"\n");
                                        System.out.println();
                                    }
                                } catch (SQLException e) {
                                    System.out.println("SQL Error: " + e.getMessage());
                                }
                                break;

                        case 7:
                                    // View All Attendance functionality
                                    String sqlViewAttendance = "SELECT * FROM attendance";

                                    try  {

                                        Statement st6 = con.createStatement();
                                        ResultSet rs = st6.executeQuery(sqlViewAttendance);

                                        while (rs.next()) {
                                            System.out.print("Attendance ID: " + rs.getInt(1) + "\n");
                                            System.out.print("Student ID: " + rs.getInt(2) + "\n");
                                            System.out.print("Date: " + rs.getDate(3) + "\n");
                                            System.out.print("Status: " + rs.getString(4)+"\n");
                                            System.out.println();
                                        }
                                    } catch (SQLException e) {
                                        System.out.println("SQL Error: " + e.getMessage());
                                    }
                                    break;

                         case 8:
                                    // View All Exams functionality
                                    String sqlViewExams = "SELECT * FROM exams";
                                        
                                    try  {

                                        Statement st7 = con.createStatement();
                                        ResultSet rs = st7.executeQuery(sqlViewExams);

                                        while (rs.next()) {
                                            System.out.print("Exam ID: " + rs.getInt(1) + "\n");
                                            System.out.print("Student Id: "+rs.getInt(5)+"\n");
                                            System.out.print("Course ID: " + rs.getInt(2) + "\n");
                                            System.out.print("Date: " + rs.getDate(3) + "\n");
                                            System.out.print("Total Marks: " + rs.getInt(4)+"\n");
                                            System.out.println();
                                        }
                                    } catch (SQLException e) {
                                        System.out.println("SQL Error: " + e.getMessage());
                                    }
                                    break;
        
                        case 9:
                                 System.out.println("Signing out from Student Page.");
                                    break;

                        case 10:
                                 System.out.println("Exiting Page.\nThank You!!!!");
                                 b1 = false;
                                 System.exit(0);
                                 break;

                         default:
                                System.out.println("Invalid choice. Try again.");
                                break;
                               
                        }
                        if (choice == 9) {
                            break;
                        }
                    }       
            }

            sc.close();
            con.close();

        } catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Some Neccesary Methods
    public static int getValidInt(String fieldName) {
        int validInt = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print( fieldName );
            if (scanner.hasNextInt()) {
                validInt = scanner.nextInt();
                isValid = true;
            } else {
                System.out.println("Error: Please Enter a Valid Integer.\n");
                scanner.next(); // Clear the invalid input
            }
        }
        return validInt;
    }

    public static String getValidString(String fieldName) {
        String input;
        boolean isValid;

        do {
            System.out.print(fieldName);
            input = scanner.next();
            isValid = true;

            // Check if the input contains any digits
            for (int i = 0; i < input.length(); i++) {
                if (Character.isDigit(input.charAt(i))) {
                    isValid = false;
                    System.out.println("Error: Please Enter a Valid String.\n");
                    break;
                }
            }

        } while (!isValid);

        return input;
    }

    public static long getValidLong(String fieldName) {
        long input = 0;
        boolean isValid;

        do {
            System.out.print(fieldName);
            try {
                input = Long.parseLong(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please Enter a Valid Number.\n");
                isValid = false;
            }
        } while (!isValid);

        return input;
    }

    static boolean login(String userID, String password) {
        boolean isAuthenticated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/studentmanagementsystem";
            String username = "root";
            String dbPassword = "";

            // Establishing connection
            connection = DriverManager.getConnection(url, username, dbPassword);

            // Query to verify user credentials
            String query = "SELECT password FROM login WHERE user_id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                // Compare passwords
                if (storedPassword.equals(password)) {
                    isAuthenticated = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isAuthenticated;
    }

    // Method to create a new user
     static void createUser(String userID, String password) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/studentmanagementsystem";
            String username = "root";
            String dbPassword = "";

            // Establishing connection
            connection = DriverManager.getConnection(url, username, dbPassword);

            // Check if user already exists
            String checkQuery = "SELECT user_id FROM login WHERE user_id = ?";
            statement = connection.prepareStatement(checkQuery);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("User ID already exists. Please choose a different one.");
            } else {
                // Insert new user into database
                String insertQuery = "INSERT INTO login (user_id, password) VALUES (?, ?)";
                statement = connection.prepareStatement(insertQuery);
                statement.setString(1, userID);
                statement.setString(2, password);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("User created successfully!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     }
  
}