package com.payment.repo;

import com.payment.dto.StudentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentOrderRepo extends JpaRepository<StudentOrder, Integer> {

    public StudentOrder findByRazorpayOrderId(String orderId);
}
