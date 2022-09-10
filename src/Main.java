import java.util.Scanner;

public class Main {
    static String[] bank = WordBank.words;
    public static void main(String[] args) {
        System.out.println("Hello world!");
        gameStart();
    }
    public static void gameStart() {
    computerChooses();
    };
    public static void computerChooses() {
        int index = (int) (Math.random() * 1000);
        String computerChoice = bank[index];
        display();
    }

    public static void display() {
//        String[] d = {"[]========\n","[]========\n","[]========\n","[]========\n","[]========\n","[]========\n","[]========\n","[]========\n","[]========\n"};
        String[] d1 = {"[]========?\n","[]........|\n","[]........O\n","[]........|\n","[]......./|\n","[]......./|\\\n","[]........|\n","[]......./ \\\n","[]\n","[]\n","[]========\n"};
//        System.out.println(d[0]+d[1]+d[2]+d[3]+d[4]+d[5]+d[6]+d[7]+d[8]);
        System.out.println(d1[0]+d1[1]+d1[2]+d1[3]+d1[4]+d1[5]+d1[6]+d1[7]+d1[8]);
    }

}