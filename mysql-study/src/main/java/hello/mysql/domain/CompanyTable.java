package hello.mysql.domain;

public class CompanyTable {
	private int siteId;
	private String siteType;
	private String companyId;
	private String siteName;
	private String siteStatus;
	private String siteCalculate;
	private String siteProtocol;
	private String siteUrlDetail;
	private String siteGearingUrl;
	private String siteManagerName;
	private String siteManagerNumber;
	private String siteManagerEmail;
	private String siteRegDTTM;
	private String siteModDTTM;
	private String siteRegUser;
	private String siteModUser;
	private String companynm;
	
	public CompanyTable() {}//default »ý¼ºÀÚ
	
	public CompanyTable(int siteId, String siteType, String companyId, String siteName, String siteStatus,
			String siteCalculate, String siteProtocol, String siteUrlDetail, String siteGearingUrl,
			String siteManagerName, String siteManagerNumber, String siteManagerEmail, String siteRegDTTM,
			String siteModDTTM, String siteRegUser, String siteModUser, String companynm) {
		super();
		this.siteId = siteId;
		this.siteType = siteType;
		this.companyId = companyId;
		this.siteName = siteName;
		this.siteStatus = siteStatus;
		this.siteCalculate = siteCalculate;
		this.siteProtocol = siteProtocol;
		this.siteUrlDetail = siteUrlDetail;
		this.siteGearingUrl = siteGearingUrl;
		this.siteManagerName = siteManagerName;
		this.siteManagerNumber = siteManagerNumber;
		this.siteManagerEmail = siteManagerEmail;
		this.siteRegDTTM = siteRegDTTM;
		this.siteModDTTM = siteModDTTM;
		this.siteRegUser = siteRegUser;
		this.siteModUser = siteModUser;
		this.companynm = companynm;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteStatus() {
		return siteStatus;
	}

	public void setSiteStatus(String siteStatus) {
		this.siteStatus = siteStatus;
	}

	public String getSiteCalculate() {
		return siteCalculate;
	}

	public void setSiteCalculate(String siteCalculate) {
		this.siteCalculate = siteCalculate;
	}

	public String getSiteProtocol() {
		return siteProtocol;
	}

	public void setSiteProtocol(String siteProtocol) {
		this.siteProtocol = siteProtocol;
	}

	public String getSiteUrlDetail() {
		return siteUrlDetail;
	}

	public void setSiteUrlDetail(String siteUrlDetail) {
		this.siteUrlDetail = siteUrlDetail;
	}

	public String getSiteManagerName() {
		return siteManagerName;
	}

	public void setSiteManagerName(String siteManagerName) {
		this.siteManagerName = siteManagerName;
	}

	public String getSiteManagerNumber() {
		return siteManagerNumber;
	}

	public void setSiteManagerNumber(String siteManagerNumber) {
		this.siteManagerNumber = siteManagerNumber;
	}

	public String getSiteManagerEmail() {
		return siteManagerEmail;
	}

	public void setSiteManagerEmail(String siteManagerEmail) {
		this.siteManagerEmail = siteManagerEmail;
	}

	public String getSiteRegDTTM() {
		return siteRegDTTM;
	}

	public void setSiteRegDTTM(String siteRegDTTM) {
		this.siteRegDTTM = siteRegDTTM;
	}

	public String getSiteModDTTM() {
		return siteModDTTM;
	}

	public void setSiteModDTTM(String siteModDTTM) {
		this.siteModDTTM = siteModDTTM;
	}

	public String getSiteRegUser() {
		return siteRegUser;
	}

	public void setSiteRegUser(String siteRegUser) {
		this.siteRegUser = siteRegUser;
	}

	public String getSiteModUser() {
		return siteModUser;
	}

	public void setSiteModUser(String siteModUser) {
		this.siteModUser = siteModUser;
	}

	public String getCompanynm() {
		return companynm;
	}

	public void setCompanynm(String companynm) {
		this.companynm = companynm;
	}

	@Override
	public String toString() {
		return "CompanyTable [siteId=" + siteId + ", companyId=" + companyId + ", siteName=" + siteName
				+ ", siteStatus=" + siteStatus + ", siteCalculate=" + siteCalculate + ", siteProtocol=" + siteProtocol
				+ ", siteUrlDetail=" + siteUrlDetail + ", siteManagerName=" + siteManagerName + ", siteManagerNumber="
				+ siteManagerNumber + ", siteManagerEmail=" + siteManagerEmail + ", siteRegDTTM=" + siteRegDTTM
				+ ", siteModDTTM=" + siteModDTTM + ", siteRegUser=" + siteRegUser + ", siteModUser=" + siteModUser
				+ ", companynm=" + companynm + "]";
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}
	public String getSiteGearingUrl() {
		return siteGearingUrl;
	}
	public void setSiteGearingUrl(String siteGearingUrl) {
		this.siteGearingUrl = siteGearingUrl;
	}

	
	
	
}
