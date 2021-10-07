package com.comeon.demo.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDTO {
    private List<String> url;
}
