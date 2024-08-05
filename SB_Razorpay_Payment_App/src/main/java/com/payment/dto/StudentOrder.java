package com.payment.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_order")
public class StudentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String name;
    private String email;
    private String phno;
    private String course;
    private Integer amount;
    private String orderStatus;
    private String razorpayOrderId;

    @Override
    public String toString() {
        return "StudentOrder{" +
                "orderId=" + orderId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phno='" + phno + '\'' +
                ", course='" + course + '\'' +
                ", amount=" + amount +
                ", orderStatus='" + orderStatus + '\'' +
                ", razorpayOrderId='" + razorpayOrderId + '\'' +
                '}';
    }
}
