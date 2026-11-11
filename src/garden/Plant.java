package garden;

public abstract class Plant {
    private String id;
    private String name;
    private String variety;
    private String plantType;

    public Plant (String id, String name, String variety){ // констрауктор с параметром
        this.id = id; // возьми id и положи в поле класса this.id
        this.name = name;
        this.variety = variety;
        this.plantType = getPlantType();
    }

    public String getId() {return id; } // string потому что геттереы принимают значение в строку? Зачем мы возвращаем id? Почему  после метода гет скобки ( пустые)?
    public void setId(String id) {this.id=id; } // почуму сет ничечего не возвращает? почему после метода сет (тип переменной и переменная) ?
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getVariety() { return variety; }
    public void setVariety(String variety) { this.variety = variety; }

    public abstract String getPlantType();

    @Override //Override - переопределение абстрактного метода, но почему с @
    public String toString() {
        return "[" + getPlantType() + "]" + name + "(сорт: " +  variety + ")";
    }
}
