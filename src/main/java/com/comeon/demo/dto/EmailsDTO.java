package com.comeon.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.ArrayList;
@Builder
@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailsDTO {
    private ArrayList<String> email;
}

