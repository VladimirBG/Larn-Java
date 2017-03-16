package dataSets;

public class ProductSet {
    private long id;
    private String tittle;
    private long count;
    private double price;

    public ProductSet(long id, String tittle, long count, double price) {
        this.id = id;
        this.tittle = tittle;
        this.count = count;
        this.price = price;
    }


    public long getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public long getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }
}

