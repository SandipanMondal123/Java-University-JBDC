# UniversityJavaDB

In this program I have 2 files aside from this readme: uniDB.java and uniPopulation.java. 

My uniDB.java file is the “main” program that contains the actual logic of the program. This is the entry point. Below is info of the second file.

The uniPopulation.java file is the file that houses all the random generation of students and data for all tables required for this assignment. 

- I created 25 4 credit and 25 3 credit courses that were housed in seperate ArrayLists.
- I put all the departments/campus in an array list.

The departments, classes, majors, and minors table was trivial as all i needed to do is create a loop that iterated through the above mentioned arraylists and it would add all the data that I would need for those tables.

- I created 100 unique first and last names that were housed in seperate ArrayLists.

The "true" random generation starts here as this is the part where I randomly assign students their curresnt/past classes, the grade they recieved, the number of credits (year), majors, minors etc.

In order to do this, I made a large for loop that would iteration through the the 100 unique first/last names (above mentioned arraylist). Now, within that for loop does the random generation come into play. The first 25 are Fr, next 25 of So, next 25 are Jr, and the last are Sr.

For instance, lets assume i'm inserting student with id 10000045. In this case, this person is a So. As a result, his credit count (without Fs in grades) would be range in the range of 30-59 to qualify that person as a sophomore. As a result, that person will be randomly have X 3 credit classes and Y 4 credit classes with the help of the Random class that has a set of predefined lower limits and upper limits based on the students year. As a result, it could be a possibility that the random sort makes it so that has 4 4 credit classes and 6 3 credit classes or 5 4 credit classes and 6 3 credit classes as an example. There are more possibilities but this is just an example combination of classes. These classes sum to be within the credit requirement of being a sophomore. After these selection are done, within that large for loop that was mentioned above, i created 2 mini loops that will randomly iterate through the 3 and 4 credit arraylists X and Y number of times respectively where X and Y are the number of classes that was randomly selected for that student (above). In order to ensure that it is randomly selecting classes from within the 2 array lists, I utilized the Collections package with the shuffle method which makes it so that arrayList is shuffled after each completion of the inner for loops for class selection. In order to determine grade, I simply used the Random class to help randomly choose a letter grade from an ArrayList containing the distinct letter grades. If an F grade is selected, that would mean, according to the professor that, that class doesn’t contribute to the credit count and as a result, I would select an extra class to make up for it. This will continue until the student has X and Y classes. As this is occurring, I’m inserting rows/info into their respective tables. In addition, within this large for loop, I randomly choose the major and minor with the help of the random class that would select an index from the majors/minors table for a number of times depending on the output. 

This is a simplified view of my random generation in the uniPopulation.java class.

# Note: These programs work me and my specific system (Intel based Mac on IntelliJ) so make sure to configure your settings as this program run specifications are dependent to my system (for example, enter your personal password)
