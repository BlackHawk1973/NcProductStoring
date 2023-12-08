package org.top.ncproductstoring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.ncproductstoring.entity.NcProductCause;
import org.top.ncproductstoring.service.NcProductCauseService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

// NcProductTypeController - контроллер для работы со справочником причин брака
@Controller
@RequestMapping("nc-product-cause")
public class NcProductCauseController {
    // сервис для работы со справочником причин брака
    private final NcProductCauseService ncProductCauseService;
    public NcProductCauseController(NcProductCauseService ncProductCauseService) {
        this.ncProductCauseService = ncProductCauseService;
    }

    // Обработчик получения всех объектов
    // http://localhost:8080/nc-product-cause
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<NcProductCause> ncProductCauses = ncProductCauseService.findAll();
        model.addAttribute("ncProductcauses", ncProductCauses);
        return "nc-product-cause/nc-product-cause-list";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/nc-product-cause/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        NcProductCause ncProductCause = new NcProductCause(); // объект для заполнения в форме
        model.addAttribute("ncProductCause", ncProductCause);
        return "nc-product-cause/add-nc-product-cause-form";
    }

    @PostMapping("new")
    public String postAddForm(NcProductCause ncProductCause, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<NcProductCause> saved = ncProductCauseService.save(ncProductCause);
            if (saved.isPresent()) {
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + saved.get() + " успешно добавлена");
            }
            return "redirect:/nc-product-cause";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Запись с такой причиной брака уже существует");
            } else {
                throw e;
            }
            return "redirect:/nc-product-cause/new";
        }
    }

    // Обработчик удаления объекта
    // http://localhost:8080/nc-product-cause/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<NcProductCause> deleted = ncProductCauseService.deleteById(id);
        if (deleted.isPresent()) {
            // запишем сообщение что запись успешно удалена
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Запись " + deleted.get() + " успешно удалена");
        } else {
            // запишем сообщение что запись не найдена
            redirectAttributes.addFlashAttribute(
                    "Запись с id " + id + "не найдена");
        }
        return "redirect:/nc-product-cause";
    }

    // Обработчики редактирования объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/nc-product-cause/update
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<NcProductCause> updated = ncProductCauseService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("ncProductCause", updated.get());
            return "nc-product-cause/update-nc-product-cause-form";
        } else {
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Запись с id " + id + " не найдена" );
            return "redirect:/nc-product-cause";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(NcProductCause ncProductCause, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<NcProductCause> updated = ncProductCauseService.update(ncProductCause);
            if (updated.isPresent()) {
                //вывод сообщение, что запись успешно обновлена
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + updated.get() + " успешно обновлена");
            } else {
                //вывод сообщения, что не получилось обновить
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Не получилось выполнить обновление");
            }
            return "redirect:/nc-product-cause";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Запись с такой причиной брака уже существует");
            } else {
                throw e;
            }
            return "redirect:/nc-product-cause";
        }
    }
}
