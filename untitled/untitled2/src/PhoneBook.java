import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PhoneBook {
    private final Map<String, List<String>> surnameToPhones = new HashMap<>();
    private final Set<String> allPhones = new HashSet<>();

    public void add(String surname, String phone) {
        if (allPhones.contains(phone)) {
            System.out.println("Ошибка: номер " + phone + " уже существует в справочнике");
            return;
        }

        List<String> phones = surnameToPhones.computeIfAbsent(surname, k -> new ArrayList<>());
        phones.add(phone);
        allPhones.add(phone);
    }

    public List<String> get(String surname) {
        return new ArrayList<>(surnameToPhones.getOrDefault(surname, new ArrayList<>()));
    }

    public boolean containsPhone(String phone) {
        return allPhones.contains(phone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Телефонный справочник:\n");
        for (Map.Entry<String, List<String>> entry : surnameToPhones.entrySet()) {
            sb.append(entry.getKey())
                    .append(": ")
                    .append(String.join(", ", entry.getValue()))
                    .append("\n");
        }
        return sb.toString();
    }
}
