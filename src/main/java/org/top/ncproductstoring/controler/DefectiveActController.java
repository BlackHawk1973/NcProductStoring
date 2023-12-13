package org.top.ncproductstoring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.ncproductstoring.entity.DefectiveAct;
import org.top.ncproductstoring.entity.NcProductCause;
import org.top.ncproductstoring.service.DefectiveActService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

//DefectiveActController - контроллер для работы со таблицей актов о браке
@Controller
@RequestMapping("defective-act")
public class DefectiveActController {

    //Внедрение зависимости DefectiveActService
    private final DefectiveActService defectiveActService;

    public DefectiveActController(DefectiveActService defectiveActService) {
        this.defectiveActService = defectiveActService;
    }

    // Обработчик получения всех объектов
    // http://localhost:8080/defective-act
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<DefectiveAct> defectiveActs = defectiveActService.findAll();
        model.addAttribute("defectiveActs", defectiveActs);
        return "defective-act/defective-act-list";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/defective-act/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        DefectiveAct defectiveAct = new DefectiveAct(); // объект для заполнения в форме
        model.addAttribute("defectiveAct", defectiveAct);
        return "defective-act/add-defective-act-form";
    }

    @PostMapping("new")
    public String postAddForm(DefectiveAct defectiveAct, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<DefectiveAct> saved = defectiveActService.save(defectiveAct);
            if (saved.isPresent()) {
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + saved.get() + " успешно добавлена");
            }
            return "redirect:/defective-act";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Запись с таким номером акта уже существует");
            } else {
                throw e;
            }
            return "redirect:/defective-act/new";
        }
    }

    // Обработчик удаления объекта
    // http://localhost:8080/defective-act/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<DefectiveAct> deleted = defectiveActService.deleteById(id);
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
        return "redirect:/defective-act";
    }

    // Обработчики редактирования объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/defective-act/update
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<DefectiveAct> updated = defectiveActService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("defectiveAct", updated.get());
            return "defective-act/update-defective-act-form";
        } else {
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Запись с id " + id + " не найдена" );
            return "redirect:/defective-act";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(DefectiveAct defectiveAct, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<DefectiveAct> updated = defectiveActService.update(defectiveAct);
            if (updated.isPresent()) {
                //вывод сообщение, что запись успешно обновлена
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + updated.get() + " успешно обновлена");
            } else {
                //вывод сообщения, что не получилось обновить
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Не получилось выполнить обновление");
            }
            return "redirect:/defective-act";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Запись с такой причиной брака уже существует");
            } else {
                throw e;
            }
            return "redirect:/defective-act";
        }
    }
}