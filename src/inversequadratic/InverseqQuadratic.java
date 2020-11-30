package inversequadratic;


import util.Constant;

import javax.activation.CommandObject;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;

public class InverseqQuadratic  {
    private  String f;

    public InverseqQuadratic(String f) {
        this.f = f;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public  double f(double x){
        double r = Fun.fun(this.f, x);
        return r;
    }


    public  ArrayList<HashMap> cal(double a, double b, double c) throws Exception {
        int k = 0;
        double [] arg ={a,b,c};
        double[] val = {f(a),f(b),f(c)};
        double x = interp(val,arg,0);
        ArrayList<HashMap> arrayList = new ArrayList<>();
        while (Math.abs(b-a)> Constant.e){
            double [] ar ={a,b,c};
            double[] va = {f(a),f(b),f(c)};
            if(Double.isNaN(interp(va,ar,0))||Double.isInfinite(interp(va,ar,0))){
                break;
            }
            x = interp(va,ar,0);
            a=b;
            b=c;
            c=x;
            k = k+1;
            HashMap<String,Object> map = new HashMap<>();
            map.put("a",a);
            map.put("b",b);
            map.put("c",c);
            map.put("k",k);
            map.put("x",x);
            arrayList.add(map);

        }
        if(Double.isNaN(x)||Double.isInfinite(x)){
            throw new Exception("无法求根，请重新输入");
        }
        return arrayList;
    }
//    public static double calculate(double a,double b,double c){
//        int k = 0;
//
//        double [] arg ={a,b,c};
//        double[] val = {f(a),f(b),f(c)};
//        double x = interp(val,arg,0);
//        ArrayList<HashMap> arrayList = new ArrayList<>();
//        while (Math.abs(b-a)> Constant.e){
//            double [] ar ={a,b,c};
//            double[] va = {f(a),f(b),f(c)};
//            x = interp(va,ar,0);
//            a=b;
//            b=c;
//            c=x;
//            k = k+1;
//            HashMap<String,Object> map = new HashMap<>();
//            map.put("a",a);
//            map.put("b",b);
//            map.put("c",c);
//            map.put("k",k);
//            map.put("x",x);
//            arrayList.add(map);
//
//        }
//        return arrayList;
//    }
    public double interp(double x[],double y[],double x0){
        int n=x.length;
        double result;

            double v=0;
            for(int k=0;k<n;k++) {
                double w=1;
                for(int j=0;j<n;j++) {
                    if(k!=j){
                        w=w*(x0-x[j])/(x[k]-x[j]);
                    }
                }
                v=v+w*y[k];
            }
            result=v;
        return result;
    }
    private static double[] interp(double x[],double y[],double[] x0){
        int m=x.length;
        int n=x0.length;
        double y0[]=new double[n];
        for(int ia=0;ia<n;ia++) {
            double j=0;
            for(int ib=0;ib<m;ib++) {
                double k=1;
                for(int ic=0;ic<m;ic++) {
                    if(ib!=ic){
                        k=k*(x0[ia]-x[ic])/(x[ib]-x[ic]);
                    }
                }
                k=k*y[ib];
                j=j+k;
            }
            y0[ia]=j;
        }
        return y0;
    }


}
