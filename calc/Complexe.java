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
    @Override
    public ObjEmp sub(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "calc.Complexe" -> {
                    this.real -= ((Complexe) oe).real;
                    this.complex -= ((Complexe) oe).complex;
                    return this;
                }
                case "calc.Nombre" -> {
                    this.real -= ((Nombre) oe).value;
                    return this;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp mul(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "calc.Complexe" -> {
                    int tmpReal = this.real * ((Complexe) oe).real;
                    int tmpImag = this.real * ((Complexe) oe).complex; 
                    this.real = tmpReal - this.complex * ((Complexe) oe).complex;
                    this.complex= tmpImag + this.complex * ((Complexe) oe).real;
                    return this;
                }
                case "calc.Nombre" -> {
                    this.real *= ((Nombre) oe).value;
                    this.complex *= ((Nombre) oe).value;
                    return this;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp div(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                
                case "calc.Nombre" -> {
                    if (((Nombre) oe).value != 0) {
                        this.real /= ((Nombre) oe).value;
                        this.complex /= ((Nombre) oe).value;
                        return this;
                    }
                    throw new Exception();
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }
    public String toString() {
        return this.real + " " +this.complex +"i";
    }
}
