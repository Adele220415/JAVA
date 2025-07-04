class Main {
        public static void main(String[] args){
            printSessionTitle("NEW LINE");
            printThis("Hello world. Welcome to java programming!");

            printSessionTitle("TABS");
            printThis("Name:\tJohn Doe\n Age:\t 25\nLocation:\tUSA");
            
            printSessionTitle("DOUBLE QUOTES");
            printThis("She said, \"Hello world\" with a smile.");

            // Back slash
            // printSessionTitle("BACKSLASH");


            // Backspace
            printSessionTitle("BACKSPACE");
            printThis("Hello world!\b\b\b\b\b\bJava!");

            // FORMAT PRINTING
            
        }

        static void printThis(String message){
            System.out.println(message);
        }

         static void printSessionTitle(String title){
            System.out.println(title + "\n________________________");
        }
}
