package com.sven.facialid.service.faceplusplus.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiOperator 
{
    @Value("${face.api.key}")
    private String apiKey;
    
    @Value("${face.api.secret}")
    private String apiSecret;
    
    @Value("${face.api.base}")
    private String baseUrl;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public String getApiKey()
    {
        return apiKey;
    }
    public void setApiKey(final String apiKey)
    {
        this.apiKey = apiKey;
    }
    public String getApiSecret()
    {
        return apiSecret;
    }
    public void setApiSecret(final String apiSecret)
    {
        this.apiSecret = apiSecret;
    }
    public String getBaseUrl()
    {
        return baseUrl;
    }
    public void setBaseUrl(final String baseUrl)
    {
        this.baseUrl = baseUrl;
    }
    public RestTemplate getRestTemplate()
    {
        return restTemplate;
    }
    public void setRestTemplate(final RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }
    
    
}
