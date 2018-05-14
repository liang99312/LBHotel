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
        return lvXingDao.updateLvXing(lvXing);
    }

    @Override
    public LvXing saveLvXing(LvXing lvXing) {
        int id = lvXingDao.saveLvXing(lvXing);
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
        String sql = "select count(1) from LvXing where 1=1";
        if (map.containsKey("khmc")) {
            sql += " and khmc like '%" + map.get("khmc") + "%'";
        }
        return lvXingDao.getCount(sql, null);
    }

    @Override
    public List<LvXing> queryLvXingsByPage(HashMap map) {
        String hql = "from LvXing where 1=1";
        if (map.containsKey("khmc")) {
            hql += " and khmc like '%" + map.get("khmc") + "%'";
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

    @Override
    public List<LvXing> tongJiLvXing(LvXing lvXing) {
        String sql = "from LvXing where 1=1";
        if(lvXing.getKhmc() != null && !"".equals(lvXing.getKhmc())){
            sql += " and khmc='" + lvXing.getKhmc() + "'";
        }
        if(lvXing.getCtxl()!= null && !"".equals(lvXing.getCtxl())){
            sql += " and ctxl='" + lvXing.getCtxl() + "'";
        }
        if(lvXing.getCtdjs()!= null && !"".equals(lvXing.getCtdjs())){
            sql += " and ctdjs='" + lvXing.getCtdjs() + "'";
        }
        if(lvXing.getA01mc() != null && !"".equals(lvXing.getA01mc())){
            sql += " and a01mc='" + lvXing.getA01mc() + "'";
        }
        if(lvXing.getSjq()!= null && !"".equals(lvXing.getSjq())){
            sql += " and ctsj > '" + lvXing.getSjq() + "'";
        }
        if(lvXing.getSjz()!= null && !"".equals(lvXing.getSjz())){
            sql += " and ctsj <= '" + lvXing.getSjz() + " 23:59:59" + "'";
        }
        return lvXingDao.getResult(sql,null);
    }

}
