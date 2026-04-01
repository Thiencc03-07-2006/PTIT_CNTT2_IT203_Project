package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String password;
    private Gender gender;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private String address;
    private Role role = Role.CUSTOMER;
    private Boolean status = true;
    private Timestamp createdDate;


    public void displayData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
        System.out.printf("[ %-3d | %-28s | %-8s | %-13s | %-13s | %-28s | %-8s | %-13s ]\n", userId, userName, gender, simpleDateFormat.format(birthday), phoneNumber, email, status ? "on" : "off", simpleDateFormat.format(createdDate));
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━┛");
    }

}