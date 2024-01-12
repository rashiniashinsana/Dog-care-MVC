package lk.ijse.dogCareClinic.dto.tm;

public class TreatmentTm {
        private String TreatmentID;
        private String Date;
        private String Medication;
        private String Diagnosis;
        private String DogID;
        private String PaymentID;


        public TreatmentTm(String TreatmentID, String Date, String Medication, String Diagnosis, String DogID, String PaymentID) {
            this.TreatmentID = TreatmentID;
            this.Date = Date;
            this.Medication = Medication;
            this.Diagnosis = Diagnosis;
            this.DogID = DogID;
            this.PaymentID = PaymentID;
        }

    public String getTreatmentID() {
        return TreatmentID;
    }

    public void setTreatmentID(String treatmentID) {
        TreatmentID = treatmentID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMedication() {
        return Medication;
    }

    public void setMedication(String medication) {
        Medication = medication;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        Diagnosis = diagnosis;
    }

    public String getDogID() {
        return DogID;
    }

    public void setDogID(String dogID) {
        DogID = dogID;
    }

    public String getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(String paymentID) {
        PaymentID = paymentID;
    }
}
