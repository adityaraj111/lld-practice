package model;

import enums.PaymentStatus;

public class ParkingPaymentResponse {
    PaymentStatus paymentStatus;
    Double totalAmount;
    public ParkingPaymentResponse(PaymentStatus paymentStatus, Double totalAmount) {
        this.paymentStatus = paymentStatus;
        this.totalAmount = totalAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}
