import java.util.Map;
import java.util.Scanner;

public class DatabaseManager {
    private final DataBase db;
    private final FileManager fileManager;

    public DatabaseManager() {
        fileManager = new FileManager("data");
        //db = new DataBase(loadFile());
        db = new DataBase();
    }

//    public void run() {
//        System.out.println("프로그램을 시작합니다");
//        Scanner scanner = new Scanner(System.in);
//
//        Command command = CommandParser.parse(scanner.nextLine());
//
//        while (!command.getType().equals("EXIT")) {
//            try {
//                System.out.println(execute(command));
//            } catch (Exception e) {
//                System.out.println("ERROR: " + e.getMessage());
//            }
//            command = CommandParser.parse(scanner.nextLine());
//        }
//        saveFile();
//        System.out.println("프로그램을 종료합니다");
//    }


    public String execute(Command command) {
//        return switch (command.getType()) {
//            case "SET" -> db.set(command.getKey(), command.getValue());
//            case "GET" -> db.get(command.getKey());
//            case "DELETE" -> db.delete(command.getKey());
//            case "KEYS" -> db.getKeys();
//            default -> throw new IllegalArgumentException("잘못된 명령");
//        };
        return null;
    }

//    private Map<String, String> loadFile() {
//        return fileManager.loadAll();
//    }

//    public void saveFile() {
//        Map<String, String> data = db.getAlls();
//        fileManager.writeFile(data);
//    }

//    public static void main(String[] args) {
//        DatabaseManager databaseManager = new DatabaseManager();
//        databaseManager.run();
//    }
}
