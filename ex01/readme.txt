
                    Exercise 1: StringList
                    -----------------------

                         unassessed
                         ----------

Description:
-----------
You have had lectures on linked lists. Now you have to extend this. You
are given the classes Node and StringList (a linked list of strings) and a driver
programm (as always) called Test. You are to implement some missing functions, in 
particular append, reverse, replace, equal, get, count and last. The details for 
these are given in the file StringList.java


Getting started:
----------------
 1. Make a directory to work in and then copy across all files
    in this directory (including this one).

 2. Compile all the java files
    - javac *.java

 3. Run the Test program
    - java Test

 4. Modify StringList.java, implementing the methods marked as // IMPLEMENT THIS
    - implement methods one at a time
    - recompile and run the Test program (3) above
    - iterate till done

 5. When you are satisfied with your work, submit your file StringList.java via MOODLE


Hints and Tips
--------------
0. Play with the Test program prior to coding and also look at Test.java
1. Test uses two lists l1 and l2, one of which is active at any time (l1). 
   By issuing a toggle (tog) command you switch between lists i.e. we swap l1 with l2.
2. l.reverse() delivers a new list! It DOES NOT alter l. You might then use
   addFront in reverse.
3. l1.append(l2) DOES alter l1, such that the last Node of l1 now points
   to the head of l2! Having done an append: what happens when we add to l2?
   what happens to l1.size()? what happens if we toggle and do a reverse? what
   happens if we do a l1.append(l1)?
   

What you should learn:
---------------------
You should learn about the way we will be working, learn about linked lists and 
what care must be taken when implementing these.


Patrick Prosser