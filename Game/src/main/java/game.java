import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.security.SecureRandom;
import java.util.Set;
import java.util.HashSet;
public class game {
    public static boolean lengthIsCorrect(String[] moves) {
        return (moves.length>=3)&&(moves.length%2!=0);
    }
    public static void hasDuplicates(String[] moves) {
        Set set = new HashSet();
        for(int i=0; i<moves.length; i++) {
            if(!set.add(moves[i])) {
                System.out.println("Имеются дублирующиеся ходы. Проверьте правильность ввода и повторите попытку");
                System.exit(1);
            }
        }
    }
    public static void checkArr(String[] moves) {
        if(!lengthIsCorrect(moves)) {System.out.println("Некорректное количество возможных ходов. Число ходов должно " +
                "быть нечётным >=3. Проверьте ввод и повторите попытку");System.exit(1);}
        hasDuplicates(moves);
    }
    public static int findCompMoveIndex(String[] moves, String compMove) {
        return Arrays.asList(moves).indexOf(compMove);
    }
    public static List<String> flipArr(String[] moves, String compMove) {
        List<String> list = Arrays.asList(moves);
        Collections.rotate(list, (((moves.length - 1) / 2) - Arrays.asList(moves).indexOf(compMove)));
        return list;
    }
    public static int generateCompMove(int movesLength) {
        return new SecureRandom().nextInt(movesLength-1);
    }
    public static void showHMAC(String[] moves, String compMove, String key) {
        int compIndex = findCompMoveIndex(moves, compMove);
        String hmac = keygen.generateHMAC(key, compIndex);
        System.out.println("HMAC: "+(hmac));
    }
    public static int userInput(String[] moves) {
        Scanner inn = new Scanner(System.in);
        System.out.print("Введите ваш ход: ");
        String move = inn.nextLine();
        if(move.equals("0")) {
            System.out.println("Выход...");
            System.exit(0);
        }
        else if (move.equals("?")) {
            System.out.println("Помощь:");
            table.generateTable(moves);
            return 0;
        }
        try { return Integer.parseInt(move); } catch (Exception e) { return -1; }
    }
    public static String menu(String[] moves) {
        String move = "";
        do {
            rules.availableMoves(moves);
            int choise = userInput(moves);
            move = rules.checkInput(choise, moves);
        }while (move.isEmpty());
        return move;
    }
    public static void main(String[] args) {
        checkArr(args);
        String[] moves = args.clone();
        String key = keygen.generateKey();
        String compMove = moves[generateCompMove(args.length)];
        showHMAC(moves, compMove, key);
        String userMove = menu(moves);
        rules.showMoves(userMove, compMove);
        System.out.println(rules.showWinner(args,userMove,compMove));
        keygen.printKey(key);
    }
}