package com.neighborhood.msbatch.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
@ConfigurationProperties("config-ms-batch")
public class ApplicationPropertiesConfig {

    private int pageSizeLimit;

    private String loanTemplate;

    private String loanObject;

    public int getPageSizeLimit() {
        return pageSizeLimit;
    }

    public void setPageSizeLimit(int pageSizeLimit) {
        this.pageSizeLimit = pageSizeLimit;
    }

    public String getLoanTemplate() {
        return StringUtils.toEncodedString(loanTemplate.getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
    }

    public void setLoanTemplate(String loanTemplate) {
        this.loanTemplate = loanTemplate;
    }

    public String getLoanObject() {
        return StringUtils.toEncodedString(loanObject.getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8"));
    }

    public void setLoanObject(String loanObject) {
        this.loanObject = loanObject;
    }


}
