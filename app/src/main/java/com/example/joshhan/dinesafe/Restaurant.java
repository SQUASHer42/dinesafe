public class Restaurant {
    private int id;
    private String name;
    private String type;
    private String address;
    private String status;
    private int minimuminspectionsperyear;
    private ArrayList<Inspection> inspections;

    public Restaurant(int a, String b, String c, String d, String e, int f, ArrayList<Inspection> g){
        id = a;
        name = b;
        type = c;
        address = d;
        status = e;
        minimuminspectionsperyear = f;
        inspections = g;
    }
