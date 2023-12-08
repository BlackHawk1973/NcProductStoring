package org.top.ncproductstoring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.ncproductstoring.entity.Sector;
import org.top.ncproductstoring.service.SectorService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

// SectorController - контроллер для работы со справочником участков
@Controller
@RequestMapping("sector")
public class SectorController {
    // сервис для работы со справочником участков
    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    // Обработчик получения всех объектов
    // http://localhost:8080/sector
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Sector> sectors = sectorService.findAll();
        model.addAttribute("sectors", sectors);
        return "sector/sector-list";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/sector/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        Sector sector = new Sector(); // объект для заполнения в форме
        model.addAttribute("sector", sector);
        return "sector/add-sector-form";
    }

    @PostMapping("new")
    public String PostAddForm(Sector sector, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<Sector> saved = sectorService.save(sector);
            if (saved.isPresent()) {
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + saved.get() + " успешно добавлена");
            }
            return "redirect:/sector";
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                System.out.println(">Возникла ошибка " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Такой участок уже существует");
            }
            else {
                throw e;
            }
        }
        return "redirect:/sector/new";
    }

    // Обработчик удаления объекта
    // http://localhost:8080/sector/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Sector> deleted = sectorService.deleteById(id);
        if (deleted.isPresent()) {
            // запишем сообщение, что запись успешно удалена
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Запись " + deleted.get() + " успешно удалена");
        }
        else {
            // запишем сообщение, что запись не найдена
            redirectAttributes.addFlashAttribute(
                    "Запись с id " + id + "не найдена");
        }
        return "redirect:/sector";
    }

    // Обработчики редактирования объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/sector/update
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Sector> updated = sectorService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("sector", updated.get());
            return "sector/update-sector-form";
        } else {
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Запись с id " + id + " не найдена" );
            return "redirect:/sector";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Sector sector, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<Sector> updated = sectorService.update(sector);
            if (updated.isPresent()) {
                //вывод сообщение, что запись успешно обновлена
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + updated.get() + " успешно обновлена");
            } else {
                //вывод сообщения, что не получилось обновить
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Не получилось выполнить обновление");
            }
            return "redirect:/sector";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Такой участок уже существует");
            } else {
                throw e;
            }
            return "redirect:/sector";
        }
    }
}
