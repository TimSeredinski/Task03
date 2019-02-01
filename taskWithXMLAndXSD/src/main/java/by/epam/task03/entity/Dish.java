package by.epam.task03.entity;

public class Dish {
    private int id;
    private String name;
    private String img;
    private String description;
    private String additionDesc;
    private String portion;
    private String additionPortion;
    private double price;

    public Dish() {
    }

    public Dish(int id, String name, String img, String description, String additionDesc, String portion, String additionPortion, double price) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.description = description;
        this.additionDesc = additionDesc;
        this.portion = portion;
        this.additionPortion = additionPortion;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionDesc() {
        return additionDesc;
    }

    public void setAdditionDesc(String additionDesc) {
        this.additionDesc = additionDesc;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getAdditionPortion() {
        return additionPortion;
    }

    public void setAdditionPortion(String additionPortion) {
        this.additionPortion = additionPortion;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish that = (Dish) o;
        if (Integer.compare(that.id, this.id) != 0) {
            return false;
        }
        if (!(that.name != null ? that.name.equals(this.name) : this.name == null)) {
            return false;
        }
        if (!(that.img != null ? that.img.equals(this.img) : this.img == null)) {
            return false;
        }
        if (!(that.description != null ? that.description.equals(this.description) : this.description == null)) {
            return false;
        }
        if (!(that.additionDesc != null ? that.additionDesc.equals(this.additionDesc) : this.additionDesc == null)) {
            return false;
        }
        if (!(that.additionPortion != null ? that.additionPortion.equals(this.additionPortion) : this.additionPortion == null)) {
            return false;
        }
        if (!(that.portion != null ? that.portion.equals(this.portion) : this.portion == null)) {
            return false;
        }
        if (Double.compare(that.price, this.price) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 829;
        result = prime * result + id;
        result = prime * result + name.length();
        result = prime * result + img.length();
        result = prime * result + description.length();
        result = prime * result + additionPortion.length();
        result = prime * result + portion.length();
        result = prime * result + (int) price;
        return result;
    }

    @Override
    public String toString() {
        return "Dish: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", portion='" + portion + '\'' +
                ", price=" + price;
    }
}
