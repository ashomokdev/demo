package com.comeon.demo.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "dataset")
public class DatasetDTO {
    private EmailsDTO emails;
    private ResourcesDTO resources;
}
