package calc;

public class PileRPL{

    private int nbObj=0;
    final private int NBMAXOBJ=5;
    private int nbmaxobj;
    private ObjEmp[] tab;
  
    public PileRPL(int nbObj){
        tab = new ObjEmp[nbObj];
        this.nbmaxobj=nbObj;
    }

    public PileRPL(){
        tab=new ObjEmp[NBMAXOBJ];
        this.nbmaxobj=NBMAXOBJ;
    }
    public void push(ObjEmp objEmp){
        if (this.isFull()){
            System.out.println("Stack is full");
        }
        else{
            this.tab[nbObj]=objEmp;
            this.nbObj=nbObj+1;
                       
        }
        
    }
    public int getnbObjEMp(){
        return this.nbObj;
    }

    public ObjEmp pop(){
        
        if(this.isEmpty()){
            System.out.println("Stack is empty");
            return null;
            
        }
        else{
            
            this.nbObj=this.nbObj-1;
            return tab[this.nbObj];
        }
    }

    private Boolean isFull(){
        return this.nbObj>=this.nbmaxobj;
    }
    private Boolean isEmpty(){
        return this.nbObj==0;
    }

    public void add(){
        ObjEmp newObjEmp;
        if (this.nbObj<2){
            System.out.println("Not enough objects");
        }
        else{
            ObjEmp obj1 = this.pop();
            ObjEmp obj2 = this.pop();
        
            newObjEmp=new ObjEmp(obj1.getReal()+obj2.getReal(),obj1.getComplex()+obj2.getComplex());
       
            this.push(newObjEmp);  
        }
              
        
    }
    public String toString(){
        String line ="";
        for (int i=0;i<this.tab.length;i=i+1){
            if (i<this.nbObj){
                line+=i+" | " + tab[i].getReal() + " + " + tab[i].getComplex() + "i" + " |  \n";
            }
            else {
                line+=i+" | "  + "         |  \n";
            }
            
        }
        line += " -----------";
        return line;
    }
    
}