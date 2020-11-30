package ryf;

import inversequadratic.Fun;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Description
 * @ClassName Dichotomy
 * @Author L-Cohen
 * @date 2020.11.23 19:43
 */
public class Dichotomy {
    private String f;

    public String getF() {
        return f;
    }

    public Dichotomy(String f) {
        this.f = f;
    }

    public ArrayList<HashMap> calculate(double a, double b, double e) throws ScriptException {
        ArrayList<HashMap> pro = new ArrayList<>();

        if (a >= b){
            throw new RuntimeException("上限大于下限");
        }
        double y0,x,y;
        x = y = 0;
        y0 = f(x);
        int i = 0;
        while ( (b - a) >= e ) {
            HashMap<String, Object> map = new HashMap<>();
            x = (a + b)/2;
            y = f(x);
            if(y*y0 > 0){
                a = x;
            }else {
                b = x;
            }
            if(i == 10000){
                System.out.println("运行超时...检查while循环结构");
                break;
            }

            map.put("k",i);
            map.put("x", x);
            pro.add(i, map);
            i++;
        }
        return pro;
    }

    public double f(double x){
        double fun = Fun.fun(getF(), x);
        return fun;
    }

}
