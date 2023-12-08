package org.top.ncproductstoring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.ncproductstoring.entity.Sector;
import org.top.ncproductstoring.entity.Team;
import org.top.ncproductstoring.service.SectorService;
import org.top.ncproductstoring.service.TeamService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Controller
@RequestMapping("/team")
public class TeamController {
    // сервис для работы со справочником участков
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // Обработчик получения всех объектов
    // http://localhost:8080/team
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "team/team-list";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/team/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        Team team = new Team(); // объект для заполнения в форме
        model.addAttribute("team", team);
        return "team/add-team-form";
    }

    @PostMapping("new")
    public String PostAddForm(Team team, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<Team> saved = teamService.save(team);
            if (saved.isPresent()) {
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + saved.get() + " успешно добавлена");
            }
            return "redirect:/team";
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                System.out.println(">Возникла ошибка " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Такая бригада уже существует");
            }
            else {
                throw e;
            }
        }
        return "redirect:/team/new";
    }

    // Обработчик удаления объекта
    // http://localhost:8080/team/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Team> deleted = teamService.deleteById(id);
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
        return "redirect:/team";
    }

    // Обработчики редактирования объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/team/update
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Team> updated = teamService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("team", updated.get());
            return "team/update-team-form";
        } else {
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Запись с id " + id + " не найдена" );
            return "redirect:/team";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Team team, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<Team> updated = teamService.update(team);
            if (updated.isPresent()) {
                //вывод сообщение, что запись успешно обновлена
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + updated.get() + " успешно обновлена");
            } else {
                //вывод сообщения, что не получилось обновить
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Не получилось выполнить обновление");
            }
            return "redirect:/team";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: Такая бригада уже существует");
            } else {
                throw e;
            }
            return "redirect:/team";
        }
    }
}
