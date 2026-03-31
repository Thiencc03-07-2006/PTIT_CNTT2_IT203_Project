package model;

import lombok.Getter;
import lombok.Setter;
import presentation.ui.CategoryUI;
import util.GetColor;
import util.InputMethod;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;


@Getter
@Setter
public class Product implements Serializable {
    private Integer productId;
    private String productName;
    private Double rootPrice;
    private Integer discount;
    private Integer inventory;
    private String color;
    private String description;
    private Integer cateId;
    private ProductStatus status;
    private Date createdDate;
    private String brand;
    private String storage;

    public Product() {
    }

    public Double getFinalPrice() {
        if (rootPrice == null || discount == null) return 0.0;
        return rootPrice - (rootPrice * discount / 100);
    }

    public void updateStatus() {
        if (inventory != null && inventory == 0) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
    }

    public void displayData() {
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-3d | %-28s | %-3s | %-13s | %-3d | %-18s | %-8s | %-3d | %-18s |\n", productId, productName, discount + "%", format.format(getFinalPrice()) + "VNĐ", inventory, storage, color, cateId, status);
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
    }

}