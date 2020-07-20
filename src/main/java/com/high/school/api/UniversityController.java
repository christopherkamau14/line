package com.high.school.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/api")
public class UniversityController {
    @Autowired
    UniversityService universityService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/register")
    public String register(){
        return "registry";
    }

    @GetMapping("/view")
    public String view(){
        return "view";
    }

    @GetMapping("/pageStudent")
    @ResponseBody
    Page<University> pageStudent(@RequestParam(required = false) String term, Pageable pageable) {
        return universityService.pageStudent(pageable,term);
    }
    @PostMapping("/postStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public void postStudent(University student) throws IOException {
        if ((student.getFile() != null) && (!student.getFile().isEmpty())) {
            if (student.getFile().getSize() != 0) {
                byte[] photo = student.getFile().getBytes();
                student.setPhoto(photo);
            }
        }
        universityService.postStudent(student);
    }
    @RequestMapping(value = "/studentPhoto/{studCode}")
    public void getStudImage(HttpServletResponse response, @PathVariable Long studCode)
            throws IOException {
        University stud = universityService.getStudent(studCode);
        if (stud.getStId()!=null && stud.getPhoto()!=null ) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(stud.getPhoto());
            response.getOutputStream().close();
        }
    }

    @RequestMapping(value = "/studentDetails/{stdId}")
    @ResponseBody
    public University studentDetails(HttpServletResponse response, @PathVariable Long stdId) {
        return universityService.getStudent(stdId);
    }
}
