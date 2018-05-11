/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lb.lbtravel.service.impl;

import com.lb.lbtravel.dao.LvXingDao;
import com.lb.lbtravel.domain.DiJieShe;
import com.lb.lbtravel.domain.LvXing;
import com.lb.lbtravel.domain.XianLu;
import com.lb.lbtravel.service.LvXingService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service("lvXingServiceImpl")
public class LvXingServiceImpl implements LvXingService {

    @Autowired
    private LvXingDao lvXingDao;

    @Override
    public LvXing getLvXingById(Integer id) {
        return (LvXing) lvXingDao.findObjectById(LvXing.class, id);
    }

    @Override
    public boolean updateLvXing(LvXing lvXing) {
        return lvXingDao.update(lvXing);
    }

    @Override
    public LvXing saveLvXing(LvXing lvXing) {
        int id = lvXingDao.save(lvXing);
        return (LvXing) lvXingDao.findObjectById(LvXing.class, id);
    }

    @Override
    public boolean deleteLvXing(Integer id) {
        LvXing lvXing = (LvXing) lvXingDao.findObjectById(LvXing.class, id);
        if(lvXing.getState() == 0){
            lvXing.setState(-1);
            return lvXingDao.update(lvXing);
        }else if(lvXing.getState() == -1){
            return lvXingDao.deleteObjById("lvXing", id);
        }
        return false;
    }

    @Override
    public int queryRows(HashMap map) {
        String sql = "select (1) from LvXing where 1=1";
        if (map.containsKey("mc")) {
            sql += " and mc like '%" + map.get("mc") + "%'";
        }
        if (map.containsKey("state")) {
            sql += " and state = " + map.get("state");
        }
        return lvXingDao.getCount(sql, null);
    }

    @Override
    public List<LvXing> queryLvXingsByPage(HashMap map) {
        String hql = "from LvXing where where 1=1";
        if (map.containsKey("mc")) {
            hql += " and mc like '%" + map.get("mc") + "%'";
        }
        if (map.containsKey("state")) {
            hql += " and state = " + map.get("state");
        }
        return lvXingDao.getPageList(hql, null, 1, 20);
    }

    @Override
    public List<XianLu> getAllXianLus() {
        return lvXingDao.getResult("from XianLu xianLu", null);
    }

    @Override
    public List<DiJieShe> getAllDiJieShes() {
        return lvXingDao.getResult("from DiJieShe diJieShe", null);
    }

}
