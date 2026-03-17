package egov.minwon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egov.minwon.service.MinwonService;
import egov.minwon.service.MinwonVO;

@Controller
public class MinwonController {

    private final MinwonService minwonService;

    public MinwonController(MinwonService minwonService) {
        this.minwonService = minwonService;
    }

    @GetMapping("/minwon/list.do")
    public String list(Model model) {
        model.addAttribute("minwonList", minwonService.selectMinwonList());
        return "minwon/list";
    }

    @PostMapping("/minwon/insert.do")
    public String insert(@ModelAttribute MinwonVO minwonVO, RedirectAttributes redirectAttributes) {
        minwonVO.setStatus("접수");
        minwonService.insertMinwon(minwonVO);
        redirectAttributes.addFlashAttribute("message", "민원이 등록되었습니다.");
        return "redirect:/minwon/list.do";
    }

    @PostMapping("/minwon/status.do")
    public String updateStatus(@RequestParam("id") Long id,
                               @RequestParam("status") String status,
                               RedirectAttributes redirectAttributes) {
        minwonService.updateMinwonStatus(id, status);
        redirectAttributes.addFlashAttribute("message", "상태가 변경되었습니다.");
        return "redirect:/minwon/list.do";
    }
}
