package model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class CartItem {
    private Integer cartId;
    private Integer customerId;
    private Integer productId;
    private Integer quantity;
    private Timestamp createdDate;
}