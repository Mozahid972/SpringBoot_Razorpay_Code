package com.payment.controller;


import com.payment.dto.StudentOrder;
import com.payment.service.StudentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String init(){
        return "index";
    }

    @PostMapping(value = "/create-order", produces = "application/json")
    @ResponseBody
    public ResponseEntity<StudentOrder> createOrder(@RequestBody StudentOrder studentOrder) throws RazorpayException {
        StudentOrder createdorder = studentService.createOrder(studentOrder);
        return new ResponseEntity<>(createdorder, HttpStatus.CREATED);
    }

    @PostMapping("/payment-callback")
    public String handlePaymentCallback(@RequestParam Map<String, String> responsePayload) {
        //System.out.println(responsePayload);
        studentService.updateOrder(responsePayload);
        return "success";
    }
}
