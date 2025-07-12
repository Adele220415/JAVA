// public static void main(String[] args){
    //     int score = 80;
    //     if (score>=70) {
    //         System.out.println("A");
    //     }else if (score>=60) {
    //         System.out.println("B");
    //     }else if (score>=50) {
    //         System.out.println("C");
    //     }else if (score>=40) {
    //         System.out.println("D");
    //     }else if (score>=30) {
    //         System.out.println("E");
    //     }else if (score<=29) {
    //         System.out.println("F");
    //     }else {
    //         System.out.println("Invalid score");
    //     }

    // public static void main(String[] args) {
    //     int score = 80;
    //     String grade;

    //     if (score >= 70) {
    //         grade = "A";
    //     } else if (score >= 60) {
    //         grade = "B";
    //     } else if (score >= 50) {
    //         grade = "C";
    //     } else if (score >= 40) {
    //         grade = "D";
    //     } else if (score >= 30) {
    //         grade = "E";
    //     } else if (score < 30) {
    //         grade = "F";
    //     } else {
    //         grade = "Invalid score";
    //     }
    
    //     System.out.println(grade);
    // }
    // int i = 10;
    // while (i > 1) {
        //     System.out.println("i = " + i);
        //     i--;
        // }
        
        public class Control {
    public static void main(String[] args){
        int password = 1234;
        int input = 0;
        do {
            System.out.print("Enter password: ");
            input = new java.util.Scanner(System.in).nextInt();
            if (input == password) {
                System.out.println("Access granted.");
            } else {
                System.out.println("Access Denied, Try Again");
            }
        } while (input != password);
    }
}