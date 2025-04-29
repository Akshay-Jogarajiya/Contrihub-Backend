package com.contribhub.contribhubbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class StackOverflowResponseDTO {

    private List<StackOverflowUserDTO> items;
}
