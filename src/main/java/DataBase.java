import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class DataBase {
    private final Map<String, Table> tables;

    public DataBase() {
        this(new HashMap<>());
    }

    public DataBase(Map<String, Table> tables) {
        this.tables = tables;
    }

    public void create(String tableName, List<String> columnNames) {
        if (hasTable(tableName)) {
            throw new IllegalStateException("동일한 이름의 테이블이 존재합니다.");
        }
        tables.put(tableName, new Table(columnNames));
    }

    private boolean hasTable(String tableName) {
        return tables.containsKey(tableName);
    }

    public String selectAll(String tableName) {
        if (!hasTable(tableName)) {
            throw new IllegalStateException("테이블이 존재하지 않음");
        }
        return tables.get(tableName).toString();
    }

}
