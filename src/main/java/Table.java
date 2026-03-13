import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Table {
    private final List<String> columnNames;
    private final Map<Long, Row> rows;
    private Long id = 1L;

    public Table(List<String> columnNames) {
        columnNames = new ArrayList<>(columnNames);
        columnNames.addFirst("id");
        this.columnNames = columnNames;
        rows = new TreeMap<>();
    }

    public void addRow(List<String> values) {
        if (values.size() + 1 != columnNames.size()) {
            throw new IllegalArgumentException("테이블 컬럼 수와 일치하지 않음");
        }

        values = new ArrayList<>(values);
        values.addFirst(String.valueOf(id));
        rows.put(id++, new Row(values));
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
