/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author domak
 */
@Entity
@Table(name = "enemy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enemy.findAll", query = "SELECT e FROM Enemy e")
    , @NamedQuery(name = "Enemy.findByEid", query = "SELECT e FROM Enemy e WHERE e.eid = :eid")
    , @NamedQuery(name = "Enemy.findByName", query = "SELECT e FROM Enemy e WHERE e.name = :name")
    , @NamedQuery(name = "Enemy.findByDmg", query = "SELECT e FROM Enemy e WHERE e.dmg = :dmg")
    , @NamedQuery(name = "Enemy.findByHp", query = "SELECT e FROM Enemy e WHERE e.hp = :hp")
    , @NamedQuery(name = "Enemy.findByDifficulty", query = "SELECT e FROM Enemy e WHERE e.difficulty = :difficulty")})
public class Enemy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EID")
    private Long eid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;
    @Column(name = "DMG")
    private BigInteger dmg;
    @Column(name = "HP")
    private BigInteger hp;
    @Column(name = "Difficulty")
    private BigInteger difficulty;

    public Enemy() {
    }

    public Enemy(Long eid) {
        this.eid = eid;
    }

    public Enemy(Long eid, String name) {
        this.eid = eid;
        this.name = name;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getDmg() {
        return dmg;
    }

    public void setDmg(BigInteger dmg) {
        this.dmg = dmg;
    }

    public BigInteger getHp() {
        return hp;
    }

    public void setHp(BigInteger hp) {
        this.hp = hp;
    }

    public BigInteger getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigInteger difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eid != null ? eid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enemy)) {
            return false;
        }
        Enemy other = (Enemy) object;
        if ((this.eid == null && other.eid != null) || (this.eid != null && !this.eid.equals(other.eid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Enemy[ eid=" + eid + " ]";
    }
    
}
