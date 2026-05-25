import java.time.LocalDate;

public class Prescription{

    private String prescription_Name, prescription_Dose, prescription_Condition, prescription_Frequency;
    private LocalDate prescription_Date;
    private int prescription_ID;

    //setters
    public void setPrescriptionName(String prescName){
        this.prescription_Name = prescName;
    }

    public void setPrescriptionDose(String prescDose){
        this.prescription_Dose = prescDose;
    }

    public void setPrescriptionCondition(String prescCondition){
        this.prescription_Condition = prescCondition;
    }

    public void setPrescriptionFrequency(String prescFrequency){
        this.prescription_Frequency = prescFrequency;
    }

    public void setPrescriptionDate(LocalDate date){
        this.prescription_Date = date;
    }

    public void setPrescriptionID(int prescID){
        this.prescription_ID = prescID;
    }

    //getters
    public String getPrescriptionName(){
        return prescription_Name;
    }

    public String getPrescriptionDose(){
        return prescription_Dose;
    }

    public String getPrescriptionCondition(){
        return prescription_Condition;
    }

    public String getPrescriptionFrequency(){
        return prescription_Frequency;
    }

    public LocalDate getPrescriptionDate(){
        return prescription_Date;
    }

    public int getPrescriptionID(){
        return prescription_ID;
    }
}