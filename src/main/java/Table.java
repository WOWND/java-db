import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Table {
    private final List<String> columnNames;
    private final Map<Long, Row> rows;

    public Table(List<String> columnNames) {
        this.columnNames = columnNames;
        rows = new TreeMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(" ", columnNames));
        for (Row row : rows.values()) {
            sb.append("\n").append(row);
        }
        return sb.toString();
    }
}
