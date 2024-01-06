import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class account {

    ArrayList<Product> Products= new ArrayList <Product>();
    ChekFormat chekFormat = new ChekFormat();
    int RUB = 0;
    int COP = 0;

    public void createAccount(){

        Scanner scanner = new Scanner(System.in);
        String productName;
        String productPrice;
        String command;
        int PriceRub;
        int PriceCop;
        boolean finish = false;
        boolean checkDouble;

        while(!finish){
            checkDouble = false;
            System.out.println("Введите название товара:");
            productName = scanner.nextLine();
            System.out.println("Введите цену товара в формате рубли.копейки (например 10.45 или 11.40):");
            while(!checkDouble) {
                productPrice = scanner.nextLine();
                HashMap<String, Integer> result = chekFormat.Chek(productPrice);
                    if(result!=null){
                        PriceRub = result.get("RUB");
                        PriceCop = result.get("COP");
                        Product pProduct = new Product(productName, PriceRub, PriceCop);
                        Products.add(pProduct);
                        RUB = RUB + PriceRub;
                        COP = COP + PriceCop;

                        checkDouble = true;
                    }else{
                        System.out.println("Введите цену в правильном формате");
                    }
            }
            System.out.println("Товар уcпешно добавлен.\nХотите добавить еще один товар? Если да - введите любой символ, если нет - 'Завершить'");
            command = scanner.nextLine();
            if(command.equalsIgnoreCase("Завершить")){
                finish = true;
            }

        }
        //если копеек больше ста, перекинем часть суммы в рубли
        if (COP >= 100){
            int val = COP / 100;
            if (val>0){
                RUB = RUB + val;
                COP = COP - (val*100);
            }
        }
    }
    
    public void printAccount(){
        System.out.println("Добавленные товары:");
        for (Product pProduct: Products) {
            System.out.println(pProduct.name + "-" + pProduct.RUB+"."+pProduct.COP);
        }
        String cop;
        if (COP < 10) {
            cop = "0";
        }else{
            cop = "";
        }
        System.out.println("Общая стоимость:" + RUB + "." + cop + COP + " " + chekFormat.GetRubleAddition(RUB));
    }
    public void splitAccount(int People){
        int rubEach = RUB / People;
        int val2 = RUB - rubEach * People;
        int val3 = 0;
        if(val2 > 0) {
            val3 = val2 * 100;
        }
        val3 = val3 + COP;
        int copEach = val3 / People;
        int val5 = val3 - copEach * People;
        String cop;
        if (copEach < 10) {
            cop = "0";
        }else{
            cop = "";
        }
        System.out.println("Каждый гость заплатит: " + rubEach + "." + cop + copEach + " "+ chekFormat.GetRubleAddition(rubEach));
        if(val5 > 0) {
            if (val5 < 10) {
                cop = "0";
            }else{
                cop = "";
            }
            System.out.println("Останется заплатить: 0." + cop + val5 + " рубля");
        }

    }

}
