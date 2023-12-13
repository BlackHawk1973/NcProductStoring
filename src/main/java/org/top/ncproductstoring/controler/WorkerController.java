package org.top.ncproductstoring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.ncproductstoring.entity.NcProductCause;
import org.top.ncproductstoring.entity.Worker;
import org.top.ncproductstoring.service.WorkerService;

import java.util.Optional;

// WorkerController - контроллер для работы со справочником сотрудников
@Controller
@RequestMapping("/worker")
public class WorkerController {
    // сервис для работы со справочником сотрудников
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    // Обработчик получения всех объектов
    // http://localhost:8080/worker
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Worker> workers = workerService.findAll();
        model.addAttribute("workers", workers);
        return "/worker/worker-list";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/worker/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        Worker worker = new Worker(); // объект для заполнения в форме
        model.addAttribute("worker", worker);
        return "worker/add-worker-form";
    }

    @PostMapping("new")
    public String postAddForm(Worker worker, RedirectAttributes redirectAttributes) {
        Optional<Worker> saved = workerService.save(worker);
        if (saved.isPresent()) {
            redirectAttributes.addFlashAttribute("successmessage",
                    "Запись " + saved.get() + " успешно добавлена");
            }
        return "redirect:/worker";
    }

    // Обработчик удаления объекта
    // http://localhost:8080/worker/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Worker> deleted = workerService.deleteById(id);
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
        return "redirect:/worker";
    }

    // Обработчики редактирования объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/worker/update
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Worker> updated = workerService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("worker", updated.get());
            return "worker/update-worker-form";
        } else {
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Запись с id " + id + " не найдена" );
            return "redirect:/worker";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Worker worker, RedirectAttributes redirectAttributes) {
        Optional<Worker> updated = workerService.update(worker);
        if (updated.isPresent()) {
            //вывод сообщение, что запись успешно обновлена
            redirectAttributes.addFlashAttribute("successMessage",
                    "Запись " + updated.get() + " успешно обновлена");
        } else {
            //вывод сообщения, что не получилось обновить
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Не получилось выполнить обновление");
        }
        return "redirect:/worker";
    }
}
