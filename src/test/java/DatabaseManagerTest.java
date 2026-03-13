import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DatabaseManagerTest {
    private DatabaseManager dbManager;

    @BeforeEach
    void init() {
        dbManager = new DatabaseManager();
    }



    @AfterEach
    void clear() throws IOException {
        Files.deleteIfExists(Path.of("data.bin"));
        Files.deleteIfExists(Path.of("data.meta"));
    }
}