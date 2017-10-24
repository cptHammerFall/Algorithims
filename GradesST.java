/**
 * Author     : Talon
 * Program    :
 * Date       :
 * Assignment :
 */
package classexersisest;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

/**
 *
 * @author TALONted
 */
public class GradesST<Key extends Comparable<Key>, Value> {

    static String[] letters = new String[]{"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E"};
    static double[] value = new double[]{4, 3.67, 3.33, 3, 2.67, 2.33, 2.00, 1.67, 1.33, 1, 0.67, 0};

//    ST st;
//
//    public GradesST() {
//        st = new ST();
//        for (int i = 0; i < letters.length; i++) {
//            st.put(letters[i], value[i]);
//        }
//    }
//    public double GPA() {
//            double GPA = 0;
//            for (int i = 0; i < grades.length; i++) {
//                
//            }
//
//            return GPA;
//        }

    /**
     * create a student with an array of all his grades and a name
     */
    
        
    

    public static void main(String[] args) {

        
        ST<String, Double> st = new ST<>();
        for(int i =0; i<letters.length; i++) {
            st.put(letters[i], value[i]);
        }
        Student[] students;

        In in = new In("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/ClassExersiseST/src/classexersisest/students.txt");

        students = new Student[in.readAllLines().length];
        StdOut.println(students.length);
        int counter = 0;

        in = new In("/Users/TALONted/Desktop/SLCC_School/2017-Fall/Data&Algorithims/ClassExersiseST/src/classexersisest/students.txt");
        while (in.hasNextLine()) {
            
            String line = in.readLine();
            
            String[] tokens = line.split("-");
            String[] gradeTokens = tokens[1].split(",");
            
            String name = tokens[0];
            String[] grades = gradeTokens;
            students[counter] = new Student(name, grades);
            StdOut.println(students[counter].toString());
            counter++;

        }
        
        String[] gradeArray;
        double fullAvg = 0;
        
        for (Student student : students) {
            gradeArray = student.getGrades();
            double singleAvg = 0;
            for (String gradeArray1 : gradeArray) {
                singleAvg += st.get(gradeArray1);
//                if (st.contains(gradeArray1)) {
//                    StdOut.println("Were good on: " + gradeArray1);
//                }
            }
            fullAvg += singleAvg;
        }
        double avg = fullAvg/letters.length;
        StdOut.printf("Average GPA = %.2f\n", avg);
    }
}
