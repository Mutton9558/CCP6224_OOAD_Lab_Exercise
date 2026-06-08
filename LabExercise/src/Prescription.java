import java.time.LocalDate;
import java.util.Random;

public class Prescription{

    private String prescription_Name, prescription_Dose, prescription_Condition, prescription_Frequency;
    private LocalDate prescription_Date;
    private int prescription_ID;

   public Prescription(){
        this.prescription_ID = 0;
        this.prescription_Name = "Copium";
   }

   public Prescription(String name, String dose, String condition, String frequency){
        Random r = new Random();
        this.prescription_ID = r.nextInt(100);
        this.prescription_Name = name;
        this.prescription_Dose = dose;
        this.prescription_Condition = condition;
        this.prescription_Frequency = frequency;
   }

   public Prescription(String name, String dose, String condition, String frequency, LocalDate date){
        Random r = new Random();
        this.prescription_ID = r.nextInt(100);
        this.prescription_Name = name;
        this.prescription_Dose = dose;
        this.prescription_Condition = condition;
        this.prescription_Frequency = frequency;
   }

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