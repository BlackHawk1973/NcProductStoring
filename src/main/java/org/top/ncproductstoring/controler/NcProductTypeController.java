package org.top.ncproductstoring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.ncproductstoring.entity.NcProductType;
import org.top.ncproductstoring.service.NcProductTypeService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

// NcProductTypeController - контроллер для работы со справочником видов брака
@Controller
@RequestMapping("nc-product-type")
public class NcProductTypeController {
    // сервис для работы со справочником видов брака
    private final NcProductTypeService ncProductTypeService;

    public NcProductTypeController(NcProductTypeService ncProductTypeService) {
        this.ncProductTypeService = ncProductTypeService;
    }

    // Обработчик получения всех объектов
    // http://localhost:8080/nc-product-type
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<NcProductType> ncProductTypes = ncProductTypeService.findAll();
        model.addAttribute("ncProductTypes", ncProductTypes);
        return "nc-product-type/nc-product-type-list";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/nc-product-type/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        NcProductType ncProductType = new NcProductType(); // объект для заполнения в форме
        model.addAttribute("ncProductType", ncProductType);
        return "nc-product-type/add-nc-product-type-form";
    }

    @PostMapping("new")
    public String postAddForm(NcProductType ncProductType, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<NcProductType> saved = ncProductTypeService.save(ncProductType);
            saved.ifPresent(productType -> redirectAttributes.addFlashAttribute("successMessage",
                    "Запись " + productType + " успешно добавлена"));
            return "redirect:/nc-product-type";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Запись с таким кодом вида брака уже существует");
            } else {
                throw e;
            }
            return "redirect:/nc-product-type/new";
        }
    }

    // Обработчик удаления объекта
    // http://localhost:8080/nc-product-type/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<NcProductType> deleted = ncProductTypeService.deleteById(id);
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
        return "redirect:/nc-product-type";
    }

    // Обработчики редактирования объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/nc-product-type/update
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<NcProductType> updated = ncProductTypeService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("ncProductType", updated.get());
            return "nc-product-type/update-nc-product-type-form";
        } else {
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Запись с id " + id + " не найдена" );
            return "redirect:/nc-product-type";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(NcProductType ncProductType, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<NcProductType> updated = ncProductTypeService.update(ncProductType);
            if (updated.isPresent()) {
                //вывод сообщение, что запись успешно обновлена
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + updated.get() + " успешно обновлена");
            } else {
                //вывод сообщения, что не получилось обновить
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Не получилось выполнить обновление");
            }
            return "redirect:/nc-product-type";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Запись с таким кодом вида брака уже существует");
            } else {
                throw e;
            }
            return "redirect:/nc-product-type";
        }
    }
}
