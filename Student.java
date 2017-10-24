/**
 * Author     :
 * Program    :   
 * Date       :   
 * Assignment :   
 */

package classexersisest;

/**
 *
 * @author TALONted
 */
public class Student {

        private String[] grades;
        private String name;

        /**
         * constructor for student
         *
         * @param name
         * @param grades
         */
        public Student(String name, String[] grades) {
            this.grades = grades;
            this.name = name;
        }

        /**
         * return the length of the grades array
         *
         * @return
         */
        public int getGradelength() {
            return grades.length;
        }

        /**
         * return the String array of grades
         *
         * @return
         */
        public String[] getGrades() {
            return grades;
        }
        
        @Override
        public String toString() {

            return name + " grades: " + grades;
        }
}
