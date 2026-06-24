import java.time.LocalDate;
import java.util.Random;

public class Prescription{

    private String prescription_Name, prescription_Dose, prescription_Condition, prescription_Frequency;
    private LocalDate prescription_Date, prescription_End;
    private int prescription_ID, prescription_Patient;
    private String prescription_Patient_Name;

//   public Prescription(){
//        this.prescription_ID = 0;
//        this.prescription_Name = "Copium";
//   }

//   public Prescription(String name, String dose, String condition, String frequency, int patient_ID, ){
//        Random r = new Random();
//        this.prescription_ID = r.nextInt(100);
//        this.prescription_Name = name;
//        this.prescription_Dose = dose;
//        this.prescription_Condition = condition;
//        this.prescription_Frequency = frequency;
//        this.prescription_Patient = patient_ID;
//   }

   public Prescription(int id, String name, String dose, String condition, String frequency, int patient_ID, LocalDate date, LocalDate end, String patient_name){
        this.prescription_ID = id;
        this.prescription_Name = name;
        this.prescription_Dose = dose;
        this.prescription_Condition = condition;
        this.prescription_Patient = patient_ID;
        this.prescription_Frequency = frequency;
        this.prescription_Patient_Name = patient_name;
        this.prescription_Date = date;
        this.prescription_End = end;
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

    public void setPrescriptionPatient(int prescPatient){
        this.prescription_Patient = prescPatient;
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
    
    public LocalDate getPrescriptionEnd(){
        return prescription_End;
    }

    public int getPrescriptionID(){
        return prescription_ID;
    }

    public int getPrescriptionPatient(){
        return prescription_Patient;
    }
}