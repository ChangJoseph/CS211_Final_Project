# CS211 Final Project

## 1. Introduction
This repo contains the source code for CS211 final project of (Lab 210) Joseph Chang, Hannah Martinez, and Yeoungmin Shin
The purpose of this project is to allow students in George Mason University to have a calculator tool to find current gpa/grade based on a number of inputs as well as what future grades are needed to pass a class


## 2. Structure
We will separate our classes based on 3 packages
1. Utility classes
2. Core calculation classes
3. Integration classes

### 2.1 Utility
In order to maximize workflow, we would have general methods and constants here that can be used in the core classes.
### 2.2 Core
There will be different types of calculators for different purposes as well as broader (parent/abstract) calculators so we would be able to utilize a certain calculator for a certain reason.
### 2.3 Integration
Here, we could have a mixture of things based on user preference:
- a dedicated input/output class would be necessary that would have a simple constructor to initialize a reader for console input as well as methods to hand the stream to whichever class calls it.
- a Java swing gui that would allow users to input their grades and such more intuitively (though we may not have nearly enough time for this implementation).

### 2.4 Package Structure
Our .java source code is located in ./src/package_name/class_name.java
The compiled .class files are located in ./bin/package_name/class_name.class
Our JUnit tests are located in ./test/package_name/name.java


## 3. Team Assignments
### 3.1 Chang, Joseph
Documentation, Json Classes, Java Swing and JavaFX
### 3.2 Martinez, Hannah
Test Classes and Scale.java
### 3.3 Shin, Yeoungmin
Brainstorming and Core Classes


## 4. Conclusion
This project tackles a pretty big issue within GMUs grading evaluation, and we showed our take on how we would fix it.

## 5. Use
If you are someone that actually wants to use this project to calculate your grades, go to the "releases" tab (below the project description; above the file explorer) and download the zip. Make sure to have a JRE installed. *Note that the project is still in beta*