package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CartItem {
    private Integer cartId;
    private Integer customerId;
    private Integer productId;
    private Integer quantity;
    private Date createdDate;
}