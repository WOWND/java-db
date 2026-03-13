import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TableTest {
    private Table table;


    @Nested
    @DisplayName("삽입")
    class Insert{
        @Test
        @DisplayName("테이블 컬럼 수와 삽입 데이터 컬럼 수가 일치하지 않으면 예외가 발생해야함")
        void columnSize() {
            List<String> columnNames = List.of("name", "age");
            table = new Table(columnNames);

            List<String> values = List.of("val1", "val2", "val3");
            assertThatThrownBy(() -> table.addRow(values))
                    .isInstanceOf(Exception.class);
        }
    }

}