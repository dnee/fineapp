package ru.deev.fineapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.deev.fineapp.domain.Fine;
import ru.deev.fineapp.repos.FineHistoryRepo;
import ru.deev.fineapp.repos.FineRepo;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StatsController {

    //Репозиторий списка штрафов
    private FineHistoryRepo fineHistoryRepo;

    //Репозиторий справочника по штрафам
    private FineRepo finesRepo;

    @Autowired
    public void setFineHistoryRepo(FineHistoryRepo fineHistoryRepo) {
        this.fineHistoryRepo = fineHistoryRepo;
    }

    @Autowired
    public void setFinesRepo(FineRepo finesRepo) {
        this.finesRepo = finesRepo;
    }

    //Обрабатывает запрос на получение списка наиболее распространенных штрафов
    @GetMapping("/finereport")
    public String fineReport(Map<String, Object> model) {
        //Получаем список штрафов из таблицы справочника
        Iterable<Fine> fineCatalog = finesRepo.findAll();
        //Коллекция ключ - конкретный штраф, значение - количество выписанных штрафов такого типа и их процент от общего числа
        Map<Fine, CountStat> fineStats = new LinkedHashMap<>();
        //Заполняем эту коллекцию
        Long totalFines = fineHistoryRepo.count();
        for (Fine fine : fineCatalog) {
            Long count = fineHistoryRepo.countFineHistoriesByFine_FineId(fine.getFineId());
            if (count > 0) {
                fineStats.put(fine, new CountStat(count, ((float) count) / (float) (totalFines) * 100.0f));
            }
        }
        //Сортируем по убыванию
        Map<Fine, CountStat> fineStatsSorted = fineStats.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(CountStat::getTotalFines).reversed())).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue(), (x, y) -> x, LinkedHashMap::new));
        fineStats.clear();
        //Получаем первые 5 значений
        fineStats = fineStatsSorted.entrySet().stream().limit(5).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue(), (x, y) -> x, LinkedHashMap::new));
        if (fineStats.size() > 0) {
            model.put("notempty", true);
        } else {
            model.put("notempty", false);
        }
        model.put("finestats", fineStats.entrySet());
        return "finereport";
    }

    //Класс используется как значение в коллекции Map
    private class CountStat {
        //Кол-во штрафов
        private Long totalFines = 0L;
        //Процент от общего числа
        private String finePercent;

        public CountStat() {
        }

        public CountStat(Long totalFines, Float finePercent) {
            this.totalFines = totalFines;
            this.finePercent = String.format("%.1f", finePercent);
        }

        public Long getTotalFines() {
            return totalFines;
        }

        public String getFinePercent() {
            return finePercent;
        }
    }
}
