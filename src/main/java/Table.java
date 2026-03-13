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

    public String findRow(String columnName, String operator, String conditionValue) {
        int columnIndex = columnNames.indexOf(columnName);
        if (columnIndex < 0) {
            throw new IllegalArgumentException("존재하지 않는 컬럼명");
        }

        List<Row> filtered = rows.values().stream()
                .filter(row -> row.matches(columnIndex, operator, conditionValue))
                .toList();

        return formatTable(columnNames,filtered);
    }

    private String formatTable(List<String> columnNames, Iterable<Row> rows) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(" ", columnNames));
        for (Row row : rows) {
            sb.append("\n").append(row);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return formatTable(columnNames, rows.values());
    }
}
