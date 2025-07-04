public class Operators {
    public static void main(String[] args){
        // Logical &&, ||, !(AND, OR, NOT)
        boolean x = true, y = false;
        System.out.println(x || y);

        // Assingment =, +=, -=, *=, /=, %=.
        int c = 5;
        c += 3;
        System.out.println(c);

        // Bitwise &, |, ^, ~, <<, >>.
        int d = 3;
        int e = 5;
        System.out.println(d & e);

        // Ternary condition ? valueIfTrue : valueIfFalse.
        int a = 10;
        int b = 20;
        int max = (a > b) ? a : b;
        System.out.println(max);



        // implicit Type casting
        int i = 100;
        double j = i;
        System.out.println(d);

        // explicit Type casting
        double f = 3.99;
        int g = (int) f;
        System.out.println(g);
    }

    // public static void main(String[] args){
    // // Assignment =, +=, -=, *=, /=, %=.
    //     int c = 5;
    //     c += 3
    // }
}
