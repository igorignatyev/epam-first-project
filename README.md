# Elective Courses System

This project represents a web-app for managing elective courses records.

## Purpose

This web-app makes registration to elective courses easy and centralized.
Students can register for any available course from their PC's. 
Teachers can manage list of students for courses they teach at, 
leave marks for students and give them feedback.

## Database Structure

You can see database structure of a project at the following picture:  
![db-scheme](/uploads/1e528f9229557374849189af6d27ca24/db-scheme.png)

## Capabilities and features

There are 3 different types of users in the system. 
Each of them has a certain set of possible actions.

1. Students. They are capable of the following actions:
    * register to the courses;
    * cancel participation in courses they had registered to;
    * see the list of courses they are registered for;
    * see their marks and feedback from teachers.
   
2. Teachers. They can perform following actions:
    * see the list of courses they teach at;
    * see the list of students for each course they teach at;
    * give marks and feedback students from above-mentioned list.
   
3. Administrator. He can make almost any change to the database, 
except modifying other users' e-mail and password. 
Particularly, he is able to:
    * create and delete courses;
    * create and delete student and teacher accounts;
    * see any table from the application database;
    * perform any action that student or teacher is capable of. 