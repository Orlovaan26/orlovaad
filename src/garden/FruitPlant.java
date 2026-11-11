package garden;

public class FruitPlant extends Plant {
    private String treeType;

    public FruitPlant(String id, String name, String variety, String treeType) {
        super(id, name, variety);
        this.treeType = treeType;
    }

    public String getTreeType () {return treeType; }
    public void setTreeType (String treeType) {this.treeType = treeType; }

    @Override
    public String getPlantType() {
        return "фрукт";
    }
}
