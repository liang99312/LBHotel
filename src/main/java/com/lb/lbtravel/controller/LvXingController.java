/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lb.lbtravel.controller;

import com.lb.lbtravel.domain.DiJieShe;
import com.lb.lbtravel.domain.Page;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lb.lbtravel.domain.LvXing;
import com.lb.lbtravel.domain.XianLu;
import com.lb.lbtravel.service.LvXingService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/lvXing")
public class LvXingController extends BaseController {

    @Resource
    private LvXingService lvXingServiceImpl;

    @RequestMapping("goLvXing.do")
    public String goLvXing() {
        if (!existsUser()) {
            return "../index";
        }
        return "lvXing/lvXing";
    }

    @RequestMapping(value = "saveLvXing.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> saveLvXing(@RequestBody LvXing model) {
        if (!existsUser()) {
            return notLoginResult();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            model.setState(0);
            LvXing lvXing = lvXingServiceImpl.saveLvXing(model);
            map.put("result", 0);
            map.put("lvXing", lvXing);
        } catch (Exception e) {
            map.put("result", -1);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "updateLvXing.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> updateLvXing(@RequestBody LvXing model) {
        if (!existsUser()) {
            return notLoginResult();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            boolean result = lvXingServiceImpl.updateLvXing(model);
            map.put("result", result? 0:-1);
        } catch (Exception e) {
            map.put("result", -1);
            map.put("msg", e.getMessage());
        }
        return map;
    }
    
    @RequestMapping(value = "deleteLvXing.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> deleteLvXing(@RequestParam Integer id) {
        if (!existsUser()) {
            return notLoginResult();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            boolean result = lvXingServiceImpl.deleteLvXing(id);
            map.put("result", result? 0:-1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", -1);
            map.put("msg", e.getMessage());
        }
        return map;
    }
    
    @RequestMapping(value = "getLvXingById.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getLvXingById(@RequestParam Integer id) {
        if (!existsUser()) {
            return notLoginResult();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            LvXing lvXing = lvXingServiceImpl.getLvXingById(id);
            map.put("result", 0);
            map.put("lvXing", lvXing);
        } catch (Exception e) {
            map.put("result", -1);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    //分页查询
    @RequestMapping(value = "listLvXingsByPage.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Page listTeachersByPage(@RequestBody Page model) {
        HashMap map = model.getParamters();
        if (map == null) {
            map = new HashMap();
        }
        if (model.getRows() == 0) {
            model.setRows(this.lvXingServiceImpl.queryRows(map));//查询记录数
        }
        if (model.getRows() == 0) {
            model.setCurrentPage(1);
            model.setList(new ArrayList());
            model.setParamters(new HashMap());
            model.setRows(0);
            model.setTotalPage(0);
            return model;
        }
        if (model.getTotalPage() == 0) {
            model.setTotalPage(model.calcTotalPage());
        }
        map.put("beginRow", model.getBegin());
        map.put("pageSize", model.getPageSize());
        model.setList(this.lvXingServiceImpl.queryLvXingsByPage(map));
        return model;
    }
    
    @RequestMapping(value = "getAllXianLus.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getAllXianLus() {
        if (!existsUser()) {
            return notLoginResult();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<XianLu> xianLuList = new ArrayList<XianLu>();
            xianLuList = lvXingServiceImpl.getAllXianLus();
            map.put("result", 0);
            map.put("sz", xianLuList);
        } catch (Exception e) {
            map.put("result", -1);
            map.put("msg", e.getMessage());
        }
        return map;
    }
    
    @RequestMapping(value = "getAllDiJieShes.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getAllDiJieShes() {
        if (!existsUser()) {
            return notLoginResult();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<DiJieShe> diJieSheList = new ArrayList<DiJieShe>();
            diJieSheList = lvXingServiceImpl.getAllDiJieShes();
            map.put("result", 0);
            map.put("sz", diJieSheList);
        } catch (Exception e) {
            map.put("result", -1);
            map.put("msg", e.getMessage());
        }
        return map;
    }

}
