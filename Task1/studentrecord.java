import java.util.ArrayList;
import java.util.Scanner;

public class studentrecord {
    String name;
    int math, sci, eng;

    studentrecord(String name, int math, int sci, int eng) {
        this.name = name;
        this.math = math;
        this.sci = sci;
        this.eng = eng;
    }

    public int getTotal() {
        return math + sci + eng;
    }

    public double getAverage() {
        return getTotal() / 3.0;
    }

    public int getHighest() {
        return Math.max(math, Math.max(sci, eng));
    }

    public int getLowest() {
        return Math.min(math, Math.min(sci, eng));
    }
}

class StudentGrade {
    public static void main(String[] args) {
        ArrayList<studentrecord> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of student :");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Math Marks: ");
            int math = sc.nextInt();
            System.out.print("Science Marks: ");
            int sci = sc.nextInt();
            System.out.print("English Marks: ");
            int eng = sc.nextInt();
            students.add(new studentrecord(name, math, sci, eng));
            System.out.println();
        }
        System.out.println("..................STUDENT REPORT..................");
        System.out.println();
        for (int i = 0; i < students.size(); i++) {
            studentrecord s = students.get(i);
            System.out.println("Name: " + s.name);
            System.out.println("  Math: " + s.math);
            System.out.println("  Science: " + s.sci);
            System.out.println("  English: " + s.eng);
            System.out.println("  Total: " + s.getTotal());
            System.out.printf ("  Average: %.1f%n", s.getAverage());
            System.out.println("  Highest: " + s.getHighest());
            System.out.println("  Lowest: "  + s.getLowest());
            System.out.println();
        }
        if (students.size() > 1) 
        {
            studentrecord high = students.get(0);
            studentrecord low = students.get(0);
            for (int i = 1; i < students.size(); i++) {
                studentrecord curr = students.get(i);
                if (curr.getTotal() > high.getTotal())
                    high = curr;
                if (curr.getTotal() < low.getTotal())
                    low = curr;
            }
            System.out.println("Highest score: " + high.name + " , Total: " + high.getTotal());
            System.out.println();
            System.out.println("Lowest score: " + low.name + " , Total: " + low.getTotal());
        }
        
    }
}
