/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import com.google.common.base.Preconditions;
import java.util.ArrayList;

/**
 *
 * @author omnya
 */
public class CustomerProfile {

    int customerID;
    int parentId;
    String customerName;
    String customerArabicName;
    String customerQuestion;
    String customerAnswer;
    String customerPhone;
    String email;
    String alternativeEmail;
    String alternativePhone;
    String country;
    String governorate;
    String city;
    String address1;
    String address2;
    String postelCode;
    String nationalID;
    String birthDate;
    String preferredLang;
    String landLinePhone;
    String autoAllocate;
    String canAddEmp;
    String accountType;
    String gender;
    String shopName;
    String ActivityType;
    String image1Url;
    String image2Url;
    String image3Url;
    String image4Url;
    String image5Url;

    AddressDTO homeAddress;
    AddressDTO workAddress;

    public String getImage1Url() {
        return image1Url;
    }

    public void setImage1Url(String image1Url) {
        this.image1Url = image1Url;
    }

    public String getImage2Url() {
        return image2Url;
    }

    public void setImage2Url(String image2Url) {
        this.image2Url = image2Url;
    }

    public String getImage3Url() {
        return image3Url;
    }

    public void setImage3Url(String image3Url) {
        this.image3Url = image3Url;
    }

    public String getImage4Url() {
        return image4Url;
    }

    public void setImage4Url(String image4Url) {
        this.image4Url = image4Url;
    }

    public String getImage5Url() {
        return image5Url;
    }

    public void setImage5Url(String image5Url) {
        this.image5Url = image5Url;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getActivityType() {
        return ActivityType;
    }

    public void setActivityType(String ActivityType) {
        this.ActivityType = ActivityType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public AddressDTO getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(AddressDTO homeAddress) {
        this.homeAddress = homeAddress;
    }

    public AddressDTO getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(AddressDTO workAddress) {
        this.workAddress = workAddress;
    }
    ArrayList<String> serviceList;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<String> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<String> serviceList) {
        this.serviceList = serviceList;
    }

    public String getAutoAllocate() {
        return autoAllocate;
    }

    public void setAutoAllocate(String autoAllocate) {
        this.autoAllocate = autoAllocate;
    }

    public String getCanAddEmp() {
        return canAddEmp;
    }

    public void setCanAddEmp(String canAddEmp) {
        this.canAddEmp = canAddEmp;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAlternativeEmail() {
        return alternativeEmail;
    }

    public void setAlternativeEmail(String alternativeEmail) {
        this.alternativeEmail = alternativeEmail;
    }

    public String getAlternativePhone() {
        return alternativePhone;
    }

    public void setAlternativePhone(String alternativePhone) {
        this.alternativePhone = alternativePhone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerAnswer() {
        return customerAnswer;
    }

    public void setCustomerAnswer(String customerAnswer) {
        this.customerAnswer = customerAnswer;
    }

    public String getCustomerArabicName() {
        return customerArabicName;
    }

    public void setCustomerArabicName(String customerArabicName) {
        this.customerArabicName = customerArabicName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerQuestion() {
        return customerQuestion;
    }

    public void setCustomerQuestion(String customerQuestion) {
        this.customerQuestion = customerQuestion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getLandLinePhone() {
        return landLinePhone;
    }

    public void setLandLinePhone(String landLinePhone) {
        this.landLinePhone = landLinePhone;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getPostelCode() {
        return postelCode;
    }

    public void setPostelCode(String postelCode) {
        this.postelCode = postelCode;
    }

    public String getPreferredLang() {
        return preferredLang;
    }

    public void setPreferredLang(String preferredLang) {
        this.preferredLang = preferredLang;
    }

    public static boolean checkMandatoryFiledsForCreate(CustomerProfile cp) {
        try {
            Preconditions.checkNotNull(cp.getCustomerArabicName());
            if (cp.getCustomerArabicName().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getCustomerName());
            if (cp.getCustomerName().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getCustomerPhone());
            if (cp.getCustomerPhone().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getAccountType());
            if (cp.getAccountType().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getGender());
            if (cp.getGender().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getBirthDate());
            if (cp.getBirthDate().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getNationalID());
            if (cp.getNationalID().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getCustomerQuestion());
            if (cp.getCustomerQuestion().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getCustomerAnswer());
            if (cp.getCustomerAnswer().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getHomeAddress().getAddressDetails());
            if (cp.getHomeAddress().getAddressDetails().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getHomeAddress().getCity());
            if (cp.getHomeAddress().getCity().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getHomeAddress().getGovernate());
            if (cp.getHomeAddress().getGovernate().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getHomeAddress().getRegion());
            if (cp.getHomeAddress().getRegion().trim().isEmpty()) {
                return false;
            }
            Preconditions.checkNotNull(cp.getShopName());
            if (cp.getShopName().trim().isEmpty()) {
                return false;
            }
            if (cp.getAccountType().equals("merchant")) {
                Preconditions.checkNotNull(cp.getWorkAddress().getAddressDetails());
                if (cp.getWorkAddress().getAddressDetails().trim().isEmpty()) {
                    return false;
                }
                Preconditions.checkNotNull(cp.getWorkAddress().getCity());
                if (cp.getWorkAddress().getCity().trim().isEmpty()) {
                    return false;
                }
                Preconditions.checkNotNull(cp.getWorkAddress().getGovernate());
                if (cp.getWorkAddress().getGovernate().trim().isEmpty()) {
                    return false;
                }
                Preconditions.checkNotNull(cp.getWorkAddress().getRegion());
                if (cp.getWorkAddress().getRegion().trim().isEmpty()) {
                    return false;
                }

                Preconditions.checkNotNull(cp.getAccountType());
                if (cp.getAccountType().trim().isEmpty()) {
                    return false;
                }

            }
        } catch (NullPointerException ex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CustomerProfile{" + "customerID=" + customerID + ", parentId=" + parentId + ", customerName=" + customerName + ", customerArabicName=" + customerArabicName + ", customerQuestion=" + customerQuestion + ", customerAnswer=" + customerAnswer + ", customerPhone=" + customerPhone + ", email=" + email + ", alternativeEmail=" + alternativeEmail + ", alternativePhone=" + alternativePhone + ", country=" + country + ", governorate=" + governorate + ", city=" + city + ", address1=" + address1 + ", address2=" + address2 + ", postelCode=" + postelCode + ", nationalID=" + nationalID + ", birthDate=" + birthDate + ", preferredLang=" + preferredLang + ", landLinePhone=" + landLinePhone + ", autoAllocate=" + autoAllocate + ", canAddEmp=" + canAddEmp + ", accountType=" + accountType + ", gender=" + gender + ", shopName=" + shopName + ", ActivityType=" + ActivityType + ", image1Url=" + image1Url + ", image2Url=" + image2Url + ", image3Url=" + image3Url + ", image4Url=" + image4Url + ", image5Url=" + image5Url + ", homeAddress=" + homeAddress + ", workAddress=" + workAddress + ", serviceList=" + serviceList + '}';
    }

}
