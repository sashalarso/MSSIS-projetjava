package calc;

public class Complexe implements ObjEmp {
    public int real;
    public int complex;

    public Complexe(int real,int complex){
        this.real=real;
        this.complex=complex;
    }

    @Override
    public ObjEmp add(ObjEmp oe) {
        
        try {
            switch (oe.getClass().getName()) {
                case "calc.Complexe" -> {
                    this.real += ((Complexe) oe).real;
                    this.complex += ((Complexe) oe).complex;
                    return this;
                }
                case "calc.Nombre" -> {
                    this.real += ((Nombre) oe).value;
                    return this;
                }
                
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }
    public String toString() {
        return this.real + " " +this.complex +"i";
    }
}
