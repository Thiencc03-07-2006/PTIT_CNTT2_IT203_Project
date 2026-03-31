package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Double totalMoney;
    private String color;
    private String storage;

    public void displayData() {
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-3d | %-28s | %-25s | %-5d | %-8s | %-18s | %-29s |\n",
                productId,
                productName,
                format.format(price) + " VNĐ",
                quantity,
                color,
                storage,
                format.format(getTotalMoney()) + " VNĐ"
        );
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}