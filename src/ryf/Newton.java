package ryf;

import inversequadratic.Fun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @Description
 * @ClassName Newton
 * @Author L-Cohen
 * @date 2020.11.23 20:30
 */
public class Newton {
    String f;

    public String getF() {
        return f;
    }

    public Newton(String f) {
        this.f = f;
    }

    public  ArrayList<HashMap> calculate(double x, double m, int n){
        ArrayList<HashMap> pro = new ArrayList<>();
        double x1;
        x1 = x - F(x) / f(x);

        for (int k = 0; true ; k++ ) {
            HashMap<String, Object> map = new HashMap<>();
            if(f(x) == 0){
                throw new RuntimeException("异常....");
            }
            x1 = x - F(x)/f(x);
            if(Math.abs(x1 - x) < m ){
                System.out.println("迭代" + (k) + "次得到结果");
                return pro;
            }
            if(k == n){
                throw new RuntimeException("迭代失败，已达最大迭代次数N....");
            }
            map.put("x", x);
            map.put("k",k);
            pro.add(k, map);
            x = x1;
        }
    }

    public  double F(double x){
        double fun = Fun.fun(getF(), x);
        return fun;
    }

    public  double f(double x) {
        double delta = 0.000001;
        return (F(x + delta) - F(x)) / delta;
    }
}
