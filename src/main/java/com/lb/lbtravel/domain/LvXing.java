package com.lb.lbtravel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class LvXing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer kh_id;
    private String mc;
    private String dh;
    private String sfz;
    private Date ctsj;//参团时间
    private String ctxl;//参团线路
    private String ctxz;//参团性质
    private String ctdjs;//地接社
    private Float ctfy;//费用
    private Float ctlr;//利润
    private Float ctfj;//返佣
    private Float ctbx;//保险
    private Integer a01_id;
    private String a01mc;
    private String bz;
    private Integer state = 0;

    public LvXing() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKh_id() {
        return kh_id;
    }

    public void setKh_id(Integer kh_id) {
        this.kh_id = kh_id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public Date getCtsj() {
        return ctsj;
    }

    public void setCtsj(Date ctsj) {
        this.ctsj = ctsj;
    }

    public String getCtxl() {
        return ctxl;
    }

    public void setCtxl(String ctxl) {
        this.ctxl = ctxl;
    }

    public String getCtxz() {
        return ctxz;
    }

    public void setCtxz(String ctxz) {
        this.ctxz = ctxz;
    }

    public String getCtdjs() {
        return ctdjs;
    }

    public void setCtdjs(String ctdjs) {
        this.ctdjs = ctdjs;
    }

    public Float getCtfy() {
        return ctfy;
    }

    public void setCtfy(Float ctfy) {
        this.ctfy = ctfy;
    }

    public Float getCtlr() {
        return ctlr;
    }

    public void setCtlr(Float ctlr) {
        this.ctlr = ctlr;
    }

    public Float getCtfj() {
        return ctfj;
    }

    public void setCtfj(Float ctfj) {
        this.ctfj = ctfj;
    }

    public Float getCtbx() {
        return ctbx;
    }

    public void setCtbx(Float ctbx) {
        this.ctbx = ctbx;
    }

    public Integer getA01_id() {
        return a01_id;
    }

    public void setA01_id(Integer a01_id) {
        this.a01_id = a01_id;
    }

    public String getA01mc() {
        return a01mc;
    }

    public void setA01mc(String a01mc) {
        this.a01mc = a01mc;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LvXing other = (LvXing) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
