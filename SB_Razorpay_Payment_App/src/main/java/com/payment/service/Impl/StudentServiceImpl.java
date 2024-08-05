package com.payment.service.Impl;

import com.payment.dto.StudentOrder;
import com.payment.repo.StudentOrderRepo;
import com.payment.service.StudentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentOrderRepo studentOrderRepo;

    @Value("${razorpay.key.id}")
    private String razorPayKey;

    @Value("${razorpay.key.secret}")
    private String razorPaySecret;

    private RazorpayClient client;

    @Override
    public StudentOrder createOrder(StudentOrder studentOrder) throws RazorpayException {
        JSONObject orderReq=new JSONObject();
        orderReq.put("amount",studentOrder.getAmount()*100);
        orderReq.put("currency","INR");
        orderReq.put("receipt",studentOrder.getEmail());

        this.client=new RazorpayClient(razorPayKey,razorPaySecret);

        //create order in razorpay
        Order razorPayOrder=client.orders.create(orderReq);
        //System.out.println(razorPayOrder);
        studentOrder.setRazorpayOrderId(razorPayOrder.get("id"));
        studentOrder.setOrderStatus(razorPayOrder.get("status"));
        studentOrderRepo.save(studentOrder);
        return studentOrder;
    }
    @Override
    public void updateOrder(Map<String,String> responsePayload){
        String razorPayOrderId = responsePayload.get("razorpay_order_id");
        StudentOrder order=studentOrderRepo.findByRazorpayOrderId(razorPayOrderId);
        order.setOrderStatus("Payment_Complete");
        StudentOrder updateOrder = studentOrderRepo.save(order);
    }

}
