package garden;
import java.util.Scanner;

public class Main {
    public static void main (String [] args){
        GardenManager manager = new GardenManager();
        Scanner scanner = new Scanner(System.in);

        int plantIdCounter = 1; // Счетчик для генерации уникальных ID (чтобы не писать их вручную)
        System.out.println("Дневник садовода");

        while (true) {
            System.out.println("\n    ГЛАВНОЕ МЕНЮ   ");
            System.out.println("1. Добавить новую грядку");
            System.out.println("2. Посадить растение на грядку");
            System.out.println("3. Показать все грядки и растения");
            System.out.println("4. Сохранить данные в JSON");
            System.out.println("5. Загрузить данные из JSON");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Введите название грядки: ");
                    String bedName = scanner.nextLine();
                    System.out.print("Введите площадь грядки: ");
                    double area = Double.parseDouble(scanner.nextLine()); // Превращаем текст в дробное число

                    GardenBed newBed = new GardenBed("bed_" + System.currentTimeMillis(), bedName, area);
                    manager.addBed(newBed);
                    break;

                case "2":
                    // Сначала проверим, есть ли вообще грядки
                    if (manager.getBeds().isEmpty()) {
                        System.out.println("Сначала создайте хотя бы одну грядку!");
                        break;
                    }

                    System.out.print("Введите название растения (например, 'Помидор'): ");
                    String plantName = scanner.nextLine();
                    System.out.print("Введите сорт (например, 'Бычье сердце'): ");
                    String variety = scanner.nextLine();

                    System.out.println("Выберите тип растения: 1 - Овощ, 2 - Фрукт/Ягода");
                    String type = scanner.nextLine();

                    Plant newPlant = null;
                    if (type.equals("1")) {
                        System.out.print("Введите количество дней до созревания: ");
                        int days = Integer.parseInt(scanner.nextLine());
                        newPlant = new VegetablePlant(String.valueOf(plantIdCounter++), plantName, variety, days);
                    } else {
                        System.out.print("Введите тип куста/дерева: ");
                        String treeType = scanner.nextLine();
                        newPlant = new FruitPlant(treeType, String.valueOf(plantIdCounter++), plantName, variety);
                    }

                    // Показываем список грядок, чтобы пользователь выбрал, куда сажать
                    System.out.println("Выберите грядку для посадки:");
                    for (int i = 0; i < manager.getBeds().size(); i++) {
                        System.out.println((i + 1) + ". " + manager.getBeds().get(i).getName());
                    }
                    int bedIndex = Integer.parseInt(scanner.nextLine()) - 1;

                    if (bedIndex >= 0 && bedIndex < manager.getBeds().size()) {
                        manager.getBeds().get(bedIndex).addPlant(newPlant);
                        System.out.println("Растение успешно посажено!");
                    } else {
                        System.out.println("Неверный номер грядки.");
                    }
                    break;

                case "3":
                    System.out.println("\n--- ВАШ САД ---");
                    if (manager.getBeds().isEmpty()) {
                        System.out.println("Пока нет ни одной грядки.");
                    } else {
                        for (GardenBed bed : manager.getBeds()) {
                            System.out.println(bed.toString());
                            for (Plant p : bed.getPlants()) {
                                System.out.println("   -> " + p.toString());
                            }
                        }
                    }
                    break;

                case "4":
                    manager.saveToJson("garden_data.json");
                    break;

                case "5":
                    manager.loadFromJson("garden_data.json");
                    break;

                case "0":
                    System.out.println("До свидания! Хорошего урожая!");
                    scanner.close();
                    return; // Выходим из программы

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");


            }

        }

    }
}
