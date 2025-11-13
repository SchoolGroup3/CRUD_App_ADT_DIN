# CRUD_App_ADT_DIN

Repository Link: ([CRUD_App_ADT_DIN](https://github.com/SchoolGroup3/CRUD_App_ADT_DIN.git))

## Database Setup

1. Locate the file **`CRUD_ADT.sql`** in the CRUD_SQL folder.
2. Open this file in **MySQL Workbench** and run it. This will create the database and insert the initial data.

## Netbeans Setup

1. Open Netbeans 8.2 and select File > New Project on the top left menu
2. In the JavaFX category, select "JavaFX Project with Existing Sources" (The last option)
3. Configure the name and location for your project, then click next
4. In the source packages folders, click the add button and add the src folder inside CRUD_Proyecto/Reto_CRUD_ADT
   4.1. In the test packages folders, click the add button and add the test folder inside CRUD_Proyecto/Reto_CRUD_ADT
5. Click the finish button
6. Right click on your netbeans project in the left section, and select properties
7. In the libraries section, click "add JAR/Folder" and select all the jars in the CRUD_Proyecto/Reto_CRUD_ADT/src directory
   7.1 In the compile tests tab, add the FxTest Library which was created in class aswell as the JUnit4.12 library if not included in the FxTest
8. Press Ok at the bottom, then configure the file **`configClase.properties`** in the "<default package>" section
9. Press play and select "main.Main" as the application class

## Project Division

Kevin:<br>
-Windows (Design): User modification, change password <br>
-Windows (Functionality): User modification, change password, linking user home together, functionality to most popups<br>
-Tests: Admin home test, user modify test<br>
-Other: Pool management, bug fixes<br>
Mosi:<br>
-Windows (Design): Admin home, admin modification<br>
-Windows (Functionality): Admin home, admin modification, user modification/deletion from admin table (Almost same as Kevins part but without the home bar)<br>
-Tests: Admin modify test<br>
-Other: bug fixes<br>
Mikel:<br>
-Windows (Design): Login, Signup<br>
-Windows (Functionality): Login, Signup<br>
-Tests: Signup test, home window test<br>
-Other: Design fixes<br>
Jagoba:<br>
-Windows (Design): Home, all popups except change password<br>
-Windows (Functionality): Home<br>
-Tests: Delete user test, logout test, change password test, login test<br>
-Other: Class diagram fixes<br>
