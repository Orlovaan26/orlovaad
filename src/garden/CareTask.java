package garden;

public class CareTask {
    private String id;
    private String description;
    private String date;
    private Plant plant;

    public CareTask(String id, String description, String date, Plant plant) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.plant = plant;
    }

    public String getId () {return id;}
    public String getDescription () {return description;}
    public void setDescription (String description) {this.description = description;}
    public String getDate () {return date;}
    public void setDate (String date) {this.date = date;}
    public Plant getPlant () {return plant;}

    @Override
    public String toString(){
        return "[" + date  + "]" + description  + " для " + plant.getName();
    }



}
