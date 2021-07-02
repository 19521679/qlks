package Util;

public class MyConvert {
    public static Integer parseStringToInt(String str)
    {
        if (str.isEmpty()) return Integer.MIN_VALUE;
        return Integer.parseInt(str);
    }
    public static String parseIntToString(Integer a)
    {
        if (a==null) return "";
        return a.toString();
    }
    public static Integer parseObjToInt(Object a)
    {
       String k= parseObjToString(a);
        if (k==null||k.isEmpty()) return Integer.MIN_VALUE;
        return Integer.parseInt(k.toString());
    }
    public static Float parseStringToFloat(String str)
    {
        if (str.isEmpty()) return Float.MIN_VALUE;
        return Float.parseFloat(str);
    }
    public static String parseFloatToString(Float a)
    {
        if (a==null) return "";
        return a.toString();
    }
    public static String parseObjToString(Object a)
    {
        if (a==null) return "";
        return a.toString();
    }

    public static Integer parseFloatToInt(Float a)
    {
        if (a==null) return Integer.MIN_VALUE;
        float fa= a;
        return (int) fa;
    }

    public static Integer parseDoubleToInt(Double a)
    {
        if (a==null) return Integer.MIN_VALUE;
        double da= a;
        return (int) da;
    }

}
