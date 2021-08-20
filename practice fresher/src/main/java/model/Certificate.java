package model;

import java.util.Date;

public class Certificate {
    private Long certificatedID;
    private String certificateName;
    private String certificateRank;
    private Date certificatedDate;

    public Certificate(Long certificatedID, String certificateName, String certificateRank, Date certificatedDate) {
        this.certificatedID = certificatedID;
        this.certificateName = certificateName;
        this.certificateRank = certificateRank;
        this.certificatedDate = certificatedDate;
    }

    public Long getCertificatedID() {
        return certificatedID;
    }

    public void setCertificatedID(Long certificatedID) {
        this.certificatedID = certificatedID;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateRank() {
        return certificateRank;
    }

    public void setCertificateRank(String certificateRank) {
        this.certificateRank = certificateRank;
    }

    public Date getCertificatedDate() {
        return certificatedDate;
    }

    public void setCertificatedDate(Date certificatedDate) {
        this.certificatedDate = certificatedDate;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificatedID=" + certificatedID +
                ", certificateName='" + certificateName + '\'' +
                ", certificateRank='" + certificateRank + '\'' +
                ", certificatedDate=" + certificatedDate +
                '}';
    }
}
