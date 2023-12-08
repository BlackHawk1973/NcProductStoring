package org.top.ncproductstoring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.ncproductstoring.entity.TechOperation;
import org.top.ncproductstoring.service.TechOperationService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

// TechOperationController - контроллер для работы со справочником технологических операций
@Controller
@RequestMapping("tech-operation")
public class TechOperationController {
    private final TechOperationService techOperationService;

    public TechOperationController(TechOperationService techOperationService) {
        this.techOperationService = techOperationService;
    }

    // Обработчик получения всех объектов
    // http://localhost:8080/tech-operation
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<TechOperation> techOperations = techOperationService.findAll();
        model.addAttribute("techOperations", techOperations);
        return "tech-operation/tech-operation-list";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/tech-operation/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        TechOperation techOperation = new TechOperation(); // объект для заполнения в форме
        model.addAttribute("techOperation", techOperation);
        return "tech-operation/add-tech-operation-form";
    }

    @PostMapping("new")
    public String postAddForm(TechOperation techOperation, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<TechOperation> saved = techOperationService.save(techOperation);
            saved.ifPresent(operation -> redirectAttributes.addFlashAttribute("successMessage",
                    "Запись " + operation + " успешно добавлена"));
            return "redirect:/tech-operation";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Такой вид технологической операции существует");
            } else {
                throw e;
            }
            return "redirect:/tech-operation/new";
        }
    }

    // Обработчик удаления объекта
    // http://localhost:8080/tech-operation/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<TechOperation> deleted = techOperationService.deleteById(id);
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
        return "redirect:/tech-operation";
    }

    // Обработчики редактирования объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/tech-operation/update
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<TechOperation> updated = techOperationService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("techOperation", updated.get());
            return "tech-operation/update-tech-operation-form";
        } else {
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Запись с id " + id + " не найдена" );
            return "redirect:/tech-operation";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(TechOperation techOperation, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<TechOperation> updated = techOperationService.update(techOperation);
            if (updated.isPresent()) {
                //вывод сообщение, что запись успешно обновлена
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + updated.get() + " успешно обновлена");
            } else {
                //вывод сообщения, что не получилось обновить
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Не получилось выполнить обновление");
            }
            return "redirect:/tech-operation";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Такой вид технологической операции уже существует");
            } else {
                throw e;
            }
            return "redirect:/tech-operation";
        }
    }
}
