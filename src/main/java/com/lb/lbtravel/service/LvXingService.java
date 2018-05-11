/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lb.lbtravel.service;

import com.lb.lbtravel.domain.DiJieShe;
import com.lb.lbtravel.domain.LvXing;
import com.lb.lbtravel.domain.XianLu;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface LvXingService {

    public LvXing getLvXingById(Integer id);
        
    public boolean updateLvXing(LvXing lvXing);
    
    public LvXing saveLvXing(LvXing lvXing);
    
    public boolean deleteLvXing(Integer id);
    
    public int queryRows(HashMap map);
    
    public List<LvXing> queryLvXingsByPage(HashMap map);
    
    public List<XianLu> getAllXianLus();
    
    public List<DiJieShe> getAllDiJieShes();

}
