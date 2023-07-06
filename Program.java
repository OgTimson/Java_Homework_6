import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static List<NoteBook> notebooks = new ArrayList<>();

    public static void main(String[] args) {
        populateNotebooks();

        Scanner scanner = new Scanner(System.in);
        boolean continueSearching = true;

        while (continueSearching) {
            System.out.println();
            System.out.println("Выберите параметр:");
            System.out.println("1 - Объем ОЗУ");
            System.out.println("2 - Объем SSD");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.print("Введите номер параметра: ");
            int parameter = scanner.nextInt();

            Map<String, String> filters = new HashMap<>();

            switch (parameter) {
                case 1:
                    System.out.println();
                    System.out.println("Выберите объем ОЗУ:");
                    System.out.println("1 - 4 Гб");
                    System.out.println("2 - 8 Гб");
                    System.out.println("3 - 16 Гб");
                    System.out.println("4 - 32 Гб");
                    System.out.print("Введите номер ОЗУ: ");
                    int ramChoice = scanner.nextInt();
                    filters.put("ram", getRamValue(ramChoice));
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Выберите объем SSD:");
                    System.out.println("1 - 128 Гб");
                    System.out.println("2 - 256 Гб");
                    System.out.println("3 - 512 Гб");
                    System.out.println("4 - 1024 Гб");
                    System.out.print("Введите номер SSD: ");
                    int ssdChoice = scanner.nextInt();
                    filters.put("ssd", getSsdValue(ssdChoice));
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Выберите операционную систему:");
                    System.out.println("1 - Mac OS");
                    System.out.println("2 - Linux");
                    System.out.println("3 - Windows");
                    System.out.print("Введите номер операционной системы: ");
                    int osChoice = scanner.nextInt();
                    filters.put("os", getOsValue(osChoice));
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Выберите цвет:");
                    System.out.println("1 - белый");
                    System.out.println("2 - серебристый");
                    System.out.println("3 - синий");
                    System.out.println("4 - золотистый");
                    System.out.println("5 - черный");
                    System.out.print("Введите номер цвета: ");
                    int colorChoice = scanner.nextInt();
                    filters.put("color", getColorValue(colorChoice));
                    break;
                default:
                    System.out.println();
                    System.out.println("Неверный выбор параметра. Повторите!");
                    System.out.println();
                    continue;
            }

            List<NoteBook> filteredNotebooks = filterNotebooks(filters);

            if (filteredNotebooks.isEmpty()) {
                System.out.println();
                System.out.println("В наличии нет подходящих ноутбуков!");
                System.out.println();
            } else {
                for (NoteBook notebook : filteredNotebooks) {
                    System.out.println();
                    System.out.println("Ноутбук: " + notebook.getName());
                    System.out.println("Объем ОЗУ: " + notebook.getRam());
                    System.out.println("Объем SSD: " + notebook.getSsd());
                    System.out.println("Операционная система: " + notebook.getOs());
                    System.out.println("Цвет: " + notebook.getColor());
                    System.out.println();
                }
            }

            System.out.println("Продолжить поиск?");
            System.out.println("1 - да");
            System.out.println("2 - нет");
            System.out.print("Введите номер операции: ");
            int choice = scanner.nextInt();

            if (choice == 2) {
                continueSearching = false;
            }
        }

        scanner.close();
    }

    private static void populateNotebooks() {
        NoteBook notebook1 = new NoteBook("Apple", "4 Гб", "128 Гб", "Mac OS", "белый");
        NoteBook notebook2 = new NoteBook("Aser", "8 Гб", "256 Гб", "Linux", "серебристый");
        NoteBook notebook3 = new NoteBook("HP", "8 Гб", "256 Гб", "Windows", "синий");
        NoteBook notebook4 = new NoteBook("Asus", "8 Гб", "512 Гб", "Windows", "золотистый");
        NoteBook notebook5 = new NoteBook("Dell", "16 Гб", "1024 Гб", "Windows", "черный");
        NoteBook notebook6 = new NoteBook("Lenovo", "32 Гб", "1024 Гб", "Windows", "черный");

        notebooks.add(notebook1);
        notebooks.add(notebook2);
        notebooks.add(notebook3);
        notebooks.add(notebook4);
        notebooks.add(notebook5);
        notebooks.add(notebook6);
    }

    private static List<NoteBook> filterNotebooks(Map<String, String> filters) {
        List<NoteBook> filteredNotebooks = new ArrayList<>();

        for (NoteBook notebook : notebooks) {
            boolean matchesFilter = true;

            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                switch (key) {
                    case "ram":
                        if (!notebook.getRam().equals(value)) {
                            matchesFilter = false;
                        }
                        break;
                    case "ssd":
                        if (!notebook.getSsd().equals(value)) {
                            matchesFilter = false;
                        }
                        break;
                    case "os":
                        if (!notebook.getOs().equals(value)) {
                            matchesFilter = false;
                        }
                        break;
                    case "color":
                        if (!notebook.getColor().equals(value)) {
                            matchesFilter = false;
                        }
                        break;
                }

                if (!matchesFilter) {
                    break;
                }
            }

            if (matchesFilter) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }

    private static String getRamValue(int choice) {
        switch (choice) {
            case 1:
                return "4 Гб";
            case 2:
                return "8 Гб";
            case 3:
                return "16 Гб";
            case 4:
                return "32 Гб";
            default:
                return null;
        }
    }

    private static String getSsdValue(int choice) {
        switch (choice) {
            case 1:
                return "128 Гб";
            case 2:
                return "256 Гб";
            case 3:
                return "512 Гб";
            case 4:
                return "1024 Гб";
            default:
                return null;
        }
    }

    private static String getOsValue(int choice) {
        switch (choice) {
            case 1:
                return "Mac OS";
            case 2:
                return "Linux";
            case 3:
                return "Windows";
            default:
                return null;
        }
    }

    private static String getColorValue(int choice) {
        switch (choice) {
            case 1:
                return "белый";
            case 2:
                return "серебристый";
            case 3:
                return "синий";
            case 4:
                return "золотистый";
            case 5:
                return "черный";
            default:
                return null;
        }
    }
}
