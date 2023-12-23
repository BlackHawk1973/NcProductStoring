package org.top.ncproductstoring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.ncproductstoring.entity.*;
import org.top.ncproductstoring.service.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

//ActItemController - контроллер для работы со таблицей содержимого акта о браке
@Controller
@RequestMapping("/act-item")
public class ActItemController {
    //Внедрение зависимости ActItemService и зависимостей справочников
    private final ActItemService actItemService;
    private final NcProductCauseService ncProductCauseService;  //Причины брака
    private final NcProductTypeService ncProductTypeService;    //Виды брака
    private final ProductionService productionService;          //Продукция
    private final SectorService sectorService;                  //Участки
    private final TeamService teamService;                      //Бригады
    private final TechOperationService techOperationService;    //Технологические операции
    private final DefectiveActService defectiveActService;      //Акты о браке


    public ActItemController(ActItemService actItemService, NcProductCauseService ncProductCauseService,
                             NcProductTypeService ncProductTypeService, ProductionService productionService,
                             SectorService sectorService, TeamService teamService,
                             TechOperationService techOperationService, DefectiveActService defectiveActService) {
        this.actItemService = actItemService;
        this.ncProductCauseService = ncProductCauseService;
        this.ncProductTypeService = ncProductTypeService;
        this.productionService = productionService;
        this.sectorService = sectorService;
        this.teamService = teamService;
        this.techOperationService = techOperationService;
        this.defectiveActService = defectiveActService;
    }

    // Обработчик получения всех объектов
    // http://localhost:8080/act-item
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<ActItem> actItems = actItemService.findAll();
        model.addAttribute("actItems", actItems);
        return "act-item/act-item-list";
    }

    // Обработчик получения детальной информации об объекте по id
    // http://localhost:8080/act-item/{id}
    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<ActItem> actItem = actItemService.findById(id);
        if (actItem.isPresent()) {
            model.addAttribute("defectiveAct", actItem.get());
//            model.addAttribute("productCategory", new ProductCategory());
            // Iterable<Category> categories = categoryService.findAll();
////            List<Category> categories = new ArrayList<>();
//            for (Category category : categoryService.findAll()) {
//                if (product.get().getProductCategorySet()
//                        .stream()
//                        .noneMatch(c -> Objects.equals(c.getCategory().getId(), category.getId()))) {
//                    categories.add(category);
//                }
//            }
//            model.addAttribute("categories", categories);
            return "act-item/act-item-details";
        }
        redirectAttributes.addFlashAttribute("dangerMessage",
                "Запись с id " + id + " не найдена" );
        return "redirect:/act-item";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/act-item/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        ActItem actItem = new ActItem(); // объект для заполнения в форме
        Iterable<NcProductCause> ncProductCauses = ncProductCauseService.findAll();
        Iterable<NcProductType> ncProductTypes = ncProductTypeService.findAll();
        Iterable<Production> productions = productionService.findAll();
        Iterable<Sector> sectors = sectorService.findAll();
        Iterable<Team> teams = teamService.findAll();
        Iterable<TechOperation> techOperations = techOperationService.findAll();
        model.addAttribute("actItem", actItem);
        model.addAttribute("ncProductCauses", ncProductCauses);
        model.addAttribute("ncProductTypes", ncProductTypes);
        model.addAttribute("productions", productions);
        model.addAttribute("sectors", sectors);
        model.addAttribute("teams", teams);
        model.addAttribute("techOperations", techOperations);
        return "act-item/add-act-item-form";
    }

    @GetMapping("new/{id}")
    public String getAddForm(@PathVariable Integer id, Model model) {
        ActItem actItem = new ActItem(); // объект для заполнения в форме
        Optional<DefectiveAct> defectiveAct = defectiveActService.findById(id);
        Iterable<NcProductCause> ncProductCauses = ncProductCauseService.findAll();
        Iterable<NcProductType> ncProductTypes = ncProductTypeService.findAll();
        Iterable<Production> productions = productionService.findAll();
        Iterable<Sector> sectors = sectorService.findAll();
        Iterable<Team> teams = teamService.findAll();
        Iterable<TechOperation> techOperations = techOperationService.findAll();
        model.addAttribute("actItem", actItem);
        model.addAttribute("ncProductCauses", ncProductCauses);
        model.addAttribute("ncProductTypes", ncProductTypes);
        model.addAttribute("productions", productions);
        model.addAttribute("sectors", sectors);
        model.addAttribute("teams", teams);
        model.addAttribute("techOperations", techOperations);
        model.addAttribute("defectiveActs", new DefectiveAct[]{defectiveAct.get()});
        return "act-item/add-act-item-form";
    }

    @PostMapping("new")
    public String postAddForm(ActItem actItem, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<ActItem> saved = actItemService.save(actItem);
            if (saved.isPresent()) {
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + saved.get() + " успешно добавлена");
            }
            return "redirect:/defective-act/" + actItem.getDefectiveAct().getId();
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: " + e.getMessage());
            } else {
                throw e;
            }
            return "redirect:/act-item/new";
        }
    }

    // Обработчик удаления объекта
    // http://localhost:8080/act-item/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<ActItem> deleted = actItemService.deleteById(id);
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
        return "redirect:/act-item";
    }

    // Обработчики редактирования объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/act-item/update
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<ActItem> updated = actItemService.findById(id);
        Iterable<NcProductCause> ncProductCauses = ncProductCauseService.findAll();
        Iterable<NcProductType> ncProductTypes = ncProductTypeService.findAll();
        Iterable<Production> productions = productionService.findAll();
        Iterable<Sector> sectors = sectorService.findAll();
        Iterable<Team> teams = teamService.findAll();
        Iterable<TechOperation> techOperations = techOperationService.findAll();
        if (updated.isPresent()) {
            model.addAttribute("actItem", updated.get());
            model.addAttribute("ncProductCauses", ncProductCauses);
            model.addAttribute("ncProductTypes", ncProductTypes);
            model.addAttribute("productions", productions);
            model.addAttribute("sectors", sectors);
            model.addAttribute("teams", teams);
            model.addAttribute("techOperations", techOperations);
            return "act-item/update-act-item-form";
        } else {
            redirectAttributes.addFlashAttribute("dangerMessage",
                    "Запись с id " + id + " не найдена" );
            return "redirect:/act-item";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(ActItem actItem, RedirectAttributes redirectAttributes) throws Exception {
        try {
            Optional<ActItem> updated = actItemService.update(actItem);
            if (updated.isPresent()) {
                //вывод сообщение, что запись успешно обновлена
                redirectAttributes.addFlashAttribute("successMessage",
                        "Запись " + updated.get() + " успешно обновлена");
            } else {
                //вывод сообщения, что не получилось обновить
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Не получилось выполнить обновление");
            }
            return "redirect:/act-item";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                // process exception here
                System.out.println(">Возникла ошибка: " + e.getMessage());
                redirectAttributes.addFlashAttribute("dangerMessage",
                        "Ошибка записи в БД: " + e.getMessage());
            } else {
                throw e;
            }
            return "redirect:/defective-act";
        }
    }
}

