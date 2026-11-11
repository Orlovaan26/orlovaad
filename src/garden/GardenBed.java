package garden;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class GardenBed implements IExportable {
    private String id;
    private String name;
    private double area;
    private List<Plant> plants;

    public GardenBed (String id, String name, double area) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.plants = new ArrayList<>(); //при создании грядки мы должны создать пустой список, иначе он будет null и мы не сможем добавить растения
    }

    public void addPlant(Plant new_plant){ // метод добавления растений (Plant (тип данных, класс - в этот метод можно передать растение) new_plant - новая внутренняя переменная)
        this.plants.add(new_plant); // .add() — это встроенная команда списка (ArrayList)
    }

    public String getId() {return id;}
    public void setId (String id) {this.id = id; }
    public String getName() {return name;}
    public void setName (String name) {this.name = name; }
    public double getArea() {return area;}
    public void setArea (double area) {this.area = area; }
    public List<Plant> getPlants() {return plants;}

    @Override
    public String toString() {
        return name + " (Площадь: " + area + " кв.м., Растений: " + plants.size() +")";
    }

    @Override
    public void exportToText (String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Грядка: " + this.name + "\n");
            writer.write("Площадь: " + this.area + "\n");
            writer.write("Растения на грядке: \n");
            for (Plant p: plants){
                writer.write("- " + p.getPlantType() + ": " + p.getName() + "(" + p.getVariety() + ")\n");
            }
            System.out.println("Данные успешно сохранены в " + filePath);
        }
        catch (IOException e){
            System.out.println ("Ошибка при сохранении: " +  e.getMessage());
        }
    }
 }
