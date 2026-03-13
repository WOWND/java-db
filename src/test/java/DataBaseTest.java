import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

            assertThat(db.findAll("users")).isEqualTo("id name age");
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
            assertThatThrownBy(() -> db.findAll("users"))
                    .isInstanceOf(Exception.class);
        }
    }

    @Nested
    @DisplayName("데이터 삽입")
    class InsertData {
        @BeforeEach
        void initTable() {
            List<String> columnNames = List.of("age", "name");
            db.create("users", columnNames);
        }

        @Test
        @DisplayName("테이블에 새로운 row를 추가")
        void insert() {
            db.insert("users", List.of("25", "gabi"));
            db.insert("users", List.of("27", "hana"));
            db.insert("users", List.of("29", "wanja"));
            db.insert("users", List.of("30", "jon"));

            assertThat(db.findAll("users")).isEqualToIgnoringWhitespace(
                    "id age name\n"
                            + "1 25 gabi\n"
                            + "2 27 hana\n"
                            + "3 29 wanja\n"
                            + "4 30 jon");
        }
    }

    @Nested
    @DisplayName("조회")
    class SelectWhere {
        @BeforeEach
        void initTable() {
            List<String> columnNames = List.of("age", "name");
            db.create("users", columnNames);

            db.insert("users", List.of("25", "gabi"));
            db.insert("users", List.of("25", "gabiClone"));
            db.insert("users", List.of("27", "hana"));
            db.insert("users", List.of("29", "wanja"));
            db.insert("users", List.of("30", "jon"));
        }

        @Test
        @DisplayName("조건 조회")
        void selectWhere() {
            assertThat(db.findBy("users", "age", "=", "25"))
                    .isEqualToIgnoringWhitespace(
                            "id age name\n"
                                    + "1 25 gabi\n"
                                    + "2 25 gabiClone");

            assertThat(db.findBy("users", "name", "=", "wanja"))
                    .isEqualToIgnoringWhitespace(
                            "id age name\n"
                                    + "4 29 wanja");
        }
    }
}