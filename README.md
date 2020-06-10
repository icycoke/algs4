# algs4
This project contains all the solutions with full marks and corresponding grader feedbacks for the assignments of [Algorithms, Part I by Princeton University](https://www.coursera.org/learn/algorithms-part1?). The project is built to help students learn better rather than escape the test. <b> DO NOT CHEAT PLEASE.</b>

There are 5 assignments for 5 weeks study in total:
1. Percolation
2. Deques and Randomized Queues
3. Collinear Points
4. 8 Puzzle
5. Kd-Trees

All the source code files are under [/src/](https://github.com/icycoke/algs4/tree/master/src) and corresponding grader feedbacks are under [/graderFeedback/](https://github.com/icycoke/algs4/tree/master/graderFeedback).

Because of the auto grader disallow student to submit class files in any package, I didn't create directories under [/src/](https://github.com/icycoke/algs4/tree/master/src). However you can easily tell which source files belong to which week's task by the following information.

## Week1: Percolation
1. Specification: [Percolation](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php)
2. Source codes:
   1. [Percolation.java](https://github.com/icycoke/algs4/blob/master/src/Percolation.java)
   2. [PercolationStats.java](https://github.com/icycoke/algs4/blob/master/src/PercolationStats.java)
3. Grader feedback: [graderFeedback1.txt](https://github.com/icycoke/algs4/blob/master/graderFeedback/graderFeedback1.txt)
4. Grade: 100/100

## Week2: Deques and Randomized Queues
1. Specification: [Queues](https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php)
2. Source codes:
   1. [Deque.java](https://github.com/icycoke/algs4/blob/master/src/Deque.java)
   2. [RandomizedQueue.java](https://github.com/icycoke/algs4/blob/master/src/RandomizedQueue.java)
   3. [Permutation.java](https://github.com/icycoke/algs4/blob/master/src/Permutation.java)
3. Grader feedback: [graderFeedback2.txt](https://github.com/icycoke/algs4/blob/master/graderFeedback/graderFeedback2.txt)
4. Grade: 100/100

## Week3: Collinear Points
1. Specification: [Collinear Points](https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php)
2. Source codes:
   1. [Point.java](https://github.com/icycoke/algs4/blob/master/src/Point.java)
   2. [BruteCollinearPoints.java](https://github.com/icycoke/algs4/blob/master/src/BruteCollinearPoints.java)
   3. [FastCollinearPoints.java](https://github.com/icycoke/algs4/blob/master/src/FastCollinearPoints.java)
   4. [LineSegment.java](https://github.com/icycoke/algs4/blob/master/src/LineSegment.java) (Don't need to code by yourself but offered by the grader)
3. Grader feedback: [graderFeedback3.txt](https://github.com/icycoke/algs4/blob/master/graderFeedback/graderFeedback3.txt)
4. Grade: 100/100

* <i>Based on my observations and hundreds of tests, the auto grader for this assignment may feedback some misleading suggestions. For instance, it told me to avoid using toString() method which was actually not used in my code. However, this issue went away after I fix a bug (I wrongly coded '<' to '>') which was not related to the issue. Different misleading suggestions occurred under different circumstances many times. As a result, I suggest you to consider the bugs without referring to the feedback when you meet a similar situation.</i>

## Week4: 8 Puzzle
1. Specification: [8 Puzzle](https://coursera.cs.princeton.edu/algs4/assignments/8puzzle/specification.php)
2. Source codes:
   1. [Board.java](https://github.com/icycoke/algs4/blob/master/src/Board.java)
   2. [Solver.java](https://github.com/icycoke/algs4/blob/master/src/Solver.java)
3. Grader feedback: [graderFeedback4.txt](https://github.com/icycoke/algs4/blob/master/graderFeedback/graderFeedback4.txt)
4. Grade: 100/100

* <i>Tip: If you are stuck with the memory issue, try to use more local variables instead of instance variables.</i>
---
##To be continued
