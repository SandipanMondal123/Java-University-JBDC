import java.sql.*;
import java.util.Scanner;
import java.util.HashMap;
//Sandipan Mondal sm2364
class uniDB {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/temp", "root", "ENTERYOURPASSWORDHERE");

            Scanner sc=new Scanner(System.in);

            System.out.println("Welcome to the university database. Queries available:\n" +
                    "1. Search students by name.\n" +
                    "2. Search students by year.\n" +
                    "3. Search for students with a GPA >= threshold.\n" +
                    "4. Search for students with a GPA <= threshold.\n" +
                    "5. Get department statistics.\n" +
                    "6. Get class statistics.\n" +
                    "7. Execute an abitrary SQL query.\n" +
                    "8. Exit the application.");
            int input;
            while(true){

                System.out.println("Which query would you like to run (1-8)?");
                input = sc.nextInt();
                if(input == 8){
                    System.out.println("Goodbye.");
                    break;
                }
                else if(input == 1)
                    getQuery1(con);
                else if(input == 2)
                    getQuery2(con);
                else if(input == 3 || input == 4)
                    getQuery34(con, input);
                else if(input == 5)
                    getQuery5(con);
                else if(input == 6)
                    getQuery6(con);
                else{
                    getQuery7(con);
                }

            }


            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public static String[] getGPA(Connection con, String studentID) {
        try{
            Statement stmt = con.createStatement();
            String statement = String.format("select SUM( CASE ct.grade WHEN 'A' THEN 4.0 WHEN 'B' THEN 3.0 WHEN 'C' THEN 2.0 WHEN 'D' THEN 1.0 WHEN 'F' THEN 0.0  END * c.credits) / SUM(c.credits) AS avg_gpa, SUM(c.credits) as creditCount from Students s JOIN HasTaken ct ON s.id = ct.sid JOIN Classes c ON ct.name = c.name " +
                    "where s.id = '%s'", studentID);
            ResultSet rs =stmt.executeQuery(statement);

            rs.next();
            String[] gpaArr = new String[] {"" + rs.getDouble(1), "" + rs.getDouble(2)};
            return gpaArr;


        } catch (Exception e){
            System.out.println(e);
        }
        return new String[] {"NaN" , "NaN"};
    }


    public static String getDepartment(Connection con, String studentID, String tableName) {
        try{
            Statement stmt = con.createStatement();
            String statement = String.format("select group_concat(dname) from %s " +
                    "where sid = '%s'", tableName,studentID);
            ResultSet rs =stmt.executeQuery(statement);

            rs.next();
            String output = rs.getString(1);
            if(output == null)
                return "None";
            return rs.getString(1);


        } catch (Exception e){
            System.out.println(e);
        }
        return "NaN";
    }
    public static void getQuery1(Connection con) {
        try{
            Scanner sc=new Scanner(System.in);
            System.out.println("Please enter the name.");
            String name1 = sc.nextLine();
            name1 = name1.toLowerCase();


            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id, first_name, last_name from Students " +
                    "where lower(first_name) like '%" + name1 + "%' or lower(last_name) like '%" + name1 + "%'");


            int numStudents = 0;
            String output = "";
            while (rs.next()){
                numStudents++;
                String studentID = rs.getString(1);
                String studentFirst_Name = rs.getString(2);
                String studentLast_name = rs.getString(3);
                String[] avgGPA = getGPA(con, studentID);
                String creditNum = getNonFCredit(con, studentID);
                String major = getDepartment(con, studentID, "Majors");
                String minor = getDepartment(con, studentID, "Minors");
                if (minor.equals("None")){
                    output += studentLast_name + ", " + studentFirst_Name + "\n" +
                            "ID: " + studentID + "\n" +
                            "Major: " + major + "\n" +
                            "GPA: " + avgGPA[0] + "\n" +
                            "Credits: " + creditNum + "\n\n";
                }
                else{
                    output += studentLast_name + ", " + studentFirst_Name + "\n" +
                        "ID: " + studentID + "\n" +
                        "Major: " + major + "\n" +
                        "Minor: " + minor + "\n" +
                        "GPA: " + avgGPA[0] + "\n" +
                        "Credits: " + creditNum + "\n\n";
                }



            }
            System.out.printf("%d students found\n", numStudents);
            System.out.println(output);


        } catch (Exception e){
            System.out.println(e);
        }

    }
    public static void getQuery2(Connection con) {
        try{
            Scanner sc=new Scanner(System.in);
            System.out.println("Please enter the year.");
            String year2 = sc.nextLine();
            String condition = switch (year2) {
                case "Fr" -> "  creditCount < 30";
                case "So" -> " creditCount between 30 and 59";
                case "Ju" -> " creditCount between 60 and 89";
                default -> " creditCount > 90";
            };

            Statement stmt = con.createStatement();
            //String query = String.format("select id, first_name, last_name, sum(credits) as creditCount from students s, hastaken ct, classes c where s.id = ct.sid and ct.name = c.name group by id, first_name, last_name having %s", condition);
            String query1 = String.format("select id, first_name, last_name, sum(credits) as creditCount from students s, hastaken ct, classes c where s.id = ct.sid and ct.name = c.name and ct.grade != 'F' group by id, first_name, last_name having %s", condition);

            ResultSet rs = stmt.executeQuery(query1);


            int numStudents = 0;
            String output = "";
            while (rs.next()){
                numStudents++;
                String studentID = rs.getString(1);
                String studentFirst_Name = rs.getString(2);
                String studentLast_name = rs.getString(3);
                String[] avgGPA = getGPA(con, studentID);
                String creditNum = getNonFCredit(con, studentID);

                String major = getDepartment(con, studentID, "Majors");
                String minor = getDepartment(con, studentID, "Minors");

                if (minor.equals("None")){
                    output += studentLast_name + ", " + studentFirst_Name + "\n" +
                            "ID: " + studentID + "\n" +
                            "Major: " + major + "\n" +
                            "GPA: " + avgGPA[0] + "\n" +
                            "Credits: " + creditNum + "\n\n";
                }
                else{
                    output += studentLast_name + ", " + studentFirst_Name + "\n" +
                            "ID: " + studentID + "\n" +
                            "Major: " + major + "\n" +
                            "Minor: " + minor + "\n" +
                            "GPA: " + avgGPA[0] + "\n" +
                            "Credits: " + creditNum + "\n\n";
                }


            }
            System.out.printf("%d students found\n", numStudents);
            System.out.println(output);


        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static void getQuery34(Connection con, int queryNum) {
        try{
            Scanner sc=new Scanner(System.in);
            System.out.println("Please enter the threshold.");
            String gpa3 = sc.nextLine();
            if(queryNum == 3)
                gpa3 = ">= " + gpa3;
            else
                gpa3 = "<= " + gpa3;

            Statement stmt = con.createStatement();
            String query = String.format("select id, first_name, last_name, SUM( CASE ct.grade WHEN 'A' THEN 4.0 WHEN 'B' THEN 3.0 WHEN 'C' THEN 2.0 WHEN 'D' THEN 1.0 WHEN 'F' THEN 0.0  END * c.credits) / SUM(c.credits) AS avg_gpa from students s, hastaken ct, classes c where s.id = ct.sid and ct.name = c.name group by id, first_name, last_name having avg_gpa %s", gpa3);
            ResultSet rs = stmt.executeQuery(query);


            int numStudents = 0;
            String output = "";
            while (rs.next()){
                numStudents++;
                String studentID = rs.getString(1);
                String studentFirst_Name = rs.getString(2);
                String studentLast_name = rs.getString(3);
                String[] avgGPA = getGPA(con, studentID);
                String major = getDepartment(con, studentID, "Majors");
                String minor = getDepartment(con, studentID, "Minors");
                String creditNum = getNonFCredit(con, studentID);
                if (minor.equals("None")){
                    output += studentLast_name + ", " + studentFirst_Name + "\n" +
                            "ID: " + studentID + "\n" +
                            "Major: " + major + "\n" +
                            "GPA: " + avgGPA[0] + "\n" +
                            "Credits: " + creditNum + "\n\n";
                }
                else{
                    output += studentLast_name + ", " + studentFirst_Name + "\n" +
                            "ID: " + studentID + "\n" +
                            "Major: " + major + "\n" +
                            "Minor: " + minor + "\n" +
                            "GPA: " + avgGPA[0] + "\n" +
                            "Credits: " + creditNum + "\n\n";
                }


            }
            System.out.printf("%d students found\n", numStudents);
            System.out.println(output);


        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static String getNonFCredit(Connection con, String id){
        try{
            Statement stmt = con.createStatement();

            String statement = String.format("select sum(credits) as creditCount from students s, hastaken ct, classes c where s.id = ct.sid and ct.name = c.name and ct.grade != 'F' and s.id = '%s'", id);

            ResultSet rs =stmt.executeQuery(statement);

            rs.next();
            String output = rs.getString(1);
            if(output == null)
                return "None";
            return rs.getString(1);


        } catch (Exception e){
            System.out.println(e);
        }
        return "NaN";
    }

    public static void getQuery5(Connection con) {
        try{
            Scanner sc=new Scanner(System.in);
            System.out.println("Please enter the department.");
            String departmentName = sc.nextLine();
            departmentName = departmentName.toLowerCase();


            Statement stmt = con.createStatement();
            String query = String.format("(select distinct Sid , dname from majors where lower(dname) = '%s' ) union ( select distinct Sid, lower(dname) from minors where dname = '%s' )", departmentName, departmentName);
            ResultSet rs = stmt.executeQuery(query);


            int numStudents = 0;
            String output = "";
            double sum = 0;
            while (rs.next()){
                numStudents++;
                String studentID = rs.getString(1);
                String[] avgGPA = getGPA(con, studentID);
                sum += Double.parseDouble(avgGPA[0]);


            }
            System.out.println("Num students: " +  numStudents);
            if(numStudents == 0)
                System.out.println("Average GPA: 0.0" );
            else
                System.out.println("Average GPA: " + (sum/numStudents));



        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static void getQuery6(Connection con) {
        try{
            Scanner sc=new Scanner(System.in);
            System.out.println("Please enter the class name.");
            String className = sc.nextLine();
            className = className.toLowerCase();


            Statement stmt = con.createStatement();
            String currentlyTakingQuery = String.format("select count(*) from isTaking t where lower(t.name) = '%s'", className);
            ResultSet rs = stmt.executeQuery(currentlyTakingQuery);
            rs.next();
            String currentlyTaking = rs.getString(1);


            String previouslyTaking = String.format("select grade, count(*) from hastaken t where lower(t.name) = '%s' group by grade", className);
            rs = stmt.executeQuery(previouslyTaking);


            HashMap<String, String> grades = new HashMap<String, String>();

            // Add keys and values (Country, City)
            grades.put("A", "0");
            grades.put("B", "0");
            grades.put("C", "0");
            grades.put("D", "0");
            grades.put("F", "0");


            int numStudents = 0;

            while (rs.next()){
                numStudents++;
                String letter = rs.getString(1);
                grades.put(letter, rs.getString(2));

            }
            System.out.println(currentlyTaking + " students currently enrolled");
            System.out.println("Grades of previous enrollees:");
            System.out.println("A " + grades.get("A"));
            System.out.println("B " + grades.get("B"));
            System.out.println("C " + grades.get("C"));
            System.out.println("D " + grades.get("D"));
            System.out.println("F " + grades.get("F"));





        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static void getQuery7(Connection con) {
        try{
            Scanner sc=new Scanner(System.in);
            System.out.println("Please enter the query.");
            String query = sc.nextLine();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int colCount = rsmd.getColumnCount();

            String colrow = "";
            for (int i = 1; i <= colCount; i++)
            {
                //getting column name of index 'i'

                String colName = rsmd.getColumnName(i) + "\t";

                colrow += colName;
            }
            System.out.println(colrow);
            while(rs.next()){
                String row = "";
                for(int i = 1; i<= colCount; i++){
                    row += rs.getString(i) + "\t";
                }
                System.out.println(row);
            }


        } catch (Exception e){
            System.out.println("Your input is INVALID. SQL ERROR TO BE SPECIFIC. Please try again");
            getQuery7(con);
        }

    }

}



