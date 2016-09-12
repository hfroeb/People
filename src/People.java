import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import com.sun.tools.javac.code.Attribute;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Reads a csv file and maps country name to a list of people who are from that country. 
 * Then, for each list sorts by last name.
 */
public class People {
    public static void main(String[] args) throws IOException {
        ArrayList<Person> peopleList = new ArrayList<>();
        HashMap<String, ArrayList<Person>> countries = new HashMap<>();

        File f = new File("people.csv");
        Scanner fileScanner = new Scanner(f);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\,");
            Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
            String key = person.getCountry();
            peopleList.add(person);
            countries.putIfAbsent(key, new ArrayList<>());
            countries.get(key).add(person);

//            if (countries.containsKey(key)) {
//                peopleList = peopleList.get(person.getCountry())
//                countries.put(key, peopleList);
//
//            } else {
//
//                peopleList.add(person);
//                countries.put(key, peopleList);
//            }
        }
        //     System.out.println(countries.size());

        saveCountries(countries);

    }
    public static void saveCountries(HashMap<String, ArrayList<Person>> countries) throws IOException {
        File f = new File("people.json");
        FileWriter fw = new FileWriter(f);
        ArrayList<ArrayList<Person>> countriesToSave = new ArrayList<>();
        for (ArrayList<Person> people: countries.values()) {
            Collections.sort(people);
            Collections.sort(people);
            System.out.println(people.toString());
            countriesToSave.add(people);
        }
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(countriesToSave);
        fw.write(json);
        fw.close();
    }


}
