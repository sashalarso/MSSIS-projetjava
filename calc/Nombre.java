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
    public String toString() {
        return String.valueOf(value);
    }
}
