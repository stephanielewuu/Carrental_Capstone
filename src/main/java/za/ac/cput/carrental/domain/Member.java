package za.ac.cput.carrental.domain;

public class Member {
    private String memberId;
    private String name;
    private String licenseNumber;
    private int phoneNumber;
    private String accountStatus;

    public Member(){

    }


    private Member(Builder builder) {
        this.memberId = builder.memberId;
        this.name = builder.name;
        this.licenseNumber = builder.licenseNumber;
        this.phoneNumber = builder.phoneNumber;
        this.accountStatus =  builder.accountStatus;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public static class Builder{
        private String memberId;
        private String name;
        private String licenseNumber;
        private int phoneNumber;
        private String accountStatus;

        public Builder setMemberId(String memberId){
            this.memberId = memberId;
            return this;
        }
        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setLicenseNumber(String licenseNumber){
            this.licenseNumber = licenseNumber;
            return this;
        }
        public Builder setPhoneNumber(int phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder setAccountStatus(String accountStatus){
            this.accountStatus = accountStatus;
            return this;
        }

        public Member build(){return new Member(this);}
    }

}


