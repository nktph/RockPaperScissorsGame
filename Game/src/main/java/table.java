import de.vandermeer.asciitable.*;
import java.util.Arrays;
import java.util.Vector;
public class table {
    public static void generateTable(String[] moves) {
        Vector<String> list = new Vector<String>(Arrays.asList(moves));
        list.add(0,"PC/USER");
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow(list);
        table.addRule();
        for(int i = 0;i<moves.length;i++) {
            list.clear();
            list.add(moves[i]);
            for (int j = 0; j < moves.length; j++) {
                list.add(rules.showWinner(moves, moves[j], moves[i]));
            }
            table.addRow(list);
            table.addRule();
        }
        System.out.println(table.render());
    }
}
