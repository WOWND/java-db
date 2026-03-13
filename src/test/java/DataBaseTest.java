import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

class DataBaseTest {
    private DataBase db;

    @BeforeEach
    void init() {
        db = new DataBase();
    }

    @Nested
    @DisplayName("테이블 생성")
    class CreateTable {
        @Test
        @DisplayName("테이블을 생성하고 내용 전체 조회를 할 수 있다.")
        void createTableAndSelect() {
            List<String> columnNames = List.of("id", "name", "age");
            db.create("users", columnNames);

            assertThat(db.selectAll("users")).isEqualTo("id name age");
        }

        @Test
        @DisplayName("테이블 생성시 동일한 이름의 테이블이 존재하면 예외 발생")
        void validateDuplicateTableName() {
            db.create("users", List.of("id", "name"));

            assertThatThrownBy(() -> db.create("users", List.of("age", "height")))
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("존재하지 않는 테이블 조회 시도하면 예외 발생")
        void tableNotFound() {
            assertThatThrownBy(() -> db.selectAll("users"))
                    .isInstanceOf(Exception.class);
        }
    }

}