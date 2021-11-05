import java.util.List;
public class rules {
    public static void availableMoves(String[] moves) {
        System.out.println("Возможные ходы:");
        int i = 1;
        for (String move : moves) {
            System.out.println(i+" - "+move);
            i++;
        }
        System.out.println("0 - Exit\n? - Help");
    }
    public static String checkInput(int choise, String[] moves) {
        String userMove =null;
        if (choise == 0) return "";
        if (choise > 0 && choise<=moves.length)
        {
            userMove = moves[(int)choise-1];
        }
        else
        {
            System.out.println("Некорректный ввод. Возврат в меню...");
            return "";
        }
        return userMove;
    }
    public static void showMoves(String userMove, String compMove) {
        System.out.println("Ваш ход: "+userMove);
        System.out.println("Ход компьютера: " + compMove);
    }
    public static String showWinner(String[] moves, String userMove, String compMove) {
        List<String> list = game.flipArr(moves,compMove);
        if (list.indexOf(userMove)> list.indexOf(compMove)) {
            return "Вы победили";
        } else if (list.indexOf(userMove)<list.indexOf(compMove)) {
            return "Компьютер победил";
        } else return "Ничья";
    }
}

