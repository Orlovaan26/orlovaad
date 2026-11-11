package garden;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.*;
import java.lang.reflect.Type;

public class GardenManager {
    private List<GardenBed> beds;
    private List<CareTask> tasks;

    public GardenManager () {
        this.beds = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public void addBed (GardenBed bed) { // добавление новых грядок в список
        this.beds.add(bed);
        System.out.println("Грядка '" + bed.getName() + "' успешно добавлена!");
    }

    public List<GardenBed> getBeds() { // возвращает список всех грядок
        return beds;
    }

    public void addTask(CareTask task) {
        this.tasks.add(task);
        System.out.println("Задача '" + task.getDescription() + "' спешно добавлена!");
    }

    public List<CareTask> getTasks(){ // возращает список всех задач
        return tasks;
    }

    public List<Plant> getAllPlants() {
        List<Plant> allPlants = new ArrayList<>();
        for (GardenBed bed : beds) {
            allPlants.addAll(bed.getPlants()); // берем растения с каждой грядки и добавлем в общий список
        }
        return allPlants;
    }

    // Методы для JSON

    // Сохранение всего состояния в JSON
    public void saveToJson(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Красивый формат (с отступами)
        try (FileWriter writer = new FileWriter(filePath)) {
            // Сохраняем и грядки, и задачи в один файл
            gson.toJson(this, writer);
            System.out.println("Все данные успешно сохранены в JSON: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения JSON: " + e.getMessage());
        }
    }

    // Загрузка состояния из JSON
    public void loadFromJson(String filePath) {
        // Создаем кастомный Gson с адаптером для абстрактного класса Plant
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Plant.class, new JsonDeserializer<Plant>() {
                    @Override
                    public Plant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        JsonObject jsonObject = json.getAsJsonObject();
                        // Смотрим, какой тип растения записан в JSON (Овощ или Фрукт)
                        String plantType = jsonObject.get("plantType").getAsString();

                        // В зависимости от типа, создаем нужный класс
                        if (plantType.equals("Овощ")) {
                            return context.deserialize(json, VegetablePlant.class);
                        } else {
                            return context.deserialize(json, FruitPlant.class);
                        }
                    }
                })
                .create();

        try (FileReader reader = new FileReader(filePath)) {
            GardenManager loadedManager = gson.fromJson(reader, GardenManager.class);
            if (loadedManager != null) {
                this.beds = loadedManager.getBeds();
                this.tasks = loadedManager.getTasks();
                System.out.println("Данные успешно загружены из JSON!");
            }
        } catch (IOException e) {
            System.out.println("Файл не найден или ошибка чтения: " + e.getMessage());
        }
    }
}
