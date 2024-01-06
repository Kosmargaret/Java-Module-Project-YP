import java.util.Scanner;

public class Main {

    static int People;

    public static void main(String[] args) {
        SetPeople();
        Calculate();
    }

    public static void SetPeople() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("На скольких человек разделить счет?");
        String pValue;
        boolean chek = false;
        while (!chek){
            pValue = scanner.next();
            try {
                int Value = Integer.parseInt(pValue.trim());
                if (Value <= 1) {
                    System.out.println("Некорректное значение (введите целое число больше 1)");
                } else {
                    People = Value;
                    chek = true;
                }
            } catch (NumberFormatException Error) {
                System.out.println("Некорректное значение (введите целое число больше 1)");
            }

        }
    }

    public static void Calculate(){
        account account = new account();
        account.createAccount();
        account.printAccount();
        account.splitAccount(People);
    }

}

