# Elective Courses System

This project represents a web-app for managing elective courses records.


## Purpose

This web-app makes registration to elective courses easy and centralized.
Students can register for any available course from their PC's. 
Teachers can manage list of students for courses they teach at, 
leave marks for students and give them feedback.


## Technology Stack

* Java SE Development Kit 8
* JavaServer Pages 2.5
* JUnit 5.2.0
* H2 Database 1.4.197
* Maven 1.7
* Tomcat 9.0.10
* Log4j 2.11.0
* Lombok 1.18.0
* Bootstrap 3.3.7


## Getting Started

To deploy the project locally, follow these steps:

1. Use `git clone` to clone this project from git repository
2. Install [Tomcat Plugin](http://tomcat.apache.org/) to your IDE if you haven't yet
3. Build the 'war exploded' artifact in Project Configurations 
(For IntelliJ IDEA: Edit Configurations -> Deployment -> Add -> Artifact)
4. To make sure it's OK you may 'clean' and 'install' in Maven Projects -> Lifecycle
5. Run the app!

The project will be available at `localhost:8080/`.


## Capabilities and Features

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


## Database Structure

You can see database structure of a project at the following picture:  
![db-scheme](/uploads/1e528f9229557374849189af6d27ca24/db-scheme.png)

There are 5 entities in this database: 
### Student
Stores ID (as primary key), first and last name. 
Connected with Participation entity as one-to-many.
### Teacher
Contains ID, first and last name as well as Student. 
Connected with Course entity as one-to many.
### Course
Has an ID, name and ID of a teacher that delivers it. 
Connected with Participation entity as one-to-many (and, as it was mentioned earlier, with Teacher).
### Participation
This entity is being used to connect Students, Courses and Reviews (so it stores Student and Course ID's as well as its own).
### Review
Being used to store mark and feedback form Teacher for certain Student.
Connected with Participation as one-to-one.

