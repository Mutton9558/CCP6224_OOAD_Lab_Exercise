import java.time.LocalDate;

public class Diagnosis {

    private String diagnosis_Name;
    private int diagnosis_ID;
    private LocalDate diagnosis_Date;

    //setters
    public void setDiagnosisName(String diagnosisName){
        this.diagnosis_Name = diagnosisName;
    }

    public void setDiagnosisDate(LocalDate date){
        this.diagnosis_Date = date;
    }

    public void setDiagnosisID(int diagnosisID){
        this.diagnosis_ID = diagnosisID;
    }

    //getters 
    public String getDiagnosisName(){
        return diagnosis_Name;
    }

    public LocalDate getDiagnosisDate(){
        return diagnosis_Date;
    }

    public int getDiagnosisID(){
        return diagnosis_ID;
    }
}
