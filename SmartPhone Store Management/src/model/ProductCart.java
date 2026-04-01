package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCart implements Serializable {
    private Integer cartId;
    private Integer productId;
    private String productName;
    private String color;
    private String storage;
    private int quantity;
    private Double price;

    public Double getTotalMoney() {
        return price * quantity;
    }


    public void displayData(int no) {
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-3d | %-39s | %-13s | %-8d | %-13s | %-13s | %-20s |\n", no, productName, format.format(price) + "VNĐ", quantity, storage, color, format.format(getTotalMoney()) + "VNĐ");
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}