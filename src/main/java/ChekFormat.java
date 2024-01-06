import java.util.HashMap;

public class ChekFormat {

    public HashMap<String, Integer> Chek(String productPrice){
        double price;
        //убедимся, что строка не пустая и не содержит пробелов
        if(!productPrice.trim().isEmpty() && productPrice.indexOf(" ") == -1) {
            try {
                //Надо убедиться, что это вообще цифра
                price = Double.parseDouble(productPrice);
                return ParceString(productPrice);
            } catch (NumberFormatException Error) {
            }
        }
        return null;

    }

    private HashMap<String, Integer> CreateHash(int RUB, int COP){
        HashMap <String, Integer> associativeArray = new HashMap<>();
        associativeArray.put("RUB", RUB);
        associativeArray.put("COP", COP);
        return associativeArray;
    }

    private HashMap<String, Integer> ParceString(String productPrice){
        String[] PriceArray = productPrice.split("\\.");
        if (PriceArray.length == 2 && PriceArray[1].length() == 2){
            int RUB = Integer.parseInt(PriceArray[0].trim());
            int COP = Integer.parseInt(PriceArray[1].trim());
            if (RUB >=0 && COP>=0){
                return CreateHash(RUB, COP);
            }
        } else if (PriceArray.length == 1) {
            int RUB = Integer.parseInt(PriceArray[0].trim());
            if (RUB >=0){
                return CreateHash(RUB, 0);
            }
        }
        return null;
    }

    public String GetRubleAddition(int num)
    {
        var preLastDigit = num % 100 / 10;
        if (preLastDigit == 1)
        {
            return "рублей";
        }

        switch (num % 10)
        {
            case 1:
                return "рубль";
            case 2:
            case 3:
            case 4:
                return "рубля";
            default:
                return "рублей";
        }
    }
}
