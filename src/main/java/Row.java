import java.util.List;

public class Row {
    private final List<String> values;

    public Row(List<String> values) {
        this.values = values;
    }

    public boolean matches(int columnIndex, String operator, String conditionValue) {
        String value = values.get(columnIndex);

        // operator는 = 연산만 들어온다고 가정하고 우선 구현
        return value.equals(conditionValue);
    }

    @Override
    public String toString() {
        return String.join(" ", values);
    }
}
