
-----------------------------
Project 4
-----------------------------

Author: Chris Rogers

IDE: NetBeans 8.2

Description: 
Java program consisting of 2 classes (RedBlackTree, Project4).
Program displays following menu to user:
1. Insert        /// Inserts element into tree
2. Contains      /// Returns true if element is in tree else returns false
3. Print Tree    /// Prints entire tree in order with * before red elements
4. Show Menu     /// Displays the menu
5. Exit          /// Exits the program



I. Sample run in NetBeans8.2
----------------------------------

run:
MENU
1. Insert
2. Contains
3. Print Tree
4. Show Menu
5. Exit
Menu Choice: 1
Insert: 8
Element inserted
Menu Choice: 1
Insert: 10
Element inserted
Menu Choice: 1
Insert: 5
Element inserted
Menu Choice: 3
*5 8 *10 
Menu Choice: 1
Insert: 6
Element inserted
Menu Choice: 3
5 *6 8 10 
Menu Choice: 1
Insert: 15
Element inserted
Menu Choice: 1
Insert: 20
Element inserted
Menu Choice: 3
5 *6 8 *10 15 *20 
Menu Choice: 5
BUILD SUCCESSFUL (total time: 59 seconds)


II. How to Run in UNIX
--------------------------------
1. save 'Project4.java' and 'RedBlackTree.java' to desired location
2. in UNIX terminal, navigate to folder containing above files
3. run ‘javac project4/Project4.java’
4. run ‘javac project4/RedBlackTree.java’
5. run ‘java project4.Project4’ command
6. use program as indicated on screen


III. Design Decisions and Issues
--------------------------------
-Started with basic tree program in textbook
-Try/Catch used to handle non-integer input
-If statement used to handle integer out of bound input

