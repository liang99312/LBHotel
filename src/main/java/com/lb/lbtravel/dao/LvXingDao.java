/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lb.lbtravel.dao;

import com.lb.lbtravel.domain.A01;
import com.lb.lbtravel.domain.DiJieShe;
import com.lb.lbtravel.domain.KeHu;
import com.lb.lbtravel.domain.LvXing;
import com.lb.lbtravel.domain.XianLu;
import com.lb.lbtravel.util.PinYinUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class LvXingDao extends BaseDao {

    public Integer saveLvXing(LvXing lx) {
        Integer result = -1;
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "select 1 from XianLu where mc='"+lx.getCtxl()+"'";
            List list = session.createSQLQuery(sql).list();
            if(list == null || list.isEmpty()){
                XianLu new_xl = new XianLu();
                new_xl.setMc(lx.getCtxl());
                new_xl.setDm(PinYinUtil.ctoE(new_xl.getMc()));
                new_xl.setState(0);
                session.save(new_xl);
                session.flush();
            }
            sql = "select 1 from DiJieShe where mc='"+lx.getCtdjs()+"'";
            list = session.createSQLQuery(sql).list();
            if(list == null || list.isEmpty()){
                DiJieShe new_djs = new DiJieShe();
                new_djs.setMc(lx.getCtdjs());
                new_djs.setDm(PinYinUtil.ctoE(new_djs.getMc()));
                new_djs.setState(0);
                session.save(new_djs);
                session.flush();
            }
            sql = "select 1 from KeHu where sfz='"+lx.getSfz()+"'";
            list = session.createSQLQuery(sql).list();
            if(list == null || list.isEmpty()){
                KeHu new_kh = new KeHu();
                new_kh.setMc(lx.getKhmc());
                new_kh.setDm(PinYinUtil.ctoE(new_kh.getMc()));
                new_kh.setDh(lx.getDh());
                new_kh.setNl(0);
                new_kh.setSfz(lx.getSfz());
                new_kh.setState(0);
                Integer id = (Integer) session.save(new_kh);
                session.flush();
                lx.setKh_id(id);
            }
            sql = "select 1 from A01 where mc='"+lx.getA01mc()+"'";
            list = session.createSQLQuery(sql).list();
            if(list == null || list.isEmpty()){
                A01 new_a01 = new A01();
                new_a01.setMc(lx.getA01mc());
                new_a01.setDm(PinYinUtil.ctoE(new_a01.getMc()));
                new_a01.setState(0);
                Integer id = (Integer) session.save(new_a01);
                session.flush();
                lx.setA01_id(id);
            }
            result = (Integer) session.save(lx);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception he) {
                he.printStackTrace();
            }

        }
        return result;
    }
    
    public boolean updateLvXing(LvXing lx) {
        boolean result = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String sql = "select 1 from XianLu where mc='"+lx.getCtxl()+"'";
            List list = session.createSQLQuery(sql).list();
            if(list == null || list.isEmpty()){
                XianLu new_xl = new XianLu();
                new_xl.setMc(lx.getCtxl());
                new_xl.setDm(PinYinUtil.ctoE(new_xl.getMc()));
                new_xl.setState(0);
                session.save(new_xl);
                session.flush();
            }
            sql = "select 1 from DiJieShe where mc='"+lx.getCtdjs()+"'";
            list = session.createSQLQuery(sql).list();
            if(list == null || list.isEmpty()){
                DiJieShe new_djs = new DiJieShe();
                new_djs.setMc(lx.getCtdjs());
                new_djs.setDm(PinYinUtil.ctoE(new_djs.getMc()));
                new_djs.setState(0);
                session.save(new_djs);
                session.flush();
            }
            sql = "select 1 from KeHu where sfz='"+lx.getSfz()+"'";
            list = session.createSQLQuery(sql).list();
            if(list == null || list.isEmpty()){
                KeHu new_kh = new KeHu();
                new_kh.setMc(lx.getCtdjs());
                new_kh.setDm(PinYinUtil.ctoE(new_kh.getMc()));
                new_kh.setDh(lx.getDh());
                new_kh.setNl(0);
                new_kh.setSfz(lx.getSfz());
                new_kh.setState(0);
                Integer id = (Integer) session.save(new_kh);
                session.flush();
                lx.setKh_id(id);
            }
            sql = "select 1 from A01 where mc='"+lx.getA01mc()+"'";
            list = session.createSQLQuery(sql).list();
            if(list == null || list.isEmpty()){
                A01 new_a01 = new A01();
                new_a01.setMc(lx.getA01mc());
                new_a01.setDm(PinYinUtil.ctoE(new_a01.getMc()));
                new_a01.setState(0);
                Integer id = (Integer) session.save(new_a01);
                session.flush();
                lx.setA01_id(id);
            }
            session.update(lx);
            session.flush();
            tx.commit();
            result = true;
        } catch (Exception e) {
            result = false;
            tx.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception he) {
                he.printStackTrace();
            }

        }
        return result;
    }
}
