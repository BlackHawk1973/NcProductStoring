package org.top.ncproductstoring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.ncproductstoring.entity.Production;
import org.top.ncproductstoring.service.ProductionService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

// ProductionController - контроллер для работы со справочником видов продукции
@Controller
@RequestMapping("production")
public class ProductionController {
    // сервис для работы со справочником видов продукции
    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    // Обработчик получения всех объектов
    // http://localhost:8080/production
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Production> productions = productionService.findAll();
        model.addAttribute("productions", productions);
        return "production/production-list";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/production/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        Production production = new Production(); // объект для заполнения в форме
        model.addAttribute("production", production);
        return "production/add-production-form";
    }

    @PostMapping("new")
    public String postAddForm(Production production, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<Production> saved = productionService.save(production);
            saved.ifPresent(value -> redirectAttributes.addFlashAttribute("successMessage",
                    "Запись " + value + " успешно добавлена"));
            return "redirect:/production";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Такой вид продукции уже существует");
            } else {
                throw e;
            }
            return "redirect:/production/new";
        }
    }

    // Обработчик удаления объекта
    // http://localhost:8080/production/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Production> deleted = productionService.deleteById(id);
        if (deleted.isPresent()) {
            // запишем сообщение, что запись успешно удалена
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Запись " + deleted.get() + " успешно удалена");
        } else {
            // запишем сообщение, что запись не найдена
            redirectAttributes.addFlashAttribute(
                    "Запись с id " + id + "не найдена");
        }
        return "redirect:/production";
    }

    // Обработчики редактирования объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/production/update
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Production> updated = productionService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("production", updated.get());
            return "production/update-production-form";
        } else {
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Запись с id " + id + " не найдена" );
            return "redirect:/production";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Production production, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<Production> updated = productionService.update(production);
            if (updated.isPresent()) {
                //вывод сообщение, что запись успешно обновлена
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + updated.get() + " успешно обновлена");
            } else {
                //вывод сообщения, что не получилось обновить
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Не получилось выполнить обновление");
            }
            return "redirect:/production";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Такой вид продукции уже существует");
            } else {
                throw e;
            }
            return "redirect:/production";
        }
    }
}
