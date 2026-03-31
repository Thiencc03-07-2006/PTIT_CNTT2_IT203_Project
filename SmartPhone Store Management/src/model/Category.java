package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class Category implements Serializable {
    private Integer cateId;
    private String cateName;
    private Boolean status = true;
    private Date createdDate;

    public Category() {
    }

    public Category(String cateName) {
        this.cateName = cateName;
        this.status = true;
        this.createdDate = new Date();
    }


    public void displayData() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-8d | %-38s | %-8s | %-18s |\n", cateId, cateName, status ? "Active" : "Inactive", format.format(createdDate));
        System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┛");
    }


}