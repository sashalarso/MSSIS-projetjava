package calc;

public class Nombre implements ObjEmp {
    public int value;

    public Nombre(int value){
        this.value=value;
    }
    @Override
    public ObjEmp add(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "calc.Nombre" -> {
                    this.value += ((Nombre) oe).value;
                    return this;
                }
                case "calc.Vecteur3D" -> {
                    ((Vecteur3D) oe).x+=this.value;
                    ((Vecteur3D) oe).y+=this.value;
                    ((Vecteur3D) oe).z+=this.value;
                    return oe;
                }
                case "calc.Complexe" -> {
                    ((Complexe) oe).real += this.value;
                    return oe;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }
    @Override
    public ObjEmp sub(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "calc.Nombre" -> {
                    this.value -= ((Nombre) oe).value;
                    return this;
                }
                case "calc.Vecteur3D" -> {
                    
                        ((Vecteur3D) oe).x -= this.value;
                        ((Vecteur3D) oe).y -= this.value;
                        ((Vecteur3D) oe).z -= this.value;
                    
                    return oe;
                }
                case "calc.Complexe" -> {
                    ((Complexe) oe).real -= this.value;
                    return oe;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {return null;}
    }

    @Override
    public ObjEmp mul(ObjEmp oe) {
        
        try {
            switch (oe.getClass().getName()) {
                case "calc.Nombre" -> {
                    this.value *= ((Nombre) oe).value;
                    return this;
                }
                case "calc.Vecteur3D" -> {
                   
                        ((Vecteur3D) oe).x *= this.value;
                        ((Vecteur3D) oe).y *= this.value;
                        ((Vecteur3D) oe).z *= this.value;
                    
                    return oe;
                }
                case "calc.Complexe" -> {
                    
                    ((Complexe) oe).real *= this.value;
                    ((Complexe) oe).complex *= this.value;
                    return oe;
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
                        this.value /= ((Nombre) oe).value;
                        return this;
                    }
                }
                case "calc.Complexe" -> {
                    if (!(((Complexe) oe).real == 0 && ((Complexe) oe).complex == 0))
                        return new Complexe(this.value, 0).div(oe);
                }
                default -> throw new Exception();
            }
            throw new Exception();
        } catch (Exception e) {return null;}
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
