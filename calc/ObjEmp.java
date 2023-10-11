package calc;

public class ObjEmp {
    
    private int real;
    private int complex;



    public ObjEmp(int value,int complex){
        this.real=value;
        this.complex=complex;
    }
    public ObjEmp(int value){
        this.real=value;
    }

    public int getReal(){
        return this.real;
    }
    public int getComplex(){
        return this.complex;
    }


}
