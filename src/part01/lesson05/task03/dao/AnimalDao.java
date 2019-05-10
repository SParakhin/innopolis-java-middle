package part01.lesson05.task03.dao;

import part01.lesson05.task03.entity.Animal;
import part01.lesson05.task03.sorting.SortAnimal;
import java.util.*;

public class AnimalDao {

    public static Map<Integer, Animal> animalMap = new HashMap<>();

    /**
     * Метод для формирования промежуточной карты для эффективного поиска животного по кличке
     * @return Карта с ключом Name, параметром - списком животных
     */
    public static Map<String, Set<Animal>> getAnimalMapByNameKey() {
        Map<String, Set<Animal>> nameAnimalValue = new HashMap<>();
        Set<Integer> integerSet = animalMap.keySet();
        Set<Animal> arrayList = new HashSet<>();
        for (Integer i : integerSet) {
            arrayList.add(animalMap.get(i));
        }
        for (Animal a : arrayList) {
            for (Map.Entry<Integer, Animal> item : animalMap.entrySet()) {
                if (item.getValue().getName().equals(a.getName())) {
                    if (!nameAnimalValue.containsKey(a.getName())) {
                        nameAnimalValue.put(item.getValue().getName(), new HashSet<>());
                    }
                    nameAnimalValue.get(item.getValue().getName()).add(a);
                }
            }
        }
        return nameAnimalValue;
    }

    /**
     * Метод для поиска животного по кличке. Учтена возможность поиска животных с совпадающими именами, но уникальными id.
     * @param map
     * @param name
     */
    public static void getAnimalByName(Map<String, Set<Animal>> map, String name) {
        System.out.println(map.get(name));
    }


    /**
     * Поиск животного по id
     *
     * @param id
     * @return Найденное животное
     */
    public static Animal getAnimalById(int id) {
        return animalMap.get(id);
    }

    /**
     * Метод для изменения данных животного по его ID
     *
     * @param id
     * @param newWeight новый вес
     * @return Животное с измененными параметрами
     */
    public static Animal updateAnimal(int id, int newWeight) {
        Animal animal = animalMap.get(id);
        animal.setWeight(newWeight);
        return animal;
    }

    /**
     * Добавление животного в список. При попытке добавить дубликат с существующим id возникает исключение.
     *
     * @param animal
     */
    public static void addAnimal(int id, Animal animal) {
        Set<Integer> integerSet = animalMap.keySet();
        if (integerSet.contains(animal.getId())) {
            try {
                throw new Exception("Животное с такими параметрами существует - " + animal.toString());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        animalMap.put(id, animal);
    }

    /**
     * Метод для сортировки по значению Хозяин, Кличка животного, вес
     */
    public static void sortedAnimalsMap() {
        ArrayList<Animal> sortList = new ArrayList<>();
        for (Map.Entry<Integer, Animal> pair : animalMap.entrySet()) {
            sortList.add(pair.getValue());
            Collections.sort(sortList, new SortAnimal());
        }
        System.out.println(sortList);
    }
}