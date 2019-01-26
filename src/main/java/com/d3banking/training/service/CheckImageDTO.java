package com.d3banking.training.service;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckImageDTO
{
    private final String url;
    private final String rel;
}
