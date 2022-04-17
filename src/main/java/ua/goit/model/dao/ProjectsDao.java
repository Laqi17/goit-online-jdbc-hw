package ua.goit.model.dao;

public class ProjectsDao {
    private Integer id;
    private String description;
    private String managerName;
    private Integer companyId;
    private Integer customerId;
    private Integer cost;

    public ProjectsDao() {
    }

    public ProjectsDao(Integer id, String description, String managerName, Integer companyId, Integer customerId, Integer cost) {
        this.id = id;
        this.description = description;
        this.managerName = managerName;
        this.companyId = companyId;
        this.customerId = customerId;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
