package com.d3banking.training.service;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;

@Service
public class CheckImageService
{

    @Cacheable("check-image-cache")
    public List<CheckImageDTO> getImage(Long transactionId)
    {
        System.out.println("Fetching Images...............................");
        return Arrays.asList(new CheckImageDTO("/1","front"),
        new CheckImageDTO("/2","back"));
    }
}
