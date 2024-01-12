package lk.ijse.dogCareClinic.dto;

import lk.ijse.dogCareClinic.model.PaymentModel;

public class PaymentDto extends PaymentModel {

        private String paymentId;
        private String Amount;
        private String Date;

        public PaymentDto(String paymentId, String Amount, String Date) {
            this. paymentId =  paymentId;
            this.Amount = Amount;
            this.Date = Date;
        }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
