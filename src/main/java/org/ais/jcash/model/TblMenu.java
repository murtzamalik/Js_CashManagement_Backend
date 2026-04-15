package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_MENU database table.
 * 
 */
@Entity
@Table(name="TBL_MENU")
@NamedQuery(name="TblMenu.findAll", query="SELECT t FROM TblMenu t")
public class TblMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_MENU_MENUID_GENERATOR", sequenceName="TBL_MENU_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_MENU_MENUID_GENERATOR")
	@Column(name="MENU_ID")
	private long menuId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="ICON_NAME")
	private String iconName;

	@Column(name="ICON_PATH")
	private String iconPath;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="MENU_CODE")
	private String menuCode;

	@Column(name="MENU_DESCRIPTION")
	private String menuDescription;

	@Column(name="MENU_PATH")
	private String menuPath;

	@Column(name="MENU_TYPE")
	private String menuType;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblMenu
	@ManyToOne
	@JoinColumn(name="PARENT_MENU")
	private TblMenu tblMenu;

	//bi-directional many-to-one association to TblMenu
	@JsonIgnore
	@OneToMany(mappedBy="tblMenu")
	private List<TblMenu> tblMenus;

	//bi-directional many-to-one association to TblModule
	@ManyToOne
	@JoinColumn(name="MODULE_ID")
	private TblModule tblModule;

	//bi-directional many-to-one association to TblRoleRight
	@JsonIgnore
	@OneToMany(mappedBy="tblMenu")
	private List<TblRoleRight> tblRoleRights;

	public TblMenu() {
	}

	public long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckerComments() {
		return this.checkerComments;
	}

	public void setCheckerComments(String checkerComments) {
		this.checkerComments = checkerComments;
	}

	public BigDecimal getCheckerId() {
		return this.checkerId;
	}

	public void setCheckerId(BigDecimal checkerId) {
		this.checkerId = checkerId;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public String getIconName() {
		return this.iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getIconPath() {
		return this.iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public Date getLastupdatedate() {
		return this.lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public BigDecimal getLastupdateuser() {
		return this.lastupdateuser;
	}

	public void setLastupdateuser(BigDecimal lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getMcStatus() {
		return this.mcStatus;
	}

	public void setMcStatus(String mcStatus) {
		this.mcStatus = mcStatus;
	}

	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuDescription() {
		return this.menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public String getMenuPath() {
		return this.menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblMenu getTblMenu() {
		return this.tblMenu;
	}

	public void setTblMenu(TblMenu tblMenu) {
		this.tblMenu = tblMenu;
	}

	public List<TblMenu> getTblMenus() {
		return this.tblMenus;
	}

	public void setTblMenus(List<TblMenu> tblMenus) {
		this.tblMenus = tblMenus;
	}

	public TblMenu addTblMenus(TblMenu tblMenus) {
		getTblMenus().add(tblMenus);
		tblMenus.setTblMenu(this);

		return tblMenus;
	}

	public TblMenu removeTblMenus(TblMenu tblMenus) {
		getTblMenus().remove(tblMenus);
		tblMenus.setTblMenu(null);

		return tblMenus;
	}

	public TblModule getTblModule() {
		return this.tblModule;
	}

	public void setTblModule(TblModule tblModule) {
		this.tblModule = tblModule;
	}

	public List<TblRoleRight> getTblRoleRights() {
		return this.tblRoleRights;
	}

	public void setTblRoleRights(List<TblRoleRight> tblRoleRights) {
		this.tblRoleRights = tblRoleRights;
	}

	public TblRoleRight addTblRoleRight(TblRoleRight tblRoleRight) {
		getTblRoleRights().add(tblRoleRight);
		tblRoleRight.setTblMenu(this);

		return tblRoleRight;
	}

	public TblRoleRight removeTblRoleRight(TblRoleRight tblRoleRight) {
		getTblRoleRights().remove(tblRoleRight);
		tblRoleRight.setTblMenu(null);

		return tblRoleRight;
	}

}