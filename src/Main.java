
public class Main {
    public static void main(String args[]) {
        MySql mySql=new MySql();
        mySql.greatStatement();

        mySql.addToSql("Paul", 32, "California", 20000.00 );
        mySql.addToSql("Allen", 25, "Texas", 15000.00 );
        mySql.addToSql("Teddy", 23, "Norway", 20000.00 );

        mySql.addUser ("Mark", 25, "Rich-Mond ", 65000.00 );

        mySql.printSql("NAME","Teddy");

        mySql.printSql("ID","2");
        System.out.println( mySql.containSql ("id","2"));

        mySql.closeStatement();
    }
}