package com.payment.service;

import com.payment.dto.StudentOrder;
import com.razorpay.RazorpayException;

import java.util.Map;

public interface StudentService {

    //create order
    StudentOrder createOrder(StudentOrder studentOrder) throws RazorpayException;
    //after payment update in db using callback
    void updateOrder(Map<String, String> responsePayload);
}
