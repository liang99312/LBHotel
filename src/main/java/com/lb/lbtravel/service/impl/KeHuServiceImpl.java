/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lb.lbtravel.service.impl;

import com.lb.lbtravel.dao.KeHuDao;
import com.lb.lbtravel.domain.KeHu;
import com.lb.lbtravel.service.KeHuService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service("keHuServiceImpl")
public class KeHuServiceImpl implements KeHuService {

    @Autowired
    private KeHuDao keHuDao;

    @Override
    public KeHu getKeHuById(Integer id) {
        return (KeHu) keHuDao.findObjectById(KeHu.class, id);
    }

    @Override
    public List<KeHu> getAllKeHus() {
        return keHuDao.getResult("from KeHu keHu", null);
    }

    @Override
    public boolean updateKeHu(KeHu keHu) {
        return keHuDao.update(keHu);
    }

    @Override
    public KeHu saveKeHu(KeHu keHu) {
        int id = keHuDao.save(keHu);
        return (KeHu) keHuDao.findObjectById(KeHu.class, id);
    }

    @Override
    public boolean deleteKeHu(Integer id) {
        KeHu keHu = (KeHu) keHuDao.findObjectById(KeHu.class, id);
        if(keHu.getState() == 0){
            keHu.setState(-1);
            return keHuDao.update(keHu);
        }else if(keHu.getState() == -1){
            return keHuDao.deleteObjById("keHu", id);
        }
        return false;
    }

    @Override
    public int queryRows(HashMap map) {
        String sql = "select count(1) from KeHu where 1=1";
        if (map.containsKey("mc")) {
            sql += " and mc like '%" + map.get("mc") + "%'";
        }
        if (map.containsKey("state")) {
            sql += " and state = " + map.get("state");
        }
        return keHuDao.getCount(sql, null);
    }

    @Override
    public List<KeHu> queryKeHusByPage(HashMap map) {
        String hql = "from KeHu where 1=1";
        if (map.containsKey("mc")) {
            hql += " and mc like '%" + map.get("mc") + "%'";
        }
        if (map.containsKey("state")) {
            hql += " and state = " + map.get("state");
        }
        return keHuDao.getPageList(hql, null, 1, 20);
    }

}
