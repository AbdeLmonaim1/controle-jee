package ma.enset.controlejee.web;

import lombok.AllArgsConstructor;
import ma.enset.controlejee.entities.Entreprise;
import ma.enset.controlejee.repository.EntrepriseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class EntrepriseController {
    private EntrepriseRepository entrepriseRepository;
    @GetMapping(path = "/user/index")
    public String entreprises(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Entreprise> entreprisePage = entrepriseRepository.findByUsernameContains(keyword, PageRequest.of(page,size));
        model.addAttribute("entreprises", entreprisePage.getContent());
        model.addAttribute("pages", new int[entreprisePage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "entreprises";
    }
    @GetMapping(path = "/")
    public String entreprises(){
        return "redirect:/user/index";
    }
    @GetMapping("/admin/delete")
    public String deleteEntreprise(Long id, String keyword, int page){
        entrepriseRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/user/entreprises")
    @ResponseBody
    public List<Entreprise> listEntreprise(){
        return entrepriseRepository.findAll();
    }
    @GetMapping("/admin/formEntreprise")
    public String formPatients(Model model){
        model.addAttribute("entreprise", new Entreprise());
        return "formEntreprise";
    }
    @PostMapping(path = "/admin/save")
    public String save(Model model, Entreprise entreprise, BindingResult bindingResult, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") int page){
        if(bindingResult.hasErrors()) return "formEntreprise";
        entrepriseRepository.save(entreprise);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editEntreprise")
    public String updatePatient(Model model, Long id, String keyword, int page){
        Entreprise entreprise = entrepriseRepository.findById(id).get();
        if (entreprise == null) throw new RuntimeException("Cette Entreprise n'existe pas!!!");
        model.addAttribute("entreprise", entreprise);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editEntreprise";
    }

}
