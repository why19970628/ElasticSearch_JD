package com.kuang.kuangshenesjd.controller;

import com.kuang.kuangshenesjd.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ContentController {
    
    
    @Autowired
    private ContentService contentService;
    
    @GetMapping("/parse/{keyword}")
    @ResponseBody
    public Boolean parse(@PathVariable("keyword") String keyword) throws IOException {
        return contentService.parseContent(keyword);
    }
    
    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public String search(@PathVariable("keyword") String keyword,
                         @PathVariable("pageNo") int pageNo,
                         @PathVariable("pageSize") int pageSize, Model model) throws IOException {
        
        if (pageNo == 0) {
            pageNo = 1;
        }
        System.out.println(keyword + pageNo + pageSize);
        List<Map<String, Object>> list =  contentService.searchPageHighlighter(keyword, pageNo, pageSize);
        model.addAttribute("list", list);
        return "index";
    }
}
