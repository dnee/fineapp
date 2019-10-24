package ru.deev.fineapp.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.deev.fineapp.domain.FineHistory;
import ru.deev.fineapp.repos.FineHistoryRepo;

import java.util.Collection;
import java.util.Map;

@Controller
public class MainController {

    //Репозиторий списка штрафов
    private FineHistoryRepo fineHistoryRepo;

    @Autowired
    public void setFineHistoryRepo(FineHistoryRepo fineHistoryRepo) {
        this.fineHistoryRepo = fineHistoryRepo;
    }

    //Обрабатывает запрос по умолчанию
    @GetMapping
    public String main(Map<String, Object> model) {
        setDefaultModel(model);
        return "main";
    }

    //Обрабатвает запрос на фильтрацию по номеру автомобиля
    @GetMapping("/filterByCar")
    public String filterByLicensePlate(@RequestParam(defaultValue = "") String licensePlate,
                                       Map<String, Object> model) {
        //Если какое либо из полей формы не заполнено отвечаем по умолчанию
        if (licensePlate.length() == 0) {
            setDefaultModel(model);
            return "main";
        }
        //Получаем штрафы по номеру автомобиля
        Iterable<FineHistory> fines = fineHistoryRepo.getFineHistoriesByCar_LicensePlate(licensePlate);

        if (((Collection<FineHistory>) fines).size() > 0) {
            model.put("notempty", true);
        } else {
            model.put("notempty", false);
        }
        model.put("licensePlate", licensePlate);
        model.put("fines", fines);
        return "filterbycar";
    }

    //Обрабатвает запрос на фильтрацию по владельцу автомобилей
    @GetMapping("/filterByDriver")
    public String filterByDriver(@RequestParam(defaultValue = "") String secondName,
                                 @RequestParam(defaultValue = "") String firstName,
                                 @RequestParam(defaultValue = "") String middleName,
                                 Map<String, Object> model) {
        //Если какое либо из полей формы не заполнено отвечаем по умолчанию
        if (secondName.length() == 0 && firstName.length() == 0 && middleName.length() == 0) {
            setDefaultModel(model);
            return "main";
        }
        //Получаем идентификатор водителя по ФИО
        String driverId = DigestUtils.sha256Hex(secondName.toLowerCase() + firstName.toLowerCase() + middleName.toLowerCase());
        //Получаем штрафы водителя по его идентификатору
        Iterable<FineHistory> fines = fineHistoryRepo.getFineHistoriesByCar_Owner_DriverId(driverId);

        if (((Collection<FineHistory>) fines).size() > 0) {
            model.put("notempty", true);
        } else {
            model.put("notempty", false);
        }
        model.put("secondName", secondName);
        model.put("firstName", firstName);
        model.put("middleName", middleName);
        model.put("fines", fines);
        return "filterbydriver";
    }

    private void setDefaultModel(Map<String, Object> model) {
        //По умолчанию получаем весь список штрафов
        Iterable<FineHistory> fines = fineHistoryRepo.findAll();

        if (((Collection<FineHistory>) fines).size() > 0) {
            model.put("notempty", true);
        } else {
            model.put("notempty", false);
        }
        model.put("fines", fines);
    }
}
