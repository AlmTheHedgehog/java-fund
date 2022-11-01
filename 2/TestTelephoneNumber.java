import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TestTelephoneNumber {
    public static void main(String[] args){
        ArrayList<Person> personList = new ArrayList<Person>();
        ArrayList<Company> companyList = new ArrayList<Company>();
        Map<TelephoneNumber, TelephoneEntry> telephoneTreeMap = new TreeMap<>();

        companyList.add(new Company("CatVidia", "962 North Richardson Street", 
        "+1", "086488597"));
        companyList.add(new Company("NorthWinds", "514 Marconi Street", 
        "+896", "686589211"));
        companyList.add(new Company("StarKeeper", "45 Race Street ", 
        "+806", "78568942"));

        personList.add(new Person("Den", "Kreg", "77 Brook St", 
        "+44", "986895475"));
        personList.add(new Person("Konul", "Kdrug", "738 Walt Whitman Ave", 
        "+385", "86845455"));
        personList.add(new Person("Bon", "Hors", "96 Lawrence Court", 
        "+896", "858525254"));
        personList.add(new Person("Aily", "Vivon", "718 Manor St",
        "+1", "585803690"));
        personList.add(new Person("Jane", "Loyi", "73 Hill Drive", 
        "44", "747203559"));
        personList.add(new Person("Kliv", "Gins", "4 Golden Star St", 
        "806", "36964750"));
        personList.add(new Person("Linx", "Debia", "47 Rocky River St",
        "+1", "573915462"));
        personList.add(new Person("Dibi", "Xiiin", "47 Rocky River St"));
        
        
        for(Person pers:personList){
            telephoneTreeMap.put(pers.getPhoneNumber(), pers);
        }
        for(Company comp:companyList){
            telephoneTreeMap.put(comp.getPhoneNumber(), comp);
        }

        for(Map.Entry<TelephoneNumber, TelephoneEntry> entry : telephoneTreeMap.entrySet()){
            System.out.println(entry.getValue().description());
        }

    }
}
