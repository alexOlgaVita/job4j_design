package ru.job4j.question;

import java.util.List;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int addedCount = 0;
        int changedCount = 0;
        int deletededCount = 0;
        for (User p : previous) {
            if (current.stream().anyMatch(c -> c.getId() == p.getId() && !c.getName().equals(p.getName()))) {
                changedCount++;
            }
        }
        List<Integer> idCurrentList = current.stream().map(User::getId).toList();
        for (User p : previous) {
            if (!idCurrentList.contains(p.getId())) {
                deletededCount++;
            }
        }
        List<Integer> idPreviousList = previous.stream().map(User::getId).toList();
        for (User c : current) {
            if (!idPreviousList.contains(c.getId())) {
                addedCount++;
            }
        }
        return new Info(addedCount, changedCount, deletededCount);
    }
}
