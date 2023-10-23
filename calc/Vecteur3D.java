package calc;

import java.util.Arrays;

public class Vecteur3D implements ObjEmp{
    public int x;
    public int y;
    public int z;

    public Vecteur3D(int x,int y,int z){
        this.x=x;
        this.y=y;
        this.z=z;
        }
    @Override
    public ObjEmp add(ObjEmp oe) {
        try {
            switch (oe.getClass().getName()) {
                case "calc.Vecteur3D" -> {
                    this.x += ((Vecteur3D) oe).x;
                    this.y += ((Vecteur3D) oe).y;
                    this.z += ((Vecteur3D) oe).z;
                    return this;
                }
                case "calc.Nombre" -> {
                    this.x += ((Nombre) oe).value;
                    this.y += ((Nombre) oe).value;
                    this.z += ((Nombre) oe).value;
                    return this;
                }
                default -> throw new Exception();
            }
        } catch (Exception e) {
            return null;
        }
    }
        
      
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z +")";
    }

}
