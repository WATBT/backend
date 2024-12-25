package alter.alter_core.dto;

import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.bridge.IMessage;
import org.hibernate.annotations.NotFound;

public class CompanyDTO {

    @NotNull()
    private String name;

    @NotNull()
    private String companyNo;

    private String phoneNumber;

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "name='" + name + '\'' +
                ", companyNo='" + companyNo + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
