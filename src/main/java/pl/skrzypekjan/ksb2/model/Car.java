package pl.skrzypekjan.ksb2.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car{

    private long id;
    @NotNull
    @Size(min = 2)
    private String mark;
    @NotNull
    @Size(min = 2)
    private String model;
    @NotNull
    private Color color;

    public Car(long id, String mark, String model, Color color) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public Car() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                '}';
    }
}
