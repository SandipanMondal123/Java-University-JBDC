
import java.sql.*;
import java.util.*;
//Sandipan Mondal sm2364
public class uniPopulation {
    public static void insertData() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/temp",
                    "root", "ENTERYOUROWNPASSWORDHERE");

            PreparedStatement ps = conn.prepareStatement("DROP TABLE IF EXISTS Majors"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();
            ps = conn.prepareStatement("DROP TABLE IF EXISTS Minors"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();
            ps = conn.prepareStatement("DROP TABLE IF EXISTS IsTaking"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();
            ps = conn.prepareStatement("DROP TABLE IF EXISTS HasTaken"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();
            /*
                CREATION OF DEPARTMENTS TABLE
             */
            ps = conn.prepareStatement("DROP TABLE IF EXISTS Departments"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();
            ps = conn.prepareStatement("CREATE TABLE Departments(name VARCHAR(100), campus VARCHAR(100), PRIMARY KEY (name))"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();

            ArrayList<String> departments = new ArrayList<String>(Arrays.asList("Bio", "Chem", "CS", "Eng", "Math", "Phys"));
            ArrayList<String> campus = new ArrayList<String>(Arrays.asList("Busch", "CAC", "Livi", "CD", "Busch", "CAC"));

            ps = conn.prepareStatement("INSERT INTO Departments VALUES(?, ?)");

            for (int i = 0; i < 6; i++)
            {
                ps.setString(1, departments.get(i));
                ps.setString(2, campus.get(i));
                ps.executeUpdate();
            }

            /*
                CREATION OF CLASSES TABLE
             */

            ps = conn.prepareStatement("DROP TABLE IF EXISTS Classes"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();
            ps = conn.prepareStatement("CREATE TABLE Classes(name VARCHAR(100), credits INT, PRIMARY KEY (name))"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();

            ArrayList<String> classes3 = new ArrayList<String>(Arrays.asList("Intro to lion", "Intro to tiger", "Intro to elephant", "Intro to giraffe", "Intro to gorilla", "Intro to monkey", "Intro to zebra", "Intro to hippopotamus", "Intro to crocodile", "Intro to kangaroo", "Intro to koala", "Intro to panda", "Intro to penguin", "Intro to dolphin", "Intro to whale", "Intro to shark", "Intro to octopus", "Intro to jellyfish", "Intro to seagull", "Intro to owl", "Intro to snake", "Intro to lizard", "Intro to rhinoceros", "Intro to buffalo", "Intro to gazelle"));
            ArrayList<String> classes4 = new ArrayList<String>(Arrays.asList("Advanced lion", "Advanced tiger", "Advanced elephant", "Advanced giraffe", "Advanced gorilla", "Advanced monkey", "Advanced zebra", "Advanced hippopotamus", "Advanced crocodile", "Advanced kangaroo", "Advanced koala", "Advanced panda", "Advanced penguin", "Advanced dolphin", "Advanced whale", "Advanced shark", "Advanced octopus", "Advanced jellyfish", "Advanced seagull", "Advanced owl", "Advanced snake", "Advanced lizard", "Advanced rhinoceros", "Advanced buffalo", "Advanced gazelle"));

            ps = conn.prepareStatement("INSERT INTO Classes VALUES(?, ?)");

            for (int i = 0; i < classes3.size(); i++)
            {
                ps.setString(1, classes3.get(i));
                ps.setInt(2, 3);
                ps.executeUpdate();

                ps.setString(1, classes4.get(i));
                ps.setInt(2, 4);
                ps.executeUpdate();
            }







            ArrayList<String> letterGrade = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "F"));
            ArrayList<String> fnames = new ArrayList<String>(Arrays.asList("Julian", "Grace", "Ariana", "Abigail", "Evelyn", "Cameron", "Easton", "Mila", "Aurora", "Ava", "Zoey", "Luna", "Nathan", "Luke", "William", "Gianna", "Victoria", "Isabella", "Asher", "Sebastian", "Zoe", "Caleb", "Cora", "Mia", "Elena", "Elijah", "Oliver", "Lincoln", "Olivia", "Lucas", "Benjamin", "Ethan", "Audrey", "Scarlett", "Sofia", "Avery", "Skylar", "Logan", "Avery", "Riley", "Lillian", "Aiden", "Charlotte", "Sophia", "Jaxson", "Nicholas", "Evelyn", "Elizabeth", "Genesis", "Hazel", "Makayla", "Harper", "Hunter", "Ellie", "Chloe", "Evan", "Mason", "Liam", "Levi", "Caden", "Isabelle", "Noah", "Adrian", "Amelia", "Brayden", "Ella", "Allison", "Bryson", "Jackson", "Aaliyah", "Hailey", "Michael", "Nora", "Sofia", "Aiden", "Caroline", "Mateo", "Aubrey", "Emma", "Connor", "Penelope", "Madison", "Naomi", "Jacob", "Ella", "Delilah", "Natalie", "Lily", "Emily", "Leah", "Nova", "Grayson", "Aria", "Samantha", "Layla", "Hinata", "Naruto", "Luffy", "Nami", "Zoro"));
            ArrayList<String> lnames = new ArrayList<String>(Arrays.asList("Uzumaki", "Dragon", "Roger", "Garp", "Gaara", "Hyuga", "Senju","Agarwal", "Ahluwalia", "Ahuja", "Bakshi", "Balakrishnan", "Bali", "Banerjee", "Batra", "Bhalla", "Bhat", "Bhatti", "Chakraborty", "Chandra", "Chatterjee", "Chawla", "Cheema", "Chhibber", "Chopra", "Datta", "Desai", "Deshpande", "Dhawan", "Dixit", "Doshi", "Dutta", "Gandhi", "Ganguly", "Garg", "Ghosh", "Gill", "Gupta", "Iyer", "Jain", "Jha", "Kapadia", "Kapoor", "Kaul", "Khanna", "Khanuja", "Khatri", "Khosla", "Kohli", "Kumar", "Lal", "Mahajan", "Malhotra", "Mehra", "Mehta", "Mitra", "Mukherjee", "Nair", "Nanda", "Narang", "Narula", "Natarajan", "Nehru", "Pandey", "Pandit", "Patel", "Pillai", "Rai", "Rajan", "Rajput", "Raman", "Rao", "Ray", "Reddy", "Saini", "Sarin", "Saxena", "Seth", "Shah", "Shankar", "Sharma", "Singh", "Solanki", "Somani", "Soni", "Sood", "Srinivasan", "Srivastava", "Subramanian", "Suri", "Talwar", "Thakur", "Trivedi", "Varma", "Vasudevan", "Venkatesh", "Verma", "Vohra", "Yadav", "Zaveri"));
            int n = lnames.size();
            int doubleMajorCount = 20;
            int minorCount = 30;

            int minFreshman3 = 2;
            int minFreshman4 = 2; //14
            int maxFreshman3 = 3;
            int maxFreshman4 = 3; //21

            int minSophomore3 = 5;
            int minSophomore4 = 4; //15 + 16 = 31
            int maxSophomore3 = 6;
            int maxSophomore4 = 6; //18 + 24 = 42

            int minJunior3 = 9;
            int minJunior4 = 9; //27 + 36 =53
            int maxJunior3 = 10;
            int maxJunior4 = 10; //30 + 40 = 70

            int minSenior3 = 13;
            int minSenior4 = 13; //39 + 56 = 91
            int maxSenior3 = 15;
            int maxSenior4 = 15; //45 + 60 = 105



            ps = conn.prepareStatement("DROP TABLE IF EXISTS Students"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();
            ps = conn.prepareStatement("CREATE TABLE Students(first_name VARCHAR(100), last_name VARCHAR(100), id VARCHAR(9), PRIMARY KEY (id))"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();

            ps = conn.prepareStatement("CREATE TABLE Majors(sid VARCHAR(9), dname VARCHAR(100), FOREIGN KEY (sid) references Students(id), FOREIGN KEY (dname) references Departments(name))"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();

            ps = conn.prepareStatement("CREATE TABLE Minors(sid VARCHAR(9), dname VARCHAR(100), FOREIGN KEY (sid) references Students(id), FOREIGN KEY (dname) references Departments(name))"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();

            ps = conn.prepareStatement("CREATE TABLE IsTaking(sid VARCHAR(9), name VARCHAR(100), FOREIGN KEY (sid) references Students(id), FOREIGN KEY (name) references Classes(name))"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();
            ps = conn.prepareStatement("CREATE TABLE HasTaken(sid VARCHAR(9), name VARCHAR(100), grade VARCHAR(1) , FOREIGN KEY (sid) references Students(id), FOREIGN KEY (name) references Classes(name))"); //If you need to preserve table contents, don't use this query!
            ps.executeUpdate();

            Random random = new Random();

            int idVALUE = 10000001;
            for (int i = 0; i < 100; i++)
            {
                String stridVALUE = Integer.toString(idVALUE);
                idVALUE++;

                ps = conn.prepareStatement("INSERT INTO Students VALUES(?, ?, ?)");

                ps.setString(1, fnames.get(i));
                ps.setString(2, lnames.get(i));
                ps.setString(3, stridVALUE);
                ps.executeUpdate();

                ps = conn.prepareStatement("INSERT INTO Majors VALUES(?, ?)");
                int majorRandom1 = random.nextInt(6);
                int optionalMajorRandom2;

                HashSet<Integer> majors = new HashSet<Integer>();
                majors.add(majorRandom1);

                if(i %5 == 0 && doubleMajorCount >= 0){
                    doubleMajorCount--;

                    do{
                        optionalMajorRandom2 = random.nextInt(6);
                    } while(optionalMajorRandom2 == majorRandom1);

                    majors.add(optionalMajorRandom2);

                    ps.setString(1, stridVALUE);
                    ps.setString(2, departments.get(optionalMajorRandom2));
                    ps.executeUpdate();
                }

                ps.setString(1, stridVALUE);
                ps.setString(2, departments.get(majorRandom1));
                ps.executeUpdate();



                ps = conn.prepareStatement("INSERT INTO Minors VALUES(?, ?)");
                int minor;

                if(i % 3 == 0 && minorCount >= 0){
                    minorCount--;

                    do{
                        minor = random.nextInt(6);
                    } while(majors.contains(minor));


                    ps.setString(1, stridVALUE);
                    ps.setString(2, departments.get(minor));
                    ps.executeUpdate();
                }
                Collections.shuffle(classes3);
                Collections.shuffle(classes4);


                ps = conn.prepareStatement("INSERT INTO HasTaken VALUES(?, ?, ?)");
                int low = random.nextInt(2) + 1;
                int high = random.nextInt(2) + 1;

                if(i < 25){
                    int three;
                    int four;
                    if(low == 1)
                        three = minFreshman3;
                    else
                        three = maxFreshman3;

                    if(high == 1)
                        four = minFreshman4;
                    else
                        four = maxFreshman4;

                    for(int x = 0; x < three; x++){
                        int grade = random.nextInt(5);
                            ps.setString(1, stridVALUE);
                            ps.setString(2, classes3.get(x));
                            ps.setString(3, letterGrade.get(grade));
                            ps.executeUpdate();
                    }

                    for(int x = 0; x < four; x++){
                        int grade = random.nextInt(5);
                        ps.setString(1, stridVALUE);
                        ps.setString(2, classes4.get(x));
                        ps.setString(3, letterGrade.get(grade));
                        ps.executeUpdate();
                    }
                }
                else if(i >= 25 && i < 50){
                    int three;
                    int four;
                    if(low == 1)
                        three = minSophomore3;
                    else
                        three = maxSophomore3;

                    if(high == 1)
                        four = minSophomore4;
                    else
                        four = maxSophomore4;

                    for(int x = 0; x < three; x++){
                        int grade = random.nextInt(5);
                        ps.setString(1, stridVALUE);
                        ps.setString(2, classes3.get(x));
                        ps.setString(3, letterGrade.get(grade));
                        ps.executeUpdate();
                    }

                    for(int x = 0; x < four; x++){
                        int grade = random.nextInt(5);
                        ps.setString(1, stridVALUE);
                        ps.setString(2, classes4.get(x));
                        ps.setString(3, letterGrade.get(grade));
                        ps.executeUpdate();
                    }

                }else if(i >= 50 && i < 75){
                    int three;
                    int four;
                    if(low == 1)
                        three = minJunior3;
                    else
                        three = maxJunior3;

                    if(high == 1)
                        four = minJunior4;
                    else
                        four = maxJunior4;

                    for(int x = 0; x < three; x++){
                        int grade = random.nextInt(5);
                        ps.setString(1, stridVALUE);
                        ps.setString(2, classes3.get(x));
                        ps.setString(3, letterGrade.get(grade));
                        ps.executeUpdate();
                    }

                    for(int x = 0; x < four; x++){
                        int grade = random.nextInt(5);
                        ps.setString(1, stridVALUE);
                        ps.setString(2, classes4.get(x));
                        ps.setString(3, letterGrade.get(grade));
                        ps.executeUpdate();
                    }

                }else{
                    int three;
                    int four;
                    if(low == 1)
                        three = minSenior3;
                    else
                        three = maxSenior3;

                    if(high == 1)
                        four = minSenior4;
                    else
                        four = maxSenior4;

                    for(int x = 0; x < three; x++){
                        int grade = random.nextInt(5);
                        ps.setString(1, stridVALUE);
                        ps.setString(2, classes3.get(x));
                        String gradeValue = letterGrade.get(grade);
                        ps.setString(3, gradeValue);
                        ps.executeUpdate();
                        if(gradeValue.equals("F"))
                            x--;



                    }

                    for(int x = 0; x < four; x++){
                        int grade = random.nextInt(5);
                        ps.setString(1, stridVALUE);
                        ps.setString(2, classes4.get(x));
                        String gradeValue = letterGrade.get(grade);
                        ps.setString(3, gradeValue);
                        ps.executeUpdate();
                        if(gradeValue.equals("F"))
                            x--;
                    }

                }

                ps = conn.prepareStatement("INSERT INTO IsTaking VALUES(?, ?)");
                int three = random.nextInt(3) + 1;
                int four = random.nextInt(3) + 1;

                for(int x = 25 - three; x < 25; x++){
                    ps.setString(1, stridVALUE);
                    ps.setString(2, classes3.get(x));
                    ps.executeUpdate();
                }
                for(int x = 25 - four; x < 25; x++){
                    ps.setString(1, stridVALUE);
                    ps.setString(2, classes4.get(x));
                    ps.executeUpdate();
                }


            }


            System.out.println("Inserted Into Every Table!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                conn.close();
            }
        }
    }
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException {
        insertData();
    }
}