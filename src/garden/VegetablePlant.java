package garden;

public class VegetablePlant extends Plant {
    private int daysToMature;

    public VegetablePlant (String id, String name, String variety, int daysToMature) {
        super(id, name, variety);
        this.daysToMature = daysToMature;
    }

    public int getDaysToMature () {return daysToMature; }
    public void setDaysToMature (int daysToMature) {this.daysToMature = daysToMature; }

    @Override
    public String getPlantType() {
        return "овощ";
    }
}
