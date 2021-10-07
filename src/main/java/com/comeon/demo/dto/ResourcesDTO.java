package com.comeon.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;

@Builder
@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDTO {
    private ArrayList<String> url;
}
