package com.high.school.Institution.controller;

import com.high.school.Institution.model.AcademicOrg;
import com.high.school.Institution.repo.SchoolRepo;
import com.high.school.Institution.service.SchoolInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/protected/institution")
public class SchoolController {

   @Autowired
   SchoolInterface schoolInterface;

   @Autowired
   SchoolRepo schoolRepo;

   @InitBinder
   protected void initBinder(WebDataBinder binder) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      dateFormat.setLenient(false);
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
   }

   @GetMapping("/school")
   private String school(Model model) {
      List<AcademicOrg> school=schoolInterface.findSchool();
      if (!(school.isEmpty())) {
         model.addAttribute("id",-2000);
         AcademicOrg school1=schoolInterface.getSchool();
         System.out.println(school1.getSchoolMobile());
         model.addAttribute("school",school1);
         return "school";
      }
      else{
         model.addAttribute("fresh",1);

           return "school";
      }

   }
   @GetMapping("/deleteSchool")
   private String deleteSchool(@RequestParam Long school,Model model) {
          schoolRepo.deleteById(school);
         model.addAttribute("fresh",1);
         return "school";
      }



   @PostMapping("createInstitution")
   public String schoolSave(  AcademicOrg school, Model model) throws IOException {
      AcademicOrg school1=schoolInterface.getSchool();
      if(school1==null){
         if ((school.getFile() != null) && (!school.getFile().isEmpty())) {
            if (school.getFile().getSize()!= 0) {
               byte[] photo = school.getFile().getBytes();
               school.setSchoolLogo(photo);
            }
         }
        AcademicOrg sc=schoolInterface.saveSchool(school);
        model.addAttribute("school",sc);
        return "school";
      }
      else{
        // AcademicOrg school2=schoolInterface.getSchool();
        // model.addAttribute("school",school2);
         model.addAttribute("error","School already exists");
         return "school";
      }
      }


   @GetMapping(value = "/logo")
   public void getImage(HttpServletResponse response) throws IOException, ServletException {
      AcademicOrg school = schoolInterface.getSchool();
      response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
      response.getOutputStream().write(school.getSchoolLogo());
      response.getOutputStream().close();
   }


}
